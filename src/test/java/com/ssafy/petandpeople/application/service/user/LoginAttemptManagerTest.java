package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.common.exception.user.LoginAttemptExceededException;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginAttemptManagerTest {

    private LoginAttemptManager loginAttemptManager;
    private ServletContext servletContext;

    @Autowired
    public LoginAttemptManagerTest(LoginAttemptManager loginAttemptManager, ServletContext servletContext) {
        this.loginAttemptManager = loginAttemptManager;
        this.servletContext = servletContext;
    }

    @BeforeEach
    void setUp() {
        servletContext = Mockito.mock(ServletContext.class);
        loginAttemptManager = new LoginAttemptManager(servletContext);

        Map<String, Integer> loginAttemptMap = new ConcurrentHashMap<>();
        when(servletContext.getAttribute("LOGIN_ATTEMPTS")).thenReturn(loginAttemptMap);
    }

    @Test
    @DisplayName("로그인 시도 횟수 증가 검증")
    void validateLoginAttempts_성공() {
        String userId = "testId";

        loginAttemptManager.validateLoginAttempts(userId);

        @SuppressWarnings("unchecked")
        Map<String, Integer> loginAttemptMap = (Map<String, Integer>) servletContext.getAttribute("LOGIN_ATTEMPTS");
        assertEquals(1, loginAttemptMap.get(userId));
    }

    @Test
    @DisplayName("로그인 시도 횟수 5회 초과 - 예외 발생")
    void validateLoginAttemptExceed_LoginAttemptExceededException() {
        String userId = "testId";

        for (int attempt = 1; attempt <= 5; attempt++) {
            loginAttemptManager.validateLoginAttempts(userId);
        }

        assertThrows(LoginAttemptExceededException.class, () -> {
            loginAttemptManager.validateLoginAttempts(userId);
        });
    }

    @Test
    @DisplayName("사용자의 로그인 시도 기록 초기화")
    void clearUserLoginAttempt() {
        String userId = "testId";

        loginAttemptManager.validateLoginAttempts(userId);

        loginAttemptManager.clearLoginAttempts(userId);

        @SuppressWarnings("unchecked")
        Map<String, Integer> loginAttemptMap = (Map<String, Integer>) servletContext.getAttribute("LOGIN_ATTEMPTS");
        assertFalse(loginAttemptMap.containsKey(userId));
    }

}
