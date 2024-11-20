package com.ssafy.petandpeople.presentation.exceptionhandler.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.email.AuthCodeMismatchException;
import com.ssafy.petandpeople.common.exception.email.EmailAlreadyRegisteredException;
import com.ssafy.petandpeople.common.exception.email.AuthCodeNotFoundInSessionException;
import com.ssafy.petandpeople.common.exception.email.EmailException;
import com.ssafy.petandpeople.common.exception.user.UserException;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class EmailExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(EmailExceptionHandler.class);

    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(EmailException emailException) {
        ErrorCodeIfs errorCodeIfs = emailException.getErrorCodeIfs();

        log.error("{} : {}", errorCodeIfs.getErrorCode(), errorCodeIfs.getMessage(), emailException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getMessage()));
    }

}
