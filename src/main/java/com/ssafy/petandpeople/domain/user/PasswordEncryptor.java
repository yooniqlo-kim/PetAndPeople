package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.password.HashAlgorithmNotFoundException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PasswordEncryptor {

    public String encryptPassword(String rawPassword, String salt) {
        byte[] hashedPassword = hashPassword(rawPassword, salt);
        return convertHashToHexString(hashedPassword);
    }

    private byte[] hashPassword(String rawPassword, String salt) {
        try {
            return generateHash(rawPassword, salt);
        } catch (Exception e) {
            throw new HashAlgorithmNotFoundException(e.getMessage());
        }
    }

    private byte[] generateHash(String rawPassword, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(rawPassword.getBytes());
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }

    private String convertHashToHexString(byte[] hashedPassword) {
        return IntStream.range(0, hashedPassword.length)
                .mapToObj(index -> String.format("%02x", hashedPassword[index]))
                .collect(Collectors.joining());
    }

}
