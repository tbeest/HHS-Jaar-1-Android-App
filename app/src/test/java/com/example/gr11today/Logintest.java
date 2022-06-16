package com.example.gr11today;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Logintest {

    //velden leeg
    @Test
    public void checkIfUsernameNotEmpty() {
        //ARRANGE
        Validate validate = new Validate();
        String username = "";

        //ACT
        boolean result = validate.validateInput(username);

        //ASSERT
        boolean expected = false;
        assertEquals(expected,result);
    }

    @Test
    public void checkIfUsernameNotNull() {
        //ARRANGE
        Validate validate = new Validate();
        String username = null;

    }

}
