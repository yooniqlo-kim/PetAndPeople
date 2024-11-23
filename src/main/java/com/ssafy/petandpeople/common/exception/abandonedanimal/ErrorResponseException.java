package com.ssafy.petandpeople.common.exception.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.abandonedanimal.AbandonedAnimalErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ErrorResponseException extends AbandonedAnimalException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ErrorResponseException(String message) {
        super(message);
        this.errorCodeIfs = AbandonedAnimalErrorCode.ERROR_RESPONSE;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
