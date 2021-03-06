/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestris.modules.views.entity;

import ancestris.view.GenjViewTopComponent;
import ancestris.view.AncestrisDockModes;
import genj.entity.EntityViewFactory;
import genj.view.ViewFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.windows.RetainLocation;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ancestris.app//Entity//EN",
autostore = false)
@RetainLocation(AncestrisDockModes.PROPERTIES)
public final class EntityTopComponent extends GenjViewTopComponent {

    private static final String PREFERRED_ID = "EntityTopComponent";
    private static EntityTopComponent factory;
    private static ViewFactory viewfactory = new EntityViewFactory();

    public ViewFactory getViewFactory() {
        return viewfactory;
    }

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized EntityTopComponent getFactory() {
        if (factory == null) {
            factory = new EntityTopComponent();
        }
        return factory;
    }

    public void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        super.writeProperties(p);
    }

    public void readProperties(java.util.Properties p) {
        super.readProperties(p);
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
