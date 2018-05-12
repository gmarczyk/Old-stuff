/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj2.test;

import org.junit.*;
import static org.junit.Assert.*;
import pl.polsl.java.proj2.model.StringArithmetic;
import pl.polsl.java.proj2.model.ValueOutOfRequiredIntervalException;

/**
 * Class provides tests for all public functions and procedures (not getters and
 * setters) of StringArithmetic class. Should provide testing invalid, valid and
 * boundary conditions.
 *
 * @author Marczyk Grzegorz
 * @version 1.0.2
 */
public class TestStringArithmetic {

    StringArithmetic stringArithmetic;
    String testedString1;
    String testedString2;

    @Before
    public void resetStrings() {
        testedString1 = null;
        testedString2 = null;
    }

    /**
     * Testing function "compare" with invalid data. IMPORTANT: Invalid data
     * doesn't always throw! There can be invalid values that are handled, such
     * as empty string.
     */
    @Test
    public void testCompareInvalid() {

        stringArithmetic = new StringArithmetic();

        // Non-digit somewhere in strings
        try {
            testedString1 = "12 3a";
            testedString2 = "12g3";
            stringArithmetic.compareValues(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit somewhere in strings");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Empty strings to compare
        try {
            testedString1 = "";
            testedString2 = "";
            assertEquals("Empty strings to compare", 0, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty strings to compare] should never throw exception");
        }

        // Empty string and values
        try {
            testedString1 = "";
            testedString2 = "5";
            assertEquals("Empty string and 5", -1, stringArithmetic.compareValues(testedString1, testedString2));
            testedString1 = "";
            testedString2 = "0";
            assertEquals("Empty string and 0", 0, stringArithmetic.compareValues(testedString1, testedString2));
            testedString1 = "5";
            testedString2 = "";
            assertEquals("5 and empty string", 1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty string and values] should never throw exception");
        }
    }

    /**
     * Testing function "compare" with valid data. Should never throw
     * exceptions.
     */
    @Test
    public void testCompareValid() {

        stringArithmetic = new StringArithmetic();

        // Equal values
        try {
            testedString1 = "4";
            testedString2 = "4";
            assertEquals("Equal values",
                    0, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Equal values] should never throw exception");
        }

        // Lower value compared to higher
        try {
            testedString1 = "4";
            testedString2 = "53";
            assertEquals("Lower value compared to higher",
                    -1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Lower value compared to higher] should never throw exception");
        }

        // Higher value compared to lower
        try {
            testedString1 = "99";
            testedString2 = "15";
            assertEquals("Higher value compared to lower",
                    1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Higher value compared to lower] should never throw exception");
        }

        // Insignificant zeros and equal values
        try {
            testedString1 = "000099";
            testedString2 = "0000000099";
            assertEquals("Insignificant zeros and equal values",
                    0, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Insignificant zeros and equal values] should never throw exception");
        }
    }

    /**
     * Testing function "compare" at boundary conditions. This test may contain
     * invalid data.
     */
    @Test
    public void testCompareBoundary() {

        stringArithmetic = new StringArithmetic();

        // Non-digit at END of string
        try {
            testedString1 = "123a";
            testedString2 = "123";
            stringArithmetic.compareValues(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at END of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Non-digit at BEGINNING of string
        try {
            testedString1 = "123";
            testedString2 = "a123";
            stringArithmetic.compareValues(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at BEGINNING of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Strings are equal in size and in numbers, but the LAST digit of SECOND value is higher.
        try {
            testedString1 = "11110";
            testedString2 = "11119";
            assertEquals("Strings are equal in size and mostly in numbers, but the LAST digit of SECOND value is higher",
                    -1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Strings are equal in size and in numbers, but the FIRST digit of SECOND value is higher]"
                    + " should never throw exception");
        }

        // Strings are equal in size and mostly in numbers, but the LAST digit of FIRST value is higher.
        try {
            testedString1 = "11119";
            testedString2 = "11110";
            assertEquals("Values are equal && Equal lengths + equal value",
                    1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Strings are equal in size and in numbers, but the FIRST digit of FIRST value is higher] "
                    + " should never throw exception");
        }

        // Strings are equal in size and in numbers, but the FIRST digit of SECOND value is higher.
        try {
            testedString1 = "11111";
            testedString2 = "91111";
            assertEquals("Strings are equal in size and in numbers, but the FIRST digit of SECOND value is higher",
                    -1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Strings are equal in size and in numbers, but the FIRST digit of SECOND value is higher]"
                    + " should never throw exception");
        }

        // Strings are equal in size and in numbers, but the FIRST digit of FIRST value is higher.
        try {
            testedString1 = "91111";
            testedString2 = "11111";
            assertEquals("Values are equal && Equal lengths + equal value",
                    1, stringArithmetic.compareValues(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Strings are equal in size and in numbers, but the LAST digit of FIRST value is higher] "
                    + " should never throw exception");
        }
    }

    /**
     * Testing function "deleteInsignificantZeros" with invalid data. IMPORTANT:
     * invalid data doesn't always throw! Some things may be taken as invalid
     * but handled by the function somehow.
     */
    @Test
    public void testDeleteInsignificantZerosInvalid() {

        stringArithmetic = new StringArithmetic();

        // String is empty
        testedString1 = "";
        assertEquals("String is empty", "", stringArithmetic.deleteInsignificantZeros(testedString1));

        // Some characters in String are not-digits (WITH insignificant zeros and WITHOUT)
        testedString1 = "00-h123";
        assertEquals("Some characters in String are not-digits [WITH]", "-h123", stringArithmetic.deleteInsignificantZeros(testedString1));
        testedString1 = "ab-123";
        assertEquals("Some characters in String are not-digits [WITHOUT]", "ab-123", stringArithmetic.deleteInsignificantZeros(testedString1));
    }

    /**
     * Testing function "deleteInsignificantZeros" with valid data. Should never
     * throw exceptions. This function supports not-numerical strings.
     */
    @Test
    public void testDeleteInsignificantZerosValid() {

        stringArithmetic = new StringArithmetic();

        // String is contains some insignificant zeros
        testedString1 = "00123";
        assertEquals("SString is contains some insignificant zeros", "123", stringArithmetic.deleteInsignificantZeros(testedString1));

        // String contains no insignificant zeros
        testedString1 = "123";
        assertEquals("String contains no insignificant zeros", "123", stringArithmetic.deleteInsignificantZeros(testedString1));

    }

    /**
     * Testing function "deleteInsignificantZeros" at boundary conditions. May
     * contain invalid data.
     */
    @Test
    public void testDeleteInsignificantZerosBoundary() {

        stringArithmetic = new StringArithmetic();

        // String is contains just one value = "0"
        testedString1 = "0";
        assertEquals("String is contains just one value = ZERO", "0", stringArithmetic.deleteInsignificantZeros(testedString1));

        // String is contains few zeros
        testedString1 = "000";
        assertEquals("String is contains few zeros", "0", stringArithmetic.deleteInsignificantZeros(testedString1));
    }

    /**
     * Testing function "writtenAdding" with invalid data. The only one throwing
     * case is when input Strings aren't pure numerical chains. Empty string is
     * also taken as invalid, but causes no exception.
     */
    @Test
    public void testWrittenAddingInvalid() {

        stringArithmetic = new StringArithmetic();

        // Non-digit somewhere in strings
        try {
            testedString1 = "12 3a";
            testedString2 = "12g3";
            stringArithmetic.writtenAdding(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit somewhere in strings");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Empty strings to add
        try {
            testedString1 = "";
            testedString2 = "";
            assertEquals("Empty strings to add", "0", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty strings to add] should never throw exception");
        }

        // Empty string and values
        try {
            testedString1 = "";
            testedString2 = "5";
            assertEquals("Empty string and 5", "5", stringArithmetic.writtenAdding(testedString1, testedString2));
            testedString1 = "";
            testedString2 = "0";
            assertEquals("Empty string and 0", "0", stringArithmetic.writtenAdding(testedString1, testedString2));
            testedString1 = "6";
            testedString2 = "";
            assertEquals("6 and empty string", "6", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty string and values] should never throw exception");
        }
    }

    /**
     * Testing function "writtenAdding" with valid data. Should never throw
     * exceptions.
     */
    @Test
    public void testWrittenAddingValid() {

        stringArithmetic = new StringArithmetic();

        // Just random adding
        try {
            testedString1 = "503928";
            testedString2 = "303155";
            assertEquals("Just random adding", "807083", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Just random adding] should never throw exception");
        }

        // Adding with insignificant zeros
        try {
            testedString1 = "00503928";
            testedString2 = "0000000303155";
            assertEquals("Adding with insignificant zeros", "807083", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Adding with insignificant zeros] should never throw exception");
        }
    }

    /**
     * Testing function "writtenAdding" at boundary conditions. May contain
     * invalid data.
     */
    @Test
    public void testWrittenAddingBoundary() {

        stringArithmetic = new StringArithmetic();

        // Non-digit at END of string
        try {
            testedString1 = "123a";
            testedString2 = "123";
            stringArithmetic.writtenAdding(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at END of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Non-digit at BEGINNING of string
        try {
            testedString1 = "123";
            testedString2 = "a123";
            stringArithmetic.writtenAdding(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at BEGINNING of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Transfer to next position
        try {
            testedString1 = "1999";
            testedString2 = "1";
            assertEquals("Transfer to next position", "2000", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Transfer to next position] should never throw exception");
        }

        // Transfer to next position, when next position doesn't exist
        try {
            testedString1 = "999";
            testedString2 = "1";
            assertEquals("Transfer to next position, when next position doesn't exist", "1000", stringArithmetic.writtenAdding(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Transfer to next position, when next position doesn't exist] should never throw exception");
        }
    }

    /**
     * Testing function "writtenSubtracting" with invalid data. The only one
     * throwing case is when input Strings aren't pure numerical chains. Lower -
     * Higher value case is also invalid, as the function supports unsigned
     * values only. Empty strings are also taken as invalid, but cause no
     * exceptions.
     */
    @Test
    public void testWrittenSubtractingInvalid() {

        stringArithmetic = new StringArithmetic();

        // Non-digit somewhere in strings
        try {
            testedString1 = "12 3a";
            testedString2 = "12g3";
            stringArithmetic.writtenSubtracting(testedString1, testedString1);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit somewhere in strings");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Empty strings to subtract
        try {
            testedString1 = "";
            testedString2 = "";
            assertEquals("Empty strings to subtract", "0", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty strings to compare] should never throw exception");
        }

        // Empty string and values
        try {
            testedString1 = "";
            testedString2 = "5";
            assertEquals("Empty string and 5", "0", stringArithmetic.writtenSubtracting(testedString1, testedString2));
            testedString1 = "";
            testedString2 = "0";
            assertEquals("Empty string and 0", "0", stringArithmetic.writtenSubtracting(testedString1, testedString2));
            testedString1 = "6";
            testedString2 = "";
            assertEquals("6 and empty string", "6", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Empty string and values] should never throw exception");
        }

        // First value is lower than second, ex. 1-5 = -4 not supported there, just returns firstVal
        try {
            testedString1 = "1";
            testedString2 = "5";
            assertEquals("First value is lower than second", "1", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[First value is lower than second] should never throw exception");
        }
    }

    /**
     * Testing function "writtenSubtracting" with valid data. Should never throw
     * exceptions.
     */
    @Test
    public void testWrittenSubtractingValid() {

        stringArithmetic = new StringArithmetic();

        // Just random subtracting
        try {
            testedString1 = "552291";
            testedString2 = "432180";
            assertEquals("Just random adding", "120111", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Just random subtracting] should never throw exception");
        }

        // Subtracting with insignificant zeros
        try {
            testedString1 = "00503928";
            testedString2 = "0000000303155";
            assertEquals("Just random adding", "200773", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Subtracting with insignificant zeros] should never throw exception");
        }
    }

    /**
     * Testing function "writtenSubtracting" at boundary conditions. May contain
     * invalid data.
     */
    @Test
    public void testWrittenSubtractingBoundary() {

        stringArithmetic = new StringArithmetic();

        // Non-digit at END of string
        try {
            testedString1 = "123a";
            testedString2 = "123";
            stringArithmetic.writtenSubtracting(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at END of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Non-digit at BEGINNING of string
        try {
            testedString1 = "123";
            testedString2 = "a123";
            stringArithmetic.writtenSubtracting(testedString1, testedString2);
            fail("Should an exception occured while any of string is not pure numerical: Non-digit at BEGINNING of string");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Borrow from next position
        try {
            testedString1 = "514";
            testedString2 = "505";
            assertEquals("Just random adding", "9", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Borrow from next position] should never throw exception");
        }

        // Borrow from >1 positions away
        try {
            testedString1 = "65020";
            testedString2 = "64030";
            assertEquals("Just random adding", "990", stringArithmetic.writtenSubtracting(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Borrow from 2 positions away] should never throw exception");
        }
    }

    /**
     * Testing function "multiplying" with invalid data. TEST DOES NOT NEED TO
     * BE PERFORMED FOR ALL SPECIFIC INVALID DATA INPUTS, BECAUSE THE ONLY
     * INVALID DATA CASES ARE THROWN BY METHODS USED INSIDE THIS ONE. THIS MEANS
     * THAT IF THESE INSIDE METHODS ARE TESTED PROPELY THIS ONE WILL SURELY
     * THROW IF SOMETHING WILL FAIL.
     */
    @Test
    public void testMultiplyingInvalid() {

        stringArithmetic = new StringArithmetic();

        /* TO BE SURE THERE WILL BE PERFORMED ONE TEST TO THROW EXCEPTION. */
        try {
            testedString1 = "532g jkdshj309";
            testedString2 = "3777";
            stringArithmetic.multiplying(testedString1, testedString2);
            fail("Should an exception occured while input data is not pure numerical chain.");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }
    }

    /**
     * Testing function "multiplying" with valid data.
     */
    @Test
    public void testMultiplyingValid() {

        stringArithmetic = new StringArithmetic();

        // Simple a*b multiplying
        try {
            testedString1 = "5";
            testedString2 = "6";
            assertEquals("Simple a*b multiplying", "30", stringArithmetic.multiplying(testedString1, testedString2));

        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Simple a*b multiplying] should never throw exception");
        }

        // Simple a*a multiplying
        try {
            testedString1 = "5";
            testedString2 = "5";
            assertEquals("Simple a*b multiplying", "25", stringArithmetic.multiplying(testedString1, testedString2));

        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Simple a*b multiplying] should never throw exception");
        }
    }

    /**
     * Testing "multiplying" function at boundary conditions. This test may
     * contain invalid data.
     */
    @Test
    public void testMultiplyingBoundary() {

        stringArithmetic = new StringArithmetic();

        // a*0 && 0*a && 0*0 multiplying
        try {
            testedString1 = "5";
            testedString2 = "0";
            assertEquals("a*0 multiplying", "0", stringArithmetic.multiplying(testedString1, testedString2));
            testedString1 = "0";
            testedString2 = "5";
            assertEquals("0*a multiplying", "0", stringArithmetic.multiplying(testedString1, testedString2));
            testedString1 = "0";
            testedString2 = "0";
            assertEquals("0*0 multiplying", "0", stringArithmetic.multiplying(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[a*0 && 0*a && 0*0 multiplying] should never throw exception");
        }
    }

    /**
     * Testing "square" method with invalid data. TEST DOES NOT NEED TO BE
     * PERFORMED FOR ALL SPECIFIC INVALID DATA INPUTS, BECAUSE THE ONLY INVALID
     * DATA CASES ARE THROWN BY METHODS USED INSIDE THIS ONE. THIS MEANS THAT IF
     * THESE INSIDE METHODS ARE TESTED PROPELY THIS ONE WILL SURELY THROW IF
     * SOMETHING WILL FAIL.
     */
    @Test
    public void testSquareInvalid() {

        stringArithmetic = new StringArithmetic();

        /*  TO BE SURE THERE WILL BE PERFORMED ONE TEST TO THROW EXCEPTION. */
        try {
            testedString1 = "asg54 64d";
            testedString2 = "123";
            stringArithmetic.square(testedString1);
            fail("Should an exception occured while input data is not pure numerical chain.");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }
    }

    /**
     * Testing "square" method with valid data.
     */
    @Test
    public void testSquareValid() {

        stringArithmetic = new StringArithmetic();

        // Any value
        try {
            testedString1 = "5";
            assertEquals("Any value", "25", stringArithmetic.square(testedString1));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Any value] should never throw exception");
        }
    }

    /**
     * Testing "square" method at boundary conditions. This test may contain
     * invalid data. THIS METHOD BOUNDARY CONDITIONS ARE THE SAME AS IN METHOD
     * THAT IS BEING INVOKED IN THIS FUNCTION. TEST DOES NOT NEED TO BE
     * PERFORMED FOR BOUNDARY CONDITIONS, BECAUSE THE CONDITIONS ARE CHECKED BY
     * this.multiplying FUNCTION, WHICH IS BEING USED IN THIS METHOD.
     */
    @Test
    public void testSquareBoundary() {
    }

    /**
     * Testing "moduloOperation" function with invalid data. TEST DOES NOT NEED
     * TO BE PERFORMED FOR ALL SPECIFIC INVALID DATA INPUTS, BECAUSE SOME OF
     * INVALID DATA CASES ARE THROWN BY METHODS USED INSIDE THIS ONE. THIS MEANS
     * THAT IF THESE INSIDE METHODS ARE TESTED PROPELY THIS ONE WILL SURELY
     * THROW IF SOMETHING FAILS.
     */
    @Test
    public void testModuloOperationInvalid() {

        stringArithmetic = new StringArithmetic();

        /* TO BE SURE THERE WILL BE PERFORMED ONE TEST TO THROW EXCEPTION. */
        try {
            testedString1 = "asg54 64d";
            testedString2 = "123";
            stringArithmetic.moduloOperation(testedString1, testedString2);
            fail("Should an exception occured while input data is not pure numerical chain.");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

        // Div by 0 exception - arithmetic exception
        try {
            testedString1 = "5";
            testedString2 = "0";
            stringArithmetic.moduloOperation(testedString1, testedString2);
            fail("Should an exception occured while performing x mod 0");
        } catch (ArithmeticException ex) {
        } catch (ValueOutOfRequiredIntervalException ex) {
        }

    }

    /**
     * Testing "moduloOperation" on valid data.
     */
    @Test
    public void testModuloOperationValid() {

        stringArithmetic = new StringArithmetic();

        // Higher mod lower value
        try {
            testedString1 = "8";
            testedString2 = "7";
            assertEquals("Higher mod lower value", "1", stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Higher mod lower value] should never throw exception");
        }

        // Lower mod higher value
        try {
            testedString1 = "5";
            testedString2 = "7";
            assertEquals("Lower mod higher value", "5", stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Lower mod higher value] should never throw exception");
        }

        // Equal values
        try {
            testedString1 = "5";
            testedString2 = "5";
            assertEquals("Equal values", "0", stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Equal values] should never throw exception");
        }
    }

    /**
     * Testing "moduloOperation" function on boundary conditions. This test may
     * contain invalid data.
     */
    @Test
    public void testModuloOperationBoundary() {

        stringArithmetic = new StringArithmetic();

        // a mod a-1, a>2
        try {
            testedString1 = "3";
            testedString2 = "2";
            assertEquals("a mod a-1", "1", stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[a mod a-1] should never throw exception");
        }

        // a mod a+1 
        try {
            testedString1 = "2";
            testedString2 = "3";
            assertEquals("a mod a+1", testedString1, stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[a mod a+1] should never throw exception");
        }

        // 0 mod a
        try {
            testedString1 = "0";
            testedString2 = "2";
            assertEquals("0 mod a", "0", stringArithmetic.moduloOperation(testedString1, testedString2));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[0 mod a] should never throw exception");
        }
    }

    /**
     * Testing "fastModularExponentiation" with invalid data. TEST DOES NOT NEED
     * TO BE PERFORMED FOR ALL SPECIFIC INVALID DATA INPUTS, BECAUSE ALL OF
     * INVALID DATA CASES ARE THROWN BY METHODS USED INSIDE THIS ONE. THIS MEANS
     * THAT IF THESE INSIDE METHODS ARE TESTED PROPELY THIS ONE WILL SURELY
     * THROW.
     */
    @Test
    public void testFastModularExponentiationInvalid() {

        stringArithmetic = new StringArithmetic();

        /* TO BE SURE 2 EXCEPTION-THROW TESTS WILL BE PERFORMED. */
        // Invalid string
        try {
            testedString1 = "532a 64d";
            testedString2 = "123";
            String testedString3 = "b";
            stringArithmetic.fastModularExponentiation(testedString1, testedString2, testedString3);
            fail("Should an exception occured while input data is not pure numerical chain.");
        } catch (ValueOutOfRequiredIntervalException ex) {
        }
        // Div by 0
        try {
            testedString1 = "532a 64d";
            testedString2 = "123";
            String testedString3 = "0";
            stringArithmetic.fastModularExponentiation(testedString1, testedString2, testedString3);
            fail("Should an exception occured while input data is not pure numerical chain.");
        } catch (ValueOutOfRequiredIntervalException ex) {
        } catch (ArithmeticException ex) {
        }
    }

    /**
     * Testing "fastModularExponentiation" with valid values.
     */
    @Test
    public void testFastModularExponentiationValid() {

        stringArithmetic = new StringArithmetic();

        // Any valid values, ex. 2^4 mod 5 = 1
        try {
            testedString1 = "2";
            testedString2 = "4";
            String testedString3 = "5";
            assertEquals("Equal values", "1", stringArithmetic.fastModularExponentiation(testedString1, testedString2, testedString3));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Equal values] should never throw exception");
        }
    }

    /**
     * Testing "fastModularExponentiation" on boundary conditions. This test may
     * contain invalid data.
     */
    @Test
    public void testFastModularExponentiationBoundary() {

        stringArithmetic = new StringArithmetic();

        // 0^a mod m = 0
        try {
            testedString1 = "0";
            testedString2 = "4";
            String testedString3 = "5";
            assertEquals("Equal values", "0", stringArithmetic.fastModularExponentiation(testedString1, testedString2, testedString3));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Equal values] should never throw exception");
        }

        // 1^a mod m = 1
        try {
            testedString1 = "1";
            testedString2 = "4";
            String testedString3 = "5";
            assertEquals("Equal values", "1", stringArithmetic.fastModularExponentiation(testedString1, testedString2, testedString3));
        } catch (ValueOutOfRequiredIntervalException ex) {
            fail("[Equal values] should never throw exception");
        }

    }

}
