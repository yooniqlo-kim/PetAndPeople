package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import com.ssafy.petandpeople.common.utils.PasswordEncryptor;

public class Password {

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Password wrap(String rawPassword) {
        return new Password(rawPassword);
    }

    public String encrypt(String salt) {
        byte[] hashedPassword = PasswordEncryptor.hashWithSHA256(this.value, salt);

        return PasswordEncryptor.generateEncryptedPassword(hashedPassword);
    }

    public Boolean validate(String loginUserPassword) {
        if (!this.value.equals(loginUserPassword)) {
            throw new PasswordMismatchException();
        }

        return true;
    }

}
