package com.ssafy.petandpeople.common.exception.ai;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AiException extends RuntimeException implements ExceptionIfs {

    public AiException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }

}
