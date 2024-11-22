package com.ssafy.petandpeople.common.error;

public enum ErrorCode implements ErrorCodeIfs{

    OK(200, 200, "성공"),

    SEVER_ERROR(500, 500, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요.");

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