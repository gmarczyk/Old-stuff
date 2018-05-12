/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import pl.polsl.java.marczyk.protocole.Protocole;
import pl.polsl.java.marczyk.server.model.ProbabilisticTests;
import pl.polsl.java.marczyk.server.model.ValueOutOfRequiredIntervalException;

public class SingleService extends Thread {

    /** Socket represents a single connection between server and client */
    private final Socket socket;

    /** Used to receive requests from client */
    private final BufferedReader requestReceiver;

    /** Used to send responses to client */
    private final PrintWriter responseSender;
    
    /** Reference to servers protocole, validation of received requests*/
    Protocole refToProtocole;
    
    /** Reference to servers model needed to load it and perform calculations */
    ProbabilisticTests refToModel;


    /**
     * Constructor basically sets the connection and prepares all streams.
     * @param socket connection socket
     * @param protocole protocole taken from the server
     * @param model model for calculations taken from the server
     * @throws IOException when initializing the streams fail
     */
    public SingleService(Socket socket, Protocole protocole, ProbabilisticTests model) throws IOException {
        
        this.socket = socket;
        responseSender = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        requestReceiver = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        this.refToProtocole = protocole;
        this.refToModel = model;

    }

    /**
     * Runs the service and handles all requests
     */
    @Override
    public void run() {
        try {
            
            while (true) {
                
                String receivedString = requestReceiver.readLine();
                if(receivedString.equalsIgnoreCase("EXIT")) break;
                
                if( (receivedString != null) && (!receivedString.equals("")) ) {   
                    
                System.out.println("From [CLIENT] to [SERVER]: " + receivedString);
                responseSender.flush();
                
                if(receivedString.equalsIgnoreCase("HELP")){
                    
                    this.responseSender.println("MSG Client request instructions for client in given schema");
                    this.responseSender.println("MSG [COMMAND] (OPTION) *ARGUMENTS exampleArg1 exampleArg2 ...exampleArgN*");
                    this.responseSender.println("MSG [FERMAT] (GET) *NUMBER, VALUES, RESULTS*");
                    this.responseSender.println("MSG [FERMAT] (SET) *NUMBER x, VALUES x y ...n*");
                    this.responseSender.println("MSG [FERMAT] (RUN) *.*");
                    break;  
                }
                    
                   if(refToProtocole.isInstructionValid(receivedString)){
                               
                       String[] instrWords  = receivedString.split(" ");            // Split string to separate words
                       String   command     = instrWords[0];                        // First one is command
                       String   option      = instrWords[1];                        // Second is option
                       String   argsString  =  "";                                  // Then form one big string of arguments
                       for (int i = 2; i < instrWords.length - 1; i++) {            // From index 2 to index max-1
                            argsString = argsString + instrWords[i] + " ";
                       }
                       argsString = argsString + instrWords[instrWords.length-1];   // Last arg mustn't have space " " after it 
                       
                       String   argsWords[] = argsString.split(" ");                // Split big string of args to separate words
                       for (int i = 0; i < argsWords.length; i++) {
                           System.out.println(argsWords[i] + " nr. " + i);
                       }
                       
                       this.responseSender.println("MSG [SERVER] Received instruction ->"
                               + " Command: " + command  
                               + ", Option: " + option  
                               + ", Args "    + argsString);
 
                       
                       switch(command.toUpperCase()){    
                           case "FERMAT":
                               switch(option.toUpperCase()){
                                    case "GET":
                                        switch(argsString.toUpperCase()){
                                            case "NUMBER":
                                                this.fermatGetNum();
                                                break;
                                            case "VALUES":
                                                this.fermatGetValues();
                                                break;
                                            case "RESULTS":
                                                this.fermatGetResults();
                                                break; 
                                            default:
                                                this.responseSender.println("MSG Unrecognized argument : " + receivedString);
                                        }
                                        break;
                                    case "SET": 
                                        switch(argsWords[0].toUpperCase()){
                                            case "NUMBER":
                                                this.fermatSetNum(argsString);
                                                break;
                                            case "VALUES":
                                                this.fermatSetValues(argsWords);
                                                break;
                                            default:
                                                this.responseSender.println("MSG Unrecognized argument : " + receivedString);
                                        }
                                        break;
                                    case "RUN": 
                                        try {
                                            this.refToModel.calculateFermatTest();
                                        } catch (ValueOutOfRequiredIntervalException ex) {
                                             this.responseSender.println("MSG [MODEL] Calculation error : " + ex.getMessage());
                                        } catch (Exception ex) {
                                             this.responseSender.println("MSG [MODEL] Problem with tested number or reference values : " + ex.getMessage());
                                        }
                                        break;
                                    default:
                                        this.responseSender.println("MSG Unrecognized option : " + option);
                                }
                                break;  // Case FERMAT break;

                           default:     // End of COMMAND switch
                               this.responseSender.println("MSG Unrecognized command : " + receivedString);
                       }
                   } else {
                   
                       this.responseSender.println("MSG Invalid instruction : " + receivedString);
                       this.responseSender.println(refToProtocole.getInvalidReason());
                   }
                }    
            }
            
        } catch (IOException e) {
            this.responseSender.println(e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("MSG [SERVER] Single service has ended");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    /**
     * Handles the instruction of getting tested number from fermat test
     */
    private void fermatGetNum() {
        
        String getNum = refToModel.getNumber();
        if(getNum == null || (getNum.equals(""))) responseSender.println("FERMAT RCV NUMBER " + "EMPTY");
        else responseSender.println("FERMAT RCV NUMBER " + getNum);
    }
    
    /**
     * Handles the instruction of getting values from fermat test
     */
    private void fermatGetValues() {
        
        String getValues = "";
        for(String singleValue : refToModel.getTestedOnNumber()){
            getValues += " " + singleValue ;
        }
        if(getValues.equals("")) responseSender.println("FERMAT RCV VALUES " + "EMPTY");
        else responseSender.println("FERMAT RCV VALUES " + getValues);
    }
    
    /**
     * Handles the instruction of getting results from fermat test
     */
    private void fermatGetResults() {
        
        String getResults = "";
        for(Boolean singleResult : refToModel.getResult()){
            getResults += singleResult.toString() + " " ;
        }
        if(!getResults.equals("")) {
            getResults = getResults.substring(0, getResults.length()-1);    // delete space at end
            responseSender.println("FERMAT RCV RESULTS " + getResults);
        }
        else responseSender.println("FERMAT RCV RESULTS " + "EMPTY");
    }
    
    /**
     * Handles the instruction of setting tested number on fermat test model
     * @param numToSet the number which has to be tested
     */
    private void fermatSetNum(String numToSet) {
        
        String[] splitArgs = numToSet.split(" ");
        
        if(splitArgs.length < 2) {
              responseSender.println("MSG [MODEL] No number to set detected!");
        } else {
            
            try {
                 refToModel.setNumber(splitArgs[1]);
                 refToModel.cleanTestedOnNum();
                 refToModel.cleanResults();
            } catch (ValueOutOfRequiredIntervalException e) {
            
                responseSender.println("MSG [MODEL] ValueOutOfRequiredIntervalException in fermatSetNumber at value : "
                      + numToSet + " Exception msg:  " + e.getMessage());
                refToModel.cleanNum();
            }    
        }
    }
    
    /**
     * Handles the instruction of setting reference values on which the fermat's
     * test has to be performed
     * @param argsToSet the values to set
     */
    private void fermatSetValues(String[] argsToSet) {
        
        
        if(argsToSet.length < 2) {
             responseSender.println("MSG [MODEL] No values detected!");
        } else {
            
            String   valToReport = argsToSet[1];
      
            try {
                for (int i=1; i< argsToSet.length; i++) {
                     valToReport = argsToSet[i];
                     refToModel.setTestOnNumber(argsToSet[i]);
                 }
            } catch (ValueOutOfRequiredIntervalException e) {

                responseSender.println("MSG [MODEL] ValueOutOfRequiredIntervalException in fermatSetValues at value: "
                        + valToReport + " Exception msg:  " + e.getMessage());
                refToModel.cleanTestedOnNum();
            } catch (Exception e) {
                responseSender.println("MSG [MODEL] Exception in fermatSetValues at value: "
                        + valToReport + " Exception msg:  " + e.getMessage());
                refToModel.cleanTestedOnNum();
            }    
        }
    }
    
 
}

