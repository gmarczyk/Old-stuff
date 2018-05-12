package pl.polsl.java.proj2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.polsl.java.proj2.model.ValueOutOfRequiredIntervalException;
import pl.polsl.java.proj2.model.ProbabilisticTests;

import pl.polsl.java.proj2.view.language.*;

/**
 * Class responsible for getting input data and displaying all communicates. All
 * interaction with user is done there.
 *
 * @author MarczykLAP
 * @version 2.0.1
 */
public final class UserInterface {

    /**
     * Available languages of the interface.
     */
    private enum Language {
        PL, ENG;
    }

    /**
     * Specifies the current language of menu.
     */
    private Language language;

    /**
     * Reference to the implementer of the bridge-pattern.
     */
    private LanguageImplementer languageImplementer;

    /**
     * Is used to perform readline() function to obtain the input data from the
     * user.
     */
    private final BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Part of the application model. FermatTest class is used to calculate
     * primality of a number.
     */
    private ProbabilisticTests probabilisticTests = new ProbabilisticTests();

    /**
     * Non-parametric constructor. Sets the default options.
     */
    public UserInterface() {
        this.language = Language.ENG;
        this.languageImplementer = new English();
    }

    /**
     * Sets interface to different language.
     *
     * @param langToSet language that has to be set. Must be equal to one of
     * enum "Language" fields.
     */
    public void setLanguage(String langToSet) {

        try {
            this.language = Language.valueOf(langToSet);
            this.setImplementer();
            this.languageImplementer.msgLanguageIsSetTo(langToSet);
        } catch (IllegalArgumentException ex) {
            this.languageImplementer.msgLanguageInvalid();
        }
    }

    /**
     * Sets implementer to proper state using current value of enum Language
     * field. No default case, all enum cases should be covered for proper
     * functionality.
     */
    private void setImplementer() {

        switch (this.language) {
            case PL:
                this.languageImplementer = new Polish();
                break;
            case ENG:
                this.languageImplementer = new English();
                break;
        }
    }

    /**
     * Prints the menu and all messages and asks user for data. Also performs
     * proper action that user wants to take.
     *
     * @return True - if exit wasn't chosen. False - if exit was chosen.
     */
    public boolean menuService() {

        this.languageImplementer.showMenu();
        int option;

        try {
            // Get input data from user - which option was chosen.
            option = Integer.parseInt(this.terminalReader.readLine());
            switch (option) {
                case 1:
                    this.performFermatTest();
                    break;
                case 2:
                    this.languageImplementer.msgExit();
                    return false;
                default:
                    this.languageImplementer.msgWrongOption();
                    break;
            }

        } catch (IOException e) {
            this.languageImplementer.msgExceptionIO();
        } catch (NumberFormatException e) {
            this.languageImplementer.msgExceptionNumberFormat();
        }
        return true;
    }

    /**
     * Performs Fermat's test. Firstly asks for the number which primality has
     * to be tested, then asks for the reference value required to perform the
     * test. User can choose if he wants to put the value by himself whether the
     * program has to do it for him.
     */
    public void performFermatTest() {

        try {
            this.probabilisticTests.clean();

            this.languageImplementer.askForFermatNumber();
            String inputNumber = this.terminalReader.readLine();
            this.probabilisticTests.setNumber(inputNumber);

            this.languageImplementer.askForAmountOfFermatTests();
            int howMany = Integer.parseInt(this.terminalReader.readLine());

            for (int i = 0; i < howMany; i++) {
                System.out.print((i + 1) + ". ");
                this.languageImplementer.askForFermatNumberForTest();
                this.probabilisticTests.setTestOnNumber(this.terminalReader.readLine());
            }

            this.probabilisticTests.calculateFermatTest();

            this.languageImplementer.showTestResults(this.probabilisticTests.getNumber(),
                    this.probabilisticTests.getTestedOnNumber(),
                    this.probabilisticTests.getResult());

        } catch (IOException ex) {
            this.languageImplementer.msgExceptionIO();
        } catch (NumberFormatException ex) {
            this.languageImplementer.msgExceptionNumberFormat();
        } catch (IllegalArgumentException ex) {
            this.languageImplementer.msgEmptyValue(); // thrown by setters
        } catch (ValueOutOfRequiredIntervalException ex) {
            this.languageImplementer.msgValueOutOfRequiredIntervalException();
        }
    }

    /**
     * Procedure displays message in specified by UserInterface language which
     * informs about finishing the initialization of startup options. Message
     * has to be public in order to execute in the main method while checking
     * startup arguments.
     */
    public void msgInitializationFinished() {
        this.languageImplementer.msgInitializationFinished();
    }

    /**
     * Procedure displays message in specified by UserInterface language which
     * informs about failing the initialization of startup options. Message has
     * to be public in order to execute in the main method while checking if
     * startup arguments are correct.
     */
    public void msgInitializationFailed() {
        this.languageImplementer.msgInitializationFailed();
    }

}
