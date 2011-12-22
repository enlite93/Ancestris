/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ActionAPropos.java
 *
 * Created on 28 févr. 2010, 00:02:23
 */
package ancestris.app;

import ancestris.core.pluginservice.PluginInterface;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 *
 * @author frederic
 */
public class ActionAPropos extends JDialog implements ActionListener {

    private String java = System.getProperty("java.runtime.version") + " - " + System.getProperty("java.home");
    private String systeme = System.getProperty("os.name") + " - " + System.getProperty("os.version") + " - " + System.getProperty("user.name");
    private String userdir = getUserDir();
    private Timer timer;
    private int yTop = 0;
    private int hSize = 0;

    /** Creates new form ActionAPropos */
    public ActionAPropos() {
        initComponents();
        setMaximumSize(new Dimension(664, 585));
        setTimer();
        //setScroller();
        setIconImage(new ImageIcon(App.class.getResource("/ancestris/app/ActionAPropos.png")).getImage());
        setTitle(NbBundle.getMessage(ActionAPropos.class, "CTL_ActionAPropos"));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        jButton1.requestFocusInWindow();
        yTop = -jPanel3.getHeight();
        jPanel2.setBounds(0, yTop, jPanel2.getWidth(), jPanel2.getHeight());
        setVisible(true);
        timer.stop();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jLabel7.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel7.text")); // NOI18N

        setBackground(new java.awt.Color(254, 254, 254));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jButton1.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "AProposPanel.jButton1.text.TTT")); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(120, 29));
        jButton1.setSelected(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 11));
        jLabel3.setText(App.getPluginVersion());

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel2.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel2.text")); // NOI18N

        jLabel1.setBackground(new java.awt.Color(240, 176, 111));
        jLabel1.setText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(640, 335));
        jLabel1.setIcon(new ImageIcon(App.class.getResource("/ancestris/app/splash.gif")));

        jButton2.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jButton2.text")); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(120, 29));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jButton3.text")); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(120, 29));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel5.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel5.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel6.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel6.text")); // NOI18N

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel8.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel8.text")); // NOI18N

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel9.setText(java);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel10.setText(systeme);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        jLabel11.setText(userdir);

        jPanel1.setBorder(null);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setHorizontalScrollBar(null);

        jPanel2.setBorder(null);
        jPanel2.setPreferredSize(new java.awt.Dimension(640, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        jLabel4.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel4.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 2, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(getContributors());
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jLabel4.AccessibleContext.accessibleName")); // NOI18N

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText(org.openide.util.NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jButton4.text")); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(120, 29));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                        .addGap(298, 298, 298))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timer.stop();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        jButton1.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        displayLicence();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        displayVersions();
    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    // Take last 72 charachters of userdir if too long
    private String getUserDir() {
        String str = System.getProperty("netbeans.user");
        int length = str.length();
        int index = length > 72 ? length - 72 : 0;
        return (index > 0 ? "..." : "") + str.substring(index);
    }

    private void setTimer() {
        int delay = 50; //milliseconds
        hSize = jPanel3.getHeight() + jLabel4.getHeight() + jLabel12.getHeight();
        jPanel2.setPreferredSize(new Dimension(jPanel2.getWidth(), hSize));

        ActionListener taskPerformer = new ActionListener() {

            // to make this work, panel 2 preferred height size has to be equal to h;
            public void actionPerformed(ActionEvent evt) {
                yTop--;
                if ((yTop + hSize - 5) < jPanel3.getHeight()) {   // 5 is to remove the little overlap we have
                    yTop = 0;
                }
                jPanel2.setBounds(jPanel2.getX(), yTop, jPanel2.getWidth(), hSize);
            }
        };
        timer = new Timer(delay, taskPerformer);
    }

    private void displayLicence() {
        String title = NbBundle.getMessage(ActionAPropos.class, "CTL_APropos_LicenceTitle");
        String text = NbBundle.getMessage(ActionAPropos.class, "CTL_APropos_LicenceText");
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(getScrollableText(text), title, NotifyDescriptor.DEFAULT_OPTION, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

    private void displayVersions() {
        String title = NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.jButton4.text");
        String text = "<html><br><b>" + 
                App.getPluginShortDescription() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>" +
                App.getPluginVersion();
        text += "<br><br><br><b>" + NbBundle.getMessage(ActionAPropos.class, "CTL_APropos_VersionTitle") + "&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</b><br>";
        text += "<table border='0'>";
        for (PluginInterface sInterface : Lookup.getDefault().lookupAll(PluginInterface.class)) {
            try {
                if (sInterface.getPluginDisplayName() != null) {
                    text += "<tr><td><b>&nbsp;&middot;&nbsp;" + sInterface.getPluginDisplayName() + " :</b></td>";
                    try {
                        text += "<td>" + sInterface.getPluginVersion() + "</td>";
                    } catch (Throwable e) {
                        text += "<td>" + "non disponible" + "</td>";
                        App.LOG.info(e.getMessage());
                    }
                    text += "</tr>";
                }
            } catch (Throwable e) {
                        App.LOG.info(e.getMessage());
            }
        }
        text += "</table><br></html>";
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(getScrollableText(text), title, NotifyDescriptor.DEFAULT_OPTION, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

    private String getContributors(){
        String contributors = NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.contributors"); // NOI18N
        String translators = NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.translators"); // NOI18N

        return NbBundle.getMessage(ActionAPropos.class, "ActionAPropos.contributors.text",
                "<br>"+contributors.replaceAll(",", "<br>"),
                "<br>"+translators.replaceAll(",", "<br>")+"<br><br><br>"+"-:-:-:-:-:-:-:-:-:-:-:-:-"+"<br><br><br>"); // NOI18N
    }

    private JScrollPane getScrollableText(String text){
        JLabel area = new JLabel(text);
        area.setOpaque(true);
        return new JScrollPane(area);
    }
}

