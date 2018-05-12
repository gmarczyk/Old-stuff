import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Test;
import pl.polsl.java.marczyk.model.ProbabilisticTests;
import pl.polsl.java.marczyk.model.ValueOutOfRequiredIntervalException;

/**
 * Class provides tests for all public functions and procedures (not getters and
 * setters) of ProbabilisticTests class. Should provide testing invalid, valid
 * and boundary conditions.
 *
 * @author Marczyk Grzegorz
 * @version 1.2.0
 */
public class TestProbabilisticTests {

    ProbabilisticTests probabilisticTests;

    /**
     * Testing "cleanNum" method.
     */
    @Test
    public void testCleanNum() {

        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        try {
            probabilisticTests.setNumber("5");
        } catch (IllegalArgumentException ex) {
            fail("Initialization of test failed");
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Initialization of test failed");
        }

        probabilisticTests.cleanNum();
        assertEquals("cleanNum", null, probabilisticTests.getNumber());
    }

    /**
     * Testing "cleanTestedOnNum" method.
     */
    @Test
    public void testCleanTestedOnNum() {
        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        try {
            probabilisticTests.setNumber("5");
            probabilisticTests.setTestOnNumber("1");
        } catch (IllegalArgumentException ex) {
            fail("Initialization of test failed");
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Initialization of test failed");
        } catch (Exception ex) {
              fail("Initialization of test failed");
        }

        probabilisticTests.cleanTestedOnNum();
        assertEquals("cleanTestedOnNum", 0, probabilisticTests.getTestedOnNumber().size());

    }

