package ancestris.modules.console;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

final class ConsoleOptionsPanel extends javax.swing.JPanel {

    private final ConsoleOptionsPanelController controller;

    ConsoleOptionsPanel(ConsoleOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayConsoleCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(displayConsoleCheckBox, org.openide.util.NbBundle.getMessage(ConsoleOptionsPanel.class, "ConsoleOptionsPanel.displayConsoleCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(displayConsoleCheckBox)
                    .addContainerGap(144, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(displayConsoleCheckBox)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        Preferences modulePreferences = NbPreferences.forModule(Console.class);

        displayConsoleCheckBox.setSelected(modulePreferences.getBoolean("DisplayConsole", false));
    }

    void store() {
        Preferences modulePreferences = NbPreferences.forModule(Console.class);
        
        modulePreferences.putBoolean("DisplayConsole", displayConsoleCheckBox.isSelected());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox displayConsoleCheckBox;
    // End of variables declaration//GEN-END:variables
}