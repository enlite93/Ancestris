/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import ancestris.util.AncestrisPreferences;
import java.util.Collection;
import java.util.prefs.Preferences;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    private boolean restart = false;

    // On verifie a chaque demarrage les mises a jour de plugin
    // a moins que l'utilisateur n'ait change le reglage
    @Override
    public void validate() throws IllegalStateException {
        Preferences p = NbPreferences.root().node("/org/netbeans/modules/autoupdate");
        p.putInt("period", p.getInt("period",0));
    }


    @Override
    public void restored() {
        // Launches main application
        App.main(new String[]{});

        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            public void run() {
            Collection<String> pfiles = AncestrisPreferences.get(App.class).get("gedcoms", (Collection<String>) null);
            App.center.load(pfiles);
            }
        });
    }

    @Override
    public boolean closing() {
        AncestrisPreferences.get(App.class).put("gedcoms", App.center.getOpenedGedcoms());
        return App.closing();
    }

    @Override
    public void close() {
        App.close();
    }

}