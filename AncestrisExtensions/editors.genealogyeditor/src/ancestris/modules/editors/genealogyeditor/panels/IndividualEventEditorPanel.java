package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.gedcom.utilities.PropertyTag2Name;
import ancestris.util.swing.DialogManager;
import ancestris.util.swing.DialogManager.ADialog;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */

/*
 *
 * EVENT_DETAIL:=
 * n TYPE <EVENT_OR_FACT_CLASSIFICATION>
 * n DATE <DATE_VALUE>
 * n <<PLACE_STRUCTURE>>
 * n <<ADDRESS_STRUCTURE>>
 * n AGNC <RESPONSIBLE_AGENCY>
 * n RELI <RELIGIOUS_AFFILIATION>
 * n CAUS <CAUSE_OF_EVENT>
 * n RESN <RESTRICTION_NOTICE>
 * n <<NOTE_STRUCTURE>>
 * n <<SOURCE_CITATION>>
 * n <<MULTIMEDIA_LINK>>
 *
 * INDIVIDUAL_EVENT_DETAIL:=
 * n <<EVENT_DETAIL>>
 * n AGE <AGE_AT_EVENT>
 *
 * INDIVIDUAL_EVENT_STRUCTURE:=
 * [
 * n [ BIRT | CHR ] [Y|<NULL>]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * +1 FAMC @<XREF:FAM>@
 * |
 * n DEAT [Y|<NULL>]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n [ BURI | CREM ]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n ADOP
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * +1 FAMC @<XREF:FAM>@
 * +2 ADOP <ADOPTED_BY_WHICH_PARENT>
 * |
 * n [ BAPM | BARM | BASM | BLES ]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n [ CHRA | CONF | FCOM | ORDN ]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n [ NATU | EMIG | IMMI ]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n [ CENS | PROB | WILL]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n [ GRAD | RETI ]
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * |
 * n EVEN
 * +1 <<INDIVIDUAL_EVENT_DETAIL>>
 * ]
 */
public class IndividualEventEditorPanel extends javax.swing.JPanel {

