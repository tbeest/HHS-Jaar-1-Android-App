package com.example.gr11today;

public class Validate {

    public static boolean validateInputStringNotNullNotEmpty(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false;
        }
        return true;
    }

/*    public static Boolean validateInputString (String input) {
        if (input.isEmpty()){
            return false;
        }
        return true;
    }*/

/*    public static Boolean allFieldsEmpty(String username, String password, String validatePassword) {
        if (username.isEmpty() || password.isEmpty() || validatePassword.isEmpty()) {
            return true;
        }
        return false;
    }*/

    public static boolean checkIfEqual(String password, String validatePassword) {
        if (password.equals(validatePassword) && password.isEmpty() && validatePassword.isEmpty() && password == null && validatePassword == null) {
            return false;
        }
        return true;
    }

    public static boolean checkRegistrationLengthField(String String) {
        if (String.length() >= 5) {
            return true;
        }
        return false;
    }

    public static boolean checkFieldNotNull(String input) {
        if (input == null) {
            return false;
        }
        return true;
    }

    public static boolean inputValidString(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if(!java.lang.Character.isAlphabetic(c) && c != ' ') {
                return false;
            }
        }
    return true;
    }
}
