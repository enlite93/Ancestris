/*
 * Copyright (C) 2012 lemovice
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ancestris.modules.gedcom.history;

import ancestris.app.App;
import ancestris.core.pluginservice.PluginInterface;
import ancestris.view.AncestrisDockModes;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.view.SelectionSink;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.RetainLocation;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ancestris.modules.gedcom.history//GedcomHistory//EN", autostore = false)
@RetainLocation(AncestrisDockModes.TABLE)
@TopComponent.Description(preferredID = "GedcomHistoryTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
//@TopComponent.OpenActionRegistration(displayName = "#CTL_GedcomHistoryAction", preferredID = "GedcomHistoryTopComponent")
public final class GedcomHistoryTopComponent extends TopComponent implements ChangeListener {

    private static final Logger log = Logger.getLogger(GedcomHistoryTopComponent.class.getName());
    GedcomHistory gedcomHistory = null;
    GedcomHistoryTableModel historyTableModel = null;

    private class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }

            Context context = App.center.getSelectedContext(true);
            if (context != null) {
                String currentId = (String) historyTableModel.getValueAt(gedcomHistoryTable.getSelectedRow(), GedcomHistoryTableModel.entityId);
                Gedcom myGedcom = context.getGedcom();

                if (currentId != null && myGedcom != null) {
                    Entity entity = myGedcom.getEntity(currentId);
                    if (entity != null) {
                        SelectionSink.Dispatcher.fireSelection(new Context(entity), true);
                    }
                }
            }
        }
    }

    public GedcomHistoryTopComponent() {
        Context context = App.center.getSelectedContext(true);
        if (context != null) {
            String gedcomName = context.getGedcom().getName().substring(0, context.getGedcom().getName().lastIndexOf(".") == -1 ? context.getGedcom().getName().length() : context.getGedcom().getName().lastIndexOf("."));
            for (PluginInterface pluginInterface : Lookup.getDefault().lookupAll(PluginInterface.class)) {
                if (pluginInterface instanceof GedcomHistoryPlugin) {

                    this.gedcomHistory = ((GedcomHistoryPlugin) pluginInterface).getGedcomHistory(gedcomName);
                    // during the plugin first install GedcomHistoryTopComponent is call before moduleinstall.start
                    if (this.gedcomHistory != null) {
                        this.historyTableModel = new GedcomHistoryTableModel(this.gedcomHistory);
                        initComponents();
                        setName(NbBundle.getMessage(this.getClass(), "CTL_GedcomHistoryTopComponent", gedcomHistory.getGedcomName()));
                        setToolTipText(NbBundle.getMessage(this.getClass(), "HINT_GedcomHistoryTopComponent"));
                        gedcomHistoryTable.getSelectionModel().addListSelectionListener(new RowListener());
                    } else {
                        log.log(Level.INFO, "No history recorder found for {0}", gedcomName);
                    }
                    return;
                }
            }
            log.log(Level.INFO, "No Instance of GedcomHistoryPlugin found");

        } else {
            log.log(Level.INFO, "No context found");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gedcomHistoryScrollPane = new javax.swing.JScrollPane();
        gedcomHistoryTable = new javax.swing.JTable();

        gedcomHistoryTable.setAutoCreateRowSorter(true);
        gedcomHistoryTable.setModel(historyTableModel);
        gedcomHistoryScrollPane.setViewportView(gedcomHistoryTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gedcomHistoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gedcomHistoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane gedcomHistoryScrollPane;
    private javax.swing.JTable gedcomHistoryTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        if (gedcomHistory != null) {
            gedcomHistory.addChangeListener(this);
        }
    }

    @Override
    public void componentClosed() {
        if (gedcomHistory != null) {
            gedcomHistory.removeChangeListener(this);
        }
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        historyTableModel.fireTableRowsInserted(gedcomHistory.getHistoryList().size() - 1, gedcomHistory.getHistoryList().size() - 1);
    }
}
