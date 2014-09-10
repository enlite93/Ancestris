package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.editors.IndividualEditor;
import ancestris.modules.editors.genealogyeditor.models.IndividualReferencesTableModel;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.DialogDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class ChildrenTablePanel extends javax.swing.JPanel {

    private final IndividualReferencesTableModel mIndividualReferencesTableModel = new IndividualReferencesTableModel();
    private final ChangeListner changeListner = new ChangeListner();
    private final ChangeSupport changeSupport = new ChangeSupport(ChildrenTablePanel.class);

    private Fam mRoot;
    private Indi mIndividual;
    PropertyXRef mAddedChild = null;

    /**
     * Creates new form IndividualsListPanel
     */
    public ChildrenTablePanel() {
        initComponents();
        childrenTable.setID(ChildrenTablePanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        childrenToolBar = new javax.swing.JToolBar();
        addChildrenButton = new javax.swing.JButton();
        linkToChildrenButton = new javax.swing.JButton();
        editChildrenButton = new javax.swing.JButton();
        deleteChildrenButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        childrenTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable();

        childrenToolBar.setFloatable(false);
        childrenToolBar.setRollover(true);

        addChildrenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addChildrenButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("ChildrenTablePanel.addChildrenButton.toolTipText"), new Object[] {})); // NOI18N
        addChildrenButton.setFocusable(false);
        addChildrenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addChildrenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addChildrenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChildrenButtonActionPerformed(evt);
            }
        });
        childrenToolBar.add(addChildrenButton);

        linkToChildrenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToChildrenButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("ChildrenTablePanel.linkToChildrenButton.text"), new Object[] {})); // NOI18N
        linkToChildrenButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("ChildrenTablePanel.linkToChildrenButton.toolTipText"), new Object[] {})); // NOI18N
        linkToChildrenButton.setFocusable(false);
        linkToChildrenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkToChildrenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToChildrenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToChildrenButtonActionPerformed(evt);
            }
        });
        childrenToolBar.add(linkToChildrenButton);

        editChildrenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editChildrenButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("ChildrenTablePanel.editChildrenButton.toolTipText"), new Object[] {})); // NOI18N
        editChildrenButton.setFocusable(false);
        editChildrenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editChildrenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editChildrenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editChildrenButtonActionPerformed(evt);
            }
        });
        childrenToolBar.add(editChildrenButton);

        deleteChildrenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteChildrenButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("ChildrenTablePanel.deleteChildrenButton.toolTipText"), new Object[] {})); // NOI18N
        deleteChildrenButton.setFocusable(false);
        deleteChildrenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteChildrenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteChildrenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChildrenButtonActionPerformed(evt);
            }
        });
        childrenToolBar.add(deleteChildrenButton);

        childrenTable.setModel(mIndividualReferencesTableModel);
        childrenTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                childrenTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(childrenTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(childrenToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(childrenToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addChildrenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChildrenButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();

        try {
            mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mIndividual = (Indi) gedcom.createEntity(Gedcom.INDI);
                    mAddedChild = mRoot.addChild(mIndividual);
                    String lastName = "";
                    if (mRoot.getHusband() != null) {
                        lastName = mRoot.getHusband().getLastName();
                    } else if (mRoot.getWife() != null) {
                        lastName = mRoot.getWife().getLastName();
                    }

                    mIndividual.setName("", lastName);
                }
            }); // end of doUnitOfWork

            IndividualEditor individualEditor = new IndividualEditor();
            individualEditor.setContext(new Context(mIndividual));
            individualEditor.addChangeListener(changeListner);
            if (individualEditor.showPanel()) {
                mIndividualReferencesTableModel.add(mAddedChild);
            }
            individualEditor.removeChangeListener(changeListner);
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addChildrenButtonActionPerformed

    private void editChildrenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editChildrenButtonActionPerformed
        int rowIndex = childrenTable.convertRowIndexToModel(childrenTable.getSelectedRow());
        if (rowIndex != -1) {
            PropertyXRef individualRef = mIndividualReferencesTableModel.getValueAt(rowIndex);
            int undoNb = mRoot.getGedcom().getUndoNb();
            IndividualEditor individualEditor = new IndividualEditor();
            individualEditor.setContext(new Context((Indi) individualRef.getTargetEntity()));
            individualEditor.addChangeListener(changeListner);
            individualEditor.showPanel();
            individualEditor.removeChangeListener(changeListner);
        }
    }//GEN-LAST:event_editChildrenButtonActionPerformed

    private void deleteChildrenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChildrenButtonActionPerformed
        int rowIndex = childrenTable.convertRowIndexToModel(childrenTable.getSelectedRow());
        Gedcom gedcom = mRoot.getGedcom();

        if (rowIndex != -1) {
            final PropertyXRef individualRef = mIndividualReferencesTableModel.getValueAt(rowIndex);

            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            ChildrenTablePanel.class, "ChildrenListPanel.deleteChildConfirmation.title",
                            individualRef.getTargetEntity()),
                    NbBundle.getMessage(
                            ChildrenTablePanel.class, "ChildrenListPanel.deleteChildConfirmation.text",
                            individualRef.getTargetEntity(),
                            mRoot));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    gedcom.doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRoot.delProperty(individualRef);
                        }
                    }); // end of doUnitOfWork
                    mIndividualReferencesTableModel.remove(rowIndex);
                    changeListner.stateChanged(null);
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
   }//GEN-LAST:event_deleteChildrenButtonActionPerformed

    private void linkToChildrenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToChildrenButtonActionPerformed
        IndividualsListPanel individualsListPanel = new IndividualsListPanel();
        List<Indi> individualsList = new ArrayList<Indi>(mRoot.getGedcom().getIndis());
        individualsListPanel.setToolBarVisible(false);
        individualsListPanel.set(mRoot, individualsList);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualsListPanel.class, "IndividualsListPanel.title.select.child"),
                individualsListPanel);
        individualsListDialog.setDialogId(IndividualsListPanel.class.getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Indi selectedIndividual = individualsListPanel.getSelectedIndividual();
            if (selectedIndividual != null) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            PropertyXRef addChild = mRoot.addChild(selectedIndividual);
                            mIndividualReferencesTableModel.add(addChild);
                        }
                    }); // end of doUnitOfWork
                    changeListner.stateChanged(null);
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToChildrenButtonActionPerformed

    private void childrenTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_childrenTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int rowIndex = childrenTable.convertRowIndexToModel(childrenTable.getSelectedRow());
            if (rowIndex != -1) {
                PropertyXRef individualRef = mIndividualReferencesTableModel.getValueAt(rowIndex);
                IndividualEditor individualEditor = new IndividualEditor();
                individualEditor.setContext(new Context((Indi) individualRef.getTargetEntity()));
                individualEditor.addChangeListener(changeListner);
                individualEditor.showPanel();
                changeListner.stateChanged(null);
            }
        }
    }//GEN-LAST:event_childrenTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addChildrenButton;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable childrenTable;
    private javax.swing.JToolBar childrenToolBar;
    private javax.swing.JButton deleteChildrenButton;
    private javax.swing.JButton editChildrenButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton linkToChildrenButton;
    // End of variables declaration//GEN-END:variables

    public void set(Fam root, List<? extends PropertyXRef> individualsList) {
        this.mRoot = root;
        mIndividualReferencesTableModel.clear();
        mIndividualReferencesTableModel.addAll(individualsList);
    }

    public Indi getSelectedChildren() {
        int selectedRow = childrenTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = childrenTable.convertRowIndexToModel(selectedRow);
            return (Indi) mIndividualReferencesTableModel.getValueAt(rowIndex).getTargetEntity();
        } else {
            return null;
        }
    }

    public void setToolBarVisible(boolean visible) {
        childrenToolBar.setVisible(visible);
    }

    /**
     * Listener
     */
    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    /**
     * Listener
     */
    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    public class ChangeListner implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            changeSupport.fireChange();
        }

        private boolean mute = false;

        public void mute() {
            mute = true;
        }

        public void unmute() {
            mute = false;
        }

    }
}
