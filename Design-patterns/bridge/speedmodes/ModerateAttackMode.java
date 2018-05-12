package pl.marczyk.patterns.bridge.speedmodes;

import pl.marczyk.patterns.bridge.WeaponSpeedMode;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class ModerateAttackMode implements WeaponSpeedMode {
    
    /* ========= ========= Basic attacks ========= ========= */
    @Override
    public void spearBasicAttack() {
       System.out.println("Performing a simple BASIC ATTACK with a simple SPEAR of a Dorne soldier");
    }

    @Override
    public void swordBasicAttack() {
       System.out.println("Performing a simple BASIC ATTACK with casual SWORD of a Kings Landing soldier");
    }

    
    /* ========= ========= Parrying ========= ========= */
    @Override
    public void spearParry() {
       System.out.println("PARRYING an attack with SPEAR, nothing special"); 
    }

    @Override
    public void swordParry() {
       System.out.println("PARRYING an attack with SWORD, nothing special"); 
    }
    
}
