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
package ancestris.modules.treesharing.panels;

import ancestris.modules.treesharing.communication.FriendGedcomEntity;
import ancestris.util.swing.DialogManager;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Indi;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import org.openide.util.NbBundle;

/**
 *
 * Represents Ancestris members with entities matching
 * 
 * @author frederic
 */
public class AncestrisFriend extends JInternalFrame {

    
    private final String name;
    private final String access;
    private Map<Indi, FriendGedcomEntity> matchedIndis = null; 
    private Map<Fam, FriendGedcomEntity> matchedFams = null; 

    
    /**
     * Creates new form AncestrisFriend
     */
    public AncestrisFriend(String name, String access) {
        super(name);
        this.name = name;
        this.access = access;
        matchedIndis = new HashMap<Indi, FriendGedcomEntity>();
        matchedFams = new HashMap<Fam, FriendGedcomEntity>();
        initComponents();
        updateStats();
        
        // Friend is created, therefore a first noticifaction is sent to friend to let hom know
        // TODO
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setToolTipText(org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.Form.toolTipText")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/friend.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(150, 100));
        setPreferredSize(new java.awt.Dimension(195, 115));
        setRequestFocusEnabled(false);
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Indi.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel1.text")); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Fam.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel5.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jLabel6.text")); // NOI18N

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showList((Map<?, FriendGedcomEntity>)matchedIndis);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showList((Map<?, FriendGedcomEntity>)matchedFams);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

    public String getFriendName() {
        return name;
    }
    
    public String getAccess() {
        return access;
    }

    public void addEntity(Entity entity, FriendGedcomEntity friendGedcomEntity) {
        if (entity instanceof Indi) {
            matchedIndis.put((Indi) entity, friendGedcomEntity);
            updateStats();
            // Friend is being match one more time for an indi, therefore notify him/her
            // TODO
            return;
        }
        if (entity instanceof Fam) {
            matchedFams.put((Fam) entity, friendGedcomEntity);
            updateStats();
            // Friend is being match one more time for a family, therefore notify him/her
            // TODO
            return;
        }
    }

    private void updateStats() {
        jButton1.setText("" + matchedIndis.size());
        jButton2.setText("" + matchedFams.size());
        jButton1.setEnabled(matchedIndis.size() != 0);
        jButton2.setEnabled(matchedFams.size() != 0);
    }

    private void showList(Map<?, FriendGedcomEntity> list) {
        DialogManager.create(NbBundle.getMessage(GedcomFriendMatch.class, "TITL_CommonEntities"), 
                new ListEntitiesPanel(NbBundle.getMessage(GedcomFriendMatch.class, "TITL_AllGedcoms"),  
                name, 
                list)).setMessageType(DialogManager.PLAIN_MESSAGE).setOptionType(DialogManager.OK_ONLY_OPTION).show();
    }


}