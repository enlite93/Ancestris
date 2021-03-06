/*
 * MergePanel.java
 *
 */

package ancestris.modules.releve.dnd;

import ancestris.modules.releve.dnd.MergeRecord.MergeParticipantType;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Indi;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 */
public class MergePanel extends javax.swing.JPanel  {

    private MergeDialog mergeDialog = null;
    private MergeModel currentModel = null;
    private MergeParticipantType participant = null;

    private JPopupMenu popupMenu;
    MouseAdapter mouseAdapter;
    private JMenuItem menuItemImportClipboard = new JMenuItem(NbBundle.getMessage(MergePanel.class, "MergePanel.menu.copyToClipboard"));


    /**
     * le construteur initialise l'affichage
     */
    public MergePanel() {        
        initComponents();

         //je cree le popupmenu
        ActionListener popupActionListener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuItemImportClipboard.equals(e.getSource())) {
                    // je copie le relevé
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection sel = new StringSelection(currentModel.record.getEventComment(currentModel.showFrenchCalendarDate));
                    clipboard.setContents(sel, sel);
                }
            }

        };
        popupMenu = new JPopupMenu();
        menuItemImportClipboard.addActionListener(popupActionListener);
        //menuItemImportClipboard.setIcon(new ImageIcon(getClass().getResource("/ancestris/modules/releve/images/NewFile.png")));
        popupMenu.add(menuItemImportClipboard);

        // je branche le clic du bouton droit de la souris sur l'afffichage
        // du popupmenu
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        };
        mergeTable1.addMouseListener(mouseAdapter);
        
        


    }

    /**
     * affiche les modeles contenant les comparaisons du releve avec les entites
     * qui peuvent être concernées par le relevé,
     * et selectionne le premier modele de la liste classée par ordre decroissant de pertinence.
     *
     * @param models    liste des modeles
     * @param selectedEntity  entite selectonne
     * @param mergeDialog     fenetre principale
     */
    protected void initData (final List<MergeModel> models, Entity selectedEntity, final MergeDialog mergeDialog , MergeParticipantType participantType) {
        
        this.mergeDialog = mergeDialog;
        this.participant = participantType;
        
        // je vide le panneau
        jPanelChoice.removeAll();
        jToggleButtonShowAllParents.setSelected(mergeDialog.getShowAllParents());

        // je dimensionne le panneau avec la taille choisie precedemment
        String splitHeight = NbPreferences.forModule(MergeDialog.class).get("MergeDialogSplitHeight"+participant.name(), "90");
        jSplitPane.setDividerLocation(Integer.parseInt(splitHeight));

        // j'ajoute les modeles avec un radio bouton pour chaque modele
        buttonGroupChoiceModel=new javax.swing.ButtonGroup();

        int position = 0;
        for(int i= 0; i< models.size(); i++) {
            MergeModel mergeModel = models.get(i);
            if ( mergeModel.getParticipantType() == participantType) {
                addRadioButton(position, mergeModel, selectedEntity);
                position++;
            }
        }

        if ( buttonGroupChoiceModel.getButtonCount() >0 ) {
            // j'ajoute un label pour occuper le bas du panel
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = buttonGroupChoiceModel.getButtonCount();
            gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.weighty = 1.0;
            JLabel jLabelEnd = new javax.swing.JLabel();
            jPanelChoice.add(jLabelEnd, gridBagConstraints);

            // je selectionne le premier modele
            JRadioButton radioButton0 = (JRadioButton)buttonGroupChoiceModel.getElements().nextElement();

            // j'affiche le titre
            jLabel1.setText(MessageFormat.format(NbBundle.getMessage(MergePanel.class, "MergePanel.title"), buttonGroupChoiceModel.getButtonCount())); // NOI18N

            //radioButton0.setSelected(true);
            radioButton0.doClick();
            //selectModel(models.get(0));
        } else {
            setVisible(false);
        }
        this.revalidate();
        this.repaint();
    }

    protected void componentClosed() {
        NbPreferences.forModule(MergeDialog.class).put("MergeDialogSplitHeight"+participant.name(), String.valueOf(jSplitPane.getDividerLocation()));
        //NbPreferences.forModule(MergeDialog.class).put("MergeDialogShowAllParents", String.valueOf(showAllParents));

        mergeTable1.componentClosed();
    }

    /**
     * affiche un radio bouton
     * @param entity
     * @param record
     * @param mergeDialog
     * @param selected
     */
    private void addRadioButton(int position, final MergeModel model, Entity selectedEntity) {
        // je cree le label a afficher en tete du panneau
        String labelText = Integer.toString(model.getNbMatch())+"/"+Integer.toString(model.getNbMatchMax());
        JLabel jLabelNbMatch =  new JLabel();
        jLabelNbMatch.setText(labelText);

        // je cree le radiobutton
        JRadioButton jRadioButton =  new JRadioButton();
        jRadioButton.setText(model.getSummary(selectedEntity));
        jRadioButton.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jRadioButton.setPreferredSize(null);
        jRadioButton.setSelected(false);
        jRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModel(model);
            }
        });

        // j'ajoute le bouton dans le groupe de boutons pour activer la selection exlusive
        buttonGroupChoiceModel.add(jRadioButton);

        // j'affiche le radiobutton dans la premiere colonne
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = position;
        gridBagConstraints.weightx = 1;
        //gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelChoice.add(jRadioButton,gridBagConstraints);

        // j'affiche le nombre de champs egaux dans la deuxième colonne
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = position;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelChoice.add(jLabelNbMatch, gridBagConstraints);

    }

    /**
     * selectionne le modele de données et l'affiche dans la fenetre
     * Cette methode est appelee par le panneau de choix des individus
     * @param entity
     * @param record
     */
    protected void selectModel(MergeModel model) {
        this.currentModel = model;
        mergeTable1.setModel(currentModel);
        mergeTable1.setEntityActionManager(mergeDialog);
        // j'affiche les données du modele dans la table
        currentModel.fireTableDataChanged();

        // je renseigne le titre de la fenetre
        mergeDialog.setTitle(currentModel.getTitle());
        
        // j'affiche  dans l'arbre l'entité proposée par le modele 
        Entity proposedEntity = currentModel.getProposedEntity();
        if (proposedEntity != null) {
            // je mets l'entité  comme racine de l'arbre
            mergeDialog.setRoot(proposedEntity);        
            // je selectionne l'entité
            mergeDialog.show(proposedEntity);
        }
        
//        if (currentModel.getSelectedEntity() != null) {
//            if ((currentModel instanceof MergeModelBirth || currentModel instanceof MergeModelDeath )
//                    && currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject != null) {
//                
//                Indi indi = (Indi) currentModel.getSelectedEntity();
//                Fam family = (Fam) currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject;
//                if (indi != null && family !=  null && indi.isDescendantOf(family) ) {
//                    // je mets la famille comme racine de l'arbre
//                    mergeDialog.setRoot(currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject);
//                    // je selectionne le nouveau né
//                    mergeDialog.show(model.getSelectedEntity());
//                } else {
//                    mergeDialog.setRoot(model.getSelectedEntity());
//                    // je selectionne sa famille
//                    mergeDialog.show(currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject);
//                }
//            } else {
//                mergeDialog.setRoot(model.getSelectedEntity());
//                mergeDialog.show(model.getSelectedEntity());
//            }
//        } else {
//            if ( currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject instanceof Fam) {
//                // je centre l'arbre sur la famille des parents
//                mergeDialog.setRoot((Fam) currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject);
//                // je selectionne sa famille
//                mergeDialog.show(currentModel.getRow(MergeModel.RowType.IndiParentFamily).entityObject);
//            }
//        }
    }

    MergeModel getCurrentModel() {
        return currentModel;
    }

   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupChoiceModel = new javax.swing.ButtonGroup();
        jSplitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelToolbar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToggleButtonShowAllParents = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelChoice = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mergeTable1 = new ancestris.modules.releve.dnd.MergeTable();

        setLayout(new java.awt.BorderLayout());

        jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanelToolbar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelToolbar.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(MergePanel.class, "MergePanel.title")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelToolbar.add(jLabel1, gridBagConstraints);

        jToggleButtonShowAllParents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/releve/images/add-spouse24.png"))); // NOI18N
        jToggleButtonShowAllParents.setToolTipText(org.openide.util.NbBundle.getMessage(MergePanel.class, "MergePanel.jToggleButtonShowAllParents.toolTipText")); // NOI18N
        jToggleButtonShowAllParents.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToggleButtonShowAllParents.setFocusable(false);
        jToggleButtonShowAllParents.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jToggleButtonShowAllParents.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToggleButtonShowAllParents.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonShowAllParents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonShowAllParentsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanelToolbar.add(jToggleButtonShowAllParents, gridBagConstraints);

        jPanel1.add(jPanelToolbar, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBar(null);

        jPanelChoice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelChoice.setRequestFocusEnabled(false);
        jPanelChoice.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(jPanelChoice);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane.setTopComponent(jPanel1);

        jScrollPane2.setPreferredSize(null);

        mergeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        mergeTable1.setPreferredScrollableViewportSize(null);
        jScrollPane2.setViewportView(mergeTable1);

        jSplitPane.setBottomComponent(jScrollPane2);

        add(jSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonShowAllParentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonShowAllParentsActionPerformed
        try {
            mergeDialog.updateData(((JToggleButton) (evt.getSource())).isSelected());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_jToggleButtonShowAllParentsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupChoiceModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChoice;
    private javax.swing.JPanel jPanelToolbar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JToggleButton jToggleButtonShowAllParents;
    private ancestris.modules.releve.dnd.MergeTable mergeTable1;
    // End of variables declaration//GEN-END:variables

}
