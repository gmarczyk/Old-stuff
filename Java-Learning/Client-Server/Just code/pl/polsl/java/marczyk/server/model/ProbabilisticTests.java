package pl.polsl.java.marczyk.server.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that performs probabilistic tests of primality on a specific level of
 * probability. Tests are performed on numeric values represented by String
 * type. Important note: probabilistic primality tests often require a random
 * value smaller than the tested number, and higher than 0. Important symbols in
 * different formulas: "a" - is the required random number, "p" - is the value
 * that is being tested.
 *
 * @author Marczyk Grzegorz
 * @version 2.0.1
 */
public class ProbabilisticTests {

    /**
     * Number of tests created in one session of application.
     */
    public static int countOfTests = 0;

    /**
     * The number that is being tested for primality. It is the "p" number in
     * following formula: a^(p-1) mod p.
     */
    private String testedNumber;

    /**
     * Test is made for specific number. It is the "a" number in following
     * formula: a^(p-1) mod p. "a" has to be within numerical interval which
     * stands for: a is higher or equal 1, but is lower than p. This is list of
     * numbers on which will the tested be performed.
     */
    private ArrayList<String> testedOnNumber = new ArrayList<>();

    /**
     * List of results of the test.
     */
    private ArrayList<Boolean> results = new ArrayList<>();

    /**
     * Arithmetical unit that is used to do all the calculations.
     */
    private StringArithmetic calcUnit = new StringArithmetic();

    /**
     * Non-parametric constructor.
     */
    public ProbabilisticTests() {

    }

    /**
     * Procedure sets value of the number that has to be tested for primality.
     *
     * @param numToSet String which has to be set as the tested number. Always
     * stands for "p" in probabilistic primality tests.
     * @throws ValueOutOfRequiredIntervalException If input string value
     * contains non-digit characters or if the value of input number does not
     * meet the requirements of the test.
     */
    public void setNumber(String numToSet) throws ValueOutOfRequiredIntervalException {

        // Check if not empty.
        if ("".equals(numToSet) || numToSet == null) {
            throw new IllegalArgumentException("Empty value given");
        }

        // Check for non-digit characters.
        for (char ch : numToSet.toCharArray()) {
            if (!(Character.isDigit(ch))) {
                throw new ValueOutOfRequiredIntervalException("Input number is not pure numerical chain of characters."
                        + " It mustn't contain not-digit characters");
            }
        }

        // Check if value is minimum 2.
        if (this.calcUnit.compareValues(numToSet, "2") < 0) {
            throw new ValueOutOfRequiredIntervalException("Number has to be higher than 1!");
        }
        this.testedNumber = numToSet;
    }

    /**
     * Just cleans tested number.
     */
    public void cleanNum() {
        this.testedNumber = null;
    }

    /**
     * Just cleans tested on numbers.
     */
    public void cleanTestedOnNum() {
        this.testedOnNumber.clear();
    }

    /**
     * Just cleans results.
     */
    public void cleanResults() {
        this.results.clear();
    }

    /**
     * Procedure sets value of the number that primality test requires to be
     * performed. It is the "a" in following formula: (a^(p-1)) mod p.
     *
     * @param numToSet String which has to be set as reference number. This
     * number always stands for "a" in probabilistic primality tests.
     * @throws ValueOutOfRequiredIntervalException If string value exceeds the
     * value of number which is going to be tested, or when its less than 1.
     * @throws Exception when testedNumber of model is not initialized
     */
    public void setTestOnNumber(String numToSet) throws ValueOutOfRequiredIntervalException, Exception {
        
        // Check if not empty.
        if ("".equals(this.testedNumber) || this.testedNumber == null) {
            throw new Exception("Model tested value not initialized!");
        }
        
        // Check if not empty.
        if ("".equals(numToSet) || numToSet == null) {
            throw new IllegalArgumentException("Empty value given");
        }

        // Check if the value is less than "p"
        if (this.calcUnit.compareValues(numToSet, this.testedNumber) >= 0) {
            throw new ValueOutOfRequiredIntervalException("Number has to be lower than p!");
        }

        //Check if the value is minimum 1.
        if (this.calcUnit.compareValues(numToSet, "1") < 0) {
            throw new ValueOutOfRequiredIntervalException("Number has to be higher than 0!");
        }

        // Check for non-digit characters.
        for (char ch : numToSet.toCharArray()) {
            if (!(Character.isDigit(ch))) {
                throw new ValueOutOfRequiredIntervalException("Input number is not pure numerical chain of characters."
                        + " It mustn't contain not-digit characters");
            }

        }
        this.testedOnNumber.add(numToSet);
    }

    /**
     * Getter of the results of probabilistic primality tests.
     *
     * @return returns actually stored result of tests.
     */
    public ArrayList<Boolean> getResult() {
        return this.results;
    }

    /**
     * Getter of the number that is being checked for primality.
     *
     * @return returns actually stored number which is purposed to be tested for
     * primality.
     */
    public String getNumber() {
        return this.testedNumber;
    }

    /**
     * Getter of the number according to which primality test is performed.
     *
     * @return returns actually stored reference value required to perform the
     * test.
     */
    public ArrayList<String> getTestedOnNumber() {
        return this.testedOnNumber;
    }

    /**
     * Procedure performs Fermat's test for currently set reference value.
     * Formula of the Fermat's test: (a^(p-1)) mod p.
     *
     * @throws ValueOutOfRequiredIntervalException takes the throws from
     * calcUnit arithmetical functions. Will happen if values passed to these
     * functions as input parameters are not pure numerical Strings.
     * @throws Exception when there is a problem with tested number or reference
     * values on which the test has to be performed.
     */
    public void calculateFermatTest() throws ValueOutOfRequiredIntervalException, Exception {

        if((this.testedNumber == null) || (this.testedNumber.equals(""))) throw new Exception("No tested value!");
        if((this.testedOnNumber.isEmpty() == true)) throw new Exception("No tested on values!");
        
        this.results.clear();

        int i = 0;
        for (String testOnIterator : this.testedOnNumber) {

            i++;
            // Result of alghoritm
            String resString;

            /* Fermat test can't be performed for value 1, because "a" in the formula has to be less than "p".
            Therefore, if "p" is 1 -> "a" maximum value is 0, and "a" MUST be higher or equal 1.
            tl;dr - tested number must be >1 or test can't be performd */
            if (this.calcUnit.compareValues(this.testedNumber, "1") > 0) {

                // p-1 from formula.
                String pMinusOne = this.calcUnit.writtenSubtracting(testedNumber, "1");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date date = new Date();
                System.out.print(i + ". START: " + dateFormat.format(date));

                resString = this.calcUnit.fastModularExponentiation(testOnIterator, pMinusOne, this.testedNumber);
                date = new Date();
                System.out.println(" STOP: " + dateFormat.format(date));

                Boolean tmpResult;
                if ("1".equals(resString)) {
                    tmpResult = true;
                    this.results.add(tmpResult);
                } else {
                    tmpResult = false;
                    this.results.add(tmpResult);
                }
            } else {
                throw new ValueOutOfRequiredIntervalException("tested number value not in interval.");
            }
        }
    }

}
