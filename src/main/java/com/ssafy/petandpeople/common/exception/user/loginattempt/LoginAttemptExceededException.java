package com.ssafy.petandpeople.common.exception.user.loginattempt;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.user.UserErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;
import com.ssafy.petandpeople.common.exception.user.UserException;

public class LoginAttemptExceededException extends UserException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public LoginAttemptExceededException() {
        this.errorCodeIfs = UserErrorCode.USER_LOGIN_ATTEMPT_EXCEEDED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
