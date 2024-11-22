package com.ssafy.petandpeople.common.exception.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.error.email.EmailErrorCode;
import com.ssafy.petandpeople.common.exception.ExceptionIfs;

public class EmailAlreadyRegisteredException extends EmailException implements ExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;

    public EmailAlreadyRegisteredException() {
        this.errorCodeIfs = EmailErrorCode.EMAIL_ALREADY_REGISTERED;
    }

    @Override
    public ErrorCodeIfs getErrorCodeIfs() {
        return errorCodeIfs;
    }

}
