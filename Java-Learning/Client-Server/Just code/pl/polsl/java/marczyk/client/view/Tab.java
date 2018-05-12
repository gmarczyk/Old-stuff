package pl.polsl.java.marczyk.client.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pl.polsl.java.marczyk.client.view.language.LanguageImplementer;

/**
 * This is class of a tab in JTabbedPane. Consists of tab component and tab
 * area. Everything done inside a tab should be implemented here.
 *
 * @author Grzegorz Marczyk
 * @version 1.1.0
 */
public class Tab {

    /**
     * Component that is the area of the tab
     */
    private JPanel jpTabArea;

    /**
     * Component that is the header of the tab. Contains title and a "X" close
     * button.
     */
    private JPanel jpTabHeader;

    /**
     * Just name of this component, mostly required to find or localize specific
     * tab. Inside this class is being used as information about tested number.
     */
    private String nameOfTab;

    private ActionListener actionListener;

    /**
     * Implementer of language needed to set up specific titles, names etc.
     */
    private LanguageImplementer languageImplementer;

    /* Swing components */
    private JTable jtTable;
    private JLabel jlTestedOnLabel;
    private JButton jbPerformTest;

    /* Text strings, required to set up names, titles of components etc. */
    // English by default, initialized at start. reinitLanguage changes it with given language  
    private String textJlTestedNum = "Tested number";
    private String textColTestedOn = "Tested on value";
    private String textColResult = "Result";
    private String textJbPerformTest = "Perform test";

    /**
     * Constructor of tab.
     *
     * @param title is name of the tab, required mostly to localize specific
     * tab.
     * @param headerComponent is the component representing tab header with the
     * title and "X" closing tab button.
     * @param listener listener of actions performed inside the tab.
     * @param impl language implementer implementer of bridge-pattern language
     * interface. Needed to initialize strings with names,titles etc.
     */
    public Tab(String title, JPanel headerComponent, ActionListener listener, LanguageImplementer impl) {

        actionListener = listener;
        jpTabHeader = headerComponent;
        nameOfTab = title;

        jpTabArea = new JPanel();
        languageImplementer = impl;

        this.initTabArea();
        this.reinitLanguage(impl);
        this.initActionListeners();
    }

    /**
     * Reinitializes language and changes all titles, names, etc to specific
     * language.
     *
     * @param impl implementer of bridge-pattern language interface. Required to
     * get the text in specific language.
     */
    public void reinitLanguage(LanguageImplementer impl) {

        textJlTestedNum = impl.textJlTestedNum();
        textColResult = impl.textColResult();
        textColTestedOn = impl.textColTestedOn();
        textJbPerformTest = impl.textJbPerformTest();

        jbPerformTest.setText(textJbPerformTest);
        jlTestedOnLabel.setText(textJlTestedNum + " = " + nameOfTab);

        DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
        model.setColumnIdentifiers(new Object[]{textColTestedOn, textColResult});
        jtTable.setModel(model);
    }

    /**
     * Getter of tab area component.
     *
     * @return tab area component.
     */
    public JPanel getTabArea() {
        return jpTabArea;
    }

    /**
     * Getter of tab header with its title and "X" button.
     *
     * @return tab header component.
     */
    public JPanel getTabHeader() {
        return jpTabHeader;
    }

    /**
     * Getter of the name of the tab.
     *
     * @return tab name.
     */
    public String getTabName() {
        return nameOfTab;
    }

    /**
     * Sets up specific row in the table with specific value.
     *
     * @param data the value to be putted in table row
     * @param row row index
     * @param col column index
     */
    public void setRow(String data, int row, int col) {

        int rowCountIndex = jtTable.getRowCount() - 1;
        int colCountIndex = jtTable.getColumnCount() - 1;

        if ((row <= rowCountIndex && row >= 0) && (col <= colCountIndex && col >= 0)) {
            DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
            model.setValueAt(data, row, col);
        }
    }

    /**
     * Adds empty row to the table.
     */
    public void addRow() {
        DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
        model.addRow(new Object[]{"", ""});
    }

    /**
     * Deletes currently selected row.
     */
    public void delSelectedRow() {

        int tmp = jtTable.getSelectedRow();
        if (tmp != -1) {
            DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
            model.removeRow(tmp);
        }
    }

    /**
     * Getter of all numbers inside first row of the table.
     *
     * @return array of numbers represented by string values.
     */
    public String[] getNumbersFromTable() {

        int maxCol = jtTable.getModel().getRowCount();
        String[] tmp = new String[maxCol];

        for (int i = 0; i < maxCol; i++) {
            Object val = jtTable.getModel().getValueAt(i, 0);
            if (val != null) {
                tmp[i] = val.toString();
            } else {
                tmp[i] = String.valueOf(0);
            }
        }
        return tmp;
    }

    /**
     * Sets up the result column with specific values.
     *
     * @param str array of boolean values to be set into the table.
     */
    public void setResultsOnTable(Boolean[] str) {

        int maxRow = str.length;

        DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
        for (int i = 0; i < maxRow; i++) {
            model.setValueAt(languageImplementer.textResult(str[i]), i, 1);
        }

    }

    /**
     * Initializes tab area components.
     */
    private void initTabArea() {

        String[] columns = {textColTestedOn, textColResult};
        String[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 0:
                        return true;
                    case 1:
                        return false;
                    default:
                        return false;
                }
            }
        };

        jtTable = new JTable(model);
        jtTable.setPreferredScrollableViewportSize(new Dimension(500, 333));
        jtTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(jtTable);
        jpTabArea.add(scrollPane);

        jlTestedOnLabel = new JLabel(textJlTestedNum + " = " + this.nameOfTab);
        jpTabArea.add(jlTestedOnLabel);

        jbPerformTest = new JButton(textJbPerformTest);
        jbPerformTest.setActionCommand("JB_PERFORM_TEST");
        jpTabArea.add(jbPerformTest);

    }

    /**
     * Initializes action listeners.
     */
    private void initActionListeners() {

        jbPerformTest.addActionListener(actionListener);
    }
}
