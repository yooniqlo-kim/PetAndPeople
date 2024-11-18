package com.ssafy.petandpeople.presentation.exceptionhandler.user;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.user.*;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class UserExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        log.error("MethodArgumentNotValidException occurred : {}", errorMessage);

        return ResponseEntity
                .status(400)
                .body(Api.ERROR(1000, errorMessage));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(UserNotFoundException userNotFoundException) {
        ErrorCodeIfs errorCodeIfs = userNotFoundException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), userNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = UserNotSignUpException.class)
    public ResponseEntity<Api<Object>> UserNotSignUpException(UserNotSignUpException userNotSignUpException) {
        ErrorCodeIfs errorCodeIfs = userNotSignUpException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), userNotSignUpException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = SaltNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(SaltNotFoundException saltNotFoundException) {
        ErrorCodeIfs errorCodeIfs = saltNotFoundException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), saltNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = LoginAttemptExceededException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(LoginAttemptExceededException loginAttemptExceededException) {
        ErrorCodeIfs errorCodeIfs = loginAttemptExceededException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), loginAttemptExceededException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = PasswordMismatchException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(PasswordMismatchException passwordMismatchException) {
        ErrorCodeIfs errorCodeIfs = passwordMismatchException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), passwordMismatchException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

}