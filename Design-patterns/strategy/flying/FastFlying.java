package pl.marczyk.patterns.strategy.flying;

import pl.marczyk.patterns.strategy.FlyingStrategy;

/**
 * Flies fast.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class FastFlying implements FlyingStrategy {

    @Override
    public void fly() {
        System.out.println("I am flying really fast :)");
    }
    
}
