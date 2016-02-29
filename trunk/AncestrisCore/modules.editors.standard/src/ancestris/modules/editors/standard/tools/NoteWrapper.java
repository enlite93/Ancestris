/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2015 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.editors.standard.tools;

import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.gedcom.Note;
import genj.gedcom.Property;
import genj.gedcom.PropertyNote;
import org.openide.util.Exceptions;



/**
 *
 * @author frederic
 */
public class NoteWrapper {

    private Property hostingProperty = null;
    private Entity targetNote = null;
    private String text = "";
    
    // Constructor for note 
    public NoteWrapper(Property property) {
        if (property == null) {
            return;
        }
        if (property instanceof PropertyNote) {
            this.hostingProperty = property;
            PropertyNote pnote = (PropertyNote) property;
            this.targetNote = (Note) pnote.getTargetEntity();
            setText(this.targetNote.getValue().trim());
        } else {
            this.hostingProperty = property.getParent();
            this.targetNote = this.hostingProperty.getEntity();
            setText(property.getValue().trim());
        }
    }

    // Constructor for note added from note chooser
    public NoteWrapper(Note entity) {
        if (entity == null) {
            return;
        }
        this.targetNote = entity;
        setText(this.targetNote.getValue().trim());
    }
    
    // Constructor from change text
    public NoteWrapper(String text) {
        setText(text);
    }

    


    /**
     * Creates or Updates the NOTE property
     *    - Creation : separate NOTE entity
     *    - Update : where it is
     * @param indi 
     */
    public void update(Indi indi) {
        // If it is a creation...
        if (hostingProperty == null) {
            try {
                if (this.targetNote == null) {
                    this.targetNote = indi.getGedcom().createEntity(Gedcom.NOTE);
                }
                indi.addNote((Note) targetNote);
                putNoteLinked((Note) targetNote);
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
            return;
        }
        
        // ... or else a modification
        Entity entity = hostingProperty.getEntity();
        // Case of property directly written within INDI
        if ((entity instanceof Indi) && !(hostingProperty instanceof PropertyNote)) {
            putNoteIntegrated(hostingProperty);
        } else 
            
        // Case of propertyNote written within INDI
        if ((entity instanceof Indi) && (hostingProperty instanceof PropertyNote)) {
            PropertyNote pn = (PropertyNote) hostingProperty;
            Property parent = pn.getParent();
            // add new link from parent
            parent.addNote((Note) targetNote);
            putNoteLinked(targetNote);
            // remove old link
            parent.delProperty(hostingProperty);
        } else
            
        // Case of property as Note entity (added chosen from NoteChooser)
        if (entity instanceof Note) {
            indi.addNote((Note) targetNote);
            putNoteLinked(targetNote);
        }
    }

    /**
     * Writes the note as a link to a Note Entity
     * 
     * n NOTE @<XREF:NOTE>@ {1:1}
     * 
     * @param property 
     */
    private void putNoteLinked(Property property) {
        property.setValue(this.text);
    }

    /**
     * Writes the note as an integrated note property
     * 
     * n NOTE [<SUBMITTER_TEXT> | <NULL>] {1:1}
     *   +1 [CONC|CONT] <SUBMITTER_TEXT> {0:M}
     * 
     * @param property 
     */
    private void putNoteIntegrated(Property property) {
        property.setValue(this.text);
    }

    
    
    public void remove(Indi indi) {
        if (hostingProperty == null) {
            return;
        }
        hostingProperty.getParent().delProperty(hostingProperty);
    }

    
    public String getText() {
        return text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setTargetEntity(Note entity) {
        this.targetNote = entity;
    }



}