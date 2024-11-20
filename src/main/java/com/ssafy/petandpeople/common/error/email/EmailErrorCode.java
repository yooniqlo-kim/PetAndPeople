package com.ssafy.petandpeople.common.error.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum EmailErrorCode implements ErrorCodeIfs {

    AUTH_CODE_NOT_FOUND_IN_SESSION(400, 1501, "인증 번호를 다시 요청해주세요."),
    AUTH_CODE_MISMATCH(400, 1502, "인증 번호가 올바르지 않습니다. 확인 후 다시 입력해 주세요."),
    EMAIL_ALREADY_REGISTERED(400, 1503, "이미 사용된 아이디입니다. 다른 아이디를 입력해 주세요.")
    ;

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
