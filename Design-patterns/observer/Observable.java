package pl.marczyk.patterns.observer;

/**
 * Provides standard methods for OBSERVED unit in OBSERVER software design pattern.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public interface Observable {

    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    public void notifyObservers();

}
