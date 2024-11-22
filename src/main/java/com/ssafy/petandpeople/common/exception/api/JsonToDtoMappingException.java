package com.ssafy.petandpeople.common.exception.api;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.api.ApiErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class JsonToDtoMappingException extends ApiException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public JsonToDtoMappingException(String message) {
        super(message);
        this.errorCodeIfs = ApiErrorCode.DTO_MAPPING_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
