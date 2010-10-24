/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MultimediaLinkBeanPanel.java
 *
 * Created on 23 oct. 2010, 16:24:23
 */
package genjfr.app.editorstd.beans;

import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.Media;
import genj.gedcom.Property;
import genj.gedcom.PropertyXRef;
import genjfr.app.editorstd.EditorStdTopComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.awt.UndoRedo.Manager;

/**
 *
 * @author frederic
 */
public class MultimediaLinkBeanPanel extends javax.swing.JPanel implements PropertyChangeListener, DocumentListener {

    private Entity[] mediaEntitiesList = new Entity[1];
    private Property[] mediaList = new Property[1];
    //
    private int index = 0;
    private String title = "";
    private Property parentProperty;
    public boolean isModified;
    private boolean isSetURmanager = false;
    private EditorStdTopComponent editor;

    /** Creates new form MultimediaLinkBeanPanel */
    public MultimediaLinkBeanPanel() {
        initComponents();
    }

    public void init(int index) {
        this.index = index;
        this.title = ((javax.swing.JTabbedPane) getParent()).getTitleAt(index);
//        addressStructure.addPropertyChangeListener(this);
//        // change listeners
//        address_line.getDocument().addDocumentListener(this);
//        address_line1.getDocument().addDocumentListener(this);
//        address_line2.getDocument().addDocumentListener(this);
//        address_city.getDocument().addDocumentListener(this);
//        address_state.getDocument().addDocumentListener(this);
//        address_postal_code.getDocument().addDocumentListener(this);
//        ((JTextComponent) address_country.getEditor().getEditorComponent()).getDocument().addDocumentListener(this);
//        phone_number1.getDocument().addDocumentListener(this);
//        phone_number2.getDocument().addDocumentListener(this);
//        phone_number3.getDocument().addDocumentListener(this);
        // reset modified flag
        setModified(false);
    }

    public void setProperties(Property parentProperty) {
        this.parentProperty = parentProperty;
        // set lists
        initEntitiesList();
        initMediaList();
        if (mediaListBox.getModel().getSize() > 0) {
            mediaListBox.setSelectedIndex(0);
        }
        enableFields(internalMediaButton.isSelected());
        // set the rest
//        addressStructure.setAddr((PropertyMultilineValue) (parentProperty.getProperty(AddressStructureBean.PROP_ADDR)));
//        if (addressStructure.getAddr() != null) {
//            addressStructure.setAddr1((PropertySimpleValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_ADDR1)));
//            addressStructure.setAddr2((PropertySimpleValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_ADDR2)));
//            addressStructure.setCity((PropertyChoiceValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_CITY)));
//            addressStructure.setStae((PropertyChoiceValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_STAE)));
//            addressStructure.setPost((PropertyChoiceValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_POST)));
//            addressStructure.setCtry((PropertyChoiceValue) (addressStructure.getAddr().getProperty(AddressStructureBean.PROP_CTRY)));
//        }
//        addressStructure.setPhon((Property[]) (parentProperty.getProperties(AddressStructureBean.PROP_PHON)));
        setModified(false);
    }

    public void displayProperties() {
        if (!editor.isBusy()) {
//            updateField(address_line, addressStructure.getAddr());
//            updateField(address_line1, addressStructure.getAddr1());
//            updateField(address_line2, addressStructure.getAddr2());
//            updateField(address_city, addressStructure.getCity());
//            updateField(address_state, addressStructure.getStae());
//            updateField(address_postal_code, addressStructure.getPost());
//            updateField(((JTextComponent) address_country.getEditor().getEditorComponent()), addressStructure.getCtry());
//            updateField(addressStructure.getPhon());
        }
    }

