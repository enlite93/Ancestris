package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.beans.ImageBean;
import ancestris.modules.editors.genealogyeditor.models.MultimediaFilesTableModel;
import genj.gedcom.*;
import genj.util.Registry;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class MultiMediaObjectEditorPanel extends javax.swing.JPanel {

    private Property mRoot;
    private Property mMultiMediaObject;
    private MultimediaFilesTableModel mMultimediaFilesTableModel = new MultimediaFilesTableModel();

    /**
     * Creates new form MultiMediaObjectEditorPanel
     */
    public MultiMediaObjectEditorPanel() {
        initComponents();
        multimediaFilesTable.setID(MultiMediaObjectEditorPanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        multiMediaObjectIDLabel = new javax.swing.JLabel();
        multiMediaObjectIDTextField = new javax.swing.JTextField();
        multiMediaObjectTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        filesToolBar = new javax.swing.JToolBar();
        addFileButton = new javax.swing.JButton();
        editFileButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        multimediaFilesTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable();
        notesPanel = new javax.swing.JPanel();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();
        multiMediaObjectReferencesPanel = new javax.swing.JPanel();
        referencesListPanel = new ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel();
        multiMediaObjectTitleLabel = new javax.swing.JLabel();
        multiMediaObjectTitleTextField = new javax.swing.JTextField();

        multiMediaObjectIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("MultiMediaObjectEditorPanel.multiMediaObjectIDLabel.text"), new Object[] {})); // NOI18N

        multiMediaObjectIDTextField.setEditable(false);
        multiMediaObjectIDTextField.setColumns(8);
        multiMediaObjectIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("MultiMediaObjectEditorPanel.multiMediaObjectIDTextField.text"), new Object[] {})); // NOI18N

        filesToolBar.setFloatable(false);
        filesToolBar.setRollover(true);

        addFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addFileButton.setToolTipText(org.openide.util.NbBundle.getMessage(MultiMediaObjectEditorPanel.class, "MultiMediaObjectEditorPanel.addFileButton.toolTipText")); // NOI18N
        addFileButton.setFocusable(false);
        addFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileButtonActionPerformed(evt);
            }
        });
        filesToolBar.add(addFileButton);

        editFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editFileButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("MultiMediaObjectEditorPanel.editFileButton.toolTipText"), new Object[] {})); // NOI18N
        editFileButton.setFocusable(false);
        editFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFileButtonActionPerformed(evt);
            }
        });
        filesToolBar.add(editFileButton);

        multimediaFilesTable.setModel(mMultimediaFilesTableModel);
        multimediaFilesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multimediaFilesTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(multimediaFilesTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(filesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        multiMediaObjectTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MultiMediaObjectEditorPanel.class, "MultiMediaObjectEditorPanel.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesPanelLayout.createSequentialGroup()
                .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        multiMediaObjectTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MultiMediaObjectEditorPanel.class, "MultiMediaObjectEditorPanel.notesPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        javax.swing.GroupLayout multiMediaObjectReferencesPanelLayout = new javax.swing.GroupLayout(multiMediaObjectReferencesPanel);
        multiMediaObjectReferencesPanel.setLayout(multiMediaObjectReferencesPanelLayout);
        multiMediaObjectReferencesPanelLayout.setHorizontalGroup(
            multiMediaObjectReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        multiMediaObjectReferencesPanelLayout.setVerticalGroup(
            multiMediaObjectReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        multiMediaObjectTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MultiMediaObjectEditorPanel.class, "MultiMediaObjectEditorPanel.multiMediaObjectReferencesPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), multiMediaObjectReferencesPanel); // NOI18N

        multiMediaObjectTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        multiMediaObjectTitleLabel.setText(org.openide.util.NbBundle.getMessage(MultiMediaObjectEditorPanel.class, "MultiMediaObjectEditorPanel.multiMediaObjectTitleLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multiMediaObjectTabbedPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(multiMediaObjectTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multiMediaObjectTitleTextField)
                        .addGap(10, 10, 10)
                        .addComponent(multiMediaObjectIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multiMediaObjectIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(multiMediaObjectIDLabel)
                    .addComponent(multiMediaObjectIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(multiMediaObjectTitleLabel)
                    .addComponent(multiMediaObjectTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(multiMediaObjectTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileButtonActionPerformed
        FileNameExtensionFilter imageFileFilter = new FileNameExtensionFilter(NbBundle.getMessage(ImageBean.class, "ImageBean.fileType"), "jpg", "jpeg", "png", "gif");
        final JFileChooser fileChooser = new JFileChooser();
        Registry registry = Registry.get(MultiMediaObjectEditorPanel.class);

        System.out.println(registry.get("rootPath", new java.io.File(".")));
        fileChooser.setFileFilter(imageFileFilter);
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setSelectedFile(new File(registry.get("rootPath", ".")));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            registry.put("rootPath", fileChooser.getSelectedFile());

            try {
                final File imageFile = fileChooser.getSelectedFile();

                mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        if (mMultiMediaObject.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
                            mMultiMediaObject.addFile(imageFile, imageFile.getName());
                        } else {
                            Property file = mMultiMediaObject.getProperty("FILE", false);

                            if (file != null && file instanceof PropertyFile) {
                                ((PropertyFile) file).addFile(imageFile, imageFile.getName());
                            } else {
                                mMultiMediaObject.addFile(imageFile, imageFile.getName());
                            }
                        }
                    }
                }); // end of doUnitOfWork

                mMultimediaFilesTableModel.clear();
                for (Property multimediaFile : mMultiMediaObject.getProperties("FILE", true)) {
                    if (multimediaFile != null && multimediaFile instanceof PropertyFile) {
                        mMultimediaFilesTableModel.add(((PropertyFile) multimediaFile).getFile());
                    }
                }
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_addFileButtonActionPerformed

    private void editFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFileButtonActionPerformed
        int selectedRow = multimediaFilesTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = multimediaFilesTable.convertRowIndexToModel(selectedRow);
            File multiMediafile = mMultimediaFilesTableModel.getValueAt(rowIndex);
            if (multiMediafile.exists()) {
                try {
                    Desktop.getDesktop().edit(multiMediafile);
                } catch (UnsupportedOperationException ex) {
                    try {
                        Desktop.getDesktop().open(multiMediafile);
                    } catch (IOException ex1) {
                        Exceptions.printStackTrace(ex1);
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_editFileButtonActionPerformed

    private void multimediaFilesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_multimediaFilesTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int selectedRow = multimediaFilesTable.getSelectedRow();
            if (selectedRow != -1) {
                int rowIndex = multimediaFilesTable.convertRowIndexToModel(selectedRow);
                File multiMediafile = mMultimediaFilesTableModel.getValueAt(rowIndex);
                try {
                    Desktop.getDesktop().open(multiMediafile);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_multimediaFilesTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFileButton;
    private javax.swing.JButton editFileButton;
    private javax.swing.JToolBar filesToolBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel multiMediaObjectIDLabel;
    private javax.swing.JTextField multiMediaObjectIDTextField;
    private javax.swing.JPanel multiMediaObjectReferencesPanel;
    private javax.swing.JTabbedPane multiMediaObjectTabbedPane;
    private javax.swing.JLabel multiMediaObjectTitleLabel;
    private javax.swing.JTextField multiMediaObjectTitleTextField;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable multimediaFilesTable;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JPanel notesPanel;
    private ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel referencesListPanel;
    // End of variables declaration//GEN-END:variables

    public void set(Property multiMediaObject) {
        if (multiMediaObject instanceof PropertyMedia) {
            mMultiMediaObject = ((PropertyMedia) multiMediaObject).getTargetEntity();
        } else {
            mMultiMediaObject = multiMediaObject;
        }
        mRoot = mMultiMediaObject.getParent() == null ? mMultiMediaObject : mMultiMediaObject.getParent();
        if (mMultiMediaObject instanceof Media) {
            multiMediaObjectIDTextField.setText(((Media) mMultiMediaObject).getId());
            multiMediaObjectTitleTextField.setText(((Media) mMultiMediaObject).getTitle());

            List<Entity> entitiesList = new ArrayList<Entity>();
            for (PropertyXRef entityRef : mMultiMediaObject.getProperties(PropertyXRef.class)) {
                entitiesList.add(entityRef.getTargetEntity());
            }
            referencesListPanel.set((Media) mMultiMediaObject, entitiesList);
        } else {
            Property propertyTitle = mMultiMediaObject.getProperty("TITL");
            multiMediaObjectTitleTextField.setText(propertyTitle != null ? propertyTitle.getValue() : "");
            multiMediaObjectIDTextField.setVisible(false);
            multiMediaObjectIDLabel.setVisible(false);
            multiMediaObjectTabbedPane.removeTabAt(multiMediaObjectTabbedPane.indexOfTab(NbBundle.getMessage(NoteEditorPanel.class, "MultiMediaObjectEditorPanel.multiMediaObjectReferencesPanel.TabConstraints.tabTitle")));
        }

        for (Property multimediaFile : mMultiMediaObject.getProperties("FILE", true)) {
            if (multimediaFile != null && multimediaFile instanceof PropertyFile) {
                mMultimediaFilesTableModel.add(((PropertyFile) multimediaFile).getFile());
            }
        }

        /*
         * +1 <<NOTE_STRUCTURE>>
         */
        noteCitationsListPanel.set(mMultiMediaObject, Arrays.asList(mMultiMediaObject.getProperties("NOTE")));
    }

    public Property commit() {
        try {
            mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    if (mMultiMediaObject instanceof Media) {
                        ((Media) mMultiMediaObject).setTitle(multiMediaObjectTitleTextField.getText());
                    } else {
                        Property propertyTitle = mMultiMediaObject.getProperty("TITL");
                        if (propertyTitle == null) {
                            mMultiMediaObject.addProperty("TITL", multiMediaObjectTitleTextField.getText());
                        } else {
                            propertyTitle.setValue(multiMediaObjectTitleTextField.getText());
                        }
                    }
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
        return mMultiMediaObject;
    }
}