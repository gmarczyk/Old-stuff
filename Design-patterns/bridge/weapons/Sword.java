package pl.marczyk.patterns.bridge.weapons;

import pl.marczyk.patterns.bridge.Weapon;
import pl.marczyk.patterns.bridge.WeaponSpeedMode;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class Sword extends Weapon {
    
    public Sword(String weaponName, WeaponSpeedMode weaponSpeedMode){
        super(weaponName,weaponSpeedMode);
    }
    
    @Override
    public void doBasicAttack(){
        this.weaponSpeedMode.swordBasicAttack();
    }
    
    @Override
    public void doParry(){
        this.weaponSpeedMode.swordParry();
    }
    
}
