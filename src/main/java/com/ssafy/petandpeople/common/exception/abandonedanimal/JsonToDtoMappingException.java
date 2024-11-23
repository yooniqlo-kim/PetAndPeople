package com.ssafy.petandpeople.common.exception.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.abandonedanimal.AbandonedAnimalErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class JsonToDtoMappingException extends AbandonedAnimalException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public JsonToDtoMappingException(String message) {
        super(message);
        this.errorCodeIfs = AbandonedAnimalErrorCode.DTO_MAPPING_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
