/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj1.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that performs Fermat's test of primality on a specific level of
 * probability. Tests are performed on numeric values represented by String
 * type.
 *
 * @author MarczykLAP
 * @version 1.0.0
 */
public class FermatTest {

    /**
     * The number that is being tested for primality. It is the "p" number in
     * following formula: a^(p-1) mod p.
     */
    private String testedNumber;

    /**
     * Test is made for specific number. It is the "a" number in following
     * formula: a^(p-1) mod p. "a" has to be within numerical interval which
     * stands for: a is higher or equal 1, but is lower than p.
     */
    private String testedOnNumber;

    /**
     * Result of the test.
     */
    private boolean result;

    /**
     * Non-parametric constructor.
     */
    public FermatTest() {

    }

    /**
     * Represents parity of a number. Consists of method that performs basic
     * operation of dimidiation algorithm.
     */
    private enum Parity {
        ODD, EVEN;

        /**
         * Function performs basic operation of dimidiation algorithm.
         *
         * @param value is the digit that lies just to right of the one that
         * parity is needed.
         * @return value determined by a constant table, depending on parity of
         * a number and value of the number that the first one is followed by.
         * digit.
         *
         * @throws NotNumericUnsignedValueException if input value wasn't digit.
         *
         */
        private int dimidiationElementary(int value) throws NotNumericUnsignedValueException {
            switch (this) {
                case ODD:
                    switch (value) {
                        case 0:
                            return 5;
                        case 1:
                            return 5;
                        case 2:
                            return 6;
                        case 3:
                            return 6;
                        case 4:
                            return 7;
                        case 5:
                            return 7;
                        case 6:
                            return 8;
                        case 7:
                            return 8;
                        case 8:
                            return 9;
                        case 9:
                            return 9;

                    }
                case EVEN:
                    switch (value) {
                        case 0:
                            return 0;
                        case 1:
                            return 0;
                        case 2:
                            return 1;
                        case 3:
                            return 1;
                        case 4:
                            return 2;
                        case 5:
                            return 2;
                        case 6:
                            return 3;
                        case 7:
                            return 3;
                        case 8:
                            return 4;
                        case 9:
                            return 4;
                    }
            }
            throw new NotNumericUnsignedValueException();
        }
    }

    /**
     * Procedure performs Fermat's test for currently set reference value.
     *
     * @throws NotNumericUnsignedValueException if action is taken on characters
     * that are not digits.
     */
    public void calculateFermatTest() throws NotNumericUnsignedValueException {

        if ("1".equals(this.fastModularExponentiation(this.testedOnNumber,
                this.writtenSubtracting(this.testedNumber, "1"),
                this.testedNumber))) {
            this.result = true;
        } else {
            this.result = false;
        }
    }

