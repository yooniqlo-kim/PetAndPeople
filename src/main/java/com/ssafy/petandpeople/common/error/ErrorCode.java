package com.ssafy.petandpeople.common.error;

public enum ErrorCode implements ErrorCodeIfs {

    BAD_REQUEST(400, 400, "잘못된 요청"),

    SEVER_ERROR(500, 500, "서버 에러");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    ErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
