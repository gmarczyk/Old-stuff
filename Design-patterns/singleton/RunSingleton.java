package pl.marczyk.executables;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.marczyk.patterns.singleton.Textures;

/**
 * Shows use of prepared design pattern : SINGLETON
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class RunSingleton {
    
    public static void main(String[] args){
 
        System.out.println("Before assigning singleton instance to texturesContainer");
        Textures texturesContainer = Textures.getInstance();
        System.out.println("After assigning singleton instance to texturesContainer");
        System.out.println(texturesContainer.getTextureByIndex(1));
        System.out.println(texturesContainer.getTextureByIndex(2));
        
        System.out.println("Creating another container for the same singleton. Singleton object"
                + " wont be created again, just an instance will be passed.");
        Textures secondContainer = Textures.getInstance();
        System.out.println("After getting instance to secondContainer");
    }
}
