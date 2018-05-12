package pl.polsl.java.marczyk.model;

import java.util.ArrayList;

/**
 * Provides chosen basic and advanced arithmetical functions performed on
 * numerical values represented by String type.
 *
 * @author Marczyk Grzegorz
 * @version 1.0.3
 */
public class StringArithmetic {

    public StringArithmetic() {

    }

    /**
     * Function performs algorithm of fast modular exponentiation given by
     * formula: ((a^p) mod m) on unsigned numeric only values represented as
     * Strings.
     *
     * @param a in the formula: ((a^p) mod m)
     * @param p in the formula: ((a^p) mod m)
     * @param m in the formula: ((a^p) mod m)
     * @return result of the algorithm
     * @throws ValueOutOfRequiredIntervalException function takes the throws
     * from this.multiplying, this.moduloOperation, this.square.
     */
    public String fastModularExponentiation(String a, String p, String m) throws ValueOutOfRequiredIntervalException {

        String binaryP = this.decToBin(p);

        String algorithmResult;
        String algorithmVariable;

        algorithmResult = "1";
        algorithmVariable = this.moduloOperation(a, m);

        for (int i = binaryP.length() - 1; i >= 0; i--) {
            if (Character.getNumericValue(binaryP.charAt(i)) == 1) {
                algorithmResult = this.multiplying(algorithmResult, algorithmVariable);
                algorithmResult = this.moduloOperation(algorithmResult, m);
                algorithmVariable = this.square(algorithmVariable);
                algorithmVariable = this.moduloOperation(algorithmVariable, m);
            } else {
                algorithmVariable = this.square(algorithmVariable);
                algorithmVariable = this.moduloOperation(algorithmVariable, m);
            }

        }
        return algorithmResult;
    }

    /**
     * Function deletes insignificant zeros in String. Simply deletes all the
     * zeros if found any non-zero character. IMPORTANT: It handles characters
     * that are not digits!
     *
     * @param inputString value to perform action on.
     * @return string without insignificant zeros. If input was empty, returns
     * empty, as returns input value if no insignificant zeros were found. Input
     * value = 0 also is returned as "0".
     */
    public String deleteInsignificantZeros(String inputString) {

        int j = 0;
        boolean justZeros = false;
        boolean foundFirstSignificantNum = false;
        while (!foundFirstSignificantNum && j < inputString.length()) {
            if (Character.getNumericValue(inputString.charAt(j)) != 0) {
                foundFirstSignificantNum = true;
            } else {
                j++;
                justZeros = true;
            }
        }
        if (foundFirstSignificantNum == true) {
            return inputString.substring(j);
        } else {
            if (justZeros == true) {
                return "0";
            }
            return inputString;
        }
    }

