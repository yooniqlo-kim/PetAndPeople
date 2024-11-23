package com.ssafy.petandpeople.common.exception.adoption;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class AdoptionException extends RuntimeException implements ExceptionIfs {

    public AdoptionException() {

    }

    public AdoptionException(String message) {
        super(message);
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return null;
    }

}
