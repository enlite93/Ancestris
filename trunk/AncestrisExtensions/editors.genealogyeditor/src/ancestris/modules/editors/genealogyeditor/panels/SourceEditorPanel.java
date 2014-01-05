package ancestris.modules.editors.genealogyeditor.panels;

import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openide.util.Exceptions;

/*
 * SOURCE_RECORD:=
 * n @<XREF:SOUR>@ SOUR
 * +1 DATA
 * +2 EVEN <EVENTS_RECORDED>
 * +3 DATE <DATE_PERIOD>
 * +3 PLAC <SOURCE_JURISDICTION_PLACE>
 * +2 AGNC <RESPONSIBLE_AGENCY>
 * +2 <<NOTE_STRUCTURE>>
 * +1 AUTH <SOURCE_ORIGINATOR>
 * +2 [CONC|CONT] <SOURCE_ORIGINATOR>
 * +1 TITL <SOURCE_DESCRIPTIVE_TITLE>
 * +2 [CONC|CONT] <SOURCE_DESCRIPTIVE_TITLE>
 * +1 ABBR <SOURCE_FILED_BY_ENTRY>
 * +1 PUBL <SOURCE_PUBLICATION_FACTS>
 * +2 [CONC|CONT] <SOURCE_PUBLICATION_FACTS>
 * +1 TEXT <TEXT_FROM_SOURCE>
 * +2 [CONC|CONT] <TEXT_FROM_SOURCE>
 * +1 <<SOURCE_REPOSITORY_CITATION>>
 * +1 REFN <USER_REFERENCE_NUMBER>
 * +2 TYPE <USER_REFERENCE_TYPE>
 * +1 RIN <AUTOMATED_RECORD_ID>
 * +1 <<CHANGE_DATE>>
 * +1 <<NOTE_STRUCTURE>>
 * +1 <<MULTIMEDIA_LINK>>
 */
/**
 *
 * @author dominique
 */
public class SourceEditorPanel extends javax.swing.JPanel {

    private Source mSource;

