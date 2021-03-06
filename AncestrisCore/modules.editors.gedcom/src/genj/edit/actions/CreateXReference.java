/**
 *
 */
package genj.edit.actions;

import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Property;
import genj.gedcom.PropertyXRef;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 * Knows how to create cross-references to other entities, namely NOTE, OBJE, SUBM, SOUR, ... and
 * even FAM (it can be added as a simple cross-reference to BIRThs)
 */
public class CreateXReference extends CreateRelationship {

    private Property source;
    private String sourceTag;
    private PropertyXRef xref;
    private static final String CREATE_XREF_ACTION_SUBMENU = "Ancestris/Actions/GedcomProperty/AddOther";

    /** Constructor */
    public CreateXReference(Property source, String sourceTag) {
        super();
        setTargetType(getTargetType(getSource(), sourceTag));
//    super(getName(source, sourceTag), getTargetType(source, sourceTag));
        this.sourceTag = sourceTag;
        setContextProperties(getSource());
        contextChanged();
    }

    private Property getSource() {
        if (context != null) {
            return context.lookup(Property.class);
        }
        return null;
    }

    /** figure out target type for given source+tag */
    private static String getTargetType(Property source, String sourceTag) {
        //XXX: we should double check this null return. I think it is ok as action 
        // is updated on context change but we probably need to write some docs about it
        if (source == null)
            return null;
        // ask a sample
        try {
            Property sample = source.getMetaProperty().getNested(sourceTag, false).create("@@");
            if (!(sample instanceof PropertyXRef)) {
                return null;
            }
            return ((PropertyXRef) sample).getTargetType();
        } catch (GedcomException e) {
            Logger.getLogger("ancestris.edit.actions").log(Level.SEVERE, "couldn't determine target type", e);
            throw new RuntimeException("Couldn't determine target type for source tag " + sourceTag);
        }
    }

    /** figure out our name - done once */
    private static String getName(Property source, String sourceTag) {
        String targetType = getTargetType(source, sourceTag);
        if (targetType.equals(sourceTag)) {
            return Gedcom.getName(targetType);
        }
        return Gedcom.getName(targetType) + " (" + Gedcom.getName(sourceTag) + ")";
    }

    @Override
    protected final void contextChanged() {
        source = null;
        if (contextProperties.size() == 1) {
            source = contextProperties.get(0);
        }
        if (source != null) {
            String tgtType = getTargetType(source, sourceTag);
            //XXX: we must find a better way to check xref validity: use MetaProperty.allow?
            if (tgtType != null) {
                setEnabled(true);
                this.targetType = tgtType;
                setText(getName(source, sourceTag));
                setTip(resources.getString("link", getDescription()));

                return;
            }
        }
        setText(Gedcom.getName(sourceTag));
        setEnabled(false);
        setTip(null);
    }

    /** more about what we do */
    @Override
    public String getDescription() {
        return resources.getString("create.xref.desc", Gedcom.getName(targetType), source.getEntity().toString());
    }

    /** do the change */
    @Override
    protected Property change(Entity target, boolean targetIsNew) throws GedcomException {

        // create a the source link
        xref = (PropertyXRef) source.addProperty(sourceTag, '@' + target.getId() + '@');

        try {
            xref.link();
            xref.addDefaultProperties();
        } catch (GedcomException e) {
            source.delProperty(xref);
            xref = null;
            throw e;
        }

        //  focus stays with owner
        return targetIsNew ? xref.getTarget() : xref;

    }

    /**
     * The created reference
     */
    public PropertyXRef getReference() {
        return xref;
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefFamc")
    @ActionRegistration(displayName = "create.famc",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 900, path = "Ancestris/Actions/GedcomProperty/AddIndiOrFam")})
    public static CreateXReference addFamcFactory() {
        return new CreateXReference(null, "FAMC");
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefNote")
    @ActionRegistration(displayName = "create.note",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 100, path = CREATE_XREF_ACTION_SUBMENU)})
    public static CreateXReference addNoteFactory() {
        return new CreateXReference(null, "NOTE");
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefRepo")
    @ActionRegistration(displayName = "create.repo",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 400, path = CREATE_XREF_ACTION_SUBMENU)})
    public static CreateXReference addRepoFactory() {
        return new CreateXReference(null, "REPO");
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefSour")
    @ActionRegistration(displayName = "create.sour",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 300, path = CREATE_XREF_ACTION_SUBMENU)})
    public static CreateXReference addSourFactory() {
        return new CreateXReference(null, "SOUR");
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefSubm")
    @ActionRegistration(displayName = "create.subm",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 500, path = CREATE_XREF_ACTION_SUBMENU)})
    public static CreateXReference addSubmFactory() {
        return new CreateXReference(null, "SUBM");
    }

    @ActionID(category = "Edit/Gedcom", id = "genj.edit.actions.AddXRefObje")
    @ActionRegistration(displayName = "create.obje",
        lazy = false)
    @ActionReferences(value = {
        @ActionReference(position = 200, path = CREATE_XREF_ACTION_SUBMENU)})
    public static CreateXReference addObjeFactory() {
        return new CreateXReference(null, "OBJE");
    }
}
