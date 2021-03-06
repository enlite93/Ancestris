/**
 * GenJ - GenealogyJ
 *
 * Copyright (C) 1997 - 2010 Nils Meier <nils@meiers.net>
 *
 * This piece of code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package genj.tree;
//XXX: genj.tree is publically exported as plugin set a dependancy on TreeView
// We must remove this like (redesign DnD logic or write some Interface API)

import ancestris.awt.FilteredMouseAdapter;
import ancestris.core.actions.AbstractAncestrisAction;
import ancestris.core.actions.AbstractAncestrisContextAction;
import ancestris.core.actions.CommonActions;
import ancestris.core.pluginservice.AncestrisPlugin;
import ancestris.gedcom.GedcomDirectory;
import ancestris.gedcom.GedcomDirectory.ContextNotFoundException;
import ancestris.util.swing.DialogManager;
import ancestris.view.ExplorerHelper;
import ancestris.view.SelectionActionEvent;
import ancestris.view.SelectionDispatcher;
import ancestris.view.TemplateToolTip;
import genj.common.SelectEntityWidget;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyXRef;
import genj.io.Filter;
import genj.renderer.Blueprint;
import genj.renderer.BlueprintManager;
import genj.renderer.BlueprintRenderer;
import genj.renderer.ChooseBlueprintAction;
import genj.renderer.DPI;
import genj.renderer.RenderOptions;
import genj.renderer.RenderSelectionHintKey;
import genj.util.Registry;
import genj.util.Resources;
import genj.util.swing.ButtonHelper;
import genj.util.swing.ImageIcon;
import genj.util.swing.PopupWidget;
import genj.util.swing.ScrollPaneWidget;
import genj.util.swing.SliderWidget;
import genj.util.swing.UnitGraphics;
import genj.util.swing.ViewPortAdapter;
import genj.util.swing.ViewPortOverview;
import genj.view.ScreenshotAction;
import genj.view.SettingsAction;
import ancestris.swing.ToolBar;
import genj.view.View;
import genj.view.ViewContext;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolTip;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.awt.DropDownButtonFactory;
import static org.openide.awt.DropDownButtonFactory.createDropDownButton;
import org.openide.awt.DynamicMenuContent;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 * TreeView
 */
// FIXME: used to find proper TreeView component for RootAction
//@ServiceProvider(service=TreeView.class)
public class TreeView extends View implements Filter {

    private static final Logger LOG = Logger.getLogger("ancestris.tree");

    protected final static ImageIcon BOOKMARK_ICON = new ImageIcon(TreeView.class, "images/Bookmark");
    protected final static Registry REGISTRY = Registry.get(TreeView.class);
    protected final static Resources RESOURCES = Resources.get(TreeView.class);
    protected final static String TITLE = RESOURCES.getString("title");
    /** the units we use */
    private final Point2D DPMM;
    /** our model */
    private final Model model;
    /** our content */
    private final Content content;
    private final JScrollPane scroll;
    /** our overview */
    private final Overview overview;
    /** our content renderer */
    private final ContentRenderer contentRenderer;
    /** our current zoom */
    private double zoom = 1.0D;
    /** our current zoom */
    private SliderWidget sliderZoom;
    /** whether we use antialising */
    private boolean isAntialiasing = false;
    /** our colors */
    private Map<String, Color> colors = new HashMap<String, Color>();
    /** our blueprints */
    private final Map<String, String> tag2blueprint = new HashMap<String, String>();
    /** our renderers */
    private final Map<String, BlueprintRenderer> tag2renderer = new HashMap<String, BlueprintRenderer>();
    /** our content's font */
    private Font contentFont = new Font("SansSerif", 0, 10);
    /** current centered position */
    private final Point2D.Double center = new Point2D.Double(0, 0);
    /** current context */
    private Context context = new Context();
    private boolean ignoreContextChange = false;
    private final Sticky sticky = new Sticky();
    // Lookup listener for action callback
    private Lookup.Result<SelectionActionEvent> result;

    private final TemplateToolTip tt = new TemplateToolTip();
    private JLabel rootTitle;
    
    // set root menu
    private JButton rootMenu;

    // set goto menu
    private JButton gotoMenu;

