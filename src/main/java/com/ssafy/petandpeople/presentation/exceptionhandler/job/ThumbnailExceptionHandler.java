package com.ssafy.petandpeople.presentation.exceptionhandler.job;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotSavedException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotUploadedException;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ThumbnailExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ThumbnailExceptionHandler.class);

    @ExceptionHandler(value = ThumbnailNotUploadedException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(ThumbnailNotUploadedException thumbnailNotUploadedException) {
        ErrorCodeIfs errorCodeIfs = thumbnailNotUploadedException.getErrorCodeIfs();

        log.error("", thumbnailNotUploadedException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

    @ExceptionHandler(value = ThumbnailNotSavedException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(ThumbnailNotSavedException thumbnailNotSavedException) {
        ErrorCodeIfs errorCodeIfs = thumbnailNotSavedException.getErrorCodeIfs();

        log.error("", thumbnailNotSavedException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

}
