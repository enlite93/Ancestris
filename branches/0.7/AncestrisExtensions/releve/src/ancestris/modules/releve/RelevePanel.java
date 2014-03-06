/*
 * RelevePanel.java
 *
 * Created on 25 mars 2012, 16:43:34
 */

package ancestris.modules.releve;

import ancestris.modules.releve.dnd.RecordTransferHandle;
import ancestris.modules.releve.table.TableSelectionListener;
import ancestris.modules.releve.editor.ReleveEditor;
import ancestris.modules.releve.model.DataManager;
import ancestris.modules.releve.model.Field;
import ancestris.modules.releve.model.Record;
import ancestris.modules.releve.table.TableModelRecordAbstract;
import ancestris.modules.releve.table.TableModelRecordAll;
import ancestris.modules.releve.table.TableModelRecordBirth;
import ancestris.modules.releve.table.TableModelRecordDeath;
import ancestris.modules.releve.table.TableModelRecordMarriage;
import ancestris.modules.releve.table.TableModelRecordMisc;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.AbstractAction;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Michel
 */
public class RelevePanel extends javax.swing.JPanel implements ReleveEditorListener, TableSelectionListener  {
    private boolean standaloneMode = false;
    private MenuCommandProvider menuCommandProvider;
    private DataManager dataManager = null;
    private PanelType panelType = null;
    private DataManager.RecordType recordType = null;
    private int currentRecordIndex = -1;

