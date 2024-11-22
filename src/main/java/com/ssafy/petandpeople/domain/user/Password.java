package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import com.ssafy.petandpeople.common.exception.user.password.HashAlgorithmNotFoundException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Password {

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password from(String encryptedPassword) {
        return new Password(encryptedPassword);
    }

    public String getValue() {
        return value;
    }

    public Boolean validatePasswordMatch(String encryptedLoginPassword) {
        if (!value.equals(encryptedLoginPassword)) {
            throw new PasswordMismatchException();
        }
        return true;
    }

}
