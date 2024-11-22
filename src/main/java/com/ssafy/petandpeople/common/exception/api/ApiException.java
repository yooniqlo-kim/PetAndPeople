package com.ssafy.petandpeople.common.exception.api;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ApiException extends RuntimeException implements ExceptionIfs {

    public ApiException() {

    }

    public ApiException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }

}
