package com.example.gr11today;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Logintest {

    //velden leeg
    @Test
    public void checkIfAnyGivenStringIsCorrect() {
        //ARRANGE
        //E.g. username
        LoginValidator loginValidator = new LoginValidator();
        String username = "test";

        //ACT
        boolean result = loginValidator.validateInputStringNotNullNotEmpty(username);

        //ASSERT
        boolean expected = true;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfAnyGivenStringNotEmpty() {
        //ARRANGE
        //E.g. username
        LoginValidator loginValidator = new LoginValidator();
        String username = "";

        //ACT
        boolean result = loginValidator.validateInputStringNotNullNotEmpty(username);

        //ASSERT
        boolean expected = false;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfAnyGivenStringNotNull() {
        //ARRANGE
        //E.g. username
        LoginValidator loginValidator = new LoginValidator();
        String testStringNull = null;

        //ACT
        boolean result = loginValidator.validateInputStringNotNullNotEmpty(testStringNull);

        //ASSERT
        boolean expected = false;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfPasswordsAreEqual() {
        //ARRANGE
        LoginValidator loginValidator = new LoginValidator();
        String TestPassword1 = "correct";
        String TestPassword2 = "correct";

        //ACT
        boolean result = loginValidator.checkIfEqual(TestPassword1, TestPassword2);

        //ASSERT
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void checkIfPasswordsAreNotEqual() {
        //ARRANGE
        LoginValidator loginValidator = new LoginValidator();
        String TestPassword1 = "correct";
        String TestPassword2 = "incorrect";

        //ACT
        boolean result = loginValidator.checkIfEqual(TestPassword1, TestPassword2);

        //ASSERT
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void checkInputStringLengthNotHigherOrEqualToFive() {
        //ARRANGE
        //E.g. username
        LoginValidator loginValidator = new LoginValidator();
        String TestString = "Kees";

        //ACT
        boolean result = loginValidator.checkRegistrationLengthField(TestString);

        //ASSERT
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void checkInputStringLengthHigherOrEqualToFive() {
        //ARRANGE
        //E.g. username
        LoginValidator loginValidator = new LoginValidator();
        String TestString = "Karel";

        //ACT
        boolean result = loginValidator.checkRegistrationLengthField(TestString);

        //ASSERT
        boolean expected = true;
        assertEquals(expected, result);
    }
}
