package com.example.gr11today;

import com.example.gr11today.models.User;

public class Validate {

    public static Boolean validateInput(String input) {
        if (input.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Boolean allFieldsEmpty(String input1, String input2, String input3) {
        if (input1.isEmpty() || input2.isEmpty() || input3.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Boolean checkIfEqual(String input1, String input2) {
        if (input1.equals(input2)) {
            return true;
        }
        return false;
    }
}
