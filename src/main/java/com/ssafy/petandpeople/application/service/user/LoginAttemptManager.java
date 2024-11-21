package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.common.exception.user.LoginAttemptExceededException;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginAttemptManager {

    private static final int MAX_ATTEMPT = 5;

    private final ServletContext servletContext;

    public LoginAttemptManager(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void validateLoginAttempts(String userId) {
        Map<String, Integer> loginAttemptMap = getLoginAttemptMap();

        int loginAttempt = loginAttemptMap.getOrDefault(userId, 0) + 1;
        validateLoginAttemptExceed(loginAttempt);

        loginAttemptMap.put(userId, loginAttempt);
    }

    private Map<String, Integer> getLoginAttemptMap() {
        @SuppressWarnings("unchecked")
        Map<String, Integer> loginAttemptMap = (Map<String, Integer>) servletContext.getAttribute("LOGIN_ATTEMPTS");

        if (loginAttemptMap == null) {
            loginAttemptMap = initializeLoginAttemptMap();
        }

        return loginAttemptMap;
    }

    private Map<String, Integer> initializeLoginAttemptMap() {
        Map<String, Integer> loginAttemptMap = new ConcurrentHashMap<>();
        servletContext.setAttribute("LOGIN_ATTEMPTS", loginAttemptMap);

        return loginAttemptMap;
    }

    private void validateLoginAttemptExceed(int loginAttempt) {
        if (loginAttempt > MAX_ATTEMPT) {
            throw new LoginAttemptExceededException();
        }
    }

    public void clearLoginAttempts(String userId) {
        Map<String, Integer> loginAttemptMap = getLoginAttemptMap();
        loginAttemptMap.remove(userId);
    }

}
