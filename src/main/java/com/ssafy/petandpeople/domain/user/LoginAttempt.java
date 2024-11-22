package com.ssafy.petandpeople.domain.user;

import com.ssafy.petandpeople.common.exception.user.LoginAttemptExceededException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
@Scope("application")
public class LoginAttempt {

    private static final int MAX_ATTEMPT = 5;
    private static final long LOCK_TIME_DURATION = 60 * 60 * 1000;

    private final Map<String, Integer> usersLoginAttempt = new ConcurrentHashMap<>();
    private final Map<String, Long> usersLockTime = new ConcurrentHashMap<>();

    public boolean validateUserLock(String userId) {
        Long lockStartTime = usersLockTime.get(userId);

        if(lockStartTime != null) {
            validateLockTime(lockStartTime);
            usersLockTime.remove(userId);
        }

        return true;
    }

    public int increaseLoginAttempt(String userId) {
        int currentAttempt = usersLoginAttempt.getOrDefault(userId, 0);
        usersLoginAttempt.put(userId, currentAttempt + 1);

        validateExceedMaxAttempt(userId);

        return usersLoginAttempt.get(userId);
    }

    public void resetLoginAttempt(String userId) {
        usersLoginAttempt.remove(userId);
        usersLockTime.remove(userId);
    }

    private void validateLockTime(Long lockStartTime) {
        if(System.currentTimeMillis() - lockStartTime < LOCK_TIME_DURATION) {
            throw new LoginAttemptExceededException();
        }
    }

    private void validateExceedMaxAttempt(String userId) {
        int currentAttempt = usersLoginAttempt.getOrDefault(userId, 0);
        if (currentAttempt >= MAX_ATTEMPT) {
            lockUserAccount(userId);
        }
    }

    private void lockUserAccount(String userId) {
        usersLockTime.put(userId, System.currentTimeMillis());
    }

}
