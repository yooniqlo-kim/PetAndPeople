package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class EmailException extends RuntimeException implements ExceptionIfs {

    public EmailException() {
    }

    public EmailException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }

}
