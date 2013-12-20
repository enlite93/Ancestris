package ancestris.modules.editors.genealogyeditor.beans;

import ancestris.modules.editors.genealogyeditor.models.SexComboBoxModel;
import genj.gedcom.*;
import org.openide.util.Exceptions;

/**
 *
 * @author dominique
 */
public class SexBean extends javax.swing.JPanel {

    private SexComboBoxModel sexComboBoxModel = new SexComboBoxModel();
    private Property root;
    private PropertySex sex;
    private boolean sexModified = false;

    /**
     * Creates new form SexBean
     */
    public SexBean() {
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

        sexLabel = new javax.swing.JLabel();
        sexComboBox = new javax.swing.JComboBox<String>();

        sexLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/beans/Bundle").getString("SexBean.sexLabel.text"), new Object[] {})); // NOI18N

        sexComboBox.setModel(sexComboBoxModel);
        sexComboBox.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/beans/Bundle").getString("SexBean.sexComboBox.toolTipText"), new Object[] {})); // NOI18N
        sexComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(sexLabel)
                .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sexComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexComboBoxActionPerformed
        sexModified = true;
    }//GEN-LAST:event_sexComboBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> sexComboBox;
    private javax.swing.JLabel sexLabel;
    // End of variables declaration//GEN-END:variables

    public void set(Property root, PropertySex sex) {
        this.root = root;
        this.sex = sex;
        sexComboBox.setSelectedIndex(sex.getSex());
        sexModified = false;
    }

    public void commit() {
        if (sexModified) {
            try {
                root.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        if (sex == null) {
                            sex = (PropertySex) root.addProperty("SEX", "");
                        }
                        sex.setSex(sexComboBox.getSelectedIndex());
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
