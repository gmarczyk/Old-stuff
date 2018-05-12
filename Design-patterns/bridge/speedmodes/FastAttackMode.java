package pl.marczyk.patterns.bridge.speedmodes;

import pl.marczyk.patterns.bridge.WeaponSpeedMode;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class FastAttackMode implements WeaponSpeedMode {

    /* ========= ========= Basic attacks ========= ========= */
    @Override
    public void spearBasicAttack() {
       System.out.println("Performing really fast BASIC ATTACK with the SPEAR, just like Oberyn Martell");
    }

    @Override
    public void swordBasicAttack() {
       System.out.println("Performing really fast BASIC ATTACK with the SWORD, just like Syrio Forel");
    }

    
    /* ========= ========= Parrying ========= ========= */
    @Override
    public void spearParry() {
       System.out.println("PARRYING an attack with light and handy SPEAR, may hurt weapons endurance"); 
    }

    @Override
    public void swordParry() {
       System.out.println("PARRYING an attack with light and handy SWORD, may hurt users stamina when "
               + "a powerful attack is parried"); 
    }
    
}
