package com.ssafy.petandpeople.common.exception.user.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.user.UserErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;
import com.ssafy.petandpeople.common.exception.user.UserException;

public class PasswordMismatchException extends UserException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public PasswordMismatchException() {
        this.errorCodeIfs = UserErrorCode.USER_PASSWORD_MISMATCH;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }
    
}
