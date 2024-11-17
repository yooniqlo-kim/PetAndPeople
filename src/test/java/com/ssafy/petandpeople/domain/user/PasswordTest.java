package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.NullHashBytesException;
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

        Password password = Password.encrypt(salt, Password.wrap(rawPassword));

        assertNotNull(password.getValue());
        assertNotEquals(rawPassword, password.getValue());
    }

    @Test
    @DisplayName("비밀번호 암호화 실패 - 암호화된 바이트 배열이 null일 때")
    void encrypt_실패_NullHashBytesException_null() {
        assertThrows(
                NullHashBytesException.class,
                () -> PasswordEncryptor.byteArrayToHex(null)
        );
    }

}
