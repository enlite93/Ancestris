/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2011 Ancestris
 * 
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.editors.standard;

import ancestris.api.editor.Editor;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.GedcomException;
import genj.view.ViewContext;
import java.awt.Component;
import org.openide.util.Exceptions;

public final class FamPanel extends Editor {

    private Context context;

    public FamPanel() {
        setOpaque(true);
        initComponents();
        marrEvent.addChangeListener(changes);
    }

    @Override
    public Component getEditorComponent() {
        return this;
    }
    private Fam fam;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        marrEvent = new ancestris.modules.beans.AEventBean();

        marrEvent.setRequestFocusEnabled(false);
        marrEvent.setShowKnown(true);
        marrEvent.setTag("MARR"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marrEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marrEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.beans.AEventBean marrEvent;
    // End of variables declaration//GEN-END:variables

    /**
     * Set the value of fam
     *
     * @param entity new value of indi
     */
    @Override
    protected void setContextImpl(Context context) {
        this.context = context;

        Entity entity = context.getEntity();
        if (entity == null) {
            return;
        }
        if (!(entity instanceof Fam)) {
            return;
        }
        this.fam = (Fam) entity;
        marrEvent.setContext(fam, null);
    }

    @Override
    public ViewContext getContext() {
        return new ViewContext(context);
    }

    @Override
    public void commit() {
        try {
            marrEvent.commit();
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    protected String getTitleImpl() {
        if (context == null || context.getEntity() == null) {
            return "";
        }
        return (new ViewContext(context.getEntity())).getText();
    }
}