    /**
     * Creates new form SourceEditorPanel
     */
    public SourceEditorPanel() {
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

        sourceIDLabel = new javax.swing.JLabel();
        sourceIDTextField = new javax.swing.JTextField();
        authorLabel = new javax.swing.JLabel();
        authorTextField = new javax.swing.JTextField();
        sourceTitleLabel = new javax.swing.JLabel();
        sourceTitleTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        sourceInformationTabbedPane = new javax.swing.JTabbedPane();
        registeredEventsPanel = new javax.swing.JPanel();
        eventTypePanel1 = new ancestris.modules.editors.genealogyeditor.panels.SourceEventTypeListPanel();
        repositoriesPanel = new javax.swing.JPanel();
        repositoriesListPanel = new ancestris.modules.editors.genealogyeditor.panels.RepositoriesListPanel();
        notesPanel = new javax.swing.JPanel();
        notesListPanel = new ancestris.modules.editors.genealogyeditor.panels.NotesListPanel();
        referencesPanel = new javax.swing.JPanel();
        referencesListPanel = new ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel();
        multimediaObjectPanel = new javax.swing.JPanel();
        multimediaObjectCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel();

        sourceIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.sourceIDLabel.text"), new Object[] {})); // NOI18N

        sourceIDTextField.setColumns(8);
        sourceIDTextField.setEditable(false);
        sourceIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.sourceIDTextField.text"), new Object[] {})); // NOI18N

        authorLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.authorLabel.text"), new Object[] {})); // NOI18N

        authorTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.authorTextField.text"), new Object[] {})); // NOI18N

        sourceTitleLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.sourceTitleLabel.text"), new Object[] {})); // NOI18N

        sourceTitleTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.sourceTitleTextField.text"), new Object[] {})); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(SourceEditorPanel.class, "SourceEditorPanel.jLabel1.text")); // NOI18N

        sourceInformationTabbedPane.setRequestFocusEnabled(false);

        javax.swing.GroupLayout registeredEventsPanelLayout = new javax.swing.GroupLayout(registeredEventsPanel);
        registeredEventsPanel.setLayout(registeredEventsPanelLayout);
        registeredEventsPanelLayout.setHorizontalGroup(
            registeredEventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventTypePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        registeredEventsPanelLayout.setVerticalGroup(
            registeredEventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventTypePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        sourceInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.registeredEventsPanel.TabConstraints.tabTitle"), new Object[] {}), registeredEventsPanel); // NOI18N

        repositoriesPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout repositoriesPanelLayout = new javax.swing.GroupLayout(repositoriesPanel);
        repositoriesPanel.setLayout(repositoriesPanelLayout);
        repositoriesPanelLayout.setHorizontalGroup(
            repositoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, repositoriesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(repositoriesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        repositoriesPanelLayout.setVerticalGroup(
            repositoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(repositoriesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        sourceInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.repositoriesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Repository.png")), repositoriesPanel); // NOI18N

        notesPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        sourceInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        referencesPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout referencesPanelLayout = new javax.swing.GroupLayout(referencesPanel);
        referencesPanel.setLayout(referencesPanelLayout);
        referencesPanelLayout.setHorizontalGroup(
            referencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referencesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        referencesPanelLayout.setVerticalGroup(
            referencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        sourceInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("SourceEditorPanel.referencesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), referencesPanel); // NOI18N

        javax.swing.GroupLayout multimediaObjectPanelLayout = new javax.swing.GroupLayout(multimediaObjectPanel);
        multimediaObjectPanel.setLayout(multimediaObjectPanelLayout);
        multimediaObjectPanelLayout.setHorizontalGroup(
            multimediaObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        multimediaObjectPanelLayout.setVerticalGroup(
            multimediaObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        sourceInformationTabbedPane.addTab(org.openide.util.NbBundle.getMessage(SourceEditorPanel.class, "SourceEditorPanel.multimediaObjectPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), multimediaObjectPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sourceTitleLabel)
                            .addComponent(sourceIDLabel)
                            .addComponent(jLabel1)
                            .addComponent(authorLabel))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sourceIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(authorTextField)
                            .addComponent(sourceTitleTextField)
                            .addComponent(jTextField1)))
                    .addComponent(sourceInformationTabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceIDLabel)
                    .addComponent(sourceIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorLabel)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceTitleLabel)
                    .addComponent(sourceTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceInformationTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTextField authorTextField;
    private ancestris.modules.editors.genealogyeditor.panels.SourceEventTypeListPanel eventTypePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel multimediaObjectCitationsListPanel;
    private javax.swing.JPanel multimediaObjectPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NotesListPanel notesListPanel;
    private javax.swing.JPanel notesPanel;
    private ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel referencesListPanel;
    private javax.swing.JPanel referencesPanel;
    private javax.swing.JPanel registeredEventsPanel;
    private ancestris.modules.editors.genealogyeditor.panels.RepositoriesListPanel repositoriesListPanel;
    private javax.swing.JPanel repositoriesPanel;
    private javax.swing.JLabel sourceIDLabel;
    private javax.swing.JTextField sourceIDTextField;
    private javax.swing.JTabbedPane sourceInformationTabbedPane;
    private javax.swing.JLabel sourceTitleLabel;
    private javax.swing.JTextField sourceTitleTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the source
     */
    public Source getSource() {
        return mSource;
    }

    /**
     * @param source the source to set
     */
    /*
     * SOURCE_RECORD:=
     */
    public void setSource(Source source) {
        this.mSource = source;

        /*
         * n @<XREF:SOUR>@ SOUR
         */
        sourceIDTextField.setText(mSource.getId());

        /*
         * +1 DATA
         * +2 EVEN <EVENTS_RECORDED>
         * +3 DATE <DATE_PERIOD>
         * +3 PLAC <SOURCE_JURISDICTION_PLACE>
         * +2 AGNC <RESPONSIBLE_AGENCY>
         * +2 <<NOTE_STRUCTURE>>
         */
        Property sourceData = mSource.getProperty("DATA");
        if (sourceData != null) {
            Property[] sourceDataEvents = sourceData.getProperties("EVEN");
            eventTypePanel1.setEventTypesList(sourceData, Arrays.asList(sourceDataEvents));
        }

        /*
         * +1 AUTH <SOURCE_ORIGINATOR>
         * +2 [CONC|CONT] <SOURCE_ORIGINATOR>
         */
        Property sourceAuthor = mSource.getProperty("AUTH");
        authorTextField.setText(sourceAuthor != null ? sourceAuthor.getValue() : "");

        /*
         * +1 TITL <SOURCE_DESCRIPTIVE_TITLE>
         * +2 [CONC|CONT] <SOURCE_DESCRIPTIVE_TITLE>
         */
        Property sourceTitle = mSource.getProperty("TITL");
        sourceTitleTextField.setText(sourceTitle != null ? sourceTitle.getValue() : "");

        /*
         * +1 ABBR <SOURCE_FILED_BY_ENTRY>
         * Not used
         */

        /*
         * +1 PUBL <SOURCE_PUBLICATION_FACTS>
         * +2 [CONC|CONT] <SOURCE_PUBLICATION_FACTS>
         * Not used
         */

        /*
         * +1 TEXT <TEXT_FROM_SOURCE>
         * +2 [CONC|CONT] <TEXT_FROM_SOURCE>
         * Not used
         */

        /*
         * +1 <<SOURCE_REPOSITORY_CITATION>>
         */
        List<Repository> repositporiesList = new ArrayList<Repository>();
        for (PropertyRepository repositoryRef : mSource.getProperties(PropertyRepository.class)) {
            repositporiesList.add((Repository) repositoryRef.getTargetEntity());
        }
        repositoriesListPanel.set(mSource, repositporiesList);

        /*
         * +1 REFN <USER_REFERENCE_NUMBER>
         * Not used
         *
         * +2 TYPE <USER_REFERENCE_TYPE>
         * Not used
         *
         * +1 RIN <AUTOMATED_RECORD_ID>
         * Not used
         *
         * +1 <<CHANGE_DATE>>
         * Handle by gedcom doUnitOfWork
         * not displayed
         */

        /*
         * +1 <<NOTE_STRUCTURE>>
         */
        List<Note> notesList = new ArrayList<Note>();
        for (PropertyNote noteRef : mSource.getProperties(PropertyNote.class)) {
            notesList.add((Note) noteRef.getTargetEntity());
        }
        notesListPanel.setNotesList(mSource, notesList);

        /*
         * +1 <<MULTIMEDIA_LINK>>
         */
        multimediaObjectCitationsListPanel.set(mSource, Arrays.asList(mSource.getProperties("OBJE")));

        List<Entity> entitiesList = new ArrayList<Entity>();
        for (PropertyXRef entityRef : mSource.getProperties(PropertyXRef.class)) {
            entitiesList.add(entityRef.getTargetEntity());
        }
        referencesListPanel.set(mSource, entitiesList);
    }

    public Source commit() {
        try {
            mSource.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    Property sourceTitle = mSource.getProperty("TITL");
                    if (sourceTitle == null) {
                        mSource.addProperty("TITL", sourceTitleTextField.getText());
                    } else {
                        sourceTitle.setValue(sourceTitleTextField.getText());
                    }
                    Property sourceAuthor = mSource.getProperty("AUTH");
                    if (sourceAuthor == null) {
                        mSource.addProperty("AUTH", authorTextField.getText());
                    } else {
                        sourceAuthor.setValue(authorTextField.getText());
                    }
                    notesListPanel.commit();
                    repositoriesListPanel.commit();
                    referencesListPanel.commit();
                }
            }); // end of doUnitOfWork
            return mSource;
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }
}
