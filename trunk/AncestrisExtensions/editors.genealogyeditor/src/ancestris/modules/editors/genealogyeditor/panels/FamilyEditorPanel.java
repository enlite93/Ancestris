package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class FamilyEditorPanel extends javax.swing.JPanel {

    private Fam family;

    /**
     * Creates new form FamilyEditorPanel
     */
    public FamilyEditorPanel() {
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

        fatherPanel = new javax.swing.JPanel();
        fatherToolBar = new javax.swing.JToolBar();
        addFatherButton = new javax.swing.JButton();
        editFatherButton = new javax.swing.JButton();
        deleteFatherButton = new javax.swing.JButton();
        ListFathersButton = new javax.swing.JButton();
        fatherNameLabel = new javax.swing.JLabel();
        fatherNameTextField = new javax.swing.JTextField();
        fatherBirthDateLabel = new javax.swing.JLabel();
        fatherBirthDateTextField = new javax.swing.JTextField();
        fatherDeathDateLabel = new javax.swing.JLabel();
        fatherDeathDateTextField = new javax.swing.JTextField();
        motherPanel = new javax.swing.JPanel();
        motherToolBar = new javax.swing.JToolBar();
        addMotherButton = new javax.swing.JButton();
        editMotherButton = new javax.swing.JButton();
        deleteMotherButton = new javax.swing.JButton();
        ListMothersButton = new javax.swing.JButton();
        motherLabel = new javax.swing.JLabel();
        motherNameTextField = new javax.swing.JTextField();
        motherBirthDateLabel = new javax.swing.JLabel();
        motherBirthDateTextField = new javax.swing.JTextField();
        motherDeathDateLabel = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        motherDeathDateTextField = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        childrensPanel = new javax.swing.JPanel();
        childrensListPanel = new ancestris.modules.editors.genealogyeditor.panels.IndividualsListPanel();
        eventsPanel = new javax.swing.JPanel();
        eventsListPanel = new ancestris.modules.editors.genealogyeditor.panels.EventsListPanel();
        sourcesPanel = new javax.swing.JPanel();
        sourcesListPanel = new ancestris.modules.editors.genealogyeditor.panels.SourcesListPanel();
        notesPanel = new javax.swing.JPanel();
        notesListPanel = new ancestris.modules.editors.genealogyeditor.panels.NotesListPanel();
        galleryPanel = new javax.swing.JPanel();
        multimediaObjectsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectsListPanel();
        jPanel1 = new javax.swing.JPanel();
        familyIDTextField = new javax.swing.JTextField();
        familyIDLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();

        fatherPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherPanel.border.title"), new Object[] {}))); // NOI18N

        fatherToolBar.setFloatable(false);
        fatherToolBar.setRollover(true);

        addFatherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addFatherButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.addFatherButton.toolTipText"), new Object[] {})); // NOI18N
        addFatherButton.setFocusable(false);
        addFatherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFatherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFatherButtonActionPerformed(evt);
            }
        });
        fatherToolBar.add(addFatherButton);

        editFatherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editFatherButton.setFocusable(false);
        editFatherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editFatherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editFatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFatherButtonActionPerformed(evt);
            }
        });
        fatherToolBar.add(editFatherButton);

        deleteFatherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteFatherButton.setFocusable(false);
        deleteFatherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteFatherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteFatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFatherButtonActionPerformed(evt);
            }
        });
        fatherToolBar.add(deleteFatherButton);

        ListFathersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Individuals.png"))); // NOI18N
        ListFathersButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.ListFathersButton.text"), new Object[] {})); // NOI18N
        ListFathersButton.setFocusable(false);
        ListFathersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ListFathersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ListFathersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListFathersButtonActionPerformed(evt);
            }
        });
        fatherToolBar.add(ListFathersButton);

        fatherNameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherNameLabel.text"), new Object[] {})); // NOI18N

        fatherNameTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherNameTextField.text"), new Object[] {})); // NOI18N

        fatherBirthDateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherBirthDateLabel.text"), new Object[] {})); // NOI18N

        fatherBirthDateTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherBirthDateTextField.text"), new Object[] {})); // NOI18N

        fatherDeathDateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherDeathDateLabel.text"), new Object[] {})); // NOI18N

        fatherDeathDateTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.fatherDeathDateTextField.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout fatherPanelLayout = new javax.swing.GroupLayout(fatherPanel);
        fatherPanel.setLayout(fatherPanelLayout);
        fatherPanelLayout.setHorizontalGroup(
            fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fatherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fatherBirthDateLabel)
                    .addComponent(fatherNameLabel)
                    .addComponent(fatherDeathDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fatherNameTextField)
                    .addComponent(fatherBirthDateTextField)
                    .addComponent(fatherDeathDateTextField))
                .addContainerGap())
            .addComponent(fatherToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fatherPanelLayout.setVerticalGroup(
            fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fatherPanelLayout.createSequentialGroup()
                .addComponent(fatherToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fatherNameLabel)
                    .addComponent(fatherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fatherBirthDateLabel)
                    .addComponent(fatherBirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fatherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fatherDeathDateLabel)
                    .addComponent(fatherDeathDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        motherPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherPanel.border.title"), new Object[] {}))); // NOI18N

        motherToolBar.setFloatable(false);
        motherToolBar.setRollover(true);

        addMotherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addMotherButton.setFocusable(false);
        addMotherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addMotherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addMotherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMotherButtonActionPerformed(evt);
            }
        });
        motherToolBar.add(addMotherButton);

        editMotherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editMotherButton.setFocusable(false);
        editMotherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editMotherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMotherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMotherButtonActionPerformed(evt);
            }
        });
        motherToolBar.add(editMotherButton);

        deleteMotherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteMotherButton.setFocusable(false);
        deleteMotherButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteMotherButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteMotherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMotherButtonActionPerformed(evt);
            }
        });
        motherToolBar.add(deleteMotherButton);

        ListMothersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Individuals.png"))); // NOI18N
        ListMothersButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.ListMothersButton.text"), new Object[] {})); // NOI18N
        ListMothersButton.setFocusable(false);
        ListMothersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ListMothersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ListMothersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMothersButtonActionPerformed(evt);
            }
        });
        motherToolBar.add(ListMothersButton);

        motherLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherLabel.text"), new Object[] {})); // NOI18N

        motherNameTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherNameTextField.text"), new Object[] {})); // NOI18N

        motherBirthDateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherBirthDateLabel.text"), new Object[] {})); // NOI18N

        motherBirthDateTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherBirthDateTextField.text"), new Object[] {})); // NOI18N

        motherDeathDateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherDeathDateLabel.text"), new Object[] {})); // NOI18N

        jTextField6.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.jTextField6.text"), new Object[] {})); // NOI18N

        motherDeathDateTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.motherDeathDateTextField.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout motherPanelLayout = new javax.swing.GroupLayout(motherPanel);
        motherPanel.setLayout(motherPanelLayout);
        motherPanelLayout.setHorizontalGroup(
            motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(motherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(motherPanelLayout.createSequentialGroup()
                        .addComponent(motherBirthDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(motherBirthDateTextField)
                        .addContainerGap())
                    .addGroup(motherPanelLayout.createSequentialGroup()
                        .addGroup(motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(motherPanelLayout.createSequentialGroup()
                                .addComponent(motherLabel)
                                .addGap(34, 34, 34)
                                .addComponent(motherNameTextField))
                            .addGroup(motherPanelLayout.createSequentialGroup()
                                .addComponent(motherDeathDateLabel)
                                .addGap(5, 5, 5)
                                .addComponent(motherDeathDateTextField)))
                        .addGap(0, 12, 12))))
            .addComponent(motherToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        motherPanelLayout.setVerticalGroup(
            motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(motherPanelLayout.createSequentialGroup()
                .addComponent(motherToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motherLabel)
                    .addComponent(motherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motherBirthDateLabel)
                    .addComponent(motherBirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(motherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motherDeathDateLabel)
                    .addComponent(motherDeathDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout childrensPanelLayout = new javax.swing.GroupLayout(childrensPanel);
        childrensPanel.setLayout(childrensPanelLayout);
        childrensPanelLayout.setHorizontalGroup(
            childrensPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(childrensListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        childrensPanelLayout.setVerticalGroup(
            childrensPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(childrensListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.childrensPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Child.png")), childrensPanel); // NOI18N

        javax.swing.GroupLayout eventsPanelLayout = new javax.swing.GroupLayout(eventsPanel);
        eventsPanel.setLayout(eventsPanelLayout);
        eventsPanelLayout.setHorizontalGroup(
            eventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        eventsPanelLayout.setVerticalGroup(
            eventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.eventsPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Event.png")), eventsPanel); // NOI18N

        javax.swing.GroupLayout sourcesPanelLayout = new javax.swing.GroupLayout(sourcesPanel);
        sourcesPanel.setLayout(sourcesPanelLayout);
        sourcesPanelLayout.setHorizontalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourcesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        sourcesPanelLayout.setVerticalGroup(
            sourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourcesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sources", new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Source.png")), sourcesPanel); // NOI18N

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.notesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), notesPanel); // NOI18N

        javax.swing.GroupLayout galleryPanelLayout = new javax.swing.GroupLayout(galleryPanel);
        galleryPanel.setLayout(galleryPanelLayout);
        galleryPanelLayout.setHorizontalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        galleryPanelLayout.setVerticalGroup(
            galleryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(multimediaObjectsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.galleryPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Media.png")), galleryPanel); // NOI18N

        familyIDTextField.setColumns(8);
        familyIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.familyIDTextField.text"), new Object[] {})); // NOI18N

        familyIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.familyIDLabel.text"), new Object[] {})); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("FamilyEditorPanel.jLabel7.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(familyIDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(familyIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(familyIDLabel)
                .addComponent(familyIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(fatherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(motherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(motherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fatherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addFatherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFatherButtonActionPerformed
        IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
        individualEditorPanel.set(new Indi());

        DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                individualEditorPanel);
        individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

        if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_addFatherButtonActionPerformed

    private void editFatherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFatherButtonActionPerformed
        IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
        individualEditorPanel.set(family.getHusband());

        DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                individualEditorPanel);
        individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

        if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_editFatherButtonActionPerformed

    private void deleteFatherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFatherButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteFatherButtonActionPerformed

    private void deleteMotherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMotherButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMotherButtonActionPerformed

    private void editMotherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMotherButtonActionPerformed
        IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
        individualEditorPanel.set(family.getWife());

        DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                individualEditorPanel);
        individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

        if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_editMotherButtonActionPerformed

    private void addMotherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMotherButtonActionPerformed
        IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
        individualEditorPanel.set(new Indi());

        DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                individualEditorPanel);
        individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

        if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_addMotherButtonActionPerformed

    private void ListFathersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListFathersButtonActionPerformed
        IndividualsListPanel individualsListPanel = new IndividualsListPanel();
        List<Indi> individualsList = new ArrayList<Indi>();
        for (Indi individual : family.getGedcom().getIndis()) {
            if (individual.getSex() == PropertySex.MALE || individual.getSex() == PropertySex.UNKNOWN) {
                individualsList.add(individual);
            }
        }
        individualsListPanel.setIndividualsList(family, individualsList);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualsListPanel.class, "IndividualEditorPanel.title"),
                individualsListPanel);
        individualsListDialog.setDialogId(IndividualsListPanel.class.getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_ListFathersButtonActionPerformed

    private void ListMothersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMothersButtonActionPerformed
        IndividualsListPanel individualsListPanel = new IndividualsListPanel();
        List<Indi> individualsList = new ArrayList<Indi>();

        for (Indi individual : family.getGedcom().getIndis()) {
            if (individual.getSex() == PropertySex.FEMALE || individual.getSex() == PropertySex.UNKNOWN) {
                individualsList.add(individual);
            }
        }
        individualsListPanel.setIndividualsList(family, individualsList);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualsListPanel.class, "IndividualEditorPanel.title"),
                individualsListPanel);
        individualsListDialog.setDialogId(IndividualsListPanel.class.getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
        }
    }//GEN-LAST:event_ListMothersButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ListFathersButton;
    private javax.swing.JButton ListMothersButton;
    private javax.swing.JButton addFatherButton;
    private javax.swing.JButton addMotherButton;
    private ancestris.modules.editors.genealogyeditor.panels.IndividualsListPanel childrensListPanel;
    private javax.swing.JPanel childrensPanel;
    private javax.swing.JButton deleteFatherButton;
    private javax.swing.JButton deleteMotherButton;
    private javax.swing.JButton editFatherButton;
    private javax.swing.JButton editMotherButton;
    private ancestris.modules.editors.genealogyeditor.panels.EventsListPanel eventsListPanel;
    private javax.swing.JPanel eventsPanel;
    private javax.swing.JLabel familyIDLabel;
    private javax.swing.JTextField familyIDTextField;
    private javax.swing.JLabel fatherBirthDateLabel;
    private javax.swing.JTextField fatherBirthDateTextField;
    private javax.swing.JLabel fatherDeathDateLabel;
    private javax.swing.JTextField fatherDeathDateTextField;
    private javax.swing.JLabel fatherNameLabel;
    private javax.swing.JTextField fatherNameTextField;
    private javax.swing.JPanel fatherPanel;
    private javax.swing.JToolBar fatherToolBar;
    private javax.swing.JPanel galleryPanel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel motherBirthDateLabel;
    private javax.swing.JTextField motherBirthDateTextField;
    private javax.swing.JLabel motherDeathDateLabel;
    private javax.swing.JTextField motherDeathDateTextField;
    private javax.swing.JLabel motherLabel;
    private javax.swing.JTextField motherNameTextField;
    private javax.swing.JPanel motherPanel;
    private javax.swing.JToolBar motherToolBar;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectsListPanel multimediaObjectsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NotesListPanel notesListPanel;
    private javax.swing.JPanel notesPanel;
    private ancestris.modules.editors.genealogyeditor.panels.SourcesListPanel sourcesListPanel;
    private javax.swing.JPanel sourcesPanel;
    // End of variables declaration//GEN-END:variables


    /**
     * @param family the family to set
     */
    public void set(Fam family) {
        this.family = family;
        update();
    }

    private void update() {
        familyIDTextField.setText(family.getId());

        // Father Panel
        Indi husband = family.getHusband();
        if (husband != null) {
            addFatherButton.setVisible(false);
        } else {
            editFatherButton.setVisible(false);
        }
        fatherNameTextField.setText(husband.getName());
        fatherBirthDateTextField.setText(husband.getBirthAsString());
        fatherDeathDateTextField.setText(husband.getDeathAsString());

        // Mother Panel
        Indi wife = family.getWife();
        if (wife != null) {
            addMotherButton.setVisible(false);
        } else {
            editMotherButton.setVisible(false);
        }
        motherNameTextField.setText(wife.getName());
        motherBirthDateTextField.setText(wife.getBirthAsString());
        motherDeathDateTextField.setText(wife.getDeathAsString());

        childrensListPanel.setIndividualsList(wife, Arrays.asList(family.getChildren()));

        List<PropertyEvent> eventsList = family.getProperties(PropertyEvent.class);
        eventsListPanel.setEventsList(family, eventsList);

        List<Source> sourcesList = new ArrayList<Source>();
        for (PropertySource sourceRef : family.getProperties(PropertySource.class)) {
            sourcesList.add((Source) sourceRef.getTargetEntity());
        }
        sourcesListPanel.set(family, sourcesList);


        List<Note> notesList = new ArrayList<Note>();
        for (PropertyNote noteRef : family.getProperties(PropertyNote.class)) {
            notesList.add((Note) noteRef.getTargetEntity());
        }
        notesListPanel.setNotesList(family, notesList);

        List<Media> mediasList = new ArrayList<Media>();
        for (PropertyMedia mediaRef : family.getProperties(PropertyMedia.class)) {
            mediasList.add((Media) mediaRef.getTargetEntity());
        }
        multimediaObjectsListPanel.set(family, mediasList);
    }

    public void commit() {
        try {
            family.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    eventsListPanel.commit();
                    sourcesListPanel.commit();
                    notesListPanel.commit();
                    multimediaObjectsListPanel.commit();
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
