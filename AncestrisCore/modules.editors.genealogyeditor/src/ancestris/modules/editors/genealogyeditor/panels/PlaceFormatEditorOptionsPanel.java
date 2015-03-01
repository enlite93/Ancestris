package ancestris.modules.editors.genealogyeditor.panels;

import genj.gedcom.Gedcom;
import genj.gedcom.PropertyPlace;
import genj.util.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JComponent;
import org.openide.util.NbBundle;

/**
 * 
 * Logic for setting place format and place order : 
 * 1/ Place format 
 *      a/ taken from current gedcom header 
 *      b/ else from Ancestris User Preferences (GedcomOptions) 
 *      c/ else from Ancestris gedcom module bundle properties 
 * 2/ Sort Order 
 *      a/ taken from local previously stored preference (using this module) 
 *      b/ else from Ancestris User Preferences (GedcomOptions) 
 *      c/ else from this module bundle
 *
 * Default jurisdictions format in Ancestris options (option.placeFormat) :
 *          Hamlet,City,,Zip_Code,County,State,Country 
 * Predefined fields in panel :
 *          Hamlet,Parish,City,zipCode,geoID,County,State,Country 
 * => Default sortorder if none found : 
 *          0     ,-1    ,1   ,3      ,2    ,4     ,5    ,6
 *
 *
 * @author dominique & frederic
 */
public class PlaceFormatEditorOptionsPanel extends javax.swing.JPanel {

    public final static int HAMLET = 0;
    public final static int PARISH = 1;
    public final static int CITY = 2;
    public final static int ZIP_CODE = 3;
    public final static int GEO_ID = 4;
    public final static int COUNTY = 5;
    public final static int STATE = 6;
    public final static int COUNTRY = 7;
    public final static int MAX_JURISDICTIONS = 8;

    private Gedcom gedcom = null;
    private Registry registry = null;
    private String[] mComboPlaceFormat, mPlaceFormat;
    private int[] mPlaceSortOrder;

