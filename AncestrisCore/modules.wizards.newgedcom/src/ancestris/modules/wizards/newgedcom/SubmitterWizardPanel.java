/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2011 Ancestris
 * 
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.wizards.newgedcom;

import ancestris.util.Utilities;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class SubmitterWizardPanel extends NewGedcomWizardPanel {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private SubmitterVisualPanel component;
    private INewGedcomProvider gedcomProvider;

    public SubmitterWizardPanel(INewGedcomProvider gedcomProvider) {
        this.gedcomProvider = gedcomProvider;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new SubmitterVisualPanel(gedcomProvider,this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    @Override
    public boolean isValid() {
        return !component.getSubmitterName().isEmpty();
    }

    @Override
    void applyNext() {
        component.applyNext();
    }

    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0

    @Override
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    @Override
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }
        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }
     
    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    @Override
    public void readSettings(WizardDescriptor settings) {
    }

    @Override
    public void storeSettings(WizardDescriptor settings) {
    }

    @Override
    public URL getHelpUrl() {
        try {
           return new URL("nbresloc:/"+Utilities.getClassName(this)+"-help.html");
        } catch (MalformedURLException r){}
        return null;
    }
}