    /**
     * Testing "cleanResults" method.
     */
    @Test
    public void testCleanResults() {
        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        try {
            probabilisticTests.setNumber("5");
            probabilisticTests.setTestOnNumber("1");
            probabilisticTests.calculateFermatTest();
        } catch (IllegalArgumentException ex) {
            fail("Initialization of test failed");
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Initialization of test failed");
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

        probabilisticTests.cleanResults();
        assertEquals("cleanTestedOnNum", 0, probabilisticTests.getResult().size());

    }

    /**
     * Testing "setNumber" method, which is a setter - but validates if the data
     * complies model requirements. Input number MUST be pure numerical chain,
     * and represent unsigned value. "minus" character is also non-digit, so it
     * makes the string not pure numerical. Also the value has to be higher than
     * 1.
     */
    @Test
    public void testSetNumber() {

        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        // Value is empty
        try {
            String testedString1 = "";
            probabilisticTests.setNumber(testedString1);
            fail("Should an exception occured while the tested value is empty");
        } catch (IllegalArgumentException ex) {
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("IllegalArgumentException is supposed to happen instead of ValueOutOfRequiredIntervalException ");
        }

        // Non-digit at END of string
        try {
            String testedString1 = "123a";
            probabilisticTests.setNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at END of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Non-digit at BEGINNING of string
        try {
            String testedString1 = "a123";
            probabilisticTests.setNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at BEGINNING of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Non-digit SOMEWHERE in the string
        try {
            String testedString1 = "12a3";
            probabilisticTests.setNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit SOMEWHERE in the string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Value is less than 2
        try {
            String testedString1 = "1";
            probabilisticTests.setNumber(testedString1);
            fail("Should an exception occured while the tested value is less than 2");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

    }

    /**
     * Testing "setTestOnNumber" method, which is a setter - but validates if
     * the data complies model requirements. Requirements are the same as in
     * "setNumber", in addition, value set by this method HAS TO BE IN SPECIFIC
     * INTERVAL. It must be higher than 0, and lower than tested number.
     */
    @Test
    public void testSetTestOnNumber() {

        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        // Value is empty
        try {
            // This value must be valid!
            String primalityTestedNumber = "15";
            probabilisticTests.setNumber(primalityTestedNumber);

            String testedString1 = "";
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while the tested value is empty");
        } catch (IllegalArgumentException ex) {
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("IllegalArgumentException is supposed to happen instead of ValueOutOfRequiredIntervalException ");
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

        // Non-digit at END of string
        try {
            String testedString1 = "123a";
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at END of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

        // Non-digit at BEGINNING of string
        try {
            String testedString1 = "a123";
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at BEGINNING of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

        // Non-digit SOMEWHERE in the string
        try {
            String testedString1 = "12a3";
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit SOMEWHERE in the string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
           fail("Initialization of test failed");
        }

        // Value must be MINIMUM 1
        try {
            String testedString1 = "0";
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while the tested value is less than 1");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
             fail("Initialization of test failed");
        }

        // Value must be less than models tested number
        try {
            String primalityTestedNumber = "15";
            String testedString1 = "15";
            probabilisticTests.setNumber(primalityTestedNumber);
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while the tested value is not lower than models tested number");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
             fail("Initialization of test failed");
        }

        // Value must be less than models tested number
        try {
            String primalityTestedNumber = "15";
            String testedString1 = "16";
            probabilisticTests.setNumber(primalityTestedNumber);
            probabilisticTests.setTestOnNumber(testedString1);
            fail("Should an exception occured while the tested value is not lower than models tested number");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
             fail("Initialization of test failed");
        }

    }

    /**
     * Testing "calcFermatTest" with invalid values.
     */
    @Test
    public void testCalcFermatTestInvalid() {

        ProbabilisticTests probabilisticTests = new ProbabilisticTests();

        // "a" in fermat test formula is higher than "p"
        try {

            probabilisticTests.setNumber("15");
            probabilisticTests.setTestOnNumber("17");
            probabilisticTests.calculateFermatTest();
            fail("Should an error occured when a is higher p");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
             fail("Initialization of test failed");
        }

        // "a" in fermat test formula is not pure numerical string
        try {

            probabilisticTests.setNumber("15");
            probabilisticTests.setTestOnNumber("gf");
            probabilisticTests.calculateFermatTest();
            fail("Should an error occured when testOnNumber is not pure numerical");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

        // "p" in fermat test formula is not pure numerical string
        try {

            probabilisticTests.setNumber("1g");
            probabilisticTests.setTestOnNumber("3");
            probabilisticTests.calculateFermatTest();
            fail("Should an error occured when testedNumber is not pure numerical");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (Exception ex) {
            fail("Initialization of test failed");
        }

    }

    /**
     * Testing "calcFermatTest" with valid values.
     */
    @Test
    public void testCalcFermatTestValid() {

        ProbabilisticTests probabilistic = new ProbabilisticTests();

        // Simple fermat test 2^4 mod 5 (a = 2 p = 5);
        try {

            probabilistic.setNumber("5");
            probabilistic.setTestOnNumber("2");
            probabilistic.calculateFermatTest();
            assertEquals("Simple fermat test", true, probabilistic.getResult().get(0));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Should never happen");
        } catch (Exception ex) {
           fail("Initialization of test failed");
        }

    }

    /**
     * Testing "calcFermatTest" on boundary conditions.
     */
    @Test
    public void testCalcFermatTestBoundary() {

        ProbabilisticTests probabilistic = new ProbabilisticTests();

        // a = 0
        try {
            probabilistic.setNumber("5");
            probabilistic.setTestOnNumber("0");
            probabilistic.calculateFermatTest();
            fail("Should throw when a is less than 1");
        } catch (ValueOutOfRequiredIntervalException ex) {

        } catch (Exception ex) {
           fail("Initialization of test failed");
        }

        // a = 1
        try {
            probabilistic.setNumber("5");
            probabilistic.setTestOnNumber("1");
            probabilistic.calculateFermatTest();

        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Shouldnt throw when a is  1");
        } catch (Exception ex) {
              fail("Initialization of test failed");
        }

        // a = p-1
        try {
            probabilistic.setNumber("5");
            probabilistic.setTestOnNumber("4");
            probabilistic.calculateFermatTest();

        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("Shouldnt throw when a is less by one than p");
        } catch (Exception ex) {
             fail("Initialization of test failed");
        }

        // a = 0
        try {
            probabilistic.setNumber("5");
            probabilistic.setTestOnNumber("5");
            probabilistic.calculateFermatTest();
            fail("Should throw when a is not less than p");
        } catch (ValueOutOfRequiredIntervalException ex) {

        } catch (Exception ex) {
             fail("Initialization of test failed");
        }
    }

}
