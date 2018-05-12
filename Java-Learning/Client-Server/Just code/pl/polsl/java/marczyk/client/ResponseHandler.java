/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.client;

import pl.polsl.java.marczyk.client.view.MainWindow;

/**
 * Class is responsible for handling response instruction given by server.
 *
 * @author Marczyk Grzegorz
 * @version 3.0.0
 */
public class ResponseHandler {

    /**
     * Reference to instance of main window of the application, used to perform
     * actions on the window
     */
    private MainWindow referenceToView;

    /**
     * Constructor with the parameter of view from client
     * @param aView reference to object of the app's display
     */
    public ResponseHandler(MainWindow aView) {

        this.referenceToView = aView;
    }

    /**
     * Handle the response from server which comes as an instruction
     * @param serversResponse Instruction from server represented as string
     */
    public void handleServersResponse(String serversResponse) {

        if ((serversResponse != null) && (!serversResponse.equals(""))) {
            
            if(!(serversResponse.substring(0, 3).equalsIgnoreCase("MSG"))) {   // If not MSG command - its an instruction to handle


                String[] instrWords = serversResponse.split(" ");              // Split string to separate words
                String   command = instrWords[0];                              // First one is command
                String   option = instrWords[1];                               // Second is option
                String   argsString = "";                                      // Then form one big string of arguments
                for (int i = 2; i < instrWords.length - 1; i++) {              // From index 2 to index max-1
                    argsString = argsString + instrWords[i] + " ";
                }
                argsString = argsString + instrWords[instrWords.length - 1];   // Last arg mustn't have space " " after it

                String   argsWords[] = argsString.split(" "); 

                switch (command.toUpperCase()) {
                    case "FERMAT":
                        switch (option.toUpperCase()) {

                            case "RCV":
                                switch (argsWords[0].toUpperCase()) {
                                    case "RESULTS":
                                        if(argsWords.length > 1) {  // in case of empty arguments (first arg is "RESULTS", after it the real results for view 
                                            this.placeResultsToView(argsWords);
                                        } else {
                                            System.out.println("No results received!");
                                        }
                                        break;
                                    default:
                                        System.out.println("Unrecognized argument : " + serversResponse);
                                }
                                break;
                            default:
                                System.out.println("Unrecognized option : " + option);
                        }
                        break;   // Case FERMAT break;

                    default:     // End of COMMAND switch
                        System.out.println("Unrecognized command : " + serversResponse);
                }
            }
        }

    }
    
    /**
     * Places received results to the table on display
     * @param theResults receiver arguments in server's response
     */
    private void placeResultsToView(String[] theResults){
        
        if(!(theResults[1].equalsIgnoreCase("EMPTY")) ){
            Boolean[] resultsToTab = new Boolean[theResults.length-1];
            for (int i = 0; i < resultsToTab.length; i++) {
                if     (theResults[i+1].equalsIgnoreCase("TRUE"))  resultsToTab[i] = true;
                else if(theResults[i+1].equalsIgnoreCase("FALSE")) resultsToTab[i] = false;
                else System.out.println("[VIEW] Result: " + theResults[i+1] + " is not a boolean !!!");
            }

            referenceToView.setResultsOnCurrentTab(resultsToTab);
        } else {
            System.out.println("Results received are empty! cannot place them to the view");
        }
    }

}