    private class DateBeanListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            PropertyAge age = (PropertyAge) mEvent.getProperty("AGE");
            if (age != null) {
                individualAgeTextField.setText(age.getValue());
            }
        }
    }
    private final ArrayList<String> mIndividualAttributesTags = new ArrayList<String>() {
        {
            /*
             * INDIVIDUAL_ATTRIBUTE
             */
            add("CAST");
            add("DSCR");
            add("EDUC");
            add("IDNO");
            add("NATI");
            add("NCHI");
            add("NMR");
            add("OCCU");
            add("PROP");
            add("RELI");
            add("RESI");
            add("SSN");
            add("TITL");
            add("FACT");
        }
    };
    private Property mEvent = null;
    private Property mRoot;
    private Property mAddress;
    private PropertyPlace mPlace;
    private PropertyDate mDate;
    private boolean mEventCauseModified = false;
    private boolean mIndividualAgeModified = false;
    private boolean mEventNameModified = false;
    private boolean mEventTypeModified = false;

    /**
     * Creates new form EventEditorPanel
     */
    public IndividualEventEditorPanel() {
        initComponents();
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

        eventInformationTabbedPane = new javax.swing.JTabbedPane();
        EventDetailPanel = new javax.swing.JPanel();
        placeLabel = new javax.swing.JLabel();
        placeTextField = new javax.swing.JTextField();
        privateRecordToggleButton = new javax.swing.JToggleButton();
        IndividualAgeLabel = new javax.swing.JLabel();
        EventTypeLabel = new javax.swing.JLabel();
        eventTypeTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        aDateBean = new ancestris.modules.beans.ADateBean();
        eventNameTextField = new javax.swing.JTextField();
        eventCauseLabel = new javax.swing.JLabel();
        eventNameLabel = new javax.swing.JLabel();
        individualAgeTextField = new javax.swing.JTextField();
        linkToPlaceButton = new javax.swing.JButton();
        editPlaceButton = new javax.swing.JButton();
        eventCauseTextField = new javax.swing.JTextField();
        sourcesPanel = new javax.swing.JPanel();
        sourceCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel();
        galleryPanel = new javax.swing.JPanel();
        multimediaObjectCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel();
        notesPanel = new javax.swing.JPanel();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        eventInformationTabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        EventDetailPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        placeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        placeLabel.setText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.placeLabel.text")); // NOI18N

        placeTextField.setText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.placeTextField.text")); // NOI18N

        privateRecordToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock_open.png"))); // NOI18N
        privateRecordToggleButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock_open.png"))); // NOI18N
        privateRecordToggleButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock.png"))); // NOI18N
        privateRecordToggleButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock.png"))); // NOI18N

        IndividualAgeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IndividualAgeLabel.setText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.IndividualAgeLabel.text")); // NOI18N

        EventTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        EventTypeLabel.setText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.EventTypeLabel.text")); // NOI18N

        eventTypeTextField.setColumns(16);
        eventTypeTextField.setToolTipText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.eventTypeTextField.toolTipText")); // NOI18N

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.dateLabel.text"), new Object[] {})); // NOI18N

        eventNameTextField.setEditable(false);

        eventCauseLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eventCauseLabel.setText(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.eventCauseLabel.text")); // NOI18N

        eventNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eventNameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.eventNameLabel.text"), new Object[] {})); // NOI18N

        individualAgeTextField.setEditable(false);
        individualAgeTextField.setColumns(4);

        linkToPlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToPlaceButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.linkToPlaceButton.toolTipText"), new Object[] {})); // NOI18N
        linkToPlaceButton.setFocusable(false);
        linkToPlaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkToPlaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToPlaceButtonActionPerformed(evt);
            }
        });

        editPlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editPlaceButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.editPlaceButton.toolTipText"), new Object[] {})); // NOI18N
        editPlaceButton.setFocusable(false);
        editPlaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editPlaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlaceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EventDetailPanelLayout = new javax.swing.GroupLayout(EventDetailPanel);
        EventDetailPanel.setLayout(EventDetailPanelLayout);
        EventDetailPanelLayout.setHorizontalGroup(
            EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventCauseLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IndividualAgeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(placeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventDetailPanelLayout.createSequentialGroup()
                        .addComponent(aDateBean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(privateRecordToggleButton))
                    .addGroup(EventDetailPanelLayout.createSequentialGroup()
                        .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(individualAgeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(eventNameTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EventTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EventDetailPanelLayout.createSequentialGroup()
                        .addComponent(placeTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linkToPlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPlaceButton))
                    .addComponent(eventCauseTextField))
                .addContainerGap())
        );
        EventDetailPanelLayout.setVerticalGroup(
            EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventDetailPanelLayout.createSequentialGroup()
                        .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel)
                            .addComponent(aDateBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eventCauseLabel)
                            .addComponent(eventCauseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(privateRecordToggleButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(eventNameLabel)
                        .addComponent(eventNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EventTypeLabel)
                        .addComponent(eventTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IndividualAgeLabel)
                    .addComponent(individualAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(linkToPlaceButton)
                    .addComponent(editPlaceButton)
                    .addGroup(EventDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(placeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(placeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        eventInformationTabbedPane.addTab(org.openide.util.NbBundle.getMessage(IndividualEventEditorPanel.class, "IndividualEventEditorPanel.EventDetailPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Place.png")), EventDetailPanel); // NOI18N

        sourceCitationsListPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout sourcesPanelLayout = new javax.swing.GroupLayout(sourcesPanel);
        sourcesPanel.setLayout(sourcesPanelLayout);
        sourcesPanelLayout.setHorizontalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sourcesPanelLayout.setVerticalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.sourcesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/source.png")), sourcesPanel); // NOI18N

        multimediaObjectCitationsListPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout galleryPanelLayout = new javax.swing.GroupLayout(galleryPanel);
        galleryPanel.setLayout(galleryPanelLayout);
        galleryPanelLayout.setHorizontalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        galleryPanelLayout.setVerticalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.galleryPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), galleryPanel); // NOI18N

        noteCitationsListPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEventEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventInformationTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventInformationTabbedPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void linkToPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToPlaceButtonActionPerformed
        PlacesListPanel placesListPanel = new PlacesListPanel(mRoot.getGedcom());
        DialogManager.ADialog placesListPanelDialog = new DialogManager.ADialog(
                NbBundle.getMessage(PlacesListPanel.class, "PlacesListPanel.title.link"),
                placesListPanel);
        placesListPanelDialog.setDialogId(PlacesListPanel.class.getName());

        if (placesListPanelDialog.show() == DialogDescriptor.OK_OPTION) {
            final PropertyPlace selectedPlace = placesListPanel.getSelectedPlace();
            if (selectedPlace != null) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            if (mPlace == null) {
                                mPlace = (PropertyPlace) mEvent.addProperty("PLAC", selectedPlace.format("all"));
                            } else {
                                mPlace.setValue(selectedPlace.format("all"));
                            }
                        }
                    }); // end of doUnitOfWork
                    placeTextField.setText(mPlace.getValueStartingWithCity());
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToPlaceButtonActionPerformed

    private void editPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlaceButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        PlaceEditorPanel placeEditorPanel = new PlaceEditorPanel();
        placeEditorPanel.set(mPlace);

        ADialog eventEditorDialog = new ADialog(
                NbBundle.getMessage(
                        PlaceEditorPanel.class, "PlaceEditorPanel.edit.title"),
                placeEditorPanel);
        eventEditorDialog.setDialogId(PlaceEditorPanel.class.getName());

        if (eventEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            placeEditorPanel.commit();
        } else {
            while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                gedcom.undoUnitOfWork(false);
            }
        }
    }//GEN-LAST:event_editPlaceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EventDetailPanel;
    private javax.swing.JLabel EventTypeLabel;
    private javax.swing.JLabel IndividualAgeLabel;
    private ancestris.modules.beans.ADateBean aDateBean;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton editPlaceButton;
    private javax.swing.JLabel eventCauseLabel;
    private javax.swing.JTextField eventCauseTextField;
    private javax.swing.JTabbedPane eventInformationTabbedPane;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JTextField eventNameTextField;
    private javax.swing.JTextField eventTypeTextField;
    private javax.swing.JPanel galleryPanel;
    private javax.swing.JTextField individualAgeTextField;
    private javax.swing.JButton linkToPlaceButton;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel multimediaObjectCitationsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JLabel placeLabel;
    private javax.swing.JTextField placeTextField;
    private javax.swing.JToggleButton privateRecordToggleButton;
    private ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel sourceCitationsListPanel;
    private javax.swing.JPanel sourcesPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @param event the event to set
     */
    public void set(Property root, Property event) {
        this.mRoot = root;
        this.mEvent = event;

        if (!mEvent.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
            privateRecordToggleButton.setVisible(false);
        }

        if (mEvent.getTag().equals("EVEN") || mEvent.getTag().equals("FACT")) {
            // Event Name
            eventNameLabel.setVisible(true);
            eventNameTextField.setVisible(true);
            eventNameTextField.setEditable(true);
            Property eventType = mEvent.getProperty("TYPE", false);
            if (eventType != null) {
                eventNameTextField.setText(eventType.getValue());
            } else {
                eventNameTextField.setText("");
            }
            eventNameTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }
            });

            eventCauseTextField.setText(mEvent.getValue());
            eventCauseTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }
            });
        } else if (mIndividualAttributesTags.contains(event.getTag())) {
            eventNameLabel.setVisible(true);
            eventNameTextField.setVisible(true);
            eventNameLabel.setText(PropertyTag2Name.getTagName(mEvent.getTag()));
            eventNameTextField.setText(mEvent.getValue());
            eventNameTextField.setEditable(true);
            eventNameTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventNameModified = true;
                }
            });

            Property eventType = mEvent.getProperty("TYPE");
            if (eventType != null) {
                eventTypeTextField.setText(eventType.getValue());
            }
            eventTypeTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }
            });

            Property eventCause = mEvent.getProperty("CAUS", false);
            if (eventCause != null) {
                eventCauseTextField.setText(eventCause.getValue());
            }
            eventCauseTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }
            });
        } else {
            // Event Name
            eventNameLabel.setVisible(false);
            eventNameTextField.setVisible(false);
            Property eventType = mEvent.getProperty("TYPE");
            if (eventType != null) {
                eventTypeTextField.setText(eventType.getValue());
            }
            eventTypeTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventTypeModified = true;
                }
            });

            Property eventCause = mEvent.getProperty("CAUS", false);
            if (eventCause != null) {
                eventCauseTextField.setText(eventCause.getValue());
            }
            eventCauseTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mEventCauseModified = true;
                }
            });
        }
        /*
         * +1 RESN <RESTRICTION_NOTICE>
         */
        Property restrictionNotice = mEvent.getProperty("RESN", true);
        if (restrictionNotice != null) {
            privateRecordToggleButton.setSelected(true);
        } else {
            privateRecordToggleButton.setSelected(false);
        }

        mDate = (PropertyDate) mEvent.getProperty("DATE", false);
        if (mDate == null) {
            try {
                mEvent.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mDate = (PropertyDate) mEvent.addProperty("DATE", "");
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        aDateBean.setContext(mDate);
        aDateBean.addChangeListener(new DateBeanListener());

        PropertyAge age = (PropertyAge) mEvent.getProperty("AGE", false);
        if (age != null) {
            individualAgeTextField.setText(age.getValue());
        }
        /*
         * Remove modification of age property
         * Need to be better handle
         *
         * individualAgeTextField.getDocument().addDocumentListener(new DocumentListener() {
         *
         * @Override
         * public void changedUpdate(DocumentEvent e) {
         * mIndividualAgeModified = true;
         * }
         *
         * @Override
         * public void removeUpdate(DocumentEvent e) {
         * mIndividualAgeModified = true;
         * }
         *
         * @Override
         * public void insertUpdate(DocumentEvent e) {
         * mIndividualAgeModified = true;
         * }
         * });
         *
         */
        mPlace = (PropertyPlace) mEvent.getProperty(PropertyPlace.TAG, false);
        if (mPlace != null) {
            placeTextField.setText(mPlace.getValueStartingWithCity());
        } else {
            placeTextField.setText("");
        }

        mAddress = mEvent.getProperty("ADDR", false);
//        addressPanel.set(mEvent, mAddress);

        /*        if (mPlace == null && mAddress != null) {
         jCheckBox1.setSelected(true);
         CardLayout cl = (CardLayout) (jPanel2.getLayout());
         cl.show(jPanel2, "address");
         }
         */
        Property[] sourcesList = mEvent.getProperties("SOUR");
        sourceCitationsListPanel.set(mEvent, Arrays.asList(sourcesList));

        noteCitationsListPanel.set(mEvent, Arrays.asList(mEvent.getProperties("NOTE")));

        multimediaObjectCitationsListPanel.set(mEvent, Arrays.asList(mEvent.getProperties("OBJE")));
    }

    public Property commit() {
        if (mRoot == null) {
            return null;
        }

        try {
            mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    if (mEvent.getTag().equals("EVEN") || mEvent.getTag().equals("FACT")) {
                        if (mEventNameModified) {
                            Property eventType = mEvent.getProperty("TYPE", false);
                            if (eventType != null) {
                                eventType.setValue(eventNameTextField.getText());
                            } else {
                                mEvent.addProperty("TYPE", eventNameTextField.getText());
                            }
                        }
                        if (mEventCauseModified) {
                            mEvent.setValue(eventCauseTextField.getText());
                        }
                    } else if (mIndividualAttributesTags.contains(mEvent.getTag())) {
                        if (mEventNameModified) {
                            mEvent.setValue(eventNameTextField.getText());
                        }
                        if (mEventTypeModified) {
                            Property eventType = mEvent.getProperty("TYPE", false);
                            if (eventType != null) {
                                eventType.setValue(eventTypeTextField.getText());
                            } else {
                                mEvent.addProperty("TYPE", eventTypeTextField.getText());
                            }
                        }
                        if (mEventCauseModified) {
                            String causeText = eventCauseTextField.getText();
                            Property eventCause = mEvent.getProperty("CAUS", false);
                            if (causeText.length() > 0) {
                                if (eventCause == null) {
                                    mEvent.addProperty("CAUS", causeText);
                                } else {
                                    eventCause.setValue(causeText);
                                }
                            } else if (eventCause != null) {
                                mRoot.delProperty(eventCause);
                            }
                        }
                    } else {
                        if (mEventTypeModified) {
                            Property eventType = mEvent.getProperty("TYPE", false);
                            if (eventType != null) {
                                eventType.setValue(eventTypeTextField.getText());
                            } else {
                                mEvent.addProperty("TYPE", eventTypeTextField.getText());
                            }
                        }
                        if (mEventCauseModified) {
                            String causeText = eventCauseTextField.getText();
                            Property eventCause = mEvent.getProperty("CAUS", false);
                            if (causeText.length() > 0) {
                                if (eventCause == null) {
                                    mEvent.addProperty("CAUS", causeText);
                                } else {
                                    eventCause.setValue(causeText);
                                }
                            } else if (eventCause != null) {
                                mRoot.delProperty(eventCause);
                            }
                        }
                    }
                    aDateBean.commit();

                    Property restrictionNotice = mEvent.getProperty("RESN", true);
                    if (privateRecordToggleButton.isSelected()) {
                        if (restrictionNotice == null) {
                            mEvent.addProperty("RESN", "confidential");
                        }
                    } else {
                        if (restrictionNotice != null) {
                            mEvent.delProperty(restrictionNotice);
                        }
                    }

                    if (mIndividualAgeModified) {
                        PropertyAge age = (PropertyAge) mEvent.getProperty("AGE", false);
                        if (age != null) {
                            age.setValue(individualAgeTextField.getText() + " y");
                        } else {
                            mEvent.addProperty("AGE", individualAgeTextField.getText() + " y");
                        }
                    }
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
//        gedcomPlacePanel.commit();
//        addressPanel.commit();
        return mEvent;
    }
}
