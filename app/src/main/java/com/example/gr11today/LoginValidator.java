package com.example.gr11today;

public class LoginValidator {

    public static boolean validateInputStringNotNullNotEmpty(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean checkIfEqual(String password, String validatePassword) {
        if (password.equals(validatePassword) && !password.isEmpty() && !validatePassword.isEmpty() && password != null && validatePassword != null) {
            return true;
        }
        return false;
    }

    public static boolean checkRegistrationLengthField(String String) {
        if (String.length() >= 5) {
            return true;
        }
        return false;
    }
}
