package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.password.PasswordMismatchException;

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
