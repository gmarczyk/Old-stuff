package model;

/**
 * Exception should be thrown when a value exceeds numerical interval.
 *
 * @author Marczyk Grzegorz
 * @version 1.0.1
 */
public class ValueOutOfRequiredIntervalException extends Exception {

    /**
     * Constructor without parametric
     */
    public ValueOutOfRequiredIntervalException() {
    }

    /**
     * Constructor
     *
     * @param message display message
     */
    public ValueOutOfRequiredIntervalException(String message) {
        super(message);
    }
}
