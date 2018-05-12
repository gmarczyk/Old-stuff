package pl.marczyk.executables;

import pl.marczyk.patterns.strategy.Bird;
import pl.marczyk.patterns.strategy.flying.*;

/**
 * Shows use of prepared design pattern : STRATEGY
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class RunStrategy {
    
    public static void main(String[] args){
        
        Bird[] differentBirds = { new Bird("Babird",   20,  1,  new UnableOfFlying()),
                                  new Bird("Kowalsky", 250, 15, new FastFlying()),
                                  new Bird("Fatty",    400, 18, new SlowFlying()) };
        
        for(Bird bird : differentBirds){
            bird.fly();
        }  
        
        System.out.println("Kowalsky was flying for soooo much time, he got tired and from now on he's flying slowly");
        differentBirds[1].setFlyingStrategy(new SlowFlying());
        differentBirds[1].fly();
    }
}
