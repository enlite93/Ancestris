/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MediaPanel.java
 *
 * Created on 25 oct. 2010, 23:57:29
 */
package genjfr.app.editorstd.media;

import java.net.URL;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public class MediaPanel extends JPanel {

    private JPanel panelOn = null;
    private ImageViewer iv = null;
    private SoundPlayer sv = null;
    private VideoPlayer vv = null;

    public MediaPanel() {
        initComponents();
        //setLayout(new BorderLayout()); // use a BorderLayout
    }

    public void showMedia(MediaWrapper mediaWrapper) {
        switch (mediaWrapper.mediaType) {
            case MediaWrapper.PHOTO:
                if (iv == null) {
                    iv = new ImageViewer(mediaWrapper);
                } else {
                    iv.setImage(mediaWrapper);
                }
                setPanel(iv);
                break;
            case MediaWrapper.AUDIO:
                if (sv == null) {
                    sv = new SoundPlayer(mediaWrapper);
                }
                setPanel(sv);
                break;
            case MediaWrapper.VIDEO:
                if (vv == null) {
                    vv = new VideoPlayer(mediaWrapper);
                }
                setPanel(vv);
                break;
            default:
                break;
        }
    }

    private void setPanel(JPanel newPanel) {
        // Remove existing panel if any
        if (panelOn != null && panelOn != newPanel) {
            changeablePanel.remove(panelOn);
        }

        // Set new panel on (Netbeans requires this lenghty code below apparently)
        GroupLayout mainPanelLayout = new GroupLayout(changeablePanel);
        changeablePanel.setLayout(mainPanelLayout);
        mainPanelLayout.setAutoCreateContainerGaps(true);
        mainPanelLayout.setAutoCreateGaps(true);
        GroupLayout.SequentialGroup hGroup = mainPanelLayout.createSequentialGroup();
        hGroup.addComponent(newPanel);
        mainPanelLayout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = mainPanelLayout.createSequentialGroup();
        vGroup.addComponent(newPanel);
        mainPanelLayout.setVerticalGroup(vGroup);
        newPanel.setVisible(true);

        // Remember displayed panel
        panelOn = newPanel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changeablePanel = new javax.swing.JPanel();

        javax.swing.GroupLayout changeablePanelLayout = new javax.swing.GroupLayout(changeablePanel);
        changeablePanel.setLayout(changeablePanelLayout);
        changeablePanelLayout.setHorizontalGroup(
            changeablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        changeablePanelLayout.setVerticalGroup(
            changeablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changeablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changeablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel changeablePanel;
    // End of variables declaration//GEN-END:variables
}
