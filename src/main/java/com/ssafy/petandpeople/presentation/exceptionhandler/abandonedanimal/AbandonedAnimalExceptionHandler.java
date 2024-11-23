package com.ssafy.petandpeople.presentation.exceptionhandler.abandonedanimal;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.abandonedanimal.AbandonedAnimalException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class AbandonedAnimalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AbandonedAnimalExceptionHandler.class);

    @ExceptionHandler(value = AbandonedAnimalException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(AbandonedAnimalException abandonedAnimalException) {
        ErrorCodeIfs errorCodeIfs = abandonedAnimalException.getErrorCodeIfs();

        log.error("{} : {}", errorCodeIfs.getErrorCode(), errorCodeIfs.getMessage(), abandonedAnimalException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

}