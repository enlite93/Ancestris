
/*
 * CopyFamPanel.java
 *
 * Created on 4 janv. 2013, 21:46:57
 */

package ancestris.modules.copyFam;

import ancestris.core.pluginservice.AncestrisPlugin;
import ancestris.gedcom.GedcomDirectory;
import ancestris.modules.releve.editor.StandaloneEditor;
import genj.gedcom.Context;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyDate;
import genj.gedcom.PropertyPlace;
import genj.gedcom.TagPath;
import genj.view.SelectionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author Michel
 */
public class CopyFamPanel extends javax.swing.JFrame implements SelectionListener {

    private Context context1;
    private Context context2;
    private Fam  currentFam;
    static CopyFamPanel panel = null;

     /** Creates new form ReleveStatistic */
    static public void  showStatistics( ) {
        if (panel == null) {
            panel = new CopyFamPanel();
            panel.setVisible(true);
            panel.initData();
        } else {
            // Si la fenetre existe déjà, je l'affiche au premier plan
            panel.toFront();
            // je la desiconifie
            panel.setState(java.awt.Frame.NORMAL);
            panel.setVisible(true);
        }
    }

    private void initData() {
        // register for selectionListener
        AncestrisPlugin.register(this);
        if ( GedcomDirectory.getInstance().getContexts().size() >= 1) {
            context1=GedcomDirectory.getInstance().getContext(0);
        }
        
        context1=GedcomDirectory.getInstance().getContext(0);
        setContext(context1,true);
        if ( GedcomDirectory.getInstance().getContexts().size() >= 2) {
            context2=GedcomDirectory.getInstance().getContext(1);
        }

        showContext();

    }


