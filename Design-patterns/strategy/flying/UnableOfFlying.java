package pl.marczyk.patterns.strategy.flying;

import pl.marczyk.patterns.strategy.FlyingStrategy;

/**
 * Can't fly for some reason.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class UnableOfFlying implements FlyingStrategy {

    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
    
}