    /**
     * Constructor
     */
    public TreeView() {

        // remember
        DPI dpi = RenderOptions.getInstance().getDPI();
        DPMM = new Point2D.Float(
                dpi.horizontal() / 2.54F / 10,
                dpi.vertical() / 2.54F / 10);

        // grab colors
        colors.put("background", Color.WHITE);
        colors.put("indis", Color.BLACK);
        colors.put("fams", Color.DARK_GRAY);
        colors.put("arcs", Color.BLUE);
        colors.put("selects", Color.RED);
        colors = REGISTRY.get("color", colors);

        // grab font
        contentFont = REGISTRY.get("font", contentFont);
        for (String tag : Gedcom.ENTITIES) {
            tag2blueprint.put(tag, REGISTRY.get("blueprint." + tag, ""));
        }

        // setup model
        model = new Model();
        model.setVertical(REGISTRY.get("vertical", true));
        model.setFamilies(REGISTRY.get("families", true));
        model.setBendArcs(REGISTRY.get("bend", true));
        model.setMarrSymbols(REGISTRY.get("marrs", true));
        TreeMetrics defm = model.getMetrics();
        model.setMetrics(new TreeMetrics(
                REGISTRY.get("windis", defm.wIndis),
                REGISTRY.get("hindis", defm.hIndis),
                REGISTRY.get("wfams", defm.wFams),
                REGISTRY.get("hfams", defm.hFams),
                REGISTRY.get("pad", defm.pad)));
        isAntialiasing = REGISTRY.get("antial", false);
        model.setHideAncestorsIDs(REGISTRY.get("hide.ancestors", new ArrayList<String>()));
        model.setHideDescendantsIDs(REGISTRY.get("hide.descendants", new ArrayList<String>()));

        // setup child components
        contentRenderer = new ContentRenderer();
        content = new Content();
        setExplorerHelper(new ExplorerHelper(content));
        scroll = new ScrollPaneWidget(new ViewPortAdapter(content));
        overview = new Overview(scroll);
        overview.setVisible(REGISTRY.get("overview", true));
        overview.setSize(REGISTRY.get("overview", new Dimension(160, 80)));
        zoom = Math.max(0.1, Math.min(1.0, REGISTRY.get("zoom", 1.0F)));

        // setup layout
        add(overview);
        add(scroll);
//FIXME: to be removed?
        // scroll to current
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Point center = getGedcom().getRegistry().get("tree.center", (Point) null);
                if (center != null){
                    scrollTo(center,true);
                } else {
                    scrollToCurrent(true);
                }
            }
        });
        // done
    }

    public Gedcom getGedcom() {
        if (context != null && context.getGedcom() != null) {
            return context.getGedcom();
        }
        return model.getRoot() == null ? null : model.getRoot().getGedcom();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // Used only for Filter interface
        AncestrisPlugin.register(this);
    }

    /**
     * @see javax.swing.JComponent#removeNotify()
     */
    @Override
    public void removeNotify() {
        AncestrisPlugin.unregister(this);
        // done
        super.removeNotify();
    }
    
    public void writeProperties(){
        // settings
        REGISTRY.put("overview", overview.isVisible());
        REGISTRY.put("overview", overview.getSize());
        REGISTRY.put("zoom", (float) zoom);
        REGISTRY.put("vertical", model.isVertical());
        REGISTRY.put("families", model.isFamilies());
        REGISTRY.put("bend", model.isBendArcs());
        REGISTRY.put("marrs", model.isMarrSymbols());
        TreeMetrics m = model.getMetrics();
        REGISTRY.put("windis", m.wIndis);
        REGISTRY.put("hindis", m.hIndis);
        REGISTRY.put("wfams", m.wFams);
        REGISTRY.put("hfams", m.hFams);
        REGISTRY.put("pad", m.pad);
        REGISTRY.put("antial", isAntialiasing);
        REGISTRY.put("font", contentFont);
        REGISTRY.put("color", colors);
        // blueprints
        for (String tag : tag2blueprint.keySet()) {
            REGISTRY.put("blueprint." + tag, getBlueprint(tag).getName());
        }

        // root    
        if (model.getRoot() != null) {
            Registry r = getGedcom().getRegistry();
            r.put("tree.root", model.getRoot().getId());
            // Center position
            r.put("tree.center", getCenter());
        }

        // stoppers
        REGISTRY.put("hide.ancestors", model.getHideAncestorsIDs());
        REGISTRY.put("hide.descendants", model.getHideDescendantsIDs());
        
    }

    // TreeView Preferences
    public static boolean isAutoScroll() {
        return REGISTRY.get("auto.scroll", true);
    }

    public static void setAutoScroll(boolean autoScroll) {
        REGISTRY.put("auto.scroll", autoScroll);
    }

    public static boolean showPopup() {
        return REGISTRY.get("show.popup", false);
    }

    public static void setShowPopup(boolean showPopup) {
        REGISTRY.put("show.popup", showPopup);
    }
    
  /**
   * option - behaviour on action event (double click)
   */
    public static TreeViewSettings.OnAction getOnAction(){
        return REGISTRY.get("on.action",TreeViewSettings.OnAction.SETROOT);
    }
  
    public static void setOnAction(TreeViewSettings.OnAction action){
        REGISTRY.put("on.action",action);
    }

    /**
     * @see java.awt.Container#doLayout()
     */
    @Override
    public void doLayout() {
        // layout components
        int w = getWidth(),
                h = getHeight();
        Component[] cs = getComponents();
        for (Component c : cs) {
            if (c == overview) {
                continue;
            }
            c.setBounds(0, 0, w, h);
        }
        // done
    }

    /**
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(480, 480);
    }

    /**
     * @see javax.swing.JComponent#isOptimizedDrawingEnabled()
     */
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return !overview.isVisible();
    }

    /**
     * Accessor - isAntialising.
     */
    public boolean isAntialising() {
        return isAntialiasing;
    }

    /**
     * Accessor - isAntialising.
     */
    public void setAntialiasing(boolean set) {
        if (isAntialiasing == set) {
            return;
        }
        isAntialiasing = set;
        repaint();
    }

    /**
     * Access - contentFont
     */
    public Font getContentFont() {
        return contentFont;
    }

    /**
     * Access - contentFont
     */
    public void setContentFont(Font set) {
        // change?
        if (set == null || contentFont.equals(set)) {
            return;
        }
        // remember
        contentFont = set;
        // show
        repaint();
    }

    public void setColors(Map<String, Color> set) {
        for (String key : colors.keySet()) {
            Color c = set.get(key);
            if (c != null) {
                colors.put(key, c);
            }
        }
        repaint();
    }

    public Map<String, Color> getColors() {
        return Collections.unmodifiableMap(colors);
    }

    /**
     * Access - Mode
     */
    Model getModel() {
        return model;
    }

    //XXX: we could probable install listners in gedcomdirectory
    private Lookup.Result<SelectionActionEvent> addLookupListener(Context context) {
        Lookup.Result<SelectionActionEvent> r;
        try {
            // Install action listener
            r = GedcomDirectory.getDefault().getDataObject(context).getLookup().lookupResult(SelectionActionEvent.class);
        } catch (ContextNotFoundException ex) {
            r = null;
        }
        final Lookup.Result<SelectionActionEvent> returnValue = r;
        if (returnValue != null) {
            returnValue.addLookupListener(new LookupListener() {

                @Override
                public void resultChanged(LookupEvent ev) {
                    // notify
                    //XXX: we must put selected nodes in global selection lookup (in fact use Explorer API)
                    for (SelectionActionEvent e : returnValue.allInstances()) {
                        if (e != null) {
                            if (e.isAction()) {
                                fireAction(e.getSource(), e.getContext());
                            } else if (isEventInMe(e.getSource())) {
                               setContext(e.getContext());
                            }
                        }
                    }
                }
            });
        }
        return returnValue;
    }

    private boolean isEventInMe(Object comp) {
        if (comp == null) {
            return false;
        }
        Component source = null;
        if (comp instanceof Component) {
            source = SwingUtilities.getAncestorOfClass(TreeView.class, (Component) comp);
        }
        return (source != null && source == this);
    }

    public void fireAction(Object comp, Context context) {
        if (context == null) {
            return;
        }
        // ignored?
        if (ignoreContextChange) {
            return;
        }
        if (sticky.isSelected()) {
            return;
        }

        Component source = null;
        if (comp != null && comp instanceof Component) {
            source = SwingUtilities.getAncestorOfClass(TreeView.class, (Component) comp);
        }
        if (source != null && source == this) {
            setRoot(context.getEntity());
        } else {
            switch(TreeView.getOnAction()){
                case NONE:
                    break;
                case SETROOT:
                    setRoot(context.getEntity());
                    break;
                case CENTER:
                    show(context.getEntity(), true);
                    break;
            }
        }
    }

    /**
     * view callback
     */
    @Override
    public void setContext(Context newContext) {
        if (newContext == null) {
            return;
        }
        // install action listener
        if (result == null) {
            result = addLookupListener(newContext);
        }
        if (context.getGedcom() != null && !newContext.getGedcom().equals(context.getGedcom())) {
            return;
        }
        // ignored?
        if (ignoreContextChange) {
            return;
        }
        if (sticky.isSelected()) {
            return;
        }

            setContextImpl(newContext);
    }

    private void setContextImpl(Context newContext) {
        context = new Context(newContext.getGedcom(), newContext.getEntities());

        // nothing we can show?
        if (context.getEntity() == null) {
            return;
        }

        show(context.getEntity());

        // done
    }

    /**
     * Set current entity
     */
    /* package */ public boolean show(Entity entity) {
        return show(entity, false);
    }

    /* package */ private boolean show(Entity entity, boolean forceCenter) {
        // allowed?
        if (!(entity instanceof Indi || entity instanceof Fam)) // FIXME: ne devrait-on pas plutot renvoyer false dans ce cas?
        {
            return true;
        }

        // Node for it?
        TreeNode node = model.getNode(entity);
        if (node == null) {
            return false;
        }

        // scroll
        scrollTo(node.pos, forceCenter);

        // make sure it's reflected
        content.repaint();
        overview.repaint();

        // done
        return true;
    }
    