    public void saveProperties() {
//        save(parentProperty, addressStructure.getAddr(), AddressStructureBean.PROP_ADDR, address_line.getText());
//        if (addressStructure.getAddr() != null) {
//            save(addressStructure.getAddr(), addressStructure.getAddr1(), AddressStructureBean.PROP_ADDR1, address_line1.getText());
//            save(addressStructure.getAddr(), addressStructure.getAddr2(), AddressStructureBean.PROP_ADDR2, address_line2.getText());
//            save(addressStructure.getAddr(), addressStructure.getCity(), AddressStructureBean.PROP_CITY, address_city.getText());
//            save(addressStructure.getAddr(), addressStructure.getStae(), AddressStructureBean.PROP_STAE, address_state.getText());
//            save(addressStructure.getAddr(), addressStructure.getPost(), AddressStructureBean.PROP_POST, address_postal_code.getText());
//            save(addressStructure.getAddr(), addressStructure.getCtry(), AddressStructureBean.PROP_CTRY, ((JTextComponent) address_country.getEditor().getEditorComponent()).getText());
//        }
//        save(parentProperty, addressStructure.getPhon());
        setModified(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        multimediaLinkBean = new genjfr.app.editorstd.beans.MultimediaLinkBean();
        mediaTypeGroup = new javax.swing.ButtonGroup();
        addMediaButton = new javax.swing.JButton();
        removeMediaButton = new javax.swing.JButton();
        xref_obje = new javax.swing.JComboBox(mediaEntitiesList);
        jLabel2 = new javax.swing.JLabel();
        descriptive_title = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        multimedia_format = new javax.swing.JTextField();
        multimedia_file_reference = new javax.swing.JTextField();
        fileSearchButton = new javax.swing.JButton();
        mediaTabbedPane = new javax.swing.JTabbedPane();
        mediaLabel = new javax.swing.JLabel();
        noteStructureBeanPanel = new genjfr.app.editorstd.beans.NoteStructureBeanPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mediaListBox = new javax.swing.JList(mediaList);
        internalMediaButton = new javax.swing.JRadioButton();
        externalMediaButton = new javax.swing.JRadioButton();

        addMediaButton.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.addMediaButton.text")); // NOI18N
        addMediaButton.setMaximumSize(new java.awt.Dimension(29, 29));
        addMediaButton.setMinimumSize(new java.awt.Dimension(29, 29));
        addMediaButton.setPreferredSize(new java.awt.Dimension(29, 29));

        removeMediaButton.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.removeMediaButton.text")); // NOI18N
        removeMediaButton.setMaximumSize(new java.awt.Dimension(29, 29));
        removeMediaButton.setMinimumSize(new java.awt.Dimension(29, 29));
        removeMediaButton.setPreferredSize(new java.awt.Dimension(29, 29));

        xref_obje.setEditable(true);
        xref_obje.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                xref_objeItemStateChanged(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.jLabel2.text")); // NOI18N

        descriptive_title.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.descriptive_title.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.jLabel3.text")); // NOI18N

        multimedia_format.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.multimedia_format.text")); // NOI18N

        multimedia_file_reference.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.multimedia_file_reference.text")); // NOI18N

        fileSearchButton.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.fileSearchButton.text")); // NOI18N
        fileSearchButton.setMaximumSize(new java.awt.Dimension(29, 29));
        fileSearchButton.setMinimumSize(new java.awt.Dimension(29, 29));
        fileSearchButton.setPreferredSize(new java.awt.Dimension(29, 29));

        mediaLabel.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.mediaLabel.text")); // NOI18N
        mediaTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.mediaLabel.TabConstraints.tabTitle"), mediaLabel); // NOI18N
        mediaTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.noteStructureBeanPanel.TabConstraints.tabTitle"), noteStructureBeanPanel); // NOI18N

        jPanel1.setMinimumSize(new java.awt.Dimension(120, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(120, 314));

        mediaListBox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mediaListBox.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                mediaListBoxValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(mediaListBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        mediaTypeGroup.add(internalMediaButton);
        internalMediaButton.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.internalMediaButton.text")); // NOI18N
        internalMediaButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                internalMediaButtonItemStateChanged(evt);
            }
        });
        internalMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                internalMediaButtonActionPerformed(evt);
            }
        });

        mediaTypeGroup.add(externalMediaButton);
        externalMediaButton.setText(org.openide.util.NbBundle.getMessage(MultimediaLinkBeanPanel.class, "MultimediaLinkBeanPanel.externalMediaButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mediaTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(internalMediaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(externalMediaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(multimedia_file_reference, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(xref_obje, 0, 249, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(descriptive_title, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multimedia_format, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xref_obje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(internalMediaButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(externalMediaButton)
                                .addComponent(multimedia_file_reference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fileSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(descriptive_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(multimedia_format, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mediaTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mediaListBoxValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_mediaListBoxValueChanged
        if ((mediaListBox.getSelectedIndex() > -1) && (mediaListBox.getSelectedIndex() < mediaListBox.getModel().getSize())) {
            displayMedia(mediaList[mediaListBox.getSelectedIndex()]);
        }
    }//GEN-LAST:event_mediaListBoxValueChanged

    private void internalMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_internalMediaButtonActionPerformed
        enableFields(internalMediaButton.isSelected());
    }//GEN-LAST:event_internalMediaButtonActionPerformed

    private void xref_objeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_xref_objeItemStateChanged
        // Select item in list box if already there, clear selection otherwise
        Entity selectedEntity = (Entity) xref_obje.getSelectedItem();
        boolean found = false;
        for (int i = 0; i < mediaList.length; i++) {
            Property property = mediaList[i];
            if (property instanceof PropertyXRef) {
                PropertyXRef pRef = (PropertyXRef) property;
                Entity entity = pRef.getTargetEntity();
                if (entity instanceof Media && entity == selectedEntity) {
                    mediaListBox.setSelectedIndex(i);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            mediaListBox.clearSelection();
        }
        //
        displayProperties((Property) xref_obje.getSelectedItem());
    }//GEN-LAST:event_xref_objeItemStateChanged

    private void internalMediaButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_internalMediaButtonItemStateChanged
        enableFields(internalMediaButton.isSelected());
    }//GEN-LAST:event_internalMediaButtonItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMediaButton;
    private javax.swing.JTextField descriptive_title;
    private javax.swing.JRadioButton externalMediaButton;
    private javax.swing.JButton fileSearchButton;
    private javax.swing.JRadioButton internalMediaButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mediaLabel;
    private javax.swing.JList mediaListBox;
    private javax.swing.JTabbedPane mediaTabbedPane;
    private javax.swing.ButtonGroup mediaTypeGroup;
    private genjfr.app.editorstd.beans.MultimediaLinkBean multimediaLinkBean;
    private javax.swing.JTextField multimedia_file_reference;
    private javax.swing.JTextField multimedia_format;
    private genjfr.app.editorstd.beans.NoteStructureBeanPanel noteStructureBeanPanel;
    private javax.swing.JButton removeMediaButton;
    private javax.swing.JComboBox xref_obje;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!editor.isBusy()) {
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_ADDR)) {
//                updateField(address_line, addressStructure.getAddr());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_ADDR1)) {
//                updateField(address_line1, addressStructure.getAddr1());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_ADDR2)) {
//                updateField(address_line2, addressStructure.getAddr2());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_CITY)) {
//                updateField(address_city, addressStructure.getCity());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_STAE)) {
//                updateField(address_state, addressStructure.getStae());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_POST)) {
//                updateField(address_postal_code, addressStructure.getPost());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_CTRY)) {
//                updateField(((JTextComponent) address_country.getEditor().getEditorComponent()), addressStructure.getCtry());
//            }
//            if (evt.getPropertyName().equals(AddressStructureBean.PROP_PHON)) {
//                updateField(addressStructure.getPhon());
//            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!editor.isBusy()) {
            setModified(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (!editor.isBusy()) {
            setModified(true);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!editor.isBusy()) {
            setModified(true);
        }
    }

    private void setModified(boolean modified) {
        isModified = modified;
        ((javax.swing.JTabbedPane) getParent()).setTitleAt(index, modified ? title + "*" : title);
        if (editor != null) {
            editor.setModified(modified);
        }
    }

    public void setManagers(Manager URmanager, EditorStdTopComponent editor) {
        if (!isSetURmanager) {
            isSetURmanager = true;
            // change listeners
//            address_line.getDocument().addUndoableEditListener(URmanager);
//            address_line1.getDocument().addUndoableEditListener(URmanager);
//            address_line2.getDocument().addUndoableEditListener(URmanager);
//            address_city.getDocument().addUndoableEditListener(URmanager);
//            address_state.getDocument().addUndoableEditListener(URmanager);
//            address_postal_code.getDocument().addUndoableEditListener(URmanager);
//            ((JTextComponent) address_country.getEditor().getEditorComponent()).getDocument().addUndoableEditListener(URmanager);
//            phone_number1.getDocument().addUndoableEditListener(URmanager);
        }
        this.editor = editor;
    }

    private void initEntitiesList() {
        Gedcom gedcom = parentProperty.getGedcom();
        List<Entity> tempList = new ArrayList<Entity>();
        tempList.add(null);
        tempList.addAll(Arrays.asList(gedcom.getEntities(Gedcom.OBJE, "OBJE:TITL")));
        mediaEntitiesList = tempList.toArray(new Entity[1]);
        xref_obje.setModel(new DefaultComboBoxModel(mediaEntitiesList));
    }

    private void initMediaList() {
        mediaList = parentProperty.getProperties("OBJE");
        mediaListBox.setListData(mediaList);
        return;
    }

    private void displayMedia(Property selectedMedia) {
        if (selectedMedia instanceof PropertyXRef) {
            internalMediaButton.setSelected(true);
            PropertyXRef pRef = (PropertyXRef) selectedMedia;
            Entity entity = pRef.getTargetEntity();
            if (entity instanceof Media) {
                xref_obje.setSelectedItem((Media) entity);
                displayProperties((Property) entity);
            }
        } else {
            externalMediaButton.setSelected(true);
            displayProperties(selectedMedia);
        }
    }

    private void enableFields(boolean isInternal) {
        xref_obje.setEnabled(isInternal);
        if (!isInternal) {
            xref_obje.setSelectedIndex(0);
        }
        multimedia_file_reference.setEnabled(!isInternal);
        fileSearchButton.setEnabled(!isInternal);
        descriptive_title.setEditable(!isInternal);
        multimedia_format.setEditable(!isInternal);
    }

    private void displayProperties(Property selectedMedia) {
        if (selectedMedia == null) {
            return;
        }
        Property pTemp;
        pTemp = selectedMedia.getPropertyByPath("OBJE:TITL");
        descriptive_title.setText(pTemp != null ? pTemp.getDisplayValue() : "");
        pTemp = selectedMedia.getPropertyByPath("OBJE:FORM");
        multimedia_format.setText(pTemp != null ? pTemp.getDisplayValue() : "");
        pTemp = selectedMedia.getPropertyByPath("OBJE:FILE");
        multimedia_file_reference.setText(pTemp != null ? pTemp.getDisplayValue() : "");
        // afficher le media (image, son, video)
        pTemp = selectedMedia.getPropertyByPath("OBJE:TITL");
        mediaLabel.setText(pTemp != null ? pTemp.getDisplayValue() : "");

    }
}
