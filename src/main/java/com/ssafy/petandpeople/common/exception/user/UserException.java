package com.ssafy.petandpeople.common.exception.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class UserException extends RuntimeException implements ExceptionIfs {

    public UserException() {

    }

    public UserException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }
}
