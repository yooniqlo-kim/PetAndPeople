package com.ssafy.petandpeople.common.exception.user.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.password.PasswordErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;
import com.ssafy.petandpeople.common.exception.user.UserException;

public class HashAlgorithmNotFoundException extends UserException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public HashAlgorithmNotFoundException(String message) {
        super(message);
        this.errorCodeIfs = PasswordErrorCode.HASH_ALGORITHM_NOT_FOUND;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
