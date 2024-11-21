package com.ssafy.petandpeople.common.exception.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.ThumbnailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ThumbnailNotDeletedException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ThumbnailNotDeletedException(String errorMessage) {
        super(errorMessage);
        this.errorCodeIfs = ThumbnailErrorCode.THUMBNAIL_NOT_DELETED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}