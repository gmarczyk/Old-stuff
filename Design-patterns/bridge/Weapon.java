package pl.marczyk.patterns.bridge;

/**
 * Abstract that generally represents any kind of weapon.
 * 
 * @author  Marczyk Grzegorz    
 * @version 1.0.0
 */
public abstract class Weapon {
    
    private   String          weaponName;
    protected WeaponSpeedMode weaponSpeedMode;
    
    public Weapon(String weaponName, WeaponSpeedMode weaponSpeedMode){
        this.weaponName      = weaponName;
        this.weaponSpeedMode = weaponSpeedMode;
    }
    
    public String getWeaponName(){
        return this.weaponName;
    }
    
    public abstract void doBasicAttack();
    public abstract void doParry();
    
}
