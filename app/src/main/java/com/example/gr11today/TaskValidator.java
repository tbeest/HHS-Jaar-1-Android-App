package com.example.gr11today;

public class TaskValidator {

    public boolean stringNotEmpty(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }else {
            return true;
        }
    }
}