    /**
     * Function compares two strings which should represent unsigned numeric
     * value. IMPORTANT: Function can compare two empty values as a 0.
     *
     * @param firstString value that is compared to the other one. Should be
     * string that represents a numerical unsigned value. Cannot contain
     * characters other than digits.
     * @param secondString value that the first one is compared to. Requirements
     * are the same as in the firstString.
     * @return returns 0 if strings are equal numeric in value, 1 if the
     * firstString is greater than the secondString, -1 if the firstString is
     * lower than the secondString.
     * @throws ValueOutOfRequiredIntervalException when any of input parameters
     * are not pure numerical chains of characters. Somewhere in the String a
     * non-digit character exists.
     */
    public int compareValues(String firstString, String secondString) throws ValueOutOfRequiredIntervalException {

        firstString = this.deleteInsignificantZeros(firstString);
        secondString = this.deleteInsignificantZeros(secondString);

        if ("".equals(firstString)) {
            firstString = "0";
        }
        if ("".equals(secondString)) {
            secondString = "0";
        }

        int firstLength = firstString.length();
        int secondLength = secondString.length();

        for (int i = 0; i < firstLength; i++) {
            int tmp = Character.getNumericValue(firstString.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit");
            }
        }
        for (int i = 0; i < secondLength; i++) {
            int tmp = Character.getNumericValue(secondString.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit");
            }
        }

        // Strings same size, if there is anyhigher number in firstString on index i, then firstString is greater
        if (firstLength == secondLength) {

            boolean foundResult = false;

            int i = 0;
            int resultSetter = 0;
            while (!foundResult && i < firstLength) { // same size strings, doesnt matter which size is taken

                int first = Character.getNumericValue(firstString.charAt(i));
                int second = Character.getNumericValue(secondString.charAt(i));

                if (first > second || first < second) {
                    foundResult = true;
                    resultSetter = first - second;
                } else {
                    i++;
                }
            }

            if (foundResult) {
                if (resultSetter < 0) {
                    return -1;
                } else {
                    return 1;
                }
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
     * Function performs basic written method of adding on two unsigned values
     * represented as String.
     *
     * @param firstNum first number of operation represented as String. Should
     * consist of digit-characters only.
     * @param secondNum second number of operation represented as String.Should
     * consist of digit-characters only.
     * @return result of adding as a String. If input parameter is empty, it is
     * treated as a 0.
     * @throws ValueOutOfRequiredIntervalException when any of input parameters
     * are not pure numerical chains of characters. Somewhere in the String a
     * non-digit character exists.
     *
     */
    public String writtenAdding(String firstNum, String secondNum) throws ValueOutOfRequiredIntervalException {

        /* Each char of string is put into array. It makes the alghoritm easier to implement. */
        ArrayList<Integer> arrayFirstNum = new ArrayList<>();
        ArrayList<Integer> arraySecondNum = new ArrayList<>();
        ArrayList<Integer> arrayResult = new ArrayList<>();

        for (int i = 0; i < firstNum.length(); i++) {
            int tmp = Character.getNumericValue(firstNum.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit");
            }
            arrayFirstNum.add(tmp);
        }
        for (int i = 0; i < secondNum.length(); i++) {
            int tmp = Character.getNumericValue(secondNum.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit");
            }
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
     * Function multiplies two numeric values represented by Strings that should
     * be unsigned
     *
     * @param multiplier first value of multiplying, is the iterator of adding
     * loop. Should consist of digit-characters only.
     * @param multiplicant second value of multiplying, is the one that is being
     * added specific number of times. Requirements are the same as in
     * multiplier.
     * @return result of multiplying.
     * @throws ValueOutOfRequiredIntervalException function takes the throws
     * from this.writtenSubtracting and this.writtenAdding.
     */
    public String multiplying(String multiplier, String multiplicant) throws ValueOutOfRequiredIntervalException {

        String multiplyingResult = "0";
        this.deleteInsignificantZeros(multiplier);
        this.deleteInsignificantZeros(multiplicant);

        // Add multiplier to result as many times as multiplicant value says
        while (!"0".equals(multiplicant)) {
            multiplicant = this.writtenSubtracting(multiplicant, "1");
            multiplyingResult = this.writtenAdding(multiplyingResult, multiplier);
        }
        return multiplyingResult;
    }

    /**
     * Function squares numeric value represented by String. Performs following
     * formula: toSquare*toSquare.
     *
     * @param toSquare is the value to be squared. Must be a String that
     * consists only of digit-characters.
     * @return square of the input value.
     * @throws ValueOutOfRequiredIntervalException function takes the throws
     * from this.multiplying.
     */
    public String square(String toSquare) throws ValueOutOfRequiredIntervalException {
        return this.multiplying(toSquare, toSquare);
    }

    /**
     * Function performs basic written method of subtracting on two unsigned
     * values represented as String. Following formula is performed: return =
     * firstNum - secondNum.
     *
     * @param firstNum first number of operation represented as String. Should
     * consist of digit-characters only. In calculations empty values are set to
     * 0.
     * @param secondNum second number of operation represented as String.
     * Requirements are the same as in firstNum.
     * @return result of subtracting. If the firstNum is lower than the
     * secondNum, then the result is the firstNum.
     * @throws ValueOutOfRequiredIntervalException when any of input parameters
     * are not pure numerical chains of characters. Somewhere in the String a
     * non-digit character exists. If empty value input, as first number,
     * returns 0.
     */
    public String writtenSubtracting(String firstNum, String secondNum) throws ValueOutOfRequiredIntervalException {

        if ("".equals(firstNum)) {
            firstNum = "0";
        }
        if ("".equals(secondNum)) {
            secondNum = "0";
        }
        /* Each char of string is put into array. It makes the alghoritm easier to implement. */
        ArrayList<Integer> arrayFirstNum = new ArrayList<>();
        ArrayList<Integer> arraySecondNum = new ArrayList<>();
        ArrayList<Integer> arrayResult = new ArrayList<>();

        firstNum = this.deleteInsignificantZeros(firstNum);
        secondNum = this.deleteInsignificantZeros(secondNum);

        for (int i = 0; i < firstNum.length(); i++) {
            int tmp = Character.getNumericValue(firstNum.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit " + firstNum);
            }
            arrayFirstNum.add(tmp);
        }
        for (int i = 0; i < secondNum.length(); i++) {
            int tmp = Character.getNumericValue(secondNum.charAt(i));
            if (tmp < 0 || tmp > 9) {
                throw new ValueOutOfRequiredIntervalException("String contains a value which is not a digit");
            }
            arraySecondNum.add(tmp);
        }


        /* If firstNum is greater than secondNum, just return firstNum,
            because the result should be signed. This function does not support unsigned values. */
        int greaterValue;
        greaterValue = this.compareValues(firstNum, secondNum);

        if (greaterValue == 0) {        // Values are the same, result is "a-a = 0".
            return "0";
        } else if (greaterValue < 0) {  // firstNum is lower, result should be negative, but this function does not support such ones.
            return firstNum;
        } else {                        // firstNum is higher, subtracting is possible.

            // Add insignificant zeros to prevent OutOfBound exceptions
            int lengthFirstNum = firstNum.length();
            int lengthSecondNum = secondNum.length();
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

            // Subtracting
            int borrow = 0;
            int value;
            // !!! It may be arraySecondNum also, as we added insignificant zeroes, both array sizes are the same.
            for (int i = arrayFirstNum.size() - 1; i >= 0; i--) {

                value = arrayFirstNum.get(i) - arraySecondNum.get(i);
                if (value < 0) {
                    borrow(arrayFirstNum, i);
                    value = arrayFirstNum.get(i) - arraySecondNum.get(i);
                }
                arrayResult.add(0, value);

            }
            return this.arrayIntToString(arrayResult);
        }
    }

    /**
     * Function performs modulo operation on two Strings that should represent a
     * numeric unsigned value. Performs following formula: result = firstNum mod
     * secondNum.
     *
     * @param firstNum value that is being divided. Should consist of
     * digit-characters only.
     * @param secondNum the divider. Requirements are the same as in firstNum.
     * @return operation of modulo division.
     * @throws ValueOutOfRequiredIntervalException function takes the throws
     * from the throw from this.compareValues.
     *
     */
    public String moduloOperation(String firstNum, String secondNum) throws ValueOutOfRequiredIntervalException {

        if ("0".equals(secondNum)) {
            throw new ArithmeticException("By 0 division.");
        }

        firstNum = this.deleteInsignificantZeros(firstNum);
        secondNum = this.deleteInsignificantZeros(secondNum);

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
     * Represents parity of a number. Contains a method that performs basic
     * operation of dimidiation algorithm.
     */
    private enum Parity {
        ODD, EVEN;

        /**
         * Function performs basic operation of dimidiation algorithm on two
         * adjacent digits. Enum should not contain more fields than "ODD" and
         * "EVEN".
         *
         * @param value is the digit that lies just to right of the one that
         * parity is needed. Digit is represented by int value in given
         * interval: 0-9 and the input value has to be in it.
         * @return value determined by constant table, depending on parity of a
         * digit and value of the digit that the first one is followed by.
         * Return value is initialized as "-1", should never return such value
         * if nothing strange happens.
         */
        private int dimidiationElementary(int value) {
            int retVal = -1;
            switch (this) {
                case ODD:
                    switch (value) {
                        case 0:
                            retVal = 5;
                            break;
                        case 1:
                            retVal = 5;
                            break;
                        case 2:
                            retVal = 6;
                            break;
                        case 3:
                            retVal = 6;
                            break;
                        case 4:
                            retVal = 7;
                            break;
                        case 5:
                            retVal = 7;
                            break;
                        case 6:
                            retVal = 8;
                            break;
                        case 7:
                            retVal = 8;
                            break;
                        case 8:
                            retVal = 9;
                            break;
                        case 9:
                            retVal = 9;
                            break;
                    }
                    break;
                case EVEN:
                    switch (value) {
                        case 0:
                            retVal = 0;
                            break;
                        case 1:
                            retVal = 0;
                            break;
                        case 2:
                            retVal = 1;
                            break;
                        case 3:
                            retVal = 1;
                            break;
                        case 4:
                            retVal = 2;
                            break;
                        case 5:
                            retVal = 2;
                            break;
                        case 6:
                            retVal = 3;
                            break;
                        case 7:
                            retVal = 3;
                            break;
                        case 8:
                            retVal = 4;
                            break;
                        case 9:
                            retVal = 4;
                            break;
                    }
                    break;
            }
            return retVal;
        }
    }

    /**
     * Function performs decimal dimidiation algorithm on a numeric value
     * represented by String.
     *
     * @param value numeric value represented by String that should be unsigned.
     * Value should be unsigned and consist of no other characters than digits,
     * otherwise some unexpected values might be calculated.
     * @return decimal value divided by two as a String.
     */
    private String decimalDimidiation(String value) {

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
     * Function uses decimal dimidiation algorithm and div by 2 with remainder
     * algorithm to convert decimal unsigned value represented as String to
     * binary value.
     *
     * @param valueToConvert should numeric value represented by String,
     * unsigned, should not consist of characters other than digits - otherwise
     * unexpected results may occur.
     * @return binary representation as a String of the decimal input value.
     */
    private String decToBin(String valueToConvert) {

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

            // Depending on the remainder, the final value is being formed.
            if (lastCharParity == Parity.EVEN) {
                arrayBinaryString.add(0, false);
            } else {
                arrayBinaryString.add(0, true);
            }
            toDiv = this.decimalDimidiation(toDiv);
        }
        return this.arrayBoolToString(arrayBinaryString);
    }

    /**
     * Function converts array of Boolean values to String without insignificant
     * zeros. Given result is a binary represented value. Values that are placed
     * closer to [0] index of array are the ones on the left in result chain.
     *
     * @param inputArray is ArrayList of Boolean type.
     * @return String that represents a numeric binary value without
     * insignificant zeros.
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
     * Function converts array of Integer values to String without insignificant
     * zeros. Values that are placed closer to [0] index of array are the ones
     * on the left in result chain.
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
     * Function is used in this.writtenSubtracting method. It finds the position
     * to borrow from and changes the input numerical chains to proper state.
     *
     * @param firstNum numerical chain. This is the chain that will be moderated
     * in order to pass a value to the position that wants the borrow.
     * @param borrowPos is position which wants to borrow.
     */
    private void borrow(ArrayList<Integer> firstNum, int borrowPos) {
        boolean foundBorrow = false;

        for (int i = borrowPos - 1; i >= 0 && foundBorrow == false; i--) {
            if (firstNum.get(i) != 0) {
                foundBorrow = true;
                firstNum.set(i, firstNum.get(i) - 1);
                firstNum.set(i + 1, firstNum.get(i + 1) + 10);
                if ((i + 1) != borrowPos) {
                    borrow(firstNum, borrowPos);
                }
            }
        }
    }

}
