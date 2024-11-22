package com.ssafy.petandpeople.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class PasswordEncryptorTest {

    private final PasswordEncryptor passwordEncryptor;

    @Autowired
    public PasswordEncryptorTest(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Test
    @DisplayName("비밀번호 암호화 성공")
    void encrypt_성공() {
        String rawPassword = "testPassword1234!";
        String salt = "f47ac10b-58cc-4372-a567-0e02b2c3d479";

        String encryptedPassword = passwordEncryptor.encryptPassword(rawPassword, salt);

        assertNotEquals(rawPassword, encryptedPassword);
        assertEquals("829869ab87eef8fe5207a1b388843bb2ae4567a6a7966a2eabf5b3524469615f", encryptedPassword);
    }

}
