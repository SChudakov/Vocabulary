package com.sschudakov.desktop.fx.controller;

public class NumberConverter {
    public int convertNumber(String number) {
        int result;
        try {
            result = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input number " + number + " has illegal format", e);
        }
        return result;
    }
}
