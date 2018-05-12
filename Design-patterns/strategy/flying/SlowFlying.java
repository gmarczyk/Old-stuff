package pl.marczyk.patterns.strategy.flying;

import pl.marczyk.patterns.strategy.FlyingStrategy;

/**
 * Flies slowly.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class SlowFlying implements FlyingStrategy {

    @Override
    public void fly() {
        System.out.println("I am flying really slowly :(");
    }
    
}
