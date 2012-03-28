/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.explorers.zipexplorer;

import java.awt.Frame;
import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.tree.TreeSelectionModel;
import org.ancestris.trancestris.resources.ZipArchive;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.ancestris.trancestris.explorers.zipexplorer//ZipExplorer//EN",
autostore = false)
public final class ZipExplorerTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static final Logger logger = Logger.getLogger(ZipExplorerTopComponent.class.getName());
    private static ZipExplorerTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/ancestris/trancestris/explorers/zipexplorer/actions/zip-icon.png";
    private static final String PREFERRED_ID = "ZipExplorerTopComponent";
    private ExplorerManager zipExplorerManager = null;
    private ZipArchive zipFile = null;
    private InstanceContent instanceContent = new InstanceContent();
    private final ProxyLookup proxyLookup;
    private ZipRootNode newZipRootNode = null;
    private Preferences modulePreferences = NbPreferences.forModule(ZipExplorerTopComponent.class);

    public ZipExplorerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ZipExplorerTopComponent.class, "CTL_ZipExplorerTopComponent"));
        setToolTipText(NbBundle.getMessage(ZipExplorerTopComponent.class, "HINT_ZipExplorerTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        zipExplorerManager = new ExplorerManager();
        ((BeanTreeView) beanTreeView).setRootVisible(false);
        ((BeanTreeView) beanTreeView).setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        associateLookup(ExplorerUtils.createLookup(zipExplorerManager, getActionMap()));
//        ZipRootNode newZipRootNode = new ZipRootNode(zipFile, content);
//        setActivatedNodes(new Node[]{newZipRootNode});
        Lookup lookup = ExplorerUtils.createLookup(zipExplorerManager, getActionMap());
        proxyLookup = new ProxyLookup(lookup, new AbstractLookup(instanceContent));
        associateLookup(proxyLookup);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        beanTreeView = new BeanTreeView();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ZipExplorerTopComponent.class, "ZipExplorerTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jPanel1.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(ZipExplorerTopComponent.class, "ZipExplorerTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.NORTH);
        add(beanTreeView, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (beanTreeView != null) {
            ((BeanTreeView) beanTreeView).expandAll();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (beanTreeView != null && newZipRootNode != null) {
            Children children = newZipRootNode.getChildren();

            for (Node node : children.getNodes()) {
                ((BeanTreeView) beanTreeView).collapseNode(node);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane beanTreeView;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ZipExplorerTopComponent getDefault() {
        if (instance == null) {
            instance = new ZipExplorerTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ZipExplorerTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ZipExplorerTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            logger.warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ZipExplorerTopComponent) {
            return (ZipExplorerTopComponent) win;
        }
        logger.warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            @Override
            public void run() {
                String dirName = "";
                if ((dirName = modulePreferences.get("Dossier", "")).equals("") != true) {
                    String fileName = modulePreferences.get("Fichier", "");
                    File tempfile = new File(dirName + System.getProperty("file.separator") + fileName);

                    if (tempfile.exists()) {
                        Locale fromLocale = getLocaleFromString(modulePreferences.get("fromLocale", ""));
                        Locale toLocale = getLocaleFromString(modulePreferences.get("toLocale", ""));
                        setBundles(tempfile, fromLocale, toLocale);
                    }
                }
            }
        });
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return zipExplorerManager;
    }

    public void setBundles(File zipFile, Locale fromLocale, Locale toLocale) {
        Frame mainWindow = WindowManager.getDefault().getMainWindow();
        mainWindow.setTitle(NbBundle.getMessage(ZipExplorerTopComponent.class, "CTL_MainWindow_Title", fromLocale.getDisplayLanguage(), toLocale.getDisplayLanguage()));

        ZipArchive zipArchive = new ZipArchive(zipFile, fromLocale, toLocale);

        newZipRootNode = new ZipRootNode(zipArchive);
        zipExplorerManager.setRootContext(newZipRootNode);
        ((BeanTreeView) beanTreeView).setRootVisible(true);
        instanceContent.add(newZipRootNode);
        this.zipFile = zipArchive;
    }

    public ZipArchive getBundles() {
        return zipFile;
    }

    private Locale getLocaleFromString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String locale[] = (str + "__").split("_", 3);

        return new Locale(locale[0], locale[1], locale[2]);
    }
}
