package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.EventsTypeComboBoxModelModel;
import ancestris.modules.gedcom.utilities.PropertyTag2Name;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class EventEditorPanel extends javax.swing.JPanel {

    private PropertyEvent mEvent = null;
    private Property mRoot;
    PropertyPlace mPlace;

    /**
     * Creates new form EventEditorPanel
     */
    public EventEditorPanel() {
        initComponents();
        eventIdLabel.setVisible(false);
        eventIDTextField.setVisible(false);
        aDateBean.setPreferHorizontal(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        eventIdLabel = new javax.swing.JLabel();
        eventIDTextField = new javax.swing.JTextField();
        eventDescriptionLabel = new javax.swing.JLabel();
        eventPlaceLabel = new javax.swing.JLabel();
        eventPlaceTextField = new javax.swing.JTextField();
        addPlaceButton = new javax.swing.JButton();
        editPlaceButton = new javax.swing.JButton();
        removePlaceButton = new javax.swing.JButton();
        eventDescriptionScrollPane = new javax.swing.JScrollPane();
        eventDescriptionTextArea = new javax.swing.JTextArea();
        aDateBean = new ancestris.modules.beans.ADateBean();
        eventTypeLabel = new javax.swing.JLabel();
        eventTypeTextField = new javax.swing.JTextField();
        eventInformationTabbedPane = new javax.swing.JTabbedPane();
        sourcesPanel = new javax.swing.JPanel();
        sourcesListPanel = new ancestris.modules.editors.genealogyeditor.panels.SourcesListPanel();
        notesPanel = new javax.swing.JPanel();
        notesListPanel = new ancestris.modules.editors.genealogyeditor.panels.NotesListPanel();
        galleryPanel = new javax.swing.JPanel();
        multimediaObjectsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectsListPanel();

        dateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.dateLabel.text"), new Object[] {})); // NOI18N

        eventIdLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventIdLabel.text"), new Object[] {})); // NOI18N

        eventIDTextField.setColumns(8);
        eventIDTextField.setEditable(false);
        eventIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventIDTextField.text"), new Object[] {})); // NOI18N
        eventIDTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventIDTextField.toolTipText"), new Object[] {})); // NOI18N

        eventDescriptionLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventDescriptionLabel.text"), new Object[] {})); // NOI18N

        eventPlaceLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventPlaceLabel.text"), new Object[] {})); // NOI18N

        eventPlaceTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventPlaceTextField.text"), new Object[] {})); // NOI18N
        eventPlaceTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventPlaceTextField.toolTipText"), new Object[] {})); // NOI18N

        addPlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addPlaceButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.addPlaceButton.text"), new Object[] {})); // NOI18N
        addPlaceButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.addPlaceButton.toolTipText"), new Object[] {})); // NOI18N
        addPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlaceButtonActionPerformed(evt);
            }
        });

        editPlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editPlaceButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.editPlaceButton.text"), new Object[] {})); // NOI18N
        editPlaceButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.editPlaceButton.toolTipText"), new Object[] {})); // NOI18N
        editPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlaceButtonActionPerformed(evt);
            }
        });

        removePlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        removePlaceButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.removePlaceButton.toolTipText"), new Object[] {})); // NOI18N
        removePlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePlaceButtonActionPerformed(evt);
            }
        });

        eventDescriptionTextArea.setColumns(20);
        eventDescriptionTextArea.setRows(5);
        eventDescriptionTextArea.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventDescriptionTextArea.toolTipText"), new Object[] {})); // NOI18N
        eventDescriptionTextArea.setWrapStyleWord(true);
        eventDescriptionScrollPane.setViewportView(eventDescriptionTextArea);

        eventTypeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventTypeLabel.text"), new Object[] {})); // NOI18N

        eventTypeTextField.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(eventIdLabel)
                .addGap(62, 62, 62)
                .addComponent(eventIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(eventDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eventPlaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(eventTypeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eventTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aDateBean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(eventDescriptionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eventPlaceTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removePlaceButton))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventIdLabel)
                    .addComponent(eventIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dateLabel)
                    .addComponent(aDateBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eventDescriptionLabel)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eventDescriptionScrollPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventPlaceLabel)
                    .addComponent(eventPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPlaceButton)
                    .addComponent(editPlaceButton)
                    .addComponent(removePlaceButton)))
        );

        sourcesListPanel.setPreferredSize(null);

        javax.swing.GroupLayout sourcesPanelLayout = new javax.swing.GroupLayout(sourcesPanel);
        sourcesPanel.setLayout(sourcesPanelLayout);
        sourcesPanelLayout.setHorizontalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourcesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        sourcesPanelLayout.setVerticalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourcesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.sourcesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Source.png")), sourcesPanel); // NOI18N

        notesListPanel.setPreferredSize(null);

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        multimediaObjectsListPanel.setPreferredSize(null);

        javax.swing.GroupLayout galleryPanelLayout = new javax.swing.GroupLayout(galleryPanel);
        galleryPanel.setLayout(galleryPanelLayout);
        galleryPanelLayout.setHorizontalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        galleryPanelLayout.setVerticalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.galleryPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), galleryPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(eventInformationTabbedPane)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventInformationTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlaceButtonActionPerformed
        PlaceEditorPanel placeEditorPanel = new PlaceEditorPanel();
        PropertyPlace place = (PropertyPlace) mEvent.getProperty(PropertyPlace.TAG);
        placeEditorPanel.set(place);
        DialogManager.ADialog placeEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.title"),
                placeEditorPanel);
        placeEditorDialog.setDialogId(PlaceEditorPanel.class.getName());
        if (placeEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            mPlace = placeEditorPanel.commit();
            addPlaceButton.setVisible(false);
            editPlaceButton.setVisible(true);
            removePlaceButton.setVisible(true);
            eventPlaceTextField.setText(mPlace.format("all"));
        } else {
            addPlaceButton.setVisible(true);
            editPlaceButton.setVisible(false);
            removePlaceButton.setVisible(false);
            eventPlaceTextField.setText("");
        }
    }//GEN-LAST:event_editPlaceButtonActionPerformed

    private void addPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlaceButtonActionPerformed
        try {
            mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mPlace = (PropertyPlace) mEvent.addProperty("PLAC", "");
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            PlaceEditorPanel placeEditorPanel = new PlaceEditorPanel();
            placeEditorPanel.set(mPlace);

            DialogManager.ADialog placeEditorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.title"),
                    placeEditorPanel);
            placeEditorDialog.setDialogId(EventEditorPanel.class.getName());

            if (placeEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                placeEditorPanel.commit();
                addPlaceButton.setVisible(false);
                editPlaceButton.setVisible(true);
                removePlaceButton.setVisible(true);
                eventPlaceTextField.setText(mPlace.format("all"));
            } else {
                mRoot.getGedcom().undoUnitOfWork(false);

                addPlaceButton.setVisible(true);
                editPlaceButton.setVisible(false);
                removePlaceButton.setVisible(false);
                eventPlaceTextField.setText("");
            }
        }
    }//GEN-LAST:event_addPlaceButtonActionPerformed

    private void removePlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePlaceButtonActionPerformed
        try {
            mEvent.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mEvent.delProperties(PropertyPlace.TAG);
                }
            }); // end of doUnitOfWork
            addPlaceButton.setVisible(true);
            editPlaceButton.setVisible(false);
            removePlaceButton.setVisible(false);
            eventPlaceTextField.setText("");
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_removePlaceButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.beans.ADateBean aDateBean;
    private javax.swing.JButton addPlaceButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton editPlaceButton;
    private javax.swing.JLabel eventDescriptionLabel;
    private javax.swing.JScrollPane eventDescriptionScrollPane;
    private javax.swing.JTextArea eventDescriptionTextArea;
    private javax.swing.JTextField eventIDTextField;
    private javax.swing.JLabel eventIdLabel;
    private javax.swing.JTabbedPane eventInformationTabbedPane;
    private javax.swing.JLabel eventPlaceLabel;
    private javax.swing.JTextField eventPlaceTextField;
    private javax.swing.JLabel eventTypeLabel;
    private javax.swing.JTextField eventTypeTextField;
    private javax.swing.JPanel galleryPanel;
    private javax.swing.JPanel jPanel1;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectsListPanel multimediaObjectsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NotesListPanel notesListPanel;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JButton removePlaceButton;
    private ancestris.modules.editors.genealogyeditor.panels.SourcesListPanel sourcesListPanel;
    private javax.swing.JPanel sourcesPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @param event the event to set
     */
    public void set(Property root, PropertyEvent event) {
        this.mRoot = root;
        this.mEvent = event;
        if (mEvent != null) {
            eventTypeTextField.setText(PropertyTag2Name.getTagName(mEvent.getTag()));
            eventDescriptionTextArea.setText(mEvent.getDisplayValue());

            PropertyPlace place = (PropertyPlace) mEvent.getProperty(PropertyPlace.TAG);
            if (place != null) {
                addPlaceButton.setVisible(false);
                editPlaceButton.setVisible(true);
                removePlaceButton.setVisible(true);
                eventPlaceTextField.setText(place.format("all"));
            } else {
                addPlaceButton.setVisible(true);
                editPlaceButton.setVisible(false);
                removePlaceButton.setVisible(false);
                eventPlaceTextField.setText("");
            }

            PropertyDate date = (PropertyDate) mEvent.getProperty("DATE");
            if (date == null) {
                date = (PropertyDate) mEvent.addProperty("DATE", "");
            }
            aDateBean.setContext(date);

            List<Source> sourcesList = new ArrayList<Source>();
            for (PropertySource sourceRef : mEvent.getProperties(PropertySource.class)) {
                sourcesList.add((Source) sourceRef.getTargetEntity());
            }
            sourcesListPanel.set(mEvent, sourcesList);

            List<Note> notesList = new ArrayList<Note>();
            for (PropertyNote noteRef : mEvent.getProperties(PropertyNote.class)) {
                notesList.add((Note) noteRef.getTargetEntity());
            }
            notesListPanel.setNotesList(mEvent, notesList);

            List<Media> mediasList = new ArrayList<Media>();
            for (PropertyMedia mediaRef : mEvent.getProperties(PropertyMedia.class)) {
                mediasList.add((Media) mediaRef.getTargetEntity());
            }
            multimediaObjectsListPanel.set(mEvent, mediasList);
        }
    }

    public PropertyEvent commit() {
        try {
            mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    aDateBean.commit();
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
        sourcesListPanel.commit();
        notesListPanel.commit();
        multimediaObjectsListPanel.commit();
        return mEvent;
    }
}