    /**
     * Procedure generates random reference value required to perform Fermat
     * test.
     *
     */
    public void generateRandomNumber() {

        /* At first the tested number have to be higher than 1,
           then it must be not empty, and not null */
        if (((this.compareValues(this.testedNumber, "1")) == 1) && (!"".equals(this.testedNumber)) && this.testedNumber != null) {

            Random generator = new Random();

            int maxNumberLength = this.testedNumber.length();
            String randomNumber = "";

            // Generator can return value = 0, length can't be =0;
            int randomNumberLenght = generator.nextInt(maxNumberLength + 1);
            while (randomNumberLenght == 0) {
                randomNumberLenght = generator.nextInt(maxNumberLength + 1);
            }

            // If our testedNumber is only 1 character, then we need to generate testedOnNumber lower by 1 and higher than 0
            if (maxNumberLength == 1) {

                randomNumber = generator.nextInt(Character.getNumericValue(this.testedNumber.charAt(0)))
                        + randomNumber;

                while ("0".equals(randomNumber)) {
                    randomNumber = "";
                    randomNumber = generator.nextInt(Character.getNumericValue(this.testedNumber.charAt(0)))
                            + randomNumber;
                }
                /*  If we have same lenghts of testedNumber and testedOnNumber, in order to
                ensure that testedOnNumber is lower than testedNumber, make sure that
                first digit of testedOnNumber is not higher than first digit of testedNumber,
                also the last digit of testedOnNumber have to be lower than last digit of testedNumber.
                If the values of both numbers are the same, just subtract testedOnNumber by 1.  */
            } else if (maxNumberLength == randomNumberLenght) {

                // Generate first digit.
                randomNumber = generator.nextInt(Character.getNumericValue(this.testedNumber.charAt(0)) + 1)
                        + randomNumber;

                while ("0".equals(randomNumber)) {
                    randomNumber = "";
                    randomNumber = generator.nextInt(Character.getNumericValue(this.testedNumber.charAt(0)) + 1)
                            + randomNumber;
                }

                // Generate every other digits except the last.
                for (int i = 0; i < maxNumberLength - 2; i++) {
                    randomNumber = randomNumber + generator.nextInt(9);
                }

                // Check if the last is 0, if is - we cant get lower digit than this, so just subtract the number by 1.
                if (Character.getNumericValue(this.testedNumber.charAt(this.testedNumber.length() - 1)) == 0) {
                    randomNumber = randomNumber + 0;
                } // If last digit is not 0, generate the digit.
                else {
                    int tmpNum = generator.nextInt(9);
                    while (tmpNum > (Character.getNumericValue(this.testedNumber.charAt(this.testedNumber.length() - 1)))) {
                        tmpNum = generator.nextInt(9);
                    }
                    randomNumber = randomNumber + generator.nextInt(9);
                }

                // Here the -1 subtraction happens.
                if (randomNumber.equals(this.testedNumber)) {
                    randomNumber = this.writtenSubtracting(randomNumber, "1");
                }

            } // Length of testedOnNumber is not the same as length of testedNumber, and is not 1.
            // Feel free and generate any number without worries.
            else {

                if (randomNumberLenght == 1) {
                    int tmpNum = generator.nextInt(9);
                    while (tmpNum == 0) {
                        tmpNum = generator.nextInt(9);
                    }
                    randomNumber = randomNumber + tmpNum;
                } else {
                    for (int i = 0; i < randomNumberLenght; i++) {
                        randomNumber = randomNumber + generator.nextInt(9);
                    }
                }
            }
            this.testedOnNumber = this.deleteInsignificantZeroes(randomNumber);
        }
    }

    /**
     * Procedure sets value of the number that has to be tested for primality.
     *
     * @param numToSet String which has to be set.
     * @throws NotNumericUnsignedValueException If input string includes
     * non-digit characters.
     * @throws WrongNumericalIntervalException If input string value is not in
     * the right numerical interval.
     */
    public void setNumber(String numToSet) throws NotNumericUnsignedValueException, WrongNumericalIntervalException {

        for (char ch : numToSet.toCharArray()) {
            if (!(Character.isDigit(ch))) {
                throw new NotNumericUnsignedValueException();
            }

            if (this.compareValues(numToSet, "1") <= 0) {
                throw new WrongNumericalIntervalException();
            }

            this.testedNumber = numToSet;
        }
    }

    /**
     * Procedure sets value of the number that primality test requires to be
     * performed. It is the "a" in following formula: (a^(p-1)) mod p.
     *
     * @param numToSet String which has to be set.
     * @throws NotNumericUnsignedValueException If input string includes
     * non-digit characters.
     * @throws WrongNumericalIntervalException If string value exceeds the value
     * of number which is going to be tested, or when its less than 1.
     */
    public void setTestOnNumber(String numToSet) throws NotNumericUnsignedValueException, WrongNumericalIntervalException {

        for (char ch : numToSet.toCharArray()) {

            if (!(Character.isDigit(ch))) {
                throw new NotNumericUnsignedValueException();
            }

            if (((this.compareValues(numToSet, this.testedNumber)) >= 0)
                    || ((this.compareValues(numToSet, "1")) < 0)) {

                throw new WrongNumericalIntervalException();

            } else {
                this.testedOnNumber = numToSet;
            }
        }
    }

