package pl.marczyk.patterns.bridge.speedmodes;

import pl.marczyk.patterns.bridge.WeaponSpeedMode;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class SlowAttackMode implements WeaponSpeedMode {
    
    /* ========= ========= Basic attacks ========= ========= */
    @Override
    public void spearBasicAttack() {
       System.out.println("Performing a slow BASIC ATTACK with the SPEAR, a huuuge spear");
    }

    @Override
    public void swordBasicAttack() {
       System.out.println("Performing a slow BASIC ATTACK with the SWORD, I'ma Gregor Clegane");
    }

    
    /* ========= ========= Parrying ========= ========= */
    @Override
    public void spearParry() {
       System.out.println("PARRYING an attack with big slow spear, needs high evade ratio but "
               + "doesnt hurt weapons endurance that much"); 
    }

    @Override
    public void swordParry() {
       System.out.println("PARRYING an attack with big and heavy SWORD, takes more endurance, "
               + "but can parry powerful attacks without losing drastically amounts of it"); 
    }
    
}