    static private String dialogTitle = NbBundle.getMessage(RelevePanel.class,"DialogTitle");
    static public enum PanelType { birth, marriage, death, misc, all }


    
    /** Creates new form RelevePanel */
    public RelevePanel() {
        initComponents();
        // J'applique un poids=1 pour que seule la largeur du composant de gauche soit mdofiée quand on change la taille de la fenetre
        jSplitPane1.setResizeWeight(1.0);
        // j'ajoute l'editeur a l'ecoute de la selection de ligne dans la table
        //releveTable.setTableSelectionListener(releveEditor);

        // je force la largeur du jButtonFile pour contenir le texte en entier et
        // et la hauteur egale aux autres boutons
        Rectangle2D rect = jButtonFile.getFont().getStringBounds(jButtonFile.getText(), jButtonFile.getFontMetrics(jButtonFile.getFont()).getFontRenderContext());
        jButtonFile.setPreferredSize(new Dimension((int)rect.getWidth()+jButtonFile.getMargin().left+jButtonFile.getMargin().right+8+jButtonFile.getInsets().left+jButtonFile.getInsets().right, 25));

        jButtonPrevious.setVisible(false);
        jTextFielRecordNo.setVisible(false);
        jButtonNext.setVisible(false);

        jButtonDelete.setEnabled(false);

        // je crée les raccourcis pour créer les nouveaux relevés
        String shortCut = "PanelShortcut";
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), shortCut);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("alt Z"), shortCut);


        getActionMap().put(shortCut, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( actionEvent.getActionCommand().toUpperCase().equals("C") ) {
                    jButtonNewActionPerformed(actionEvent);
                } else if ( actionEvent.getActionCommand().toUpperCase().equals("S") ) {
                    jButtonDeleteActionPerformed(actionEvent);
                } else if ( actionEvent.getActionCommand().toUpperCase().equals("Z") ) {
                    Record record = dataManager.getDataModel().undo();
                    if (record != null ) {
                        selectRecord(dataManager.getDataModel().getIndex(record));
                    } else {
                        selectRecord(-1);
                    }
                }
            }

        });
    }

    public void setModel(final DataManager dataManager, PanelType panelType,
            MenuCommandProvider menuComandProvider) {

        this.menuCommandProvider = menuComandProvider;
        this.dataManager = dataManager;
        this.panelType = panelType;

        TableModelRecordAbstract tableModel;
        switch (panelType) {
            case birth:
                tableModel = new TableModelRecordBirth(dataManager);
                break;
            case death:
                tableModel = new TableModelRecordDeath(dataManager);
                break;
            case marriage:
                tableModel = new TableModelRecordMarriage(dataManager);
                break;
            case misc:
                tableModel = new TableModelRecordMisc(dataManager);
                break;
            default:
                tableModel = new TableModelRecordAll(dataManager);

        }

        releveTable.setTableSelectionListener(this);
        releveTable.setModel(tableModel);
        releveTable.setFocusable(true);
        releveTable.requestFocus();
        releveTable.setDropMode(DropMode.USE_SELECTION);
        releveTable.setTransferHandler(new RecordTransferHandle(dataManager));
        releveTable.setDragEnabled(true);

        releveEditor.setModel(dataManager, menuComandProvider);

        
        jScrollPaneTable.setViewportView(releveTable);
        jSplitPane1.setResizeWeight(1.0);
        
        // je recupere le toolTipText du bouton
        String toolTipText = org.openide.util.NbBundle.getMessage(ReleveEditor.class, "ReleveEditor.jButtonNew.toolTipText");

        // je complete le toolTipText du bouton "créer" en fonction du modele
        switch (panelType) {
            case birth:
                jButtonNew.setToolTipText(toolTipText+ " (ALT-N)");
                recordType = DataManager.RecordType.birth;
                break;
            case marriage:
                jButtonNew.setToolTipText(toolTipText+ " (ALT-M)");
                recordType = DataManager.RecordType.marriage;
                break;
            case death:
                jButtonNew.setToolTipText(toolTipText+ " (ALT-D)");
                recordType = DataManager.RecordType.death;
                break;
            case misc:
                jButtonNew.setToolTipText(toolTipText+ " (ALT-V)");
                recordType = DataManager.RecordType.misc;
                break;
             default:
                jButtonNew.setToolTipText(toolTipText+ " (ALT-T)");
                recordType = null;
                break;
        }

        // j'initialise la largeur de l'editeur avec la largeur de la session precedente
        // Remarque : il faut differer le changement de taille car sinon jSplitPane1.getSize() est nul
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
//                jSplitPane1.setDividerLocation(jSplitPane1.getSize().width
//                             - jSplitPane1.getInsets().right
//                             - jSplitPane1.getDividerSize()
//                             - releveEditor.getEditorWidth());

                int editorWidth = getEditorWidth();
                // je dimensionne le panneau droit de jSplitPane1
                if (jSplitPane1.getWidth() > editorWidth) {
                jSplitPane1.setDividerLocation(jSplitPane1.getWidth() - editorWidth - jSplitPane1.getDividerSize());
                }

            }
        });
        
    }

    /**
     * sauvegarde de la configuration a la fermeture du composant
     */
    public void componentClosed() {
        releveTable.componentClosed();
        // je sauvegarde la largeur de l'editeur
//        releveEditor.putEditorWidth(jSplitPane1.getSize().width
//                - jSplitPane1.getInsets().right
//                - jSplitPane1.getDividerSize()
//                - jSplitPane1.getDividerLocation() );

        int editorWidth = jSplitPane1.getWidth() - jSplitPane1.getDividerLocation() - jSplitPane1.getDividerSize();
        putEditorWidth(editorWidth );
    }

    /**
     * selectionne une ligne en fonction de l'index de record du modele
     * @param recordIndex index du relevé
     */
    public void selectRecord(int recordIndex) {
        if ( recordIndex != -1) {
            selectRow(releveTable.convertRowIndexToView(recordIndex));
        } else {
            selectRow((-1));
        }

    }

    /**
     * selectionne une ligne en fonction du numéro de ligne de la table
     * @param rowIndex numero de la ligne dans la table
     */
    public void selectRow(int rowIndex) {
        if (releveTable.getRowCount() > 0 && rowIndex != -1) {
            // je verifie la coherence du releve en cours d'edition
            if ( verifyRecord() ) {
                // je recupere l'index du releve courant dans la table
                currentRecordIndex = releveTable.convertRowIndexToModel(rowIndex);
                // je selectionne la ligne dans la table
                releveTable.selectRecord(currentRecordIndex);

                // j'active le bouton Delete si le releve courant est valide
                // et j'affiche le numero du relevé.
                if (rowIndex != -1) {
                    jButtonDelete.setEnabled(true);
                    jTextFielRecordNo.setText(String.valueOf(currentRecordIndex+1));
                } else {
                    jButtonDelete.setEnabled(false);
                    jTextFielRecordNo.setText("");
                }
            }
        } else {
            releveEditor.selectRecord(-1);
        }
    }

    public void setStandaloneMode() {
        jButtonFile.setVisible(false);
        jButtonPrevious.setVisible(true);
        jButtonNext.setVisible(true);
        jTextFielRecordNo.setVisible(true);
        standaloneMode = true;

        // je masque la table
        tablePanel.setVisible(false);
    }


    /**
     * active le listener de la souris pour l'affichage du popupmenu quand
     * on clique avec le bouton droit de la souris
     */
    @Override
    public void addMouseListener(MouseListener mouseListener) {
        releveTable.addMouseListener(mouseListener);
        jScrollPaneTable.addMouseListener(mouseListener);
        jSplitPane1.addMouseListener(mouseListener);
        tablePanel.addMouseListener(mouseListener);
    }



    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        tablePanel = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        releveTable = new ancestris.modules.releve.table.ReleveTable();
        editorPanel = new javax.swing.JPanel();
        editorBar = new javax.swing.JPanel();
        jButtonFile = new javax.swing.JButton();
        jButtonConfig = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonPrevious = new javax.swing.JButton();
        jTextFielRecordNo = new javax.swing.JTextField();
        jButtonNext = new javax.swing.JButton();
        jButtonStandalone = new javax.swing.JButton();
        releveEditor = new ancestris.modules.releve.editor.ReleveEditor();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setResizeWeight(1.0);

        tablePanel.setPreferredSize(new java.awt.Dimension(0, 0));
        tablePanel.setLayout(new java.awt.BorderLayout());

        jScrollPaneTable.setViewportView(releveTable);

        tablePanel.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(tablePanel);

        editorPanel.setPreferredSize(new java.awt.Dimension(270, 100));
        editorPanel.setLayout(new java.awt.BorderLayout(4, 4));

        editorBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editorBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonFile.setText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonFile.text")); // NOI18N
        jButtonFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonFile.setPreferredSize(new java.awt.Dimension(49, 25));
        jButtonFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileActionPerformed(evt);
            }
        });
        editorBar.add(jButtonFile);

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/Settings.png"))); // NOI18N
        jButtonConfig.setToolTipText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonConfig.toolTipText")); // NOI18N
        jButtonConfig.setPreferredSize(new java.awt.Dimension(29, 25));
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });
        editorBar.add(jButtonConfig);

        jButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/NewRecord.png"))); // NOI18N
        jButtonNew.setToolTipText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonNew.toolTipText")); // NOI18N
        jButtonNew.setActionCommand("CreateRecord"); // NOI18N
        jButtonNew.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonNew.setPreferredSize(new java.awt.Dimension(29, 25));
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        editorBar.add(jButtonNew);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/DeleteRecord.png"))); // NOI18N
        jButtonDelete.setToolTipText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonDelete.toolTipText")); // NOI18N
        jButtonDelete.setActionCommand("RemoveRecord"); // NOI18N
        jButtonDelete.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(29, 25));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        editorBar.add(jButtonDelete);

        jButtonPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/Back.png"))); // NOI18N
        jButtonPrevious.setToolTipText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonPrevious.toolTipText")); // NOI18N
        jButtonPrevious.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });
        editorBar.add(jButtonPrevious);

        jTextFielRecordNo.setEditable(false);
        jTextFielRecordNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFielRecordNo.setPreferredSize(new java.awt.Dimension(32, 20));
        editorBar.add(jTextFielRecordNo);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/Forward.png"))); // NOI18N
        jButtonNext.setToolTipText("Next"); // NOI18N
        jButtonNext.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        editorBar.add(jButtonNext);

        jButtonStandalone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/editor.png"))); // NOI18N
        jButtonStandalone.setToolTipText(org.openide.util.NbBundle.getMessage(RelevePanel.class, "RelevePanel.jButtonStandalone.toolTipText")); // NOI18N
        jButtonStandalone.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonStandalone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStandaloneActionPerformed(evt);
            }
        });
        editorBar.add(jButtonStandalone);

        editorPanel.add(editorBar, java.awt.BorderLayout.NORTH);

        releveEditor.setFont(new java.awt.Font("Arial", 2, 11));
        releveEditor.setMinimumSize(new java.awt.Dimension(100, 300));
        editorPanel.add(releveEditor, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(editorPanel);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileActionPerformed
        // j'affiche le menu Fichier
        menuCommandProvider.showPopupMenu(jButtonFile, 0, jButtonFile.getHeight());
}//GEN-LAST:event_jButtonFileActionPerformed

    private void jButtonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigActionPerformed
        // J'affiche le panneau des options
        menuCommandProvider.showOptionPanel();
}//GEN-LAST:event_jButtonConfigActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        createRecord();
}//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        removeRecord();
}//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        // avant de creer de changer de releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            // je recherche l'index de la ligne precedente
            if ( releveTable.getRowCount() > 0) {
                int rowIndex = releveTable.convertRowIndexToView(currentRecordIndex );

                if ( rowIndex  > 0)  {
                    // je selection la ligne precedente
                    selectRow(rowIndex -1);
                } else {
                    // j'affiche la derniere ligne
                    Toolkit.getDefaultToolkit().beep();
                    selectRow(releveTable.getRowCount() - 1);
                }
            } else {
                // la table est vide
                selectRow(-1);
            }
           

        }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        // avant de changer de releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            // je recherche l'index de la ligne suivante
            if ( releveTable.getRowCount() > 0) {
                int rowIndex = releveTable.convertRowIndexToView(currentRecordIndex);
                if (rowIndex < releveTable.getRowCount() -1 ) {
                    selectRow(rowIndex +1);
                } else {
                    // j'affiche la premiere ligne
                    Toolkit.getDefaultToolkit().beep();
                    selectRow(0);
                }
            } else {
                // la table est vide
                selectRow(-1);
            }

        }
}//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonStandaloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStandaloneActionPerformed
        if ( standaloneMode == false ) {
            // j'affiche l'editeur standalone
            menuCommandProvider.showStandalone();
        } else {
            // j'affiche l'editeur de la fenetre principale au premier plan
            menuCommandProvider.showToFront();
        }
}//GEN-LAST:event_jButtonStandaloneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editorBar;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JButton jButtonConfig;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonFile;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JButton jButtonStandalone;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextFielRecordNo;
    private ancestris.modules.releve.editor.ReleveEditor releveEditor;
    private ancestris.modules.releve.table.ReleveTable releveTable;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables

    void selectField(Field.FieldType fieldType) {
        releveEditor.selectField(fieldType);
    }

    public void createRecord() {
        // avant de creer le nouveau releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            if( panelType != RelevePanel.PanelType.all) {
                // je cree un nouveau releve
                currentRecordIndex = dataManager.addRecord(dataManager.createRecord(recordType));
            } else {
                // je demande de choisir le type du releve
                String title = NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.title");
                String message = NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.createRecord.message");
                String[] options = {
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.birth"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.marriage"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.death"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.misc"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.cancel")};
                Toolkit.getDefaultToolkit().beep();
                int result = JOptionPane.showOptionDialog(this,
                        message,
                        title,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        (javax.swing.Icon) null,
                        options,
                        options[4]);
                switch (result) {
                    case 0:
                        currentRecordIndex = dataManager.addRecord(dataManager.createRecord(DataManager.RecordType.birth));
                        break;
                    case 1:
                        currentRecordIndex = dataManager.addRecord(dataManager.createRecord(DataManager.RecordType.marriage));
                        break;
                    case 2:
                        currentRecordIndex = dataManager.addRecord(dataManager.createRecord(DataManager.RecordType.death));
                        break;
                    case 3:
                        currentRecordIndex = dataManager.addRecord(dataManager.createRecord(DataManager.RecordType.misc));
                        break;
                    default:
                        // j'abandonne la creation du releve
                        return;
                }
            }

            
            // je selectionne le nouveau releve dans la table
            // et le releve est selectionné automatiquement dans l'editeur par la selection dans la table
            releveTable.selectRecord(currentRecordIndex);
            releveEditor.selectFirstField();
            // j'active le bouton de suppression de l'enregistrement
            jButtonDelete.setEnabled(true);
            jTextFielRecordNo.setText(String.valueOf(currentRecordIndex+1));
        }
    }

    void insertRecord() {
        // avant de creer le nouveau releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            // je cree un nouveau releve
            if( panelType != RelevePanel.PanelType.all) {
                dataManager.insertRecord(recordType, currentRecordIndex);
            } else {
                String title = NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.title");
                String message = NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.createRecord.message");
                String[] options = {
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.birth"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.marriage"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.death"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.misc"),
                    NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.cancel")};
                Toolkit.getDefaultToolkit().beep();
                int result = JOptionPane.showOptionDialog(this,
                        message,
                        title,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        (javax.swing.Icon) null,
                        options,
                        options[4]);
                switch (result) {
                    case 0:
                        dataManager.insertRecord(DataManager.RecordType.birth, currentRecordIndex);
                        break;
                    case 1:
                        dataManager.insertRecord(DataManager.RecordType.marriage, currentRecordIndex);
                        break;
                    case 2:
                        dataManager.insertRecord(DataManager.RecordType.death, currentRecordIndex);
                        break;
                    case 3:
                        dataManager.insertRecord(DataManager.RecordType.misc, currentRecordIndex);
                        break;
                    default:
                        // j'abandonne la creation du releve
                        return;
                }
            }
            releveTable.selectRecord(currentRecordIndex);
            releveEditor.selectFirstField();
            jButtonDelete.setEnabled(true);
            jTextFielRecordNo.setText(String.valueOf(currentRecordIndex+1));
        } 
    }

    @Override
    public void swapRecordNext() {
        // avant de deplacer le  releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            // j'echange la position du releve avec le suivant
            dataManager.swapRecordNext(dataManager.getRecord(currentRecordIndex));
            releveTable.selectRecord(currentRecordIndex+1);
        }
    }

    @Override
    public void swapRecordPrevious() {
        // avant de déplacer le  releve , je verifie la coherence du releve courant
        if (verifyRecord()) {
            // j'echange la position du releve avec le suivant
            dataManager.swapRecordPrevious(dataManager.getRecord(currentRecordIndex));
            releveTable.selectRecord(currentRecordIndex-1);
        }
    }

    public void removeRecord() {

         Toolkit.getDefaultToolkit().beep();
        int choice = JOptionPane.showConfirmDialog(this,
                "Confirmez-vous la suppression ?",
                dialogTitle,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
                );
        switch (choice)  {
            case 0: // YES
                //removeRecord();
                break;
            default: // CANCEL
                //rien à faire
                return;
        }
        // je memorise l'index de la ligne qui va être supprimée
        int rowIndex = releveTable.convertRowIndexToView(currentRecordIndex);
        dataManager.removeRecord(dataManager.getRecord(currentRecordIndex));

        if( rowIndex < releveTable.getRowCount()) {
            // je selectionne la ligne suivante
            selectRow(rowIndex);
        } else {
            // il n'y a pas de ligne suivante
            if ( rowIndex > 0) {
                // je selectionne la ligne precedente
                selectRow(rowIndex-1);
            } else {
                // il n'y a plus de lignes
                selectRow(-1);
            }
        }
    }

    @Override
    public boolean verifyRecord() {
        releveEditor.commitCurrentFocusedBean();
        String errorMessage = dataManager.verifyRecord(currentRecordIndex);
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // j'affiche le message d'erreur  et je demande s'il faut continuer
            Toolkit.getDefaultToolkit().beep();
            errorMessage += NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.verifyRecord.message");
            int choice = JOptionPane.showConfirmDialog(this,
                errorMessage,
                NbBundle.getMessage(ReleveTopComponent.class, "RelevePanel.verifyRecord.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE
            );

            switch (choice) {
                case 0: // YES
                    return false;
                case 1: // NO
                    return true;
                default: // CANCEL
                    return true;
            }
        }

    }

    public int getEditorWidth() {
        return Integer.valueOf(NbPreferences.forModule(RelevePanel.class).get(
                panelType.name() + "Width", "270"));
    }

    public void putEditorWidth(int width) {
        NbPreferences.forModule(RelevePanel.class).put(
                panelType.name() + "Width", String.valueOf(width));
    }


    ///////////////////////////////////////////////////////////////////////////
    // Implement TableSelectionListener methods
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void tableRecordSelected(int recordIndex, boolean isNew) {
        releveEditor.selectRecord(recordIndex);
        // je memorise le numero du releve
        currentRecordIndex = recordIndex;
    }

    /**
     * retourne l'index du releve courant
     * @return
     */
    @Override
    public int getCurrentRecordIndex() {
        return currentRecordIndex;
    }


    

}