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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package genj.search;

import ancestris.awt.FilteredMouseAdapter;
import ancestris.core.actions.AbstractAncestrisAction;
import ancestris.view.SelectionDispatcher;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomListener;
import genj.gedcom.Property;
import genj.util.GridBagHelper;
import genj.util.Registry;
import genj.util.Resources;
import genj.util.WordBuffer;
import genj.util.swing.ChoiceWidget;
import genj.util.swing.HeadlessLabel;
import genj.util.swing.ImageIcon;
import genj.util.swing.PopupWidget;
import ancestris.swing.ToolBar;
import ancestris.view.ExplorerHelper;
import genj.view.View;
import genj.view.ViewContext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import spin.Spin;

/**
 * View for searching
 */
public class SearchView extends View {

    /** formatting */
    private final static String OPEN = "<font color=red>",
            CLOSE = "</font>",
            NEWLINE = "<br>";
    /** default values */
    private final static String[] DEFAULT_VALUES = {
        "M(a|e)(i|y)er", "San.+Francisco", "^(M|F)"
    },
            DEFAULT_TAGS = {
        "NAME", "BIRT", "BIRT, PLAC", "OCCU", "NOTE", "BIRT, NOTE", "RESI"
    };
    /** how many old values we remember */
    private final static int MAX_OLD = 16;
    /** resources */
    /* package */ final static Resources RESOURCES = Resources.get(SearchView.class);
    /** current context */
    private Context context = new Context();
    /** registry */
    private final static Registry REGISTRY = Registry.get(SearchView.class);
    /** shown results */
    private Results results = new Results();
    private ResultWidget listResults = new ResultWidget();
    /** headless label used for view creation */
    private HeadlessLabel viewFactory = new HeadlessLabel(listResults.getFont());
    /** criterias */
    private ChoiceWidget choiceTag, choiceValue;
    private JCheckBox checkRegExp;
    private JLabel labelCount;
    private AbstractAncestrisAction actionStart = new ActionStart(), actionStop = new ActionStop();
    /** history */
    private LinkedList<String> oldTags, oldValues;
    /** images */
    private final static ImageIcon IMG_START = new ImageIcon(SearchView.class, "images/Start"),
            IMG_STOP = new ImageIcon(SearchView.class, "images/Stop");
    /** worker */
    private Worker worker;

    /**
     * Constructor
     */
    public SearchView() {

        // setup worker
        worker = new Worker((WorkerListener) Spin.over(new WorkerListener() {

            @Override
            public void more(List<Hit> hits) {
                results.add(hits);
                labelCount.setText("" + results.getSize());
            }

            @Override
            public void started() {
                // clear current results
                results.clear();
                labelCount.setText("");
                actionStart.setEnabled(false);
                actionStop.setEnabled(true);
            }

            @Override
            public void stopped() {
                actionStop.setEnabled(false);
                actionStart.setEnabled(context.getGedcom() != null);
            }
        }));

        // lookup old search values & settings
        oldTags = new LinkedList<String>(Arrays.asList(REGISTRY.get("old.tags", DEFAULT_TAGS)));
        oldValues = new LinkedList<String>(Arrays.asList(REGISTRY.get("old.values", DEFAULT_VALUES)));
        boolean useRegEx = REGISTRY.get("regexp", false);

        // prepare an action listener connecting to click
        ActionListener aclick = new ActionListener() {

            /** button */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionStop.isEnabled()) {
                    stop();
                }
                if (actionStart.isEnabled()) {
                    start();
                }
            }
        };

        // prepare search criteria
        JLabel labelValue = new JLabel(RESOURCES.getString("label.value"));
        checkRegExp = new JCheckBox(RESOURCES.getString("label.regexp"), useRegEx);

        choiceValue = new ChoiceWidget(oldValues);
        choiceValue.addActionListener(aclick);

        PopupWidget popupPatterns = new PopupWidget("...", null);
        popupPatterns.addItems(createPatternActions());
        popupPatterns.setMargin(new Insets(0, 0, 0, 0));

        JLabel labelTag = new JLabel(RESOURCES.getString("label.tag"));
        choiceTag = new ChoiceWidget(oldTags);
        choiceTag.addActionListener(aclick);

        PopupWidget popupTags = new PopupWidget("...", null);
        popupTags.addItems(createTagActions());
        popupTags.setMargin(new Insets(0, 0, 0, 0));

        labelCount = new JLabel();

        JPanel paneCriteria = new JPanel();
        try {
            paneCriteria.setFocusCycleRoot(true);
        } catch (Throwable t) {
        }

        GridBagHelper gh = new GridBagHelper(paneCriteria);
        // .. line 0
        gh.add(labelValue, 0, 0, 2, 1, 0, new Insets(0, 0, 0, 8));
        gh.add(checkRegExp, 2, 0, 1, 1, GridBagHelper.GROW_HORIZONTAL | GridBagHelper.FILL_HORIZONTAL);
        gh.add(labelCount, 3, 0, 1, 1);
        // .. line 1
        gh.add(popupPatterns, 0, 1, 1, 1);
        gh.add(choiceValue, 1, 1, 3, 1, GridBagHelper.GROW_HORIZONTAL | GridBagHelper.FILL_HORIZONTAL, new Insets(3, 3, 3, 3));
        // .. line 2
        gh.add(labelTag, 0, 2, 4, 1, GridBagHelper.GROW_HORIZONTAL | GridBagHelper.FILL_HORIZONTAL);
        // .. line 3
        gh.add(popupTags, 0, 3, 1, 1);
        gh.add(choiceTag, 1, 3, 3, 1, GridBagHelper.GROW_HORIZONTAL | GridBagHelper.FILL_HORIZONTAL, new Insets(0, 3, 3, 3));

        // prepare layout
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, paneCriteria);
        add(BorderLayout.CENTER, new JScrollPane(listResults));
        choiceValue.requestFocusInWindow();

        // FIXME: right clic doesn't work because selection is handled by ListSelectionListener rather than MouseListener