    /**
     * Getter of the result of Fermat's test.
     *
     * @return returns actually stored result of Fermat's test.
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     * Getter of the number that is being checked for primality.
     *
     * @return returns actually stored number of Fermat's test.
     */
    public String getNumber() {
        return this.testedNumber;
    }

    /**
     * Getter of the number according to which primality test is performed.
     *
     * @return returns actually stored reference value required to perform the
     * Fermat's test.
     */
    public String getTestedOnNumber() {
        return this.testedOnNumber;
    }

    /**
     * Function performs algorithm of fast modular exponentiation given by
     * formula: ((a^p) mod m) on numeric only values represented as Strings.
     *
     * @param a in the formula: ((a^p) mod m)
     * @param p in the formula: ((a^p) mod m)
     * @param m in the formula: ((a^p) mod m)
     * @return result of the algorithm
     *
     * @throws NotNumericUnsignedValueException if conversion to binary will
     * receive non-digit character.
     *
     */
    private String fastModularExponentiation(String a, String p, String m) throws NotNumericUnsignedValueException {

        String binaryP = this.decToBin(p);

        String algorithmResult;
        String algorithmVariable;

        algorithmResult = "1";
        algorithmVariable = this.moduloOperation(a, m);

        for (int i = binaryP.length() - 1; i >= 0; i--) {
            if (Character.getNumericValue(binaryP.charAt(i)) == 1) {
                algorithmResult = this.multiplying(algorithmResult, algorithmVariable);
                algorithmResult = this.moduloOperation(algorithmResult, m);
                algorithmVariable = this.squareStringValue(algorithmVariable);
                algorithmVariable = this.moduloOperation(algorithmVariable, m);
            } else {
                algorithmVariable = this.squareStringValue(algorithmVariable);
                algorithmVariable = this.moduloOperation(algorithmVariable, m);
            }
        }
        return algorithmResult;
    }

    /**
     * Function performs modulo operation on two Strings that should represent a
     * numeric unsigned value.
     *
     * @param firstNum value that is being divided
     * @param secondNum the divider
     * @return operation of modulo division.
     *
     */
    private String moduloOperation(String firstNum, String secondNum) {

        firstNum = this.deleteInsignificantZeroes(firstNum);
        secondNum = this.deleteInsignificantZeroes(secondNum);

        int firstNumLength = firstNum.length();
        int secondNumLength = secondNum.length();

        int comparisionResult = this.compareValues(firstNum, secondNum);

        if (comparisionResult == 0) {
            return "0";
        } else if (comparisionResult < 0) {
            return firstNum;
        } else {

            while (comparisionResult > 0) {
                firstNum = this.writtenSubtracting(firstNum, secondNum);
                comparisionResult = this.compareValues(firstNum, secondNum);
            }
            return firstNum;
        }
    }

    /**
     * Function deletes insignificant zeros in String that should represent a
     * numeric unsigned value.
     *
     * @param inputString value to perform action on.
     * @return value without insignificant zeros.
     */
    private String deleteInsignificantZeroes(String inputString) {

        int j = 0;
        boolean foundFirstSignificantNum = false;
        while (!foundFirstSignificantNum && j < inputString.length()) {
            if (Character.getNumericValue(inputString.charAt(j)) != 0) {
                foundFirstSignificantNum = true;
            } else {
                j++;
            }
        }
        if (foundFirstSignificantNum == true) {
            return inputString.substring(j);
        } else {
            return "0";
        }
    }

    /**
     * Function converts array of Integer values to String without insignificant
     * zeros.
     *
     * @param inputArray is ArrayList of Integer type.
     * @return String that represents a numeric value without insignificant
     * zeros.
     */
    private String arrayIntToString(ArrayList<Integer> inputArray) {
        String returnString = "";
        int j = 0;
        boolean foundFirstNum = false;
        while (!foundFirstNum && j < inputArray.size()) {
            if (inputArray.get(j) != 0) {
                foundFirstNum = true;
            } else {
                j++;
            }
        }
        for (int i = j; i < inputArray.size(); i++) {
            returnString = returnString + inputArray.get(i);
        }
        if (foundFirstNum == true) {
            return returnString;
        } else {
            return "0";
        }
    }

