package com.ssafy.petandpeople.common.exception.post;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.ThumbnailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ThumbnailNotSavedException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ThumbnailNotSavedException(String errorMessage) {
        super(errorMessage);
        this.errorCodeIfs = ThumbnailErrorCode.THUMBNAIL_IMAGE_NOT_SAVED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}