//        setExplorerHelper(new ExplorerHelper(listResults));
        // done
    }

    public void start() {

        // if context
        if (context == null) {
            return;
        }

        // stop worker
        worker.stop();

        // prep args
        String value = choiceValue.getText();
        String tags = choiceTag.getText();
        remember(choiceValue, oldValues, value);
        remember(choiceTag, oldTags, tags);

        // start anew
        worker.start(context.getGedcom(), tags, value, checkRegExp.isSelected());

        // done
    }

    public void stop() {

        worker.stop();

    }

    /**
     * @see javax.swing.JComponent#removeNotify()
     */
    @Override
    public void removeNotify() {
        // keep old
        REGISTRY.put("regexp", checkRegExp.isSelected());
        REGISTRY.put("old.values", oldValues);
        REGISTRY.put("old.tags", oldTags);
        // continue
        super.removeNotify();
    }

    @Override
    public void setContext(Context newContext) {

        // disconnect old
        if (context.getGedcom() != null && context.getGedcom() != newContext.getGedcom()) {

            stop();
            results.clear();
            labelCount.setText("");
            actionStart.setEnabled(false);

            context.getGedcom().removeGedcomListener((GedcomListener) Spin.over(results));
        }

        // keep new
        context = newContext;

        // connect new
        if (context.getGedcom() != null) {
            context = new Context(newContext.getGedcom());
            context.getGedcom().addGedcomListener((GedcomListener) Spin.over(results));
            actionStart.setEnabled(true);
        }

    }

    /**
     * @see genj.view.ToolBarSupport#populate(javax.swing.JToolBar)
     */
    @Override
    public void populate(ToolBar toolbar) {
        toolbar.add(actionStart);
        toolbar.add(actionStop);
    }

    /**
     * Remembers a value
     */
    private void remember(ChoiceWidget choice, LinkedList<String> old, String value) {
        // not if empty
        if (value.trim().length() == 0) {
            return;
        }
        // keep (up to max)
        old.remove(value);
        old.addFirst(value);
        if (old.size() > MAX_OLD) {
            old.removeLast();
        }
        // update choice
        choice.setValues(old);
        choice.setText(value);
        // done
    }

    /**
     * Create preset Tag Actions
     */
    private List<AbstractAncestrisAction> createTagActions() {

        // loop through DEFAULT_TAGS
        List<AbstractAncestrisAction> result = new ArrayList<AbstractAncestrisAction>();
        for (String tag : DEFAULT_TAGS) {
            result.add(new ActionTag(tag));
        }

        // done
        return result;
    }

    /**
     * Create RegExp Pattern Actions
     */
    private List<AbstractAncestrisAction> createPatternActions() {
        // loop until ...
        List<AbstractAncestrisAction> result = new ArrayList<AbstractAncestrisAction>();
        for (int i = 0;; i++) {
            // check text and pattern
            String key = "regexp." + i,
                    txt = RESOURCES.getString(key + ".txt", false),
                    pat = RESOURCES.getString(key + ".pat", false);
            // no more?
            if (txt == null) {
                break;
            }
            // pattern?
            if (pat == null) {
                continue;
            }
            // create action
            result.add(new ActionPattern(txt, pat));
        }
        return result;
    }

    /**
     * Return list of results
     */
    public List<Property> getResults() {
        List<Property> props = new ArrayList<Property>();
        for (Hit hit : results.hits) {
            props.add(hit.getProperty());
        }
        return props;
    }

    /**
     * Action - select predefined paths
     */
    private class ActionTag extends AbstractAncestrisAction {

        private final String tags;

        /**
         * Constructor
         */
        private ActionTag(String tags) {
            this.tags = tags;

            WordBuffer txt = new WordBuffer(", ");
            for (String t : tags.split(",")) {
                txt.append(Gedcom.getName(t.trim()));
            }
            setText(txt.toString());
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            choiceTag.setText(tags);
        }
    } //ActionPath

    /**
     * Action - insert regexp construct
     * {0} all text
     * {1} before selection
     * {2} (selection)
     * {3} after selection
     */
    private class ActionPattern extends AbstractAncestrisAction {

        /** pattern */
        private final String pattern;

        /**
         * Constructor
         */
        private ActionPattern(String txt, String pat) {
            // make first word bold
            int i = txt.indexOf(' ');
            if (i > 0) {
                txt = "<html><b>" + txt.substring(0, i) + "</b>&nbsp;&nbsp;&nbsp;" + txt.substring(i) + "</html>";
            }

            setText(txt);
            pattern = pat;
        }

        /**
         * @see genj.util.swing.AbstractAncestrisAction#execute()
         */
        @Override
        public void actionPerformed(ActionEvent event) {

            // analyze what we've got
            final JTextField field = choiceValue.getTextEditor();
            int selStart = field.getSelectionStart(),
                    selEnd = field.getSelectionEnd();
            if (selEnd <= selStart) {
                selStart = field.getCaretPosition();
                selEnd = selStart;
            }
            // {0} all text
            String all = field.getText();
            // {1} before selection
            String before = all.substring(0, selStart);
            // {2} (selection)
            String selection = selEnd > selStart ? '(' + all.substring(selStart, selEnd) + ')' : "";
            // {3] after selection
            String after = all.substring(selEnd);

            // calculate result
            final String result = MessageFormat.format(pattern, new Object[]{all, before, selection, after});

            // invoke this later - selection might otherwise not work correctly
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    int pos = result.indexOf('#');

                    // show
                    field.setText(result.substring(0, pos) + result.substring(pos + 1));
                    field.select(0, 0);
                    field.setCaretPosition(pos);

                    // make sure regular expressions are enabled now
                    checkRegExp.setSelected(true);
                }
            });

            // done
        }
    } //ActionInsert

    /**
     * Action - trigger search
     */
    private class ActionStart extends AbstractAncestrisAction {

        /** constructor */
        private ActionStart() {
            setImage(IMG_START);
            setTip(RESOURCES.getString("start.tip"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            stop();
            start();
        }
    } //ActionSearch

    /**
     * Action - stop search
     */
    private class ActionStop extends AbstractAncestrisAction {

        /** constructor */
        private ActionStop() {
            setImage(IMG_STOP);
            setTip(RESOURCES.getString("stop.tip"));
            setEnabled(false);
        }

        /** run */
        @Override
        public void actionPerformed(ActionEvent event) {
            stop();
        }
    } //ActionStop

    /**
     * Our result bucket
     */
    private static class Results extends AbstractListModel implements GedcomListener {

        /** the results */
        private List<Hit> hits = new ArrayList<Hit>(Worker.MAX_HITS);

        /**
         * clear the results (sync to EDT)
         */
        private void clear() {
            // nothing to do?
            if (hits.isEmpty()) {
                return;
            }
            // clear&notify
            int size = hits.size();
            hits.clear();
            fireIntervalRemoved(this, 0, size - 1);
            // done
        }

        /**
         * add a result (sync to EDT)
         */
        private void add(List<Hit> list) {
            // nothing to do?
            if (list.isEmpty()) {
                return;
            }
            // remember 
            int size = hits.size();
            hits.addAll(list);
            fireIntervalAdded(this, size, hits.size() - 1);
            // done
        }

        /**
         * @see javax.swing.ListModel#getElementAt(int)
         */
        @Override
        public Object getElementAt(int index) {
            return hits.get(index);
        }

        /**
         * @see javax.swing.ListModel#getSize()
         */
        @Override
        public int getSize() {
            return hits.size();
        }

        /**
         * access to property
         */
        private Hit getHit(int i) {
            return hits.get(i);
        }

        @Override
        public void gedcomEntityAdded(Gedcom gedcom, Entity entity) {
            // TODO could do a re-search here
        }

        @Override
        public void gedcomEntityDeleted(Gedcom gedcom, Entity entity) {
            // ignored
        }

        @Override
        public void gedcomPropertyAdded(Gedcom gedcom, Property property, int pos, Property added) {
            // TODO could do a re-search here
        }

        @Override
        public void gedcomPropertyChanged(Gedcom gedcom, Property prop) {
            for (int i = 0; i < hits.size(); i++) {
                Hit hit = hits.get(i);
                if (hit.getProperty() == prop) {
                    fireContentsChanged(this, i, i);
                }
            }
        }

        @Override
        public void gedcomPropertyDeleted(Gedcom gedcom, Property property, int pos, Property removed) {
            for (int i = 0; i < hits.size();) {
                Hit hit = hits.get(i);
                if (hit.getProperty() == removed) {
                    hits.remove(i);
                    fireIntervalRemoved(this, i, i);
                } else {
                    i++;
                }
            }
        }
    } //Results

    /**
     * our specialized list
     */
    private class ResultWidget extends JList implements ListSelectionListener, ListCellRenderer {

        /** our text component for rendering */
        private final JTextPane text = new JTextPane();
        /** background colors */
        private final Color[] bgColors = new Color[3];

        /**
         * Constructor
         */
        private ResultWidget() {
            super(results);
            // colors
            bgColors[0] = getSelectionBackground();
            bgColors[1] = getBackground();
            bgColors[2] = new Color(
                    Math.max(bgColors[1].getRed() - 16, 0),
                    Math.min(bgColors[1].getGreen() + 16, 255),
                    Math.max(bgColors[1].getBlue() - 16, 0));

            // rendering
            setCellRenderer(this);
            addListSelectionListener(this);
            text.setOpaque(true);
                        //XXX: rework double click handler
            // patch selecting
            // Copied from PropertyTableWidget
            addMouseListener(new FilteredMouseAdapter() {

                @Override
                public void mouseClickedFiltered(MouseEvent e) {
                    int row = listResults.getSelectedIndex();
                    if (row >= 0) {
                        // FIXME: action is handled here and selection is handled in changeSelection
                        Object cell = results.getHit(row).getProperty();
                        if (cell != null && cell instanceof Property) {
                            SelectionDispatcher.fireSelection(e, new Context((Property) cell));
                        }
                    }
                }
            });

        }

        /**
         * ContextProvider - callback
         */
        public ViewContext getContext() {

            if (context == null) {
                return null;
            }

            List<Property> properties = new ArrayList<Property>();
            Object[] selection = getSelectedValues();
            for (Object selection1 : selection) {
                Hit hit = (Hit) selection1;
                properties.add(hit.getProperty());
            }
            return new ViewContext(context.getGedcom(), null, properties);
        }

        /**
         * we know about action delegates and will use that here if applicable
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Hit hit = (Hit) value;

            // prepare color
            int c = isSelected ? 0 : 1 + (hit.getEntity() & 1);
            text.setBackground(bgColors[c]);

            // show hit document (includes image and text)
            text.setDocument(hit.getDocument());

            // done
            return text;
        }

        /**
         * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = listResults.getSelectedIndex();
            if (row >= 0) {
                SelectionDispatcher.fireSelection(new Context(results.getHit(row).getProperty()));
            }
        }
    } //ResultWidget
} //SearchView
