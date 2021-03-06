/**
 * GenJ - GenealogyJ
 *
 * Copyright (C) 1997 - 2002 Nils Meier <nils@meiers.net>
 *
 * This piece of code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package genj.tree;

import genj.util.Resources;
import ancestris.core.actions.AbstractAncestrisAction;
import genj.util.swing.ColorsWidget;
import genj.util.swing.FontChooser;
import genj.util.swing.NestedBlockLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The settings component for the Tree View
 */
//XXX: All these settings must be moved to preferences
public class TreeViewSettings extends JTabbedPane {
  
  private final static Resources RESOURCES = Resources.get(TreeViewSettings.class);

  /** members  */
  private final JSpinner[] spinners = new JSpinner[5]; 
  private final ColorsWidget colors;
  private final JCheckBox checkBending, checkAntialiasing, checkMarrSymbols;
  private final JComboBox<OnAction> jcAction;
  private final JCheckBox cbShowPopup;
  private final JCheckBox cbTreeAutoScroll;
  private AbstractAncestrisAction 
    up = new Move(-1), 
    down = new Move( 1), 
    delete =  new Delete(); 
  private final FontChooser font;
  private final Commit commit;
  private Bookmarks bookmarks;
  private JList bList;
  
  /**
   * Constructor
   * @param view
   */
  public TreeViewSettings(TreeView view) {
    
    commit = new Commit(view);
    
    // panel for checkbox options    
    JPanel options = new JPanel(new NestedBlockLayout(
        "<col>"+
         "<check gx=\"1\"/>"+
         "<row><label/><checkbox/></row>"+
         "<check gx=\"1\"/>"+
         "<check gx=\"1\"/>"+
         "<check gx=\"1\"/>"+
         "<check gx=\"1\"/>"+
         "<font gx=\"1\"/>"+
         "<row><label/><spinner/></row>"+
         "<row><label/><spinner/></row>"+
         "<row><label/><spinner/></row>"+
         "<row><label/><spinner/></row>"+
         "<row><label/><spinner/></row>"+
         "</col>"
     ));

    checkBending = createCheck("bend", view.getModel().isBendArcs());
    checkAntialiasing = createCheck("antialiasing", view.isAntialising());
    checkMarrSymbols = createCheck("marrsymbols", view.getModel().isMarrSymbols());

    jcAction = new JComboBox<OnAction>(OnAction.values());
    jcAction.setSelectedItem(TreeView.getOnAction());
    jcAction.addActionListener(commit);

    cbTreeAutoScroll = createCheck("autoscroll", TreeView.isAutoScroll());
    cbShowPopup = createCheck("showpopup", TreeView.showPopup());
    font = new FontChooser();
    font.setSelectedFont(view.getContentFont());
    font.addChangeListener(commit);
    
    options.add(cbShowPopup);
    options.add(new JLabel(RESOURCES.getString("tv.action")));
    options.add(jcAction);
    options.add(cbTreeAutoScroll);
    options.add(checkBending);
    options.add(checkAntialiasing);
    options.add(checkMarrSymbols);
    options.add(font);    

    TreeMetrics m = view.getModel().getMetrics();
    spinners[0] = createSpinner("indiwidth",  options, 0.4, m.wIndis*0.1D, 16.0);
    spinners[1] = createSpinner("indiheight", options, 0.4, m.hIndis*0.1D,16.0);
    spinners[2] = createSpinner("famwidth",   options, 0.4, m.wFams*0.1D, 16.0);
    spinners[3] = createSpinner("famheight",  options, 0.4, m.hFams*0.1D, 16.0);
    spinners[4] = createSpinner("padding",    options, 0.4, m.pad*0.1D, 4.0);

    // color chooser
    colors = new ColorsWidget();
    for (String key : view.getColors().keySet()) 
      colors.addColor(key, RESOURCES.getString("color."+key), view.getColors().get(key));
    colors.addChangeListener(commit);
    
    // bookmarks
    bookmarks = new Bookmarks(view.getModel().getBookmarks());
    bookmarks.addListDataListener(commit);
    
    bList = new JList(bookmarks);
    bList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    bList.getModel().addListDataListener(commit);
    bList.addListSelectionListener(new ListSelectionListener() {
      /** update buttons */
      @Override
      public void valueChanged(ListSelectionEvent e) {
        int 
          i = bList.getSelectedIndex(),
          n = bookmarks.getSize();
        up.setEnabled(i>0);
        down.setEnabled(i>=0&&i<n-1);
        delete.setEnabled(i>=0);
      }
    });
    
    JPanel bPanel = new JPanel(new NestedBlockLayout("<col><list wx=\"1\" wy=\"1\"/><row><up/><dn/><del/></row></col>"));
    bPanel.add(new JScrollPane(bList));
    bPanel.add(new JButton(up));
    bPanel.add(new JButton(down));
    bPanel.add(new JButton(delete));
    
    // add those tabs
    add(RESOURCES.getString("page.main")  , options);
    add(RESOURCES.getString("page.colors"), colors);
    add(RESOURCES.getString("page.bookmarks"), bPanel);

    // done
  }

