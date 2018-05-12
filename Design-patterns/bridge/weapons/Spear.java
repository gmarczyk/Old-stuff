package pl.marczyk.patterns.bridge.weapons;

import pl.marczyk.patterns.bridge.Weapon;
import pl.marczyk.patterns.bridge.WeaponSpeedMode;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class Spear extends Weapon {
    
    public Spear(String weaponName, WeaponSpeedMode weaponSpeedMode){
        super(weaponName,weaponSpeedMode);
    }
    
    @Override
    public void doBasicAttack(){
       this.weaponSpeedMode.spearBasicAttack();
    }
    
    @Override
    public void doParry() {
       this.weaponSpeedMode.spearParry();
    }
    
}
