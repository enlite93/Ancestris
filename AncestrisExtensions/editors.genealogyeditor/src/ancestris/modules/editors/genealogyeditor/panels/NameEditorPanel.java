package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.NameTypeComboBoxModel;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dominique
 */
public class NameEditorPanel extends javax.swing.JPanel {

    private NameTypeComboBoxModel nameTypeComboBoxModelModel = new NameTypeComboBoxModel();
    private Indi root;
    private PropertyName name;
    private boolean nameModified = false;
    private boolean nameTypeModified = false;
    private boolean familyNamePrefixModified = false;
    private boolean familyNameModified = false;
    private boolean firstNamePrefixModified = false;
    private boolean firstNameSuffixModified = false;
    private boolean firstNameModified = false;
    private boolean nicknameModified = false;
    private final static Logger logger = Logger.getLogger(NameEditorPanel.class.getName(), null);

    /**
     * Creates new form NameEditorPanel
     */
    public NameEditorPanel() {
        initComponents();
        jCheckBox1.setSelected(false);
        firstNamePrefixLabel.setVisible(false);
        firstNamePrefixTextField.setVisible(false);
        familyNamePrefixLabel.setVisible(false);
        familyNamePrefixTextField.setVisible(false);
        firstNameSuffixLabel.setVisible(false);
        firstNameSuffixTextField.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        nameTypeLabel = new javax.swing.JLabel();
        nameTypeComboBox = new javax.swing.JComboBox<String>();
        firstNamePrefixLabel = new javax.swing.JLabel();
        firstNamePrefixTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        familyNamePrefixLabel = new javax.swing.JLabel();
        familyNamePrefixTextField = new javax.swing.JTextField();
        familyNameTextField = new javax.swing.JTextField();
        firstNameSuffixTextField = new javax.swing.JTextField();
        nicknameLabel = new javax.swing.JLabel();
        nicknameTextField = new javax.swing.JTextField();
        firstnameLabel = new javax.swing.JLabel();
        familyNameLabel = new javax.swing.JLabel();
        firstNameSuffixLabel = new javax.swing.JLabel();

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(NameEditorPanel.class, "NameEditorPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        nameTypeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nameTypeLabel.text"), new Object[] {})); // NOI18N

        nameTypeComboBox.setEditable(true);
        nameTypeComboBox.setModel(nameTypeComboBoxModelModel);
        nameTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTypeComboBoxActionPerformed(evt);
            }
        });

        firstNamePrefixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNamePrefixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNamePrefixLabel.text"), new Object[] {})); // NOI18N

        firstNamePrefixTextField.setColumns(8);
        firstNamePrefixTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        firstNamePrefixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNamePrefixTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNamePrefixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
                nameModified = true;
            }
        });

        firstNameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNameModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNameModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNameModified = true;
                nameModified = true;
            }
        });

        familyNamePrefixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        familyNamePrefixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNamePrefixLabel.text"), new Object[] {})); // NOI18N

        familyNamePrefixTextField.setColumns(8);
        familyNamePrefixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNamePrefixTextField.toolTipText"), new Object[] {})); // NOI18N
        familyNamePrefixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
                nameModified = true;
            }
        });

        familyNameTextField.setColumns(16);
        familyNameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNameTextField.toolTipText"), new Object[] {})); // NOI18N
        familyNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                familyNameModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                familyNameModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                familyNameModified = true;
                nameModified = true;
            }
        });

        firstNameSuffixTextField.setColumns(8);
        firstNameSuffixTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixTextField.text"), new Object[] {})); // NOI18N
        firstNameSuffixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNameSuffixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
                nameModified = true;
            }
        });

        nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nicknameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nicknameLabel.text"), new Object[] {})); // NOI18N

        nicknameTextField.setColumns(8);
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        nicknameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nicknameTextField.toolTipText"), new Object[] {})); // NOI18N
        nicknameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                nicknameModified = true;
                nameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nicknameModified = true;
                nameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                nicknameModified = true;
                nameModified = true;
            }
        });

        firstnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstnameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstnameLabel.text"), new Object[] {})); // NOI18N

        familyNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        familyNameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNameLabel.text"), new Object[] {})); // NOI18N

        firstNameSuffixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameSuffixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixLabel.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(familyNamePrefixLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstNamePrefixLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstNamePrefixTextField)
                            .addComponent(familyNamePrefixTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nicknameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(familyNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(firstnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameTextField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nicknameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(familyNameTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstNameSuffixLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstNameSuffixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTypeLabel)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNamePrefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNamePrefixLabel)
                    .addComponent(firstnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(familyNamePrefixLabel)
                    .addComponent(familyNamePrefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(familyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameSuffixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(familyNameLabel)
                    .addComponent(firstNameSuffixLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nicknameLabel)
                    .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTypeComboBoxActionPerformed
        nameTypeModified = true;
        nameModified = true;
    }//GEN-LAST:event_nameTypeComboBoxActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            firstNamePrefixLabel.setVisible(true);
            firstNamePrefixTextField.setVisible(true);
            familyNamePrefixLabel.setVisible(true);
            familyNamePrefixTextField.setVisible(true);
            firstNameSuffixLabel.setVisible(true);
            firstNameSuffixTextField.setVisible(true);
        } else {
            firstNamePrefixLabel.setVisible(false);
            firstNamePrefixTextField.setVisible(false);
            familyNamePrefixLabel.setVisible(false);
            familyNamePrefixTextField.setVisible(false);
            firstNameSuffixLabel.setVisible(false);
            firstNameSuffixTextField.setVisible(false);
        }
        revalidate();
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel familyNameLabel;
    private javax.swing.JLabel familyNamePrefixLabel;
    private javax.swing.JTextField familyNamePrefixTextField;
    private javax.swing.JTextField familyNameTextField;
    private javax.swing.JLabel firstNamePrefixLabel;
    private javax.swing.JTextField firstNamePrefixTextField;
    private javax.swing.JLabel firstNameSuffixLabel;
    private javax.swing.JTextField firstNameSuffixTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> nameTypeComboBox;
    private javax.swing.JLabel nameTypeLabel;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JTextField nicknameTextField;
    // End of variables declaration//GEN-END:variables

    public void set(Indi root, PropertyName name) {
        this.root = root;
        this.name = name;

        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();

        for (Indi indi : root.getGedcom().getIndis()) {
            firstNames.add(indi.getFirstName());
            lastNames.add(indi.getLastName());
        }

        AutoCompleteDecorator.decorate(firstNameTextField, firstNames, false);
        AutoCompleteDecorator.decorate(familyNameTextField, lastNames, false);

        String version = root.getGedcom().getGrammar().getVersion();
        if (version.equals("5.5")) {
            nameTypeLabel.setVisible(false);
            nameTypeComboBox.setVisible(false);
        }

        if (name != null) {
            /*
             * Indicates the name type, for example the name issued or assumed
             * as an immigrant.
             */
            Property nameType = name.getProperty("TYPE");
            if (nameType != null) {
                nameTypeComboBox.setSelectedItem(nameType.getValue());
            } else {
                nameTypeComboBox.setSelectedIndex(1);
            }
            nameTypeModified = false;

            /*
             * NPFX Non indexing name piece that appears preceding the given
             * name and surname parts. Different name prefix parts are separated
             * by a comma.
             *
             */
            Property firstnamePrefix = name.getProperty("NPFX");
            firstNamePrefixTextField.setText(firstnamePrefix != null ? firstnamePrefix.getValue() : "");
            firstNamePrefixModified = false;

            /*
             * GIVN Given name or earned name. Different given names are
             * separated by a comma.
             */
            Property givenName = name.getProperty("GIVN");
            firstNameTextField.setText(givenName != null ? givenName.getValue() : name.getFirstName());
            firstNameModified = false;

            /*
             * NSFX Non-indexing name piece that appears after the given name
             * and surname parts. Different name suffix parts are separated by a
             * comma.
             */
            Property firstNameSuffix = name.getProperty("NSFX");
            firstNameSuffixTextField.setText(firstNameSuffix != null ? firstNameSuffix.getValue() : "");
            firstNameSuffixModified = false;

            /*
             * SPFX surname prefix or article used in a family name. Different
             * surname articles are separated by a comma, for example in the
             * name "de la Cruz", this value would be "de, la".
             */
            Property familyNamePrefix = name.getProperty("SPFX");
            familyNamePrefixTextField.setText(familyNamePrefix != null ? familyNamePrefix.getValue() : "");
            familyNamePrefixModified = false;

            /*
             * SURN Surname or family name. Different surnames are separated by
             * a comma.
             */
            Property familyName = name.getProperty("SURN");
            if (familyName != null) {
                familyNameTextField.setText(familyName.getValue());
            } else {
                familyNameTextField.setText(name.getLastName());
            }
            familyNameModified = false;

            /*
             * NICK A descriptive or familiar name used in connection with one's
             * proper name.
             */
            Property nickName = name.getProperty("NICK");
            nicknameTextField.setText(nickName != null ? nickName.getValue() : "");
            nicknameModified = false;

            if (firstnamePrefix != null || firstNameSuffix != null || familyNamePrefix != null) {
                jCheckBox1.setSelected(true);
                firstNamePrefixLabel.setVisible(true);
                firstNamePrefixTextField.setVisible(true);
                familyNamePrefixLabel.setVisible(true);
                familyNamePrefixTextField.setVisible(true);
                firstNameSuffixLabel.setVisible(true);
                firstNameSuffixTextField.setVisible(true);
            } else {
                jCheckBox1.setSelected(false);
                firstNamePrefixLabel.setVisible(false);
                firstNamePrefixTextField.setVisible(false);
                familyNamePrefixLabel.setVisible(false);
                familyNamePrefixTextField.setVisible(false);
                firstNameSuffixLabel.setVisible(false);
                firstNameSuffixTextField.setVisible(false);
            }
        } else {
            nameTypeComboBox.setSelectedIndex(1);
            nameTypeModified = false;
            jCheckBox1.setSelected(false);
            firstNamePrefixLabel.setVisible(false);
            firstNamePrefixTextField.setVisible(false);
            familyNamePrefixLabel.setVisible(false);
            familyNamePrefixTextField.setVisible(false);
            firstNameSuffixLabel.setVisible(false);
            firstNameSuffixTextField.setVisible(false);
        }
        revalidate();
        nameModified = false;
    }

    public void commit() {
        final String version = root.getGedcom().getGrammar().getVersion();

        if (nameModified) {
            logger.log(Level.INFO, "Commiting ...");
            try {
                root.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        if (name == null) {
                            logger.log(Level.INFO, "Add property NAME");

                            name = (PropertyName) root.addProperty("NAME", "");
                        }

                        if (version.equals("5.5.1") && nameTypeModified == true) {

                            Property nameType = name.getProperty("TYPE");
                            if (nameType == null) {
                                logger.log(Level.INFO, "Add property TYPE");

                                name.addProperty("TYPE", nameTypeComboBox.getSelectedItem().toString());
                            } else {
                                logger.log(Level.INFO, "Update property TYPE");

                                nameType.setValue(nameTypeComboBox.getSelectedItem().toString());
                            }
                        }

                        /*
                         * NPFX Non indexing name piece that appears preceding the
                         * given name and surname parts. Different name prefix parts
                         * are separated by a comma.
                         */
                        if (firstNamePrefixModified == true) {
                            Property firstnamePrefix = name.getProperty("NPFX");
                            if (firstnamePrefix == null) {
                                logger.log(Level.INFO, "Add property NPFX");

                                name.addProperty("NPFX", firstNamePrefixTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property NPFX");
                                firstnamePrefix.setValue(firstNamePrefixTextField.getText().trim());
                            }
                        }

                        /*
                         * GIVN Given name or earned name. Different given names are
                         * separated by a comma.
                         */
                        if (firstNameModified == true) {
                            Property givenName = name.getProperty("GIVN");
                            if (givenName == null) {
                                // Suppressed as an IndexOutOfBoundsException is thrown on undo
                                // logger.log(Level.INFO, "Add property GIVN");
                                // name.addProperty("GIVN", firstNameTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property GIVN");
                                givenName.setValue(firstNameTextField.getText().trim());
                            }
                        }

                        if (firstNameSuffixModified == true) {
                            Property firstNameSuffix = name.getProperty("NSFX");
                            if (firstNameSuffix == null) {
                                logger.log(Level.INFO, "Add property NSFX");
                                name.addProperty("NSFX", firstNameSuffixTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property NSFX");
                                firstNameSuffix.setValue(firstNameSuffixTextField.getText().trim());
                            }
                        }

                        /*
                         * SPFX surname prefix or article used in a family name.
                         * Different surname articles are separated by a comma, for
                         * example in the name "de la Cruz", this value would be
                         * "de, la".
                         */
                        if (familyNamePrefixModified == true) {
                            Property familyNamePrefix = name.getProperty("SPFX");
                            if (familyNamePrefix == null) {
                                logger.log(Level.INFO, "Add property SPFX");
                                name.addProperty("SPFX", familyNamePrefixTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property SPFX");
                                familyNamePrefix.setValue(familyNamePrefixTextField.getText().trim());
                            }
                        }

                        /*
                         * SURN Surname or family name. Different surnames are
                         * separated by a comma.
                         */
                        if (familyNameModified == true) {
                            Property familyName = name.getProperty("SURN");
                            if (familyName == null) {
                                // Suppressed as an IndexOutOfBoundsException is thrown on undo
                                // logger.log(Level.INFO, "Add property SURN");
                                // name.addProperty("SURN", familyNameTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property SURN");
                                familyName.setValue(familyNameTextField.getText().trim());
                            }
                        }

                        if (nicknameModified == true) {
                            Property nickname = name.getProperty("NICK");
                            if (nickname == null) {
                                logger.log(Level.INFO, "Update property NICK");
                                name.addProperty("NICK", nicknameTextField.getText().trim());
                            } else {
                                logger.log(Level.INFO, "Update property NICK");
                                nickname.setValue(nicknameTextField.getText().trim());
                            }
                        }
                        // ... store changed value
                        name.setName(
                                firstNamePrefixTextField.getText().trim(),
                                firstNameTextField.getText().trim(),
                                familyNamePrefixTextField.getText().trim(),
                                familyNameTextField.getText().trim(),
                                firstNameSuffixTextField.getText().trim(),
                                false);
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
            logger.log(Level.INFO, "... finished");
        }
    }

    PropertyName get() {
        return name;
    }
}
