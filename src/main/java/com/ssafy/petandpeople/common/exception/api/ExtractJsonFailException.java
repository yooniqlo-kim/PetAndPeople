package com.ssafy.petandpeople.common.exception.api;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.api.ApiErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ExtractJsonFailException extends ApiException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ExtractJsonFailException(String message) {
        super(message);
        this.errorCodeIfs = ApiErrorCode.EXTRACT_JSON_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
