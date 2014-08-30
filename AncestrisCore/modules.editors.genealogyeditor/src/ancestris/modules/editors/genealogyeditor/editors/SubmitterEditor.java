package ancestris.modules.editors.genealogyeditor.editors;

import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.GedcomException;
import genj.gedcom.Property;
import genj.gedcom.PropertyChange;
import genj.gedcom.Submitter;
import genj.view.ViewContext;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class SubmitterEditor extends EntityEditor {

    private Context context;
    private Submitter mSubmitter;
    private boolean submitterNameModified = false;
    private boolean submitterLanguageModified = false;

    /**
     * Creates new form SubmitterEditor
     */
    public SubmitterEditor() {
        this(false);
    }

    public SubmitterEditor(boolean isNew) {
        super(isNew);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changeDateLabel = new javax.swing.JLabel();
        changeDateLabeldate = new javax.swing.JLabel();
        submitterNameLabel = new javax.swing.JLabel();
        submitterNameTextField = new javax.swing.JTextField();
        languageLabel = new javax.swing.JLabel();
        submitterLanguageTextField = new javax.swing.JTextField();
        submitterTabbedPane = new javax.swing.JTabbedPane();
        addressEditorPanel = new ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel();
        multimediaObjectCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();

        changeDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(changeDateLabel, org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.changeDateLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(submitterNameLabel, org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.submitterNameLabel.text")); // NOI18N

        submitterNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                submitterNameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                submitterNameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                submitterNameModified = true;
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(languageLabel, org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.languageLabel.text")); // NOI18N

        submitterLanguageTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                submitterLanguageModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                submitterLanguageModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                submitterLanguageModified = true;
            }
        });

        submitterTabbedPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.addressEditorPanel.TabConstraints.tabTitle"), addressEditorPanel); // NOI18N
        submitterTabbedPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.multimediaObjectCitationsListPanel.TabConstraints.tabTitle"), multimediaObjectCitationsListPanel); // NOI18N
        submitterTabbedPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.noteCitationsListPanel.TabConstraints.tabTitle"), noteCitationsListPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitterTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(changeDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeDateLabeldate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(languageLabel)
                            .addComponent(submitterNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submitterNameTextField)
                            .addComponent(submitterLanguageTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitterNameLabel)
                    .addComponent(submitterNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageLabel)
                    .addComponent(submitterLanguageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitterTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeDateLabeldate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeDateLabel)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.editors.genealogyeditor.panels.AddressEditorPanel addressEditorPanel;
    private javax.swing.JLabel changeDateLabel;
    private javax.swing.JLabel changeDateLabeldate;
    private javax.swing.JLabel languageLabel;
    private ancestris.modules.editors.genealogyeditor.panels.MultimediaObjectCitationsListPanel multimediaObjectCitationsListPanel;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JTextField submitterLanguageTextField;
    private javax.swing.JLabel submitterNameLabel;
    private javax.swing.JTextField submitterNameTextField;
    private javax.swing.JTabbedPane submitterTabbedPane;
    // End of variables declaration//GEN-END:variables
    @Override
    public ViewContext getContext() {
        return new ViewContext(context);
    }

    @Override
    public Component getEditorComponent() {
        return this;
    }

    @Override
    protected String getTitleImpl() {
        if (context == null || context.getEntity() == null) {
            return "";
        }
        return (new ViewContext(context.getEntity())).getText();
    }

    /*
     * 5.5
     * n @<XREF:SUBM>@ SUBM
     * +1 NAME <SUBMITTER_NAME>
     * +1 <<ADDRESS_STRUCTURE>>
     * +1 <<MULTIMEDIA_LINK>>
     * +1 LANG <LANGUAGE_PREFERENCE>
     * +1 RFN <SUBMITTER_REGISTERED_RFN>
     * +1 RIN <AUTOMATED_RECORD_ID>
     * +1 <<NOTE_STRUCTURE>>
     * +1 <<CHANGE_DATE>>
     * 5.5.1
     * n @<XREF:SUBM>@ SUBM
     * +1 NAME <SUBMITTER_NAME>
     * +1 <<ADDRESS_STRUCTURE>>
     * +1 <<MULTIMEDIA_LINK>>
     * +1 LANG <LANGUAGE_PREFERENCE>
     * +1 RFN <SUBMITTER_REGISTERED_RFN>
     * +1 RIN <AUTOMATED_RECORD_ID>
     * +1 <<NOTE_STRUCTURE>>
     * +1 <<CHANGE_DATE>>
     */
    @Override
    protected void setContextImpl(Context context) {
        this.context = context;

        Entity entity = context.getEntity();

        if (entity != null && entity instanceof Submitter) {
            mSubmitter = (Submitter) entity;

            setTitle(NbBundle.getMessage(SubmitterEditor.class, isNew() ? "SubmitterEditor.create.title" : "SubmitterEditor.edit.title", mSubmitter));

            /*
             * +1 NAME <SUBMITTER_NAME>
             */
            Property name = mSubmitter.getProperty("NAME");
            submitterNameTextField.setText(name != null ? name.getDisplayValue() : "");

            /*
             * +1 <<ADDRESS_STRUCTURE>>
             */
            Property address = mSubmitter.getProperty("ADDR", false);
            addressEditorPanel.set(mSubmitter, address);

            /*
             * +1 <<MULTIMEDIA_LINK>>
             */
            multimediaObjectCitationsListPanel.set(mSubmitter, Arrays.asList(mSubmitter.getProperties("OBJE")));

            /*
             * +1 LANG <LANGUAGE_PREFERENCE>    
             */
            Property language = mSubmitter.getProperty("LANG", false);
            submitterLanguageTextField.setText(language != null ? language.getValue() : "");

            /*
             * +1 <<NOTE_STRUCTURE>>
             */
            if (!mSubmitter.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
                submitterTabbedPane.removeTabAt(submitterTabbedPane.indexOfTab(NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.noteCitationsListPanel.TabConstraints.tabTitle")));
            } else {
                if (submitterTabbedPane.indexOfTab(NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.noteCitationsListPanel.TabConstraints.tabTitle")) == -1) {
                    submitterTabbedPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterEditor.class, "SubmitterEditor.noteCitationsListPanel.TabConstraints.tabTitle"), noteCitationsListPanel);
                }
                noteCitationsListPanel.set(mSubmitter, Arrays.asList(mSubmitter.getProperties("NOTE")));
            }

            /*
             * +1 <<CHANGE_DATE>>
             * Handle by gedcom doUnitOfWork
             */
            Property changeDate = mSubmitter.getProperty("CHAN");
            if (changeDate != null) {
                changeDateLabeldate.setText(((PropertyChange) changeDate).getDisplayValue());
            }
        }
    }

    @Override
    public void commit() throws GedcomException {
        Property name = mSubmitter.getProperty("NAME");
        if (submitterNameModified) {
            if (name != null) {
                name.setValue(submitterNameTextField.getText());
            } else {
                mSubmitter.addProperty("NAME", submitterNameTextField.getText());
            }
        }

        if (submitterLanguageModified) {
            Property language = mSubmitter.getProperty("LANG", false);
            if (language != null) {
                language.setValue(submitterLanguageTextField.getText());
            } else {
                mSubmitter.addProperty("LANG", submitterLanguageTextField.getText());
            }
        }

        addressEditorPanel.commit();
    }
}