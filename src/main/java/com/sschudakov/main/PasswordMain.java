package com.sschudakov.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordMain {
    public static void main(String[] args) {
        String rawPassword = "password";
        String cryptedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(rawPassword + " : " + cryptedPassword);
    }
}
