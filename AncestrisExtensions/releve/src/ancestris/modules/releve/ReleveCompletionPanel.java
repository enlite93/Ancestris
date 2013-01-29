/*
 * ReleveCompletionPanel.java
 *
 * Created on 9 déc. 2012, 11:57:25
 */

package ancestris.modules.releve;

import ancestris.core.pluginservice.AncestrisPlugin;
import ancestris.modules.releve.model.CompletionProvider;
import ancestris.modules.releve.model.CompletionProvider.CompletionType;
import ancestris.modules.releve.model.DataManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;

/**
 * permet de decocher les noms ou prenom que l'on ne souhaite pas voir
 * apparaitre dans les listes de completion
 * @author Michel
 */
public class ReleveCompletionPanel extends javax.swing.JFrame {

    CompletionModel model = new CompletionModel() ;
    CompletionType completionType;

    
    /**
     * affiche la fenetre de completion des prénoms
     */
    static public void  showFirstNameCompletionPanel() {
        ReleveCompletionPanel statistics = new ReleveCompletionPanel();        
        statistics.initData(CompletionType.firstName );
        statistics.setVisible(true);

    }

    /**
     * affiche la fenetre de completion des noms
     */
    static public void  showLastNameCompletionPanel() {
        ReleveCompletionPanel statistics = new ReleveCompletionPanel();       
        statistics.initData(CompletionType.lastName );
        statistics.setVisible(true);
    }

    /** Creates new form ReleveCompletionPanel */
    public ReleveCompletionPanel() {
        initComponents();
    }

    public void initData(CompletionType completionType) {
        List<String> lastNameList;
        List<String> excludeList;
        this.completionType=completionType;

        DataManager dataManager = null;
        // je choisis le premier ReleveTopComponent
        for (ReleveTopComponent tc : AncestrisPlugin.lookupAll(ReleveTopComponent.class)) {
            dataManager = tc.getDataManager();
            break;
        }
        if(dataManager == null) {
            return;
        }

        switch( completionType ) {
            case firstName:
               model.setColumnName("Prénom");
               lastNameList = dataManager.getCompletionProvider().getFirstNames();
               excludeList = CompletionProvider.loadExcludeCompletion(completionType);
               break;
            case lastName:
               model.setColumnName("Nom");
               lastNameList = dataManager.getCompletionProvider().getLastNames();
               excludeList = CompletionProvider.loadExcludeCompletion(completionType);
               break;
            default:
               model.setColumnName("");
               lastNameList = new ArrayList<String>();
               excludeList = new ArrayList<String>();
        }
        
        // je recupere la liste des valeurs existantes
        HashMap<String,Boolean> lastNameMap= new HashMap<String,Boolean>();

        for (Iterator<String> it = lastNameList.iterator(); it.hasNext(); ) {
            lastNameMap.put(it.next(), true);
        }

        for (Iterator<String> it = excludeList.iterator(); it.hasNext(); ) {
            lastNameMap.put(it.next(), false);
        }
        
        // je copie les valeurs dans le modele
        for (Iterator<Entry<String,Boolean>> it = lastNameMap.entrySet().iterator(); it.hasNext(); ) {
           Entry<String,Boolean> entry = it.next();
           model.add(entry.getKey(), entry.getValue());
        }
        jTableLastName.setModel(model);
        jTableLastName.setAutoCreateRowSorter(true);
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        jTableLastName.getRowSorter().setSortKeys(sortKeys);

    }


    private void saveExcluded() {
        ArrayList<String> excludeList = new ArrayList<String>();
        int n = model.getRowCount();
        for(int i = 0; i <n ; i++) {
            if (((Boolean)model.getValueAt(i, 0)) == false) {
                excludeList.add(model.getValueAt(i,1).toString());
            }
        }
        
        // j'enregistre les valeurs dans les preferences
        CompletionProvider.saveExcludedCompletion(excludeList, completionType);

        // je notifie les instance de la mise a jour 
        for (ReleveTopComponent tc : AncestrisPlugin.lookupAll(ReleveTopComponent.class)) {
            tc.getDataManager().getCompletionProvider().refreshExcludeCompletion(completionType);
        }
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

        jPanelLastName = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLastName = new javax.swing.JTable();
        jPanelFirstName = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanelLastName.setLayout(new java.awt.GridBagLayout());

        jTableLastName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableLastName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelLastName.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanelLastName, gridBagConstraints);

        jPanelFirstName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFirstName.setLayout(new java.awt.GridBagLayout());

        jButton1.setText(org.openide.util.NbBundle.getMessage(ReleveCompletionPanel.class, "ReleveCompletionPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelFirstName.add(jButton1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanelFirstName, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        saveExcluded();
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int[] selectedRows = jTableLastName.getSelectedRows();

        for (int row : selectedRows) {
            model.remove(jTableLastName.convertRowIndexToModel(row));
        }
	model.fireTableDataChanged();
        jTableLastName.clearSelection();

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new ReleveCompletionPanel().setVisible(true);
//            }
//        });
//    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanelFirstName;
    private javax.swing.JPanel jPanelLastName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLastName;
    // End of variables declaration//GEN-END:variables


    private class CompletionModel extends AbstractTableModel {
        ArrayList<Boolean> includeList = new ArrayList<Boolean>();
        ArrayList<String> valueList = new ArrayList<String>();
        String columnName;

        public void setColumnName(String columnName) {

            this.columnName = columnName;

        }

        public void add(String value, boolean include) {
            valueList.add(value);
            includeList.add(include);
        }
        
        public void remove(int row) {
            valueList.remove(row);
            includeList.remove(row);
        }

        final Class columnClass[] = {Boolean.class, String.class};
        
        @Override
        public int getColumnCount() {
            return columnClass.length;
        }

        @Override
        public int getRowCount() {
            return valueList.size();
        }

        @Override
        public String getColumnName(int col) {
            switch (col) {
                case 0:
                    return "Inclu";
                case 1:
                    return columnName;
                default:
                    return null;
            }
        }

        @Override
        public Class<?> getColumnClass(int col) {
            return columnClass[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            switch (col) {
                case 0:
                    return includeList.get(row);
                case 1:
                    return valueList.get(row);
                default:
                    return null;
            }
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            switch (col) {
                case 0:
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            switch (col) {
                case 0:
                    includeList.set(row,(Boolean)value);
                    break;
                default:
                    break;
            }
            fireTableCellUpdated(row, col);
        }


    }
}
