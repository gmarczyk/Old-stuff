/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj1.view.language;

/**
 * Implementer of the bridge-pattern used to display communicates in different
 * languages.
 *
 * @author Marczyk Grzegorz
 * @version 1.0.0
 */
public interface LanguageImplementer {

    /**
     * Procedure prints information about recent Fermat's test with the results.
     *
     * @param testedNumber The number which was tested ("p" in formula).
     * @param testedOnValue Value according to which the test was performed ("a"
     * in formula).
     * @param resultOfTest Result in true-false whether the test was positive or
     * not.
     */
    public void showTestResults(String testedNumber, String testedOnValue, boolean resultOfTest);

    /**
     * Procedure prints the main menu of the application.
     */
    public void showMenu();

    /**
     * Procedure prints language in which interface is currently displayed.
     *
     * @param language Language in String.
     */
    public void msgLanguageSetTo(String language);

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
     * ArrayIndexOutOfBoundsException.
     */
    public void msgExceptionArrayIndexOutOfBounds();

    /**
     * Procedure prints the message that should be displayed after
     * WrongNumericalIntervalException.
     */
    public void msgExceptionWrongNumericalInterval();

    /**
     * Procedure prints the message that should be displayed after
     * NumericUnsignedValueException.
     */
    public void msgExceptionNotNumericUnsignedValue();

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
     */
    public void showGeneratedNumberToTest();

    /**
     * Procedure displays the available options about the Fermat's test.
     */
    public void showFermatTestOptions();

}
