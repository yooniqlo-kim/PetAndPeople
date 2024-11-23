package com.ssafy.petandpeople.common.exception.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.abandonedanimal.AbandonedAnimalErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class ExtractJsonFailException extends AbandonedAnimalException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public ExtractJsonFailException(String message) {
        super(message);
        this.errorCodeIfs = AbandonedAnimalErrorCode.EXTRACT_JSON_FAIL;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
