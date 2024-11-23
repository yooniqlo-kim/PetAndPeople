package com.ssafy.petandpeople.common.error.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum AbandonedAnimalErrorCode implements ErrorCodeIfs {

    DTO_MAPPING_FAIL(500, 3001, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."),
    EXTRACT_JSON_FAIL(500, 3003, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."),
    ERROR_RESPONSE(500, 3004, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    AbandonedAnimalErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
