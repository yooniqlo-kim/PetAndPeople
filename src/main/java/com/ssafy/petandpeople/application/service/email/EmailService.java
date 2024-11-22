package com.ssafy.petandpeople.application.service.email;

import com.ssafy.petandpeople.application.dto.email.EmailDto;
import com.ssafy.petandpeople.common.exception.email.AuthCodeInSessionNotFoundException;
import com.ssafy.petandpeople.common.exception.email.AuthCodeMismatchException;
import com.ssafy.petandpeople.common.exception.email.EmailAlreadyRegisteredException;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
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

    private static final Dotenv DOTENV = Dotenv.load();
    private static final String ADMIN_EMAIL = DOTENV.get("ADMIN_EMAIL");

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    public void sendAuthCodeToUserEmail(EmailDto emailDto, HttpServletRequest request) throws MessagingException {
        validateEmailAlreadyRegistered(emailDto);

        String authCode = generateAuthCode();
        String receiverEmail = emailDto.getEmail();
        String subject = "[PetAndPeople] 이메일 주소 확인";
        String text = "안녕하세요. PetAndPeople입니다.\n\n"
                + "회원님의 계정을 보호하기 위한 인증 코드입니다.\n"
                + "인증 코드: " + authCode + "\n\n"
                + "문의사항이 있으시면 언제든지 고객센터로 연락 주세요.\n\n"
                + "감사합니다.";

        sendEmail(receiverEmail, subject, text);
        saveAuthCodeInSession(authCode, request);
    }

    public void validateAuthCode(EmailDto emailDto, HttpServletRequest request) {
        String inputAuthCode = emailDto.getAuthCode();
        String sessionStoredAuthCode = getSessionStoredAuthCode(request);

        validateAuthCodeMatch(inputAuthCode, sessionStoredAuthCode);
    }

    private void validateEmailAlreadyRegistered(EmailDto emailDto) {
        if (userRepository.findByUserId(emailDto.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    private String generateAuthCode() {
        SecureRandom randomGenerator = new SecureRandom();
        int randomSixDigitNumber = 100000 + randomGenerator.nextInt(900000);

        return String.valueOf(randomSixDigitNumber);
    }

    private void sendEmail(String receiverEmail, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        helper.setFrom(ADMIN_EMAIL);
        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(text);

        javaMailSender.send(message);
    }

    private void saveAuthCodeInSession(String authCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("AUTH_CODE", authCode);
    }

    private String getSessionStoredAuthCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sessionStoredAuthCode = (String) session.getAttribute("AUTH_CODE");

        validateAuthCodePresenceInSession(sessionStoredAuthCode);

        return sessionStoredAuthCode;
    }

    private void validateAuthCodePresenceInSession(String sessionStoredAuthCode) {
        if (sessionStoredAuthCode == null) {
            throw new AuthCodeInSessionNotFoundException();
        }
    }

    private void validateAuthCodeMatch(String inputAuthCode, String sessionStoredAuthCode) {
        if (!inputAuthCode.equals(sessionStoredAuthCode)) {
            throw new AuthCodeMismatchException();
        }
    }

}
