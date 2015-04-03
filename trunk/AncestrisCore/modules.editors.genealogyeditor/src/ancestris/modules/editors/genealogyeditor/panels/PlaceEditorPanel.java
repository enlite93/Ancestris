package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.api.place.Place;
import ancestris.modules.editors.genealogyeditor.models.GeonamePlacesListModel;
import ancestris.modules.place.geonames.GeonamesPlacesList;
import genj.gedcom.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.openide.util.*;

/**
 *
 * @author dominique
 */
public class PlaceEditorPanel extends javax.swing.JPanel {

    private final static int DEFAULT_LAT = 45; // same default as in Geo module, i.e. in the middle of the sea
    private final static int DEFAULT_LON = -4; // same default as in Geo module, i.e. in the middle of the sea
    private PropertyPlace mPlace;
    private final GeonamePlacesListModel geonamePlacesListModel = new GeonamePlacesListModel();
    private Property mAddress;
    private MapPopupMenu popupMenu;

    /**
     * Creates new form GedcomPlacesEditorPanel
     */
    public PlaceEditorPanel() {
        initComponents();
        jXMapKit1.setDataProviderCreditShown(true);
        jXMapKit1.getMainMap().setRecenterOnClickEnabled(true);
        jXMapKit1.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jXMapKit1.setMiniMapVisible(false);
        jXMapKit1.getZoomSlider().setValue(5);
        setMouseListener();
        setPopuMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorsTabbedPane = new javax.swing.JTabbedPane();
        placeEditorTabPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        gedcomPlaceEditorPanel = new ancestris.modules.editors.genealogyeditor.panels.GedcomPlaceEditorPanel();
        placeEditorTabbedPane = new javax.swing.JTabbedPane();
        mapPanel = new javax.swing.JPanel();
        MapScrollPane = new javax.swing.JScrollPane();
        jXMapKit1 = new org.jdesktop.swingx.JXMapKit();
        searchPlacePanel = new javax.swing.JPanel();
        searchPlaceTextField = new javax.swing.JTextField();
        searchPlaceButton = new javax.swing.JButton();
        geonamesScrollPane = new javax.swing.JScrollPane();
        geonamesPlacesList = new javax.swing.JList<String>();
        completePlaceButton = new javax.swing.JButton();
        replacePlaceButton = new javax.swing.JButton();
        sourceCitationsTablePanel = new ancestris.modules.editors.genealogyeditor.panels.SourceCitationsTablePanel();
        noteCitationsTablePanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsTablePanel();
        addressEditorTabPanel = new javax.swing.JPanel();
        addressEditorPanel = new ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel();

        setMinimumSize(new java.awt.Dimension(537, 414));

        placeEditorTabbedPane.setToolTipText(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "RightClicOnMap")); // NOI18N
        placeEditorTabbedPane.setMinimumSize(new java.awt.Dimension(513, 263));

