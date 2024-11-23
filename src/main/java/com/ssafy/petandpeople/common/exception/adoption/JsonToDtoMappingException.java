package com.ssafy.petandpeople.common.exception.adoption;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.adoption.AdoptionErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class JsonToDtoMappingException extends AdoptionException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public JsonToDtoMappingException(String message) {
        super(message);
        this.errorCodeIfs = AdoptionErrorCode.DTO_MAPPING_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
