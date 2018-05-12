package pl.marczyk.patterns.strategy;

/**
 * Represents an animal which is a bird that is capable of flying in different ways.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class Bird {
   
    String  name;
    int     weightGrams;
    int     ageMonths;
    
    /** Interface specifying the way a bird flies, use of design pattern */
    FlyingStrategy flyingStrategy;

    public Bird(String name, int weightGrams, int ageMonths, FlyingStrategy flyingStrategy){
        this.name           = name;
        this.weightGrams    = weightGrams;
        this.ageMonths      = ageMonths;
        this.flyingStrategy = flyingStrategy;
    }

    public void setFlyingStrategy(FlyingStrategy strategy) {
        this.flyingStrategy = strategy; 
    }

    /** 
     * Tells the bird to fly in specific way, according to current state of being, which for example can be 
     * unable for newborn bird, but an adult can fly fast, slow, or be unable because of broken wing.
     */
    public void fly() {
        System.out.println("My name is: " + name + " and I'm " + ageMonths + " months old. I weigh " + weightGrams + " grams");
        flyingStrategy.fly();
    }
    
}
