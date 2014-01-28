/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app.tools.webbook;

import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class WebBookVisualPanel7 extends JPanel {

    /** Creates new form WebBookVisualPanel3 */
    public WebBookVisualPanel7() {
        initComponents();
        setComponents();
    }

    public void setComponents() {
        jTextField2.setEnabled(jCheckBox1.isSelected() && !jCheckBox2.isSelected());
        jTextField3.setEnabled(jCheckBox1.isSelected() && !jCheckBox2.isSelected());
        jCheckBox2.setEnabled(jCheckBox1.isSelected());
        if (!jCheckBox1.isSelected()) {
            jCheckBox2.setSelected(false);
        }
        jTextField1.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField5.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField6.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField7.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField8.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField9.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
        jTextField11.setEnabled(jCheckBox1.isSelected() && jCheckBox2.isSelected());
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(WebBookWizardAction.class, "CTL_Step_7");
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(437, 390));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPSupport")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel2.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPTest")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPIntegrate")); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jTextField5.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField5.text")); // NOI18N
        jTextField5.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPInit")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField6.text")); // NOI18N
        jTextField6.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPMyScript")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel6.text")); // NOI18N

        jTextField7.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField7.text")); // NOI18N
        jTextField7.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPHeadStart")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel8.text")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField8.text")); // NOI18N
        jTextField8.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPHeadCSS")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel10.text")); // NOI18N

        jTextField9.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField9.text")); // NOI18N
        jTextField9.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPHeadEnd")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel12.text")); // NOI18N

        jTextField11.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField11.text")); // NOI18N
        jTextField11.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPFooter")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel11.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPProfil")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel13.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jLabel14.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "WebBookVisualPanel7.jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(org.openide.util.NbBundle.getMessage(WebBookVisualPanel7.class, "TTT_PHPCode")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(51, 51, 51)
                        .addComponent(jCheckBox2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel12)
                        .addGap(33, 33, 33)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(75, 75, 75)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        setComponents();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        setComponents();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    public String getPref01() {
        return jCheckBox1.isSelected() ? "1" : "0";
    }

    public void setPref01(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPSupport");
        }
        jCheckBox1.setSelected(str.equals("1"));
    }

    public String getPref02() {
        return jTextField2.getText();
    }

    public void setPref02(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPProfil");
        }
        jTextField2.setText(str);
    }

    public String getPref03() {
        return jTextField3.getText();
    }

    public void setPref03(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPCode");
        }
        jTextField3.setText(str);
    }

    public String getPref04() {
        return jCheckBox2.isSelected() ? "1" : "0";
    }

    public void setPref04(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPIntegrate");
        }
        jCheckBox2.setSelected(str.equals("1"));
    }

    public String getPref05() {
        return jTextField1.getText();
    }

    public void setPref05(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPTest");
        }
        jTextField1.setText(str);
    }

    public String getPref06() {
        return jTextField5.getText();
    }

    public void setPref06(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPInit");
        }
        jTextField5.setText(str);
    }

    public String getPref07() {
        return jTextField6.getText();
    }

    public void setPref07(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPMyScript");
        }
        jTextField6.setText(str);
    }

    public String getPref08() {
        return jTextField7.getText();
    }

    public void setPref08(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPHeadStart");
        }
        jTextField7.setText(str);
    }

    public String getPref09() {
        return jTextField8.getText();
    }

    public void setPref09(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPHeadCSS");
        }
        jTextField8.setText(str);
    }

    public String getPref10() {
        return jTextField9.getText();
    }

    public void setPref10(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPHeadEnd");
        }
        jTextField9.setText(str);
    }

    public String getPref11() {
        return jTextField11.getText();
    }

    public void setPref11(String str) {
        if (str.isEmpty()) {
            str = NbBundle.getMessage(WebBookWizardAction.class, "PREF_PHPFooter");
        }
        jTextField11.setText(str);
    }

}
