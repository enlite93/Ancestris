/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2015 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.treesharing.options;

import ancestris.modules.treesharing.communication.MemberProfile;
import ancestris.util.swing.DialogManager;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.apache.commons.io.FileUtils;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class TreeSharingOptionsPanel extends javax.swing.JPanel {

    private final TreeSharingOptionsPanelController controller;
    private String photoPath = "";
    private boolean loading = false;
    
    public static final String[] MATCHING_TYPES = new String[] { 
        NbBundle.getMessage(TreeSharingOptionsPanel.class, "Match1"), 
        NbBundle.getMessage(TreeSharingOptionsPanel.class, "Match2")
    };

    TreeSharingOptionsPanel(TreeSharingOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        loading = false;
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jCheckBox1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel1.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel3.text")); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(MATCHING_TYPES));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel2.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel4.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel5.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel6.text")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel7.text")); // NOI18N

        jTextField4.setText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jTextField4.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel8.toolTipText")); // NOI18N
        jLabel8.setBorder(null);
        jLabel8.setIconTextGap(0);
        jLabel8.setPreferredSize(new java.awt.Dimension(155, 186));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel8.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel8.AccessibleContext.accessibleName")); // NOI18N
        jLabel8.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel8.AccessibleContext.accessibleDescription")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        loading = true;
        chooseAndDisplayImage();
        loading = false;
    }//GEN-LAST:event_jLabel8MouseClicked

    void load() {
        jTextField1.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Pseudo", "").trim());
        jTextField2.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Lastname", "").trim());
        jTextField3.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Firstname", "").trim());
        jTextField4.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Email", "").trim());
        jTextField5.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("City", "").trim());
        jTextField6.setText(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Country", "".trim()));
        photoPath = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Photo", "");
        loadPhoto(new File(photoPath));
        jCheckBox1.setSelected(NbPreferences.forModule(TreeSharingOptionsPanel.class).getBoolean("RespectPrivacy", true));
        jComboBox1.setSelectedItem(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("MatchingType", MATCHING_TYPES[0]));
    }

    void store() {
        String str = jTextField1.getText();
        int i = str.indexOf(" ");
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Pseudo", i == -1 ? str : str.substring(0, i));  // no space in pseudo
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Lastname", jTextField2.getText().trim());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Firstname", jTextField3.getText().trim());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Email", jTextField4.getText().trim());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("City", jTextField5.getText().trim());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Country", jTextField6.getText().trim());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("Photo", photoPath);
        NbPreferences.forModule(TreeSharingOptionsPanel.class).putBoolean("RespectPrivacy", jCheckBox1.isSelected());
        NbPreferences.forModule(TreeSharingOptionsPanel.class).put("MatchingType", (String) jComboBox1.getSelectedItem());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    private void chooseAndDisplayImage() {
        JFileChooser jfc = new JFileChooser();
        int ret = jfc.showDialog(jfc, NbBundle.getMessage(TreeSharingOptionsPanel.class, "FileChooserTitle"));
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            if (loadPhoto(f)) {
                File dest = new File(System.getProperty("netbeans.user") + File.separator + f.getName());
                try {
                    FileUtils.copyFile(f, dest);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
                photoPath = dest.getAbsolutePath();
            } else {
                jLabel8.setText(NbBundle.getMessage(TreeSharingOptionsPanel.class, "TreeSharingOptionsPanel.jLabel8.toolTipText"));
                jLabel8.setIcon(null);
            }
        }
    }
    
    private boolean loadPhoto(File f) {
        if (f != null) {
            try {
                Image bi = ImageIO.read(f);
                if (bi != null) {
                    jLabel8.setText("");
                    jLabel8.setIcon(new ImageIcon(bi.getScaledInstance(jLabel8.getPreferredSize().width, jLabel8.getPreferredSize().height, Image.SCALE_SMOOTH)));
                    return true;
                }
            } catch (Exception ex) {
                //Exceptions.printStackTrace(ex);
                if (loading) {
                    DialogManager.create(NbBundle.getMessage(TreeSharingOptionsPanel.class, "TITL_BadPicture"),
                            NbBundle.getMessage(TreeSharingOptionsPanel.class, "TITL_PleaseChooseOther")).setMessageType(DialogManager.ERROR_MESSAGE).show();
                }
            }
        }
        return false;
    }
    
    public static String getPseudo() {
        return NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Pseudo", "").trim();
    }

    public static MemberProfile getProfile() {
        MemberProfile profile = new MemberProfile();
        profile.lastname = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Lastname", "").trim();
        profile.firstname = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Firstname", "").trim();
        profile.email = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Email", "").trim();
        profile.city = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("City", "").trim();
        profile.country = NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Country", "".trim());
        profile.setPhotoBytes(new File(NbPreferences.forModule(TreeSharingOptionsPanel.class).get("Photo", "")));
        return profile;
    }


}
