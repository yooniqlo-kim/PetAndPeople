package com.ssafy.petandpeople.presentation.exceptionhandler;

import com.ssafy.petandpeople.common.error.ErrorCode;
import com.ssafy.petandpeople.presentation.response.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> ExceptionHandler(Exception exception) {
        log.error("", exception);

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(ErrorCode.SEVER_ERROR));
    }

}
