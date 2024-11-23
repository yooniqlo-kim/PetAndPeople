package com.ssafy.petandpeople.common.exception.post;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.post.PostErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class PostNotAuthorizedException extends PostException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public PostNotAuthorizedException() {
        this.errorCodeIfs = PostErrorCode.POST_NOT_AUTHORIZED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}