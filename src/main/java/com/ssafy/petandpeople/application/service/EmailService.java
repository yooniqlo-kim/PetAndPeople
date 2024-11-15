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

    private static final String SENDER_EMAIL = "yoon73337@gmail.com";
    private final JavaMailSender javaMailSender;

    private EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendAuthCodeToUserEmail(EmailDto emailDto, HttpServletRequest request) throws MessagingException {
        String authCode = generateAuthCode();
        String receiverEmail = emailDto.getEmail();
        String subject = "[PetAndPeople] 이메일 주소 확인";
        String text = "확인 코드 : " + authCode;

        sendEmail(receiverEmail, subject, text);
        saveAuthCodeInSession(authCode, request);
    }

    private String generateAuthCode() {
        SecureRandom randomGenerator = new SecureRandom();
        int randomSixDigitNumber = 100000 + randomGenerator.nextInt(900000);
        String authCode = String.valueOf(randomSixDigitNumber);

        return authCode;
    }

    private void sendEmail(String receiverEmail, String subject, String text) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        helper.setFrom(SENDER_EMAIL);
        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(text);

        javaMailSender.send(message);
    }

    private void saveAuthCodeInSession(String authCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("AUTH_CODE", authCode);
    }

    public void validateAuthCode(EmailDto emailDto, HttpServletRequest request) {
        String inputAuthCode = emailDto.getAuthCode();
        String sessionStoredAuthCode = getSessionStoredAuthCode(request);

        validateAuthCodeMatch(inputAuthCode, sessionStoredAuthCode);
    }

    private String getSessionStoredAuthCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sessionStoredAuthCode = (String) session.getAttribute("AUTH_CODE");

        validateSessionStoredAuthCode(sessionStoredAuthCode);

        return sessionStoredAuthCode;
    }

    private void validateSessionStoredAuthCode(String sessionStoredAuthCode) {
        if(sessionStoredAuthCode == null) {
            throw new StoredAuthCodeNotFoundException();
        }
    }

    private void validateAuthCodeMatch(String inputAuthCode, String sessionStoredAuthCode) {
        if(!inputAuthCode.equals(sessionStoredAuthCode)) {
            throw new AuthCodeMismatchException();
        }
    }

}
