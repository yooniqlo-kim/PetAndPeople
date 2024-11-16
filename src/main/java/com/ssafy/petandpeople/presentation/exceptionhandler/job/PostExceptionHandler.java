package com.ssafy.petandpeople.presentation.exceptionhandler.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.job.PostNotAuthorizedException;
import com.ssafy.petandpeople.common.exception.job.PostNotFoundException;
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

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(PostNotFoundException postNotFoundException) {
        ErrorCodeIfs errorCodeIfs = postNotFoundException.getErrorCodeIfs();;

        log.error("{}", errorCodeIfs.getMessage(), postNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = PostNotAuthorizedException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(PostNotAuthorizedException postNotAuthorizedException) {
        ErrorCodeIfs errorCodeIfs = postNotAuthorizedException.getErrorCodeIfs();;

        log.error("{}", errorCodeIfs.getMessage(), postNotAuthorizedException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

}
