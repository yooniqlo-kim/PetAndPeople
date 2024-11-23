package com.ssafy.petandpeople.common.exception.ai;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.ai.AiErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;
import com.ssafy.petandpeople.common.exception.adoption.AdoptionException;

public class AdoptionRequestFailedException extends AdoptionException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public AdoptionRequestFailedException(String message) {
        super(message);
        this.errorCodeIfs = AiErrorCode.API_REQUEST_FAILED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
