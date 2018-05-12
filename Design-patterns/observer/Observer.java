package pl.marczyk.patterns.observer;

import pl.marczyk.patterns.observer.subjects.DiscountUnit;

/**
 * Stands for an Observer in OBSERVER design pattern.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public interface Observer {

    public void update(DiscountUnit[] discountUnits);

}
