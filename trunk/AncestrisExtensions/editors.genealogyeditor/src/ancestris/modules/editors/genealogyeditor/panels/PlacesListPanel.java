package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.GedcomPlaceTableModel;
import ancestris.modules.gedcom.utilities.GedcomUtilities;
import genj.gedcom.Gedcom;
import genj.gedcom.PropertyPlace;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author dominique
 */
public class PlacesListPanel extends javax.swing.JPanel {

    String[] mPlaceFormat = null;
    private GedcomPlaceTableModel mPlacesListTableModel;
    private TableRowSorter<TableModel> mPlaceTableSorter;
    private Gedcom mGedcom = null;

    /**
     * Creates new form PlacesListPanel
     */
    public PlacesListPanel(final Gedcom gedcom) {
        this.mGedcom = gedcom;
        mPlaceFormat = PropertyPlace.getFormat(gedcom);

        mPlacesListTableModel = new GedcomPlaceTableModel(mPlaceFormat);
        initComponents();

        this.mGedcom = gedcom;
        List<PropertyPlace> gedcomPlacesList = GedcomUtilities.searchProperties(gedcom, PropertyPlace.class, GedcomUtilities.ENT_ALL);
        Map<String, Set<PropertyPlace>> placesMap = new HashMap<String, Set<PropertyPlace>>();
        for (PropertyPlace propertyPlace : gedcomPlacesList) {
            String gedcomPlace = propertyPlace.getDisplayValue();

            Set<PropertyPlace> propertySet = placesMap.get(gedcomPlace);
            if (propertySet == null) {
                propertySet = new HashSet<PropertyPlace>();
                placesMap.put(gedcomPlace, propertySet);
            }
            propertySet.add(propertyPlace);
        }

        mPlacesListTableModel.update(placesMap);
        mPlaceTableSorter = new TableRowSorter<TableModel>(placesListTable.getModel());
        placesListTable.setRowSorter(mPlaceTableSorter);
        placesListTable.setID(PlacesListPanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchPlaceLabel = new javax.swing.JLabel();
        filterGedcomPlaceTextField = new javax.swing.JTextField();
        searchPlaceComboBox = new javax.swing.JComboBox<String>();
        filterGedcomPlaceButton = new javax.swing.JButton();
        clearFilterGedcomPlaceButton = new javax.swing.JButton();
        placesListTableScrollPane = new javax.swing.JScrollPane();
        placesListTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable();

        searchPlaceLabel.setText(org.openide.util.NbBundle.getMessage(PlacesListPanel.class, "PlacesListPanel.searchPlaceLabel.text")); // NOI18N

        filterGedcomPlaceTextField.setText(org.openide.util.NbBundle.getMessage(PlacesListPanel.class, "PlacesListPanel.filterGedcomPlaceTextField.text")); // NOI18N
        filterGedcomPlaceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterGedcomPlaceButtonActionPerformed(evt);
            }
        });
        filterGedcomPlaceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filterGedcomPlaceTextFieldKeyTyped(evt);
            }
        });

        searchPlaceComboBox.setModel(new DefaultComboBoxModel<String>(mPlaceFormat));

        filterGedcomPlaceButton.setText(org.openide.util.NbBundle.getMessage(PlacesListPanel.class, "PlacesListPanel.filterGedcomPlaceButton.text")); // NOI18N
        filterGedcomPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterGedcomPlaceButtonActionPerformed(evt);
            }
        });

        clearFilterGedcomPlaceButton.setText(org.openide.util.NbBundle.getMessage(PlacesListPanel.class, "PlacesListPanel.clearFilterGedcomPlaceButton.text")); // NOI18N
        clearFilterGedcomPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilterGedcomPlaceButtonActionPerformed(evt);
            }
        });

        placesListTable.setModel(mPlacesListTableModel);
        placesListTableScrollPane.setViewportView(placesListTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchPlaceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPlaceComboBox, 0, 153, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterGedcomPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterGedcomPlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearFilterGedcomPlaceButton))
                    .addComponent(placesListTableScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPlaceLabel)
                    .addComponent(filterGedcomPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterGedcomPlaceButton)
                    .addComponent(clearFilterGedcomPlaceButton)
                    .addComponent(searchPlaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placesListTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterGedcomPlaceTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterGedcomPlaceTextFieldKeyTyped
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newFilter(filterGedcomPlaceTextField.getText());
        }
    }//GEN-LAST:event_filterGedcomPlaceTextFieldKeyTyped

    private void filterGedcomPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterGedcomPlaceButtonActionPerformed
        newFilter(filterGedcomPlaceTextField.getText());
    }//GEN-LAST:event_filterGedcomPlaceButtonActionPerformed

    private void clearFilterGedcomPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilterGedcomPlaceButtonActionPerformed
        filterGedcomPlaceTextField.setText("");
        newFilter(filterGedcomPlaceTextField.getText());
    }//GEN-LAST:event_clearFilterGedcomPlaceButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearFilterGedcomPlaceButton;
    private javax.swing.JButton filterGedcomPlaceButton;
    private javax.swing.JTextField filterGedcomPlaceTextField;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable placesListTable;
    private javax.swing.JScrollPane placesListTableScrollPane;
    private javax.swing.JComboBox<String> searchPlaceComboBox;
    private javax.swing.JLabel searchPlaceLabel;
    // End of variables declaration//GEN-END:variables

    private void newFilter(String filter) {
        RowFilter<TableModel, Integer> rf;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + filter, searchPlaceComboBox.getSelectedIndex());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }

        mPlaceTableSorter.setRowFilter(rf);
    }

    public PropertyPlace getSelectedPlace() {
        int selectedRow = placesListTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = placesListTable.convertRowIndexToModel(selectedRow);
            Set<PropertyPlace> valueAt = mPlacesListTableModel.getValueAt(rowIndex);
            return (PropertyPlace) mPlacesListTableModel.getValueAt(rowIndex).toArray()[0];
        } else {
            return null;
        }
    }
}
