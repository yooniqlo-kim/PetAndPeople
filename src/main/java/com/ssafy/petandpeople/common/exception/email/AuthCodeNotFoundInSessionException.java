package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.email.EmailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AuthCodeNotFoundInSessionException extends EmailException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public AuthCodeNotFoundInSessionException() {
        this.errorCodeIfs = EmailErrorCode.AUTH_CODE_NOT_FOUND_IN_SESSION;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }
}
