/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.explorers.zipexplorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import org.ancestris.trancestris.resources.ZipArchive;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.NotifyDescriptor.Confirmation;
import org.openide.cookies.SaveCookie;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;

/**
 *
 * @author dominique
 */
public class ZipRootNode extends AbstractNode implements PropertyChangeListener {

    private boolean change = false;

    private class SaveCookieImpl implements SaveCookie {

        @Override
        public void save() throws IOException {
            Confirmation msg = new NotifyDescriptor.Confirmation("Do you want to save ?", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.QUESTION_MESSAGE);

            Object result = DialogDisplayer.getDefault().notify(msg);

            //When user clicks "Yes", indicating they really want to save,
            //we need to disable the Save button and Save menu item,
            //so that it will only be usable when the next change is made
            //to the text field:
            if (NotifyDescriptor.YES_OPTION.equals(result)) {
                change = false;
                //Implement your save functionality here.
            }
        }
    }

    /** Creates a new instance of RootNode */
    public ZipRootNode(ZipArchive root) {
        super(new ZipDirectoryChildren(root.getRoot()));
        setDisplayName(root.getName());
    }

    @Override
    public Node.Cookie getCookie(Class type) {
        if (type == SaveCookie.class && change == true) {
            return new SaveCookieImpl();
        } else {
            return super.getCookie(type);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        change = true;
        fireCookieChange();
    }
}