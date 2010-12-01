/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import ancestris.util.AncestrisPreferences;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;
import org.netbeans.api.actions.Openable;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;

final class OptionFilesPanel extends javax.swing.JPanel {

    private final OptionFilesOptionsPanelController controller;
    //Create a file chooser
    private JFileChooser fc = null;

    OptionFilesPanel(OptionFilesOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    private void chooseFileDir(javax.swing.JTextField jTF, boolean dirOnly, boolean arg) {
        String str = jTF.getText();
        fc = new JFileChooser(str != null ? str.substring(0, Math.max(str.indexOf(" "), str.length())) : "");
        if (dirOnly) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            jTF.setText(fc.getSelectedFile().getAbsolutePath() + (arg ? " %" : ""));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerNumberModel(256, 128, 16384, 128));
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        logLevel = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(691, 503));

        jTextField3.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField3.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel13.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSpinner1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jSpinner1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton6, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton7, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField2.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton5, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel6.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel12.text")); // NOI18N

        jTextField9.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField9.text")); // NOI18N
        jTextField9.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField9.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel9.text")); // NOI18N

        jTextField7.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField7.text")); // NOI18N
        jTextField7.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField7.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel10.text")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField8.text")); // NOI18N
        jTextField8.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField8.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel7.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField5.text")); // NOI18N
        jTextField5.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField5.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel8.text")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField6.text")); // NOI18N
        jTextField6.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField6.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel11.text")); // NOI18N

        jTextField4.setText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField4.text")); // NOI18N
        jTextField4.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jTextField4.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton8, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton9, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionFilesPanel.jLabel14.text")); // NOI18N

        logLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OFF", "SEVERE", "WARNING", "INFO", "CONFIG", "FINE", "FINER", "FINEST", "ALL" }));
        logLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logLevelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8)
                                    .addComponent(jButton6)
                                    .addComponent(jButton4))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9)
                                    .addComponent(jButton7)
                                    .addComponent(jButton5)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2)))))
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(logLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(29, 29, 29)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(logLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        chooseFileDir(jTextField3, true, false);
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        chooseFileDir(jTextField2, false, false);
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        chooseFileDir(jTextField5, false, true);
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        chooseFileDir(jTextField8, false, true);
}//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        chooseFileDir(jTextField4, false, true);
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        chooseFileDir(jTextField7, false, true);
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        chooseFileDir(jTextField6, false, true);
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        chooseFileDir(jTextField9, false, true);
}//GEN-LAST:event_jButton9ActionPerformed

    private void logLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logLevelActionPerformed

    void load() {
        AncestrisPreferences gedcomPrefs = AncestrisPreferences.get(genj.gedcom.Options.class);
        AncestrisPreferences appPrefs = AncestrisPreferences.get(genj.app.Options.class);

        setGedcomFile(gedcomPrefs.get("gedcomFile", ""));
        setReportDir(gedcomPrefs.get("reportDir", ""));

//TODO: not used atm        registry.put("options.associations", "6");
//        registry.put("options.associations.1", NbPreferences.forModule(App.class).get("assoTxt", ""));
//        registry.put("options.associations.2", NbPreferences.forModule(App.class).get("assoOffice", ""));
//        registry.put("options.associations.3", NbPreferences.forModule(App.class).get("assoAdobe", ""));
//        registry.put("options.associations.4", NbPreferences.forModule(App.class).get("assoImages", ""));
//        registry.put("options.associations.5", NbPreferences.forModule(App.class).get("assoSound", ""));
//        registry.put("options.associations.6", NbPreferences.forModule(App.class).get("assoWeb", ""));

//        setAssoTxt(NbPreferences.forModule(App.class).get("assoTxt", ""));
//        setAssoOffice(NbPreferences.forModule(App.class).get("assoOffice", ""));
//        setAssoAdobe(NbPreferences.forModule(App.class).get("assoAdobe", ""));
//        setAssoImages(NbPreferences.forModule(App.class).get("assoImages", ""));
//        setAssoSound(NbPreferences.forModule(App.class).get("assoSound", ""));
//        setAssoWeb(NbPreferences.forModule(App.class).get("assoWeb", ""));
        setAssoTxt("");
        setAssoOffice("");
        setAssoAdobe("");
        setAssoImages("");
        setAssoSound("");
        setAssoWeb("");

        setLogSize(appPrefs.get("maxLogSizeKB", ""));
        setLogLevel(appPrefs.get("logLevel", "INFO"));
    }

    void store() {
        AncestrisPreferences gedcomPrefs = AncestrisPreferences.get(genj.gedcom.Options.class);
        AncestrisPreferences appPrefs = AncestrisPreferences.get(genj.app.Options.class);

        gedcomPrefs.put("gedcomFile", getGedcomFile());
        gedcomPrefs.put("reportDir", getReportDir());
//        NbPreferences.forModule(App.class).put("assoTxt", getAssoTxt());
//        NbPreferences.forModule(App.class).put("assoOffice", getAssoOffice());
//        NbPreferences.forModule(App.class).put("assoAdobe", getAssoAdobe());
//        NbPreferences.forModule(App.class).put("assoImages", getAssoImages());
//        NbPreferences.forModule(App.class).put("assoSound", getAssoSound());
//        NbPreferences.forModule(App.class).put("assoWeb", getAssoWeb());
        appPrefs.put("maxLogSizeKB", getLogSize());
        appPrefs.put("logLevel", getLogLevel());

//        NbPreferences.forModule(App.class).put("optionswizard", "3"); // should be same as in the wizard

        StatusDisplayer.getDefault().setStatusText(org.openide.util.NbBundle.getMessage(OptionFilesPanel.class, "OptionPanel.saved.statustext"));

        Collection<? extends Openable> actions;
        actions = Lookup.getDefault().lookupAll(Openable.class);
        for (Iterator<? extends Openable> it = actions.iterator(); it.hasNext();) {
            Openable openable = it.next();
            openable.open();
        }

    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox logLevel;
    // End of variables declaration//GEN-END:variables

    void setGedcomFile(String str) {
        if (str.equals("")) {
            str = ""; //System.getProperty("user.dir") + File.separator + "maGenealogie.ged";
        }
        jTextField2.setText(str);
    }

    String getGedcomFile() {
        return jTextField2.getText();
    }

    void setReportDir(String str) {
        if (str.equals("")) {
            str = System.getProperty("user.dir") + File.separator + "report";
        }
        jTextField3.setText(str);
    }

    String getReportDir() {
        return jTextField3.getText();
    }

    /**
     * For all asso fields, str parameters is of format (file extension)*(description)*(command)
     * @param str : the whole string
     */
    String getAssoCommand(String str) {
        String command = "";
        int i = str.lastIndexOf("*");
        if (i < 0) {
            command = str;
        } else if ((i + 1) == (str.length()) || (str.equals(""))) {
            command = "";
        } else {
            command = str.substring(i + 1, str.length());
        }
        return command;
    }

    void setAssoTxt(String str) {
        jTextField4.setText(getAssoCommand(str));
    }

    String getAssoTxt() {
        return "txt,log*Fichier Texte*" + jTextField4.getText();
    }

    void setAssoOffice(String str) {
        jTextField5.setText(getAssoCommand(str));
    }

    String getAssoOffice() {
        return "doc,xls,ppt,pptx,odt,ods,odp*Documents de bureau*" + jTextField5.getText();
    }

    void setAssoAdobe(String str) {
        jTextField6.setText(getAssoCommand(str));
    }

    String getAssoAdobe() {
        return "pdf,xpdf*Fichier Adobe*" + jTextField6.getText();
    }

    void setAssoImages(String str) {
        jTextField7.setText(getAssoCommand(str));
    }

    String getAssoImages() {
        return "jpg,png,bmp*Images*" + jTextField7.getText();
    }

    void setAssoSound(String str) {
        jTextField8.setText(getAssoCommand(str));
    }

    String getAssoSound() {
        return "mp3,ogg,wav*Music*" + jTextField8.getText();
    }

    void setAssoWeb(String str) {
        jTextField9.setText(getAssoCommand(str));
    }

    String getAssoWeb() {
        return "html,xml,htm*Internet*" + jTextField9.getText();
    }

    void setLogSize(String str) {
        if (str.equals("-1")) {
            str = "128";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 256;
        }
        if (i > 16384) {
            i = 16384;
        }
        jSpinner1.setValue(i);
    }

    String getLogSize() {
        return jSpinner1.getValue().toString();
    }

    String getLogLevel() {
        return logLevel.getSelectedItem().toString();
    }

    void setLogLevel(String str) {
        logLevel.setSelectedItem(str);
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
