package com.ssafy.petandpeople.presentation.exceptionhandler.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.password.HashAlgorithmNotFoundException;
import com.ssafy.petandpeople.common.exception.password.NullHashBytesException;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class UserExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(UserNotFoundException userNotFoundException) {
        ErrorCodeIfs errorCodeIfs = userNotFoundException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), userNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        log.error("MethodArgumentNotValidException occurred : {}", errorMessage);

        return ResponseEntity
                .status(400)
                .body(Api.ERROR(1001, errorMessage));
    }

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