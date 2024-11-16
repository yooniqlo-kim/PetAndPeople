package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.utils.PasswordEncryptor;

public class Password {

    private String value;

    private Password(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Password encrypt(String salt, String rawPassword) {
        byte[] hashPassword = PasswordEncryptor.getSHA256(salt, rawPassword);
        String encryptValue = PasswordEncryptor.byteArrayToHex(hashPassword);

        return new Password(encryptValue);
    }

}
