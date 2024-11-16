package com.ssafy.petandpeople.presentation.exceptionhandler.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
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
        String errorMessage =
                methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .get();

        log.error("MethodArgumentNotValidException occurred : {}", errorMessage);

        return ResponseEntity
                .status(400)
                .body(Api.ERROR(1001, errorMessage));
    }

}