package com.ssafy.petandpeople.common.error.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public enum ThumbnailErrorCode implements ErrorCodeIfs {

    THUMBNAIL_IMAGE_NOT_UPLOADED (500, 3000, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."),
    THUMBNAIL_IMAGE_NOT_SAVED (500, 3001, "일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요."),
    THUMBNAIL_NOT_EXISTED(500, 3002, "DB에 저장된 썸네일 이미지가 존재하지 않습니다."),
    THUMBNAIL_NOT_DELETED(500, 3003, "S3에 업로드 된 썸네일 이미지를 삭제하는데 실패했습니다.");
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;

    ThumbnailErrorCode(Integer httpStatusCode, Integer errorCode, String message) {
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
