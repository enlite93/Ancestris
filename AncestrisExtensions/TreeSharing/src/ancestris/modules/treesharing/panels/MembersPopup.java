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
package ancestris.modules.treesharing.panels;

import ancestris.modules.treesharing.TreeSharingTopComponent;
import ancestris.modules.treesharing.communication.AncestrisMember;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class MembersPopup extends JPopupMenu implements TableModelListener {

    private final TreeSharingTopComponent owner;
    
    private final ImageIcon ALLOWED_ICON  = new ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/allowed.png"));
    private final ImageIcon MEMBER_ICON = new ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/friend16.png"));
    
    
    /**
     * Creates new form MembersPopup
     */
    public MembersPopup(TreeSharingTopComponent tstc, List<AncestrisMember> ancestrisMembers) {
        this.owner = tstc;
        initComponents();
        
        // Set Table
        setLayout(new BorderLayout());
        JTable table = new JTable(new MyTableModel(ancestrisMembers));
        
        // Sortable columns
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setToolTipText(NbBundle.getMessage(MembersPopup.class, "TIP_SortHeader"));
        
        // Editable Table
        table.getModel().addTableModelListener(this);
        
        // Resize first column
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        
        // Set tooltip text for name column (is string and does not loose its format)
        DefaultTableCellRenderer rendererCol1 = new DefaultTableCellRenderer();
        rendererCol1.setToolTipText(NbBundle.getMessage(MembersPopup.class, "TIP_Allowed"));
        table.getColumnModel().getColumn(1).setCellRenderer(rendererCol1);        

        // Remove grid lines
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        // Set header icons
        Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
        JLabel allowedLabel = new JLabel("", ALLOWED_ICON, JLabel.CENTER);
        allowedLabel.setBorder(headerBorder);
        JLabel nameLabel = new JLabel("", MEMBER_ICON, JLabel.CENTER);
        nameLabel.setBorder(headerBorder);
        TableCellRenderer renderer = new JComponentTableCellRenderer();
        table.getColumnModel().getColumn(0).setHeaderRenderer(renderer);
        table.getColumnModel().getColumn(1).setHeaderRenderer(renderer);
        table.getColumnModel().getColumn(0).setHeaderValue(allowedLabel);
        table.getColumnModel().getColumn(1).setHeaderValue(nameLabel);
        
        // Resize table based on its number of lines (max 15 lines)
        add(new JScrollPane(table));
        Dimension preferredSize = table.getPreferredSize();
        preferredSize.width += 30;
        preferredSize.height = table.getRowHeight()*15;
        //preferredSize.height += 30; // if all is to be displayed
        table.setPreferredScrollableViewportSize(preferredSize);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

        // TODO something with the data... (in case members is changed to allowed or not allowed, current matching ancestris friend has to be started or stopped)
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    class MyTableModel extends AbstractTableModel {

        String[] columnNames = { "", "" };
        Object[][] data;
        
        private MyTableModel(List<AncestrisMember> ancestrisMembers) {
            data = new Object[ancestrisMembers.size()][2];
            int i = 0;
            for (AncestrisMember member : ancestrisMembers) {
                data[i][0] = true;
                data[i][1] = member.getName();
                i++;
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();       }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }
    
    class JComponentTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            return (JComponent) value;
        }
    }
    
   
    
}