package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.utilities.PropertyTag2Name;
import genj.gedcom.*;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.Exceptions;

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
 *
 * FAMILY_EVENT_DETAIL:=
 * n HUSB
 * +1 AGE <AGE_AT_EVENT>
 * n WIFE
 * +1 AGE <AGE_AT_EVENT>
 * n <<EVENT_DETAIL>>
 *
 * FAMILY_EVENT_STRUCTURE:=
 * [
 * n [ ANUL | CENS | DIV | DIVF ]
 * +1 <<FAMILY_EVENT_DETAIL>>
 * |
 * n [ ENGA | MARB | MARC ]
 * +1 <<FAMILY_EVENT_DETAIL>>
 * |
 * n MARR [Y|<NULL>]
 * +1 <<FAMILY_EVENT_DETAIL>>
 * |
 * n [ MARL | MARS ]
 * +1 <<FAMILY_EVENT_DETAIL>>
 * |
 * n RESI
 * +1 <<FAMILY_EVENT_DETAIL>>
 * |
 * n EVEN [<EVENT_DESCRIPTOR> | <NULL>]
 * +1 <<FAMILY_EVENT_DETAIL>>
 * ]
 */
public class EventEditorPanel extends javax.swing.JPanel {

    private class DateBeanListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (mEventType == INDIVIDUAL_EVENT_TYPE) {
                PropertyAge age = (PropertyAge) mEvent.getProperty("AGE");
                if (age != null) {
                    individualAgeTextField.setText(age.getValue());
                }
            } else if (mEventType == FAMILY_EVENT_TYPE) {
                PropertyAge husbandAge = (PropertyAge) mEvent.getPropertyByPath(".:HUSB:AGE");
                if (husbandAge != null) {
                    husbandAgeTextField.setText(husbandAge.getValue());
                }
                PropertyAge wifeAge = (PropertyAge) mEvent.getPropertyByPath(".:WIFE:AGE");
                if (wifeAge != null) {
                    wifeAgeTextField.setText(wifeAge.getValue());
                }
            }
        }
    }
    public final static int INDIVIDUAL_EVENT_TYPE = 1;
    public final static int FAMILY_EVENT_TYPE = 2;
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
//            add("FACT"); not defined in gedcom xml definition files
        }
    };
    private int mEventType = INDIVIDUAL_EVENT_TYPE;
    private Property mEvent = null;
    private Property mRoot;
    private Property mAddress;
    private PropertyPlace mPlace;
    private PropertyDate mDate;
    private boolean mEventCauseModified = false;
    private boolean mIndividualAgeModified = false;
    private boolean mHusbandAgeModified = false;
    private boolean mWifeAgeModified = false;
    private boolean mEventNameModified = false;
    private boolean mEventTypeModified = false;

    /**
     * Creates new form EventEditorPanel
     */
    public EventEditorPanel() {
        this(INDIVIDUAL_EVENT_TYPE);
    }

    public EventEditorPanel(int eventType) {
        mEventType = eventType;
        initComponents();
        eventIdLabel.setVisible(false);
        eventIDTextField.setVisible(false);
        aDateBean.setPreferHorizontal(true);
        if (eventType == INDIVIDUAL_EVENT_TYPE) {
            CardLayout cl = (CardLayout) (agePanel.getLayout());
            cl.show(agePanel, "IndividualCard");
        } else if (eventType == FAMILY_EVENT_TYPE) {
            CardLayout cl = (CardLayout) (agePanel.getLayout());
            cl.show(agePanel, "familyCard");
        } else {
            agePanel.setVisible(false);
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

        jPanel1 = new javax.swing.JPanel();
        eventIdLabel = new javax.swing.JLabel();
        eventIDTextField = new javax.swing.JTextField();
        eventNameLabel = new javax.swing.JLabel();
        eventNameTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        aDateBean = new ancestris.modules.beans.ADateBean();
        eventCauseLabel = new javax.swing.JLabel();
        eventCauseScrollPane = new javax.swing.JScrollPane();
        eventCauseTextArea = new javax.swing.JTextArea();
        EventTypeLabel = new javax.swing.JLabel();
        eventTypeTextField = new javax.swing.JTextField();
        privateRecordToggleButton = new javax.swing.JToggleButton();
        agePanel = new javax.swing.JPanel();
        familyAgePanel = new javax.swing.JPanel();
        husbandAgeLabel = new javax.swing.JLabel();
        husbandAgeTextField = new javax.swing.JTextField();
        wifeAgeLabel = new javax.swing.JLabel();
        wifeAgeTextField = new javax.swing.JTextField();
        individualAgePanel = new javax.swing.JPanel();
        IndividualAgeLabel = new javax.swing.JLabel();
        individualAgeTextField = new javax.swing.JTextField();
        eventInformationTabbedPane = new javax.swing.JTabbedPane();
        placePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        gedcomPlacePanel = new ancestris.modules.editors.genealogyeditor.panels.GedcomPlaceEditorPanel();
        addressPanel = new ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel();
        sourcesPanel = new javax.swing.JPanel();
        sourceCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel();
        galleryPanel = new javax.swing.JPanel();
        multimediaObjectCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel();
        notesPanel = new javax.swing.JPanel();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        helpEditorPane = new javax.swing.JEditorPane();

        eventIdLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventIdLabel.text"), new Object[] {})); // NOI18N

        eventIDTextField.setEditable(false);
        eventIDTextField.setColumns(8);

        eventNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eventNameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.eventNameLabel.text"), new Object[] {})); // NOI18N

        eventNameTextField.setEditable(false);

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.dateLabel.text"), new Object[] {})); // NOI18N

        eventCauseLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eventCauseLabel.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.eventCauseLabel.text")); // NOI18N

        eventCauseTextArea.setColumns(20);
        eventCauseTextArea.setLineWrap(true);
        eventCauseTextArea.setRows(2);
        eventCauseTextArea.setToolTipText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.eventCauseTextArea.toolTipText")); // NOI18N
        eventCauseTextArea.setWrapStyleWord(true);
        eventCauseScrollPane.setViewportView(eventCauseTextArea);

        EventTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        EventTypeLabel.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.EventTypeLabel.text")); // NOI18N

        eventTypeTextField.setColumns(16);
        eventTypeTextField.setToolTipText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.eventTypeTextField.toolTipText")); // NOI18N

        privateRecordToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock_open.png"))); // NOI18N
        privateRecordToggleButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock_open.png"))); // NOI18N
        privateRecordToggleButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock.png"))); // NOI18N
        privateRecordToggleButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/lock.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(eventIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EventTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventTypeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(eventNameTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventCauseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(aDateBean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(privateRecordToggleButton))
                    .addComponent(eventCauseScrollPane)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventIdLabel)
                    .addComponent(eventIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(eventNameLabel)
                        .addComponent(eventNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(privateRecordToggleButton))
                    .addComponent(aDateBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EventTypeLabel)
                        .addComponent(eventTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eventCauseScrollPane)
                    .addComponent(eventCauseLabel))
                .addContainerGap())
        );

        agePanel.setLayout(new java.awt.CardLayout());

        husbandAgeLabel.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.husbandAgeLabel.text")); // NOI18N

        husbandAgeTextField.setEditable(false);
        husbandAgeTextField.setColumns(4);

        wifeAgeLabel.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.wifeAgeLabel.text")); // NOI18N

        wifeAgeTextField.setEditable(false);
        wifeAgeTextField.setColumns(4);

        javax.swing.GroupLayout familyAgePanelLayout = new javax.swing.GroupLayout(familyAgePanel);
        familyAgePanel.setLayout(familyAgePanelLayout);
        familyAgePanelLayout.setHorizontalGroup(
            familyAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familyAgePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(husbandAgeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(husbandAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(wifeAgeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wifeAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );
        familyAgePanelLayout.setVerticalGroup(
            familyAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familyAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(wifeAgeLabel)
                .addComponent(wifeAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(familyAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(husbandAgeLabel)
                .addComponent(husbandAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        agePanel.add(familyAgePanel, "familyCard");

        IndividualAgeLabel.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.IndividualAgeLabel.text")); // NOI18N

        individualAgeTextField.setEditable(false);
        individualAgeTextField.setColumns(4);

        javax.swing.GroupLayout individualAgePanelLayout = new javax.swing.GroupLayout(individualAgePanel);
        individualAgePanel.setLayout(individualAgePanelLayout);
        individualAgePanelLayout.setHorizontalGroup(
            individualAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(individualAgePanelLayout.createSequentialGroup()
                .addComponent(IndividualAgeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(individualAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 657, Short.MAX_VALUE))
        );
        individualAgePanelLayout.setVerticalGroup(
            individualAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(individualAgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(IndividualAgeLabel)
                .addComponent(individualAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        agePanel.add(individualAgePanel, "IndividualCard");

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
        );

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel2.add(gedcomPlacePanel, "place");
        jPanel2.add(addressPanel, "address");

        javax.swing.GroupLayout placePanelLayout = new javax.swing.GroupLayout(placePanel);
        placePanel.setLayout(placePanelLayout);
        placePanelLayout.setHorizontalGroup(
            placePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        placePanelLayout.setVerticalGroup(
            placePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placePanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        eventInformationTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.placePanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Place.png")), placePanel); // NOI18N

        sourceCitationsListPanel.setPreferredSize(null);

        javax.swing.GroupLayout sourcesPanelLayout = new javax.swing.GroupLayout(sourcesPanel);
        sourcesPanel.setLayout(sourcesPanelLayout);
        sourcesPanelLayout.setHorizontalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sourcesPanelLayout.setVerticalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.sourcesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/source.png")), sourcesPanel); // NOI18N

        javax.swing.GroupLayout galleryPanelLayout = new javax.swing.GroupLayout(galleryPanel);
        galleryPanel.setLayout(galleryPanelLayout);
        galleryPanelLayout.setHorizontalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        galleryPanelLayout.setVerticalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.galleryPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), galleryPanel); // NOI18N

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteCitationsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        eventInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        helpEditorPane.setEditable(false);
        helpEditorPane.setContentType("text/html"); // NOI18N
        helpEditorPane.setText(org.openide.util.NbBundle.getMessage(EventEditorPanel.class, "EventEditorPanel.helpEditorPane.text")); // NOI18N
        helpEditorPane.setEnabled(false);
        jScrollPane2.setViewportView(helpEditorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventInformationTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(agePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventInformationTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            CardLayout cl = (CardLayout) (jPanel2.getLayout());
            cl.show(jPanel2, "address");
        } else {
            CardLayout cl = (CardLayout) (jPanel2.getLayout());
            cl.show(jPanel2, "place");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EventTypeLabel;
    private javax.swing.JLabel IndividualAgeLabel;
    private ancestris.modules.beans.ADateBean aDateBean;
    private ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel addressPanel;
    private javax.swing.JPanel agePanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel eventCauseLabel;
    private javax.swing.JScrollPane eventCauseScrollPane;
    private javax.swing.JTextArea eventCauseTextArea;
    private javax.swing.JTextField eventIDTextField;
    private javax.swing.JLabel eventIdLabel;
    private javax.swing.JTabbedPane eventInformationTabbedPane;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JTextField eventNameTextField;
    private javax.swing.JTextField eventTypeTextField;
    private javax.swing.JPanel familyAgePanel;
    private javax.swing.JPanel galleryPanel;
    private ancestris.modules.editors.genealogyeditor.panels.GedcomPlaceEditorPanel gedcomPlacePanel;
    private javax.swing.JEditorPane helpEditorPane;
    private javax.swing.JLabel husbandAgeLabel;
    private javax.swing.JTextField husbandAgeTextField;
    private javax.swing.JPanel individualAgePanel;
    private javax.swing.JTextField individualAgeTextField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel multimediaObjectCitationsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JPanel placePanel;
    private javax.swing.JToggleButton privateRecordToggleButton;
    private ancestris.modules.editors.genealogyeditor.panels.SourceCitationsListPanel sourceCitationsListPanel;
    private javax.swing.JPanel sourcesPanel;
    private javax.swing.JLabel wifeAgeLabel;
    private javax.swing.JTextField wifeAgeTextField;
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
            Property eventType = mEvent.getProperty("TYPE", false);
            if (eventType != null) {
                eventNameTextField.setText(eventType.getValue());
            }
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

            eventCauseTextArea.setText(mEvent.getValue());
            eventCauseTextArea.getDocument().addDocumentListener(new DocumentListener() {

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
                eventCauseTextArea.setText(eventCause.getValue());
            }
            eventCauseTextArea.getDocument().addDocumentListener(new DocumentListener() {

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
                eventCauseTextArea.setText(eventCause.getValue());
            }
            eventCauseTextArea.getDocument().addDocumentListener(new DocumentListener() {

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

        if (mEventType == INDIVIDUAL_EVENT_TYPE) {
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
        } else if (mEventType == FAMILY_EVENT_TYPE) {
            PropertyAge husbandAge = (PropertyAge) mEvent.getPropertyByPath(".:HUSB:AGE");
            if (husbandAge != null) {
                husbandAgeTextField.setText(husbandAge.getValue());
            }
            /*
             * Remove modification of age property
             * Need to be better handle
             *
             * husbandAgeTextField.getDocument().addDocumentListener(new DocumentListener() {
             *
             * @Override
             * public void changedUpdate(DocumentEvent e) {
             * mHusbandAgeModified = true;
             * }
             *
             * @Override
             * public void removeUpdate(DocumentEvent e) {
             * mHusbandAgeModified = true;
             * }
             *
             * @Override
             * public void insertUpdate(DocumentEvent e) {
             * mHusbandAgeModified = true;
             * }
             * });
             *
             */

            PropertyAge wifeAge = (PropertyAge) mEvent.getPropertyByPath(".:WIFE:AGE");
            if (wifeAge != null) {
                wifeAgeTextField.setText(wifeAge.getValue());
            }
            /*
             * Remove modification of age property
             * Need to be better handle
             *
             * wifeAgeTextField.getDocument().addDocumentListener(new DocumentListener() {
             *
             * @Override
             * public void changedUpdate(DocumentEvent e) {
             * mWifeAgeModified = true;
             * }
             *
             * @Override
             * public void removeUpdate(DocumentEvent e) {
             * mWifeAgeModified = true;
             * }
             *
             * @Override
             * public void insertUpdate(DocumentEvent e) {
             * mWifeAgeModified = true;
             * }
             * });
             *
             */
        } else {
            agePanel.setVisible(false);
        }

        mPlace = (PropertyPlace) mEvent.getProperty(PropertyPlace.TAG, false);
        gedcomPlacePanel.set(mEvent, mPlace);

        mAddress = mEvent.getProperty("ADDR", false);
        addressPanel.set(mEvent, mAddress);

        if (mPlace == null && mAddress != null) {
            jCheckBox1.setSelected(true);
            CardLayout cl = (CardLayout) (jPanel2.getLayout());
            cl.show(jPanel2, "address");
        }

        Property[] sourcesList = mEvent.getProperties("SOUR");
        sourceCitationsListPanel.set(mEvent, Arrays.asList(sourcesList));

        noteCitationsListPanel.set(mEvent, Arrays.asList(mEvent.getProperties("NOTE")));

        multimediaObjectCitationsListPanel.set(mEvent, Arrays.asList(mEvent.getProperties("OBJE")));
    }

    public void commit() {
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
                mEvent.setValue(eventCauseTextArea.getText());
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
                String causeText = eventCauseTextArea.getText();
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
                String causeText = eventCauseTextArea.getText();
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
        try {
            aDateBean.commit();
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }

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

        if (mEventType == INDIVIDUAL_EVENT_TYPE) {
            if (mIndividualAgeModified) {
                PropertyAge age = (PropertyAge) mEvent.getProperty("AGE", false);
                if (age != null) {
                    age.setValue(individualAgeTextField.getText() + " y");
                } else {
                    mEvent.addProperty("AGE", individualAgeTextField.getText() + " y");
                }
            }
        } else if (mEventType == FAMILY_EVENT_TYPE) {
            if (mHusbandAgeModified) {
                PropertyAge husbandAge = (PropertyAge) mEvent.getPropertyByPath(".:HUSB:AGE");
                if (husbandAge != null) {
                    husbandAge.setValue(husbandAgeTextField.getText() + " y");
                } else {
                    Property addProperty = mEvent.addProperty("HUSB", "");
                    addProperty.addProperty("AGE", husbandAgeTextField.getText() + " y");
                }
            }
            if (mWifeAgeModified) {
                PropertyAge wifeAge = (PropertyAge) mEvent.getPropertyByPath(".:WIFE:AGE");
                if (wifeAge != null) {
                    wifeAge.setValue(wifeAgeTextField.getText() + " y");
                } else {
                    Property addProperty = mEvent.addProperty("WIFE", "");
                    addProperty.addProperty("AGE", wifeAgeTextField.getText() + " y");
                }
            }
        }
        gedcomPlacePanel.commit();
        addressPanel.commit();
    }
}