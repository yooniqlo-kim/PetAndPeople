package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.dto.EmailDto;
import com.ssafy.petandpeople.common.exception.email.AuthCodeMismatchException;
import com.ssafy.petandpeople.common.exception.email.StoredAuthCodeNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "yoon73337@gmail.com";

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendAuthCodeToUserEmail(EmailDto emailDto, HttpServletRequest request) throws MessagingException {
        String authCode = generateAuthCode();

        String receiverEmail = emailDto.getEmail();
        String subject = "[PetAndPeople] 이메일 주소 확인";
        String text = "확인 코드 : " + authCode;

        if(sendEmail(receiverEmail, subject, text)) {
            saveAuthCodeInSession(authCode, request);
            return true;
        }

        return false;
    }

    private String generateAuthCode() {
        SecureRandom randomGenerator = new SecureRandom();

        int randomSixDigitNumber = 100000 + randomGenerator.nextInt(900000);
        String authCode = String.valueOf(randomSixDigitNumber);

        return authCode;
    }

    private void saveAuthCodeInSession(String authCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("AUTH_CODE", authCode);
    }

    private boolean sendEmail(String to, String subject, String text) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(senderEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        javaMailSender.send(message);

        return true;
    }


    public boolean validateAuthCode(EmailDto emailDto, HttpServletRequest request) {
        String inputAuthCode = emailDto.getAuthCode();

        String storedAuthCode = isAuthCodeStored(request);

        return isAuthCodeMatch(inputAuthCode, storedAuthCode);
    }

    private String isAuthCodeStored(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String storedAuthCode = (String) session.getAttribute("AUTH_CODE");

        if(storedAuthCode == null) {
            throw new StoredAuthCodeNotFoundException();
        }

        return storedAuthCode;
    }

    private boolean isAuthCodeMatch(String inputAuthCode, String storedAuthCode) {
        if(!inputAuthCode.equals(storedAuthCode)) {
            throw new AuthCodeMismatchException();
        }

        return true;
    }

}