    /**
     * Function converts array of Boolean values to String without insignificant
     * zeros.
     *
     * @param inputArray is ArrayList of Boolean type.
     * @return String that represents a numeric value without insignificant
     * zeros.
     */
    private String arrayBoolToString(ArrayList<Boolean> inputArray) {
        String returnString = "";
        int j = 0;
        boolean foundFirstNum = false;
        while (!foundFirstNum && j < inputArray.size()) {
            if (inputArray.get(j) != false) {
                foundFirstNum = true;
            } else {
                j++;
            }
        }
        for (int i = j; i < inputArray.size(); i++) {
            if (inputArray.get(i) == true) {
                returnString = returnString + '1';
            } else {
                returnString = returnString + '0';
            }
        }
        if (foundFirstNum == true) {
            return returnString;
        } else {
            return "0";
        }
    }

    /**
     * Function performs decimal dimidiation algorithm on a numeric value
     * represented by String. Value should be unsigned.
     *
     * @param value numeric value represented by String that should be unsigned.
     * @return decimal value divided by two as a String. not-digit Char in
     * function parameter.
     * @throws NotNumericUnsignedValueException if elementary operation of
     * dimidiation is being taken on character that is not a digit.
     */
    private String decimalDimidiation(String value) throws NotNumericUnsignedValueException {

        String resultString = "";

        int firstNum;
        int secondNum;

        Parity firstNumParity;

        // Algorithm requires to have '0' as the first Char of String;
        value = '0' + value;
        for (int i = 0; i < value.length() - 1; i++) {

            firstNum = Character.getNumericValue(value.charAt(i));
            secondNum = Character.getNumericValue(value.charAt(i + 1));

            if ((firstNum & 1) == 0) {
                firstNumParity = Parity.EVEN;
            } else {
                firstNumParity = Parity.ODD;
            }

            Integer elementaryDivResult = firstNumParity.dimidiationElementary(secondNum);
            resultString += elementaryDivResult.toString();

        }
        // Check if the result is not single 0, if it isn't, delete insignificant zero at the beginning
        if (resultString.charAt(0) == '0' && resultString.length() != 1) {
            resultString = resultString.substring(1, resultString.length());
        }
        return resultString;
    }

    /**
     * Function performs basic written method of adding on two unsigned values
     * represented as String
     *
     * @param firstNum first number of operation
     * @param secondNum second number of operation
     * @return result of adding
     */
    private String writtenAdding(String firstNum, String secondNum) {

        /* Each char of string is put into array. It makes the alghoritm easier to implement. */
        ArrayList<Integer> arrayFirstNum = new ArrayList<>();
        ArrayList<Integer> arraySecondNum = new ArrayList<>();
        ArrayList<Integer> arrayResult = new ArrayList<>();

        for (int i = 0; i < firstNum.length(); i++) {
            arrayFirstNum.add(Character.getNumericValue(firstNum.charAt(i)));
        }
        for (int i = 0; i < secondNum.length(); i++) {
            arraySecondNum.add(Character.getNumericValue(secondNum.charAt(i)));
        }

        /* Checking the length and comparing if both numbers are same size. If not, add insignificant zeroes to array,
           so both arrays are the same size.
           It allows to evade adding array1[i]+array2[i] when some of those may not exist (if they are different sizes).
           (ArrayOutOfBoundException) */
        int lengthFirstNum = arrayFirstNum.size() - 1;
        int lengthSecondNum = arraySecondNum.size() - 1;

        if (lengthFirstNum != lengthSecondNum) {

            int highestLength = Math.max(lengthFirstNum, lengthSecondNum);
            if (lengthFirstNum != highestLength) {
                for (int i = 0; i < highestLength - lengthFirstNum; i++) {
                    arrayFirstNum.add(0, 0);
                }
            } else if (lengthSecondNum != highestLength) {
                for (int i = 0; i < highestLength - lengthSecondNum; i++) {
                    arraySecondNum.add(0, 0);
                }
            }
        }

        // Adding alghoritm
        int carriage = 0;
        int value;
        // !!! It may be arraySecondNum also, as we added insignificant zeroes, both array sizes are the same.
        for (int i = arrayFirstNum.size() - 1; i >= 0; i--) {

            value = arrayFirstNum.get(i) + arraySecondNum.get(i) + carriage;
            carriage = 0;

            if (value > 9) {
                carriage++;
                value -= 10;
            }
            arrayResult.add(0, value);
        }
        if (carriage != 0) {
            arrayResult.add(0, carriage);
        }
        return this.arrayIntToString(arrayResult);
    }

