package pl.marczyk.patterns.observer.subjects;

import java.util.ArrayList;
import pl.marczyk.patterns.observer.Observable;
import pl.marczyk.patterns.observer.Observer;

/**
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public class DiscountMagazine implements Observable {
    
    DiscountUnit[] discounts = {
        new DiscountUnit("Biedronka"),
        new DiscountUnit("Lidl"),
    };
    
    protected ArrayList<Observer> observers;
    
    public DiscountMagazine(){
        this.observers = new ArrayList<Observer>();
        
        // Initializing just for testing puproses (these are just examples)
        this.discounts[0].setProductName("Bread");
        this.discounts[0].setPriceBefore(5.0f);
        this.discounts[0].setDiscountPercentage(20.0f);
        this.discounts[0].setPriceNow(this.calcDiscountFromPercent(5.0f, 20.0f));
        
        this.discounts[1].setProductName("Milk");
        this.discounts[1].setPriceBefore(2.0f);
        this.discounts[1].setDiscountPercentage(5.0f);
        this.discounts[1].setPriceNow(this.calcDiscountFromPercent(2.0f, 5.0f));
    }
        
    @Override
    public void registerObserver(Observer observer) {
       this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : this.observers){
            observer.update(discounts);
        }
    }
    
    public void updateDiscountUnit(int discountIndex, String productName, float percentageOff, float price) {
        if(discountIndex < discounts.length && discountIndex >= 0) {
            this.discounts[discountIndex].setProductName(productName);
            this.discounts[discountIndex].setPriceBefore(price);
            this.discounts[discountIndex].setDiscountPercentage(percentageOff);
            this.discounts[discountIndex].setPriceNow(this.calcDiscountFromPercent(price, percentageOff));
            this.notifyObservers();
        }
    }
    
    private float calcDiscountFromPercent(float price, float percent){
       
        return (price-(price*(percent/100)));
    }

}
