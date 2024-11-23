package com.ssafy.petandpeople.common.error.ai;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum AiErrorCode implements ErrorCodeIfs {

    API_REQUEST_FAILED(500, 4001, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."),
    INVALID_AI_RESPONSE(500, 4002, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    AiErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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