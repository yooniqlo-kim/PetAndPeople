package com.ssafy.petandpeople.common.exception.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class InvalidFileFormatException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public InvalidFileFormatException() {
        this.errorCodeIfs = PostErrorCode.INVALID_FILE_FORMAT;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
