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
package ancestris.modules.editors.standard.tools;

import static ancestris.modules.editors.standard.tools.Utils.getImageFromFile;
import static ancestris.modules.editors.standard.tools.Utils.getResizedIcon;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.Media;
import genj.gedcom.Property;
import genj.gedcom.PropertyFile;
import genj.util.Registry;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author frederic
 */
public class MediaChooser extends javax.swing.JPanel {

    private static int THUMB_WIDTH = 50;
    private static int THUMB_HEIGHT = 70;
    
    private Registry registry = null;
    private ThumbComparator thumbComparator = new ThumbComparator();
    private TreeSet<MediaThumb> allMedia = new TreeSet<MediaThumb>(thumbComparator);
    private DefaultListModel filteredModel = new DefaultListModel();
    
    private Image mainImage = null;
    private Image scaledImage = null;
    private String mainTitle = null;
    private JButton okButton = null;
    
    /**
     * Creates new form MediaChooser
     */
    public MediaChooser(Gedcom gedcom, Image image, String title, JButton okButton) {
        mainImage = image;
        mainTitle = title;
        this.okButton = okButton;
        createMediaThumbs((Collection<Media>) gedcom.getEntities(Gedcom.OBJE));
        
        registry = Registry.get(getClass());
        initComponents();
        this.setPreferredSize(new Dimension(registry.get("mediaWindowWidth", this.getPreferredSize().width), registry.get("mediaWindowHeight", this.getPreferredSize().height)));
        labelPhoto.setText("");
        displayIconAndTitle();
        mediaList.setCellRenderer(new ListEntryCellRenderer());
        if (mediaList.isSelectionEmpty()) {
            okButton.setEnabled(false);
        }
        textFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                filterModel(textFilter.getText());
            }
        });
    }


    private void displayIconAndTitle() {
        displayIconAndTitle(labelPhoto.getPreferredSize().width, labelPhoto.getPreferredSize().height);
    }
    
    private void displayIconAndTitle(int width, int height) {
        if (mainImage != null) {
            if (width< height) {
                scaledImage = mainImage.getScaledInstance(width, -1, Image.SCALE_DEFAULT);
            } else {
                scaledImage = mainImage.getScaledInstance(-1, height, Image.SCALE_DEFAULT);
            }
        }
        labelPhoto.repaint(); 
        photoTitle.setText("<html><center>" + mainTitle + "</center></html>");
        photoTitle.setPreferredSize(new Dimension(width, -1));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPhoto = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (scaledImage != null) {
                    ((Graphics2D) g).drawImage(scaledImage, 0 + ((getWidth() - scaledImage.getWidth(this)) / 2), ((getHeight() - scaledImage.getHeight(this)) / 2), null);
                    registry.put("mediaWindowWidth", getParent().getWidth());
                    registry.put("mediaWindowHeight", getParent().getHeight());
                }
            }

        };
        photoTitle = new javax.swing.JLabel();
        filterLabel = new javax.swing.JLabel();
        textFilter = new javax.swing.JTextField();
        jScrollPaneMedia = new javax.swing.JScrollPane();
        mediaList = new javax.swing.JList(filteredModel);

        labelPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(labelPhoto, org.openide.util.NbBundle.getMessage(MediaChooser.class, "MediaChooser.labelPhoto.text")); // NOI18N
        labelPhoto.setToolTipText(org.openide.util.NbBundle.getMessage(MediaChooser.class, "MediaChooser.labelPhoto.toolTipText")); // NOI18N
        labelPhoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelPhoto.setPreferredSize(new java.awt.Dimension(232, 352));
        labelPhoto.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                labelPhotoComponentResized(evt);
            }
        });

        photoTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(photoTitle, org.openide.util.NbBundle.getMessage(MediaChooser.class, "MediaChooser.photoTitle.text")); // NOI18N
        photoTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        photoTitle.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(0,5,10,5)));

        org.openide.awt.Mnemonics.setLocalizedText(filterLabel, org.openide.util.NbBundle.getMessage(MediaChooser.class, "MediaChooser.filterLabel.text")); // NOI18N

        textFilter.setText(org.openide.util.NbBundle.getMessage(MediaChooser.class, "MediaChooser.textFilter.text")); // NOI18N

        mediaList.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 10)); // NOI18N
        mediaList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mediaList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        mediaList.setVisibleRowCount(-1);
        mediaList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mediaListMouseClicked(evt);
            }
        });
        mediaList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                mediaListValueChanged(evt);
            }
        });
        jScrollPaneMedia.setViewportView(mediaList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(photoTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMedia)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filterLabel)
                            .addComponent(textFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneMedia))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(photoTitle)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mediaListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_mediaListValueChanged
        if (!mediaList.isSelectionEmpty()) {
            MediaThumb media = (MediaThumb) filteredModel.get(mediaList.getSelectedIndex());
            mainImage = media.getImage();
            mainTitle = media.title;
            displayIconAndTitle(labelPhoto.getWidth(), labelPhoto.getHeight());
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_mediaListValueChanged

    private void labelPhotoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_labelPhotoComponentResized
        displayIconAndTitle(labelPhoto.getWidth(), labelPhoto.getHeight());
    }//GEN-LAST:event_labelPhotoComponentResized

    private void mediaListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mediaListMouseClicked
        if (evt.getClickCount() == 2) {
            okButton.doClick();
        }
    }//GEN-LAST:event_mediaListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel filterLabel;
    private javax.swing.JScrollPane jScrollPaneMedia;
    private javax.swing.JLabel labelPhoto;
    private javax.swing.JList mediaList;
    private javax.swing.JLabel photoTitle;
    private javax.swing.JTextField textFilter;
    // End of variables declaration//GEN-END:variables

    
    
    
    private void createMediaThumbs(Collection<Media> entities) {
        // Get all media
        allMedia.clear();
        for (Media entity : entities) {
            File file = null;
            String title = "";
            Property mediaFile = entity.getProperty("FILE", true);
            if (mediaFile != null && mediaFile instanceof PropertyFile) {
                file = ((PropertyFile) mediaFile).getFile();
                Property mediaTitle = mediaFile.getProperty("TITL");
                if (mediaTitle != null) {
                    title = mediaTitle.getDisplayValue();
                }
            }
            MediaThumb media = new MediaThumb(entity, file, title);
            allMedia.add(media);
        }
        
        // Put them in model in sorted order
        filteredModel.clear();
        for (MediaThumb item : allMedia) {
            filteredModel.addElement(item);
        }
        
    }

    public Media getSelectedEntity() {
        MediaThumb media = (MediaThumb) filteredModel.get(mediaList.getSelectedIndex());
        return media == null ? null : media.entity;
    }


    
    public void filterModel(String filter) {
        mediaList.clearSelection();
        filteredModel.clear();
        for (MediaThumb item : allMedia) {
            if (item.title.contains(filter)) {
                filteredModel.addElement(item);
            }
        }
    }    
  
    
    
    private static class ListEntryCellRenderer extends JLabel implements ListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            MediaThumb entry = (MediaThumb) value;

            setHorizontalTextPosition(JLabel.CENTER);
            setVerticalTextPosition(JLabel.BOTTOM);
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.TOP);

            int labelWidth = THUMB_WIDTH + 30;
            int nbLines = getFontMetrics(getFont()).stringWidth(entry.title) / labelWidth + 3; // +1 for rounding and +2 again to compensate for average line breaks
            int labelHeight = THUMB_HEIGHT + 12 * nbLines;  // 12 pixels per line for font size 10 set in component netbeans parameters
            
            setPreferredSize(new Dimension(labelWidth, labelHeight));
            setText("<html><center>" + entry.title + "</center></html>");
            setIcon(entry.icon);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);

            return this;
        }
    }


    
    
    
    
    
    private class MediaThumb {
        
        public Media entity = null;
        public File file = null;
        public ImageIcon icon = null;
        public String title = "";
        
        public MediaThumb(Media entity, File file, String title) {
            this.entity = entity;
            this.file = file;
            this.icon = getResizedIcon(new ImageIcon(getImage()), THUMB_WIDTH, THUMB_HEIGHT);
            this.title = title;
        }
        
        public Image getImage() {
            return getImageFromFile(file);
        }
    }



    
    private class ThumbComparator implements Comparator<MediaThumb> {

        public int compare(MediaThumb o1, MediaThumb o2) {
            return o1.title.toLowerCase().compareTo(o2.title.toLowerCase());
        }
    }
    
}