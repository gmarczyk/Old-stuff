/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj1.view.language;

/**
 * Concrete class of the bridge implementer, stands for English language (ENG).
 *
 * @author MarczykLAP
 * @version 1.0.0
 */
public class English implements LanguageImplementer {

    /**
     * Non-parametric constructor.
     */
    public English() {

    }

    @Override
    public void showMenu() {
        System.out.println("[1] - Do fermat's test");
        System.out.println("[2] - Exit");
    }

    @Override
    public void msgLanguageSetTo(String language) {
        System.out.println("User interface is set to: " + language + ".");
    }

    @Override
    public void msgLanguageInvalid() {
        System.out.println("Language was invalid, language is set to default.");
    }

    @Override
    public void msgInitializationFinished() {
        System.out.println("Initialization finished.");
    }

    @Override
    public void msgExit() {
        System.out.println("Exit was choosen.");
    }

    @Override
    public void msgExceptionArrayIndexOutOfBounds() {
        System.out.println("Wrong startup options.");
    }

    @Override
    public void msgExceptionIO() {
        System.out.println("Error while reading data.");
    }

    @Override
    public void msgExceptionNumberFormat() {
        System.out.println("Input value is incorrect.");
    }

    @Override
    public void msgExceptionNotNumericUnsignedValue() {
        System.out.println("Input value consists of characters that are not digits.");
    }

    @Override
    public void msgExceptionWrongNumericalInterval() {
        System.out.println("Number does not meet the interval requirements.");
    }

    @Override
    public void msgWrongOption() {
        System.out.println("You have chosen wrong option.");
    }

    @Override
    public void askForFermatNumber() {
        System.out.println("Please enter number which you want to test.");
        System.out.println("Number must higher than 1!.");
    }

    @Override
    public void askForFermatNumberForTest() {
        System.out.println("Enter value from <1 - tested number) for which test will be performed.");
    }

    @Override
    public void showFermatTestOptions() {
        System.out.println("Test requires reference value for which will be executed.");
        System.out.println("1 - I want to choose the value by myself");
        System.out.println("2 - I want the program to choose random value");
        System.out.println("3 - Cancel - back to menu");
    }

    @Override
    public void showGeneratedNumberToTest() {
        System.out.println("Generated reference value is: ");
    }

    @Override
    public void showTestResults(String testedNumber, String testedOnValue, boolean resultOfTest) {
        System.out.println("Test was done for number: " + testedNumber + ".");
        System.out.println("Was based on the number : " + testedOnValue + ".");
        String tmp;
        if (resultOfTest) {
            tmp = "Positive";
        } else {
            tmp = "Negative";
        }
        System.out.println("Result of the test: " + tmp + ".");

    }
}
