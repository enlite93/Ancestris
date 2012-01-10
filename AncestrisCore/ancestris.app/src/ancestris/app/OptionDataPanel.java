/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestris.app;

import ancestris.core.beans.ConfirmChangeWidget;
import genj.util.AncestrisPreferences;
import genj.util.Registry;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.openide.awt.StatusDisplayer;

final class OptionDataPanel extends javax.swing.JPanel {

    private final OptionDataOptionsPanelController controller;
    // Values
    String[] encodings = new String[]{"ANSEL", "Unicode", "ASCII", "Latin1", "ANSI", "UTF-8"};

    OptionDataPanel(OptionDataOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jtSubmitterName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbCreateSpouse = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jCheckBox18 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox(encodings);
        jCheckBox19 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        placeFormat = new ancestris.modules.beans.APlaceFormatBean();
        cbAutoCommit = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        cbAddNameSubtags = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jtGivenTag = new javax.swing.JTextField();
        cbSpaceIsSep = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();

        jtSubmitterName.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jtSubmitterName.text")); // NOI18N
        jtSubmitterName.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jtSubmitterName.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel3.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel4.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel5.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField3.toolTipText")); // NOI18N

        jTextField4.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField4.text")); // NOI18N
        jTextField4.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField4.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel6.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField5.text")); // NOI18N
        jTextField5.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField5.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel7.text")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField6.text")); // NOI18N
        jTextField6.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField6.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel8.text")); // NOI18N

        jTextField7.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField7.text")); // NOI18N
        jTextField7.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jTextField7.toolTipText")); // NOI18N

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel22.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbCreateSpouse, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbCreateSpouse.text_1")); // NOI18N
        cbCreateSpouse.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbCreateSpouse.toolTipText")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel20.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox18, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox18.text")); // NOI18N
        jCheckBox18.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox18.toolTipText")); // NOI18N

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jLabel21.text")); // NOI18N

        jComboBox1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jComboBox1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox19, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox19.text")); // NOI18N
        jCheckBox19.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox19.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jCheckBox18))
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox19)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox18)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(cbAutoCommit, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbAutoCommit.text")); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbAddNameSubtags, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbAddNameSubtags.text")); // NOI18N
        cbAddNameSubtags.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbAddNameSubtags.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox1.toolTipText")); // NOI18N

        jtGivenTag.setText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jtGivenTag.text")); // NOI18N
        jtGivenTag.setMinimumSize(new java.awt.Dimension(64, 23));

        org.openide.awt.Mnemonics.setLocalizedText(cbSpaceIsSep, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.cbSpaceIsSep.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox3, org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionDataPanel.jCheckBox3.text")); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(cbAddNameSubtags)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtGivenTag, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbSpaceIsSep)
                    .addComponent(jCheckBox2))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAddNameSubtags)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jtGivenTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSpaceIsSep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jLabel22))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAutoCommit))
                            .addComponent(cbCreateSpouse)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
                                    .addComponent(jtSubmitterName, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jtSubmitterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbCreateSpouse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAutoCommit)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        jtGivenTag.setEnabled(jCheckBox3.isSelected());
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    void load() {
        AncestrisPreferences gedcomPrefs = Registry.get(genj.gedcom.Options.class);
        genj.gedcom.Options gedcomOptions = genj.gedcom.Options.getInstance();

        setSubmName(gedcomPrefs.get("submName", ""));
        setSubmCity(gedcomPrefs.get("submCity", ""));
        setSubmPhone(gedcomPrefs.get("submPhone", ""));
        setSubmPostCode(gedcomPrefs.get("submPostCode", ""));
        setSubmEmail(gedcomPrefs.get("submEmail", ""));
        setSubmCountry(gedcomPrefs.get("submCountry", ""));
        setSubmWeb(gedcomPrefs.get("submWeb", ""));
        jCheckBox1.setSelected(gedcomOptions.isUpperCaseNames());
        setNamesSpouse(gedcomPrefs.get("setWifeLastname", ""));
        cbCreateSpouse.setSelected(genj.gedcom.Options.getInstance().getCreateSpouse());
        placeFormat.setJurisdictions(genj.gedcom.Options.getInstance().getPlaceFormat());
        placeFormat.setShowJuridcitions(genj.gedcom.Options.getInstance().getShowJuridictions());
        placeFormat.setSortOrder(genj.gedcom.Options.getInstance().getPlaceSortOrder());
        placeFormat.setDisplayFormat(genj.gedcom.Options.getInstance().getPlaceDisplayFormat());

        //FIXME:        cbSpaces.setSelected(genj.gedcom.Options.getInstance().isUseSpacedPlaces)setAddressSpaces(gedcomPrefs.get("isUseSpacedPlaces", ""));
        setIDFilling(gedcomPrefs.get("isFillGapsInIDs", ""));
        setEncoding(gedcomPrefs.get("defaultEncoding", ""));
        jCheckBox19.setSelected(ancestris.app.Options.isWriteBOM());
        cbAutoCommit.setSelected(ConfirmChangeWidget.getAutoCommit());
        cbAddNameSubtags.setSelected(genj.gedcom.Options.getInstance().getAddNameSubtags());
        jtGivenTag.setText(gedcomOptions.getGivenTag());
        jCheckBox3.setSelected(!gedcomOptions.getGivenTag().isEmpty());
        jCheckBox3ActionPerformed(new java.awt.event.ActionEvent(this,0,null));
        cbSpaceIsSep.setSelected(gedcomOptions.spaceIsSeparator());
    }

    void store() {
        AncestrisPreferences gedcomPrefs = Registry.get(genj.gedcom.Options.class);
        genj.gedcom.Options gedcomOptions = genj.gedcom.Options.getInstance();

        gedcomPrefs.put("submName", getSubmName());
        gedcomPrefs.put("submCity", getSubmCity());
        gedcomPrefs.put("submPhone", getSubmPhone());
        gedcomPrefs.put("submPostCode", getSubmPostCode());
        gedcomPrefs.put("submEmail", getSubmEmail());
        gedcomPrefs.put("submCountry", getSubmCountry());
        gedcomPrefs.put("submWeb", getSubmWeb());
        gedcomOptions.setUpperCaseNames(jCheckBox1.isSelected());
        gedcomPrefs.put("setWifeLastname", getNamesSpouse());

        gedcomOptions.setCreateSpouse(cbCreateSpouse.isSelected());
        gedcomOptions.setPlaceFormat(placeFormat.getJurisdictions());
        gedcomOptions.setShowJuridictions(placeFormat.getShowJuridictions());
        gedcomOptions.setPlaceSortOrder(placeFormat.getSortOrder());
        gedcomOptions.setPlaceDisplayFormat(placeFormat.getDisplayFormat());

//FIXME:        gedcomPrefs.put("isUseSpacedPlaces", getAddressSpaces());
        gedcomPrefs.put("isFillGapsInIDs", getIdFilling());
        gedcomPrefs.put("defaultEncoding", getEncoding());
        ancestris.app.Options.setWriteBOM(jCheckBox19.isSelected());
        ConfirmChangeWidget.setAutoCommit(cbAutoCommit.isSelected());
        genj.gedcom.Options.getInstance().setAddNameSubtags(cbAddNameSubtags.isSelected());
        StatusDisplayer.getDefault().setStatusText(org.openide.util.NbBundle.getMessage(OptionDataPanel.class, "OptionPanel.saved.statustext"));
        gedcomOptions.setGivenTag(jCheckBox3.isSelected()?jtGivenTag.getText().trim():"");
        gedcomOptions.setSpaceIsSeparator(cbSpaceIsSep.isSelected());
    }

    public boolean valid() {
        return !jtSubmitterName.getText().isEmpty();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAddNameSubtags;
    private javax.swing.JCheckBox cbAutoCommit;
    private javax.swing.JCheckBox cbCreateSpouse;
    private javax.swing.JCheckBox cbSpaceIsSep;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jtGivenTag;
    private javax.swing.JTextField jtSubmitterName;
    private ancestris.modules.beans.APlaceFormatBean placeFormat;
    // End of variables declaration//GEN-END:variables

    void setSubmName(String str) {
        jtSubmitterName.setText(str);
    }

    String getSubmName() {
        return jtSubmitterName.getText();
    }

    void setSubmCity(String str) {
        jTextField2.setText(str);
    }

    String getSubmCity() {
        return jTextField2.getText();
    }

    void setSubmPhone(String str) {
        jTextField3.setText(str);
    }

    String getSubmPhone() {
        return jTextField3.getText();
    }

    void setSubmPostCode(String str) {
        jTextField4.setText(str);
    }

    String getSubmPostCode() {
        return jTextField4.getText();
    }

    void setSubmEmail(String str) {
        jTextField5.setText(str);
    }

    String getSubmEmail() {
        return jTextField5.getText();
    }

    void setSubmCountry(String str) {
        jTextField6.setText(str);
    }

    String getSubmCountry() {
        return jTextField6.getText();
    }

    void setSubmWeb(String str) {
        jTextField7.setText(str);
    }

    String getSubmWeb() {
        return jTextField7.getText();
    }

    void setNamesSpouse(String str) {
        jCheckBox2.setSelected(str.equals("true") ? true : false);
    }

    String getNamesSpouse() {
        return jCheckBox2.isSelected() ? "true" : "false";
    }

    void setIDFilling(String str) {
        if (str.equals("")) {
            str = "true";
        }
        jCheckBox18.setSelected(str.equals("true") ? true : false);
    }

    String getIdFilling() {
        return jCheckBox18.isSelected() ? "true" : "false";
    }

    void setEncoding(String str) {
        if (str.equals("-1")) {
            str = "0";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 0;
        }
        if (i > encodings.length) {
            i = encodings.length;
        }
        jComboBox1.setSelectedIndex(i);
    }

    String getEncoding() {
        return jComboBox1.getSelectedIndex() + "";
    }

    private Integer getIntFromStr(String str) {

        Integer i = 0;
        try {
            i = Integer.valueOf(str);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

}
