package com.ssafy.petandpeople.presentation.controller.email;

import com.ssafy.petandpeople.application.dto.email.EmailDto;
import com.ssafy.petandpeople.application.service.email.EmailService;
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

    private EmailController(EmailService emailService) {
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
