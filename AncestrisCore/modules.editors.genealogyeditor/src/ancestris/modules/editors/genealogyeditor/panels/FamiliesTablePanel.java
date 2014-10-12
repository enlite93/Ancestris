package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.editors.FamilyEditor;
import ancestris.modules.editors.genealogyeditor.models.FamiliesTableModel;
import ancestris.modules.editors.genealogyeditor.renderer.TextPaneTableCellRenderer;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class FamiliesTablePanel extends javax.swing.JPanel {

    public static int LIST_FAM = 0;
    public static int EDIT_FAMC = 1;
    public static int EDIT_FAMS = 2;
    private FamiliesTableModel mFamiliesTableModel;
    private Property mRoot;
    private int mFamilyEditingType;
    private Fam mCreateFamily = null;

    /**
     * Creates new form FamiliesListPanel
     */
    public FamiliesTablePanel() {
        mFamilyEditingType = LIST_FAM;
        mFamiliesTableModel = new FamiliesTableModel(FamiliesTableModel.FAMILY_LIST);
        initComponents();
        familyNamesTable.setID(FamiliesTablePanel.class.getName());
        if (mFamilyEditingType == LIST_FAM) {
            familyNamesToolBar.setVisible(false);
        }
    }

    public FamiliesTablePanel(int familyEditingType) {
        mFamilyEditingType = familyEditingType;
        if (mFamilyEditingType == EDIT_FAMC) {
            mFamiliesTableModel = new FamiliesTableModel(FamiliesTableModel.FAMILY_CHILD);
        } else if (mFamilyEditingType == EDIT_FAMS) {
            mFamiliesTableModel = new FamiliesTableModel(FamiliesTableModel.FAMILY_SPOUSE);
        } else if (mFamilyEditingType == LIST_FAM) {
            mFamiliesTableModel = new FamiliesTableModel(FamiliesTableModel.FAMILY_LIST);
        }
        initComponents();
        familyNamesTable.setID(FamiliesTablePanel.class.getName());
        if (mFamilyEditingType == LIST_FAM) {
            familyNamesToolBar.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        familyNamesToolBar = new javax.swing.JToolBar();
        addFamilyNameButton = new javax.swing.JButton();
        linkToFamilyButton = new javax.swing.JButton();
        editFamilyNameButton = new javax.swing.JButton();
        deleteFamilyNameButton = new javax.swing.JButton();
        familyNamesScrollPane = new javax.swing.JScrollPane();
        familyNamesTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 4) {
                    return new TextPaneTableCellRenderer ();
                }
                return super.getCellRenderer(row, column);
            }
        };

        familyNamesToolBar.setFloatable(false);
        familyNamesToolBar.setRollover(true);

        addFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addFamilyNameButton.setFocusable(false);
        addFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(addFamilyNameButton);

        linkToFamilyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToFamilyButton.setFocusable(false);
        linkToFamilyButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        linkToFamilyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToFamilyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToFamilyButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(linkToFamilyButton);

        editFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editFamilyNameButton.setFocusable(false);
        editFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(editFamilyNameButton);

        deleteFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteFamilyNameButton.setFocusable(false);
        deleteFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(deleteFamilyNameButton);

        familyNamesTable.setModel(mFamiliesTableModel);
        familyNamesTable.setSelectionBackground(new java.awt.Color(89, 142, 195));
        familyNamesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                familyNamesTableMouseClicked(evt);
            }
        });
        familyNamesScrollPane.setViewportView(familyNamesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(familyNamesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addComponent(familyNamesScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(familyNamesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(familyNamesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFamilyNameButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();

        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mCreateFamily = (Fam) gedcom.createEntity(Gedcom.FAM);
                }
            }); // end of doUnitOfWork

            FamilyEditor familyEditor = new FamilyEditor();
            familyEditor.setContext(new Context(mCreateFamily));
            if (familyEditor.showPanel()) {
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        if (mFamilyEditingType == EDIT_FAMC) {
                            mCreateFamily.addChild((Indi) mRoot);
                        } else if (mFamilyEditingType == EDIT_FAMS) {
                            if (((Indi) mRoot).getSex() == PropertySex.MALE) {
                                mCreateFamily.setHusband((Indi) mRoot);
                            } else {
                                mCreateFamily.setWife((Indi) mRoot);
                            }
                        }
                    }
                }); // end of doUnitOfWork
                mFamiliesTableModel.add(mCreateFamily);
                editFamilyNameButton.setEnabled(true);
                deleteFamilyNameButton.setEnabled(true);

            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addFamilyNameButtonActionPerformed

    private void editFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFamilyNameButtonActionPerformed
        int rowIndex = familyNamesTable.convertRowIndexToModel(familyNamesTable.getSelectedRow());
        Gedcom gedcom = mRoot.getGedcom();

        if (rowIndex != -1) {
            Fam family = mFamiliesTableModel.getValueAt(rowIndex);
            FamilyEditor familyEditor = new FamilyEditor();
            familyEditor.setContext(new Context(family));
            familyEditor.showPanel();
        }
    }//GEN-LAST:event_editFamilyNameButtonActionPerformed

    private void deleteFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFamilyNameButtonActionPerformed
        final int selectedRow = familyNamesTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();

        if (selectedRow != -1) {
            int rowIndex = familyNamesTable.convertRowIndexToModel(selectedRow);
            Fam family = mFamiliesTableModel.getValueAt(rowIndex);

            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            FamiliesTablePanel.class, "FamiliesListPanel.deleteFamilyConfirmation.title",
                            family),
                    NbBundle.getMessage(
                            FamiliesTablePanel.class, "FamiliesListPanel.deleteFamilyConfirmation.text",
                            family,
                            mRoot));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    gedcom.doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRoot.delProperty(mFamiliesTableModel.remove(familyNamesTable.convertRowIndexToModel(selectedRow)));
                        }
                    }); // end of doUnitOfWork
                    if (mFamiliesTableModel.getRowCount() <= 0) {
                        editFamilyNameButton.setEnabled(false);
                        deleteFamilyNameButton.setEnabled(false);
                    }

                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_deleteFamilyNameButtonActionPerformed

    private void linkToFamilyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToFamilyButtonActionPerformed
        FamiliesTablePanel familiesListPanel = new FamiliesTablePanel(LIST_FAM);
        familiesListPanel.set(mRoot, new ArrayList<Fam>(mRoot.getGedcom().getFamilies()));
        DialogManager.ADialog familiesListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(FamiliesTablePanel.class, "FamiliesListPanel.linkto.title"),
                familiesListPanel);
        familiesListDialog.setDialogId(FamiliesTablePanel.class.getName());

        if (familiesListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Fam selectedFamily = familiesListPanel.getSelectedFamily();
            if (selectedFamily != null) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            if (mFamilyEditingType == EDIT_FAMC) {
                                selectedFamily.addChild((Indi) mRoot);
                            } else if (mFamilyEditingType == EDIT_FAMS) {
                                if (((Indi) mRoot).getSex() == PropertySex.MALE) {
                                    selectedFamily.setHusband((Indi) mRoot);
                                } else {
                                    selectedFamily.setWife((Indi) mRoot);
                                }
                            }
                        }
                    }); // end of doUnitOfWork
                    mFamiliesTableModel.add(selectedFamily);
                    editFamilyNameButton.setEnabled(true);
                    deleteFamilyNameButton.setEnabled(true);
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToFamilyButtonActionPerformed

    private void familyNamesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_familyNamesTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int rowIndex = familyNamesTable.convertRowIndexToModel(familyNamesTable.getSelectedRow());
            Gedcom gedcom = mRoot.getGedcom();

            if (rowIndex != -1) {
                Fam family = mFamiliesTableModel.getValueAt(rowIndex);
                FamilyEditor familyEditor = new FamilyEditor();
                familyEditor.setContext(new Context(family));
                familyEditor.showPanel();
            }
        }
    }//GEN-LAST:event_familyNamesTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFamilyNameButton;
    private javax.swing.JButton deleteFamilyNameButton;
    private javax.swing.JButton editFamilyNameButton;
    private javax.swing.JScrollPane familyNamesScrollPane;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable familyNamesTable;
    private javax.swing.JToolBar familyNamesToolBar;
    private javax.swing.JButton linkToFamilyButton;
    // End of variables declaration//GEN-END:variables

    public void set(Property root, List<Fam> familiesList) {
        this.mRoot = root;
        mFamiliesTableModel.clear();
        mFamiliesTableModel.addAll(familiesList);
        if (mFamiliesTableModel.getRowCount() > 0) {
            editFamilyNameButton.setEnabled(true);
            deleteFamilyNameButton.setEnabled(true);
        } else {
            editFamilyNameButton.setEnabled(false);
            deleteFamilyNameButton.setEnabled(false);
        }
    }

    public Fam getSelectedFamily() {
        int selectedRow = familyNamesTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = familyNamesTable.convertRowIndexToModel(selectedRow);
            return mFamiliesTableModel.getValueAt(rowIndex);
        } else {
            return null;
        }
    }
}
