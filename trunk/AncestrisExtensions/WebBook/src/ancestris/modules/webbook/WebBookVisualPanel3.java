/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestris.modules.webbook;

import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class WebBookVisualPanel3 extends JPanel {

    String[] sourcesType = new String[]{
        NbBundle.getMessage(WebBookVisualPanel3.class, "sourceType.type1"),
        NbBundle.getMessage(WebBookVisualPanel3.class, "sourceType.type2"),
        NbBundle.getMessage(WebBookVisualPanel3.class, "sourceType.type3")
    };

    /** Creates new form WebBookVisualPanel3 */
    public WebBookVisualPanel3() {
        initComponents();
        setComponents();
    }

    public void setComponents() {
        jComboBox1.setEnabled(jCheckBox1.isSelected());
        jCheckBox2.setEnabled(jCheckBox1.isSelected());
        jCheckBox4.setEnabled(jCheckBox3.isSelected());
        jCheckBox6.setEnabled(jCheckBox5.isSelected());
        jTextField1.setEnabled(jCheckBox5.isSelected());

    }
    @Override
    public String getName() {
        return NbBundle.getMessage(WebBookWizardAction.class, "CTL_Step_3");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox(sourcesType);
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_DisplaySources")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox1.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_TypeOfSources")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_SourceCopies")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox3, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox3.text")); // NOI18N
        jCheckBox3.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_DisplayMedia")); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox4, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox4.text")); // NOI18N
        jCheckBox4.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_MediaCopies")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox5, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox5.text")); // NOI18N
        jCheckBox5.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_DisplayMap")); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox6, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jCheckBox6.text")); // NOI18N
        jCheckBox6.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_DisplayEmptyLoc")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jLabel4.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "WebBookVisualPanel3.jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel3.class, "TTT_GoogleKey")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox2)
                    .addComponent(jComboBox1, 0, 259, Short.MAX_VALUE)
                    .addComponent(jCheckBox1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        jComboBox1.setEnabled(jCheckBox1.isSelected());
        jCheckBox2.setEnabled(jCheckBox1.isSelected());

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        jCheckBox4.setEnabled(jCheckBox3.isSelected());

    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        jCheckBox6.setEnabled(jCheckBox5.isSelected());
        jTextField1.setEnabled(jCheckBox5.isSelected());
    }//GEN-LAST:event_jCheckBox5ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public String getPref01() {
        return jCheckBox1.isSelected() ? "1" : "0";
    }

    public void setPref01(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultGeneSources");
        }
        jCheckBox1.setSelected(str.equals("1"));
    }

    public String getPref02() {
        return jComboBox1.getSelectedItem().toString();
    }

    public void setPref02(String str) {
        if (str.isEmpty()) {
            str = "0";
        }
        for (int i = 0; i < sourcesType.length; i++) {
            String type = sourcesType[i];
            if (type.equals(str)) {
                jComboBox1.setSelectedIndex(i);
            }
        }
    }

    public String getPref03() {
        return jCheckBox2.isSelected() ? "1" : "0";
    }

    public void setPref03(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultCopySources");
        }
        jCheckBox2.setSelected(str.equals("1"));
    }

    public String getPref04() {
        return jCheckBox3.isSelected() ? "1" : "0";
    }

    public void setPref04(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultGeneMedia");
        }
        jCheckBox3.setSelected(str.equals("1"));
    }

    public String getPref05() {
        return jCheckBox4.isSelected() ? "1" : "0";
    }

    public void setPref05(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultCopyMedia");
        }
        jCheckBox4.setSelected(str.equals("1"));
    }

    public String getPref06() {
        return jCheckBox5.isSelected() ? "1" : "0";
    }

    public void setPref06(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultGeneMap");
        }
        jCheckBox5.setSelected(str.equals("1"));
    }

    public String getPref07() {
        return jCheckBox6.isSelected() ? "1" : "0";
    }

    public void setPref07(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultDispUnknownLoc");
        }
        jCheckBox6.setSelected(str.equals("1"));
    }

    public String getPref08() {
        return jTextField1.getText();
    }

    public void setPref08(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_defaultGoogleKey");
        }
        jTextField1.setText(str);
    }

}

