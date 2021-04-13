package com.hse.organizer.modules.implementation;

import com.hse.organizer.modules.DrugCodeValidator;

public class DrugCodeValidatorImplementation implements DrugCodeValidator {
    /**
     * @param code is a bar code
     * @return if drug is original return 1 else 0
     */
    @Override
    public Boolean validate(String code) {

        if(code.length() != 13)
            return false;

        String evenNumbers, oddNumbers, curSum;
        int evenNumberSum = 0, oddNumberSum = 0, sumCurrent = 0, currentControlSum;

        int controlSumNumAsserted = Integer.parseInt(String.valueOf(code.charAt(code.length() - 1)));

        code = removeLastChar(code);
        evenNumbers = getEvenNumbers(code);
        oddNumbers = getOddNumbers(code);

        for (int i = 0; i < evenNumbers.length(); i++) {
            evenNumberSum += Integer.parseInt(String.valueOf(evenNumbers.charAt(i)));
            oddNumberSum += Integer.parseInt(String.valueOf(oddNumbers.charAt(i)));
        }

        evenNumberSum = evenNumberSum * 3;
        sumCurrent = evenNumberSum + oddNumberSum;

        curSum = String.valueOf(sumCurrent);

        currentControlSum = Integer.parseInt(String.valueOf(curSum.charAt(curSum.length() - 1)));

        if ((10 - currentControlSum) == controlSumNumAsserted) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str string code
     * @return Четные числа в коде
     */
    @Override
    public String getEvenNumbers(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < str.length(); i = i + 2) {
            result.append(str.charAt(i));
        }

        return result.toString();
    }

    /**
     * @param str string code
     * @return Нечетные числа в коде
     */
    @Override
    public String getOddNumbers(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i = i + 2) {
            result.append(str.charAt(i));
        }

        return result.toString();
    }

    /**
     * @param s string code
     * @return string without last number
     */
    @Override
    public String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }
}
