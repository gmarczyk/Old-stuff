package pl.marczyk.patterns.singleton;

import java.util.ArrayList;

/**
 * Simulation of object that gives access to textures in order to perform graphic transformations etc.
 * This structure loads all textures on startup and gives access to those elements.
 * Naming it an 'structure' because it somehow reminds an Active Record object, with no saving methods.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public class Textures {

    /** List of textures, which for this simulation are represented by String */
    public ArrayList<String> texturesList;
    
    
    /**
    * Thread safe single check initialization. Seems like quite fine option.
    */
    private static Textures selfInstance;

    public static synchronized Textures getInstance(){
        if(selfInstance == null)
             selfInstance = new Textures();
         
        return selfInstance;
    } 
    
    
    /** 
     * Eager initialization, should use if initialization doesn't take much time. Uncomment if wanna try it out.
     */
    
    /*
        private static final Textures selfInstance = new Textures();
    
        public static Textures getInstance(){
            return selfInstance;
        } 
    */
 
    private Textures(){
        this.texturesList = new ArrayList<String>();
        // Here loading the textures from external source or something, but just for simulation:
        texturesList.add("First texture");
        texturesList.add("Second texture");
        System.out.println("SINGLETON [Textures] was initialized");
    }
  
    public String getTextureByIndex(int index){
        if(isIndexCorrect(index))
            return texturesList.get(index);
        else
            return "Neutral texture filled with question marks";
    }
    
    private boolean isIndexCorrect(int index){
        return ( (index < this.texturesList.size()) && (index >= 0) );
    }
}
