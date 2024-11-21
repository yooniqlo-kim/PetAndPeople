package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import com.ssafy.petandpeople.common.utils.PasswordEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordTest {

    @Test
    @DisplayName("비밀번호 암호화 성공")
    void encrypt_성공() {
        String salt = PasswordEncryptor.generateSalt();
        String rawPassword = "SecurePassword123!";
        Password password = Password.wrap(rawPassword);
        String encryptedPassword = password.encrypt(salt);

        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    @DisplayName("비밀번호 검증 실패 - 불일치")
    void validate_실패_PasswordMismatchException() {
        String rawPassword = "WrongPassword";
        Password wrongPassword = Password.wrap(rawPassword);

        String savedEncryptedPassword = "SecurePassword123!";

        assertThrows(
                PasswordMismatchException.class,
                () -> wrongPassword.validate(savedEncryptedPassword)
        );
    }

}
