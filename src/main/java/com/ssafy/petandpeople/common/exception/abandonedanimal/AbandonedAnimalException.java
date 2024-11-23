package com.ssafy.petandpeople.common.exception.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AbandonedAnimalException extends RuntimeException implements ExceptionIfs {

    public AbandonedAnimalException() {

    }

    public AbandonedAnimalException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }

}
