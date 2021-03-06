/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2016 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.editors.standard;

import ancestris.modules.editors.standard.tools.EventUsage;
import ancestris.modules.editors.standard.tools.FamilyTreeRenderer;
import ancestris.api.editor.Editor;
import ancestris.core.pluginservice.AncestrisPlugin;
import ancestris.gedcom.privacy.standard.Options;
import ancestris.modules.editors.geoplace.PlaceEditorPanel;
import ancestris.modules.editors.standard.tools.AssoManager;
import ancestris.modules.editors.standard.tools.AssoWrapper;
import ancestris.modules.editors.standard.tools.AutoCompletion;
import ancestris.modules.editors.standard.tools.ErrorPanel;
import ancestris.modules.editors.standard.tools.EventLabel;
import ancestris.modules.editors.standard.tools.EventTableModel;
import ancestris.modules.editors.standard.tools.EventWrapper;
import ancestris.modules.editors.standard.tools.ImagePanel;
import ancestris.modules.editors.standard.tools.IndiChooser;
import ancestris.modules.editors.standard.tools.IndiCreator;
import ancestris.modules.editors.standard.tools.MediaChooser;
import ancestris.modules.editors.standard.tools.MediaWrapper;
import ancestris.modules.editors.standard.tools.NameDetailsPanel;
import ancestris.modules.editors.standard.tools.NodeWrapper;
import ancestris.modules.editors.standard.tools.NoteChooser;
import ancestris.modules.editors.standard.tools.NoteWrapper;
import ancestris.modules.editors.standard.tools.RepoChooser;
import ancestris.modules.editors.standard.tools.SourceChooser;
import ancestris.modules.editors.standard.tools.SourceWrapper;
import ancestris.modules.editors.standard.tools.Utils;
import static ancestris.modules.editors.standard.tools.Utils.getImageFromFile;
import static ancestris.modules.editors.standard.tools.Utils.getResizedIcon;
import ancestris.util.TimingUtility;
import ancestris.util.swing.DialogManager;
import static ancestris.util.swing.DialogManager.OK_ONLY_OPTION;
import ancestris.util.swing.FileChooserBuilder;
import ancestris.view.SelectionDispatcher;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.gedcom.Media;
import genj.gedcom.Note;
import genj.gedcom.Property;
import genj.gedcom.PropertyFile;
import genj.gedcom.PropertyForeignXRef;
import genj.gedcom.PropertyMedia;
import genj.gedcom.PropertyName;
import genj.gedcom.PropertySex;
import genj.gedcom.Repository;
import genj.gedcom.Source;
import genj.util.Registry;
import genj.util.Validator;
import genj.view.ViewContext;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

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

/**
 *
 * @author frederic
 */
public class IndiPanel extends Editor implements DocumentListener {

    private static final Logger LOG = Logger.getLogger("ancestris.editor.indi");

    private int PHOTO_WIDTH = 160;
    private int PHOTO_HEIGHT = 186;
    private Image PHOTO_MALE = null;
    private Image PHOTO_FEMALE = null;
    private Image PHOTO_UNKNOWN = null;
    
    private Context context;
    private Gedcom gedcom;
    private Indi indi;
    private boolean reloadData = true;
    private boolean listernersOn = false;
    
    private DefaultMutableTreeNode familyTop = null;
    private Registry registry = null;

    private String SOSA_TAG = "_SOSA";                                                              // FIXME : use existing parameter
    private final static String NO_SOSA = NbBundle.getMessage(IndiPanel.class, "noSosa");
    
    private static Map<String, EventUsage> eventUsages = null;

    private ImagePanel imagePanel = null;
    private NameDetailsPanel nameDetails = null;
    
    // Media
    private List<MediaWrapper> mediaSet = null;
    private int mediaIndex = 0, savedMediaIndex = -1;
    private List<MediaWrapper> mediaRemovedSet = null;
    private boolean isBusyMedia = false;
    
    // Events
    private List<EventWrapper> eventSet = null;
    private List<EventWrapper> eventRemovedSet = null;
    private boolean isBusyEvent = false;
    private PlaceEditorPanel placeEditor = null;
    private boolean isBusyEventNote = false;
    private boolean isBusyEventSource = false;
    public Map<String, NoteWrapper> refNotes = null;       // Reference to all note entities used by id, to avoid duplicates
    public Map<String, SourceWrapper> refSources = null;   // Reference to all sources used by id, to avoid duplicates
    private int eventIndex = 0, savedEventNoteIndex = -1, savedEventSourceIndex = -1;       // memory
    private String savedEventTagDateDesc = "-1";                                            // memory
    private Component savedFocusedControl = null;                                           // memory
    
    // Associations
    private DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    private List<AssoWrapper> assoSet = null;
    private List<AssoWrapper> assoRemovedSet = null;

    // Warnings
    private List<ViewContext> errorSet = null;
    
    // Filters
    List<String> allFirstNames = null;
    JTextField firstnamesText = null;
    List<String> allLastNames = null;
    JTextField lastnameText = null;
    List<String> allPlaces = null;
    JTextField eventPlaceText = null;

    
    /**
     * Creates new form IndiPanel
     */
    public IndiPanel() {

        // Fixed variables
        try {
            this.PHOTO_MALE = ImageIO.read(getClass().getResourceAsStream("/ancestris/modules/editors/standard/images/profile_male.png"));
            this.PHOTO_FEMALE = ImageIO.read(getClass().getResourceAsStream("/ancestris/modules/editors/standard/images/profile_female.png"));
            this.PHOTO_UNKNOWN = ImageIO.read(getClass().getResourceAsStream("/ancestris/modules/editors/standard/images/profile_unknown.png"));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        // Data
        eventUsages = new HashMap<String, EventUsage>();
        EventUsage.init(eventUsages);
        
        familyTop = new DefaultMutableTreeNode(new NodeWrapper(NodeWrapper.PARENTS, null));
        placeEditor = new PlaceEditorPanel();
        refNotes = new HashMap<String, NoteWrapper>();
        refSources = new HashMap<String, SourceWrapper>();
        
        reloadData = true; // force data load at initialisation
        
        // Components
        initComponents();
        nameDetails = new NameDetailsPanel();
        
        familyTree.setCellRenderer(new FamilyTreeRenderer());
        familyTree.addMouseListener(new FamilyTreeMouseListener());

        firstnamesCombo.setPrototypeDisplayValue("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        lastnameCombo.setPrototypeDisplayValue("MMMMMMMMMMMMMMMMMMMMMMMMM");

        firstnamesText = (JTextField) firstnamesCombo.getEditor().getEditorComponent();
        lastnameText = (JTextField) lastnameCombo.getEditor().getEditorComponent();
        eventPlaceText = (JTextField) eventPlaceCombo.getEditor().getEditorComponent();
        
        registry = Registry.get(getClass());
        eventSplitPane.setDividerLocation(registry.get("eventSplitDividerLocation", eventSplitPane.getDividerLocation()));
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGender = new javax.swing.ButtonGroup();
        indiAddButton = new javax.swing.JButton();
        warningButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        indiDelButton = new javax.swing.JButton();
        mediaPanel = new javax.swing.JPanel();
        photos = new javax.swing.JLabel();
        scrollPanePhotos = new javax.swing.JScrollPane();
        textAreaPhotos = new javax.swing.JTextArea();
        scrollPhotos = new javax.swing.JScrollBar();
        addMediaButton = new javax.swing.JButton();
        delMediaButton = new javax.swing.JButton();
        namePanel = new javax.swing.JPanel();
        TopButtonsdPanel = new javax.swing.JPanel();
        fatherButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        sosaLabel = new javax.swing.JLabel();
        motherButton = new javax.swing.JButton();
        moreNamesButton = new javax.swing.JButton();
        firstnamesLabel = new javax.swing.JLabel();
        lastnameLabel = new javax.swing.JLabel();
        firstnamesCombo = new javax.swing.JComboBox();
        lastnameCombo = new javax.swing.JComboBox();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        unknownRadioButton = new javax.swing.JRadioButton();
        privateCheckBox = new javax.swing.JCheckBox();
        BottomButtonsPanel = new javax.swing.JPanel();
        brothersButton = new javax.swing.JButton();
        sistersButton = new javax.swing.JButton();
        spousesButton = new javax.swing.JButton();
        childrenButton = new javax.swing.JButton();
        scrollPaneFamily = new javax.swing.JScrollPane();
        familyTree = new javax.swing.JTree(familyTop);
        separator = new javax.swing.JSeparator();
        eventSplitPane = new javax.swing.JSplitPane();
        eventLeft = new javax.swing.JPanel();
        eventBirtButton = new javax.swing.JButton();
        eventBaptButton = new javax.swing.JButton();
        eventMarrButton = new javax.swing.JButton();
        eventDeatButton = new javax.swing.JButton();
        eventBuriButton = new javax.swing.JButton();
        eventOccuButton = new javax.swing.JButton();
        eventRetiButton = new javax.swing.JButton();
        eventResiButton = new javax.swing.JButton();
        eventOthersButton = new javax.swing.JButton();
        eventRemoveButton = new javax.swing.JButton();
        eventScrollPane = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();
        modificationLabel = new javax.swing.JLabel();
        sourcePanel = new javax.swing.JPanel();
        imagePanel = new ImagePanel(this);
        sourceImagePanel = imagePanel;
        eventRight = new javax.swing.JPanel();
        eventTitle = new javax.swing.JLabel();
        eventDescription = new javax.swing.JTextField();
        datelabel = new javax.swing.JLabel();
        eventDate = new genj.edit.beans.DateBean();
        dayOfWeek = new javax.swing.JLabel();
        ageAtEvent = new javax.swing.JLabel();
        placeLabel = new javax.swing.JLabel();
        eventPlaceCombo = new javax.swing.JComboBox();
        eventPlaceButton = new javax.swing.JButton();
        eventNotePanel = new javax.swing.JPanel();
        noteLabel = new javax.swing.JLabel();
        changeNoteImg = new javax.swing.JLabel();
        eventNoteScrollPane = new javax.swing.JScrollPane();
        eventNote = new javax.swing.JTextArea();
        scrollNotesEvent = new javax.swing.JScrollBar();
        addNoteEventButton = new javax.swing.JButton();
        delNoteEventButton = new javax.swing.JButton();
        eventSourcePanel = new javax.swing.JPanel();
        eventSourceTitle = new javax.swing.JTextField();
        eventSourceScrollPane = new javax.swing.JScrollPane();
        eventSourceText = new javax.swing.JTextArea();
        repoPanel = new javax.swing.JPanel();
        repoText = new javax.swing.JTextField();
        repoEditButton = new javax.swing.JButton();
        scrollSourcesEvent = new javax.swing.JScrollBar();
        addSourceEventButton = new javax.swing.JButton();
        delSourceEventButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        assoPanel = new javax.swing.JPanel();
        assoComboBox = new javax.swing.JComboBox();
        assoEditButton = new javax.swing.JButton();
        assoEditIndi = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(32767, 500));
        setPreferredSize(new java.awt.Dimension(557, 800));

        indiAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/indi-add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(indiAddButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.indiAddButton.text")); // NOI18N
        indiAddButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.indiAddButton.toolTipText")); // NOI18N
        indiAddButton.setPreferredSize(new java.awt.Dimension(30, 26));
        indiAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indiAddButtonActionPerformed(evt);
            }
        });

        warningButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/warning.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(warningButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.warningButton.text")); // NOI18N
        warningButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.warningButton.toolTipText")); // NOI18N
        warningButton.setPreferredSize(new java.awt.Dimension(30, 26));
        warningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warningButtonActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(title, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.title.text")); // NOI18N

        indiDelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/indi-delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(indiDelButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.indiDelButton.text")); // NOI18N
        indiDelButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.indiDelButton.toolTipText")); // NOI18N
        indiDelButton.setPreferredSize(new java.awt.Dimension(30, 26));
        indiDelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indiDelButtonActionPerformed(evt);
            }
        });

