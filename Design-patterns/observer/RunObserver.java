package pl.marczyk.executables;

import pl.marczyk.patterns.observer.Observer;
import pl.marczyk.patterns.observer.observers.BargainHunter;
import pl.marczyk.patterns.observer.subjects.DiscountMagazine;

/**
 * Shows use of prepared design pattern : OBSERVER
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class RunObserver {
    
    public static void main(String[] args){
        
        /* !!! Implementation of observable is not protected against empty discount */
        
        DiscountMagazine observable = new DiscountMagazine();
        Observer observer = new BargainHunter(observable);
        
        observable.updateDiscountUnit(0, "Potatoes", 20.0f, 100.0f);
        observable.updateDiscountUnit(1, "Cola", 10.0f, 5.0f);
    }
}