    /**
     * Function performs basic written method of subtracting on two unsigned
     * values represented as String. It does not support unsigned values and
     * does not return such ones.
     *
     * @param firstNum first number of operation, String should consist only of
     * numbers and represent a unsigned numeric value
     * @param secondNum second number of operation, same requirements as
     * firstNum
     * @return result of subtracting. If the firstNum is lower than the
     * secondNum, then the result is the firstNum.
     */
    private String writtenSubtracting(String firstNum, String secondNum) {

        /* Each char of string is put into array. It makes the alghoritm easier to implement. */
        ArrayList<Integer> arrayFirstNum = new ArrayList<>();
        ArrayList<Integer> arraySecondNum = new ArrayList<>();
        ArrayList<Integer> arrayResult = new ArrayList<>();

        for (int i = 0; i < firstNum.length(); i++) {
            arrayFirstNum.add(Character.getNumericValue(firstNum.charAt(i)));
        }
        for (int i = 0; i < secondNum.length(); i++) {
            arraySecondNum.add(Character.getNumericValue(secondNum.charAt(i)));
        }

        /* Checking the length and comparing if both numbers are same size. If not, add insignificant zeroes to array,
               so both arrays are the same size. It allows to evade ArrayOutOfBoundException */
        int lengthFirstNum = arrayFirstNum.size() - 1;
        int lengthSecondNum = arraySecondNum.size() - 1;

        if (lengthFirstNum != lengthSecondNum) {

            int highestLength = Math.max(lengthFirstNum, lengthSecondNum);
            if (lengthFirstNum != highestLength) {
                for (int i = 0; i < highestLength - lengthFirstNum; i++) {
                    arrayFirstNum.add(0, 0);
                }
            } else if (lengthSecondNum != highestLength) {
                for (int i = 0; i < highestLength - lengthSecondNum; i++) {
                    arraySecondNum.add(0, 0);
                }
            }
        }

        /* Deleting insignificant zeroes before using String::compareTo function,
            because those zeroes could affect the result */
        firstNum = this.deleteInsignificantZeroes(firstNum);
        secondNum = this.deleteInsignificantZeroes(secondNum);

        /* If firstNum is greater than secondNum, just return firstNum,
            because the result should be signed. This function does not support unsigned values. */
        int greaterValue;
        greaterValue = this.compareValues(firstNum, secondNum);

        if (greaterValue == 0) {        // Values are the same, result is "a-a = 0".
            return "0";
        } else if (greaterValue < 0) {  // firstNum is lower, result should be negative, but this function does not support such ones.
            return firstNum;
        } else {                        // firstNum is higher, subtracting is possible.

            // Adding alghoritm
            int borrow = 0;
            int value;
            // !!! It may be arraySecondNum also, as we added insignificant zeroes, both array sizes are the same.
            for (int i = arrayFirstNum.size() - 1; i >= 0; i--) {

                value = arrayFirstNum.get(i) - arraySecondNum.get(i);
                if (value < 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        int borrowValue = arrayFirstNum.get(j);
                        if (borrowValue != 0) {
                            arrayFirstNum.set(j, borrowValue - 1);
                            arrayFirstNum.set(i, arrayFirstNum.get(i) + 10);
                            value = arrayFirstNum.get(i) - arraySecondNum.get(i);
                        }
                    }
                }
                arrayResult.add(0, value);

            }
            return this.arrayIntToString(arrayResult);
        }
    }

    /**
     * Function compares two strings which should represent unsigned numeric
     * value.
     *
     * @param firstString value that is compared to the other one
     * @param secondString value that the first one is compared to
     * @return returns 0 as a String if strings are equal numeric value, 1 if
     * the firstString is greater than the secondString, -1 if the firstString
     * is lower than the secondString.
     */
    private int compareValues(String firstString, String secondString) {

        firstString = this.deleteInsignificantZeroes(firstString);
        secondString = this.deleteInsignificantZeroes(secondString);

        int firstLength = firstString.length();
        int secondLength = secondString.length();

        // Strings same size, if there is anyhigher number in firstString on index i, then firstString is greater
        if (firstLength == secondLength) {

            boolean foundGreaterThanSecond = false;
            boolean stringsNotEqual = false;

            int i = firstLength - 1;
            while (!foundGreaterThanSecond && i >= 0) {

                int first = Character.getNumericValue(firstString.charAt(i));
                int second = Character.getNumericValue(secondString.charAt(i));

                if (first > second) {
                    foundGreaterThanSecond = true;
                } else {
                    i--;
                }

                // If strings differ on any position, they are not equal
                if (first != second) {
                    stringsNotEqual = true;
                }
            }

            if (foundGreaterThanSecond) {
                return 1;
            } else if (stringsNotEqual) {
                return -1;
            } else {
                return 0;
            }
        } else if (firstLength > secondLength) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Function multiplies two numeric values represented by Strings that should
     * be unsigned
     *
     * @param multiplier first value of multiplying, is the iterator of adding
     * loop
     * @param multiplicant second value of multiplying, is the one that is being
     * added specific number of times
     * @return result of multiplying two unsigned values as a String
     */
    private String multiplying(String multiplier, String multiplicant) {

        String multiplyingResult = "0";

        // Add multiplier to result as many times as multiplicant value says
        while (!"0".equals(multiplicant)) {
            multiplicant = this.writtenSubtracting(multiplicant, "1");
            multiplyingResult = this.writtenAdding(multiplyingResult, multiplier);
        }
        return multiplyingResult;
    }

    /**
     * Function squares numeric value represented by String
     *
     * @param toSquare is the value to be squared.
     * @return square of the value input as a String
     */
    private String squareStringValue(String toSquare) {
        return this.multiplying(toSquare, toSquare);
    }

    /**
     * Function uses decimal dimidiation algorithm and div by 2 with remainder
     * algorithm to convert decimal unsigned value represented as String to
     * binary value.
     *
     * @param valueToConvert numeric value represented by String, should be
     * unsigned.
     * @return binary representation in String of the decimal input value.
     * dimidiation on not numeric-only String.
     *
     * @throws NotNumericUnsignedValueException if decimal dimidiation is not
     * performed on a digit.
     */
    private String decToBin(String valueToConvert) throws NotNumericUnsignedValueException {

        // Result binary string represented as array
        ArrayList<Boolean> arrayBinaryString = new ArrayList<>();

        // Current value to divide
        String toDiv = valueToConvert;
        while (!"0".equals(toDiv)) {

            int toDivLength = toDiv.length();
            int lastCharValue = Character.getNumericValue(toDiv.charAt(toDivLength - 1));

            /* Using AND operation on binary representation of value to check if a value is even or odd
               if it is even, there is no remainder. */
            Parity lastCharParity;
            if ((lastCharValue & 1) == 0) {
                lastCharParity = Parity.EVEN;
            } else {
                lastCharParity = Parity.ODD;
            }

            // Depending on the remainder, the final value forms
            if (lastCharParity == Parity.EVEN) {
                arrayBinaryString.add(0, false);
            } else {
                arrayBinaryString.add(0, true);
            }
            toDiv = this.decimalDimidiation(toDiv);
        }

        return this.arrayBoolToString(arrayBinaryString);
    }

}
