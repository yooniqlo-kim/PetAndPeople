package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.email.EmailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AuthCodeInSessionNotFoundException extends EmailException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public AuthCodeInSessionNotFoundException() {
        this.errorCodeIfs = EmailErrorCode.AUTH_CODE_IN_SESSION_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
