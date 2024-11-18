package com.ssafy.petandpeople.common.exception.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.password.PasswordErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class HashAlgorithmNotFoundException extends RuntimeException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public HashAlgorithmNotFoundException() {
        this.errorCodeIfs = PasswordErrorCode.HASH_ALGORITHM_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
