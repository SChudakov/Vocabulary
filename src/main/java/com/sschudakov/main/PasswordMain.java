package com.sschudakov.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Locale;

public class PasswordMain {
    public static void main(String[] args) {
        /*String rawPassword = "password";
        String cryptedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(rawPassword + " : " + cryptedPassword);*/
        System.out.println(new Locale.Builder().setLanguage("ru").setRegion("EE").build());
    }
}
