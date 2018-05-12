package pl.polsl.java.marczyk.client.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import pl.polsl.java.marczyk.client.view.language.LanguageImplementer;

/**
 * Represents main window of the application.
 *
 * @author Grzegorz Marczyk
 * @version v.1.1.0
 */
public class MainWindow extends JFrame {

    /* SETTINGS, Primitive types, basic fields. */
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final int JTB_HEIGHT = 25;          // JToolBar
    private final int JTB_SIZE_COMPONENT = 20;
    private final int JMI_SIZE_COMPONENT = 20;  // JMenuItem
    private final int JTP_SIZE_CLOSEBUTTONICON = 10; // JTabbedPane
    private final Dimension JTP_SIZE_CLOSEBUTTON = new Dimension(8, 10);

    int jtpTabCreatedAmout = 0;

    /* Names, strings, titles, pathes */
    private final String PATH_ICON_NEWTEST = "icons/File-48.png";
    private final String PATH_ICON_SAVETEST = "icons/Save-48.png";
    private final String PATH_ICON_OPENTEST = "icons/Add File-48.png";
    private final String PATH_ICON_EXIT = "icons/Cancel-48.png";
    private final String PATH_ICON_ADDROW = "icons/AddRow-48.png";
    private final String PATH_ICON_DELROW = "icons/Delete-48 (1).png";
    private final String PATH_ICON_CLOSETAB = "icons/Delete-48.png";

    /* Names, texts, titles of components, messages etc. */
    // English by default, initialized at start. reinitLanguage changes it with given language
    private String textJmbFile = "File";
    private String textJmbEdit = "Edit";
    private String textJmbHelp = "Help";

    private String textJmiNewTest = "New primality test";
    private String textJmiSave = "Save test as";
    private String textJmiOpen = "Open test";
    private String textJmiExit = "Exit";
    private String textJmiFermat = "Fermat test";

    /* Swing components */
    private JToolBar jtbToolBar;
    private JButton jtbAddRowButton, jtbDelRowButton;

    private JMenuBar jmbMenuBar;
    private JMenu jmFile, jmEdit, jmHelp, jmiNewTest;
    private JMenuItem jmiSave, jmiOpen, jmiExit, jmiFermatTest;

    private JTabbedPane jtpTabbedPane;
    private ArrayList<Tab> jtpTabsInPane = new ArrayList<>();

    /* Listeners, event handling etc */
    ActionListener actionListener;

    /* Else */
    LanguageImplementer languageImplementer;

    /* Resources */
    private ImageIcon iconJmiNewPrimalityTest;
    private ImageIcon iconJmiSaveTestAs;
    private ImageIcon iconJmiOpenTest;
    private ImageIcon iconJmiExit;

    private ImageIcon iconJtbAddRow;
    private ImageIcon iconJtbDelRow;
    private ImageIcon iconJtbCloseTab;

    /**
     * Constructor, uses mostly "init"-names private methods.
     * @param listener action listener for components inside this class.
     * @param impl implementer of bridge-pattern language interface.
     */
    public MainWindow(ActionListener listener, LanguageImplementer impl) {
        super();
        this.actionListener = listener;
        this.languageImplementer = impl;

        this.initImageResources();
        this.initFrame();

        this.initMenuBar();
        this.initToolBar();
        this.initTabbedPane();

        this.initActionListeners();
        this.setVisible(true);
    }

    /**
     * Adds empty row on currently selected tab.
     */
    public void addRowOnCurrentTab() {

        int tabIndex = jtpTabbedPane.getSelectedIndex();
        if (tabIndex != -1) {
            jtpTabsInPane.get(tabIndex).addRow();
        }

    }

    /**
     * Deletes currently selected row in tab.
     */
    public void delRowCurrentlySelected() {
        jtpTabsInPane.get(
                jtpTabbedPane.getSelectedIndex()).delSelectedRow();
    }

    /**
     * Gets all the numbers which were put to the table.
     *
     * @return array of numbers represented as string.
     */
    public String[] getNumbersFromCurrentTab() {
        return jtpTabsInPane.get(
                jtpTabbedPane.getSelectedIndex()).getNumbersFromTable();

    }

    /**
     * Sets the results column in currently opened tab.
     *
     * @param str array of results represented by boolean objects.
     */
    public void setResultsOnCurrentTab(Boolean[] str) {
        jtpTabsInPane.get(jtpTabbedPane.getSelectedIndex()).setResultsOnTable(str);
    }

    /**
     * Gets name of currently opened tab.
     *
     * @return name of currently opened tab.
     */
    public String getNameOfCurrentTab() {
        return jtpTabsInPane.get(jtpTabbedPane.getSelectedIndex()).getTabName();
    }

