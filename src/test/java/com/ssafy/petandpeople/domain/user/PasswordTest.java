package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.utils.PasswordEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordTest {

    @Test
    @DisplayName("Password 클래스 wrapping 성공")
    void wrap_성공() {
        String rawPassword = "SecurePassword123!";
        Password password = Password.wrap(rawPassword);

        assertEquals(rawPassword, password.getValue());
    }

    @Test
    @DisplayName("비밀번호 암호화 성공")
    void encrypt_성공() {
        String salt = PasswordEncryptor.generateSalt();
        String rawPassword = "SecurePassword123!";

        Password password = Password.encrypt(salt,Password.wrap(rawPassword));

        assertNotNull(password.getValue());
        assertNotEquals(rawPassword, password.getValue());
    }

    @Test
    @DisplayName("비밀번호 암호화 실패 - 다른 salt 값 사용")
    void encrypt_실패_differentSalt() {
        String salt1 = "salt1";
        String salt2 = "salt2";
        String rawPassword = "SecurePassword123!";

        Password password1 = Password.encrypt(salt1,Password.wrap(rawPassword));
        Password password2 = Password.encrypt(salt2,Password.wrap(rawPassword));

        assertNotEquals(password1.getValue(), password2.getValue());
    }

}
