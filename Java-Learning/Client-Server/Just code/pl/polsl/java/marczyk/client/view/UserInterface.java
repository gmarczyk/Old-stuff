package pl.polsl.java.marczyk.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.java.marczyk.client.RequestSender;
import pl.polsl.java.marczyk.server.model.ValueOutOfRequiredIntervalException;
import pl.polsl.java.marczyk.server.model.ProbabilisticTests;
import pl.polsl.java.marczyk.client.view.language.English;
import pl.polsl.java.marczyk.client.view.language.LanguageImplementer;
import pl.polsl.java.marczyk.client.view.language.Polish;

/**
 * Class responsible for getting input data and displaying all communicates. All
 * interaction with user is done there.
 *
 * @author MarczykLAP
 * @version 3.0.
 */
public final class UserInterface implements ActionListener {

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
    private ProbabilisticTests modelProbabilisticTests = new ProbabilisticTests();

    /**
     * Object of a class that represents main window of the application.
     */
    private MainWindow viewMainWindow;
    
    private RequestSender refToRequestSender;

    /**
     * Sets the main display up.
     * @param sender is the request to the server sender
     */
    public UserInterface(RequestSender sender) {
        this.language = Language.ENG;
        this.languageImplementer = new English();
        this.refToRequestSender = sender;
        
        this.viewMainWindow = new MainWindow(this, this.languageImplementer);
        this.viewMainWindow.reinitLanguage(languageImplementer);

    }
    
    /**
     * Getter of object which represents main window of the view.
     * 
     * @return instance of main window of the view
     */
    public MainWindow getMainWindow(){
        return viewMainWindow;
    }

    /**
     * Initializes MainWindow JFrame and reinitializes its language to proper
     * one.
     * @deprecated Structure of app in program no.4 requires to have these
     * values initialized in constructor
     */
    @Deprecated
    public void runFrame() {
        this.viewMainWindow = new MainWindow(this, this.languageImplementer);
        this.viewMainWindow.reinitLanguage(languageImplementer);
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

            if (viewMainWindow != null) {
                viewMainWindow.reinitLanguage(languageImplementer);
            }

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
     * @deprecated used in console-view.
     */
    @Deprecated
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
     *
     * @deprecated used in console-view.
     */
    @Deprecated
    public void performFermatTest() {

        try {
            this.modelProbabilisticTests.cleanNum();

            this.languageImplementer.askForFermatNumber();
            String inputNumber = this.terminalReader.readLine();
            this.modelProbabilisticTests.setNumber(inputNumber);

            this.languageImplementer.askForAmountOfFermatTests();
            int howMany = Integer.parseInt(this.terminalReader.readLine());

            for (int i = 0; i < howMany; i++) {
                System.out.print((i + 1) + ". ");
                this.languageImplementer.askForFermatNumberForTest();
                this.modelProbabilisticTests.setTestOnNumber(this.terminalReader.readLine());
            }

            this.modelProbabilisticTests.calculateFermatTest();

            this.languageImplementer.showTestResults(this.modelProbabilisticTests.getNumber(),
                    this.modelProbabilisticTests.getTestedOnNumber(),
                    this.modelProbabilisticTests.getResult());

        } catch (IOException ex) {
            this.languageImplementer.msgExceptionIO();
        } catch (NumberFormatException ex) {
            this.languageImplementer.msgExceptionNumberFormat();
        } catch (IllegalArgumentException ex) {
            this.languageImplementer.msgEmptyValue(); // thrown by setters
        } catch (ValueOutOfRequiredIntervalException ex) {
            this.languageImplementer.msgValueOutOfRequiredIntervalException();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    /**
     * Components event handling.
     *
     * @param e action generated by some object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("JTB_ADD_ROW")) {

            viewMainWindow.addRowOnCurrentTab();
            modelProbabilisticTests.cleanTestedOnNum();
            modelProbabilisticTests.cleanResults();

        } else if (actionCommand.equals("JTB_DEL_ROW")) {

            viewMainWindow.delRowCurrentlySelected();
            modelProbabilisticTests.cleanTestedOnNum();
            modelProbabilisticTests.cleanResults();

        } else if (actionCommand.equals("JMI_NEW_FERMAT")) {

            String testedNum = languageImplementer.jopAskForNumber();
            boolean isOk = false;

            try {
                modelProbabilisticTests.cleanTestedOnNum();
                modelProbabilisticTests.cleanResults();
                modelProbabilisticTests.cleanNum();
                modelProbabilisticTests.setNumber(testedNum);
                isOk = true;
            } catch (IllegalArgumentException | NullPointerException ex) {
                languageImplementer.jopEmptyValue(viewMainWindow); // thrown by setters
            } catch (ValueOutOfRequiredIntervalException ex) {
                languageImplementer.jopValueOutOfRequiredIntervalException(viewMainWindow);
            }
            //

            if (isOk == true) {
                viewMainWindow.createTab(testedNum);
                ProbabilisticTests.countOfTests++;
            }

        } else if (actionCommand.equals("JMI_EXIT")) {

            viewMainWindow.closeFrame();

        } else if (actionCommand.equals("JB_PERFORM_TEST")) {

            String[] valuesToSet = viewMainWindow.getNumbersFromCurrentTab();
            String   numberToSet = viewMainWindow.getNameOfCurrentTab();
            
            if(    (numberToSet == null) 
                || (numberToSet.equals(""))
                || (valuesToSet.length == 0) ) {
                
                System.out.println("Cannot perform test, either no number to test or values to test on are entered");
            } else {
                
                String   valueToSetString = "";
                for (int i = 0; i < valuesToSet.length - 1; i++) {                         // From index 2 to index max-1
                     valueToSetString = valueToSetString + valuesToSet[i] + " ";
                }
                valueToSetString = valueToSetString + valuesToSet[valuesToSet.length-1];   // Last arg mustn't have space " " after it 

                refToRequestSender.sendMessage("FERMAT SET NUMBER " + numberToSet);
                refToRequestSender.sendMessage("FERMAT SET VALUES " + valueToSetString );
                refToRequestSender.sendMessage("FERMAT RUN .");
                refToRequestSender.sendMessage("FERMAT GET RESULTS");

            }
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
