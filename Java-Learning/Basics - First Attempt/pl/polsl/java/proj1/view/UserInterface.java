/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj1.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.polsl.java.proj1.model.FermatTest;
import pl.polsl.java.proj1.model.NotNumericUnsignedValueException;
import pl.polsl.java.proj1.model.WrongNumericalIntervalException;
import pl.polsl.java.proj1.view.language.*;

/**
 * Class responsible for getting input data and displaying all communicates. All
 * interaction with user is done there.
 *
 * @author MarczykLAP
 * @version 1.0.0
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
    private FermatTest fermatTest = new FermatTest();

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
            this.languageImplementer.msgLanguageSetTo(langToSet);
        } catch (IllegalArgumentException ex) {
            this.languageImplementer.msgLanguageInvalid();
            this.language = Language.ENG; // Setting default language as ENG
            this.setImplementer();
        }

    }

    /**
     * Sets implementer to proper state using current value of enum Language
     * field.
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
     * Prints the menu and all communicates and asks user for data. Also
     * performs proper action that user wanted to take.
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

            this.languageImplementer.askForFermatNumber();
            String inputNumber = this.terminalReader.readLine();
            this.fermatTest.setNumber(inputNumber);

            this.languageImplementer.showFermatTestOptions();

            int option = Integer.parseInt(this.terminalReader.readLine());
            switch (option) {
                case 1:
                    this.languageImplementer.askForFermatNumberForTest();
                    String inputTestOnNumber = this.terminalReader.readLine();
                    this.fermatTest.setTestOnNumber(inputTestOnNumber);
                    this.fermatTest.calculateFermatTest();
                    this.languageImplementer.showTestResults(this.fermatTest.getNumber(),
                            this.fermatTest.getTestedOnNumber(), this.fermatTest.getResult());

                    break;
                case 2:
                    this.fermatTest.generateRandomNumber();
                    this.languageImplementer.showGeneratedNumberToTest();
                    System.out.println(this.fermatTest.getTestedOnNumber());
                    this.fermatTest.calculateFermatTest();
                    this.languageImplementer.showTestResults(this.fermatTest.getNumber(),
                            this.fermatTest.getTestedOnNumber(), this.fermatTest.getResult());
                    break;
                case 3:
                    break;
            }

        } catch (IOException ex) {
            this.languageImplementer.msgExceptionIO();
        } catch (NotNumericUnsignedValueException ex) {
            this.languageImplementer.msgExceptionNotNumericUnsignedValue();
        } catch (WrongNumericalIntervalException ex) {
            this.languageImplementer.msgExceptionWrongNumericalInterval();
        }
    }

    /**
     * Procedure displays message in specified by UserInterface language which
     * informs about ArrayIndexOutOfBoundException. Message has to be public in
     * order to execute in the main method while checking if startup arguments
     * are correct.
     */
    public void msgExceptionArrayIndexOutOfBounds() {
        this.languageImplementer.msgExceptionArrayIndexOutOfBounds();
    }

    /**
     * Procedure displays message in specified by UserInterface language which
     * informs about finishing the initialization of startup options. Message
     * has to be public in order to execute in the main method while checking if
     * startup arguments are correct.
     */
    public void msgInitializationFinished() {
        this.languageImplementer.msgInitializationFinished();
    }

}
