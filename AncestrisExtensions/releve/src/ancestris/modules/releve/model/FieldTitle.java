package ancestris.modules.releve.model;

import javax.swing.KeyStroke;

/**
 *
 * @author Michel
 */
public class FieldTitle extends Field {

    private KeyStroke keyStroke;
    private String title = "";

    public FieldTitle(String title, KeyStroke keyStroke) {
        this.keyStroke = keyStroke;
        this.title = title;
    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

    public void setKeyStroke(KeyStroke keystroke) {
        this.keyStroke = keystroke;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String getValue() {
        return "";
    }

   @Override
    public void setValue(Object value) {
        
    }

    @Override
    public boolean isEmpty() {
        return title.isEmpty();
    }

}
