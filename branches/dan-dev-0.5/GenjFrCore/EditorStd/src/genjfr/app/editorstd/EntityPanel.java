/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EntityPanel.java
 *
 * Created on 4 oct. 2010, 23:52:02
 */
package genjfr.app.editorstd;

import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomListener;
import genj.gedcom.Indi;
import genj.gedcom.Media;
import genj.gedcom.Note;
import genj.gedcom.Property;
import genj.gedcom.Repository;
import genj.gedcom.Source;
import genj.gedcom.Submitter;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.openide.awt.UndoRedo.Manager;

/**
 *
 * @author frederic
 */
public class EntityPanel extends javax.swing.JPanel implements GedcomListener {

    private static SortedMap<String, EntityPanel> instances;

    /** Creates new form IndiPanel */
    public EntityPanel() {
    }

    public static synchronized EntityPanel findInstance(Entity entity) {
        if (instances == null) {
            instances = new TreeMap<String, EntityPanel>();
        }
        if (entity == null) {
            return null;
        }
        EntityPanel entPanel = instances.get(entity.getGedcom() + "." + entity.getTag());
        if (entPanel == null) {
            if (entity instanceof Indi) {
                entPanel = new IndiPanel(entity);
            } else if (entity instanceof Fam) {
                entPanel = new EntityPanel();
                //instance = new FamPanel(selectedEntity);
            } else if (entity instanceof Source) {
                entPanel = new EntityPanel();
                //instance = new SourcePanel(selectedEntity);
            } else if (entity instanceof Repository) {
                entPanel = new EntityPanel();
                //instance = new RepositoryPanel(selectedEntity);
            } else if (entity instanceof Note) {
                entPanel = new EntityPanel();
                //instance = new NotePanel(selectedEntity);
            } else if (entity instanceof Submitter) {
                entPanel = new SubmitterPanel(entity);
            } else if (entity instanceof Media) {
                entPanel = new EntityPanel();
                //instance = new MediaPanel(selectedEntity);
            } else {
                entPanel = new EntityPanel();
            }
            instances.put(entity.getGedcom() + "." + entity.getTag(), entPanel);
            entity.getGedcom().addGedcomListener(entPanel);
            entPanel.init();
        }
        return entPanel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void init() {
        return;
    }

    public void checkIfModified() {
        return;
    }

    public void loadEntity(Entity entity) {
        return;
    }

    public void displayEntity() {
        return;
    }

    public void saveEntity() {
        return;
    }

    public Entity getEntity() {
        return null;
    }

    @Override
    public void gedcomEntityAdded(Gedcom gedcom, Entity entity) {
        return;
    }

    @Override
    public void gedcomEntityDeleted(Gedcom gedcom, Entity entity) {
        return;
    }

    @Override
    public void gedcomPropertyChanged(Gedcom gedcom, Property property) {
        loadEntity(this.getEntity());
    }

    @Override
    public void gedcomPropertyAdded(Gedcom gedcom, Property property, int pos, Property added) {
        loadEntity(this.getEntity());
    }

    @Override
    public void gedcomPropertyDeleted(Gedcom gedcom, Property property, int pos, Property deleted) {
        loadEntity(this.getEntity());
    }

    public int getTabIndex(JTabbedPane tabPane, JPanel panel) {
        for (int i = 0; i < tabPane.getTabCount(); i++) {
            if (tabPane.getComponentAt(i) == panel) {
                return i;
            }
        }
        return 0;
    }

    void setManagers(Manager URmanager, EditorStdTopComponent editor) {
        return;
    }

}
