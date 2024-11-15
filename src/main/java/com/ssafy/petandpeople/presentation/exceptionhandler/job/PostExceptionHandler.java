package com.ssafy.petandpeople.presentation.exceptionhandler.job;

import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(PostExceptionHandler.class);

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(DataIntegrityViolationException dataIntegrityViolationException) {
        log.error("{}", dataIntegrityViolationException.getMessage(), dataIntegrityViolationException);

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(2000));
    }

}
