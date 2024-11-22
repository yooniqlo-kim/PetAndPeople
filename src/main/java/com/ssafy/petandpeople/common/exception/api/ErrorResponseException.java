package com.ssafy.petandpeople.common.exception.api;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.api.ApiErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ErrorResponseException extends ApiException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ErrorResponseException(String message) {
        super(message);
        this.errorCodeIfs = ApiErrorCode.INVALID_RESPONSE;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
