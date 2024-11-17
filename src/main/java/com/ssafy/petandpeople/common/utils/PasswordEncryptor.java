package com.ssafy.petandpeople.common.utils;

import com.ssafy.petandpeople.common.exception.user.HashAlgorithmNotFoundException;
import com.ssafy.petandpeople.common.exception.user.NullHashBytesException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordEncryptor {

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public static byte[] getSHA256(String source, String salt) {
        try {
            return generateHash("SHA-256",source, salt);
        } catch(NoSuchAlgorithmException e) {
            throw new HashAlgorithmNotFoundException();
        }
    }

    public static byte[] generateHash(String algorithm, String source, String salt) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(source.getBytes());
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }

    public static String byteArrayToHex(byte[] hashBytes) {
        validateHashBytes(hashBytes);

        return IntStream.range(0, hashBytes.length)
                .mapToObj(i -> String.format("%02x", hashBytes[i]))
                .collect(Collectors.joining());
    }

    private static void validateHashBytes(byte[] hashBytes) {
        if (hashBytes == null || hashBytes.length == 0) {
            throw new NullHashBytesException();
        }
    }

}
