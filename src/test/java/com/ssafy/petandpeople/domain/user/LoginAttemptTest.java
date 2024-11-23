package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.loginattempt.LoginAttemptExceededException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LoginAttemptTest {

    private static final String testUserId = "testId";

    private final LoginAttempt loginAttempt;

    @Autowired
    public LoginAttemptTest(LoginAttempt loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    @AfterEach
    void setDown() {
        loginAttempt.resetLoginAttempt(testUserId);
    }

    @Test
    @DisplayName("유저의 계정이 잠기지 않음")
    void validateUserLock_성공() {
        assertFalse(loginAttempt.validateUserLock(testUserId));
    }

    @Test
    @DisplayName("유저의 계정이 잠김")
    void validateUserLock_실패_LoginAttemptExceededException() {
        for (int count = 1; count <= 5; count++) {
            loginAttempt.increaseLoginAttempt(testUserId);
        }
        assertThrows(LoginAttemptExceededException.class, () -> loginAttempt.validateUserLock(testUserId));
    }

    @Test
    @DisplayName("로그인 시도 횟수 증가")
    void increaseLoginAttempt() {
        assertEquals(1, loginAttempt.increaseLoginAttempt(testUserId));
    }

}