    /** Creates new form CopyFamPanel */
    public CopyFamPanel() {
        initComponents();
        // je configure la taille de la fenetre
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        String size = NbPreferences.forModule(StandaloneEditor.class).get("CopyPanelSize", "300,450,0,0");
        String[] dimensions = size.split(",");
        if ( dimensions.length >= 4 ) {
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            int x = Integer.parseInt(dimensions[2]);
            int y = Integer.parseInt(dimensions[3]);
            if ( width < 100 ) {
                width = 100;
            }
            if ( height < 100 ) {
                height = 100;
            }
            if ( x < 10 || x > screen.width -10) {
                x = (screen.width / 2) - (width / 2);
            }
            if ( y < 10 || y > screen.height -10) {
                y = (screen.height / 2) - (height / 2);
            }
            setBounds(x, y, width, height);
        } else {
            setBounds(screen.width / 2 -100, screen.height / 2- 100, 300, 450);
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        husbandLastName = new javax.swing.JLabel();
        gedcom1 = new javax.swing.JLabel();
        wifeLastName = new javax.swing.JLabel();
        gedcom2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        swapContext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        husbandLastName.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.husbandLastName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(husbandLastName, gridBagConstraints);

        gedcom1.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.gedcom1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(gedcom1, gridBagConstraints);

        wifeLastName.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.wifeLastName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(wifeLastName, gridBagConstraints);

        gedcom2.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.gedcom2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(gedcom2, gridBagConstraints);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel1, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton1.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.jButton1.text")); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.SOUTH);

        swapContext.setText(org.openide.util.NbBundle.getMessage(CopyFamPanel.class, "CopyFamPanel.swapContext.text")); // NOI18N
        swapContext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swapContextActionPerformed(evt);
            }
        });
        getContentPane().add(swapContext, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // j'enregistre la taille dans les preferences
        String size;
        size = String.valueOf(evt.getWindow().getWidth()) + ","
                + String.valueOf(evt.getWindow().getHeight()) + ","
                + String.valueOf(evt.getWindow().getLocation().x + ","
                + String.valueOf(evt.getWindow().getLocation().y));

        NbPreferences.forModule(StandaloneEditor.class).put("CopyPanelSize", size);
        panel = null;
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (currentFam == null) {
                Toolkit.getDefaultToolkit().beep();
            }
            if (currentFam.getHusband() == null || currentFam.getWife() == null) {
                Toolkit.getDefaultToolkit().beep();
            }

            // je cree le mari
            Indi husband = copyIndi(currentFam.getHusband());
            // je cree la femme
            Indi wife = copyIndi(currentFam.getWife());

            // je cree la famille
            Fam newFamily = (Fam) context2.getGedcom().createEntity(Gedcom.FAM);
            newFamily.setHusband(husband);
            newFamily.setWife(wife);

            // je crée la propriété MARR
            if ( currentFam.getProperty(new TagPath("FAM:MARR"))!= null ) {
                Property marriageProperty = newFamily.addProperty("MARR", "");
                // je copie la date de mariage
                PropertyDate marriageDate = (PropertyDate) currentFam.getProperty(new TagPath("FAM:MARR:DATE"));
                if (marriageDate!=null) {
                    PropertyDate propertyDate = (PropertyDate) marriageProperty.addProperty("DATE", "");
                    propertyDate.setValue(marriageDate.getValue());
                }

                // je copie le lieu du mariage
                PropertyPlace marriagePlace = (PropertyPlace) currentFam.getProperty(new TagPath("FAM:MARR:PLAC"));
                if (marriagePlace!=null) {
                    Property propertyPlace = marriageProperty.addProperty("PLAC", "");
                    propertyPlace.setValue(marriagePlace.getValue());
                }
            }

            // je crée la propriété MARC
            if ( currentFam.getProperty(new TagPath("FAM:MARC"))!= null ) {
                Property marriageProperty = newFamily.addProperty("MARC", "");
                // je copie la date de mariage
                PropertyDate marriageDate = (PropertyDate) currentFam.getProperty(new TagPath("FAM:MARC:DATE"));
                if (marriageDate!=null) {
                    PropertyDate propertyDate = (PropertyDate) marriageProperty.addProperty("DATE", "");
                    propertyDate.setValue(marriageDate.getValue());
                }

                // je copie le lieu du mariage
                PropertyPlace marriagePlace = (PropertyPlace) currentFam.getProperty(new TagPath("FAM:MARC:PLAC"));
                if (marriagePlace!=null) {
                    Property propertyPlace = marriageProperty.addProperty("PLAC", "");
                    propertyPlace.setValue(marriagePlace.getValue());
                }
            }

        } catch (GedcomException ex) {
            Toolkit.getDefaultToolkit().beep();
            Exceptions.printStackTrace(ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void swapContextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapContextActionPerformed
        // TODO add your handling code here:
        Context context = context2;
        context2 = context1;
        context1 = context;
        showContext();
        setContext(context1,true);
    }//GEN-LAST:event_swapContextActionPerformed

    private void showContext() {
        if ( context1 != null) {
            gedcom1.setText(context1.getGedcom().getName());
        }

        if ( context2 != null ) {
            gedcom2.setText(context2.getGedcom().getName());
        }
    }

    private Indi copyIndi(Indi indi) throws GedcomException {
        // je cree l'individu
        Indi newIndi = (Indi) context2.getGedcom().createEntity(Gedcom.INDI);
        newIndi.setName(indi.getFirstName(), indi.getLastName());
        newIndi.setSex(indi.getSex());
        // j'ajoute la naissance
        Property birthProperty = null;
        if (indi.getValue(new TagPath("INDI:BIRT"), "") != null) {
            birthProperty = newIndi.addProperty("BIRT", "");
        }
        // j'ajoute la date de la naissance
        if (indi.getBirthDate() != null) {
            PropertyDate propertyDate = (PropertyDate) birthProperty.addProperty("DATE", "");
            propertyDate.setValue(indi.getBirthDate().getValue());
        }
        // j'ajoute le lieu
        if (indi.getValue(new TagPath("INDI:BIRT:PLAC"), "") != null) {
            PropertyPlace birthPropertyPlace = (PropertyPlace) birthProperty.addProperty("PLAC", "");
            birthPropertyPlace.setValue(indi.getValue(new TagPath("INDI:BIRT:PLAC"), ""));
        }

        // j'ajoute le deces
        Property deathProperty = null;
        if (indi.getValue(new TagPath("INDI:DEAT"), "") != null) {
            deathProperty = newIndi.addProperty("DEAT", "");
        }
        // j'ajoute la date de deces
        if (indi.getDeathDate() != null) {
            PropertyDate propertyDate = (PropertyDate) deathProperty.addProperty("DATE", "");
            propertyDate.setValue(indi.getDeathDate().getValue());
        }
        // j'ajoute le lieu de deces
        if (indi.getValue(new TagPath("INDI:DEAT:PLAC"), "") != null) {
            PropertyPlace deathPropertyPlace = (PropertyPlace) deathProperty.addProperty("PLAC", "");
            deathPropertyPlace.setValue(indi.getValue(new TagPath("INDI:DEAT:PLAC"), ""));
        }
        return newIndi;

    }
    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CopyFamPanel().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gedcom1;
    private javax.swing.JLabel gedcom2;
    private javax.swing.JLabel husbandLastName;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton swapContext;
    private javax.swing.JLabel wifeLastName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setContext(Context context, boolean isActionPerformed) {
         if (context != null && context.getGedcom() != null) {
            if (context.getGedcom().equals(this.context1.getGedcom()) ) {
                if (context.getEntity() != null && context.getEntity() instanceof Fam) {
                    currentFam = (Fam)context.getEntity();
                    if ( currentFam.getHusband() != null ) {
                        husbandLastName.setText(currentFam.getHusband().getName());
                    } else {
                        husbandLastName.setText("");
                    }
                    if ( currentFam.getWife() != null ) {
                        wifeLastName.setText(currentFam.getWife().getName());
                    } else {
                        wifeLastName.setText("");
                    }
                }
            }
        }
    }

}
