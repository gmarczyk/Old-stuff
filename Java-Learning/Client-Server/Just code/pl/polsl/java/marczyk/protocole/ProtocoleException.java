/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.protocole;

/**
 * Appears when transmission fails for a reason that protocole can't work propely
 * @author Marczyk Grzegorz
 * @version 1.0.0
 */


/**
 * Exception should be thrown when a value exceeds numerical interval.
 *
 * @author Marczyk Grzegorz
 * @version 1.0.1
 */
public class ProtocoleException extends Exception {

    /**
     * Constructor without parametric
     */
    public ProtocoleException() {
    }

    /**
     * Constructor
     *
     * @param message display message
     */
    public ProtocoleException(String message) {
        super(message);
    }
}
