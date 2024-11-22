package com.ssafy.petandpeople.common.error.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum PostErrorCode implements ErrorCodeIfs {

    POST_NOT_EXIST(400, 2001, "작성된 게시글이 없습니다."),
    POST_NOT_AUTHORIZED(400, 2002,"게시물 삭제/수정 권한이 없습니다."),
    INVALID_FILE_FORMAT(400, 2003, "유효하지 않은 파일타입 입니다. 이미지 파일을 올려주세요."),
    ORIGINAL_FILE_NAME_IS_EMPTY(400, 2004, "원본 파일이 이름이 비어있습니다."),
    INVALID_FILE_EXTENSION(400, 2005, "유효하지 않는 파일 확장자입니다.")
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
