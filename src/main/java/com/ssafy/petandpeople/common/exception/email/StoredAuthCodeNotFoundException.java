package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.email.EmailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class StoredAuthCodeNotFoundException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public StoredAuthCodeNotFoundException() {
        this.errorCodeIfs = EmailErrorCode.STORED_AUTH_CODE_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }
}
