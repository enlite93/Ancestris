/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FeedbackPanel.java
 *
 * Created on 15 mars 2010, 23:31:41
 */

package ancestris.extensions.feedback;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author daniel
 */
public class FeedbackPanel extends javax.swing.JPanel {
    private long fileLength;
    private javax.swing.ImageIcon ancestris_logo = new javax.swing.ImageIcon(getClass().getResource("/ancestris/extensions/feedback/ancestris_logo.gif")); // NOI18N

    /** Creates new form FeedbackPanel */
    public FeedbackPanel(long length) {
        this.fileLength = length;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbIncGenjLog = new javax.swing.JCheckBox();
        jtEmailTo = new javax.swing.JFormattedTextField();
        jtEmail = new javax.swing.JFormattedTextField();
        jtName = new javax.swing.JFormattedTextField();
        jtSubject = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Image image = ancestris_logo.getImage();
                int x = (this.getWidth() - image.getWidth(null)) / 2;
                int y = (this.getHeight() - image.getHeight(null)) / 2;
                g2d.drawImage(image,0, 0, 116, 116, null);
                super.paintComponent(g);
            }
        };

        setBackground(getBackground());

        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        jTextArea1.setBackground(getBackground());
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(jTextArea1.getFont().deriveFont(jTextArea1.getFont().getSize()+2f));
        jTextArea1.setForeground(new java.awt.Color(0, 51, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jTextArea1.text")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel5.text")); // NOI18N

        jLabel6.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/extensions/feedback/Bundle").getString("FeedbackPanel.jLabel6.text"), new Object[] {fileLength})); // NOI18N

        jcbIncGenjLog.setSelected(true);
        jcbIncGenjLog.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jcbIncGenjLog.text")); // NOI18N
        jcbIncGenjLog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jtEmail.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtEmail.text")); // NOI18N

        jtName.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtName.text")); // NOI18N

        jtSubject.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtSubject.text")); // NOI18N

        jtaText.setColumns(20);
        jtaText.setLineWrap(true);
        jtaText.setRows(5);
        jtaText.setWrapStyleWord(true);
        jtaText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jtaText);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(116, 116));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbIncGenjLog))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                    .addComponent(jtSubject, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                    .addComponent(jtEmailTo, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                    .addComponent(jtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                    .addComponent(jtName, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))))
                        .addGap(8, 8, 8)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtEmailTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jcbIncGenjLog))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    javax.swing.JCheckBox jcbIncGenjLog;
    javax.swing.JFormattedTextField jtEmail;
    javax.swing.JFormattedTextField jtEmailTo;
    javax.swing.JFormattedTextField jtName;
    javax.swing.JFormattedTextField jtSubject;
    javax.swing.JTextArea jtaText;
    // End of variables declaration//GEN-END:variables

}
