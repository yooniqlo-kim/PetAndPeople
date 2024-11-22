package com.ssafy.petandpeople.common.exception.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.user.UserErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class InvalidSessionException extends UserException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public InvalidSessionException() {
        this.errorCodeIfs = UserErrorCode.INVALID_SESSION;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
