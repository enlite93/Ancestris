/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorOpenActionPanel.java
 *
 * Created on 16 juin 2011, 23:11:29
 */
package org.ancestris.trancestris.explorers.zipexplorer.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 *
 * @author dominique
 */
public class ZipExplorerOpenActionPanel extends javax.swing.JPanel {

    File zipFile = null;
    static HashMap<String, Locale> localeList = new HashMap<String, Locale>();
    static Locale[] locales = null;
    Locale fromLocale = Locale.UK;
    Locale toLocale = Locale.getDefault();

    {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (localeList.get(locale.getDisplayLanguage()) == null) {
                localeList.put(locale.getDisplayLanguage(), locale);
            }
        }

        locales = new Locale[localeList.size()];
        SortedSet<String> sortedset = new TreeSet<String>(localeList.keySet());

        Iterator<String> iter = sortedset.iterator();

        int index = 0;
        while (iter.hasNext()) {
            locales[index++] = localeList.get(iter.next());
        }
    }

    private class LocaleComboBoxModel extends DefaultComboBoxModel {

        String selectedLocale = Locale.getDefault().getDisplayLanguage();

        public LocaleComboBoxModel() {
            super();
        }

        @Override
        public int getSize() {
            return localeList.size();
        }

        @Override
        public Object getElementAt(int i) {
            return locales[i].getDisplayLanguage();
        }

        @Override
        public void setSelectedItem(Object o) {
            selectedLocale = (String) o;
        }

        @Override
        public Object getSelectedItem() {
            return selectedLocale;
        }
    }

    /** Creates new form EditorOpenActionPanel */
    public ZipExplorerOpenActionPanel() {
        fromLocale = localeList.get(NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("fromLocale", Locale.UK.getDisplayLanguage()));
        toLocale = localeList.get(NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("toLocale", Locale.getDefault().getDisplayLanguage()));
        initComponents();
        jComboBox1.setSelectedItem(fromLocale.getDisplayLanguage());
        jComboBox2.setSelectedItem(toLocale.getDisplayLanguage());
        jTextField1.setText(NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", ""));
        String dirName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Dossier", "");
        String fileName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", "");
        if ((dirName + System.getProperty("file.separator") + fileName).length() > 0) {
            zipFile = new File(dirName + System.getProperty("file.separator") + fileName);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox(new LocaleComboBoxModel());
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox(new LocaleComboBoxModel());

        setMaximumSize(new java.awt.Dimension(32789, 24));
        setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ancestris/trancestris/explorers/zipexplorer/actions/translate.gif"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(ZipExplorerOpenActionPanel.class, "ZipExplorerOpenActionPanel.jLabel1.text")); // NOI18N

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText(org.openide.util.NbBundle.getMessage(ZipExplorerOpenActionPanel.class, "ZipExplorerOpenActionPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText(org.openide.util.NbBundle.getMessage(ZipExplorerOpenActionPanel.class, "ZipExplorerOpenActionPanel.jTextField1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ZipExplorerOpenActionPanel.class, "ZipExplorerOpenActionPanel.jLabel2.text")); // NOI18N

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Zip files", "zip");
        String dirName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Dossier", "");
        String fileName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", "");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        if (dirName.length() > 0) {
            // Set the current directory
            fileChooser.setCurrentDirectory(new File(dirName));
        }

        if (fileName.length() > 0) {
            fileChooser.setSelectedFile(new File(fileName));
        }

        if (fileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow()) == JFileChooser.APPROVE_OPTION) {
            zipFile = fileChooser.getSelectedFile();
            jTextField1.setText(zipFile.getName());
            try {
                NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Dossier", fileChooser.getCurrentDirectory().getCanonicalPath());
            } catch (IOException ex) {
                NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Dossier", "");
            }
            NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Fichier", zipFile.getName());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        fromLocale = localeList.get((String) jComboBox1.getSelectedItem());
        NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("fromLocale", fromLocale.getDisplayLanguage());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        toLocale = localeList.get((String) jComboBox2.getSelectedItem());
        NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("toLocale", toLocale.getDisplayLanguage());
    }//GEN-LAST:event_jComboBox2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
