package com.ssafy.petandpeople.common.error.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(400, 1000, "UserNotFoundException occurred");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    UserErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
