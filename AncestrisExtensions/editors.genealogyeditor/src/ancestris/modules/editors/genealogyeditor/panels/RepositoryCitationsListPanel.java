package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.RepositoryCitationsTableModel;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class RepositoryCitationsListPanel extends javax.swing.JPanel {

    private Property mRoot;
    private RepositoryCitationsTableModel mRepositoryCitationsTableModel = new RepositoryCitationsTableModel();
    private Repository mRepository;
    private PropertyRepository mRepositoryCitation;

    /**
     * Creates new form SourcesListPanel
     */
    public RepositoryCitationsListPanel() {
        initComponents();
        repositoriesTable.setID(RepositoryCitationsListPanel.class.getName());
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
        linkToRepositoryButton = new javax.swing.JButton();
        repositoriesTableScrollPane = new javax.swing.JScrollPane();
        repositoriesTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable();

        repositoriesToolBar.setFloatable(false);
        repositoriesToolBar.setRollover(true);

        addRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationsListPanel.addRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
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
        editRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationsListPanel.editRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
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
        deleteRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationsListPanel.deleteRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        deleteRepositoryButton.setFocusable(false);
        deleteRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRepositoryButtonActionPerformed(evt);
            }
        });
        repositoriesToolBar.add(deleteRepositoryButton);

        linkToRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationsListPanel.linkToRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        linkToRepositoryButton.setFocusable(false);
        linkToRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkToRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToRepositoryButtonActionPerformed(evt);
            }
        });
        repositoriesToolBar.add(linkToRepositoryButton);

        repositoriesTable.setAutoCreateRowSorter(true);
        repositoriesTable.setModel(mRepositoryCitationsTableModel);
        repositoriesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repositoriesTableMouseClicked(evt);
            }
        });
        repositoriesTableScrollPane.setViewportView(repositoriesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(repositoriesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addComponent(repositoriesTableScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(repositoriesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repositoriesTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRepositoryButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mRepository = (Repository) gedcom.createEntity(Gedcom.REPO);
                }
            }); // end of doUnitOfWork

            RepositoryEditorPanel repositoryEditorPanel = new RepositoryEditorPanel();
            repositoryEditorPanel.setRepository(mRepository);

            DialogManager.ADialog editorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(RepositoryEditorPanel.class, "RepositoryEditorPanel.create.title"),
                    repositoryEditorPanel);
            editorDialog.setDialogId(RepositoryEditorPanel.class.getName());

            if (editorDialog.show() == DialogDescriptor.OK_OPTION) {
                final Repository repository = repositoryEditorPanel.commit();
                mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mRepositoryCitation = (PropertyRepository) mRoot.addProperty("REPO", '@' + repository.getId() + '@');
                        mRepositoryCitation.link();
                    }
                }); // end of doUnitOfWork
                mRepositoryCitationsTableModel.add(mRepositoryCitation);
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addRepositoryButtonActionPerformed

    private void editRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRepositoryButtonActionPerformed
        int selectedRow = repositoriesTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        if (selectedRow != -1) {
            int rowIndex = repositoriesTable.convertRowIndexToModel(selectedRow);
            RepositoryEditorPanel repositoryEditorPanel = new RepositoryEditorPanel();
            repositoryEditorPanel.setRepository((Repository) mRepositoryCitationsTableModel.getValueAt(rowIndex).getTargetEntity());

            DialogManager.ADialog editorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(RepositoryEditorPanel.class, "RepositoryEditorPanel.edit.title", mRepositoryCitationsTableModel.getValueAt(rowIndex)),
                    repositoryEditorPanel);
            editorDialog.setDialogId(RepositoryEditorPanel.class.getName());

            if (editorDialog.show() == DialogDescriptor.OK_OPTION) {
                repositoryEditorPanel.commit();
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        }
    }//GEN-LAST:event_editRepositoryButtonActionPerformed

    private void deleteRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRepositoryButtonActionPerformed
        final int selectedRow = repositoriesTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();

        if (selectedRow != -1) {
            PropertyRepository repositoryCitation = mRepositoryCitationsTableModel.getValueAt(repositoriesTable.convertRowIndexToModel(selectedRow));
            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            RepositoryCitationsListPanel.class, "RepositoriesListPanel.deleteRepository.title",
                            repositoryCitation),
                    NbBundle.getMessage(
                            RepositoryCitationsListPanel.class, "RepositoriesListPanel.deleteRepository.text",
                            repositoryCitation,
                            mRoot));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRoot.delProperty(mRepositoryCitationsTableModel.remove(repositoriesTable.convertRowIndexToModel(selectedRow)));
                        }
                    }); // end of doUnitOfWork
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_deleteRepositoryButtonActionPerformed

    private void repositoriesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repositoriesTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int selectedRow = repositoriesTable.getSelectedRow();
            Gedcom gedcom = mRoot.getGedcom();
            int undoNb = gedcom.getUndoNb();
            if (selectedRow != -1) {
                int rowIndex = repositoriesTable.convertRowIndexToModel(selectedRow);
                RepositoryEditorPanel repositoryEditorPanel = new RepositoryEditorPanel();
                repositoryEditorPanel.setRepository((Repository) mRepositoryCitationsTableModel.getValueAt(rowIndex).getTargetEntity());

                DialogManager.ADialog editorDialog = new DialogManager.ADialog(
                        NbBundle.getMessage(RepositoryEditorPanel.class, "RepositoryEditorPanel.edit.title",
                                mRepositoryCitationsTableModel.getValueAt(rowIndex)),
                        repositoryEditorPanel);
                editorDialog.setDialogId(RepositoryEditorPanel.class.getName());

                if (editorDialog.show() == DialogDescriptor.OK_OPTION) {
                    repositoryEditorPanel.commit();
                } else {
                    while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                        gedcom.undoUnitOfWork(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_repositoriesTableMouseClicked

    private void linkToRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToRepositoryButtonActionPerformed
        List<Repository> repositoriesList = new ArrayList<Repository>((Collection<Repository>) mRoot.getGedcom().getEntities(Gedcom.REPO));

        RepositoriesListPanel repositoriesListPanel = new RepositoriesListPanel();
        repositoriesListPanel.set(mRoot, repositoriesList);
        repositoriesListPanel.setToolBarVisible(false);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
            NbBundle.getMessage(RepositoryCitationsListPanel.class, "RepositoriesListPanel.linkTo.title"),
            repositoriesListPanel);
        individualsListDialog.setDialogId(RepositoryCitationsListPanel.class.getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Repository selectedRepository = repositoriesListPanel.getSelectedRepository();
            if (selectedRepository != null) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRepositoryCitation = (PropertyRepository) mRoot.addProperty("REPO", '@' + selectedRepository.getId() + '@');
                            mRepositoryCitation.link();
                        }
                    }); // end of doUnitOfWork
                    mRepositoryCitationsTableModel.add(mRepositoryCitation);
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToRepositoryButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRepositoryButton;
    private javax.swing.JButton deleteRepositoryButton;
    private javax.swing.JButton editRepositoryButton;
    private javax.swing.JButton linkToRepositoryButton;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable repositoriesTable;
    private javax.swing.JScrollPane repositoriesTableScrollPane;
    private javax.swing.JToolBar repositoriesToolBar;
    // End of variables declaration//GEN-END:variables

    public void set(Property root, List<PropertyRepository> repositoriesList) {
        this.mRoot = root;
        mRepositoryCitationsTableModel.update(repositoriesList);
    }
}
