package pl.marczyk.executables;

import pl.marczyk.patterns.bridge.Weapon;
import pl.marczyk.patterns.bridge.speedmodes.*;
import pl.marczyk.patterns.bridge.weapons.*;

/**
 * Shows use of prepared design pattern : BRIDGE
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class RunBridge {
    
    public static void main(String[] args){
        
        Weapon[] differentWeapons = { 
            new Sword("Heavy Sword",          new SlowAttackMode()),                           
            new Sword("Simple Sword",         new ModerateAttackMode()),                         
            new Sword("Light Sword",          new FastAttackMode()),   
            
            new Spear("Big Heavy Iron Spear", new SlowAttackMode()),                          
            new Spear("Simple Iron Spear",    new ModerateAttackMode()),                         
            new Spear("Light Wooden Spear",   new FastAttackMode()) 
        };
        
        for(Weapon weapon : differentWeapons){
            System.out.println("A weapon named: " + weapon.getWeaponName() + " performs such an attack: ");
            weapon.doBasicAttack();
            System.out.println("Then it parries opponents attack in this way: ");
            weapon.doParry();
            System.out.println();
        }  

    }
}
