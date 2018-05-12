package pl.marczyk.patterns.bridge;

/**
 * Contains methods that are required to perform specific attacks for weapons with different
 * speed category and levels of convenience (how easy to use, like heavy sword vs light one).
 *
 * @author  Marczyk Grzegorz
 * @version 1.1.0
 */
public interface WeaponSpeedMode {
        
    public void spearBasicAttack();
    public void swordBasicAttack();
    
    public void spearParry();
    public void swordParry();

}