//    /**
//     * Show current entity, show root if failed
//     */
//    /* package */ public boolean show(Entity entity, boolean fallbackRoot) {
//        // try to show
//        if (show(context.getEntity())) {
//            return true;
//        }
//        // otherwise try root
//        if (fallbackRoot) {
//            return show(getRoot());
//        }
//        return false;
//    }
//
    /**
     * Scroll to given position
     */
    private void scrollTo(Point2D p, boolean forceCenter) {
        if (forceCenter || isAutoScroll()) {
            // remember
            center.setLocation(p);
            // scroll
            Rectangle2D b = model.getBounds();
            Dimension d = getSize();
            Rectangle r = new Rectangle(
                    (int) ((p.getX() - b.getMinX()) * (DPMM.getX() * zoom)) - d.width / 2,
                    (int) ((p.getY() - b.getMinY()) * (DPMM.getY() * zoom)) - d.height / 2,
                    d.width,
                    d.height);
            content.scrollRectToVisible(r);
            // done
        }
    }

    private Point getCenter(){
        JViewport v = scroll.getViewport();
        return view2model(new Point(
                v.getViewPosition().x+v.getExtentSize().width/2,
                v.getViewPosition().y+v.getExtentSize().height/2));
    }
    /**
     * Scroll to current entity
     */
    private void scrollToCurrent() {
        scrollToCurrent(false);
    }

    /**
     * Scroll to current entity
     */
    private void scrollToCurrent(boolean force) {

        Entity current = context.getEntity();
        if (current == null) {
            return;
        }

        // Node for it?
        TreeNode node = model.getNode(current);
        if (node == null) {
            return;
        }

        // scroll
        scrollTo(node.pos, force);

        // done    
    }

    private void setZoom(double d) {
        Point centr = getCenter();
        zoom = Math.max(0.1D, Math.min(1.0, d));
        content.invalidate();
        if (isAutoScroll()){
            scrollToCurrent();
        } else {
            scrollTo(centr, true);
        }
        TreeView.this.validate();
        repaint();
    }

    /**
     * @see genj.view.ToolBarSupport#populate(JToolBar)
     */
    @Override
    public void populate(ToolBar toolbar) {

        // zooming!    
        sliderZoom = new SliderWidget(1, 100, (int) (zoom * 100));
        sliderZoom.addChangeListener(new ZoomGlue());
        sliderZoom.setAlignmentX(0F);
        sliderZoom.setOpaque(false);
        sliderZoom.setFocusable(false);
        toolbar.add(sliderZoom);

        // overview
        ButtonHelper bh = new ButtonHelper();
        toolbar.add(bh.create(new ActionOverview(), null, overview.isVisible()));

        // gap
        toolbar.addSeparator();

        // vertical/horizontal
        toolbar.add(bh.create(new ActionOrientation(), Images.imgVert, model.isVertical()));

        // families?
        toolbar.add(bh.create(new ActionFamsAndSpouses(), Images.imgDoFams, model.isFamilies()));

        // toggless?
        toolbar.add(bh.create(new ActionFoldSymbols(), null, model.isFoldSymbols()));

        // gap
        toolbar.addSeparator();

        // bookmarks
        PopupWidget pb = new PopupWidget("", BOOKMARK_ICON) {

            @Override
            public void showPopup() {
                removeItems();
                for (Bookmark bookmark : TreeView.this.model.getBookmarks()) {
                    addItem(new ActionGoto(bookmark));
                }
                // add items now
                super.showPopup();
            }
        };
        pb.setToolTipText(RESOURCES.getString("bookmark.tip"));
        pb.setOpaque(false);
        toolbar.add(pb);
        
        rootMenu = createDropDownButton(Images.imgView,null); 
        Action def = new ActionChooseRoot(rootMenu);
        rootMenu.putClientProperty(
                DropDownButtonFactory.PROP_DROP_DOWN_MENU,
                Utilities.actionsToPopup(new Action[]{def,new ActionRootContext(rootMenu)}, Lookup.EMPTY));
        rootMenu.setAction(def);
        
        gotoMenu = createDropDownButton(Images.imgGotoRoot, null);
        def = new ActionGotoRoot(gotoMenu);
        gotoMenu.putClientProperty(
                DropDownButtonFactory.PROP_DROP_DOWN_MENU,
                Utilities.actionsToPopup(new Action[]{def,new ActionGotoContext(gotoMenu)}, Lookup.EMPTY));
        gotoMenu.setAction(def);

        toolbar.add(gotoMenu);
        toolbar.add(rootMenu);
        toolbar.addSeparator();

        // settings
        toolbar.add(new ScreenshotAction(content));

        rootTitle = new JLabel();
        rootTitle.setHorizontalAlignment(SwingConstants.CENTER);
        toolbar.add(rootTitle, "growx, pushx, center");
        setRootTitle("");

        toolbar.addSeparator();
        toolbar.add(new ActionBluePrint());
        // sticky
        toolbar.add(new JToggleButton(sticky));

        toolbar.add(new Settings());
        toolbar.setFloatable(false);

        // done
    }

    private void setRootTitle(String title) {
        rootTitle.setText("<html><b>" + title + "</b></html");

    }

    /**
     * // XXX: we will have to check this API when we will deal wil global drag and
     * drop in all other componants
     *
     * Retreive entity at given cooodinates
     *
     * @param entityPos Point in TreeView coordinates
     *
     * @return entity over mouse pointer or null if there is no entity
     */
    public Entity getEntityAt(Point entityPos) {
        if (model == null) {
            return null;
        }

        // je recupere la position de Content / Treeview
        ViewPortAdapter va = (ViewPortAdapter) content.getParent();
        JViewport vp = (JViewport) va.getParent();
        Point viewPosition = vp.getViewPosition();
        // je recupere la position décalée de "content" due au centrage
        // qui n'est pas nul quand "content" est plus petit que viewport
        Point contentShift = content.getLocation();

        // je change de repere TreeView => Content
        Point entityContentPos = new Point();
        entityContentPos.x = entityPos.x + viewPosition.x - contentShift.x;
        entityContentPos.y = entityPos.y + viewPosition.y - contentShift.y;
        // je change de repere Content => model
        Point modelPos = view2model(entityContentPos);
        // je recherche l'entité a cette position dans le modele
        return model.getEntityAt(modelPos.x, modelPos.y);
    }

    public Entity getRoot() {
        if (model == null) {
            return null;
        }
        return model.getRoot();
    }

    /**
     * Sets the root of this view
     */
    public void setRoot(Entity root) {

        // save bookmarks
        Entity old = model.getRoot();
        if (old != null) {
            Gedcom gedcom = old.getGedcom();
            REGISTRY.put(gedcom.getName() + ".bookmarks", model.getBookmarks());
        }

        // switch root
        if (root == null || root instanceof Indi || root instanceof Fam) {
            model.setRoot(root);
            show(root, true);
            String title = root == null ? "" : root.toString(false);
            setRootTitle(title);
        }

        // load bookmarks
        if (root != null) {
            Gedcom gedcom = root.getGedcom();
            List<Bookmark> bookmarks = new ArrayList<Bookmark>();
            for (String b : REGISTRY.get(gedcom.getName() + ".bookmarks", new String[0])) {
                try {
                    bookmarks.add(new Bookmark(gedcom, b));
                } catch (Throwable t) {
                }
            }
            model.setBookmarks(bookmarks);
        }

    }

    /**
     * Translate a view position into a model position
     */
    private Point view2model(Point pos) {
        Rectangle bounds = model.getBounds();
        return new Point(
                (int) Math.rint(pos.x / (DPMM.getX() * zoom) + bounds.getMinX()),
                (int) Math.rint(pos.y / (DPMM.getY() * zoom) + bounds.getMinY()));
    }

    /**
     * Resolve a blueprint
     */
    /* package */ Blueprint getBlueprint(String tag) {
        return BlueprintManager.getInstance().getBlueprint(tag, tag2blueprint.get(tag));
    }

    /**
     * Resolve a renderer
     */
    private BlueprintRenderer getEntityRenderer(String tag) {
        BlueprintRenderer renderer = tag2renderer.get(tag);
        if (renderer == null) {
            renderer = new BlueprintRenderer(getBlueprint(tag));
            tag2renderer.put(tag, renderer);
        }
        return renderer;
    }

    /**
     * Overview
     */
    private class Overview extends ViewPortOverview implements ModelListener {

        /**
         * Constructor
         */
        private Overview(JScrollPane scroll) {
            super(scroll.getViewport());
            super.setSize(new Dimension(TreeView.this.getWidth() / 4, TreeView.this.getHeight() / 4));
        }

        @Override
        public void addNotify() {
            // cont
            super.addNotify();
            // listen to model events
            model.addListener(this);
        }

        @Override
        public void removeNotify() {
            model.removeListener(this);
            // cont
            super.removeNotify();
        }

        /**
         * @see java.awt.Component#setSize(int, int)
         */
        @Override
        public void setSize(int width, int height) {
            width = Math.max(32, width);
            height = Math.max(32, height);
            super.setSize(width, height);
        }

        /**
         * @see genj.util.swing.ViewPortOverview#paintContent(java.awt.Graphics, double, double)
         */
        @Override
        protected void renderContent(Graphics g, double zoomx, double zoomy) {

            // fill backgound
            g.setColor(Color.WHITE);
            Rectangle r = g.getClipBounds();
            g.fillRect(r.x, r.y, r.width, r.height);

            // go 2d
            UnitGraphics gw = new UnitGraphics(g, DPMM.getX() * zoomx * zoom, DPMM.getY() * zoomy * zoom);

            // init renderer
            contentRenderer.cIndiShape = Color.BLACK;
            contentRenderer.cFamShape = Color.BLACK;
            contentRenderer.cArcs = Color.LIGHT_GRAY;
            contentRenderer.cSelectedShape = Color.RED;
            contentRenderer.cRootShape = Color.GREEN;
            contentRenderer.selected = context.getEntities();
            contentRenderer.root = getRoot();
            contentRenderer.indiRenderer = null;
            contentRenderer.famRenderer = null;

            // let the renderer do its work
            contentRenderer.render(gw, model);

            // restore
            gw.popTransformation();

            // done  
        }

        /**
         * @see genj.tree.ModelListener#nodesChanged(genj.tree.Model, java.util.List)
         */
        @Override
        public void nodesChanged(Model arg0, Collection<TreeNode> arg1) {
            repaint();
        }

        /**
         * @see genj.tree.ModelListener#structureChanged(genj.tree.Model)
         */
        @Override
        public void structureChanged(Model arg0) {
            repaint();
        }
    } //Overview

    /**
     * The content we use for drawing
     */
    private class Content extends JComponent implements ModelListener {

        private transient FilteredMouseAdapter mouseAdapter;

        /**
         * Constructor
         */
        private Content() {
            putClientProperty("print.printable", Boolean.TRUE); // NOI18N
            // listen to mouse events
            mouseAdapter = new FilteredMouseAdapter() {

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    Content.this.mouseWheelMoved(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    Content.this.mousePressed(e);
                }

                @Override
                public void mouseClickedFiltered(MouseEvent me) {
                    Content.this.mouseClicked(me);
                }
            };
            addMouseListener(mouseAdapter);
            addMouseWheelListener(mouseAdapter);
//      setFocusable(true);
//      setRequestFocusEnabled(true);

            //XXX: actions in layer (registration)
//      new Up().install(this, "U", JComponent.WHEN_FOCUSED);
        }

        private class Up extends AbstractAncestrisAction {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up");
            }
        }

        @Override
        public void addNotify() {
            // cont
            super.addNotify();
            // listen to model events
            model.addListener(this);
            ToolTipManager.sharedInstance().registerComponent(this);
        }

        @Override
        public void removeNotify() {
            model.removeListener(this);
            // cont
            super.removeNotify();
            ToolTipManager.sharedInstance().unregisterComponent(this);
        }

        @Override
        public JToolTip createToolTip() {
            tt.setComponent(this);
            return tt;
        }

        /*
         * fake set tt text to let tooltip manager hide or show tt
         * the get ttlocation must return null if no entity can be found. if not tt show a blank component
         */
        private Entity oldTTEntity = null;

        @Override
        public String getToolTipText(MouseEvent event) {
            if (!showPopup()) {
                oldTTEntity = null;
                return null;
            }
            Entity entity = getEntityForEvent(event);
            if (entity != oldTTEntity) {
                ttPosition = null;
                oldTTEntity = entity;
            }
            tt.setEntity(oldTTEntity);
            if (oldTTEntity == null) {
                return null;
            } else {
                return oldTTEntity.getId();
            }
        }

        /**
         * Helper to find entity for a MouseEvent position in Content coordinate
         *
         * @param event
         *
         * @return Entity
         */
        private Entity getEntityForEvent(MouseEvent event) {
            // check node
            Entity entity = null;
            Point p = view2model(event.getPoint());
            Object content = model.getContentAt(p.x, p.y);
            // nothing?
            if (content != null && content instanceof Entity) {
                entity = (Entity) content;
            }
            return entity;
        }

        private Point ttPosition = null;

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            if (!showPopup() || oldTTEntity == null) {
                return null;
            }

            if (ttPosition == null) {
                ttPosition = new Point(event.getX() - 5, event.getY() + 2);
            }
            return ttPosition;
        }

        /**
         * @param e
         */
        public void mouseWheelMoved(MouseWheelEvent e) {

            // zoom
            if (e.isControlDown()) {
                sliderZoom.setValue(sliderZoom.getValue() - e.getWheelRotation() * 10);
                return;
            }

            // scroll
            JViewport viewport = (JViewport) getParent().getParent();
            Rectangle r = viewport.getVisibleRect();
            if (e.isShiftDown()) {
                r.x += e.getWheelRotation() * 32;
            } else {
                r.y += e.getWheelRotation() * 32;
            }
            viewport.scrollRectToVisible(r);

        }

        /**
         * @see genj.tree.ModelListener#structureChanged(Model)
         */
        @Override
        public void structureChanged(Model model) {
            // 20030403 dropped revalidate() here because it works
            // lazily - for scrolling to work the invalidate()/validate()
            // has to run synchronously and the component has to 
            // be layouted correctly. Then no intermediates are
            // painted and the scroll calculation is correct
            invalidate();
            TreeView.this.validate(); // note: call on parent
            // still shuffle a repaint
            repaint();
            // scrolling should work now
            scrollToCurrent();
        }

        /**
         * @see genj.tree.ModelListener#nodesChanged(Model, List)
         */
        @Override
        public void nodesChanged(Model model, Collection<TreeNode> nodes) {
            repaint();
        }

        /**
         * @see java.awt.Component#getPreferredSize()
         */
        @Override
        public Dimension getPreferredSize() {
            Rectangle2D bounds = model.getBounds();
            double w = bounds.getWidth() * (DPMM.getX() * zoom),
                    h = bounds.getHeight() * (DPMM.getY() * zoom);
            return new Dimension((int) w, (int) h);
        }

        /**
         * @see javax.swing.JComponent#paintComponent(Graphics)
         */
        @Override
        public void paint(Graphics g) {
            // fill backgound
            g.setColor(colors.get("background"));
            Rectangle r = g.getClipBounds();
            g.fillRect(r.x, r.y, r.width, r.height);
            // resolve our Graphics
            UnitGraphics gw = new UnitGraphics(g, DPMM.getX() * zoom, DPMM.getY() * zoom);
            gw.setAntialiasing(isAntialiasing);

            // render selection?
            Boolean selection = (Boolean) ((Graphics2D) g).getRenderingHint(RenderSelectionHintKey.KEY);
            if (selection == null) {
                selection = true;
            }

            // init renderer
            contentRenderer.font = contentFont;
            contentRenderer.cIndiShape = colors.get("indis");
            contentRenderer.cFamShape = colors.get("fams");
            contentRenderer.cArcs = colors.get("arcs");
            contentRenderer.cSelectedShape = colors.get("selects");
            contentRenderer.cRootShape = Color.GREEN;
            contentRenderer.selected = selection ? context.getEntities() : new ArrayList<Entity>();
            contentRenderer.root = getRoot();
            contentRenderer.indiRenderer = getEntityRenderer(Gedcom.INDI);
            contentRenderer.famRenderer = getEntityRenderer(Gedcom.FAM);
            // let the renderer do its work
            contentRenderer.render(gw, model);
            // done
        }

        /**
         * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
         */
        public void mousePressed(MouseEvent e) {
            requestFocusInWindow();
            // check node
            Point p = view2model(e.getPoint());
            Object content = model.getContentAt(p.x, p.y);
            // nothing?
            if (content == null) {
                repaint();
                overview.repaint();
            }
            if (content instanceof Entity) {
                Entity entity = (Entity) content;
                // change current!
                if ((e.getModifiers() & MouseEvent.CTRL_DOWN_MASK) != 0) {
                    List<Entity> entities = new ArrayList<Entity>(context.getEntities());
                    if (entities.contains(entity)) {
                        entities.remove(entity);
                    } else {
                        entities.add(entity);
                    }
                } else {
                    context = new Context(entity);
                }
//        repaint();
//        overview.repaint();
                // propagate to others
                try {
//          ignoreContextChange = true;
                    SelectionDispatcher.fireSelection(e, context);
                } finally {
//          ignoreContextChange = false;
                }
            }
            // done
        }

        /**
         * @see java.awt.event.MouseAdapter#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e) {
            requestFocusInWindow();
            // check node
            Point p = view2model(e.getPoint());
            Object content = model.getContentAt(p.x, p.y);
// FIXME: remove
//            // entity?
//            if (content instanceof Entity) {
//                Entity entity = (Entity) content;
//                // change current!
//                if ((e.getModifiers() & MouseEvent.CTRL_DOWN_MASK) != 0) {
//                    List<Entity> entities = new ArrayList<Entity>(context.getEntities());
//                    if (entities.contains(entity)) {
//                        entities.remove(entity);
//                    } else {
//                        entities.add(entity);
//                    }
//                } else {
//                    context = new Context(entity);
//                }
////        repaint();
////        overview.repaint();
//                // propagate to others
//                try {
////          ignoreContextChange = true;
//                    SelectionDispatcher.fireSelection(e, context);
//                } finally {
////          ignoreContextChange = false;
//                }
//                return;
//            }
            // runnable?
            if (content instanceof Runnable) {
                ((Runnable) content).run();
            }
        }
    } //Content

    /**
     * Glue for zooming
     */
    private class ZoomGlue implements ChangeListener {

        /**
         * @see javax.swing.event.ChangeListener#stateChanged(ChangeEvent)
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            setZoom(sliderZoom.getValue() * 0.01D);
        }
    } //ZoomGlue

    /**
     * Action for opening overview
     */
    private class ActionOverview extends AbstractAncestrisAction {

        /**
         * Constructor
         */
        private ActionOverview() {
            setImage(Images.imgOverview);
            setTip(RESOURCES.getString("overview.tip"));
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            overview.setVisible(!overview.isVisible());
        }
    } //ActionOverview    

    public Action getRootAction(Entity e, boolean b) {
        if (e instanceof Indi || e instanceof Fam) {
            return new ActionRoot(e, false);
        }
        return CommonActions.NOOP;
    }

    /**
     * ActionRoot
     */
    private class ActionRoot extends AbstractAncestrisAction {

        /** entity */
        private Entity root;

        /**
         * Constructor
         */
        private ActionRoot(Entity entity, boolean in) {
            putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
            root = entity;
            setText(RESOURCES.getString(in ? "root.in" : "root", TITLE));
            setImage(Images.imgView);
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            setRoot(root);
        }
    } //ActionRoot

    /**
     * Action Orientation change
     */
    private class ActionOrientation extends AbstractAncestrisAction {

        /**
         * Constructor
         */
        private ActionOrientation() {
            super.setImage(Images.imgHori);
            super.setTip(RESOURCES.getString("orientation.tip"));
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            model.setVertical(!model.isVertical());
            scrollToCurrent();
        }
    } //ActionOrientation

    /**
     * Action Families n Spouses
     */
    private class ActionFamsAndSpouses extends AbstractAncestrisAction {

        /**
         * Constructor
         */
        private ActionFamsAndSpouses() {
            super.setImage(Images.imgDontFams);
            super.setTip(RESOURCES.getString("families.tip"));
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            model.setFamilies(!model.isFamilies());
            scrollToCurrent();
        }
    } //ActionFamsAndSpouses

    /**
     * Action FoldSymbols on/off
     */
    private class ActionFoldSymbols extends AbstractAncestrisAction {

        /**
         * Constructor
         */
        private ActionFoldSymbols() {
            super.setImage(Images.imgFoldSymbols);
            super.setTip(RESOURCES.getString("foldsymbols.tip"));
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            model.setFoldSymbols(!model.isFoldSymbols());
            scrollToCurrent();
        }
    } //ActionFolding

    /**
     * Action - choose a root through dialog
     * set parent menu button action on this action 
     */
    // FIXME: should we implement this in AbstractAncestrisAction?
    private class ActionChooseRoot extends AbstractAncestrisAction {
        private JButton bMenu = null;

        /** constructor */
        private ActionChooseRoot() {
            this(null);
        }
        private ActionChooseRoot(JButton b) {
            setText(RESOURCES.getString("select.root"));
            setTip(RESOURCES.getString("select.root"));
            setImage(Images.imgView);
            bMenu = b;
        }

        /** do the choosin' */
        @Override
        public void actionPerformed(ActionEvent event) {

            // let the user choose an individual
            SelectEntityWidget select = new SelectEntityWidget(context.getGedcom(), Gedcom.INDI, null);
            Object rc = DialogManager.create(getText(), new JComponent[]{select})
                    .setOptionType(DialogManager.OK_CANCEL_OPTION)
                    .setDialogId("select.root")
                    .show();
            if (rc == DialogManager.OK_OPTION) {
                setRoot(select.getSelection());
            }
            if (bMenu.getAction() != this)
                bMenu.setAction( this );
            // done
        }
    } //ActionChooseRoot

    /**
     * Action to set root to current context 
     */
    private class ActionRootContext extends AbstractAncestrisContextAction {

        private Entity entity;
        private JButton bMenu = null;

        public ActionRootContext() {
            this(null);
        }
        public ActionRootContext(JButton b) {
            super();
            bMenu = b;
            Entity e = null;
            Context c = TreeView.this.context;
            if (c != null)
                e = c.getEntity();
            if (e == null)
                e = TreeView.this.getRoot();
            setTip(RESOURCES.getString("root.context",e==null?"":e.toString(false)));
            setText(RESOURCES.getString("root.context",e==null?"":e.toString(false)));
            setImage(Images.imgView);
        }

        @Override
        protected void contextChanged() {
            if (!contextProperties.isEmpty()) {
                Property prop = contextProperties.get(0);
                if (prop.getEntity() instanceof Indi || prop.getEntity() instanceof Fam) {
                    entity = prop.getEntity();
                } else if (entity == null) {
                    entity = prop.getGedcom().getFirstEntity(Gedcom.INDI);
                }
            }
            if (entity != null){
                setTip(RESOURCES.getString("root.context",entity==null?"":entity.toString(false)));
                setText(RESOURCES.getString("root.context",entity==null?"":entity.toString(false)));
            }
            super.contextChanged();
        }

        @Override
        protected void actionPerformedImpl(final ActionEvent event) {
            setRoot(entity);
            if (bMenu.getAction() != this)
                bMenu.setAction( this );
        }
    }

    /**
     * Action - recenter tree to root
     */
    private class ActionGotoRoot extends AbstractAncestrisAction {
        private JButton bMenu = null;
        
        /** constructor */
        private ActionGotoRoot() {
            this(null);
        }
        private ActionGotoRoot(JButton b) {
            bMenu = b;
            setTip(RESOURCES.getString("goto.root.tip"));
            setText(RESOURCES.getString("goto.root.tip"));
            setImage(Images.imgGotoRoot);
        }

        /** do the choosin' */
        @Override
        public void actionPerformed(ActionEvent event) {
            show(getRoot(), true);
            if (bMenu.getAction() != this)
                bMenu.setAction( this );
            // done
        }
    } //ActionChooseRoot

    private class ActionGoto extends AbstractAncestrisAction {

        private Bookmark bookmark;

        private ActionGoto(Bookmark bookmark) {
            this.bookmark = bookmark;
            // setup text
            setText(bookmark.getName());
            setImage(Gedcom.getEntityImage(bookmark.getEntity().getTag()));
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            // let everyone know
            Context newContext = new Context(bookmark.getEntity());

            try {
                ignoreContextChange = true;
                SelectionDispatcher.fireSelection(newContext);
            } finally {
                ignoreContextChange = false;
            }
            setRoot(bookmark.getEntity());
            setContextImpl(newContext);
        }
    }

    public Action getBookmarkAction(Entity e, boolean local) {
        if (e instanceof Indi || e instanceof Fam) {
            return new ActionBookmark(e, local);
        }
        return CommonActions.NOOP;
    }

    /**
     * Action - bookmark something
     */
    private class ActionBookmark extends AbstractAncestrisAction {

        /** the entity */
        private Entity entity;

        /**
         * Constructor
         */
        private ActionBookmark(Entity e, boolean local) {
            putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
            entity = e;
            setImage(BOOKMARK_ICON);
            if (local) {
                setText(RESOURCES.getString("bookmark.add"));
            } else {
                setText(RESOURCES.getString("bookmark.in", TITLE));
            }
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {

            // calculate a name
            String name = "";
            if (entity instanceof Indi) {
                name = ((Indi) entity).getName();
            }
            if (entity instanceof Fam) {
                Indi husb = ((Fam) entity).getHusband();
                Indi wife = ((Fam) entity).getWife();
                if (husb == null && wife == null) {
                    name = entity.getId();
                } else {
                    name = (husb == null ? "" : husb.getName()) + " & " + (wife == null ? "" : wife.getName());
                }
            }

            // Ask for name of bookmark
            name = DialogManager.create(
                    TITLE, RESOURCES.getString("bookmark.name"), name)
                    .show();

            if (name == null) {
                return;
            }

            // create it
            model.addBookmark(new Bookmark(name, entity));

            // save bookmarks
            {
                Entity root = model.getRoot();
                if (root != null) {
                    REGISTRY.put(root.getGedcom().getName() + ".bookmarks", model.getBookmarks());
                }
            }

            // done
        }
    } //ActionBookmark

    private class Settings extends SettingsAction {

        @Override
        protected TreeViewSettings getEditor() {
            return new TreeViewSettings(TreeView.this);
        }
    }

    /**
     * Action - toggle sticky mode
     */
    private class Sticky extends AbstractAncestrisAction {

        /** constructor */
        protected Sticky() {
            super.setImage(ancestris.core.resources.Images.imgStickOff);
            super.setTip(RESOURCES.getString("action.stick.tip"));
            super.setSelected(false);
        }

        /** run */
        @Override
        public void actionPerformed(ActionEvent event) {
            setSelected(isSelected());
        }

        @Override
        public boolean setSelected(boolean selected) {
            super.setImage(selected ? ancestris.core.resources.Images.imgStickOn : ancestris.core.resources.Images.imgStickOff);
            return super.setSelected(selected);
        }
    } //Sticky

    private class ActionBluePrint extends AbstractAncestrisContextAction {

        private final ImageIcon IMAGE = new ImageIcon(ChooseBlueprintAction.class, "Blueprint.png");
        private Entity entity;

        public ActionBluePrint() {
            super();
            setImage(IMAGE);
            /*
             * Reset and set image and text to be sure that propertyCHanged event is
             * fired. just after init, image and text are changed and if no change is done
             * on them, the display can be out of sync. PropertertyChangeListeners can only be
             * called after object construction so in our case we must update ui after all 
             * initialisations occured
             */
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    Icon icon = getImage();
                    String text = getText();
                    String tt = getTip();
                    setImage(IMAGE).setImage(icon);
                    setText("").setText(text);
                    setTip("").setTip(tt);
                }
            });
        }

        @Override
        protected void contextChanged() {
            if (!contextProperties.isEmpty()) {
                Property prop = contextProperties.get(0);
                if (prop.getEntity() instanceof Indi || prop.getEntity() instanceof Fam) {
                    entity = prop.getEntity();
                } else if (entity == null) {
                    entity = prop.getGedcom().getFirstEntity(Gedcom.INDI);
                }
            }
            if (entity == null) {
                entity = new Indi();
            }

            setImageText(IMAGE.getOverLayed(entity.getImage(false)),
                    NbBundle.getMessage(ChooseBlueprintAction.class, "blueprint.select.for", Gedcom.getName(entity.getTag())));
            super.contextChanged();
        }

        @Override
        protected void actionPerformedImpl(final ActionEvent event) {
            if (entity != null) {

                new ChooseBlueprintAction(entity, getBlueprint(entity.getTag())) {

                    @Override
                    protected void commit(Entity recipient, Blueprint blueprint) {
                        tag2blueprint.put(recipient.getTag(), blueprint.getName());
                        tag2renderer.remove(recipient.getTag());
                        repaint();
                    }
                }.actionPerformed(null);
            }
        }
    }

    private class ActionGotoContext extends AbstractAncestrisContextAction {

        private Entity entity;
        private JButton bMenu = null;

        public ActionGotoContext() {
            this(null);
        }
        public ActionGotoContext(JButton b) {
            super();
            bMenu = b;
            Entity e = null;
            Context c = TreeView.this.context;
            if (c != null)
                e = c.getEntity();
            if (e == null)
                e = TreeView.this.getRoot();
            setTip(RESOURCES.getString("goto.context.tip",e==null?"":e.toString(false)));
            setText(RESOURCES.getString("goto.context.tip",e==null?"":e.toString(false)));
            setImage(Images.imgGotoRoot);
        }

        @Override
        protected void contextChanged() {
            if (!contextProperties.isEmpty()) {
                Property prop = contextProperties.get(0);
                if (prop.getEntity() instanceof Indi || prop.getEntity() instanceof Fam) {
                    entity = prop.getEntity();
                } else if (entity == null) {
                    entity = prop.getGedcom().getFirstEntity(Gedcom.INDI);
                }
            }
            if (entity != null){
                setTip(NbBundle.getMessage(ActionGotoContext.class, "goto.context.tip",entity.toString(false)));
                setText(NbBundle.getMessage(ActionGotoContext.class, "goto.context.tip",entity.toString(false)));
            }
            super.contextChanged();
        }

        @Override
        protected void actionPerformedImpl(final ActionEvent event) {
            TreeView.this.scrollToCurrent(true);
            if (bMenu.getAction() != this)
                bMenu.setAction( this );
        }
    }

    private class TreeContext extends ViewContext {

        public TreeContext(Context context) {
            super(context);
        }
    }

