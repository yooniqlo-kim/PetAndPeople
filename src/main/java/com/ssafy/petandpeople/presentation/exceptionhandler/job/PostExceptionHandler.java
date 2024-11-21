package com.ssafy.petandpeople.presentation.exceptionhandler.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.job.PostException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(PostExceptionHandler.class);

    @ExceptionHandler(value = PostException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(PostException postException) {
        ErrorCodeIfs errorCodeIfs = postException.getErrorCodeIfs();

        log.error("{} : {}", errorCodeIfs.getErrorCode(), errorCodeIfs.getMessage(), postException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

}
