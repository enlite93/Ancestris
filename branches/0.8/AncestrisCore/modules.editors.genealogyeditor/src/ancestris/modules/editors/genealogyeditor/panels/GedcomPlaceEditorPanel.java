package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.api.place.Place;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.openide.DialogDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class GedcomPlaceEditorPanel extends javax.swing.JPanel {

    private final static int BEGINNING = 0;
    private final static Logger logger = Logger.getLogger(GedcomPlaceEditorPanel.class.getName(), null);
    private Property mRoot;
    private PropertyPlace mPlace;
    private int mPlaceOrder[];
    private String mPlaceFormat[];
    JComponent mGedcomFields[][];
    boolean mPlaceModified = false;
    boolean updateOnGoing = false;

    /**
     * Creates new form GedcomPlaceEditorPanel
     */
    public GedcomPlaceEditorPanel() {
        initComponents();
        mGedcomFields = new JComponent[][]{
            {gedcomHamletLabel, gedcomHamletTextField}, // hamlet
            {gedcomParishLabel, gedcomParishTextField}, // parish
            {gedcomCityLabel, gedcomCityTextField}, // city,
            {gedcomZipCodeLabel, gedcomZipCodeTextField},// zip Code
            {gedcomGeoIdLabel, gedcomGeoIDTextField}, // geo ID,
            {gedcomCountyLabel, gedcomCountyTextField}, // county,
            {gedcomStateLabel, gedcomStateTextField}, // state
            {gedcomCountryLabel, gedcomCountryTextField} // country
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gedcomLatitudeLabel = new javax.swing.JLabel();
        gedcomParishLabel = new javax.swing.JLabel();
        gedcomLongitudeTextField = new javax.swing.JTextField();
        gedcomCountyTextField = new javax.swing.JTextField();
        gedcomCityLabel = new javax.swing.JLabel();
        gedcomHamletLabel = new javax.swing.JLabel();
        gedcomLongitudeLabel = new javax.swing.JLabel();
        gedcomStateLabel = new javax.swing.JLabel();
        gedcomHamletTextField = new javax.swing.JTextField();
        gedcomGeoIDTextField = new javax.swing.JTextField();
        gedcomCountyLabel = new javax.swing.JLabel();
        gedcomGeoIdLabel = new javax.swing.JLabel();
        gedcomZipCodeTextField = new javax.swing.JTextField();
        gedcomLatitudeTextField = new javax.swing.JTextField();
        gedcomCityTextField = new javax.swing.JTextField();
        gedcomZipCodeLabel = new javax.swing.JLabel();
        gedcomCountryLabel = new javax.swing.JLabel();
        gedcomStateTextField = new javax.swing.JTextField();
        gedcomCountryTextField = new javax.swing.JTextField();
        gedcomParishTextField = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        parametersButton = new javax.swing.JButton();

        gedcomLatitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomLatitudeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.gedcomLatitudeLabel.text"), new Object[] {})); // NOI18N

        gedcomParishLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomParishLabel.setText("Parish"); // NOI18N

        gedcomLongitudeTextField.setColumns(16);
        gedcomLongitudeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCountyTextField.setColumns(16);
        gedcomCountyTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[5], gedcomCountyTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 6);
                    } else {
                        updatePlace(null, 6);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[5], gedcomCountyTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 6);
                    } else {
                        updatePlace(null, 6);
                    }
                }
            }
        });

        gedcomCityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCityLabel.setText("City"); // NOI18N

        gedcomHamletLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomHamletLabel.setText("Hamlet"); // NOI18N

        gedcomLongitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomLongitudeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.gedcomLongitudeLabel.text"), new Object[] {})); // NOI18N

        gedcomStateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomStateLabel.setText("State"); // NOI18N

        gedcomHamletTextField.setColumns(16);
        gedcomHamletTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[0], gedcomHamletTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 1);
                    } else {
                        updatePlace(null, 1);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[0], gedcomHamletTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 1);
                    } else {
                        updatePlace(null, 1);
                    }
                }
            }
        });

        gedcomGeoIDTextField.setColumns(16);
        gedcomGeoIDTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCountyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCountyLabel.setText("County"); // NOI18N

        gedcomGeoIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomGeoIdLabel.setText("Geo ID"); // NOI18N

        gedcomZipCodeTextField.setColumns(16);
        gedcomZipCodeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomLatitudeTextField.setColumns(16);
        gedcomLatitudeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCityTextField.setColumns(16);
        gedcomCityTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[2], gedcomCityTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 3);
                    } else {
                        updatePlace(null, 3);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[2], gedcomCityTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 3);
                    } else {
                        updatePlace(null, 3);
                    }
                }
            }
        });

        gedcomZipCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomZipCodeLabel.setText("Zip Code "); // NOI18N

        gedcomCountryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCountryLabel.setText("Country"); // NOI18N

        gedcomStateTextField.setColumns(16);
        gedcomStateTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[6], gedcomStateTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 7);
                    } else {
                        updatePlace(null, 7);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[6], gedcomStateTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 7);
                    } else {
                        updatePlace(null, 7);
                    }
                }
            }
        });

        gedcomCountryTextField.setColumns(16);
        gedcomCountryTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomParishTextField.setColumns(16);
        gedcomParishTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mPlaceModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(filler1);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "GedcomPlaceEditorPanel.jLabel1.text")); // NOI18N
        jToolBar1.add(jLabel1);

        parametersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/parameters.png"))); // NOI18N
        parametersButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.parametersButton.text"), new Object[] {})); // NOI18N
        parametersButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.parametersButton.toolTipText"), new Object[] {})); // NOI18N
        parametersButton.setFocusable(false);
        parametersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        parametersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        parametersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametersButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(parametersButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gedcomHamletLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomCityLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomCountyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomStateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomLatitudeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gedcomLatitudeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomStateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomCityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomCountyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gedcomHamletTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gedcomCountryLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomGeoIdLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomZipCodeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomParishLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomLongitudeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gedcomCountryTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomGeoIDTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomZipCodeTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomParishTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomLongitudeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gedcomParishLabel)
                        .addComponent(gedcomParishTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gedcomHamletLabel)
                        .addComponent(gedcomHamletTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomZipCodeLabel)
                    .addComponent(gedcomZipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCityLabel)
                    .addComponent(gedcomCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomGeoIdLabel)
                    .addComponent(gedcomGeoIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountryLabel)
                    .addComponent(gedcomStateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomStateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomLongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomLongitudeLabel)
                    .addComponent(gedcomLatitudeLabel)
                    .addComponent(gedcomLatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void parametersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametersButtonActionPerformed
        PlaceFormatEditorOptionsPanel gedcomPlaceFormatEditorPanel = new PlaceFormatEditorOptionsPanel(mRoot.getGedcom());
        mPlaceFormat = gedcomPlaceFormatEditorPanel.getPlaceFormat();
        mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceSortOrder();

        DialogManager.ADialog gedcomPlaceFormatEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.title"),
                gedcomPlaceFormatEditorPanel);
        gedcomPlaceFormatEditorDialog.setDialogId(PlaceFormatEditorOptionsPanel.class.getName());

        if (gedcomPlaceFormatEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceSortOrder();
            gedcomPlaceFormatEditorPanel.registerPlaceSortOrder();

            for (int index = 0; index < mPlaceOrder.length; index++) {
                if (mPlaceOrder[index] != -1) {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText(mPlaceFormat[mPlaceOrder[index]]);
                    if (mPlace != null && mPlace.getJurisdiction(mPlaceOrder[index]) != null) {
                        ((javax.swing.JTextField) (mGedcomFields[index][1])).setText(mPlace.getJurisdiction(mPlaceOrder[index]));
                        List<String> jurisdictions = Arrays.asList(mPlace.getAllJurisdictions(mPlaceOrder[index], true));
                        if (jurisdictions != null) {
                            AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], jurisdictions, false);
                        }
                    }
                    mGedcomFields[index][0].setVisible(true);
                    mGedcomFields[index][1].setVisible(true);
                } else {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                    ((javax.swing.JTextField) (mGedcomFields[index][1])).setText("");
                    mGedcomFields[index][0].setVisible(false);
                    mGedcomFields[index][1].setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_parametersButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel gedcomCityLabel;
    private javax.swing.JTextField gedcomCityTextField;
    private javax.swing.JLabel gedcomCountryLabel;
    private javax.swing.JTextField gedcomCountryTextField;
    private javax.swing.JLabel gedcomCountyLabel;
    private javax.swing.JTextField gedcomCountyTextField;
    private javax.swing.JTextField gedcomGeoIDTextField;
    private javax.swing.JLabel gedcomGeoIdLabel;
    private javax.swing.JLabel gedcomHamletLabel;
    private javax.swing.JTextField gedcomHamletTextField;
    private javax.swing.JLabel gedcomLatitudeLabel;
    private javax.swing.JTextField gedcomLatitudeTextField;
    private javax.swing.JLabel gedcomLongitudeLabel;
    private javax.swing.JTextField gedcomLongitudeTextField;
    private javax.swing.JLabel gedcomParishLabel;
    private javax.swing.JTextField gedcomParishTextField;
    private javax.swing.JLabel gedcomStateLabel;
    private javax.swing.JTextField gedcomStateTextField;
    private javax.swing.JLabel gedcomZipCodeLabel;
    private javax.swing.JTextField gedcomZipCodeTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton parametersButton;
    // End of variables declaration//GEN-END:variables

    /**
     * @param root
     * @param place the place to set
     */
    public void set(Property root, PropertyPlace place) {
        this.mPlace = place;
        this.mRoot = root;
        PlaceFormatEditorOptionsPanel gedcomPlaceFormatEditorPanel = new PlaceFormatEditorOptionsPanel(mRoot.getGedcom());
        mPlaceFormat = gedcomPlaceFormatEditorPanel.getPlaceFormat();
        mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceSortOrder();

        if (!gedcomPlaceFormatEditorPanel.isRegisteredPlaceSortOrder()) {
            DialogManager.ADialog gedcomPlaceFormatEditorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.title"),
                    gedcomPlaceFormatEditorPanel);
            gedcomPlaceFormatEditorDialog.setDialogId(PlaceFormatEditorOptionsPanel.class.getName());
            if (gedcomPlaceFormatEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceSortOrder();
                gedcomPlaceFormatEditorPanel.registerPlaceSortOrder();
            }
        } 
        
        for (int index = 0; index < mPlaceOrder.length; index++) {
            if (mPlaceOrder[index] != -1) {
                if (mPlaceOrder[index] < mPlaceFormat.length && mPlaceFormat[mPlaceOrder[index]] != null) {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText(mPlaceFormat[mPlaceOrder[index]]);
                    List<String> jurisdictions = Arrays.asList(PropertyPlace.getAllJurisdictions(mRoot.getGedcom(), mPlaceOrder[index], true));
                    if (jurisdictions != null) {
                        AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], jurisdictions, false);
                        InputMap map = ((javax.swing.JTextField) mGedcomFields[index][1]).getInputMap();
                        map.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), DefaultEditorKit.deletePrevCharAction);
                    } else {
                        AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], new <String>ArrayList(), false);
                        InputMap map = ((javax.swing.JTextField) mGedcomFields[index][1]).getInputMap();
                        map.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), DefaultEditorKit.deletePrevCharAction);
                    }
                mGedcomFields[index][0].setVisible(true);
                mGedcomFields[index][1].setVisible(true);
                }
            } else {
                ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                ((javax.swing.JTextField) (mGedcomFields[index][1])).setText("");
                mGedcomFields[index][0].setVisible(false);
                mGedcomFields[index][1].setVisible(false);
            }
        }

        updatePlace(mPlace, 0);

    }

    public void modify(Place place, boolean completePlace) {
        String[] jurisdictions = place.getJurisdictions();

        if (!completePlace || gedcomCityTextField.getText().isEmpty()) {
            gedcomCityTextField.setText(jurisdictions[0] != null ? jurisdictions[0] : ""); // City
        }
        if (!completePlace || gedcomZipCodeTextField.getText().isEmpty()) {
            gedcomZipCodeTextField.setText(jurisdictions[1] != null ? jurisdictions[1] : ""); // Postal code    
        }
        if (!completePlace || gedcomGeoIDTextField.getText().isEmpty()) {
            gedcomGeoIDTextField.setText(jurisdictions[2] != null ? jurisdictions[2] : ""); // GeoID
        }
        if (!completePlace || gedcomCountyTextField.getText().isEmpty()) {
            gedcomCountyTextField.setText(jurisdictions[3] != null ? jurisdictions[3] : ""); // County
        }
        if (!completePlace || gedcomStateTextField.getText().isEmpty()) {
            gedcomStateTextField.setText(jurisdictions[4] != null ? jurisdictions[4] : ""); // State
        }
        if (!completePlace || gedcomCountryTextField.getText().isEmpty()) {
            gedcomCountryTextField.setText(jurisdictions[5] != null ? jurisdictions[5] : ""); // Country
        }
        if (!completePlace || gedcomLatitudeTextField.getText().isEmpty()) {
            gedcomLatitudeTextField.setText(place.getLatitude().toString());
        }
        if (!completePlace || gedcomLongitudeTextField.getText().isEmpty()) {
            gedcomLongitudeTextField.setText(place.getLongitude().toString());
        }
    }

    public void modifyCoordinates(String lat, String lon, boolean completePlace) {
        if (!completePlace || gedcomLatitudeTextField.getText().isEmpty()) {
            gedcomLatitudeTextField.setText(lat);
        }
        if (!completePlace || gedcomLongitudeTextField.getText().isEmpty()) {
            gedcomLongitudeTextField.setText(lon);
        }
    }

    private void updatePlace(PropertyPlace place, int startIndex) {

        updateOnGoing = true;
        Property latitude = null;
        Property longitude = null;
        final String mapTAG;
        final String latitudeTAG;
        final String longitudeTAG;

        if (place != null) {
            logger.log(Level.INFO, "startIndex {0}", new Object[]{startIndex});

            for (int index = startIndex; index < mPlaceOrder.length; index++) {
                if (mPlaceOrder[index] != -1) {
                    if (mPlaceOrder[index] < mPlaceFormat.length) {
                        String jurisdiction = place.getJurisdiction(mPlaceOrder[index]);
                        ((javax.swing.JTextField) (mGedcomFields[index][1])).setText(jurisdiction != null ? jurisdiction : "");
                    }
                } else {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                }
            }

            if (place.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
                mapTAG = "MAP";
                latitudeTAG = "LATI";
                longitudeTAG = "LONG";
            } else {
                mapTAG = "_MAP";
                latitudeTAG = "_LATI";
                longitudeTAG = "_LONG";
            }

            Property map = place.getProperty(mapTAG);
            if (map != null) {
                latitude = map.getProperty(latitudeTAG);
                longitude = map.getProperty(longitudeTAG);
            }

            if (latitude != null && longitude != null) {
                gedcomLatitudeTextField.setText(latitude.getValue());
                gedcomLongitudeTextField.setText(longitude.getValue());
            } else {
                gedcomLatitudeTextField.setText("");
                gedcomLongitudeTextField.setText("");
            }
        } else {
            logger.log(Level.INFO, "No place found startIndex {0}", new Object[]{startIndex});

            for (int index = startIndex; index < mPlaceOrder.length; index++) {
                ((javax.swing.JTextField) (mGedcomFields[index][1])).setText("");
            }
            gedcomLatitudeTextField.setText("");
            gedcomLongitudeTextField.setText("");
        }
        updateOnGoing = false;
    }

    public String getPlaceString() {
        return getPlaceString(PlaceFormatEditorOptionsPanel.CITY);
    }

    public String getPlaceString(int startingFrom) {

        String placeString = "";

        javax.swing.JTextField gedcomFieldsOrder[] = new javax.swing.JTextField[mPlaceFormat.length];
        for (int placeOrderindex = startingFrom; placeOrderindex < mPlaceOrder.length; placeOrderindex++) {
            if (mPlaceOrder[placeOrderindex] != -1 && mPlaceOrder[placeOrderindex] < gedcomFieldsOrder.length) {
                gedcomFieldsOrder[mPlaceOrder[placeOrderindex]] = (javax.swing.JTextField) mGedcomFields[placeOrderindex][1];
            }
        }

        for (int index = 0; index < mPlaceFormat.length; index++) {
            if (index > 0) {
                placeString += PropertyPlace.JURISDICTION_SEPARATOR;
            }
            if (gedcomFieldsOrder[index] != null) {
                placeString += gedcomFieldsOrder[index].getText();
            }
        }

        return placeString;
    }

    public void commit() {
        if (mPlaceModified) {
            final String mapTAG;
            Property map;
            final String latitudeTAG;
            Property latitude;
            final String longitudeTAG;
            Property longitude;

            if (mPlace == null) {
                mPlace = (PropertyPlace) mRoot.addProperty(PropertyPlace.TAG, "");
            }
            mPlace.setValue(getPlaceString(BEGINNING));

            if (!gedcomLatitudeTextField.getText().isEmpty() && !gedcomLongitudeTextField.getText().isEmpty()) {
                if (mPlace.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
                    mapTAG = "MAP";
                    latitudeTAG = "LATI";
                    longitudeTAG = "LONG";
                } else {
                    mapTAG = "_MAP";
                    latitudeTAG = "_LATI";
                    longitudeTAG = "_LONG";
                }

                map = mPlace.getProperty(mapTAG);
                if (map != null) {
                    latitude = map.getProperty(latitudeTAG);
                    if (latitude == null) {
                        map.addProperty(latitudeTAG, gedcomLatitudeTextField.getText());
                    } else {
                        latitude.setValue(gedcomLatitudeTextField.getText());
                    }
                    longitude = map.getProperty(longitudeTAG);
                    if (longitude == null) {
                        map.addProperty(longitudeTAG, gedcomLongitudeTextField.getText());
                    } else {
                        longitude.setValue(gedcomLongitudeTextField.getText());
                    }
                } else {
                    map = mPlace.addProperty(mapTAG, "");
                    map.addProperty(latitudeTAG, gedcomLatitudeTextField.getText());
                    map.addProperty(longitudeTAG, gedcomLongitudeTextField.getText());
                }
            }
        }
    }
}
