package com.ssafy.petandpeople.common.error.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum PostErrorCode implements ErrorCodeIfs {

    POST_NOT_FOUND(400, 2000, "PostNotFoundException occurred");
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    PostErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
