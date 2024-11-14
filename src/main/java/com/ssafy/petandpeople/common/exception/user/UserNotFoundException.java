package com.ssafy.petandpeople.common.exception.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.user.UserErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class UserNotFoundException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public UserNotFoundException() {
        this.errorCodeIfs = UserErrorCode.USER_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
