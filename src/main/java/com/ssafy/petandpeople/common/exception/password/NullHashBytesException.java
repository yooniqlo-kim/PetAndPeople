package com.ssafy.petandpeople.common.exception.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.password.PasswordErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class NullHashBytesException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public NullHashBytesException() {
        this.errorCodeIfs = PasswordErrorCode.NULL_HASH_BYTES;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
