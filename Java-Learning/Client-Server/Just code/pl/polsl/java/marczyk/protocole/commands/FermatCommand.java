/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.protocole.commands;

import pl.polsl.java.marczyk.protocole.InCommandHandler;
import pl.polsl.java.marczyk.protocole.ProtocoleUserType;

/**
 *
 * @author Marczyk Grzegorz
 * @version 1.2.0
 */
public class FermatCommand implements InCommandHandler {

    /** Options in instruction that Client can receive */
    private enum ClientOptions{
   
        RCV;
        
    }
    
    /** Options in instruction that Server can receive */
    private enum ServerOptions{
        
        GET,
        SET,
        RUN;
        
    }
    
    /**
     * Checks if the option is valid within this command
     * @param theOption option to check
     * @param userType  protocole user type, recognizes if receiver is client or
     * server
     * @return true if is valid, false if not
     */
    @Override
    public boolean isOptionValid(String theOption, ProtocoleUserType userType) {
        
        switch(userType){
            case CLIENT:
                for (ClientOptions clOption : ClientOptions.values()) {
                    if(clOption.toString().equalsIgnoreCase(theOption)) return true;
                }
                break;
            case SERVER:
                for (ServerOptions swOption : ServerOptions.values()) {
                    if(swOption.toString().equalsIgnoreCase(theOption)) return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the arguments passed in instructions are valid within this command.
     * @param theOption option which carries the arguments
     * @param optionArguments arguments of the option
     * @param userType protocole user type, recognizes if receiver is client or
     * server
     * @return true if are valid, false if not
     */
    @Override
    public boolean areArgumentsValid(String theOption, String optionArguments, ProtocoleUserType userType) {
       
          String[]    arguments   = optionArguments.split(" ");
          boolean     returnState = false;
          
          switch(userType){
              case CLIENT:
                  switch(theOption){
                      
                      case "RCV":
                            switch(arguments[0].toUpperCase()){
                                case "NUMBER": 
                                    returnState = true;
                                case "VALUES":
                                    returnState = true;
                                case "RESULTS":
                                    returnState = true;
                          }
                          break;   
                          
                  } 
                  break;   
              case SERVER:
                  switch(theOption){
                      
                      case "GET":
                         switch(arguments[0].toUpperCase()){
                                case "NUMBER": 
                                    returnState = true;
                                case "VALUES":
                                    returnState = true;
                                case "RESULTS":
                                    returnState = true;
                          }
                          break;   
                      case "SET":
                           switch(arguments[0].toUpperCase()){
                                case "NUMBER": 
                                    returnState = true;
                                case "VALUES":
                                    returnState = true;
                          }
                          break;
                      case "RUN":
                          returnState = true;
                          break;

                  }
                  break;       
          }
          
          return returnState;
    }
       
}
