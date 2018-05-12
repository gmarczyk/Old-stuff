package pl.polsl.java.marczyk.client.view.language;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Implementer of the bridge-pattern used to display communicates in different
 * languages.
 *
 * @author Marczyk Grzegorz
 * @version 2.1.0
 */
public interface LanguageImplementer {

    /**
     * Procedure prints information about recent probabilistic tests with the
     * result.
     *
     * @param testedNumber The number which was tested ("p" in formula).
     * @param testedOnValue Value according to which the test was performed ("a"
     * in formula).
     * @param resultOfTest Result in true-false whether the test was positive or
     * not.
     */
    public void showTestResults(String testedNumber, ArrayList<String> testedOnValue, ArrayList<Boolean> resultOfTest);

    /**
     * Procedure prints the main menu of the application.
     */
    public void showMenu();

    /**
     * Message to be printed if user accidentally or on purpose presses enter
     * without a value that he was asked for.
     */
    public void msgEmptyValue();

    /**
     * Message to be displayed in dialog window if user accidentally or on
     * purpose presses enter without a value that he was asked for.
     *
     * @param frame Parent frame in which dialog box has to be displayed
     */
    public void jopEmptyValue(JFrame frame);

    /**
     * Procedure prints language in which the interface is currently displayed.
     *
     * @param language Language in String.
     */
    public void msgLanguageIsSetTo(String language);

    /**
     * Procedure prints information about that the language is invalid and is
     * set to default.
     */
    public void msgLanguageInvalid();

    /**
     * Procedure prints information that user has chosen wrong option.
     */
    public void msgWrongOption();

    /**
     * Procedure prints information that startup arguments have been loaded.
     */
    public void msgInitializationFinished();

    /**
     * Procedure prints information that startup arguments were invalid.
     */
    public void msgInitializationFailed();

    /**
     * Procedure prints information that exit option was chosen.
     */
    public void msgExit();

    /**
     * Procedure prints the message that should be displayed after IOException.
     */
    public void msgExceptionIO();

    /**
     * Procedure hows in dialog window the message that should be displayed
     * after NumberFormatException.
     */
    public void msgExceptionNumberFormat();

    /**
     * Procedure shows dialog box with the message that should be displayed
     * after NumberFormatException.
     *
     * @param frame Parent frame in which dialog box has to be displayed
     */
    public void jopExceptionNumberFormat(JFrame frame);

    /**
     * Procedure prints the message that should be displayed after
     * WrongNumericalIntervalException.
     */
    public void msgValueOutOfRequiredIntervalException();

    /**
     * Procedure shows in dialog window the message that should be displayed
     * after WrongNumericalIntervalException.
     *
     * @param frame Parent frame in which dialog box has to be displayed
     */
    public void jopValueOutOfRequiredIntervalException(JFrame frame);

    /**
     * Procedure asks the user for the number which has to be tested for
     * primality.
     */
    public void askForFermatNumber();

    /**
     * Procedure asks the user for the number according to which the primality
     * test has to be performed. Should happen if user chooses to put the number
     * by himself, not automatically by the application.
     */
    public void askForFermatNumberForTest();

    /**
     * Procedure prints the number "a" in Fermat's test which was generated
     * automatically by the application. Should happen after user chooses
     * application to generate the number for him.
     *
     * @param number the number to be displayed.
     * @deprecated current version of view does not provide showing these
     * numbers in this way.
     */
    @Deprecated
    public void showGeneratedNumberToTest(String number);

    /**
     * Procedure displays the available options about the Fermat's test.
     *
     * @deprecated current version of application does not provide option to be
     * chosen in Fermat test.
     */
    @Deprecated
    public void showFermatTestOptions();

    /**
     * Procedure asks the user for number of the tests he wants to perform
     * during Fermat test.
     */
    public void askForAmountOfFermatTests();

    /**
     * @return Returns text of "File" item in menu bar.
     */
    public String textJmFile();

    /**
     * @return Returns text of "Edit" item in menu bar.
     */
    public String textJmEdit();

    /**
     * @return Returns text of "Help" item in menu bar.
     */
    public String textJmHelp();

    /**
     * @return Returns text of "New test" item in menu bar.
     */
    public String textJmiNewTest();

    /**
     * @return Returns text of "Save test" item in menu bar.
     */
    public String textJmiSave();

    /**
     * @return Returns text of "Open test" item in menu bar.
     */
    public String textJmiOpen();

    /**
     * @return Returns text of "Exit" item in menu bar.
     */
    public String textJmiExit();

    /**
     * @return Returns text of "Fermat test" item in menu bar.
     */
    public String textJmiFermat();

    /**
     * @return Returns text of JLabel "TestedNum" in "Tab" class.
     */
    public String textJlTestedNum();

    /**
     * @return Returns text of column "TestedOn" in "Tab" class.
     */
    public String textColTestedOn();

    /**
     * @return Returns text of column "Result" in "Tab" class.
     */
    public String textColResult();

    /**
     * @return Returns positive or negative in given language.
     * @param res true/false to be translated.
     */
    public String textResult(boolean res);

    /**
     * @return Returns "Perform test" in given language.
     */
    public String textJbPerformTest();

    /**
     * @return Returns input value done by user after JOptionPane asks for it.
     */
    public String jopAskForNumber();

}