        photos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(photos, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.photos.text")); // NOI18N
        photos.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.photos.toolTipText")); // NOI18N
        photos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        photos.setPreferredSize(new java.awt.Dimension(160, 186));
        photos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photosMouseClicked(evt);
            }
        });

        textAreaPhotos.setColumns(20);
        textAreaPhotos.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        textAreaPhotos.setLineWrap(true);
        textAreaPhotos.setRows(4);
        textAreaPhotos.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.textAreaPhotos.text")); // NOI18N
        textAreaPhotos.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.textAreaPhotos.toolTipText")); // NOI18N
        textAreaPhotos.setWrapStyleWord(true);
        scrollPanePhotos.setViewportView(textAreaPhotos);

        scrollPhotos.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        scrollPhotos.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollPhotosMouseWheelMoved(evt);
            }
        });
        scrollPhotos.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollPhotosAdjustmentValueChanged(evt);
            }
        });

        addMediaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addMediaButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addMediaButton.text")); // NOI18N
        addMediaButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addMediaButton.toolTipText")); // NOI18N
        addMediaButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addMediaButton.setIconTextGap(0);
        addMediaButton.setPreferredSize(new java.awt.Dimension(16, 16));
        addMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMediaButtonActionPerformed(evt);
            }
        });

        delMediaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(delMediaButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delMediaButton.text")); // NOI18N
        delMediaButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delMediaButton.toolTipText")); // NOI18N
        delMediaButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delMediaButton.setIconTextGap(0);
        delMediaButton.setPreferredSize(new java.awt.Dimension(16, 16));
        delMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delMediaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mediaPanelLayout = new javax.swing.GroupLayout(mediaPanel);
        mediaPanel.setLayout(mediaPanelLayout);
        mediaPanelLayout.setHorizontalGroup(
            mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mediaPanelLayout.createSequentialGroup()
                .addComponent(scrollPhotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(delMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(photos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPanePhotos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        mediaPanelLayout.setVerticalGroup(
            mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mediaPanelLayout.createSequentialGroup()
                .addComponent(photos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanePhotos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delMediaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMediaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPhotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        fatherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/father.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(fatherButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.fatherButton.text")); // NOI18N
        fatherButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.fatherButton.toolTipText")); // NOI18N
        fatherButton.setPreferredSize(new java.awt.Dimension(60, 27));
        fatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherButtonActionPerformed(evt);
            }
        });

        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(idLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.idLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(sosaLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.sosaLabel.text")); // NOI18N

        motherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/mother.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(motherButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.motherButton.text")); // NOI18N
        motherButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.motherButton.toolTipText")); // NOI18N
        motherButton.setPreferredSize(new java.awt.Dimension(60, 27));
        motherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motherButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TopButtonsdPanelLayout = new javax.swing.GroupLayout(TopButtonsdPanel);
        TopButtonsdPanel.setLayout(TopButtonsdPanelLayout);
        TopButtonsdPanelLayout.setHorizontalGroup(
            TopButtonsdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopButtonsdPanelLayout.createSequentialGroup()
                .addComponent(fatherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sosaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(motherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopButtonsdPanelLayout.setVerticalGroup(
            TopButtonsdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopButtonsdPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(TopButtonsdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(fatherButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLabel)
                    .addComponent(sosaLabel)
                    .addComponent(motherButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        moreNamesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/name.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(moreNamesButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.moreNamesButton.text")); // NOI18N
        moreNamesButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.moreNamesButton.toolTipText")); // NOI18N
        moreNamesButton.setIconTextGap(0);
        moreNamesButton.setPreferredSize(new java.awt.Dimension(30, 26));
        moreNamesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreNamesButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(firstnamesLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.firstnamesLabel.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lastnameLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.lastnameLabel.text")); // NOI18N

        firstnamesCombo.setEditable(true);
        firstnamesCombo.setMaximumRowCount(19);

        lastnameCombo.setEditable(true);
        lastnameCombo.setMaximumRowCount(19);

        buttonGender.add(maleRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(maleRadioButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.maleRadioButton.text")); // NOI18N
        maleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleRadioButtonActionPerformed(evt);
            }
        });

        buttonGender.add(femaleRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(femaleRadioButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.femaleRadioButton.text")); // NOI18N
        femaleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleRadioButtonActionPerformed(evt);
            }
        });

        buttonGender.add(unknownRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(unknownRadioButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.unknownRadioButton.text")); // NOI18N
        unknownRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unknownRadioButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(privateCheckBox, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.privateCheckBox.text")); // NOI18N
        privateCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.privateCheckBox.toolTipText")); // NOI18N
        privateCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        privateCheckBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/private.png"))); // NOI18N
        privateCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateCheckBoxActionPerformed(evt);
            }
        });

        brothersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/brother.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(brothersButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.brothersButton.text")); // NOI18N
        brothersButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.brothersButton.toolTipText")); // NOI18N
        brothersButton.setMaximumSize(new java.awt.Dimension(45, 27));
        brothersButton.setMinimumSize(new java.awt.Dimension(45, 27));
        brothersButton.setPreferredSize(new java.awt.Dimension(60, 27));
        brothersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brothersButtonActionPerformed(evt);
            }
        });

        sistersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/sister.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(sistersButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.sistersButton.text")); // NOI18N
        sistersButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.sistersButton.toolTipText")); // NOI18N
        sistersButton.setMaximumSize(new java.awt.Dimension(45, 27));
        sistersButton.setMinimumSize(new java.awt.Dimension(45, 27));
        sistersButton.setPreferredSize(new java.awt.Dimension(60, 27));
        sistersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sistersButtonActionPerformed(evt);
            }
        });

        spousesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/spouse.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(spousesButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.spousesButton.text")); // NOI18N
        spousesButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.spousesButton.toolTipText")); // NOI18N
        spousesButton.setMaximumSize(new java.awt.Dimension(45, 27));
        spousesButton.setMinimumSize(new java.awt.Dimension(45, 27));
        spousesButton.setPreferredSize(new java.awt.Dimension(60, 27));
        spousesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spousesButtonActionPerformed(evt);
            }
        });

        childrenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/children.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(childrenButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.childrenButton.text")); // NOI18N
        childrenButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.childrenButton.toolTipText")); // NOI18N
        childrenButton.setMaximumSize(new java.awt.Dimension(45, 27));
        childrenButton.setMinimumSize(new java.awt.Dimension(45, 27));
        childrenButton.setPreferredSize(new java.awt.Dimension(60, 27));
        childrenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childrenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BottomButtonsPanelLayout = new javax.swing.GroupLayout(BottomButtonsPanel);
        BottomButtonsPanel.setLayout(BottomButtonsPanelLayout);
        BottomButtonsPanelLayout.setHorizontalGroup(
            BottomButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BottomButtonsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(brothersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sistersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spousesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(childrenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BottomButtonsPanelLayout.setVerticalGroup(
            BottomButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomButtonsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(BottomButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brothersButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spousesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(childrenButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopButtonsdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BottomButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(namePanelLayout.createSequentialGroup()
                        .addComponent(maleRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femaleRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unknownRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(privateCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(namePanelLayout.createSequentialGroup()
                        .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstnamesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstnamesCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(namePanelLayout.createSequentialGroup()
                                .addComponent(lastnameLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(namePanelLayout.createSequentialGroup()
                                .addComponent(lastnameCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moreNamesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))))))
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addComponent(TopButtonsdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstnamesLabel)
                    .addComponent(lastnameLabel))
                .addGap(1, 1, 1)
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(moreNamesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstnamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastnameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(privateCheckBox)
                    .addComponent(maleRadioButton)
                    .addComponent(femaleRadioButton)
                    .addComponent(unknownRadioButton))
                .addGap(5, 5, 5)
                .addComponent(BottomButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrollPaneFamily.setPreferredSize(new java.awt.Dimension(106, 113));
        scrollPaneFamily.setRequestFocusEnabled(false);
        scrollPaneFamily.setViewportView(familyTree);

        separator.setMinimumSize(new java.awt.Dimension(2, 2));
        separator.setPreferredSize(new java.awt.Dimension(2, 2));

        eventSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                eventSplitPanePropertyChange(evt);
            }
        });

        eventLeft.setPreferredSize(new java.awt.Dimension(200, 306));

        eventBirtButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/birth.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventBirtButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBirtButton.text")); // NOI18N
        eventBirtButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBirtButton.toolTipText")); // NOI18N
        eventBirtButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventBirtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventBirtButtonActionPerformed(evt);
            }
        });

        eventBaptButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/baptism.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventBaptButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBaptButton.text")); // NOI18N
        eventBaptButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBaptButton.toolTipText")); // NOI18N
        eventBaptButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventBaptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventBaptButtonActionPerformed(evt);
            }
        });

        eventMarrButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/marr.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventMarrButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventMarrButton.text")); // NOI18N
        eventMarrButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventMarrButton.toolTipText")); // NOI18N
        eventMarrButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventMarrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventMarrButtonActionPerformed(evt);
            }
        });

        eventDeatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/death.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventDeatButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventDeatButton.text")); // NOI18N
        eventDeatButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventDeatButton.toolTipText")); // NOI18N
        eventDeatButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventDeatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventDeatButtonActionPerformed(evt);
            }
        });

        eventBuriButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/burial.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventBuriButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBuriButton.text")); // NOI18N
        eventBuriButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventBuriButton.toolTipText")); // NOI18N
        eventBuriButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventBuriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventBuriButtonActionPerformed(evt);
            }
        });

        eventOccuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/occu.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventOccuButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOccuButton.text")); // NOI18N
        eventOccuButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOccuButton.toolTipText")); // NOI18N
        eventOccuButton.setActionCommand(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOccuButton.actionCommand")); // NOI18N
        eventOccuButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventOccuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventOccuButtonActionPerformed(evt);
            }
        });

        eventRetiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/retirement.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventRetiButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRetiButton.text")); // NOI18N
        eventRetiButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRetiButton.toolTipText")); // NOI18N
        eventRetiButton.setActionCommand(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRetiButton.actionCommand")); // NOI18N
        eventRetiButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventRetiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventRetiButtonActionPerformed(evt);
            }
        });

        eventResiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/residency.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventResiButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventResiButton.text")); // NOI18N
        eventResiButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventResiButton.toolTipText")); // NOI18N
        eventResiButton.setActionCommand(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventResiButton.actionCommand")); // NOI18N
        eventResiButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventResiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventResiButtonActionPerformed(evt);
            }
        });

        eventOthersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/event.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventOthersButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOthersButton.text")); // NOI18N
        eventOthersButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOthersButton.toolTipText")); // NOI18N
        eventOthersButton.setActionCommand(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventOthersButton.actionCommand")); // NOI18N
        eventOthersButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventOthersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventOthersButtonActionPerformed(evt);
            }
        });

        eventRemoveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventRemoveButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRemoveButton.text")); // NOI18N
        eventRemoveButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRemoveButton.toolTipText")); // NOI18N
        eventRemoveButton.setActionCommand(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventRemoveButton.actionCommand")); // NOI18N
        eventRemoveButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventRemoveButtonActionPerformed(evt);
            }
        });

        eventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        eventScrollPane.setViewportView(eventTable);

        modificationLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        modificationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        org.openide.awt.Mnemonics.setLocalizedText(modificationLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.modificationLabel.text")); // NOI18N

        sourcePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sourcePanel.setPreferredSize(new java.awt.Dimension(197, 140));

        javax.swing.GroupLayout sourceImagePanelLayout = new javax.swing.GroupLayout(sourceImagePanel);
        sourceImagePanel.setLayout(sourceImagePanelLayout);
        sourceImagePanelLayout.setHorizontalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sourceImagePanelLayout.setVerticalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sourcePanelLayout = new javax.swing.GroupLayout(sourcePanel);
        sourcePanel.setLayout(sourcePanelLayout);
        sourcePanelLayout.setHorizontalGroup(
            sourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sourcePanelLayout.setVerticalGroup(
            sourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout eventLeftLayout = new javax.swing.GroupLayout(eventLeft);
        eventLeft.setLayout(eventLeftLayout);
        eventLeftLayout.setHorizontalGroup(
            eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventLeftLayout.createSequentialGroup()
                .addGroup(eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventLeftLayout.createSequentialGroup()
                        .addComponent(eventBirtButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventBaptButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventMarrButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventDeatButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventBuriButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eventLeftLayout.createSequentialGroup()
                        .addComponent(eventOccuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventResiButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventRetiButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventOthersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 36, Short.MAX_VALUE))
            .addGroup(eventLeftLayout.createSequentialGroup()
                .addGroup(eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventLeftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(modificationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(eventScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        eventLeftLayout.setVerticalGroup(
            eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventLeftLayout.createSequentialGroup()
                .addGroup(eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventBirtButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventBaptButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventMarrButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventDeatButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventBuriButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(eventLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventOccuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventRetiButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventResiButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventOthersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(eventScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificationLabel))
        );

        eventSplitPane.setLeftComponent(eventLeft);

        eventRight.setPreferredSize(new java.awt.Dimension(300, 106));

        eventTitle.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        eventTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(eventTitle, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventTitle.text")); // NOI18N

        eventDescription.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventDescription.text")); // NOI18N
        eventDescription.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventDescription.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(datelabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.datelabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dayOfWeek, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.dayOfWeek.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ageAtEvent, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.ageAtEvent.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(placeLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.placeLabel.text")); // NOI18N

        eventPlaceCombo.setEditable(true);
        eventPlaceCombo.setMaximumRowCount(19);

        eventPlaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/place.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(eventPlaceButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventPlaceButton.text")); // NOI18N
        eventPlaceButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventPlaceButton.toolTipText")); // NOI18N
        eventPlaceButton.setPreferredSize(new java.awt.Dimension(30, 26));
        eventPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventPlaceButtonActionPerformed(evt);
            }
        });

        eventNotePanel.setPreferredSize(new java.awt.Dimension(256, 60));

        org.openide.awt.Mnemonics.setLocalizedText(noteLabel, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.noteLabel.text")); // NOI18N

        changeNoteImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeNoteImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/note.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(changeNoteImg, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.changeNoteImg.text")); // NOI18N
        changeNoteImg.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.changeNoteImg.toolTipText")); // NOI18N
        changeNoteImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeNoteImgMouseClicked(evt);
            }
        });

        eventNote.setColumns(20);
        eventNote.setLineWrap(true);
        eventNote.setRows(3);
        eventNote.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventNote.text")); // NOI18N
        eventNote.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.textAreaNotes.toolTipText")); // NOI18N
        eventNote.setWrapStyleWord(true);
        eventNote.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                eventNoteMouseWheelMoved(evt);
            }
        });
        eventNoteScrollPane.setViewportView(eventNote);

        scrollNotesEvent.setBlockIncrement(1);
        scrollNotesEvent.setVisibleAmount(5);
        scrollNotesEvent.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollNotesEventMouseWheelMoved(evt);
            }
        });
        scrollNotesEvent.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollNotesEventAdjustmentValueChanged(evt);
            }
        });

        addNoteEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addNoteEventButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addNoteEventButton.text")); // NOI18N
        addNoteEventButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addNoteEventButton.toolTipText")); // NOI18N
        addNoteEventButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addNoteEventButton.setIconTextGap(0);
        addNoteEventButton.setPreferredSize(new java.awt.Dimension(16, 16));
        addNoteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoteEventButtonActionPerformed(evt);
            }
        });

        delNoteEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(delNoteEventButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delNoteEventButton.text")); // NOI18N
        delNoteEventButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delNoteEventButton.toolTipText")); // NOI18N
        delNoteEventButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delNoteEventButton.setIconTextGap(0);
        delNoteEventButton.setPreferredSize(new java.awt.Dimension(16, 16));
        delNoteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delNoteEventButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout eventNotePanelLayout = new javax.swing.GroupLayout(eventNotePanel);
        eventNotePanel.setLayout(eventNotePanelLayout);
        eventNotePanelLayout.setHorizontalGroup(
            eventNotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventNotePanelLayout.createSequentialGroup()
                .addGroup(eventNotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(eventNotePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(changeNoteImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventNoteScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(eventNotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(scrollNotesEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNoteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delNoteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        eventNotePanelLayout.setVerticalGroup(
            eventNotePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventNotePanelLayout.createSequentialGroup()
                .addComponent(scrollNotesEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(addNoteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(delNoteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(eventNoteScrollPane)
            .addGroup(eventNotePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeNoteImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eventSourceTitle.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventSourceTitle.text")); // NOI18N
        eventSourceTitle.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventSourceTitle.toolTipText")); // NOI18N

        eventSourceText.setColumns(20);
        eventSourceText.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        eventSourceText.setLineWrap(true);
        eventSourceText.setRows(3);
        eventSourceText.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventSourceText.text")); // NOI18N
        eventSourceText.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.eventSourceText.toolTipText")); // NOI18N
        eventSourceText.setWrapStyleWord(true);
        eventSourceText.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                eventSourceTextMouseWheelMoved(evt);
            }
        });
        eventSourceScrollPane.setViewportView(eventSourceText);

        repoText.setEditable(false);
        repoText.setText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.repoText.text")); // NOI18N
        repoText.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.repoText.toolTipText")); // NOI18N

        repoEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/repository.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(repoEditButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.repoEditButton.text")); // NOI18N
        repoEditButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.repoEditButton.toolTipText")); // NOI18N
        repoEditButton.setIconTextGap(0);
        repoEditButton.setPreferredSize(new java.awt.Dimension(30, 26));
        repoEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repoEditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout repoPanelLayout = new javax.swing.GroupLayout(repoPanel);
        repoPanel.setLayout(repoPanelLayout);
        repoPanelLayout.setHorizontalGroup(
            repoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repoPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(repoText)
                .addGap(2, 2, 2)
                .addComponent(repoEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        repoPanelLayout.setVerticalGroup(
            repoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, repoPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(repoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(repoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repoEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        scrollSourcesEvent.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollSourcesEventMouseWheelMoved(evt);
            }
        });
        scrollSourcesEvent.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollSourcesEventAdjustmentValueChanged(evt);
            }
        });

        addSourceEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addSourceEventButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addSourceEventButton.text")); // NOI18N
        addSourceEventButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.addSourceEventButton.toolTipText")); // NOI18N
        addSourceEventButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addSourceEventButton.setIconTextGap(0);
        addSourceEventButton.setPreferredSize(new java.awt.Dimension(16, 16));
        addSourceEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSourceEventButtonActionPerformed(evt);
            }
        });

        delSourceEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(delSourceEventButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delSourceEventButton.text")); // NOI18N
        delSourceEventButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.delSourceEventButton.toolTipText")); // NOI18N
        delSourceEventButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delSourceEventButton.setIconTextGap(0);
        delSourceEventButton.setPreferredSize(new java.awt.Dimension(16, 16));
        delSourceEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delSourceEventButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout eventSourcePanelLayout = new javax.swing.GroupLayout(eventSourcePanel);
        eventSourcePanel.setLayout(eventSourcePanelLayout);
        eventSourcePanelLayout.setHorizontalGroup(
            eventSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventSourcePanelLayout.createSequentialGroup()
                .addGroup(eventSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventSourcePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventSourceTitle))
                    .addComponent(eventSourceScrollPane)
                    .addComponent(repoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(eventSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(scrollSourcesEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSourceEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delSourceEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        eventSourcePanelLayout.setVerticalGroup(
            eventSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventSourcePanelLayout.createSequentialGroup()
                .addComponent(scrollSourcesEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(addSourceEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(delSourceEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(eventSourcePanelLayout.createSequentialGroup()
                .addGroup(eventSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventSourceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(eventSourceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(repoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        assoComboBox.setMaximumRowCount(20);
        assoComboBox.setModel(cbModel);
        assoComboBox.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.assoComboBox.toolTipText")); // NOI18N

        assoEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/association.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(assoEditButton, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.assoEditButton.text")); // NOI18N
        assoEditButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.assoEditButton.toolTipText")); // NOI18N
        assoEditButton.setIconTextGap(0);
        assoEditButton.setPreferredSize(new java.awt.Dimension(30, 26));
        assoEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assoEditButtonActionPerformed(evt);
            }
        });

        assoEditIndi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/editindi.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(assoEditIndi, org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.assoEditIndi.text")); // NOI18N
        assoEditIndi.setToolTipText(org.openide.util.NbBundle.getMessage(IndiPanel.class, "IndiPanel.assoEditIndi.toolTipText")); // NOI18N
        assoEditIndi.setIconTextGap(0);
        assoEditIndi.setPreferredSize(new java.awt.Dimension(30, 26));
        assoEditIndi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assoEditIndiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assoPanelLayout = new javax.swing.GroupLayout(assoPanel);
        assoPanel.setLayout(assoPanelLayout);
        assoPanelLayout.setHorizontalGroup(
            assoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assoPanelLayout.createSequentialGroup()
                .addComponent(assoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(assoEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(assoEditIndi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        assoPanelLayout.setVerticalGroup(
            assoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(assoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(assoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(assoEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(assoEditIndi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout eventRightLayout = new javax.swing.GroupLayout(eventRight);
        eventRight.setLayout(eventRightLayout);
        eventRightLayout.setHorizontalGroup(
            eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventRightLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(eventRightLayout.createSequentialGroup()
                        .addComponent(datelabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eventRightLayout.createSequentialGroup()
                                .addComponent(dayOfWeek)
                                .addGap(18, 18, 18)
                                .addComponent(ageAtEvent)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(eventRightLayout.createSequentialGroup()
                                .addComponent(eventDate, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                .addGap(2, 2, 2))))
                    .addGroup(eventRightLayout.createSequentialGroup()
                        .addComponent(eventTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventDescription))
                    .addGroup(eventRightLayout.createSequentialGroup()
                        .addComponent(placeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventPlaceCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(eventPlaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addComponent(eventNotePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(eventSourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        eventRightLayout.setVerticalGroup(
            eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventRightLayout.createSequentialGroup()
                .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(datelabel)
                    .addComponent(eventDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayOfWeek)
                    .addComponent(ageAtEvent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eventRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(placeLabel)
                    .addComponent(eventPlaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventPlaceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventNotePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventSourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        eventSplitPane.setRightComponent(eventRight);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(eventSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mediaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneFamily, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(separator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(indiAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(warningButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(indiDelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(indiAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indiDelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warningButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(namePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPaneFamily, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addComponent(mediaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(eventSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void maleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleRadioButtonActionPerformed
        changes.setChanged(true);
        if (mediaSet == null || mediaSet.isEmpty()) {
            setPhoto(null, PropertySex.MALE);
        }
    }//GEN-LAST:event_maleRadioButtonActionPerformed

    private void femaleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleRadioButtonActionPerformed
        changes.setChanged(true);
        if (mediaSet == null || mediaSet.isEmpty()) {
            setPhoto(null, PropertySex.FEMALE);
        }
    }//GEN-LAST:event_femaleRadioButtonActionPerformed

    private void unknownRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unknownRadioButtonActionPerformed
        changes.setChanged(true);
        if (mediaSet == null || mediaSet.isEmpty()) {
            setPhoto(null, PropertySex.UNKNOWN);
        }
    }//GEN-LAST:event_unknownRadioButtonActionPerformed

    private void privateCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateCheckBoxActionPerformed
        changes.setChanged(true);
    }//GEN-LAST:event_privateCheckBoxActionPerformed

    private void scrollPhotosAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollPhotosAdjustmentValueChanged
        if (isBusyMedia) {
            return;
        }
        int i = scrollPhotos.getValue();
        if (mediaSet != null && !mediaSet.isEmpty() && i >= 0 && i < mediaSet.size() && i != mediaIndex) {
            mediaIndex = scrollPhotos.getValue();
            displayPhoto();
        }
    }//GEN-LAST:event_scrollPhotosAdjustmentValueChanged

    private void photosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (chooseMedia(mediaIndex)) {
                displayPhoto();
                textAreaPhotos.requestFocus();
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            if (mediaSet != null && !mediaSet.isEmpty() && (mediaIndex >= 0) && (mediaIndex < mediaSet.size())) {
                try {
                    if (mediaSet.get(mediaIndex) != null && mediaSet.get(mediaIndex).getFile() != null) {
                        Desktop.getDesktop().open(mediaSet.get(mediaIndex).getFile());
                    }
                } catch (IOException ex) {
                    //Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_photosMouseClicked

    private void addMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMediaButtonActionPerformed
        if (chooseMedia(mediaSet.size())) {
            displayPhoto();
            textAreaPhotos.requestFocus();
        }
    }//GEN-LAST:event_addMediaButtonActionPerformed

    private void delMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delMediaButtonActionPerformed
        if (mediaSet != null && !mediaSet.isEmpty() && (mediaIndex >= 0) && (mediaIndex < mediaSet.size())) {
            MediaWrapper media = mediaSet.get(mediaIndex);
            mediaRemovedSet.add(media);
            mediaSet.remove(mediaIndex);
            mediaIndex--;
            if (mediaIndex < 0) {
                mediaIndex = 0;
            }
            changes.setChanged(true);
        }
        displayPhoto();        
    }//GEN-LAST:event_delMediaButtonActionPerformed

    private void brothersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brothersButtonActionPerformed
        showPopupFamilyMenu(brothersButton, IndiCreator.REL_BROTHER, null, indi.getBrothers(true));
    }//GEN-LAST:event_brothersButtonActionPerformed

    private void sistersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sistersButtonActionPerformed
        showPopupFamilyMenu(sistersButton, IndiCreator.REL_SISTER, null, indi.getSisters(true));
    }//GEN-LAST:event_sistersButtonActionPerformed

    private void spousesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spousesButtonActionPerformed
        showPopupFamilyMenu(spousesButton, IndiCreator.REL_PARTNER, null, indi.getPartners());
    }//GEN-LAST:event_spousesButtonActionPerformed

    private void childrenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_childrenButtonActionPerformed
        showPopupFamilyMenu(childrenButton, IndiCreator.REL_CHILD, null, indi.getChildren());
    }//GEN-LAST:event_childrenButtonActionPerformed

    private void fatherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatherButtonActionPerformed
        showPopupFamilyMenu(fatherButton, IndiCreator.REL_FATHER, indi.getBiologicalFather(), null);
    }//GEN-LAST:event_fatherButtonActionPerformed

    private void motherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motherButtonActionPerformed
        showPopupFamilyMenu(motherButton, IndiCreator.REL_MOTHER, indi.getBiologicalMother(), null);
    }//GEN-LAST:event_motherButtonActionPerformed

    private void moreNamesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreNamesButtonActionPerformed
        DialogManager.create(NbBundle.getMessage(getClass(), "IndiPanel.moreNamesButton.toolTipText"), nameDetails).setMessageType(DialogManager.PLAIN_MESSAGE).setOptionType(OK_ONLY_OPTION).show();
        String f = nameDetails.getFirstName();
        String l = nameDetails.getLastName();
        if (!firstnamesText.getText().equals(f)) {
            firstnamesText.setText(f);
        }
        if (!lastnameText.getText().equals(l)) {
            lastnameText.setText(l);
        }
    }//GEN-LAST:event_moreNamesButtonActionPerformed

    private void eventBuriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventBuriButtonActionPerformed
        createOrPreSelectEvent("BURI");
        selectEvent(getRowFromIndex(eventIndex));
        eventDescription.requestFocus();
    }//GEN-LAST:event_eventBuriButtonActionPerformed

    private void scrollNotesEventAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollNotesEventAdjustmentValueChanged
        if (isBusyEventNote) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        int i = scrollNotesEvent.getValue();
        if (event.eventNoteSet != null && !event.eventNoteSet.isEmpty() && i >= 0 && i < event.eventNoteSet.size() && i != event.eventNoteIndex) {
            event.eventNoteIndex = scrollNotesEvent.getValue();
            displayEventNote(event);
        }
    }//GEN-LAST:event_scrollNotesEventAdjustmentValueChanged

    private void scrollPhotosMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollPhotosMouseWheelMoved
        int notches = evt.getWheelRotation();
        if (isBusyMedia) {
            return;
        }
        if (mediaSet != null && !mediaSet.isEmpty()) {
            int i = mediaIndex + notches;
            if (i >= mediaSet.size()) {
                i = mediaSet.size() - 1;
            }
            if (i < 0) {
                i = 0;
            }
            mediaIndex = i;
            displayPhoto();
        }
        
        
    }//GEN-LAST:event_scrollPhotosMouseWheelMoved

    private void scrollNotesEventMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollNotesEventMouseWheelMoved
        int notches = evt.getWheelRotation();
        scrollEventNotes(notches);
    }//GEN-LAST:event_scrollNotesEventMouseWheelMoved

    private void eventNoteMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_eventNoteMouseWheelMoved
        int notches = evt.getWheelRotation();
        if (evt.isControlDown()) {
            scrollEventNotes(notches);
        } else {
            JScrollBar vbar = eventNoteScrollPane.getVerticalScrollBar();
            int currentPosition = vbar.getValue();
            vbar.setValue(currentPosition + notches * vbar.getBlockIncrement() * 3);
        }
    }//GEN-LAST:event_eventNoteMouseWheelMoved

    private void addNoteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteEventButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseEventNote(event, event.eventNoteSet.size())) {
            displayEventNote(event);
            eventNote.requestFocus();
        }
    }//GEN-LAST:event_addNoteEventButtonActionPerformed

    private void delNoteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delNoteEventButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (event.eventNoteSet != null && !event.eventNoteSet.isEmpty() && (event.eventNoteIndex >= 0) && (event.eventNoteIndex < event.eventNoteSet.size())) {
            NoteWrapper note = event.eventNoteSet.get(event.eventNoteIndex);
            event.eventNoteRemovedSet.add(note);
            event.eventNoteSet.remove(event.eventNoteIndex);
            event.eventNoteIndex--;
            if (event.eventNoteIndex < 0) {
                event.eventNoteIndex = 0;
            }
            changes.setChanged(true);
        }
        displayEventNote(event);
    }//GEN-LAST:event_delNoteEventButtonActionPerformed

    private void eventSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_eventSplitPanePropertyChange
        registry.put("eventSplitDividerLocation", eventSplitPane.getDividerLocation());
        imagePanel.redraw();
    }//GEN-LAST:event_eventSplitPanePropertyChange

    private void addSourceEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSourceEventButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseSource(event, event.eventSourceSet.size())) {
            displayEventSource(event);
            eventSourceTitle.requestFocus();
        }
    }//GEN-LAST:event_addSourceEventButtonActionPerformed

    private void scrollSourcesEventAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollSourcesEventAdjustmentValueChanged
        if (isBusyEventSource) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        int i = scrollSourcesEvent.getValue();
        if (event.eventSourceSet != null && !event.eventSourceSet.isEmpty() && i >= 0 && i < event.eventSourceSet.size() && i != event.eventSourceIndex) {
            event.eventSourceIndex = scrollSourcesEvent.getValue();
            displayEventSource(event);
        }
    }//GEN-LAST:event_scrollSourcesEventAdjustmentValueChanged

    private void scrollSourcesEventMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollSourcesEventMouseWheelMoved
        int notches = evt.getWheelRotation();
        scrollEventSources(notches);
    }//GEN-LAST:event_scrollSourcesEventMouseWheelMoved

    private void delSourceEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delSourceEventButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (event.eventSourceSet != null && !event.eventSourceSet.isEmpty() && (event.eventSourceIndex >= 0) && (event.eventSourceIndex < event.eventSourceSet.size())) {
            SourceWrapper source = event.eventSourceSet.get(event.eventSourceIndex);
            event.eventSourceRemovedSet.add(source);
            event.eventSourceSet.remove(event.eventSourceIndex);
            event.eventSourceIndex--;
            if (event.eventSourceIndex < 0) {
                event.eventSourceIndex = 0;
            }
            changes.setChanged(true);
        }
        displayEventSource(event);
    }//GEN-LAST:event_delSourceEventButtonActionPerformed

    private void repoEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repoEditButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseRepository(event)) {
            displayEventSource(event);
        }
        eventSourceTitle.requestFocus();

    }//GEN-LAST:event_repoEditButtonActionPerformed

    private void assoEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assoEditButtonActionPerformed
        if (manageAssociations()) {
            displayAssociationsComboBox();
            selectAssociation();
        }
        eventSourceTitle.requestFocus();

    }//GEN-LAST:event_assoEditButtonActionPerformed

    private void eventSourceTextMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_eventSourceTextMouseWheelMoved
        int notches = evt.getWheelRotation();
        if (evt.isControlDown()) {
            scrollEventSources(notches);
        } else {
            JScrollBar vbar = eventSourceScrollPane.getVerticalScrollBar();
            int currentPosition = vbar.getValue();
            vbar.setValue(currentPosition + notches * vbar.getBlockIncrement() * 3);
        }
    }//GEN-LAST:event_eventSourceTextMouseWheelMoved

    private void assoEditIndiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assoEditIndiActionPerformed
        AssoWrapper asso = (AssoWrapper) assoComboBox.getSelectedItem();
        if (asso.assoIndi != null) {
            SelectionDispatcher.fireSelection(new Context(asso.assoIndi));
        }
    }//GEN-LAST:event_assoEditIndiActionPerformed

    private void eventBirtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventBirtButtonActionPerformed
        createOrPreSelectEvent("BIRT");
        selectEvent(getRowFromIndex(eventIndex));
        eventDescription.requestFocus();
    }//GEN-LAST:event_eventBirtButtonActionPerformed

    private void eventBaptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventBaptButtonActionPerformed
        createOrPreSelectEvent("CHR");
        selectEvent(getRowFromIndex(eventIndex));
        eventDescription.requestFocus();
    }//GEN-LAST:event_eventBaptButtonActionPerformed

    private void eventMarrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventMarrButtonActionPerformed
        showPopupEventMenu(eventMarrButton, "MARR", "EventMenu_AddMarr", "EventMenu_DisplayNextMarr");
    }//GEN-LAST:event_eventMarrButtonActionPerformed

    private void eventDeatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventDeatButtonActionPerformed
        createOrPreSelectEvent("DEAT");
        selectEvent(getRowFromIndex(eventIndex));
        eventDescription.requestFocus();
    }//GEN-LAST:event_eventDeatButtonActionPerformed

    private void eventOccuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventOccuButtonActionPerformed
        showPopupEventMenu(eventOccuButton, "OCCU", "EventMenu_AddOccu", "EventMenu_DisplayNextOccu");
    }//GEN-LAST:event_eventOccuButtonActionPerformed

    private void eventRetiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventRetiButtonActionPerformed
        showPopupEventMenu(eventRetiButton, "RETI", "EventMenu_AddReti", "EventMenu_DisplayNextReti");
    }//GEN-LAST:event_eventRetiButtonActionPerformed

    private void eventResiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventResiButtonActionPerformed
        showPopupEventMenu(eventResiButton, "RESI", "EventMenu_AddResi", "EventMenu_DisplayNextResi");
    }//GEN-LAST:event_eventResiButtonActionPerformed

    private void eventRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventRemoveButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        eventRemovedSet.add(event);
        eventSet.remove(eventIndex);
        int row = getRowFromIndex(eventIndex);
        if (row == eventSet.size()) {
            row--;
        }
        if (row < 0) {
            row = 0;
        }
        displayEventTable();
        selectEvent(row);
        changes.setChanged(true);
    }//GEN-LAST:event_eventRemoveButtonActionPerformed

    private void eventOthersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventOthersButtonActionPerformed
        showPopupEventMenu(eventOthersButton);
    }//GEN-LAST:event_eventOthersButtonActionPerformed

    private void warningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warningButtonActionPerformed
        showWarningsAndErrors();
    }//GEN-LAST:event_warningButtonActionPerformed

    private void indiAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indiAddButtonActionPerformed
        IndiCreator indiCreator = new IndiCreator(IndiCreator.CREATION, indi, IndiCreator.REL_NONE, null, null);
        SelectionDispatcher.fireSelection(new Context(indiCreator.getIndi()));
    }//GEN-LAST:event_indiAddButtonActionPerformed

    private void indiDelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indiDelButtonActionPerformed
        if (DialogManager.YES_OPTION == DialogManager.create(NbBundle.getMessage(getClass(), "TITL_WARNING_Delete_Indi"), NbBundle.getMessage(getClass(), "MSG_WARNING_Delete_Indi", indi.toString())).setMessageType(DialogManager.WARNING_MESSAGE).setOptionType(DialogManager.YES_NO_OPTION).show()) {
            new IndiCreator(IndiCreator.DESTROY, indi, IndiCreator.REL_NONE, null, null);
        }
    }//GEN-LAST:event_indiDelButtonActionPerformed

    private void changeNoteImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeNoteImgMouseClicked
        if (!changeNoteImg.isEnabled()) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseEventNote(event, event.eventNoteIndex)) {
            displayEventNote(event);
            eventNote.requestFocus();
        }
    }//GEN-LAST:event_changeNoteImgMouseClicked

    private void eventPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventPlaceButtonActionPerformed
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseEventPlace(event)) {
            eventPlaceText.setText(event.place.getDisplayValue());
            eventPlaceText.requestFocus();
        }
    }//GEN-LAST:event_eventPlaceButtonActionPerformed

    
    private void scrollEventNotes(int notches) {
        if (isBusyEventNote) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (event.eventNoteSet != null && !event.eventNoteSet.isEmpty()) {
            int i = event.eventNoteIndex + notches;
            if (i >= event.eventNoteSet.size()) {
                i = event.eventNoteSet.size() - 1;
            }
            if (i < 0) {
                i = 0;
            }
            event.eventNoteIndex = i;
            displayEventNote(event);
        }
    }

    private void scrollEventSources(int notches) {
        if (isBusyEventSource) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (event.eventSourceSet != null && !event.eventSourceSet.isEmpty()) {
            int i = event.eventSourceIndex + notches;
            if (i >= event.eventSourceSet.size()) {
                i = event.eventSourceSet.size() - 1;
            }
            if (i < 0) {
                i = 0;
            }
            event.eventSourceIndex = i;
            displayEventSource(event);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomButtonsPanel;
    private javax.swing.JPanel TopButtonsdPanel;
    private javax.swing.JButton addMediaButton;
    private javax.swing.JButton addNoteEventButton;
    private javax.swing.JButton addSourceEventButton;
    private javax.swing.JLabel ageAtEvent;
    private javax.swing.JComboBox assoComboBox;
    private javax.swing.JButton assoEditButton;
    private javax.swing.JButton assoEditIndi;
    private javax.swing.JPanel assoPanel;
    private javax.swing.JButton brothersButton;
    private javax.swing.ButtonGroup buttonGender;
    private javax.swing.JLabel changeNoteImg;
    private javax.swing.JButton childrenButton;
    private javax.swing.JLabel datelabel;
    private javax.swing.JLabel dayOfWeek;
    private javax.swing.JButton delMediaButton;
    private javax.swing.JButton delNoteEventButton;
    private javax.swing.JButton delSourceEventButton;
    private javax.swing.JButton eventBaptButton;
    private javax.swing.JButton eventBirtButton;
    private javax.swing.JButton eventBuriButton;
    private genj.edit.beans.DateBean eventDate;
    private javax.swing.JButton eventDeatButton;
    private javax.swing.JTextField eventDescription;
    private javax.swing.JPanel eventLeft;
    private javax.swing.JButton eventMarrButton;
    private javax.swing.JTextArea eventNote;
    private javax.swing.JPanel eventNotePanel;
    private javax.swing.JScrollPane eventNoteScrollPane;
    private javax.swing.JButton eventOccuButton;
    private javax.swing.JButton eventOthersButton;
    private javax.swing.JButton eventPlaceButton;
    private javax.swing.JComboBox eventPlaceCombo;
    private javax.swing.JButton eventRemoveButton;
    private javax.swing.JButton eventResiButton;
    private javax.swing.JButton eventRetiButton;
    private javax.swing.JPanel eventRight;
    private javax.swing.JScrollPane eventScrollPane;
    private javax.swing.JPanel eventSourcePanel;
    private javax.swing.JScrollPane eventSourceScrollPane;
    private javax.swing.JTextArea eventSourceText;
    private javax.swing.JTextField eventSourceTitle;
    private javax.swing.JSplitPane eventSplitPane;
    private javax.swing.JTable eventTable;
    private javax.swing.JLabel eventTitle;
    private javax.swing.JTree familyTree;
    private javax.swing.JButton fatherButton;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JComboBox firstnamesCombo;
    private javax.swing.JLabel firstnamesLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JButton indiAddButton;
    private javax.swing.JButton indiDelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox lastnameCombo;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JPanel mediaPanel;
    private javax.swing.JLabel modificationLabel;
    private javax.swing.JButton moreNamesButton;
    private javax.swing.JButton motherButton;
    private javax.swing.JPanel namePanel;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel photos;
    private javax.swing.JLabel placeLabel;
    private javax.swing.JCheckBox privateCheckBox;
    private javax.swing.JButton repoEditButton;
    private javax.swing.JPanel repoPanel;
    private javax.swing.JTextField repoText;
    private javax.swing.JScrollBar scrollNotesEvent;
    private javax.swing.JScrollPane scrollPaneFamily;
    private javax.swing.JScrollPane scrollPanePhotos;
    private javax.swing.JScrollBar scrollPhotos;
    private javax.swing.JScrollBar scrollSourcesEvent;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton sistersButton;
    private javax.swing.JLabel sosaLabel;
    private javax.swing.JPanel sourceImagePanel;
    private javax.swing.JPanel sourcePanel;
    private javax.swing.JButton spousesButton;
    private javax.swing.JTextArea textAreaPhotos;
    private javax.swing.JLabel title;
    private javax.swing.JRadioButton unknownRadioButton;
    private javax.swing.JButton warningButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getEditorComponent() {
        return this;
    }

    @Override
    public ViewContext getContext() {
        Property prop = getCurrentEvent().eventProperty;
        if (indi.contains(prop)) {  
            context = new Context(prop);
        } else {
            context = new Context(indi); // newly created properties are not yet attached to indi and point to a temporary gedcom
        }
        return new ViewContext(context);
    }

    @Override
    public void setGedcomHasChanged(boolean flag) {
        // Force Reload
        reloadData = true;
        
        // Remember selections
        EventWrapper ew = getCurrentEvent();            
        savedMediaIndex = mediaIndex;
        savedEventTagDateDesc = ew.getEventKey(flag);
        savedEventNoteIndex = ew.eventNoteIndex;
        savedEventSourceIndex = ew.eventSourceIndex;
        savedFocusedControl = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    }
    
    @Override
    protected void setContextImpl(Context context) {
        LOG.finer(TimingUtility.geInstance().getTime() + ": setContextImpl().start");

        // force data reload if to be reloaded or if entity selected is different
        if (reloadData || (this.context != null && context != null && !this.context.equals(context) && this.context.getEntity() != context.getEntity())) {
            reloadData = true;
        }
        
        this.context = context;
        Entity entity = context.getEntity();
        if (entity != null && (entity instanceof Indi)) {
            this.indi = (Indi) entity;
            this.gedcom = indi.getGedcom();

            if (reloadData) {  // do not reload data when not necessary, for performance reasons when selecting properties in Gedcom editor for instance
                loadData();
                warningButton.setVisible(passControls());
                reloadData = false;
            }

            if (!listernersOn) {
                addListeners();
                listernersOn = true;
            }
            
            // Focus saved focused field, or else on firstnames
            WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
                @Override
                public void run() {
                    if (savedFocusedControl == null || !savedFocusedControl.isFocusable()) {
                        firstnamesText.setCaretPosition(firstnamesText.getText().length());
                        firstnamesText.requestFocus();
                    } else {
                        if (savedFocusedControl instanceof JTextField) {
                            JTextField jtf = (JTextField) savedFocusedControl;
                            jtf.setCaretPosition(jtf.getText().length());
                        }
                        savedFocusedControl.requestFocus();
                        savedFocusedControl = null;
                    }
                }
            });
            
            // Overwrite and select default context property if any
            selectPropertyContext(context);
            
            // Reset change flag
            changes.setChanged(false);
            
        }

        LOG.finer(TimingUtility.geInstance().getTime() + ": setContextImpl().finish");
    }

    /**
     * Select event corresponding to property
     * - if context is of different entity
     * - if context is an event, select if
     * TODO:
     * - if context if a wedding type event, select it
     * - if context is an Asso type data, select it ?
     * - if context is a note, select it
     * - if context is a source, select it
     * 
     * @param context 
     */
    private void selectPropertyContext(Context context) {
        // Select event selected when last saved (it if not necessarily a property in case it is being created for instance)
        if (!savedEventTagDateDesc.equals("-1") && eventSet != null) {
            scrollPhotos.setValue(savedMediaIndex);             savedMediaIndex = -1;
            selectEvent(savedEventTagDateDesc);                 savedEventTagDateDesc = "-1";
            scrollNotesEvent.setValue(savedEventNoteIndex);     savedEventNoteIndex = -1;
            scrollSourcesEvent.setValue(savedEventSourceIndex); savedEventSourceIndex = -1;
            return;
        } 
        
        // Else select property if any (coming from fireSelection)
        Property propertyToDisplay = context.getProperty();
        if (propertyToDisplay != null && eventSet != null) {
            Property ent = propertyToDisplay.getEntity();
            while (propertyToDisplay != ent) {
                for (EventWrapper event : eventSet) {
                    if (event.eventProperty.equals(propertyToDisplay)) {
                        int index = eventSet.indexOf(event);
                        if (index != -1) {
                            selectEvent(getRowFromIndex(index));
                            return;
                        }
                    }
                } // end for
                propertyToDisplay = propertyToDisplay.getParent();
            }
        }

        // Else select first row if eventSet not empty
        if (eventSet != null) {
            selectEvent(0);
        }
        
    }
    
    private void selectEvent(String key) {
        if (eventTable.getRowCount() == 0) {
            return;
        }
        for (EventWrapper event : eventSet) {
            if (key.equals(event.getEventKey())) {
                selectEvent(getRowFromIndex(eventSet.indexOf(event)));
                return;
            }
        }
        selectEvent(0);
    }

    private void selectEvent(int row) {
        if (eventTable.getRowCount() == 0) {
            return;
        }
        if (row < 0 || row >= eventTable.getRowCount()) {
            row = 0;
        }
        eventTable.setRowSelectionInterval(row, row);
        eventTable.scrollRectToVisible(new Rectangle(eventTable.getCellRect(row, 0, true)));
        eventIndex = eventTable.convertRowIndexToModel(eventTable.getSelectedRow());
    }

    private int getRowFromIndex(int index) {
        if (eventTable != null && eventTable.getRowCount() > 0) {
            return eventTable.convertRowIndexToView(index);
        }
        return -1;
    }
    
    
    @Override
    public void commit() throws GedcomException {
        saveData();
    }


    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    private void loadData() {
        String str = "";
        int i = 0;
        boolean privateTagFound = false;
        refNotes.clear();
        refSources.clear();
        
        // Title
        title.setText("<html> <font color=\"red\"><b>/!\\ UNDER CONSTRUCTION /!\\</b></font> " + indi.getFirstName() + " " + indi.getLastName() + " </html> ");

        // IDs
        idLabel.setText(NbBundle.getMessage(IndiPanel.class, "IndiPanel.idLabel.text") + " " + indi.getId());
        str = indi.getPropertyDisplayValue(SOSA_TAG); // TODO : prévoir sosa daboville en référence utilisateur
        if (str == null || str.isEmpty()) {
            str = NO_SOSA;
        }
        sosaLabel.setText(NbBundle.getMessage(IndiPanel.class, "IndiPanel.sosaLabel.text") + " " + str);

        // Names
        allFirstNames = PropertyName.getFirstNames(gedcom, true);
        AutoCompletion.reset(firstnamesCombo, allFirstNames);
        allLastNames = PropertyName.getLastNames(gedcom, true);
        AutoCompletion.reset(lastnameCombo, allLastNames);
        firstnamesText.setText(indi.getFirstName());
        lastnameText.setText(indi.getLastName());
        nameDetails.setDetails(indi);

        // Sex
        i = indi.getSex();
        if (i == PropertySex.MALE) {
            maleRadioButton.setSelected(true);
        } else if (i == PropertySex.FEMALE) {
            femaleRadioButton.setSelected(true);
        } else {
            unknownRadioButton.setSelected(true);
        }
        
        // Privacy
        privateTagFound = (indi.getProperty(Options.getInstance().getPrivateTag()) != null);
        privateCheckBox.setSelected(privateTagFound);
        
        // Medias
        if (mediaSet != null) {
            mediaSet.clear();
            mediaSet = null;
        }
        if (mediaRemovedSet != null) {
            mediaRemovedSet.clear();
            mediaRemovedSet = null;
        }
        mediaSet = getMedia(indi);
        mediaRemovedSet = new ArrayList<MediaWrapper>();
        scrollPhotos.setMinimum(0);
        scrollPhotos.setBlockIncrement(1);
        scrollPhotos.setUnitIncrement(1);
        mediaIndex = 0;
        displayPhoto();

        // Family tree (parents, siblings, mariages and corresponding childrens)
        createFamilyNodes(indi);
        familyTree.repaint();
        
        // Places
        allPlaces = gedcom.getReferenceSet("PLAC").getKeys(gedcom.getCollator());
        AutoCompletion.reset(eventPlaceCombo, allPlaces);
        
        // Events (with description, date, place, note, source)
        if (eventSet != null) {
            eventSet.clear();
            eventSet = null;
        }
        if (eventRemovedSet != null) {
            eventRemovedSet.clear();
            eventRemovedSet = null;
        }
        eventSet = getEvents(indi);
        eventRemovedSet = new ArrayList<EventWrapper>();
        displayEventTable();
        eventIndex = 0;
        
        // Associations
        if (assoSet != null) {
            assoSet.clear();
            assoSet = null;
        }
        if (assoRemovedSet != null) {
            assoRemovedSet.clear();
            assoRemovedSet = null;
        }
        assoSet = getAssociations(indi);
        assoRemovedSet = new ArrayList<AssoWrapper>();
        displayAssociationsComboBox();
        
        // Modification timestamp
        modificationLabel.setText(NbBundle.getMessage(IndiPanel.class, "IndiPanel.modificationLabel.text") + " : " + (indi.getLastChange() != null ? indi.getLastChange().getDisplayValue() : ""));
        //

    }

    private void addListeners() {
        firstnamesText.getDocument().addDocumentListener(this);
        lastnameText.getDocument().addDocumentListener(this);
        nameDetails.addListeners(this);
        textAreaPhotos.getDocument().addDocumentListener(new PhotoTitleListener());

        eventDescription.getDocument().addDocumentListener(new EventDescriptionListener());
        eventDate.addChangeListener(new EventDateListener());
        eventPlaceText.getDocument().addDocumentListener(new EventPlaceListener());
        
        eventNote.getDocument().addDocumentListener(new EventNoteTextListener());
        EventSourceTextListener estl = new EventSourceTextListener();
        eventSourceTitle.getDocument().addDocumentListener(estl);
        eventSourceText.getDocument().addDocumentListener(estl);
        
    }

    
    
    private void saveData() {
        
        // Save Indi and Sex
        indi.setName(firstnamesText.getText(), lastnameText.getText());
        nameDetails.saveDetails(indi);
        
        //
        indi.setSex(getSex());
        //
        
        // Save privacy
        boolean privateTagFound = (indi.getProperty(Options.getInstance().getPrivateTag()) != null);
        if (privateCheckBox.isSelected()) {
            if (!privateTagFound) {
                indi.addProperty(Options.getInstance().getPrivateTag(), "");
            }
        } else {
            if (privateTagFound) {
                indi.delProperty(indi.getProperty(Options.getInstance().getPrivateTag()));
            }
        }
        
        // Save the rest
        //
        saveMedia();

        //
        saveEvents();

        //
        saveAssociations();
        //.......................................
        
        
    }

    

    
    
    /***************************************************************************
     * Media
     */
    
    private List<MediaWrapper> getMedia(Indi indi) {
        List<MediaWrapper> ret = new ArrayList<MediaWrapper>();
        
        // Look for media directly attached to indi (media always have a file, so look for all files underneath OBJE but not underneath SOUR)
        for (PropertyFile propFile : indi.getProperties(PropertyFile.class)) {
            if (!Utils.parentTagsContains(propFile, "SOUR")) {
                Property propObje = getParentTag(propFile, "OBJE");
                ret.add(new MediaWrapper(propObje));
            }
        }
        
        // Look for media as links to entities
        for (PropertyMedia propMedia : indi.getProperties(PropertyMedia.class)) {
            if (!Utils.parentTagsContains(propMedia.getParent(), "SOUR")) {
                ret.add(new MediaWrapper(propMedia));
            }
        }
        
        return ret;
    }

    
    private void saveMedia() {
        //mediaSet
        for (MediaWrapper media : mediaSet) {
            media.update(indi);
        }
        for (MediaWrapper media : mediaRemovedSet) {
            media.remove(indi);
        }
    }

    
    

    
    private Property getParentTag(Property prop, String tag) {
        if (prop == null) {
            return null;
        }
        Property parent = prop.getParent();
        if (parent == null) {
            return null;
        }
        if (parent.getTag().equals(tag)) {
            return parent;
        }
        return getParentTag(parent, tag);
    }

    
    
    
    private void displayPhoto() {
        isBusyMedia = true;
        if (mediaSet != null && !mediaSet.isEmpty() && (mediaIndex >= 0) && (mediaIndex < mediaSet.size())) {        
            setPhoto(mediaSet.get(mediaIndex), indi.getSex());
        } else {
            setPhoto(null, getSex());
        }
        isBusyMedia = false;
    }
    
    private void setPhoto(MediaWrapper media, int sex) {
        
        File file = null;
        String localTitle = "";
        ImageIcon defaultIcon = new ImageIcon(getSexImage(sex));
        ImageIcon imageIcon = null;
        
        if (media != null) {
            file = media.getFile();
            localTitle = media.getTitle();
        }
        
        // Photo
        if (file != null && file.exists()) {
            imageIcon = new ImageIcon(getImageFromFile(file, getClass()));
        } else {
            imageIcon = defaultIcon;
        }

        photos.setIcon(getResizedIcon(imageIcon, PHOTO_WIDTH, PHOTO_HEIGHT));   // preferred size of photo label but getPreferredSize() does not return those values...
        photos.setText("");
        
        // Title
        textAreaPhotos.setText(localTitle);
        textAreaPhotos.setCaretPosition(0);
        
            
        // Update scroll
        scrollPhotos.setValues(mediaIndex, 1, 0, mediaSet.size());
        scrollPhotos.setToolTipText(getScrollPhotosLabel());
    }

    private String getScrollPhotosLabel() {
        return String.valueOf(mediaSet.size() > 0 ? mediaIndex + 1 : mediaIndex) + "/" + String.valueOf(mediaSet.size());
    }

    private int getSex() {
        return maleRadioButton.isSelected() ? PropertySex.MALE : femaleRadioButton.isSelected() ? PropertySex.FEMALE : PropertySex.UNKNOWN;
    }

    private Image getSexImage(int sex) {
        return (sex == PropertySex.MALE ? PHOTO_MALE : (sex == PropertySex.FEMALE ? PHOTO_FEMALE : PHOTO_UNKNOWN));
    }

    private boolean chooseMedia(int index) {
        boolean b = false;
        boolean exists = (mediaSet != null) && (!mediaSet.isEmpty()) && (index >= 0) && (index < mediaSet.size());
        
        JButton mediaButton = new JButton(NbBundle.getMessage(getClass(), "Button_ChooseMedia"));
        JButton fileButton = new JButton(NbBundle.getMessage(getClass(), "Button_LookForFile"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { mediaButton, fileButton, cancelButton };
        MediaChooser mediaChooser = new MediaChooser(gedcom, exists ? mediaSet.get(index).getFile() : null,
                exists ? getImageFromFile(mediaSet.get(index).getFile(), getClass()) : getSexImage(getSex()),
                exists ? mediaSet.get(index).getTitle() : "",
                exists ? mediaSet.get(index) : null, 
                mediaButton, cancelButton
        );
        int size = mediaChooser.getNbMedia();
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChooseMediaTitle", size), mediaChooser).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == mediaButton) {
            File file = mediaChooser.getSelectedFile();
            String mediaTitle = mediaChooser.getSelectedTitle();
            if (mediaChooser.isSelectedEntityMedia()) {
                Media entity = (Media) mediaChooser.getSelectedEntity();
                if (exists) {
                    mediaSet.get(index).setTargetEntity(entity);
                    mediaSet.get(index).setTitle(mediaTitle);
                    mediaIndex = index;
                } else {
                    MediaWrapper media = new MediaWrapper(entity);
                    media.setTitle(mediaTitle);
                    mediaSet.add(media);
                    mediaIndex = mediaSet.size() - 1;
                }
                changes.setChanged(true);
                b = true;
            } else {
                if (exists) {
                    mediaSet.get(index).setFile(file);
                    mediaSet.get(index).setTitle(mediaTitle);
                    mediaIndex = index;
                } else {
                    MediaWrapper media = new MediaWrapper(file, mediaTitle);
                    mediaSet.add(media);
                    mediaIndex = mediaSet.size() - 1;
                }
                changes.setChanged(true);
                b = true;
            }
        } else if (o == fileButton) {
            return chooseFileImage(index);
        }
        
        return b;
    }
    
    
    
    private boolean chooseFileImage(int index) {
        boolean b = false;
        boolean exists = (mediaSet != null) && (!mediaSet.isEmpty()) && (index >= 0) && (index < mediaSet.size());
        
        File file = new FileChooserBuilder(IndiPanel.class.getCanonicalName()+"Images")
                .setFilesOnly(true)
                .setDefaultBadgeProvider()
                .setTitle(NbBundle.getMessage(getClass(), "FileChooserTitle"))
                .setApproveText(NbBundle.getMessage(getClass(), "FileChooserOKButton"))
                .setDefaultExtension(FileChooserBuilder.getImageFilter().getExtensions()[0])
                .addFileFilter(FileChooserBuilder.getImageFilter())
                .addFileFilter(FileChooserBuilder.getSoundFilter())
                .addFileFilter(FileChooserBuilder.getVideoFilter())
                .setAcceptAllFileFilterUsed(false)
                .setFileHiding(true)
                .showOpenDialog();
        if (file != null) {
            if (exists) {
                mediaSet.get(index).setFile(file);
                mediaIndex = index;
            } else {
                MediaWrapper media = new MediaWrapper(file);
                mediaSet.add(media);
                mediaIndex = mediaSet.size() - 1;
            }
            changes.setChanged(true);
            b = true;
        } else {
            textAreaPhotos.requestFocus();
        }
        return b;
    }

    
    
    
    
    
    /***************************************************************************
     * Family tree
     */
    
    private void createFamilyNodes(Indi indi) {
        familyTop.removeAllChildren();
        familyTop.setUserObject(new NodeWrapper(NodeWrapper.PARENTS, indi.getFamilyWhereBiologicalChild()));
        DefaultMutableTreeNode meNode = new DefaultMutableTreeNode(new NodeWrapper(NodeWrapper.MEUNKNOWN, indi));
        Indi[] siblings = indi.getSiblings(true);
        if (siblings.length > 0) {
            for (Indi sibling : siblings) {
                if (!sibling.equals(indi)) {
                    familyTop.add(new DefaultMutableTreeNode(new NodeWrapper(NodeWrapper.SIBLING, sibling)));
                } else {
                    familyTop.add(meNode);
                }
            }
        } else {
            familyTop.add(meNode);
        }
        Fam[] fams = indi.getFamiliesWhereSpouse();
        for (Fam fam : fams) {
            DefaultMutableTreeNode famNode = new DefaultMutableTreeNode(new NodeWrapper(NodeWrapper.SPOUSE, fam, indi));
            meNode.add(famNode);
            Indi[] children = fam.getChildren(true);
            for (Indi child : children) {
                famNode.add(new DefaultMutableTreeNode(new NodeWrapper(NodeWrapper.CHILD, child)));
            }
        }

        ((DefaultTreeModel) familyTree.getModel()).reload();
        
        for (int i = 0; i < familyTree.getRowCount(); i++) {
            familyTree.expandRow(i);
        }
    }



    

    
    


    

    
    
    
    
    
    /***************************************************************************
     * Events
     */
    
    private List<EventWrapper> getEvents(Indi indi) {
        List<EventWrapper> ret = new ArrayList<EventWrapper>();
        
        // Start adding the general event which will only hold general notes and sources for the individual
        ret.add(new EventWrapper(indi, indi, refNotes, refSources));
                
        // Look for all individual events
        // - INDIVIDUAL_EVENT_STRUCTURE (birth, etc.)
        // - INDIVIDUAL_ATTRIBUTE_STRUCTURE (occu, resi, etc.)
        //
        String[] INDI_TAGS = EventUsage.getTags(eventUsages, "INDI");
        for (String tag : INDI_TAGS) {
            Property[] eventProps = indi.getProperties(tag);
            for (Property prop : eventProps) {
                if (prop != null) {
                    ret.add(new EventWrapper(prop, indi, refNotes, refSources));
                }
            }
        }
        
        // Look for all family events in which indi is a spouse
        // - FAMILY_EVENT_STRUCTURE (marr, etc.)
        //
        String[] FAM_TAGS = EventUsage.getTags(eventUsages, "FAM");
        Fam[] fams = indi.getFamiliesWhereSpouse();
        for (Fam fam : fams) {
            for (String tag : FAM_TAGS) {
                Property[] eventProps = fam.getProperties(tag);
                for (Property prop : eventProps) {
                    if (prop != null) {
                        ret.add(new EventWrapper(prop, indi, refNotes, refSources));
                    }
                }
            }
        }

        return ret;
    }

    
    private void saveEvents() {
        //eventSet
        for (EventWrapper event : eventSet) {
            event.update(indi);
        }
        
        // Remove events updating associations at the same time
        Set<AssoWrapper> tmpList = new HashSet<AssoWrapper>();
        for (EventWrapper event : eventRemovedSet) {
            for (AssoWrapper asso : assoSet) {
                if (asso.assoProp.getTargetParent() == event.eventProperty) {
                    tmpList.add(asso);
                }
            }
            event.remove(indi);
        }
        for (AssoWrapper asso : tmpList) {
            assoSet.remove(asso);
        }
    }


    private void displayEventTable() {
        EventTableModel etm = new EventTableModel(eventSet);
        eventTable.setModel(etm);    
        eventTable.setAutoCreateRowSorter(true);
        
        int maxWidth = 0;
        for (EventWrapper event : eventSet) {
            maxWidth = Math.max(maxWidth, getFontMetrics(getFont()).stringWidth(event.eventLabel.getShortLabel()));
        }
        eventTable.getColumnModel().getColumn(0).setPreferredWidth(maxWidth + 15); // add icon size
        eventTable.getColumnModel().getColumn(1).setPreferredWidth(getFontMetrics(getFont()).stringWidth(" 9999 "));
        eventTable.getColumnModel().getColumn(2).setPreferredWidth(getFontMetrics(getFont()).stringWidth(" 99.9 "));

        eventTable.setShowHorizontalLines(false);
        eventTable.setShowVerticalLines(false);

        eventTable.getColumnModel().getColumn(0).setCellRenderer(new IconTextCellRenderer());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        eventTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        eventTable.getColumnModel().getColumn(2).setCellRenderer(new DoubleCellRenderer());

        DefaultTableCellRenderer centerHRenderer = new DefaultTableCellRenderer();
        centerHRenderer.setToolTipText(NbBundle.getMessage(EventTableModel.class, "TIP_Sort"));
        centerHRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        eventTable.getColumnModel().getColumn(0).setHeaderRenderer(centerHRenderer);
        eventTable.getColumnModel().getColumn(1).setHeaderRenderer(centerHRenderer);
        eventTable.getColumnModel().getColumn(2).setHeaderRenderer(centerHRenderer);
        
        eventTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && !isBusyEvent && eventTable.getSelectedRow() != -1) {
                    eventIndex = eventTable.convertRowIndexToModel(eventTable.getSelectedRow());
                    displayEvent();
                    selectAssociation();
                }
            }
        });
        sortEventTable();
        
    }

    private void sortEventTable() {
        TableRowSorter sorter = new TableRowSorter<EventTableModel>((EventTableModel) eventTable.getModel());
        sorter.setComparator(0, new Comparator<EventLabel>() {
            public int compare(EventLabel l1, EventLabel l2) {
                Integer i1 = eventUsages.get(l1.getTag()).getOrder();
                Integer i2 = eventUsages.get(l2.getTag()).getOrder();
                return i1.compareTo(i2);
            }
        });
        sorter.setComparator(2, new Comparator<String>() {
            public int compare(String s1, String s2) {
                try {
                    if (s1.equals("-")) {
                        s1 = "0";
                    }
                    if (s2.equals("-")) {
                        s2 = "0";
                    }
                    Double d1 = new DecimalFormat(EventWrapper.AGE_FORMAT).parse(s1).doubleValue();
                    Double d2 = new DecimalFormat(EventWrapper.AGE_FORMAT).parse(s2).doubleValue();
                    return d1.compareTo(d2);
                } catch (ParseException ex) {
                    Exceptions.printStackTrace(ex);
                    return 0;
                }
            }
        });
        eventTable.setRowSorter(sorter);
        List<SortKey> sortKeys = new ArrayList<SortKey>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    private void showGeneralInformation(boolean show) {
        //eventTitle.setVisible(show);
        eventDescription.setVisible(show);
        datelabel.setVisible(show);
        eventDate.setVisible(show);
        dayOfWeek.setVisible(show);
        ageAtEvent.setVisible(show);
        placeLabel.setVisible(show);
        eventPlaceCombo.setVisible(show);
        eventPlaceButton.setVisible(show);
    }
    
    private void displayEvent() {
        isBusyEvent = true;
        eventDate.removeChangeListener(changes);

        EventWrapper event = getCurrentEvent();
        if (event != null) {        
            
            showGeneralInformation(!event.isGeneral);
            eventRemoveButton.setEnabled(eventIndex != 0);

            eventTitle.setText(event.title);
            
            if (!event.isGeneral) {
                // Title
                eventDescription.setText(event.description);
                eventDescription.setCaretPosition(0);

                // Date
                if (event.date != null) {
                    eventDate.setPropertyImpl(event.date);
                } else {
                    eventDate.setPropertyImpl(null);
                }
                ageAtEvent.setText(event.age);
                dayOfWeek.setText(event.dayOfWeek);

                // Place
                if (event.place != null) {
                    eventPlaceText.setText(event.place.getDisplayValue());
                } else {
                    eventPlaceText.setText("");
                }
                eventPlaceText.setCaretPosition(0);
            }

            // Notes
            scrollNotesEvent.setMinimum(0);
            scrollNotesEvent.setBlockIncrement(1);
            scrollNotesEvent.setUnitIncrement(1);
            event.eventNoteIndex = 0;
            displayEventNote(event);
        
            
            // Sources - Media & Text & Repo
            scrollSourcesEvent.setMinimum(0);
            scrollSourcesEvent.setBlockIncrement(1);
            scrollSourcesEvent.setUnitIncrement(1);
            event.eventSourceIndex = 0;
            displayEventSource(event);
            
        }
        isBusyEvent = false;
        eventDate.addChangeListener(changes);
    }

    
    
    
    
    
    /***************************************************************************
     * Place
     */

    private boolean chooseEventPlace(EventWrapper event) {
        
        boolean b = false;
        
        JButton OKButton = new JButton(NbBundle.getMessage(getClass(), "Button_Ok"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { OKButton, cancelButton };
        
        placeEditor.set(gedcom, event.place, false);
        placeEditor.setOKButton(OKButton);
        
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChoosePlaceTitle"), placeEditor).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        placeEditor.saveSize();
        if (o == OKButton) {
            placeEditor.copyValue(event.place);
            eventPlaceText.setText(event.place.getDisplayValue());
            eventPlaceText.setCaretPosition(0);
            b = true;
        }
        
        return b;
    }
    
    
    
    
    
    
    /***************************************************************************
     * Notes
     */

    
    private void displayEventNote(EventWrapper event) {
        isBusyEventNote = true;
        if (event.eventNoteSet != null && !event.eventNoteSet.isEmpty() && (event.eventNoteIndex >= 0) && (event.eventNoteIndex < event.eventNoteSet.size())) {        
            setEventNote(event, event.eventNoteSet.get(event.eventNoteIndex));
        } else {
            setEventNote(event, null);
        }
        isBusyEventNote = false;
    }
    
    private void setEventNote(EventWrapper event, NoteWrapper note) {
        
        String localText = "";
        
        if (note != null) {
            localText = note.getText();
        }
        
        // Icon button
        boolean enabled = (event != null) && (event.eventNoteSet.size() > 0) && (event.eventNoteIndex < event.eventNoteSet.size()) && ((event.eventNoteSet.get(event.eventNoteIndex)).isRecord());
        changeNoteImg.setEnabled(enabled);
        changeNoteImg.setToolTipText(NbBundle.getMessage(IndiPanel.class, enabled ? "IndiPanel.changeNoteImg.toolTipText" : "IndiPanel.changeNoteImg.toolTipTextOff"));
        eventNote.setCaretPosition(0);
        
        // Text
        eventNote.setText(localText);
        eventNote.setCaretPosition(0);
        
        // Update scroll
        scrollNotesEvent.setValues(event.eventNoteIndex, 1, 0, event.eventNoteSet.size());
        scrollNotesEvent.setToolTipText(getScrollEventNotesLabel(event));
    }


    private String getScrollEventNotesLabel(EventWrapper event) {
        return String.valueOf(event.eventNoteSet.size() > 0 ? event.eventNoteIndex + 1 : event.eventNoteIndex) + "/" + String.valueOf(event.eventNoteSet.size());
    }

    
    
    private boolean chooseEventNote(EventWrapper event, int index) {
        boolean b = false;
        boolean exists = (event.eventNoteSet != null) && (!event.eventNoteSet.isEmpty()) && (index >= 0) && (index < event.eventNoteSet.size());
        
        JButton noteButton = new JButton(NbBundle.getMessage(getClass(), "Button_ChooseNote"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { noteButton, cancelButton };
        NoteChooser noteChooser = new NoteChooser(gedcom, exists ? event.eventNoteSet.get(index) : null, noteButton, cancelButton);
        int size = noteChooser.getNbNotes();
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChooseNoteTitle", size), noteChooser).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == noteButton) {
            String noteText = noteChooser.getSelectedText();
            if (noteChooser.isSelectedEntityNote()) {
                Note entity = (Note) noteChooser.getSelectedEntity();
                if (exists) {
                    event.setNote(entity, noteText, index);
                } else {
                    event.addNote(entity, noteText);
                }
                changes.setChanged(true);
                b = true;
            } else {
                if (exists) {
                    event.setNote(noteText, index);
                } else {
                    event.addNote(noteText);
                }
                changes.setChanged(true);
                b = true;
            }
        }
        
        return b;
    }

    
    
    
    
    /***************************************************************************
     * Sources
     */
    
    
    private void displayEventSource(EventWrapper event) {
        isBusyEventSource = true;
        if (event.eventSourceSet != null && !event.eventSourceSet.isEmpty() && (event.eventSourceIndex >= 0) && (event.eventSourceIndex < event.eventSourceSet.size())) {        
            setEventSource(event, event.eventSourceSet.get(event.eventSourceIndex));
        } else {
            setEventSource(event, null);
        }
        isBusyEventSource = false;
    }

    private void setEventSource(EventWrapper event, SourceWrapper source) {
        
        // Title
        eventSourceTitle.setText(source == null ? "" : source.getTitle());
        eventSourceTitle.setCaretPosition(0);
        
        // Text
        eventSourceText.setText(source == null ? "" : source.getText());
        eventSourceText.setCaretPosition(0);
        
        // Media
        imagePanel.setMedia(source != null ? source.getFile() : null);
        
        // Repositoryname
        repoText.setText(source != null ? source.getRepoName() : "");
        repoText.setCaretPosition(0);
        
        // Update scroll
        scrollSourcesEvent.setValues(event.eventSourceIndex, 1, 0, event.eventSourceSet.size());
        scrollSourcesEvent.setToolTipText(getScrollEventSourcesLabel(event));
    }

    private String getScrollEventSourcesLabel(EventWrapper event) {
        return String.valueOf(event.eventSourceSet.size() > 0 ? event.eventSourceIndex + 1 : event.eventSourceIndex) + "/" + String.valueOf(event.eventSourceSet.size());
    }

    public void chooseSource() {
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        if (chooseSource(event, event.eventSourceIndex)) {
            displayEventSource(event);
            eventSourceTitle.requestFocus();
        }
    }
    
    public boolean chooseSource(EventWrapper event, int index) {
        boolean b = false;
        boolean exists = (event.eventSourceSet != null) && (!event.eventSourceSet.isEmpty()) && (index >= 0) && (index < event.eventSourceSet.size());
        
        JButton sourceButton = new JButton(NbBundle.getMessage(getClass(), "Button_ChooseSource"));
        JButton fileButton = new JButton(NbBundle.getMessage(getClass(), "Button_LookForFile"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { sourceButton, fileButton, cancelButton };
        SourceChooser sourceChooser = new SourceChooser(gedcom, imagePanel.getFile(), imagePanel.getImage(), eventSourceTitle.getText(), event.getEventSource(),sourceButton, cancelButton);
        int size = sourceChooser.getNbSource();
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChooseSourceTitle", size), sourceChooser).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == sourceButton) {
            if (sourceChooser.isSelectedEntitySource()) {
                Source entity = (Source) sourceChooser.getSelectedEntity();
                if (exists) {
                    event.setSource(entity, index);
                } else {
                    event.addSource(entity);
                }   
                changes.setChanged(true);
                b = true;
            }
        } else if (o == fileButton) {
            return chooseSourceFile(event, index);
        }
        
        return b;
    }
    

    
    private boolean chooseSourceFile(EventWrapper event, int index) {
        boolean b = false;
        boolean exists = (event.eventSourceSet != null) && (!event.eventSourceSet.isEmpty()) && (index >= 0) && (index < event.eventSourceSet.size());

        File file = new FileChooserBuilder(IndiPanel.class.getCanonicalName()+"Sources")
                .setFilesOnly(true)
                .setDefaultBadgeProvider()
                .setTitle(NbBundle.getMessage(getClass(), "FileChooserTitle"))
                .setApproveText(NbBundle.getMessage(getClass(), "FileChooserOKButton"))
                .setDefaultExtension(FileChooserBuilder.getImageFilter().getExtensions()[0])
                .addFileFilter(FileChooserBuilder.getImageFilter())
                .addFileFilter(FileChooserBuilder.getSoundFilter())
                .addFileFilter(FileChooserBuilder.getVideoFilter())
                .setAcceptAllFileFilterUsed(false)
                .setFileHiding(true)
                .setDefaultPreviewer()
                .showOpenDialog();
        if (file != null) {
            if (exists) {
                event.setSource(file, index);
            } else {
                event.addSource(file);
            }
            changes.setChanged(true);
            b = true;
        }
        return b;
    }

    
    public boolean chooseRepository(EventWrapper event) {
        boolean b = false;
        JButton repoButton = new JButton(NbBundle.getMessage(getClass(), "Button_ChooseRepo"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { repoButton, cancelButton };
        SourceWrapper source = event.eventSourceSet.isEmpty() ?  null : event.eventSourceSet.get(event.eventSourceIndex);
        RepoChooser repoChooser = new RepoChooser(gedcom, source, repoButton, cancelButton);
        int size = repoChooser.getNbRepos();
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChooseRepoTitle", size), repoChooser).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == repoButton) {
            if (repoChooser.isSelectedEntityRepo()) {
                boolean exists = (event.eventSourceSet != null) && (!event.eventSourceSet.isEmpty()) && (event.eventSourceIndex >= 0) && (event.eventSourceIndex < event.eventSourceSet.size());
                Repository repo = (Repository) repoChooser.getSelectedEntity();
                if (exists) {
                    event.setSourceRepository(repo);
                } else {
                    event.addSourceRepository(repo);
                }
                changes.setChanged(true);
                b = true;
            }
        }
        return b;
    }
    
    
    
    /***************************************************************************
     * Associations
     */
    
    private List<AssoWrapper> getAssociations(Indi indi) {
        List<AssoWrapper> ret = new ArrayList<AssoWrapper>();
        
        // Get ASSO tags from entities where Indi is referenced
        List<PropertyForeignXRef> assoList = indi.getProperties(PropertyForeignXRef.class);
        for (PropertyForeignXRef assoProp : assoList) {
            Property eventProp = assoProp.getParent();
            EventWrapper event = getEventWherePropIs(eventProp);
            if (event != null) {
                AssoWrapper asso = new AssoWrapper(assoProp, event);
                ret.add(asso);
            }
        }
        
        // Get ASSO tags from entities where Fam is referenced (although is not Gedcom compliant)
        Fam[] fams = indi.getFamiliesWhereSpouse();
        for (Fam fam : fams) {
            assoList = fam.getProperties(PropertyForeignXRef.class);
            for (PropertyForeignXRef assoProp : assoList) {
                Property eventProp = assoProp.getParent();
                EventWrapper event = getEventWherePropIs(eventProp);
                if (event != null) {
                    AssoWrapper asso = new AssoWrapper(assoProp, event);
                    ret.add(asso);
                }
            }
        }
        return ret;
    }
    
    private void displayAssociationsComboBox() {
        cbModel.removeAllElements();
        for (AssoWrapper asso : assoSet) {
            cbModel.addElement(asso);
        }
        if (cbModel.getSize() == 0) {
            cbModel.addElement(new AssoWrapper(NbBundle.getMessage(getClass(), "No_Association_Text")));
        }
        assoComboBox.setModel(cbModel);
        assoEditButton.setEnabled(!(eventSet.isEmpty() && assoSet.isEmpty()));
    }

    
    private void selectAssociation() {
        if (eventSet == null || eventSet.isEmpty() || assoSet == null) {
            return;
        }
        EventWrapper event = eventSet.get(eventIndex);
        if (assoComboBox.getModel().getSize() > 0) {
            for (AssoWrapper asso : assoSet) {
                if (asso.targetEvent.eventProperty == event.eventProperty) {
                    assoComboBox.setSelectedItem(asso);
                }
            }
        }
    }

    
    private boolean manageAssociations() {
        boolean b = false;
        JButton okButton = new JButton(NbBundle.getMessage(getClass(), "Button_Ok"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { okButton, cancelButton };
        AssoManager assoManager = new AssoManager(indi, eventSet, assoSet, (AssoWrapper) assoComboBox.getSelectedItem(), okButton, cancelButton);
        String localTitle = NbBundle.getMessage(getClass(), "TITL_AssoManagerTitle", assoManager.getIndi());
        Object o = DialogManager.create(localTitle, assoManager).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == okButton && assoManager.hasChanged()) {
            for (AssoWrapper asso : assoSet) {
                if (!assoManager.contains(asso)) {
                    assoRemovedSet.add(asso);
                }
            }
            assoSet = assoManager.clone(assoManager.getSet());
            changes.setChanged(true);
            b = true;
        }
        return b;
    }


    private void saveAssociations() {
        //assoSet
        for (AssoWrapper asso : assoSet) {
            asso.update();
        }
        for (AssoWrapper asso : assoRemovedSet) {
            asso.remove();
        }
    }


    
    
    

    /***************************************************************************
     * Updaters (user has made a change to in a field or control, data is stored in data structure)
     */
    private void updatePhotoTitle(int index) {
        if (isBusyMedia) {
            return;
        }
        String photoTitle = textAreaPhotos.getText();
        if ((mediaSet != null) && (!mediaSet.isEmpty()) && (index >= 0) && (index < mediaSet.size())) {
            mediaSet.get(index).setTitle(photoTitle);
            mediaIndex = index;
        } else {
            MediaWrapper media = new MediaWrapper(photoTitle);
            mediaSet.add(media);
            mediaIndex = mediaSet.size()-1;
        }
        changes.setChanged(true);
    }

    private void updateEventDescription(DocumentEvent e) {
        if (isBusyEvent) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event != null) {
            event.setDescription(eventDescription.getText());
            changes.setChanged(true);
        }
    }

    private void updateEventPlace(DocumentEvent e) {
        if (isBusyEvent) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event != null) {
            event.setPlace(eventPlaceText.getText());
            changes.setChanged(true);
        }
    }

    private void updateEventDate(ChangeEvent e) {
        if (isBusyEvent) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event != null) {
            event.setDate(eventDate);
        }
    }

    private void updateEventNoteText() {
        if (isBusyEvent || isBusyEventNote) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        String noteText = eventNote.getText();
        if ((event.eventNoteSet != null) && (!event.eventNoteSet.isEmpty()) && (event.eventNoteIndex >= 0) && (event.eventNoteIndex < event.eventNoteSet.size())) {
            event.setNote(noteText);
        } else {
            event.addNote(noteText);
        }
        changes.setChanged(true);
    }

    private void updateEventSourceText() {
        if (isBusyEvent || isBusyEventSource) {
            return;
        }
        EventWrapper event = getCurrentEvent();
        if (event == null) {
            return;
        }
        String sourceTitle = eventSourceTitle.getText();
        String sourceText = eventSourceText.getText();
        if ((event.eventSourceSet != null) && (!event.eventSourceSet.isEmpty()) && (event.eventSourceIndex >= 0) && (event.eventSourceIndex < event.eventSourceSet.size())) {
            event.setSource(sourceTitle, sourceText);
        } else {
            event.addSource(sourceTitle, sourceText);
        }
        changes.setChanged(true);
    }


    
    /***************************************************************************
     * Family buttons navigation
     */
    
    private void showPopupFamilyMenu(JButton button, final int relation, Indi familyMember, Indi[] familyMembers) {
        
        ImageIcon createIcon = new ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/ico_create.png"));
        ImageIcon attachIcon = new ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/ico_attach.png"));
        ImageIcon detachIcon = new ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/ico_detach.png"));
        
        // If modifications not saved, add "save and " in front of each label
        String prefixLabel = changes.hasChanged() ? NbBundle.getMessage(getClass(), "SaveAnd")+" " : "";

        // Initiate menu
        JPopupMenu menu = new JPopupMenu("");   
        JMenuItem menuItem = null;
        boolean putSeparator = false;
        
        // Build popup menu with items
        // - (save and ) create <family member> : only if <family member> does not exist or more than one can be created
        // - (save and ) attach <family member> : only if <family member> does not exist or more than one can be created
        // - (save and ) detach <family member> : only if <family member> exists, with sub-menu if several exist
        
        // create father or mother 
        if ((relation == IndiCreator.REL_FATHER || relation == IndiCreator.REL_MOTHER) && familyMember == null) {
            String label = NbBundle.getMessage(getClass(), "CreateIndi_" + IndiCreator.RELATIONS[relation]);
            menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), createIcon);
            menu.add(menuItem);
            putSeparator = true;
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (changes.hasChanged()) {
                        changes.fireChangeEvent(new Boolean(true));  // force changes to be saved (true) in a separate commit from the indi creation which is coming...
                    }
                    IndiCreator indiCreator = new IndiCreator(IndiCreator.CREATION, indi, relation, null, null);
                    SelectionDispatcher.fireSelection(new Context(indiCreator.getIndi()));
                }
            });
        }
        // attach father or mother 
        if ((relation == IndiCreator.REL_FATHER || relation == IndiCreator.REL_MOTHER) && familyMember == null) {
            if (putSeparator) {
                menu.addSeparator();
            }
            Indi[] potentialFamilyMembers = Utils.getPotentialFamilyMembers(indi, relation);
            for (Indi potMember : potentialFamilyMembers) {
                String label = "";
                if (potMember != null) { 
                    label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation], Utils.getDetails(potMember));
                } else {
                    if (potentialFamilyMembers.length >= 2) {
                        label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation] + "_others");
                    } else {
                        label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation] + "_other");
                    }
                }
                menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), attachIcon);
                menu.add(menuItem);
                putSeparator = true;
                final Indi fIndi = potMember;
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        Indi indiToAttach = fIndi;
                        if (indiToAttach == null) {
                            indiToAttach = getIndiFromUser(indi, relation == IndiCreator.REL_FATHER ? indi.getLastName() : "", relation);
                        }
                        if (indiToAttach != null) {
                            if (changes.hasChanged()) {
                                changes.fireChangeEvent(new Boolean(true));
                            }
                            IndiCreator indiCreator = new IndiCreator(IndiCreator.ATTACH, indi, relation, null, indiToAttach);
                            SelectionDispatcher.fireSelection(new Context(indiCreator.getIndi()));
                        }
                    }

                });
            }
        }
        // detach father or mother 
        if ((relation == IndiCreator.REL_FATHER || relation == IndiCreator.REL_MOTHER) && familyMember != null) {
            if (putSeparator) {
                menu.addSeparator();
            }
            String label = NbBundle.getMessage(getClass(), "DetachIndi_" + IndiCreator.RELATIONS[relation], familyMember.getName());
            menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), detachIcon);
            menu.add(menuItem);
            final Indi fIndi = familyMember;
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (changes.hasChanged()) {
                        changes.fireChangeEvent(new Boolean(true));
                    }
                    new IndiCreator(IndiCreator.DETACH, indi, relation, null, fIndi);
                    SelectionDispatcher.fireSelection(new Context(indi));
                }
            });
        }

        putSeparator = false;
        
        
        // create family members
        if (relation != IndiCreator.REL_FATHER && relation != IndiCreator.REL_MOTHER) {
            String label = NbBundle.getMessage(getClass(), "CreateIndi_" + IndiCreator.RELATIONS[relation]);
            final Indi currentSpouse;
            if (relation == IndiCreator.REL_CHILD) {
                currentSpouse = Utils.getCurrentSpouse(indi, familyTree);
                label += currentSpouse != null ? " " + NbBundle.getMessage(getClass(), "CreateIndi_CHILD_spouse", currentSpouse) : "";
            } else {
                currentSpouse = null;
            }
            menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), createIcon);
            menu.add(menuItem);
            putSeparator = true;
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (changes.hasChanged()) {
                        changes.fireChangeEvent(new Boolean(true));  // force changes to be saved (true) in a separate commit from the indi creation which is coming...
                    }
                    IndiCreator indiCreator = new IndiCreator(IndiCreator.CREATION, indi, relation, currentSpouse, null);
                    SelectionDispatcher.fireSelection(new Context(indiCreator.getIndi()));
                }
            });
        }
        // attach family members
        if (relation != IndiCreator.REL_FATHER && relation != IndiCreator.REL_MOTHER) {
            if (putSeparator) {
                menu.addSeparator();
            }
            Indi[] potentialFamilyMembers = Utils.getPotentialFamilyMembers(indi, relation);
            for (Indi potMember : potentialFamilyMembers) {
                String label = "";
                if (potMember != null) { 
                    label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation], Utils.getDetails(potMember));
                } else {
                    if (potentialFamilyMembers.length >= 2) {
                        label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation] + "_others");
                    } else {
                        label = NbBundle.getMessage(getClass(), "AttachIndi_" + IndiCreator.RELATIONS[relation] + "_other");
                    }
                }
                menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), attachIcon);
                menu.add(menuItem);
                putSeparator = true;
                final Indi fIndi = potMember;
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        Indi indiToAttach = fIndi;
                        if (indiToAttach == null) {
                            String filter = "";
                            if (relation == IndiCreator.REL_BROTHER || relation == IndiCreator.REL_SISTER || (relation == IndiCreator.REL_CHILD && indi.getSex() == PropertySex.MALE)) {
                                filter = indi.getLastName();
                            }
                            indiToAttach = getIndiFromUser(indi, filter, relation);
                        }
                        if (indiToAttach != null) {
                            if (changes.hasChanged()) {
                                changes.fireChangeEvent(new Boolean(true));
                            }
                            IndiCreator indiCreator = new IndiCreator(IndiCreator.ATTACH, indi, relation, null, indiToAttach);
                            SelectionDispatcher.fireSelection(new Context(indiCreator.getIndi()));
                        }
                    }

                });
            }
        }
        // detach family members
        if (relation != IndiCreator.REL_FATHER && relation != IndiCreator.REL_MOTHER && familyMembers != null && familyMembers.length != 0) {
            if (putSeparator) {
                menu.addSeparator();
            }
            for (Indi i : familyMembers) {
                String label = NbBundle.getMessage(getClass(), "DetachIndi_" + IndiCreator.RELATIONS[relation], i.getName());
                menuItem = new JMenuItem(prefixLabel + (changes.hasChanged() ? label.toLowerCase() : label), detachIcon);
                menu.add(menuItem);
                final Indi fIndi = i;
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        if (changes.hasChanged()) {
                            changes.fireChangeEvent(new Boolean(true));
                        }
                        new IndiCreator(IndiCreator.DETACH, indi, relation, null, fIndi);
                        SelectionDispatcher.fireSelection(new Context(indi));
                    }
                });
            }
        }

        
        
        
        // Show menu
        menu.show(button, 3, button.getHeight()-5);

        
    }
    
    private Indi getIndiFromUser(Indi tmpIndi, String filter, int relation) {
        // Init variables
        JButton okButton = new JButton(NbBundle.getMessage(getClass(), "Button_Ok"));
        JButton cancelButton = new JButton(NbBundle.getMessage(getClass(), "Button_Cancel"));
        Object[] options = new Object[] { okButton, cancelButton };
        String str = NbBundle.getMessage(getClass(), "TITL_" + IndiCreator.RELATIONS[relation]);

        // Create chooser
        IndiChooser indiChooser = new IndiChooser(tmpIndi, filter, relation, okButton);
        
        // Open dialog
        Object o = DialogManager.create(NbBundle.getMessage(getClass(), "TITL_ChooseIndiTitle", str, tmpIndi.toString(true)), indiChooser).setMessageType(DialogManager.PLAIN_MESSAGE).setOptions(options).show();
        if (o == okButton) {
            return indiChooser.getIndi();
        } else {
            return null;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /***************************************************************************
     * Event buttons manipulation and navigation
     */
    
    private EventWrapper getCurrentEvent() {
        EventWrapper event = null;
        if (eventSet != null && !eventSet.isEmpty() && (eventIndex >= 0) && (eventIndex < eventSet.size())) {
            event = eventSet.get(eventIndex);
        }
        return event;
    }
    
    private EventWrapper getEventWherePropIs(Property eventProp) {
        for (EventWrapper event : eventSet) {
            if (event.eventProperty == eventProp) {
                return event;
            }
        }
        if (eventProp instanceof Entity) {
            return new EventWrapper((Entity) eventProp, refNotes, refSources);
        }
        return null;
    }
    
    
    private void createOrPreSelectEvent(String tag) {
        int index = getEvent(tag);

        if (index == -1) { // Create event if it does not exists
            // Need to use properties attached to a gedcom, with same grammar, in order to be able to display icons
            Gedcom tmpGedcom = new Gedcom();
            tmpGedcom.setGrammar(gedcom.getGrammar());
            Entity tmpIndi = null;
            try {
                tmpIndi = tmpGedcom.createEntity(Gedcom.INDI);
            } catch (GedcomException ex) {
                return;
            }
            Property prop = tmpIndi.addProperty(tag, "");
            createEvent(prop);
        } else { // else select it
            eventIndex = index;
        }
    }
    
    private void createEvent(Property prop) {
        eventSet.add(new EventWrapper(prop, indi, refNotes, refSources));
        displayEventTable();
        eventIndex = eventSet.size() - 1;
        changes.setChanged(true);
    }
    
    private int getEvent(String tag) {
        for (EventWrapper event : eventSet) {
            if (event.eventProperty.getTag().equals(tag)) {
                return eventSet.indexOf(event);
            }
        }
        return -1;
    }

    private int getNextEvent(String tag) {
        int index = eventIndex + 1;
        if (index == eventSet.size()) {
            index = 0;
        }
        for (int i = index; i < eventSet.size(); i++) {
            if (eventSet.get(i).eventProperty.getTag().equals(tag)) {
                return i;
            }
        }
        for (int i = 0; i < index; i++) {
            if (eventSet.get(i).eventProperty.getTag().equals(tag)) {
                return i;
            }
        }
        return -1;
    }

    private int getEventNb(String tag) {
        int nb = 0;
        for (EventWrapper event : eventSet) {
            if (event.eventProperty.getTag().equals(tag)) {
                nb++;
            }
        }
        return nb;
    }

    
    private void showPopupEventMenu(JButton button, final String tag, String createLabel, String displayNextLabel) {

        // Need to use properties attached to a gedcom, with same grammar, in order to be able to display icons
        Gedcom tmpGedcom = new Gedcom();
        tmpGedcom.setGrammar(gedcom.getGrammar());
        Entity tmpIndi = null, tmpFam = null;
        Property prop = null;
        try {
            tmpIndi = tmpGedcom.createEntity(Gedcom.INDI);
            tmpFam = tmpGedcom.createEntity(Gedcom.FAM);
        } catch (GedcomException ex) {
            return;
        }
        prop = tmpIndi.addProperty(tag, "");
        if (!tmpIndi.getMetaProperty().allows(tag)) {
            prop = tmpFam.addProperty(tag, "");
        }
        final Property fProp = prop;

        // if tag does not exist, create it and return
        int nbEvent = getEventNb(tag);
        if (nbEvent == 0) {
            createEvent(fProp);
            selectEvent(getRowFromIndex(eventIndex));
            eventDescription.requestFocus();
            return;
        }
        String nextLabel = nbEvent == 1 ? displayNextLabel : displayNextLabel+"_many";
        
        JPopupMenu menu = new JPopupMenu("");   // title in popup would be nice but L&F does not display it
        JMenuItem menuItem = new JMenuItem(NbBundle.getMessage(getClass(), createLabel));
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                createEvent(fProp);
                selectEvent(getRowFromIndex(eventIndex));
                eventDescription.requestFocus();
            }
        });
        menuItem = new JMenuItem(NbBundle.getMessage(getClass(), nextLabel));
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int index = getNextEvent(tag);
                if (index != -1) {
                    selectEvent(getRowFromIndex(index));
                    eventDescription.requestFocus();
                }
            }
        });
        menu.show(button, 3, button.getHeight()-5);
    }


    private void showPopupEventMenu(JButton button) {
        JPopupMenu menu = new JPopupMenu("");   // title in popup would be nice but L&F does not display it
        JMenuItem menuItem = null;
        
        // Need to use properties attached to a gedcom, with same grammar, in order to be able to display icons
        Gedcom tmpGedcom = new Gedcom();
        tmpGedcom.setGrammar(gedcom.getGrammar());
        Entity tmpIndi = null, tmpFam = null;
        Property prop = null;
        try {
            tmpIndi = tmpGedcom.createEntity(Gedcom.INDI);
            tmpFam = tmpGedcom.createEntity(Gedcom.FAM);
        } catch (GedcomException ex) {
            return;
        }

        // Loop on all other events to build list of sorted items
        SortedMap<String, Property> names = new TreeMap<String, Property>();
        for (final String tag : EventUsage.otherEventsList) {
            prop = tmpIndi.addProperty(tag, "");
            if (!tmpIndi.getMetaProperty().allows(tag)) {
                prop = tmpFam.addProperty(tag, "");
            }
            names.put(prop.getPropertyName(), prop);
        }
        
        // Retrieve list in sorted order and build menu items
        for (String name : names.keySet())     {
            final Property fProp = names.get(name);
            menuItem = new JMenuItem(fProp.getPropertyName(), fProp.getImage());
            menu.add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    createEvent(fProp);
                    selectEvent(getRowFromIndex(eventIndex));
                    eventDescription.requestFocus();
                }
            });
        }
        // End loop

        // Show menu
        menu.show(button, 3, button.getHeight()-5);
    }

    
    
    
    
    
    
    
    
    
    /***************************************************************************
     * Management of errors
     */
    
    private Validator getValidator() {
        Validator validator = null;
        Collection<? extends AncestrisPlugin> plugins = Lookup.getDefault().lookupAll(AncestrisPlugin.class);
        for (AncestrisPlugin plugin : plugins) {
            if (plugin.getPluginName().equals("ancestris.modules.gedcom.gedcomvalidate")) {
                validator = (Validator) plugin;
                break;
            }
        }
        return validator;
    }
    
    private boolean passControls() {

        // Refresh errorSet
        if (errorSet == null) {
            errorSet = new ArrayList<ViewContext>();
        }
        errorSet.clear();
        
        
        // If validator loaded, use it
        Validator validator = getValidator();
        if (validator != null) {
            List<ViewContext> errors = new ArrayList<ViewContext>();
            
            // Control INDI
            errors = validator.start(indi);
            if (errors != null) {
                errorSet.addAll(errors);
            }
            
            // Control parents of INDI
            errors = validator.start(indi.getFamilyWhereBiologicalChild());
            if (errors != null) {
                errorSet.addAll(errors);
            }
            
            // Control spouse of INDI
            for (Fam fam : indi.getFamiliesWhereSpouse()) {
                errors = validator.start(fam);
                if (errors != null) {
                    errorSet.addAll(errors);
                }
            }
            
            return errorSet != null && !errorSet.isEmpty();
        }
        
        // No validator found, used basic default one detecting only negative ages
        for (EventWrapper event : eventSet) {
            
            // negative age
            if (event.isAgeNegative()) {
                String str = event.eventProperty.getPropertyName();
                if (str.contains(" ")) {
                    str = str.substring(0, str.indexOf(" ")); // only take first word
                }
                String msg = NbBundle.getMessage(getClass(), "MSG_WARNING_Control_01", event.date.getDisplayValue(), str);
                errorSet.add(new ViewContext(event.eventProperty).setText(msg));
            }
        }
        return !errorSet.isEmpty();
    }
    
    
    private void showWarningsAndErrors() {
        if (errorSet == null || errorSet.isEmpty()) {
            return;
        }
        
        ErrorPanel ep = new ErrorPanel(errorSet, getValidator() != null);
        DialogManager.create(
                NbBundle.getMessage(getClass(), "TITL_WARNING_Control", indi.toString()), ep)
                .setMessageType(DialogManager.WARNING_MESSAGE)
                .setOptionType(DialogManager.OK_ONLY_OPTION)
                .show();
        warningButton.setVisible(passControls());
    }

    
    


    
    
    
    
    
    
    
    
    
    
    
    /**
     * Document listener methods for name, etc information
     */
    public void insertUpdate(DocumentEvent e) {
        if (!isBusyEvent) {
            changes.setChanged(true);
        }
    }

    public void removeUpdate(DocumentEvent e) {
        if (!isBusyEvent) {
            changes.setChanged(true);
        }
    }

    public void changedUpdate(DocumentEvent e) {
        if (!isBusyEvent) {
            changes.setChanged(true);
        }
    }



    





    
    
    
    
    
    /**
     * ******************  CLASSES  ********************************************
     */
    
    
    
    /**
     * Document listener methods for Main pictures
     */
    private class PhotoTitleListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updatePhotoTitle(mediaIndex);
        }

        public void removeUpdate(DocumentEvent e) {
            updatePhotoTitle(mediaIndex);
        }

        public void changedUpdate(DocumentEvent e) {
            updatePhotoTitle(mediaIndex);
        }
    }

    
    /**
     * Document listener methods for Event description
     */
    private class EventDescriptionListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateEventDescription(e);
        }

        public void removeUpdate(DocumentEvent e) {
            updateEventDescription(e);
        }

        public void changedUpdate(DocumentEvent e) {
            updateEventDescription(e);
        }
    }

    /**
     * Document listener methods for Event date
     */
    private class EventDateListener implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            updateEventDate(e);
        }

    }

    /**
     * Document listener methods for Event Places
     */
    private class EventNoteTextListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateEventNoteText();
        }

        public void removeUpdate(DocumentEvent e) {
            updateEventNoteText();
        }

        public void changedUpdate(DocumentEvent e) {
            updateEventNoteText();
        }
    }

    /**
     * Document listener methods for Event notes
     */
    private class EventPlaceListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateEventPlace(e);
        }

        public void removeUpdate(DocumentEvent e) {
            updateEventPlace(e);
        }

        public void changedUpdate(DocumentEvent e) {
            updateEventPlace(e);
        }
    }
    

    /**
     * Document listener methods for Event source
     */
    private class EventSourceTextListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateEventSourceText();
        }

        public void removeUpdate(DocumentEvent e) {
            updateEventSourceText();
        }

        public void changedUpdate(DocumentEvent e) {
            updateEventSourceText();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    private class FamilyTreeMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
            int selRow = familyTree.getRowForLocation(e.getX(), e.getY());
            if (selRow != -1 && e.getClickCount() == 2) {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) familyTree.getLastSelectedPathComponent();
                NodeWrapper node = (NodeWrapper) treeNode.getUserObject();
                if (node.getEntity() != null) {
                    SelectionDispatcher.fireSelection(new Context(node.getEntity()));
                }
            }
        }

        public void mouseClicked(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
        
    }

    private class IconTextCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                setIcon(((EventLabel) value).getIcon());
                setText(((EventLabel) value).getShortLabel());
            }
        return this;
        }
    }
    
    
    private class DoubleCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                String text = (String) value;
                try {
                    Double d = new DecimalFormat("#").parse(text).doubleValue();
                    DecimalFormat df = new DecimalFormat("#.#");
                    setText(df.format(d));
                } catch (ParseException ex) {
                    //Exceptions.printStackTrace(ex);
                    setText(text);
                }
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            return this;
        }
    }
    
    
    }
