package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.RepositoriesTableModel;
import genj.gedcom.Property;
import genj.gedcom.Repository;
import java.util.List;

/**
 *
 * @author dominique
 */
public class RepositoriesListPanel extends javax.swing.JPanel {

    private Property root;
    private RepositoriesTableModel repositoriesTableModel = new RepositoriesTableModel();

    /**
     * Creates new form SourcesListPanel
     */
    public RepositoriesListPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        repositoriesToolBar = new javax.swing.JToolBar();
        addRepositoryButton = new javax.swing.JButton();
        editRepositoryButton = new javax.swing.JButton();
        deleteRepositoryButton = new javax.swing.JButton();
        repositoriesScrollPane = new javax.swing.JScrollPane();
        repositoriesTable = new javax.swing.JTable();

        repositoriesToolBar.setFloatable(false);
        repositoriesToolBar.setRollover(true);

        addRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoriesListPanel.addRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        addRepositoryButton.setFocusable(false);
        addRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRepositoryButtonActionPerformed(evt);
            }
        });
        repositoriesToolBar.add(addRepositoryButton);

        editRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoriesListPanel.editRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        editRepositoryButton.setFocusable(false);
        editRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRepositoryButtonActionPerformed(evt);
            }
        });
        repositoriesToolBar.add(editRepositoryButton);

        deleteRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoriesListPanel.deleteRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        deleteRepositoryButton.setFocusable(false);
        deleteRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRepositoryButtonActionPerformed(evt);
            }
        });
        repositoriesToolBar.add(deleteRepositoryButton);

        repositoriesTable.setModel(repositoriesTableModel);
        repositoriesTable.setShowHorizontalLines(false);
        repositoriesTable.setShowVerticalLines(false);
        repositoriesTable.getColumnModel().getColumn(0).setMaxWidth(100);
        repositoriesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repositoriesTableMouseClicked(evt);
            }
        });
        repositoriesScrollPane.setViewportView(repositoriesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(repositoriesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(repositoriesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(repositoriesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repositoriesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRepositoryButtonActionPerformed

    }//GEN-LAST:event_addRepositoryButtonActionPerformed

    private void editRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRepositoryButtonActionPerformed
        int selectedRow = repositoriesTable.getSelectedRow();
        if (selectedRow != -1) {

        }
    }//GEN-LAST:event_editRepositoryButtonActionPerformed

    private void deleteRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRepositoryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteRepositoryButtonActionPerformed

    private void repositoriesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repositoriesTableMouseClicked
        if (evt.getClickCount() >= 2) {
        }
    }//GEN-LAST:event_repositoriesTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRepositoryButton;
    private javax.swing.JButton deleteRepositoryButton;
    private javax.swing.JButton editRepositoryButton;
    private javax.swing.JScrollPane repositoriesScrollPane;
    private javax.swing.JTable repositoriesTable;
    private javax.swing.JToolBar repositoriesToolBar;
    // End of variables declaration//GEN-END:variables

    public void set(Property root, List<Repository> repositoriesList) {
        this.root = root;
        repositoriesTableModel.update(repositoriesList);
    }

    public void commit() {
    }
}