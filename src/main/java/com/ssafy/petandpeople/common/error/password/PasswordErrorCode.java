package com.ssafy.petandpeople.common.error.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum PasswordErrorCode implements ErrorCodeIfs {

    HASH_ALGORITHM_NOT_FOUND(500, 1200, "HashAlgorithmNotFoundException occurred");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    PasswordErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
