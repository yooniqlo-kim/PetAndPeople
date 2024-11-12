package com.ssafy.petandpeople.common.error;

public enum ErrorCode implements ErrorCodeIfs{

    OK(200, 200, "성공"),

    BAD_REQUEST(400, 400, "잘못된 요청"),

    SEVER_ERROR(500, 500, "서버 에러");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCode(Integer httpStatusCode, Integer errorCode, String description) {
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