        jXMapKit1.setToolTipText(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "RightClicOnMap")); // NOI18N
        MapScrollPane.setViewportView(jXMapKit1);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );

        placeEditorTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("PlaceEditorPanel.mapPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/map.png")), mapPanel); // NOI18N

        searchPlaceTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("PlaceEditorPanel.searchPlaceTextField.text_1"), new Object[] {})); // NOI18N
        searchPlaceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPlaceButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(searchPlaceButton, java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("PlaceEditorPanel.searchPlaceButton.text_1"), new Object[] {})); // NOI18N
        searchPlaceButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.searchPlaceButton.toolTipText")); // NOI18N
        searchPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPlaceButtonActionPerformed(evt);
            }
        });

        geonamesPlacesList.setModel(geonamePlacesListModel);
        geonamesPlacesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        geonamesPlacesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                geonamesPlacesListValueChanged(evt);
            }
        });
        geonamesScrollPane.setViewportView(geonamesPlacesList);

        org.openide.awt.Mnemonics.setLocalizedText(completePlaceButton, org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.completePlaceButton.text")); // NOI18N
        completePlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completePlaceButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(replacePlaceButton, org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.replacePlaceButton.text")); // NOI18N
        replacePlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replacePlaceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPlacePanelLayout = new javax.swing.GroupLayout(searchPlacePanel);
        searchPlacePanel.setLayout(searchPlacePanelLayout);
        searchPlacePanelLayout.setHorizontalGroup(
            searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPlacePanelLayout.createSequentialGroup()
                        .addComponent(searchPlaceTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPlaceButton))
                    .addComponent(geonamesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPlacePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(replacePlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(completePlaceButton)))
                .addContainerGap())
        );
        searchPlacePanelLayout.setVerticalGroup(
            searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPlaceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(geonamesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(replacePlaceButton)
                    .addComponent(completePlaceButton))
                .addContainerGap())
        );

        placeEditorTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("PlaceEditorPanel.searchPlacePanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Place.png")), searchPlacePanel); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gedcomPlaceEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(placeEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(gedcomPlaceEditorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placeEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N
        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.sourceCitationsTablePanel.TabConstraints.tabTitle"), sourceCitationsTablePanel); // NOI18N
        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.noteCitationsTablePanel.TabConstraints.tabTitle"), null, noteCitationsTablePanel, org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.noteCitationsTablePanel.TabConstraints.tabToolTip")); // NOI18N

        javax.swing.GroupLayout placeEditorTabPanelLayout = new javax.swing.GroupLayout(placeEditorTabPanel);
        placeEditorTabPanel.setLayout(placeEditorTabPanelLayout);
        placeEditorTabPanelLayout.setHorizontalGroup(
            placeEditorTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        placeEditorTabPanelLayout.setVerticalGroup(
            placeEditorTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        editorsTabbedPane.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.placeEditorTabPanel.TabConstraints.tabTitle"), placeEditorTabPanel); // NOI18N

        javax.swing.GroupLayout addressEditorTabPanelLayout = new javax.swing.GroupLayout(addressEditorTabPanel);
        addressEditorTabPanel.setLayout(addressEditorTabPanelLayout);
        addressEditorTabPanelLayout.setHorizontalGroup(
            addressEditorTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addressEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        addressEditorTabPanelLayout.setVerticalGroup(
            addressEditorTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addressEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );

        editorsTabbedPane.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.addressEditorTabPanel.TabConstraints.tabTitle"), addressEditorTabPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorsTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(editorsTabbedPane)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void replacePlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replacePlaceButtonActionPerformed
        if (geonamesPlacesList.getSelectedIndex() != -1) {
            Place place = geonamePlacesListModel.getPlaceAt(geonamesPlacesList.getSelectedIndex());
            gedcomPlaceEditorPanel.modify(place, false);
            jXMapKit1.setAddressLocation(new GeoPosition(place.getLatitude(), place.getLongitude()));
        }
    }//GEN-LAST:event_replacePlaceButtonActionPerformed

    private void completePlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completePlaceButtonActionPerformed
        if (geonamesPlacesList.getSelectedIndex() != -1) {
            Place place = geonamePlacesListModel.getPlaceAt(geonamesPlacesList.getSelectedIndex());
            gedcomPlaceEditorPanel.modify(place, true);
            jXMapKit1.setAddressLocation(new GeoPosition(place.getLatitude(), place.getLongitude()));
        }
    }//GEN-LAST:event_completePlaceButtonActionPerformed

    private void geonamesPlacesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_geonamesPlacesListValueChanged
        if (geonamesPlacesList.getSelectedIndex() != -1) {
            Place place = geonamePlacesListModel.getPlaceAt(geonamesPlacesList.getSelectedIndex());
            jXMapKit1.setAddressLocation(new GeoPosition(place.getLatitude(), place.getLongitude()));
        }
    }//GEN-LAST:event_geonamesPlacesListValueChanged

    private void searchPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPlaceButtonActionPerformed
        String searchedPlace = searchPlaceTextField.getText();

            if (searchedPlace.isEmpty() == false) {
                searchPlaceButton.setEnabled(false);
                geonamePlacesListModel.clear();
                GeonamesPlacesList geonamesPlacesList1 = new GeonamesPlacesList();
                geonamesPlacesList1.searchPlace(searchedPlace, geonamePlacesListModel);
                geonamesPlacesList1.getTask().addTaskListener(new TaskListener() {

                    @Override
                    public void taskFinished(Task task) {
                        searchPlaceButton.setEnabled(true);
                    }
                });
            }
    }//GEN-LAST:event_searchPlaceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MapScrollPane;
    private ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel addressEditorPanel;
    private javax.swing.JPanel addressEditorTabPanel;
    private javax.swing.JButton completePlaceButton;
    private javax.swing.JTabbedPane editorsTabbedPane;
    private ancestris.modules.editors.genealogyeditor.panels.GedcomPlaceEditorPanel gedcomPlaceEditorPanel;
    private javax.swing.JList<String> geonamesPlacesList;
    private javax.swing.JScrollPane geonamesScrollPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXMapKit jXMapKit1;
    private javax.swing.JPanel mapPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsTablePanel noteCitationsTablePanel;
    private javax.swing.JPanel placeEditorTabPanel;
    private javax.swing.JTabbedPane placeEditorTabbedPane;
    private javax.swing.JButton replacePlaceButton;
    private javax.swing.JButton searchPlaceButton;
    private javax.swing.JPanel searchPlacePanel;
    private javax.swing.JTextField searchPlaceTextField;
    private ancestris.modules.editors.genealogyeditor.panels.SourceCitationsTablePanel sourceCitationsTablePanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the place
     */
    public PropertyPlace get() {
        return mPlace;
    }

    /**
     * @param root
     * @param place the place to set
     * @param address
     */
    public void set(Property root, PropertyPlace place, Property address) {

        this.mPlace = place;
        this.mAddress = address;
        gedcomPlaceEditorPanel.set(root, mPlace);
        addressEditorPanel.set(root, mAddress);
        if (mPlace != null) {
            editorsTabbedPane.setSelectedComponent(placeEditorTabPanel);
            PropertyMap propMap = mPlace.getMap();
            double longitude = Double.NaN;
            double latitude = Double.NaN;

            
            if (propMap != null){
                longitude = propMap.getLongitude()==null?Double.NaN:propMap.getLongitude().getDoubleValue();
                latitude = propMap.getLatitude()==null?Double.NaN:propMap.getLatitude().getDoubleValue();
            }

            if (longitude != Double.NaN && latitude != Double.NaN) {
                // Center map on existing geo coordinates
                jXMapKit1.setAddressLocation(new GeoPosition(latitude, longitude));
                // Set search field in case user may want to search another location similar to the one existing, but stay on map tab
                searchPlaceTextField.setText(gedcomPlaceEditorPanel.getPlaceString().replaceAll(",", " ").replaceAll("\\s+", " "));
            } else {
                // Center map on a clearly non found place
                jXMapKit1.setAddressLocation(new GeoPosition(DEFAULT_LAT, DEFAULT_LON)); 
                // Be ready for the search
                searchPlaceTextField.setText(gedcomPlaceEditorPanel.getPlaceString().replaceAll(",", " ").replaceAll("\\s+", " "));
                // And display search tab
                placeEditorTabbedPane.setSelectedComponent(searchPlacePanel);
            }
            /*
             * +1 <<NOTE_STRUCTURE>>
             */
            noteCitationsTablePanel.set(mPlace, Arrays.asList(mPlace.getProperties("NOTE")));

            /*
             * +1 <<SOURCE_CITATION>>
             */
            sourceCitationsTablePanel.set(mPlace, Arrays.asList(mPlace.getProperties("SOUR")));
        } else if (mAddress != null) {
            editorsTabbedPane.setSelectedComponent(addressEditorTabPanel);
            placeEditorTabbedPane.setSelectedComponent(searchPlacePanel);
        } else {
            // Center map on a clearly non found place
            jXMapKit1.setAddressLocation(new GeoPosition(DEFAULT_LAT, DEFAULT_LON)); 
            editorsTabbedPane.setSelectedComponent(placeEditorTabPanel);
            placeEditorTabbedPane.setSelectedComponent(searchPlacePanel);
        }
    }

    public void commit() {
        addressEditorPanel.commit();
        gedcomPlaceEditorPanel.commit();
    }

    public void hideAddressPanel() {
        int index = editorsTabbedPane.indexOfComponent(addressEditorTabPanel);
        editorsTabbedPane.removeTabAt(index);
    }

    public void runSearch() {
        searchPlaceButton.doClick();
    }

    private void setPopuMenu() {
        popupMenu = new MapPopupMenu(jXMapKit1.getMainMap());
        popupMenu.add(new MapPopupAction("ACTION_MapCopyPoint", null, popupMenu));
    }

    private class MapPopupMenu extends JPopupMenu {

        private JMenu submenu = null;
        private JXMapViewer map = null;
        private Point point = new Point(0, 0);

        public MapPopupMenu(JXMapViewer map) {
            super();
            this.map = map;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        
        public GeoPosition getGeoPoint() {
            return map.convertPointToGeoPosition(point);
        }
    }

    private class MapPopupAction extends AbstractAction {

        private String actionName = "";
        private MapPopupMenu mpm = null;

        public MapPopupAction(String name, Object o, MapPopupMenu mpm) {
            this.actionName = name;
            this.mpm = mpm;
            putValue(NAME, NbBundle.getMessage(PlaceEditorPanel.class, name, o));
        }

        @Override
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent e) {
            if (actionName.equals("ACTION_MapCopyPoint")) {
                // rounds to 5 decimal places
                Double dLat = (double)Math.round(mpm.getGeoPoint().getLatitude() * 100000) / 100000;
                Double dLon = (double)Math.round(mpm.getGeoPoint().getLongitude() * 100000) / 100000;
                gedcomPlaceEditorPanel.modifyCoordinates(String.valueOf(dLat), String.valueOf(dLon), false);
                jXMapKit1.setAddressLocation(mpm.getGeoPoint());
            }
        }
    }
    
    private void setMouseListener() {
        MouseInputListener mia = new PlaceMouseInputListener();
        jXMapKit1.getMainMap().addMouseListener(mia);
        jXMapKit1.getMainMap().addMouseMotionListener(mia);
    }



    private class PlaceMouseInputListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                popupMenu.setPoint(e.getPoint());
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

}
