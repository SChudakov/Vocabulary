package com.sschudakov.ui.controller;

public class InputChecker {

    private static final String REGEX_FOR_STRING_INPUT = "[a-zA-Zа-яА-Я]+";

    public void checkMinNumOfMeanings(int number) {
        ensureNumberNonNegative(number);
    }

    public void checkNumOfMeanings(int number) {
        ensureNumberNonNegative(number);
    }

    private void ensureNumberNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("A number should be non-negative");
        }
    }

    public void checkStringInput(String input) {
        if (!input.matches(REGEX_FOR_STRING_INPUT)) {
            throw new IllegalArgumentException("Input string has illegal format");
        }
    }
}
