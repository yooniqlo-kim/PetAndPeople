package com.ssafy.petandpeople.common.exception.adoption;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.adoption.AdoptionErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ExtractJsonFailException extends AdoptionException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ExtractJsonFailException(String message) {
        super(message);
        this.errorCodeIfs = AdoptionErrorCode.EXTRACT_JSON_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
