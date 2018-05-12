package pl.polsl.java.proj3;

import java.awt.EventQueue;
import pl.polsl.java.proj3.view.UserInterface;

/**
 * The main class of program which does primality test.
 *
 * @author MarczykLAP
 * @version 2.0.0
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
    private UserInterface userInterface;

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
        primalityTest.userInterface = new UserInterface();

        try {
            primalityTest.userInterface.setLanguage(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            primalityTest.userInterface.msgInitializationFailed();
            primalityTest.runProgram = false;
        } finally {
            primalityTest.userInterface.msgInitializationFinished();
        }

        if (primalityTest.runProgram == true) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    primalityTest.userInterface.runFrame();
                }
            });
        }
    }
}
