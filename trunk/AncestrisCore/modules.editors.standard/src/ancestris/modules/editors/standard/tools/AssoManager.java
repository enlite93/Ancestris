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

import genj.gedcom.Fam;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyForeignXRef;
import genj.util.Registry;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class AssoManager extends javax.swing.JPanel {

    private Registry registry = null;
    private Indi indi = null;
    private List<EventWrapper> eventSet = null;
    private int eventIndex = 0;
    
    // Association With indi and Of indi
    private List<AssoWrapper> assoWithSet = null;
    private List<AssoWrapper> assoOfSet = null;
    private AssoWithTableModel awtm = null;
    
    /**
     * Creates new form AssoManager
     */
    public AssoManager(Indi indi, List<EventWrapper> eventSet, int eventIndex, List<AssoWrapper> assoSet, AssoWrapper selectedAsso, JButton okButton, JButton cancelButton) {
        
        this.eventSet = eventSet;
        this.eventIndex = eventIndex;
        this.indi = indi;

        registry = Registry.get(getClass());
        initComponents();

        this.setPreferredSize(new Dimension(registry.get("assoWindowWidth", this.getPreferredSize().width), registry.get("assoWindowHeight", this.getPreferredSize().height)));
        assoSplitPanel.setDividerLocation(registry.get("assoSplitDividerLocation", assoSplitPanel.getDividerLocation()));

        // Get associations of both types
        assoWithSet = assoSet;
        assoOfSet = new ArrayList<AssoWrapper>();
        getAssociationOf(indi);
        
        // Titles
        assoWithIndiTitle.setText(NbBundle.getMessage(getClass(), "AssoManager.assoWithIndiTitle.text", getIndi()));
        assoOfIndoTitle.setText(NbBundle.getMessage(getClass(), "AssoManager.assoOfIndoTitle.text", getIndi()));
        
        //
        // assoWithTable:
        //
        
        // Build list
        awtm = new AssoWithTableModel(assoWithSet);
        assoWithTable.setModel(awtm);    
        assoWithTable.setAutoCreateRowSorter(true);
        
        // Resize columns
        FontMetrics fm = getFontMetrics(getFont());
        for (int i = 0; i < assoWithTable.getColumnCount(); i++) {
            assoWithTable.getColumnModel().getColumn(i).setPreferredWidth(awtm.getMaxWidth(fm, i));
        }
        
        // Resize table based on its number of lines
        Dimension preferredSize = assoWithTable.getPreferredSize();
        preferredSize.height = assoWithTable.getRowHeight() * awtm.getRowCount() + 1;
        //assoWithTable.setPreferredScrollableViewportSize(preferredSize);
        assoWithTable.setPreferredSize(preferredSize);
        assoWithTable.revalidate();
        assoWithTable.repaint();
        
        // Cell renderers
        assoWithTable.getColumnModel().getColumn(0).setCellRenderer(new IconTextCellRenderer());
        
        // Set event column as a combobox
        EventWrapper[] arrayEvents = eventSet.toArray(new EventWrapper[eventSet.size()]);
        Arrays.sort(arrayEvents, new Comparator() {
            public int compare(Object e1, Object e2) {
                String s1 = ((EventWrapper)e1).eventLabel.getLongLabel().toLowerCase();
                String s2 = ((EventWrapper)e2).eventLabel.getLongLabel().toLowerCase();
                return s1.compareTo(s2);
            }
        });
        JComboBox comboBoxEvents = new JComboBox(arrayEvents);
        ComboBoxEventsRenderer renderer = new ComboBoxEventsRenderer();
        comboBoxEvents.setRenderer(renderer);
        comboBoxEvents.setMaximumRowCount(10);
        assoWithTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBoxEvents));

        // Select appropriate association
        int row = 0;
        for (AssoWrapper asso : assoWithSet) {
            if (asso == selectedAsso) {
                assoWithTable.setRowSelectionInterval(row, row);
            }
            row++;
        }

        //
        // assoOfTable:
        //
        
        ////////////////////////// check displayEventTable in IndiPanel
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        assoSplitPanel = new javax.swing.JSplitPane();
        assoWithIndiPanel = new javax.swing.JPanel();
        assoWithIndiTitle = new javax.swing.JLabel();
        assoListScrollPane = new javax.swing.JScrollPane();
        assoWithTable = new javax.swing.JTable();
        addLineButton = new javax.swing.JButton();
        removeLineButton = new javax.swing.JButton();
        AssoOfIndiPanel = new javax.swing.JPanel();
        assoOfIndoTitle = new javax.swing.JLabel();
        relaListScrollPane = new javax.swing.JScrollPane();
        assoOfTable = new javax.swing.JTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        assoSplitPanel.setDividerLocation(275);
        assoSplitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        assoSplitPanel.setResizeWeight(0.5);
        assoSplitPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                assoSplitPanelPropertyChange(evt);
            }
        });

        assoWithIndiPanel.setPreferredSize(new java.awt.Dimension(468, 273));

        assoWithIndiTitle.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(assoWithIndiTitle, org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.assoWithIndiTitle.text")); // NOI18N

        assoWithTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        assoWithTable.setPreferredSize(new java.awt.Dimension(300, 10));
        assoWithTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        assoListScrollPane.setViewportView(assoWithTable);

        addLineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addLineButton, org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.addLineButton.text")); // NOI18N
        addLineButton.setToolTipText(org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.addLineButton.toolTipText")); // NOI18N
        addLineButton.setPreferredSize(new java.awt.Dimension(24, 24));

        removeLineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(removeLineButton, org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.removeLineButton.text")); // NOI18N
        removeLineButton.setToolTipText(org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.removeLineButton.toolTipText")); // NOI18N
        removeLineButton.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout assoWithIndiPanelLayout = new javax.swing.GroupLayout(assoWithIndiPanel);
        assoWithIndiPanel.setLayout(assoWithIndiPanelLayout);
        assoWithIndiPanelLayout.setHorizontalGroup(
            assoWithIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assoWithIndiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assoWithIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assoWithIndiPanelLayout.createSequentialGroup()
                        .addComponent(assoWithIndiTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(removeLineButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addLineButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(assoListScrollPane))
                .addContainerGap())
        );
        assoWithIndiPanelLayout.setVerticalGroup(
            assoWithIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assoWithIndiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assoWithIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(assoWithIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addLineButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removeLineButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(assoWithIndiTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assoListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        assoSplitPanel.setTopComponent(assoWithIndiPanel);

        AssoOfIndiPanel.setPreferredSize(new java.awt.Dimension(268, 160));

        assoOfIndoTitle.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(assoOfIndoTitle, org.openide.util.NbBundle.getMessage(AssoManager.class, "AssoManager.assoOfIndoTitle.text")); // NOI18N

        relaListScrollPane.setPreferredSize(new java.awt.Dimension(256, 175));

        assoOfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        relaListScrollPane.setViewportView(assoOfTable);

        javax.swing.GroupLayout AssoOfIndiPanelLayout = new javax.swing.GroupLayout(AssoOfIndiPanel);
        AssoOfIndiPanel.setLayout(AssoOfIndiPanelLayout);
        AssoOfIndiPanelLayout.setHorizontalGroup(
            AssoOfIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssoOfIndiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssoOfIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assoOfIndoTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(relaListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AssoOfIndiPanelLayout.setVerticalGroup(
            AssoOfIndiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssoOfIndiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assoOfIndoTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(relaListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        assoSplitPanel.setRightComponent(AssoOfIndiPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(assoSplitPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(assoSplitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        registry.put("assoWindowWidth", evt.getComponent().getWidth());
        registry.put("assoWindowHeight", evt.getComponent().getHeight());
    }//GEN-LAST:event_formComponentResized

    private void assoSplitPanelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_assoSplitPanelPropertyChange
        registry.put("assoSplitDividerLocation", assoSplitPanel.getDividerLocation());
    }//GEN-LAST:event_assoSplitPanelPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssoOfIndiPanel;
    private javax.swing.JButton addLineButton;
    private javax.swing.JScrollPane assoListScrollPane;
    private javax.swing.JLabel assoOfIndoTitle;
    private javax.swing.JTable assoOfTable;
    private javax.swing.JSplitPane assoSplitPanel;
    private javax.swing.JPanel assoWithIndiPanel;
    private javax.swing.JLabel assoWithIndiTitle;
    private javax.swing.JTable assoWithTable;
    private javax.swing.JScrollPane relaListScrollPane;
    private javax.swing.JButton removeLineButton;
    // End of variables declaration//GEN-END:variables


    private void getAssociationOf(Indi indi) {
        
    }

    public String getIndi() {
        return indi.toString();
    }

    
    
    
    
    
    
    
    
    
    
    
    

    private class IconTextCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                EventWrapper event = (EventWrapper) value;
                setIcon(event.eventLabel.getIcon());
                setText(event.eventLabel.getLongLabel());
            }
        return this;
        }
    }
    

    
    
    
    
    
    
    
    
    
    private class ComboBoxEventsRenderer extends JLabel implements ListCellRenderer {

        public ComboBoxEventsRenderer() {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                EventWrapper event = (EventWrapper) value;
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setIcon(event.eventLabel.getIcon());
                setText(event.eventLabel.getLongLabel());
            }
            return this;
        }
    }





}
