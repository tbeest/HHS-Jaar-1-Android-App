package com.example.gr11today;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.gr11today.models.Task;


public class TaskTest {
    @Test
    public void checkIfEmptyNull() {
        TaskValidator tv = new TaskValidator();

        String testStringNull = null;

        boolean result = tv.stringNotEmpty(testStringNull);

        assertFalse(result);
    }

    @Test
    public void checkIfEmptyEmpty() {
        TaskValidator tv = new TaskValidator();

        String testStringEmpty = "";

        boolean result = tv.stringNotEmpty(testStringEmpty);

        assertFalse(result);
    }

    @Test
    public void checkIfEmptyNot() {
        TaskValidator tv = new TaskValidator();

        String testStringFilled = "Test";

        boolean result = tv.stringNotEmpty(testStringFilled);

        assertTrue(result);
    }
}
