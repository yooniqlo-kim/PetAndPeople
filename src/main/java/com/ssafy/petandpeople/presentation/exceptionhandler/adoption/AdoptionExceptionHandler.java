package com.ssafy.petandpeople.presentation.exceptionhandler.adoption;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.adoption.AdoptionException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class AdoptionExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AdoptionExceptionHandler.class);

    @ExceptionHandler(value = AdoptionException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(AdoptionException adoptionException) {
        ErrorCodeIfs errorCodeIfs = adoptionException.getErrorCodeIfs();

        log.error("{} : {}", errorCodeIfs.getErrorCode(), errorCodeIfs.getMessage(), adoptionException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

}