package ancestris.modules.editors.genealogyeditor.panels;

import genj.gedcom.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dominique
 */
public final class IndividualEditorPanel extends javax.swing.JPanel {

    private Indi mIndividual;

    /**
     * Creates new form IndividualEditorPanel
     */
    public IndividualEditorPanel() {
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

        generalPanel = new javax.swing.JPanel();
        individualIDLabel = new javax.swing.JLabel();
        individualIDTextField = new javax.swing.JTextField();
        nameEditorPanel = new ancestris.modules.editors.genealogyeditor.panels.NameEditorPanel();
        sexBeanPanel = new ancestris.modules.editors.genealogyeditor.beans.SexBean();
        imageBean = new ancestris.modules.editors.genealogyeditor.beans.ImageBean();
        individualInformationTabbedPane = new javax.swing.JTabbedPane();
        eventsPanel = new javax.swing.JPanel();
        eventsListPanel = new ancestris.modules.editors.genealogyeditor.panels.EventsListPanel();
        familiesChildPanel = new javax.swing.JPanel();
        familiesChildListPanel = new ancestris.modules.editors.genealogyeditor.panels.FamiliesListPanel(FamiliesListPanel.EDIT_FAMC);
        familiesSpousePanel = new javax.swing.JPanel();
        familiesSpouseListPanel = new ancestris.modules.editors.genealogyeditor.panels.FamiliesListPanel(FamiliesListPanel.EDIT_FAMS);
        sourcesPanel = new javax.swing.JPanel();
        sourceCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel();
        namesPanel = new javax.swing.JPanel();
        namesListPanel = new ancestris.modules.editors.genealogyeditor.panels.NamesListPanel();
        notesPanel = new javax.swing.JPanel();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();
        referencesPanel = new javax.swing.JPanel();
        associationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.AssociationsListPanel();
        galleryPanel = new javax.swing.JPanel();
        multimediaObjectCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel();

        setMinimumSize(new java.awt.Dimension(758, 380));
        setName(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.name"), new Object[] {})); // NOI18N

        individualIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.individualIDLabel.text"), new Object[] {})); // NOI18N

        individualIDTextField.setColumns(8);
        individualIDTextField.setEditable(false);
        individualIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.individualIDTextField.text"), new Object[] {})); // NOI18N
        individualIDTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.individualIDTextField.toolTipText"), new Object[] {})); // NOI18N

        imageBean.setPreferredSize(new java.awt.Dimension(90, 120));

        javax.swing.GroupLayout imageBeanLayout = new javax.swing.GroupLayout(imageBean);
        imageBean.setLayout(imageBeanLayout);
        imageBeanLayout.setHorizontalGroup(
            imageBeanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        imageBeanLayout.setVerticalGroup(
            imageBeanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(sexBeanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(individualIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(individualIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sexBeanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(individualIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(individualIDLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameEditorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(imageBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        individualInformationTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        javax.swing.GroupLayout eventsPanelLayout = new javax.swing.GroupLayout(eventsPanel);
        eventsPanel.setLayout(eventsPanelLayout);
        eventsPanelLayout.setHorizontalGroup(
            eventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        eventsPanelLayout.setVerticalGroup(
            eventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsPanelLayout.createSequentialGroup()
                .addComponent(eventsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.eventsPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/event.png")), eventsPanel); // NOI18N

        javax.swing.GroupLayout familiesChildPanelLayout = new javax.swing.GroupLayout(familiesChildPanel);
        familiesChildPanel.setLayout(familiesChildPanelLayout);
        familiesChildPanelLayout.setHorizontalGroup(
            familiesChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(familiesChildListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        familiesChildPanelLayout.setVerticalGroup(
            familiesChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(familiesChildListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.familiesChildPanel.TabConstraints.tabTitle"), new Object[] {}), familiesChildPanel); // NOI18N

        javax.swing.GroupLayout familiesSpousePanelLayout = new javax.swing.GroupLayout(familiesSpousePanel);
        familiesSpousePanel.setLayout(familiesSpousePanelLayout);
        familiesSpousePanelLayout.setHorizontalGroup(
            familiesSpousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(familiesSpouseListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        familiesSpousePanelLayout.setVerticalGroup(
            familiesSpousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(familiesSpouseListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.familiesSpousePanel.TabConstraints.tabTitle"), new Object[] {}), familiesSpousePanel); // NOI18N

        javax.swing.GroupLayout sourcesPanelLayout = new javax.swing.GroupLayout(sourcesPanel);
        sourcesPanel.setLayout(sourcesPanelLayout);
        sourcesPanelLayout.setHorizontalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        sourcesPanelLayout.setVerticalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sourcesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.sourcesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Source.png")), sourcesPanel); // NOI18N

        javax.swing.GroupLayout namesPanelLayout = new javax.swing.GroupLayout(namesPanel);
        namesPanel.setLayout(namesPanelLayout);
        namesPanelLayout.setHorizontalGroup(
            namesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(namesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        namesPanelLayout.setVerticalGroup(
            namesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, namesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(namesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.namesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Name.png")), namesPanel); // NOI18N

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        javax.swing.GroupLayout referencesPanelLayout = new javax.swing.GroupLayout(referencesPanel);
        referencesPanel.setLayout(referencesPanelLayout);
        referencesPanelLayout.setHorizontalGroup(
            referencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(associationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        referencesPanelLayout.setVerticalGroup(
            referencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referencesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(associationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );

        individualInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("IndividualEditorPanel.referencesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), referencesPanel); // NOI18N

        javax.swing.GroupLayout galleryPanelLayout = new javax.swing.GroupLayout(galleryPanel);
        galleryPanel.setLayout(galleryPanelLayout);
        galleryPanelLayout.setHorizontalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        galleryPanelLayout.setVerticalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        individualInformationTabbedPane.addTab("Gallery", new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), galleryPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(individualInformationTabbedPane)
                    .addComponent(generalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(individualInformationTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.editors.genealogyeditor.panels.AssociationsListPanel associationsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.EventsListPanel eventsListPanel;
    private javax.swing.JPanel eventsPanel;
    private ancestris.modules.editors.genealogyeditor.panels.FamiliesListPanel familiesChildListPanel;
    private javax.swing.JPanel familiesChildPanel;
    private ancestris.modules.editors.genealogyeditor.panels.FamiliesListPanel familiesSpouseListPanel;
    private javax.swing.JPanel familiesSpousePanel;
    private javax.swing.JPanel galleryPanel;
    private javax.swing.JPanel generalPanel;
    private ancestris.modules.editors.genealogyeditor.beans.ImageBean imageBean;
    private javax.swing.JLabel individualIDLabel;
    private javax.swing.JTextField individualIDTextField;
    private javax.swing.JTabbedPane individualInformationTabbedPane;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel multimediaObjectCitationsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NameEditorPanel nameEditorPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NamesListPanel namesListPanel;
    private javax.swing.JPanel namesPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JPanel referencesPanel;
    private ancestris.modules.editors.genealogyeditor.beans.SexBean sexBeanPanel;
    private ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel sourceCitationsListPanel;
    private javax.swing.JPanel sourcesPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the individual
     */
    public Indi getIndividual() {
        return mIndividual;
    }

    /**
     * @param individual the individual to set
     */
    public void set(Indi individual) {
        this.mIndividual = individual;

        individualIDTextField.setText(individual.getId());
        List<PropertyName> namesList = individual.getProperties(PropertyName.class);
        if (namesList.size() > 0) {
            PropertyName name = namesList.remove(0);
            if (name != null) {
                nameEditorPanel.set(individual, name);
            }
        } else {
            nameEditorPanel.set(individual, null);
        }
        namesListPanel.setNamesList(individual, namesList);

        PropertySex sex = (PropertySex) individual.getProperty("SEX", true);
        if (sex == null) {
            individual.setSex(PropertySex.UNKNOWN);
            sex = (PropertySex) individual.getProperty("SEX", true);
        }
        sexBeanPanel.set(individual, sex);

        List<PropertyEvent> eventsList = individual.getProperties(PropertyEvent.class);
        for (Fam family : individual.getFamiliesWhereSpouse()) {
            eventsList.addAll(family.getProperties(PropertyEvent.class));
        }
        eventsListPanel.setEventsList(individual, eventsList);

        List<Fam> familiesChildList = Arrays.asList(individual.getFamiliesWhereChild());
        familiesChildListPanel.setFamiliesList(individual, familiesChildList);

        List<Fam> familiesSpouseList = Arrays.asList(individual.getFamiliesWhereSpouse());
        familiesSpouseListPanel.setFamiliesList(individual, familiesSpouseList);

        Property[] sourcesList = individual.getProperties("SOUR");
        sourceCitationsListPanel.set(individual, Arrays.asList(sourcesList));

        noteCitationsListPanel.setNotesList(individual, Arrays.asList(individual.getProperties("NOTE")));

        associationsListPanel.setAssociationsList(individual, individual.getProperties(PropertyAssociation.class));


        Property multimediaObject = individual.getProperty("OBJE");
        if (multimediaObject != null) {
            imageBean.setImage(individual, multimediaObject);
        } else {
            imageBean.setImage(individual, null);
        }
        multimediaObjectCitationsListPanel.set(individual, Arrays.asList(individual.getProperties("OBJE")));
    }

    public Indi commit() {
        nameEditorPanel.commit();
        imageBean.commit();
        sexBeanPanel.commit();
        eventsListPanel.commit();
        namesListPanel.commit();
        sourceCitationsListPanel.commit();
        noteCitationsListPanel.commit();
        associationsListPanel.commit();
        multimediaObjectCitationsListPanel.commit();
        return mIndividual;
    }
}