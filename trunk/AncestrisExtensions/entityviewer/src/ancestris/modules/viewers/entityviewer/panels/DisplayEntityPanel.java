package ancestris.modules.viewers.entityviewer.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import javax.swing.tree.TreeSelectionModel;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author lemovice
 */
public class DisplayEntityPanel extends javax.swing.JPanel implements ExplorerManager.Provider {

    private ExplorerManager explorerManager = new ExplorerManager();
    private BeanTreeView beanTreeView = new BeanTreeView();

    /**
     * Creates new form DisplayEntityPanel
     */
    public DisplayEntityPanel() {
        initComponents();
        beanTreeView.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

        explorerManager.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(ExplorerManager.PROP_SELECTED_NODES)) {
                    setSelectedNodes(explorerManager.getSelectedNodes());
                }
            }
        });
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    /**
     * This method will be invoked whenever a node is selected in the
     * ExplorerManager.
     *
     * @param selectedNodes An array with the selected nodes.
     */
    private void setSelectedNodes(Node[] selectedNodes) {
        ArrayList<Node> newSelectedNodes = new ArrayList<Node>();
        System.out.println("------");
        for (Node node : selectedNodes) {
           System.out.println("node " + node.getDisplayName());
/*           newSelectedNodes.add(node);
            if (node.isLeaf() == false) {
                beanTreeView.expandNode(node);
                newSelectedNodes.addAll(Arrays.asList(node.getChildren().getNodes()));
            }
*/        }
        System.out.println("------");
/*        try {
            explorerManager.setSelectedNodes(newSelectedNodes.toArray(new Node[newSelectedNodes.size()]));
        } catch (PropertyVetoException ex) {
            Exceptions.printStackTrace(ex);
        }
*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = beanTreeView;

        setLayout(new java.awt.BorderLayout());
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the beanTreeView
     */
    public BeanTreeView getBeanTreeView() {
        return beanTreeView;
    }

    public JScrollBar getHorizontalScrollBar() {
        return jScrollPane1.getHorizontalScrollBar();
    }

    public JScrollBar getVerticalScrollBar() {
        return jScrollPane1.getVerticalScrollBar();
    }
}