    /**
     * Creates new form PlaceFormatEditorOptionsPanel
     * 
     * @param gedcom
     */
    public PlaceFormatEditorOptionsPanel(Gedcom gedcom) {

        this.gedcom = gedcom;
        this.registry = gedcom.getRegistry();
    
        // Read place format from Gedcom, else Ancestris user preferences, else from gedcom bundle
        mPlaceFormat = PropertyPlace.getFormat(gedcom);
        if (mPlaceFormat == null || mPlaceFormat.length == 0) {
            mPlaceFormat = toJurisdictions(genj.gedcom.GedcomOptions.getInstance().getPlaceFormat());
        }
        
        // Build the list used for all combo boxes
        mComboPlaceFormat = new String[Math.min(MAX_JURISDICTIONS, mPlaceFormat.length) + 1];
        mComboPlaceFormat[0] = "";
        System.arraycopy(mPlaceFormat, 0, mComboPlaceFormat, 1, mPlaceFormat.length);

        // Read place sort order to intiate the mapping between known juridictions and those in the gedcom format
        mPlaceSortOrder = initPlaceSortOrder();
        if (isRegisteredPlaceSortOrder()) {
            mPlaceSortOrder = getRegisteredPlaceSortOrder();
        } 
                
        // Init panel
        initComponents();
        JComponent gedcomFields[] = {
            hamletComboBox,
            parishComboBox,
            cityComboBox,
            zipCodeComboBox,
            geoIDComboBox,
            countyComboBox,
            stateComboBox,
            countryComboBox
        };

        for (int index = 0; index < mPlaceSortOrder.length; index++) {
            if (mPlaceSortOrder[index] > -1 && mPlaceSortOrder[index] + 1 < mComboPlaceFormat.length) {
                ((javax.swing.JComboBox) gedcomFields[index]).setSelectedIndex(mPlaceSortOrder[index] + 1);
            } else {
                ((javax.swing.JComboBox) gedcomFields[index]).setSelectedIndex(0);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hamletLabel = new javax.swing.JLabel();
        parishLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        zipCodeLabel = new javax.swing.JLabel();
        geoIDLabel = new javax.swing.JLabel();
        countyLabel = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        countryLabel = new javax.swing.JLabel();
        hamletComboBox = new javax.swing.JComboBox<String>();
        cityComboBox = new javax.swing.JComboBox<String>();
        geoIDComboBox = new javax.swing.JComboBox<String>();
        stateComboBox = new javax.swing.JComboBox<String>();
        parishComboBox = new javax.swing.JComboBox<String>();
        zipCodeComboBox = new javax.swing.JComboBox<String>();
        countyComboBox = new javax.swing.JComboBox<String>();
        countryComboBox = new javax.swing.JComboBox<String>();

        hamletLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(hamletLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.hamletLabel.text")); // NOI18N

        parishLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(parishLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.parishLabel.text")); // NOI18N

        cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(cityLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.cityLabel.text")); // NOI18N

        zipCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(zipCodeLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.zipCodeLabel.text")); // NOI18N

        geoIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(geoIDLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.geoIDLabel.text")); // NOI18N

        countyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(countyLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.countyLabel.text")); // NOI18N

        stateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(stateLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.stateLabel.text")); // NOI18N

        countryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(countryLabel, org.openide.util.NbBundle.getMessage(PlaceFormatEditorOptionsPanel.class, "PlaceFormatEditorOptionsPanel.countryLabel.text")); // NOI18N

        hamletComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));
        hamletComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hamletComboBoxActionPerformed(evt);
            }
        });

        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        geoIDComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        parishComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        zipCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        countyComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        countryComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(mComboPlaceFormat));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hamletLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(parishLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zipCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geoIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(geoIDComboBox, 0, 176, Short.MAX_VALUE)
                    .addComponent(cityComboBox, 0, 176, Short.MAX_VALUE)
                    .addComponent(hamletComboBox, 0, 176, Short.MAX_VALUE)
                    .addComponent(parishComboBox, 0, 176, Short.MAX_VALUE)
                    .addComponent(zipCodeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countryComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hamletLabel)
                    .addComponent(hamletComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parishLabel)
                    .addComponent(parishComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zipCodeLabel)
                    .addComponent(zipCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(geoIDLabel)
                    .addComponent(geoIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateLabel)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countryLabel)
                    .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hamletComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hamletComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hamletComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cityComboBox;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JComboBox<String> countryComboBox;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JComboBox<String> countyComboBox;
    private javax.swing.JLabel countyLabel;
    private javax.swing.JComboBox<String> geoIDComboBox;
    private javax.swing.JLabel geoIDLabel;
    private javax.swing.JComboBox<String> hamletComboBox;
    private javax.swing.JLabel hamletLabel;
    private javax.swing.JComboBox<String> parishComboBox;
    private javax.swing.JLabel parishLabel;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JComboBox<String> zipCodeComboBox;
    private javax.swing.JLabel zipCodeLabel;
    // End of variables declaration//GEN-END:variables

    public int[] getPlaceSortOrder() {
        mPlaceSortOrder[0] = (Integer) hamletComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[1] = (Integer) parishComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[2] = (Integer) cityComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[3] = (Integer) zipCodeComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[4] = (Integer) geoIDComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[5] = (Integer) countyComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[6] = (Integer) stateComboBox.getSelectedIndex() - 1;
        mPlaceSortOrder[7] = (Integer) countryComboBox.getSelectedIndex() - 1;
        return mPlaceSortOrder;
    }

    public String[] getPlaceFormat() {
        return mPlaceFormat;
    }
    
    public void registerPlaceSortOrder() {
        registry.put("PLAC.hamlet.index", mPlaceSortOrder[0]);
        registry.put("PLAC.parish.index", mPlaceSortOrder[1]);
        registry.put("PLAC.city.index", mPlaceSortOrder[2]);
        registry.put("PLAC.zipCode.index", mPlaceSortOrder[3]);
        registry.put("PLAC.geoID.index", mPlaceSortOrder[4]);
        registry.put("PLAC.county.index", mPlaceSortOrder[5]);
        registry.put("PLAC.state.index", mPlaceSortOrder[6]);
        registry.put("PLAC.country.index", mPlaceSortOrder[7]);
    }
    
    public boolean isRegisteredPlaceSortOrder() {
        return registry.get("PLAC.hamlet.index", -2) != -2;
    }

    public int[] getRegisteredPlaceSortOrder() {
        mPlaceSortOrder[0] = registry.get("PLAC.hamlet.index", mPlaceSortOrder[0]);
        mPlaceSortOrder[1] = registry.get("PLAC.parish.index", mPlaceSortOrder[1]);
        mPlaceSortOrder[2] = registry.get("PLAC.city.index", mPlaceSortOrder[2]);
        mPlaceSortOrder[3] = registry.get("PLAC.zipCode.index", mPlaceSortOrder[3]);
        mPlaceSortOrder[4] = registry.get("PLAC.geoID.index", mPlaceSortOrder[4]);
        mPlaceSortOrder[5] = registry.get("PLAC.county.index", mPlaceSortOrder[5]);
        mPlaceSortOrder[6] = registry.get("PLAC.state.index", mPlaceSortOrder[6]);
        mPlaceSortOrder[7] = registry.get("PLAC.country.index", mPlaceSortOrder[7]);
        return mPlaceSortOrder;
    }
    
    private static String[] toJurisdictions(String value) {
        ArrayList<String> result = new ArrayList<String>(10);
        String lastToken = PropertyPlace.JURISDICTION_SEPARATOR;
        for (StringTokenizer tokens = new StringTokenizer(value, ",", true); tokens.hasMoreTokens();) {
            String token = tokens.nextToken();
            if (!PropertyPlace.JURISDICTION_SEPARATOR.equals(token)) {
                result.add(token);
            } else if (PropertyPlace.JURISDICTION_SEPARATOR.equals(lastToken)) {
                result.add("");
            }
            lastToken = token;
        }
        if (PropertyPlace.JURISDICTION_SEPARATOR.equals(lastToken)) {
            result.add("");
        }
        return result.toArray(new String[result.size()]);
    }

    private int[] initPlaceSortOrder() {
        String placeOrderString = gedcom.getPlaceSortOrder();
        if (placeOrderString == null || placeOrderString.trim().isEmpty()) {
            placeOrderString = NbBundle.getMessage(PlaceEditorPanel.class, "PlaceFormatEditorOptionsPanel.defaultSortOrder");
        }
        String[] placeOrderArray = toJurisdictions(placeOrderString);
        int[] results = new int[MAX_JURISDICTIONS];
        for (int i = 0; i < MAX_JURISDICTIONS; i++) {
            try {
                if (i < placeOrderArray.length) {
                    results[i] = Integer.parseInt(placeOrderArray[i]);
                } else {
                    results[i] = -1;
                }
                if (results[i] < -1) {
                    results[i] = -1;
                }
                if (results[i] >= MAX_JURISDICTIONS) {
                    results[i] = MAX_JURISDICTIONS - 1;
                }
            } catch (NumberFormatException nfe) {
            };
        }
        return results;
    }
}
