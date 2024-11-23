package com.ssafy.petandpeople.common.exception.post;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.ThumbnailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ThumbnailNotExistedException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ThumbnailNotExistedException() {
        this.errorCodeIfs = ThumbnailErrorCode.THUMBNAIL_NOT_EXISTED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}