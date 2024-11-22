package com.ssafy.petandpeople.common.exception.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class OriginalFileNameIsEmptyException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public OriginalFileNameIsEmptyException() {
        this.errorCodeIfs = PostErrorCode.ORIGINAL_FILE_NAME_IS_EMPTY;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
