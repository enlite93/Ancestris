/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2012 Ancestris
 * 
 * Author: Dominique Baron (lemovice-at-ancestris-dot-org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.gedcom.sosanumbers;

import ancestris.util.swing.SelectEntityDialog;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.util.Utilities;

@ActionID(id = "ancestris.modules.gedcom.sosanumbers.GenerateSosaAction",
category = "Tools")
@ActionRegistration(iconBase = "ancestris/modules/gedcom/sosanumbers/SosaNumbersIcon.png",
iconInMenu = true,
displayName = "#CTL_GenerateSosaAction")
@ActionReference(path = "Menu/Tools/Gedcom")
public final class GenerateSosaAction implements ActionListener {

    Gedcom myGedcom = null;
    private final Preferences modulePreferences = NbPreferences.forModule(SosaNumbers.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        Context context;

        if ((context = Utilities.actionsGlobalContext().lookup(Context.class)) != null) {
            myGedcom = context.getGedcom();

            SelectEntityDialog selectEntityDialog = new SelectEntityDialog(NbBundle.getMessage(this.getClass(), "GenerateSosaAction.AskDeCujus"), myGedcom, Gedcom.INDI);
            Indi indiDeCujus = (Indi) selectEntityDialog.getEntity();
            if (indiDeCujus != null) {
                new SosaNumbers().generateSosaNbs(myGedcom, indiDeCujus);
                modulePreferences.put("SelectEntityDialog." + myGedcom.getName(), indiDeCujus.getId());
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(GenerateSosaAction.class, "GenerateSosaAction.done", indiDeCujus.getName()), NotifyDescriptor.INFORMATION_MESSAGE));
            }
        }
    }
}