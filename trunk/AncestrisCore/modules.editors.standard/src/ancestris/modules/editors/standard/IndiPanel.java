package ancestris.modules.editors.standard;

import ancestris.api.editor.Editor;
import ancestris.util.TimingUtility;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.view.ViewContext;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

/**
 *
 * @author frederic
 */
public class IndiPanel extends Editor implements DocumentListener {

    private static final Logger LOG = Logger.getLogger("ancestris.editor.indi");
    
    private Context context;
    private Indi indi;
    
    
    /**
     * Creates new form IndiPanel
     */
    public IndiPanel() {
        initComponents();
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.jLabel1.text_1")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.jTextField1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.jLabel2.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.jTextField2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public ViewContext getContext() {
        return new ViewContext(context);
    }

    @Override
    public Component getEditorComponent() {
        return this;
    }

    @Override
    protected void setContextImpl(Context context) {
        LOG.log(Level.FINE, "{0}: setContextImpl().start", TimingUtility.geInstance().getTime());
        
        this.context = context;
        Entity entity = context.getEntity();
        if (entity == null || !(entity instanceof Indi)) {
            return;
        }
        this.indi = (Indi) entity;
        
        loadData();
        

        LOG.log(Level.FINE, "{0}: setContextImpl().finish", TimingUtility.geInstance().getTime());
    }

    
    @Override
    public void commit() throws GedcomException {
        saveData();
    }


    
    
    
    
    
    /**
     * Document listener methods
     */
    public void insertUpdate(DocumentEvent e) {
        changes.setChanged(true);
    }

    public void removeUpdate(DocumentEvent e) {
        changes.setChanged(true);
    }

    public void changedUpdate(DocumentEvent e) {
        changes.setChanged(true);
    }

    
    
    
    
    
    
    
    
    
    
    private void loadData() {
        jTextField1.setText(indi.getFirstName());
        jTextField1.getDocument().addDocumentListener(this);
        
        jTextField2.setText(indi.getLastName());
        jTextField2.getDocument().addDocumentListener(this);
        
    }

    
    private void saveData() {
        indi.setName(jTextField1.getText(), jTextField2.getText());
    }
}
