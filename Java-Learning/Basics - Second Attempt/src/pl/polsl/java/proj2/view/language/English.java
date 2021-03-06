package pl.polsl.java.proj2.view.language;

import java.util.ArrayList;

/**
 * Concrete class of the bridge implementer, stands for English language (ENG).
 *
 * @author MarczykLAP
 * @version 2.0.1
 */
public class English implements LanguageImplementer {

    /**
     * Non-parametric constructor.
     */
    public English() {

    }

    @Override
    public void msgEmptyValue() {
        System.out.println("No value was entered!");
    }

    @Override
    public void showMenu() {
        System.out.println("[1] - Do fermat's test");
        System.out.println("[2] - Exit");
    }

    @Override
    public void msgLanguageIsSetTo(String language) {
        System.out.println("User interface is set to: " + language + ".");
    }

    @Override
    public void msgLanguageInvalid() {
        System.out.println("Language was invalid, language is set to default.");
    }

    @Override
    public void msgInitializationFinished() {
        System.out.println("Initializing process of startup parameters has finished.");
    }

    @Override
    public void msgInitializationFailed() {
        System.out.println("Input parameters are invalid! Program will be exitted.");
    }

    @Override
    public void msgExit() {
        System.out.println("Exit was choosen.");
    }

    @Override
    public void msgExceptionIO() {
        System.out.println("Error while reading data.");
    }

    @Override
    public void msgExceptionNumberFormat() {
        System.out.println("Input value format is incorrect.");
    }

    @Override
    public void msgValueOutOfRequiredIntervalException() {
        System.out.println("Number or its component does not meet the interval requirements.");
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
    @Deprecated
    public void showFermatTestOptions() {
        System.out.println("Test requires reference value for which will be executed.");
        System.out.println("1 - I want to choose the value by myself");
        System.out.println("2 - I want the program to choose random value");
        System.out.println("3 - Cancel - back to menu");
    }

    @Override
    @Deprecated
    public void showGeneratedNumberToTest(String number) {
        System.out.println("Generated reference value is: " + number);
    }

    @Override
    public void showTestResults(String testedNumber, ArrayList<String> testedOnValue, ArrayList<Boolean> resultOfTest) {

        for (int i = 0; i < resultOfTest.size(); i++) {
            String resultMessage;
            if (resultOfTest.get(i) == true) {
                resultMessage = "Positive";
            } else {
                resultMessage = "Negative";
            }
            System.out.println((i + 1) + ". " + resultMessage + " on value: " + testedOnValue.get(i));

        }
    }

    @Override
    public void askForAmountOfFermatTests() {
        System.out.println("Please enter amout of the tests you will be performing");
    }
}
