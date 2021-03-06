package ancestris.modules.releve.editor;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class Java2sAutoComboBox extends JComboBox<String> {

    private class AutoTextFieldEditor extends BasicComboBoxEditor {

        private Java2sAutoTextField getAutoTextFieldEditor() {
            return (Java2sAutoTextField) editor;
        }

        AutoTextFieldEditor(List<String> list) {
            editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
        }

        @Override
        public void setItem(Object anObject) {
            if (anObject == null) {
                editor.setText("");
            } else {
                editor.setText(anObject.toString());
            }
        }
    }

    public Java2sAutoComboBox(List<String> list) {
        isFired = false;
        autoTextFieldEditor = new AutoTextFieldEditor(list);
        setEditable(true);
        setModel(new DefaultComboBoxModel<String>(list.toArray(new String[list.size()])) {

            @Override
            protected void fireContentsChanged(Object obj, int i, int j) {
                if (!isFired) {
                    super.fireContentsChanged(obj, i, j);
                }
            }
        });
        setEditor(autoTextFieldEditor);
        setPrototypeDisplayValue("AAAAAAAAAAA");
        revalidate();
    }

    /**
     * Add change listener
     */
    public void addChangeListener(ChangeListener l) {
        autoTextFieldEditor.getAutoTextFieldEditor().addChangeListener(l);
    }

    /**
     * Remove change listener
     */
    public void removeChangeListener(ChangeListener l) {
        autoTextFieldEditor.getAutoTextFieldEditor().removeChangeListener(l);
    }

    public boolean isCaseSensitive() {
        return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
    }

    public void setCaseSensitive(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
    }

    public void setUpperAllFirstChar(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setUpperAllFirstChar(flag);
    }

    public void setUpperAllChar(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setUpperAllChar(flag);
    }

    public boolean isStrict() {
        return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
    }

    public void setStrict(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
    }

    public List<String> getDataList() {
        return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
    }

    public void setDataList(List<String> list) {
        isFired = true;
        Object selectedObject = getEditor().getItem();
        autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
        setModel(new DefaultComboBoxModel<String>(list.toArray(new String[list.size()])));
        setSelectedItem(selectedObject);
        isFired = false;
    }

    protected void setSelectedValue(Object obj) {
        if (isFired) {
            return;
        } else {
            isFired = true;
            setSelectedItem(obj);
            //fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder,
            //    1));
            isFired = false;
            return;
        }
    }

    @Override
    protected void fireActionEvent() {
        if (!isFired) {
            super.fireActionEvent();
        }
    }
    private AutoTextFieldEditor autoTextFieldEditor;
    private boolean isFired;
}
