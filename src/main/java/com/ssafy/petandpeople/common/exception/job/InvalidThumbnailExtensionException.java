package com.ssafy.petandpeople.common.exception.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class InvalidThumbnailExtensionException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public InvalidThumbnailExtensionException() {
        this.errorCodeIfs = PostErrorCode.INVALID_FILE_EXTENSION;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