  private JCheckBox createCheck(String key, boolean checked) {
    JCheckBox result = new JCheckBox(RESOURCES.getString(key), checked);
    result.setToolTipText(RESOURCES.getString(key+".tip",false));
    result.addActionListener(commit);
    return result;
  }
  
  /**
   * Create a spinner
   */
  private JSpinner createSpinner(String key, Container c, double min, double val,double max) {
    
    val = Math.min(max, Math.max(val, min));
    
    JSpinner result = new JSpinner(new SpinnerNumberModel(val, min, max, 0.1D));
    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(result, "##0.0");
    result.setEditor(editor);
    result.addChangeListener(editor);
    result.setToolTipText(RESOURCES.getString("info."+key+".tip"));
    result.addChangeListener(commit);
    
    c.add(new JLabel(RESOURCES.getString("info."+key)));
    c.add(result);
    
    // done
    return result;
  }
  
  private class Bookmarks extends AbstractListModel {
    
    private final ArrayList<Bookmark> list;
    
    Bookmarks(List<Bookmark> list) {
      this.list = new ArrayList<Bookmark>(list);
    }

    @Override
    public Object getElementAt(int index) {
      return list.get(index);
    }

    @Override
    public int getSize() {
      return list.size();
    }
    
    public void swap(int i, int j) {
      if (i==j)
        return;
      Bookmark b = list.get(i);
      list.set(i, list.get(j));
      list.set(j, b);
      fireContentsChanged(this, Math.min(i,j), Math.max(i,j));
    }

    public void delete(int i) {
      list.remove(i);
      fireIntervalRemoved(this, i, i);
    }

    public List<Bookmark> get() {
      return Collections.unmodifiableList(list);
    }
  }
  
  /**
   * Action - move a bookmark
   */
  private class Move extends AbstractAncestrisAction {
    /** by how much to move */
    private final int by;
    private Move(int how) {
      setText(RESOURCES.getString("bookmark.move."+how));
      setEnabled(false);
      by = how;
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      int i = bList.getSelectedIndex();
      bookmarks.swap(i, i+by);
      bList.setSelectedIndex(i+by);
    }
  } //ActionMove
  
  /**
   * Action - delete a bookmark
   */
  private class Delete extends AbstractAncestrisAction {
    private Delete() {
      setText(RESOURCES.getString("bookmark.del"));
      setEnabled(false);
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      int i = bList.getSelectedIndex();
      bookmarks.delete(i);
    }
  } //ActionDelete

  private class Commit implements ChangeListener, ActionListener, ListDataListener {
    
    private final TreeView view;
    
    private Commit(TreeView view) {
      this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      actionPerformed(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      // options
        TreeView.setOnAction((OnAction) jcAction.getSelectedItem());
        TreeView.setAutoScroll(cbTreeAutoScroll.isSelected());
        TreeView.setShowPopup(cbShowPopup.isSelected());
      view.getModel().setBendArcs(checkBending.isSelected());
      view.setAntialiasing(checkAntialiasing.isSelected());
      view.setContentFont(font.getSelectedFont());
      view.getModel().setMarrSymbols(checkMarrSymbols.isSelected());
      // metrics
      view.getModel().setMetrics(new TreeMetrics(
        (int)(((Double)spinners[0].getModel().getValue()).doubleValue()*10),
        (int)(((Double)spinners[1].getModel().getValue()).doubleValue()*10),
        (int)(((Double)spinners[2].getModel().getValue()).doubleValue()*10),
        (int)(((Double)spinners[3].getModel().getValue()).doubleValue()*10),
        (int)(((Double)spinners[4].getModel().getValue()).doubleValue()*10)
      ));
      // colors
      view.setColors(colors.getColors());
      // bookmarks
      view.getModel().setBookmarks(bookmarks.get());
      // done
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
      actionPerformed(null);
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
      actionPerformed(null);
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
      actionPerformed(null);
    }
  }

  /**
   * Enum for operation done on an action (ie double clic). Possible values are:
   * <li/>NONE: Nothing special. In fact the same as selection
   * <li/>CENTER: Centers tree view on this entity if possible (entity displayed in tree)
   * <li/>SETROOT: this entity becomes the new root for this tree view
   */
    public enum OnAction {

        NONE("tv.action.none"),
        CENTER("tv.action.center"),
        SETROOT("tv.action.setroot");
        private final String description;

        private OnAction(String desc) {
            description = RESOURCES.getString(desc);
        }

        @Override
        public String toString() {
            return description;
        }
    };
} //TreeViewSettings
