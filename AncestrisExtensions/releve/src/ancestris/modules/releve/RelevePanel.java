/*
 * RelevePanel.java
 *
 * Created on 25 mars 2012, 16:43:34
 */

package ancestris.modules.releve;

import ancestris.modules.releve.model.DataManager;
import ancestris.modules.releve.model.Field;
import ancestris.modules.releve.model.ModelAbstract;
import ancestris.modules.releve.model.PlaceManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class RelevePanel extends javax.swing.JPanel  {
    ModelAbstract releveModel ;
    //ShowTableListener showTableListener;
    
    /** Creates new form RelevePanel */
    public RelevePanel() {
        initComponents();
        // j'ajoute l'editeur a l'ecoute de la selection de ligne dans la table
        releveTable.setTableSelectionListener(releveEditor);
    }

    public void setModel(DataManager dataManager, DataManager.ModelType modelType, PlaceManager placeManager) {
        releveModel = dataManager.getModel(modelType);
        releveTable.setModel(dataManager, modelType, placeManager);
        releveEditor.setModel(dataManager, modelType, placeManager);

        // j'envoie une notification pour initialiser l'affichage de la table et de l'editeur
        // remarque : ne pas utiliser dataManager.fireTableStructureChanged();
        // ça réinitialise la largeur des colonnes
        releveModel.fireTableDataChanged();

        // j'initialise la largeur de l'editeur avec la largeur de la session precedente
        // Remarque : il faut differer le changement de taille car sinon jSplitPane1.getSize() est nul
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                jSplitPane1.setDividerLocation(jSplitPane1.getSize().width
//                             - jSplitPane1.getInsets().right
//                             - jSplitPane1.getDividerSize()
//                             - releveEditor.getEditorWidth());
//
//            }
//        });
        jSplitPane1.setDividerLocation(releveEditor.getEditorWidth());
        
    }

//    @Override
//    public void setBounds(int x,
//                      int y,
//                      int width,
//                      int height) {
//        super.setBounds(x, y, width, height);
//        jSplitPane1.setDividerLocation(width
//                             - jSplitPane1.getInsets().right
//                             - jSplitPane1.getDividerSize()
//                             - releveEditor.getEditorWidth());
//    }

    /**
     * sauvegarde de la configuration a la fermeture du composant
     */
    void componentClosed() {
        releveTable.saveColumnLayout();
        // je sauvegarde la largeur de l'editeur
//        releveEditor.putEditorWidth(jSplitPane1.getSize().width
//                - jSplitPane1.getInsets().right
//                - jSplitPane1.getDividerSize()
//                - jSplitPane1.getDividerLocation() );
        releveEditor.putEditorWidth(jSplitPane1.getDividerLocation() );
    }

    public void selectRecord(int rowIndex) {
        if (releveTable.getRowCount() > 0) {
            // je verifie la coherence du releve en cours d'edition
            String errorMessage = releveModel.verifyRecord(releveEditor.getCurrentRecordIndex());
            if ( errorMessage.isEmpty() ) {
                // je recupere l'index du releve courant dans la table
                int recordIndex = releveTable.convertRowIndexToModel(rowIndex);
                // j'affiche le premier enregistrement dans l'editeur
                releveEditor.selectRecord(recordIndex);
                // je selectionne la ligne dans la table
                releveTable.setRowSelectionInterval(rowIndex, rowIndex);
                // je rends visible la premiere ligne selectionnée et de la colonne triée
                Rectangle cellRect = releveTable.getCellRect(rowIndex, releveTable.getSelectedColumn(), true);
                if (cellRect != null) {
                    releveTable.scrollRectToVisible(cellRect);
                }
            } else {
                // j'affiche le message d'erreur
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, errorMessage, "Relevé", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            releveEditor.selectRecord(-1);
        }
    }

    /**
     * retourne l'index du releve courant
     * @return
     */
    public int getCurrentRecordIndex() {
        return releveEditor.getCurrentRecordIndex();
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
        editorPanel = new javax.swing.JPanel();
        releveEditor = new ancestris.modules.releve.editor.ReleveEditor();
        tablePanel = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        releveTable = new ancestris.modules.releve.ReleveTable();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setRequestFocusEnabled(false);

        editorPanel.setPreferredSize(new java.awt.Dimension(270, 100));
        editorPanel.setLayout(new java.awt.BorderLayout(4, 4));

        releveEditor.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        releveEditor.setMinimumSize(new java.awt.Dimension(100, 300));
        editorPanel.add(releveEditor, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(editorPanel);

        tablePanel.setPreferredSize(new java.awt.Dimension(0, 0));
        tablePanel.setLayout(new java.awt.BorderLayout());

        releveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPaneTable.setViewportView(releveTable);

        tablePanel.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(tablePanel);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editorPanel;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JSplitPane jSplitPane1;
    private ancestris.modules.releve.editor.ReleveEditor releveEditor;
    private ancestris.modules.releve.ReleveTable releveTable;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables

    public int getEditorWidth() {
        return editorPanel.getWidth();
    }

    void selectField(Field.FieldType fieldType) {
        releveEditor.selectField(fieldType);
    }

    void createRecord() {
        releveEditor.createRecord();
    }


}
