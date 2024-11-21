package com.ssafy.petandpeople.common.utils;

import com.ssafy.petandpeople.common.exception.user.password.HashAlgorithmNotFoundException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordEncryptor {

    public static String generateSalt() {
        return UUIDGenerator.generateUUIDtoString();
    }

    public static byte[] hashWithSHA256(String source, String salt) {
        try {
            return generateHash("SHA-256",source, salt);
        } catch(Exception e) {
            throw new HashAlgorithmNotFoundException(e.getMessage());
        }
    }

    public static byte[] generateHash(String algorithm, String source, String salt) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(source.getBytes());
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }

    public static String generateEncryptedPassword(byte[] hashBytes) {
        return IntStream.range(0, hashBytes.length)
                .mapToObj(i -> String.format("%02x", hashBytes[i]))
                .collect(Collectors.joining());
    }

}
