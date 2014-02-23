package ancestris.modules.editors.genealogyeditor.beans;

import genj.gedcom.Property;
import genj.gedcom.PropertyFile;
import genj.gedcom.PropertyMedia;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.openide.util.Exceptions;

/**
 *
 * @author dominique
 */
public class ImageBean extends javax.swing.JPanel {

    private BufferedImage resizedImage;
    private InputStream imageInputStream = null;

    /**
     * Creates new form ImageBean
     */
    public ImageBean() {
        super();
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

        setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/beans/Bundle").getString("ImageBean.toolTipText"), new Object[] {})); // NOI18N
        setMinimumSize(new java.awt.Dimension(30, 40));
        setPreferredSize(new java.awt.Dimension(150, 200));
        setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void setImage(Property multimediaObject) {
        Property file;

        if (multimediaObject != null) {
            if (multimediaObject instanceof PropertyMedia) {
                file = ((PropertyMedia) multimediaObject).getTargetEntity().getProperty("FILE", true);
            } else {
                file = multimediaObject.getProperty("FILE", true);
            }
            if (file != null && file instanceof PropertyFile && ((PropertyFile) file).getFile().exists()) {
                try {
                    imageInputStream = new FileInputStream(((PropertyFile) file).getFile());
                } catch (FileNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                    imageInputStream = ImageBean.class.getResourceAsStream("/ancestris/modules/editors/genealogyeditor/resources/indi_defaultimage.png");
                }
            } else {
                imageInputStream = ImageBean.class.getResourceAsStream("/ancestris/modules/editors/genealogyeditor/resources/indi_defaultimage.png");
            }
        } else {
            imageInputStream = ImageBean.class.getResourceAsStream("/ancestris/modules/editors/genealogyeditor/resources/indi_defaultimage.png");
        }

        try {
            BufferedImage loadImage = ImageIO.read(imageInputStream);
            resizedImage = resizeImage(loadImage, 90, 120);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
