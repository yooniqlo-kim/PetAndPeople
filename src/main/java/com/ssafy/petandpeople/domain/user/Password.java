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

    public Password encrypt(String salt) {
        byte[] hashPassword = PasswordEncryptor.getSHA256(salt, this.getValue());
        String encryptValue = PasswordEncryptor.byteArrayToHex(hashPassword);

        return new Password(encryptValue);
    }

    public Boolean validate(String savedPassword) {
        if(!this.getValue().equals(savedPassword)) {
            throw new PasswordMismatchException();
        }

        return true;
    }

}