// Filter interface

    @Override
    public boolean veto(Property prop) {
        // all non-entities are fine
        return false;
    }

    @Override
    public boolean veto(Entity ent) {
        Set ents = model.getEntities();
        // indi?
        if (ent instanceof Indi) {
            return !ents.contains(ent);
        }
        // fam?
        if (ent instanceof Fam) {
            boolean b = ents.contains(ent);
            if (model.isFamilies() || b) {
                return !b;
            }
            Fam fam = (Fam) ent;
            boolean father = ents.contains(fam.getHusband()),
                    mother = ents.contains(fam.getWife()),
                    child = false;
            Indi[] children = fam.getChildren();
            for (int i = 0; child == false && i < children.length; i++) {
                if (ents.contains(children[i])) {
                    child = true;
                }
            }
            // father and mother or parent and child
            return !((father && mother) || (father && child) || (mother && child));
        }
        // let submitter through if it's THE one
        if (model.getRoot().getGedcom().getSubmitter() == ent) {
            return false;
        }
        // maybe a referenced other type?
        Entity[] refs = PropertyXRef.getReferences(ent);
        for (Entity ref : refs) {
            if (ents.contains(ref)) {
                return false;
            }
        }
        // not
        return true;
    }

    /**
     * A string representation of this view as a filter
     */
    @Override
    public String getFilterName() {
        return NbBundle.getMessage(TreeView.class, "TTL_Filter",
                model.getEntities().size(), TITLE);
    }

    @Override
    public boolean canApplyTo(Gedcom gedcom) {
        return (gedcom != null && gedcom.equals(getGedcom()));
    }
} //TreeView
