package pl.polsl.java.proj2;

import pl.polsl.java.proj2.view.UserInterface;

/**
 * The main class of program which does primality test.
 *
 * @author MarczykLAP
 * @version 1.0.2
 */
public class PrimalityTestMain {

    /**
     * Boolean type specifies whether the main infinite loop should remain or be
     * left because of order to exit the program.
     */
    private boolean runProgram = true;

    /**
     * User interface object which is controlling whole application and is
     * communicating with the user.
     */
    private UserInterface userInterface = new UserInterface();

    /**
     * The main method.
     *
     * @param args is program call parameter, supposed to be ID of language.
     * List of supported languages "Language(ID)":
     * <p>
     * Polish(PL), English(ENG)</p>
     *
     */
    public static void main(String args[]) {

        PrimalityTestMain primalityTest = new PrimalityTestMain();
        try {
            primalityTest.userInterface.setLanguage(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            primalityTest.userInterface.msgInitializationFailed();
            primalityTest.runProgram = false;
        } finally {
            primalityTest.userInterface.msgInitializationFinished();
        }

        while (primalityTest.runProgram) {
            primalityTest.runProgram = primalityTest.userInterface.menuService();
        }

    }
}
