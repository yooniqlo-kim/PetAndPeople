package com.ssafy.petandpeople.common.exception.adoption;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.adoption.AdoptionErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ErrorResponseException extends AdoptionException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ErrorResponseException(String message) {
        super(message);
        this.errorCodeIfs = AdoptionErrorCode.ERROR_RESPONSE;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
