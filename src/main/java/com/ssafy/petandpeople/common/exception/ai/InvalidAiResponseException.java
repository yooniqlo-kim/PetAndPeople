package com.ssafy.petandpeople.common.exception.ai;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.ai.AiErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;
import com.ssafy.petandpeople.common.exception.adoption.AdoptionException;

public class InvalidAiResponseException extends AdoptionException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public InvalidAiResponseException() {
        this.errorCodeIfs = AiErrorCode.INVALID_AI_RESPONSE;
    }

    public InvalidAiResponseException(String message) {
        super(message);
        this.errorCodeIfs = AiErrorCode.INVALID_AI_RESPONSE;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
