package com.hse.organizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.hse.organizer.modules.implementation.DrugCodeValidatorImplementation;

public class DrugCodeValidatorTests {
    DrugCodeValidatorImplementation validator = new DrugCodeValidatorImplementation();
    String sample = "3282770072815";

    @Test
    void getEvenNumbersTest(){
        String result = validator.getEvenNumbers(validator.removeLastChar(sample));
        Assertions.assertEquals(result, "227021");
    }

    @Test
    void getOddNumbersTest(){
        String result = validator.getOddNumbers(validator.removeLastChar(sample));
        Assertions.assertEquals(result, "387078");
    }

    @Test
    void validateTest(){
        Assertions.assertTrue(validator.validate(sample));
    }
}
