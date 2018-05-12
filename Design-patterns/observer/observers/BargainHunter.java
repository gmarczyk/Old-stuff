package pl.marczyk.patterns.observer.observers;

import pl.marczyk.patterns.observer.Observable;
import pl.marczyk.patterns.observer.Observer;
import pl.marczyk.patterns.observer.subjects.DiscountMagazine;
import pl.marczyk.patterns.observer.subjects.DiscountUnit;

/**
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public class BargainHunter implements Observer {
   
    DiscountUnit[] discounts = {
        new DiscountUnit("Biedronka"),
        new DiscountUnit("Lidl"),
    };
    
    public BargainHunter(DiscountMagazine observableMagazine){
        observableMagazine.registerObserver(this);
    }
    
    @Override
    public void update(DiscountUnit[] discountUnits) {
        discounts = discountUnits;    
        System.out.println("Update got done, the current list is: ");
        this.showDiscounts();
    }

    public void showDiscounts(){
        for(DiscountUnit discountUnit : discounts){
            System.out.println("Source: "        + discountUnit.getDiscountSource());
            System.out.println("Product name:: " + discountUnit.getProductName());
            System.out.println("% off: "         + discountUnit.getDiscountPercentage());
            System.out.println("Price before: "  + discountUnit.getPriceBefore());
            System.out.println("Price now: "     + discountUnit.getPriceNow());    
            System.out.println();
        }   
    }
}
