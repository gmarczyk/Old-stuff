/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.protocole;

/**
 * Interface for protocole provides methods for validation
 * @author Marczyk Grzegorz
 * @version 1.0.0
 */
public interface InCommandHandler {
    
    public boolean isOptionValid(String theOption,ProtocoleUserType userType);
    public boolean areArgumentsValid(String theOption, String optionArguments,ProtocoleUserType userType);
    
}
