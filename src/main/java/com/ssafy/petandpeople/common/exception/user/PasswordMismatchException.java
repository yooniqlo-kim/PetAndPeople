package com.ssafy.petandpeople.common.exception.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.user.UserErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class PasswordMismatchException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public PasswordMismatchException() {
        this.errorCodeIfs = UserErrorCode.USER_PASSWORD_MISMATCH;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }
    
}
