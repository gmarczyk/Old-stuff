package pl.polsl.java.proj2.view.language;

import java.util.ArrayList;

/**
 * Implementer of the bridge-pattern used to display communicates in different
 * languages.
 *
 * @author Marczyk Grzegorz
 * @version 2.0.1
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
     * Procedure prints the message that should be displayed after
     * NumberFormatException.
     */
    public void msgExceptionNumberFormat();

    /**
     * Procedure prints the message that should be displayed after
     * WrongNumericalIntervalException.
     */
    public void msgValueOutOfRequiredIntervalException();

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
}
