package com.ssafy.petandpeople.application.service.email;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpServletRequest;

import com.ssafy.petandpeople.application.dto.email.EmailDto;
import com.ssafy.petandpeople.common.exception.email.AuthCodeMismatchException;
import com.ssafy.petandpeople.common.exception.email.AuthCodeNotFoundInSessionException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmailServiceTest {

    private final EmailService emailService;
    @MockBean
    private JavaMailSender javaMailSender;
    private MockHttpServletRequest request;
    private HttpSession session;

    @Autowired
    public EmailServiceTest(EmailService emailService) {
        this.emailService = emailService;
    }

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        session = request.getSession();
    }

    @Test
    @DisplayName("이메일 인증 코드 전송 성공")
    void sendAuthCodeToUserEmail_성공() throws MessagingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail("ssafy@ssafy.com");

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendAuthCodeToUserEmail(emailDto, request);

        assertNotNull(session.getAttribute("AUTH_CODE"));
    }

    @Test
    @DisplayName("인증 코드 검증 성공")
    void validateAuthCode_성공() {
        String authCode = "123456";
        session.setAttribute("AUTH_CODE", authCode);

        EmailDto emailDto = new EmailDto();
        emailDto.setAuthCode(authCode);

        assertDoesNotThrow(() -> emailService.validateAuthCode(emailDto, request));
    }

    @Test
    @DisplayName("인증 코드 검증 실패 - 불일치")
    void validateAuthCode_실패_AuthCodeMismatch() {
        session.setAttribute("AUTH_CODE", "654321");

        EmailDto emailDto = new EmailDto();
        emailDto.setAuthCode("123456");

        assertThrows(AuthCodeMismatchException.class, () -> emailService.validateAuthCode(emailDto, request));
    }

    @Test
    @DisplayName("인증 코드 검증 실패 - 세션에 저장된 코드 없음")
    void validateAuthCode_실패_StoredAuthCodeNotFound() {
        EmailDto emailDto = new EmailDto();
        emailDto.setAuthCode("123456");

        assertThrows(AuthCodeNotFoundInSessionException.class, () -> emailService.validateAuthCode(emailDto, request));
    }

}