    /**
     * Closes the window which is object of this class.
     */
    public void closeFrame() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Sets toolbar items avaiability (enabled/disabled) depending on
     * information if there is any tab opened.
     */
    public void setAddAndDelAvaiability() {

        int index = jtpTabsInPane.size();
        if (index == 0) {
            jtbAddRowButton.setEnabled(false);
            jtbDelRowButton.setEnabled(false);
        } else {
            jtbAddRowButton.setEnabled(true);
            jtbDelRowButton.setEnabled(true);
        }
    }

    /**
     * Creates a tab for probabilistic tests with specific title.
     *
     * @param title Title of the tab, is written on it.
     */
    public void createTab(String title) {

        JPanel newTabHeader = new JPanel();
        JLabel newTabLabel = new JLabel(title);

        Action close = new AbstractAction() {
            String paneTitle = title;

            @Override
            public void actionPerformed(ActionEvent e) {

                int tmp = findTabIndex(title);
                jtpTabbedPane.remove(tmp);
                jtpTabsInPane.remove(tmp);
                setAddAndDelAvaiability();
            }
        };

        JButton xButton = new JButton();
        xButton.setAction(close);
        xButton.setIcon(iconJtbCloseTab);
        xButton.setPreferredSize(JTP_SIZE_CLOSEBUTTON);
        //    xButton.setSize(JTP_SIZE_CLOSEBUTTON);

        newTabHeader.add(newTabLabel);
        newTabHeader.add(xButton);

        newTabHeader.setOpaque(false);

        jtpTabsInPane.add(new Tab(title, newTabHeader, actionListener, languageImplementer));
        jtpTabbedPane.addTab(String.valueOf(jtpTabCreatedAmout),
                jtpTabsInPane.get(findTabIndex(title)).getTabArea());
        jtpTabbedPane.setTabComponentAt(findTabIndex(title),
                jtpTabsInPane.get(findTabIndex(title)).getTabHeader());

        this.setAddAndDelAvaiability();
        jtpTabCreatedAmout++;

    }

    /**
     * Reinitializes language and sets all component names, titles, messages
     * etc. to proper language.
     *
     * @param impl implementer of bridge-pattern language interface.
     */
    public void reinitLanguage(LanguageImplementer impl) {

        this.languageImplementer = impl;

        textJmbFile = impl.textJmFile();
        jmFile.setText(textJmbFile);

        textJmbEdit = impl.textJmEdit();
        jmEdit.setText(textJmbEdit);

        textJmbHelp = impl.textJmHelp();
        jmHelp.setText(textJmbHelp);

        textJmiNewTest = impl.textJmiNewTest();
        jmiNewTest.setText(textJmiNewTest);

        textJmiSave = impl.textJmiSave();
        jmiSave.setText(textJmiSave);
        jmiSave.setToolTipText("gds");

        textJmiOpen = impl.textJmiOpen();
        jmiOpen.setText(textJmiOpen);

        textJmiExit = impl.textJmiExit();
        jmiExit.setText(textJmiExit);

        textJmiFermat = impl.textJmiFermat();
        jmiFermatTest.setText(textJmiFermat);

        for (Tab eachTab : jtpTabsInPane) {
            eachTab.reinitLanguage(impl);
        }

    }

    /**
     * Initializes action listeners for most of components. ! ! ! IMPORTANT: "X"
     * BUTTON IN TABS OF TABBED PANE HAS HIS ACTION LISTENER ADDED RIGHT AFTER
     * CREATION, IN createTab method. ! ! !
     */
    private void initActionListeners() {

        /*  */
        jtbAddRowButton.addActionListener(actionListener);
        jtbAddRowButton.setActionCommand("JTB_ADD_ROW");

        jtbDelRowButton.addActionListener(actionListener);
        jtbDelRowButton.setActionCommand("JTB_DEL_ROW");

        jmiFermatTest.addActionListener(actionListener);
        jmiFermatTest.setActionCommand("JMI_NEW_FERMAT");

        /*jmiOpen.addActionListener(actionListener);
        jmiOpen.setActionCommand("JMI_OPEN");*/
        jmiExit.addActionListener(actionListener);
        jmiExit.setActionCommand("JMI_EXIT");

        /*jmiSave.addActionListener(actionListener);
        jmiSave.setActionCommand("JMI_SAVE");*/
    }

