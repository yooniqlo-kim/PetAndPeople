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

    public static Password encrypt(String salt, Password rawPassword) {
        byte[] hashPassword = PasswordEncryptor.getSHA256(salt, rawPassword.getValue());
        String encryptValue = PasswordEncryptor.byteArrayToHex(hashPassword);

        return new Password(encryptValue);
    }

    public static Boolean validate(Password password, String storedPassword) {
        String encryptLoginPassword = password.getValue();

        if(!encryptLoginPassword.equals(storedPassword)) {
            throw new PasswordMismatchException();
        }

        return true;
    }

}
