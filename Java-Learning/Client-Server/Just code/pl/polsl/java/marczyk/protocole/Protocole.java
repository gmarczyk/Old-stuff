/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.protocole;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.java.marczyk.protocole.commands.FermatCommand;

/**
 * Protocole checks if the transmission is valid.
 * @author Marczyk Grzegorz
 * @version 1.0.0
 */
public class Protocole {

    /** Recognizes if receiver is client or server */
    private ProtocoleUserType userType;
    
    /** Object of validator for FERMAT command*/
    InCommandHandler fermatCommandValidator = new FermatCommand();
    
    /** Flags of validation, used to recognize the problem with transmission */
    boolean commandIsValid, optionIsValid, argsAreValid, wordAmountValid;

    /** List of commands that Client can receive */
    private enum ClientCommandList {

        FERMAT,
        
        
        MSG;    // Special instr.

    }
    
    /** List of commands that Server can receive */
    private enum ServerCommandList {

        FERMAT,
        
        
        HELP;   // Special instr.

    }

    /**
     * Constructor with the type of protocole user
     * @param type user type to recognize who receives messages
     */
    public Protocole(ProtocoleUserType type) {
        this.userType = type;
    }
    
    /**
     * Just resetting flags to "all ok"
     */
    private void setFlagsTrue(){
        this.commandIsValid  = true;
        this.optionIsValid   = true;
        this.argsAreValid    = true;
        this.wordAmountValid = true;
    }
    
    /**
     * Forms log with problems
     * @return String with information what failed
     */
    public String getInvalidReason(){
        
        String returnReason = "[PROTOCOLE]";
        if (this.wordAmountValid == false ) return "[PROTOCOLE] Amount of words in instruction is incorrect, ";
        if (this.commandIsValid  == false ) returnReason += " Command is invalid ";
        if (this.optionIsValid   == false ) returnReason += " Option is invalid ";
        if (this.argsAreValid    == false ) returnReason += " Arguments are invalid ";

        return returnReason;
    }

    /**
     * Checks if instruction received is valid
     * @param theInstruction instruction received
     * @return true if valid, false if not
     */
    public boolean isInstructionValid(String theInstruction) {

        if((theInstruction.equalsIgnoreCase("HELP")))                return true;
        if((theInstruction.substring(0, 2).equalsIgnoreCase("MSG"))) return true;
        
        boolean     returnState = true;
        String[]    instrWords  = theInstruction.split(" ");
        this.setFlagsTrue();
        
        if((instrWords.length) < 3) {  
            this.wordAmountValid = false;
            return false; 
        }
        
        String      command      =  instrWords[0].toUpperCase();
        String      option       =  instrWords[1].toUpperCase();
        String      argsString   =  "";
        for (int i = 2; i < instrWords.length - 1; i++) {               // Loop to pre-last arg (max index -1)                
            argsString = argsString + instrWords[i] + " ";
        }
        argsString = argsString + instrWords[instrWords.length-1];      // Last arg mustnt have space " " after it 
        
        
        boolean tmpCommandValidator = false;
        try {
            tmpCommandValidator = isCommandValid(command);
        } catch (ProtocoleException ex) {
            System.out.println("[PROTOCOLE] Command is invalid: " + ex.getMessage());
            Logger.getLogger(Protocole.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!tmpCommandValidator) {
            this.commandIsValid = false;
            returnState = false;
        } 
        
            switch(userType){
                case CLIENT:
                    switch(command){
                        case "FERMAT":
                            if(!(fermatCommandValidator.isOptionValid(option, userType))){
                                this.optionIsValid = false;
                                returnState = false;       
                            } else {
                                if(!(fermatCommandValidator.areArgumentsValid(option, argsString, userType))){
                                    this.argsAreValid = false;
                                    returnState = false;
                                }
                            }
                            break;
                        case "MSG":
                            return true;
                    }
                    break;
                    
                case SERVER:
                    switch(command){
                        case "FERMAT":
                            if(!(fermatCommandValidator.isOptionValid(option, userType))){
                                this.optionIsValid = false;
                                returnState = false;
                            } else {
                                if(!(fermatCommandValidator.areArgumentsValid(option, argsString, userType))){
                                    this.argsAreValid = false;
                                    returnState = false;
                                }
                            }
                            break;
                        case "HELP":
                            return true;
                    }
                    break;
            }
                          
        return returnState;
    }

    /**
     * Checks if command of the instruction is valid
     * @param theCommand command to validate
     * @return true if valid, false if not
     * @throws ProtocoleException when unrecognized protocole user type is given.
     */
    private boolean isCommandValid(String theCommand) throws ProtocoleException {

        switch (userType) {
            case CLIENT:
                for (ClientCommandList command : ClientCommandList.values()) {
                    if (command.toString().equalsIgnoreCase(theCommand)) {
                        return true;
                    }

                }
                return false;
            case SERVER:
                for (ServerCommandList command : ServerCommandList.values()) {
                    if (command.toString().equalsIgnoreCase(theCommand)) {
                        return true;
                    }
                }
                return false;
            default:
                throw new ProtocoleException("ProtocoleUserType not found, may be not supported!");
        }
    }
}
