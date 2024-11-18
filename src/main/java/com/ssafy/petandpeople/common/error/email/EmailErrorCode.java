package com.ssafy.petandpeople.common.error.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum EmailErrorCode implements ErrorCodeIfs {

    STORED_AUTH_CODE_NOT_FOUND(400, 1501, "StoredAuthCodeNotFoundException occurred"),
    AUTH_CODE_MISMATCH(400, 1502, "AuthCodeMismatchException occurred");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    EmailErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
