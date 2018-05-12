package pl.polsl.java.marczyk.model;


import java.util.ArrayList;

/**
 * Contains all data about a single probabilistic primality test.
 *
 * @author Marczyk Grzegorz
 * @version 1.0
 */
public class SingleProbabilisticTest {

    /** Number which was tested for primality */
    private String            testedNum;
    
    /** Reference values according to which the test was performed  */
    private ArrayList<String> testedOnValues;
    
    /** Results for each reference value */
    private ArrayList<String> results;
    
    
    /**
     * Creates object containing data of single probabilistic primality test.
     * 
     * @param testedNum       number which was tested for primality
     * @param testedOnValues  reference values according to which the test was performed 
     * @param results         results for each reference value 
     */
    public SingleProbabilisticTest(String testedNum, ArrayList<String> testedOnValues, ArrayList<String> results) {

        this.testedNum      = testedNum;
        this.testedOnValues = new ArrayList<>(testedOnValues);
        this.results        = new ArrayList<>(results);
    }

    /* ======================== Getters and setters ======================== */ 
    public String             getTestedNum()      { return this.testedNum;      }
    public ArrayList<String>  getTestedOnValues() { return this.testedOnValues; }
    public ArrayList<String>  getResults()        { return this.results;        }
}
