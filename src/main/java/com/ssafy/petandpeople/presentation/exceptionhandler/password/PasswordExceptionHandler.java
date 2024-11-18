package com.ssafy.petandpeople.presentation.exceptionhandler.password;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.password.HashAlgorithmNotFoundException;
import com.ssafy.petandpeople.common.exception.password.NullHashBytesException;
import com.ssafy.petandpeople.presentation.exceptionhandler.user.UserExceptionHandler;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class PasswordExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PasswordExceptionHandler.class);

    @ExceptionHandler(value = HashAlgorithmNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(HashAlgorithmNotFoundException hashAlgorithmNotFoundException) {
        ErrorCodeIfs errorCodeIfs = hashAlgorithmNotFoundException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), hashAlgorithmNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = NullHashBytesException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(NullHashBytesException nullHashBytesException) {
        ErrorCodeIfs errorCodeIfs = nullHashBytesException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), nullHashBytesException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

}
