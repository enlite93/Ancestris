/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.tipoftheday;

/**
 *
 * @author lemovice
 */
public class ConfirmationCheckBox extends javax.swing.JPanel {

    /**
     * Creates new form ConfirmationCheckBox
     */
    public ConfirmationCheckBox(String text) {
        initComponents();
        jCheckBox1.setText(text);
    }

    /**
     * @return the jCheckBox1
     */
    public boolean isSelected() {
        return jCheckBox1.isSelected();
    }

    /**
     * @param jCheckBox1 the jCheckBox1 to set
     */
    public void setSelected(boolean selected) {
        jCheckBox1.setSelected(selected);
    }

    
    String getText() {
        return jCheckBox1.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBox1)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    // End of variables declaration//GEN-END:variables
}
