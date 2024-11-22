package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.email.EmailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AuthCodeMismatchException extends EmailException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public AuthCodeMismatchException() {
        this.errorCodeIfs = EmailErrorCode.AUTH_CODE_MISMATCH;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
