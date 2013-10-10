package ancestris.modules.editors.placeeditor;

import ancestris.api.place.Place;
import ancestris.modules.editors.placeeditor.models.GeonamePostalCodeTableModel;
import ancestris.place.geonames.GeonamesPlacesList;
import genj.gedcom.PropertyPlace;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class FindPlacePanel extends javax.swing.JPanel {

    GeonamePostalCodeTableModel placeTableModel = new GeonamePostalCodeTableModel();
    
    /**
     * Creates new form FindPlacePanel
     */
    public FindPlacePanel() {
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

        searchPanel = new JPanel();
        placeLabel = new JLabel();
        placeTextField = new JTextField();
        searchPlaceButton = new JButton();
        SearchResultPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setLayout(new BorderLayout());

        Mnemonics.setLocalizedText(placeLabel, NbBundle.getMessage(FindPlacePanel.class, "FindPlacePanel.placeLabel.text")); // NOI18N

        placeTextField.setText(NbBundle.getMessage(FindPlacePanel.class, "FindPlacePanel.placeTextField.text")); // NOI18N

        Mnemonics.setLocalizedText(searchPlaceButton, NbBundle.getMessage(FindPlacePanel.class, "FindPlacePanel.searchPlaceButton.text")); // NOI18N
        searchPlaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchPlaceButtonActionPerformed(evt);
            }
        });

        GroupLayout searchPanelLayout = new GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placeTextField, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPlaceButton)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(placeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPlaceButton)
                    .addComponent(placeLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(searchPanel, BorderLayout.PAGE_START);

        jTable1.setModel(placeTableModel);
        jScrollPane1.setViewportView(jTable1);

        GroupLayout SearchResultPanelLayout = new GroupLayout(SearchResultPanel);
        SearchResultPanel.setLayout(SearchResultPanelLayout);
        SearchResultPanelLayout.setHorizontalGroup(
            SearchResultPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(SearchResultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        SearchResultPanelLayout.setVerticalGroup(
            SearchResultPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(SearchResultPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(SearchResultPanel, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void searchPlaceButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_searchPlaceButtonActionPerformed
        List<Place> findPlaces = new GeonamesPlacesList().findPlace(new PropertyPlace(placeTextField.getText()));
        if (findPlaces != null) {
            placeTableModel.update(findPlaces);
        }
    }//GEN-LAST:event_searchPlaceButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel SearchResultPanel;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JLabel placeLabel;
    private JTextField placeTextField;
    private JPanel searchPanel;
    private JButton searchPlaceButton;
    // End of variables declaration//GEN-END:variables
}
