package com.ssafy.petandpeople.common.exception.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.job.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class PostNotFoundException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public PostNotFoundException() {
        this.errorCodeIfs = PostErrorCode.POST_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}