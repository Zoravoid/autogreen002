package com.iucosoft.mylinksspringboot.util.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordGenerator {


    public static void main(String[] args) {

        String rawPassword = "1234";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);


    }
}
