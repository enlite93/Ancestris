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
package ancestris.util.swing;

import genj.common.SelectEntityWidget;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import java.util.prefs.Preferences;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbPreferences;

/**
 *
 * @author dominique
 */
public class SelectEntityDialog {

    Preferences modulePreferences = NbPreferences.forModule(SelectEntityDialog.class);
    String entityTag = "";
    Gedcom gedcom;
    SelectEntityPanel selectEntityPanel = null;
    NotifyDescriptor notifyDescriptor = null;

    public SelectEntityDialog(String title, Gedcom gedcom, String entityTag) {
        this.gedcom = gedcom;
        this.entityTag = entityTag;
        // Create instance of your panel, extends JPanel...
        selectEntityPanel = new SelectEntityPanel(gedcom, entityTag);

        // Create a custom NotifyDescriptor, specify the panel instance as a parameter + other params
        notifyDescriptor = new NotifyDescriptor(
                selectEntityPanel, // instance of your panel
                title, // title of the dialog
                NotifyDescriptor.OK_CANCEL_OPTION, // it is Yes/No dialog ...
                NotifyDescriptor.QUESTION_MESSAGE, // ... of a question type => a question mark icon
                null, // we have specified YES_NO_OPTION => can be null, options specified by L&F,
                // otherwise specify options as:
                //     new Object[] { NotifyDescriptor.YES_OPTION, ... etc. },
                NotifyDescriptor.NO_OPTION // default option is "Yes"
                );
    }

    public Entity getEntity() {
        String selectedEntityID = modulePreferences.get("SelectEntityDialog." + gedcom.getName() + "." + entityTag, "");
        if (!selectedEntityID.isEmpty()) {
            Entity selectedEntity = gedcom.getEntity(selectedEntityID);
            if (selectedEntity != null) {
                selectEntityPanel.setSelection(selectedEntity);
            }
        }
        
        // let's display the dialog now...
        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            modulePreferences.put("SelectEntityDialog." + gedcom.getName() + "." + entityTag, selectEntityPanel.getSelection().getId());

            return selectEntityPanel.getSelection();
        }
        return null;
    }
}
