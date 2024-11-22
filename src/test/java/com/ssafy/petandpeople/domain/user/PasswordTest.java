package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordTest {

    private final PasswordEncryptor passwordEncryptor;

    @Autowired
    public PasswordTest(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Test
    @DisplayName("비밀번호 검증 성공 - 일치")
    void validatePasswordMatch_성공() {
        String salt = "f47ac10b-58cc-4372-a567-0e02b2c3d479";
        String encryptedUserPassword = passwordEncryptor.encryptPassword("testPassword1234!", salt);

        Password userPassword = Password.from(encryptedUserPassword);

        String encryptedLoginPassword = passwordEncryptor.encryptPassword("testPassword1234!", salt);

        assertTrue(userPassword.validatePasswordMatch(encryptedLoginPassword));
    }

    @Test
    @DisplayName("비밀번호 검증 실패 - 불일치")
    void validatePasswordMatch_실패_PasswordMismatchException() {
        String salt = "f47ac10b-58cc-4372-a567-0e02b2c3d479";
        String encryptedUserPassword = passwordEncryptor.encryptPassword("testPassword1234!", salt);

        Password userPassword = Password.from(encryptedUserPassword);

        String encryptedLoginPassword = passwordEncryptor.encryptPassword("testPassword1234#", salt);

        assertThrows(PasswordMismatchException.class, () -> userPassword.validatePasswordMatch(encryptedLoginPassword));
    }

}
