package com.ssafy.petandpeople.common.exception.post;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.post.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class OriginalFileNameEmptyException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public OriginalFileNameEmptyException() {
        this.errorCodeIfs = PostErrorCode.ORIGINAL_FILE_NAME_EMPTY;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}