package com.ssafy.petandpeople.common.error.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum PostErrorCode implements ErrorCodeIfs {

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    PostErrorCode(Integer httpStatusCode, Integer errorCode, String description) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.description = description;
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
    public String getDescription() {
        return this.description;
    }

}
