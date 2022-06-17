package com.example.gr11today;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Logintest {

    //velden leeg
    @Test
    public void checkIfAnyGivenStringNotEmpty() {
        //ARRANGE
        //E.g. username
        Validate validate = new Validate();
        String username = "";

        //ACT
        boolean result = validate.validateInputStringNotNullNotEmpty(username);

        //ASSERT
        boolean expected = false;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfAnyGivenStringNotNull() {
        //ARRANGE
        //E.g. username
        Validate validate = new Validate();
        String testStringNull = null;

        //ACT
        boolean result = validate.validateInputStringNotNullNotEmpty(testStringNull);

        //ASSERT
        boolean expected = false;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfPasswordsAreEqual() {
        //ARRANGE
        Validate validate = new Validate();
        String TestPassword1 = "correct";
        String TestPassword2 = "correct";

        //ACT
        boolean result = validate.checkIfEqual(TestPassword1, TestPassword2);

        //ASSERT
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void checkIfPasswordsAreNotEqual() {
        //ARRANGE
        Validate validate = new Validate();
        String TestPassword1 = "correct";
        String TestPassword2 = "incorrect";

        //ACT
        boolean result = validate.checkIfEqual(TestPassword1, TestPassword2);

        //ASSERT
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void checkIfPasswordsOneIsNull() {
        //ARRANGE
        Validate validate = new Validate();
        String TestPassword1 = "correct";
        String TestPassword2 = null;

        //ACT
        boolean result = validate.checkIfEqual(TestPassword1, TestPassword2);

        //ASSERT
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void checkInputStringLengthNotHigherOrEqualToFive() {
        //ARRANGE
        //E.g. username
        Validate validate = new Validate();
        String TestString = "Kees";

        //ACT
        boolean result = validate.checkRegistrationLengthField(TestString);

        //ASSERT
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void checkInputStringLengthHigherOrEqualToFive() {
        //ARRANGE
        //E.g. username
        Validate validate = new Validate();
        String TestString = "Karel";

        //ACT
        boolean result = validate.checkRegistrationLengthField(TestString);

        //ASSERT
        boolean expected = true;
        assertEquals(expected, result);
    }
}