    /**
     * Initializes main frame with mostly JFrame settings.
     */
    private void initFrame() {

        this.setSize(this.FRAME_WIDTH, this.FRAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("Primality tester");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Initializes resources from file.
     */
    private void initImageResources() {

        iconJmiNewPrimalityTest = new ImageIcon(this.getClass().getResource(PATH_ICON_NEWTEST));
        iconJmiOpenTest = new ImageIcon(this.getClass().getResource(PATH_ICON_OPENTEST));
        iconJmiSaveTestAs = new ImageIcon(this.getClass().getResource(PATH_ICON_SAVETEST));
        iconJmiExit = new ImageIcon(this.getClass().getResource(PATH_ICON_EXIT));

        iconJtbAddRow = new ImageIcon(this.getClass().getResource(PATH_ICON_ADDROW));
        iconJtbDelRow = new ImageIcon(this.getClass().getResource(PATH_ICON_DELROW));
        iconJtbCloseTab = new ImageIcon(this.getClass().getResource(PATH_ICON_CLOSETAB));

        // Rescaling
        iconJmiNewPrimalityTest = rescaleIcon(iconJmiNewPrimalityTest, JMI_SIZE_COMPONENT);
        iconJmiOpenTest = rescaleIcon(iconJmiOpenTest, JMI_SIZE_COMPONENT);
        iconJmiSaveTestAs = rescaleIcon(iconJmiSaveTestAs, JMI_SIZE_COMPONENT);
        iconJmiExit = rescaleIcon(iconJmiExit, JMI_SIZE_COMPONENT);

        iconJtbAddRow = rescaleIcon(iconJtbAddRow, JMI_SIZE_COMPONENT);
        iconJtbDelRow = rescaleIcon(iconJtbDelRow, JMI_SIZE_COMPONENT);
        iconJtbCloseTab = rescaleIcon(iconJtbCloseTab, JTP_SIZE_CLOSEBUTTONICON);

    }

    /**
     * Initializes swing JToolBar and its components.
     */
    private void initToolBar() {

        jtbToolBar = new JToolBar();
        jtbToolBar.setBounds(0, 0, this.FRAME_WIDTH, this.JTB_HEIGHT);
        jtbToolBar.setFloatable(false);
        jtbToolBar.setBorder(BorderFactory.createEtchedBorder());

        // Toolbar buttons
        jtbAddRowButton = new JButton(iconJtbAddRow);
        jtbDelRowButton = new JButton(iconJtbDelRow);
        jtbAddRowButton.setEnabled(false);
        jtbDelRowButton.setEnabled(false);

        jtbToolBar.add(jtbAddRowButton);
        jtbToolBar.add(jtbDelRowButton);

        // jtbToolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        this.add(jtbToolBar);
    }

    /**
     * Initializes swing JTabbedPane and its components.
     */
    private void initTabbedPane() {

        jtpTabbedPane = new JTabbedPane();
        jtpTabbedPane.setBounds(-1, JTB_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT); // with -1 it fits better
        jtpTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.add(jtpTabbedPane);
    }

    /**
     * Initializes swing JMenuBar and its components.
     */
    private void initMenuBar() {

        jmbMenuBar = new JMenuBar();
        this.setJMenuBar(jmbMenuBar);

        // "File" option
        jmFile = new JMenu(this.textJmbFile);
        jmFile.setEnabled(true);
        jmbMenuBar.add(jmFile);

        // "Edit" option
        jmEdit = new JMenu(this.textJmbEdit);
        jmEdit.setEnabled(false);
        jmbMenuBar.add(jmEdit);

        // "Help" option
        jmHelp = new JMenu(this.textJmbHelp);
        jmHelp.setEnabled(false);
        jmbMenuBar.add(jmHelp);

        // "New test" under "File"
        jmiNewTest = new JMenu(this.textJmiNewTest);
        jmiNewTest.setIcon(this.iconJmiNewPrimalityTest);
        jmFile.add(jmiNewTest);
        jmFile.addSeparator();
        // Tree under "New test" under "File"
        jmiFermatTest = new JMenuItem(this.textJmiFermat);
        jmiNewTest.add(jmiFermatTest);

        // "Open test" under "File"
        jmiOpen = new JMenuItem(this.textJmiOpen, this.iconJmiOpenTest);
        jmFile.add(jmiOpen);
        jmiOpen.setEnabled(false);

        // "Save test" under "File"
        jmiSave = new JMenuItem(this.textJmiSave, this.iconJmiSaveTestAs);
        jmFile.add(jmiSave);
        jmiSave.setEnabled(false);
        jmFile.addSeparator();

        // "Exit" under "File"
        jmiExit = new JMenuItem(this.textJmiExit, this.iconJmiExit);
        jmFile.add(jmiExit);

    }

    /**
     * Finds specific tab depending on name of the tab.
     *
     * @param title name of tab.
     * @return index of the tab that has been found.
     */
    private int findTabIndex(String title) {

        boolean found = false;
        int ret = -1;

        for (Tab pt : jtpTabsInPane) {
            ret++;
            if (pt.getTabName().equals(title)) {
                found = true;
                break;
            }
        }

        if (found == true) {
            return ret;
        } else {
            return -1;
        }
    }

    /**
     * Rescales icon.
     *
     * @param icon the icon to rescale
     * @param size size of the new icon (its a square)
     * @return resized icon.
     */
    private ImageIcon rescaleIcon(ImageIcon icon, int size) {

        Image tmpImg = icon.getImage();  // Rescale to make it smaller
        Image newImg = tmpImg.getScaledInstance(size, size,
                java.awt.Image.SCALE_SMOOTH);
        return icon = new ImageIcon(newImg);
    }
}
