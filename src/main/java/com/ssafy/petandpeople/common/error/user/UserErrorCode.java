package com.ssafy.petandpeople.common.error.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(400, 1001, "가입되지 않은 사용자입니다. 다시 확인해주세요."),
    USER_LOGIN_ATTEMPT_EXCEEDED(400, 1002, "로그인 시도 횟수가 5회 초과되었습니다. 이메일 인증을 진행해주세요."),
    USER_PASSWORD_MISMATCH(400, 1003, "비밀번호가 일치하지 않습니다."),
    INVALID_SESSION(400, 1004, "유효하지 않은 요청입니다.");

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