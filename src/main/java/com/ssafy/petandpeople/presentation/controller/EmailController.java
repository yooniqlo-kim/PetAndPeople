package com.ssafy.petandpeople.presentation.controller;

import com.ssafy.petandpeople.application.dto.EmailDto;
import com.ssafy.petandpeople.application.service.EmailService;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Api<Object> sendEmail(@RequestBody EmailDto emailDto, HttpServletRequest request) throws MessagingException {
        emailService.sendAuthCodeToUserEmail(emailDto, request);

        return Api.OK();
    }

    @PostMapping("/verify")
    public Api<Object> validateAuthCode(@RequestBody EmailDto emailDto, HttpServletRequest request) {
        emailService.validateAuthCode(emailDto, request);

        return Api.OK();
    }
}
