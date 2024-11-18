package com.ssafy.petandpeople.presentation.exceptionhandler.email;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;
import com.ssafy.petandpeople.common.exception.email.AuthCodeMismatchException;
import com.ssafy.petandpeople.common.exception.email.DuplicateEmailException;
import com.ssafy.petandpeople.common.exception.email.StoredAuthCodeNotFoundException;
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

    @ExceptionHandler(value = MessagingException.class)
    @Order(1)
    public ResponseEntity<Api<Object>> exceptionHandler(MessagingException messagingException) {
        log.error("MessagingException occurred : ", messagingException);

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(1500));
    }

    @ExceptionHandler(value = MailSendException.class)
    @Order(2)
    public ResponseEntity<Api<Object>> exceptionHandler(MailSendException mailSendException) {
        log.error("MailSendException occurred : ", mailSendException);

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(1501));
    }

    @ExceptionHandler(value = StoredAuthCodeNotFoundException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(StoredAuthCodeNotFoundException storedAuthCodeNotFoundException) {
        ErrorCodeIfs errorCodeIfs = storedAuthCodeNotFoundException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), storedAuthCodeNotFoundException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = AuthCodeMismatchException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(AuthCodeMismatchException authCodeMismatchException) {
        ErrorCodeIfs errorCodeIfs = authCodeMismatchException.getErrorCodeIfs();

        log.error("{}", errorCodeIfs.getMessage(), authCodeMismatchException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs));
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    public ResponseEntity<Api<Object>> exceptionHandler(DuplicateEmailException duplicateEmailException) {
        ErrorCodeIfs errorCodeIfs = duplicateEmailException.getErrorCodeIfs();
        log.error("{}", errorCodeIfs.getMessage(), duplicateEmailException);

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs.getErrorCode(), "이메일 중복"));
    }

}
