package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.domain.user.Password;
import com.ssafy.petandpeople.domain.user.PasswordEncryptor;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserSecurityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceTest {

    private final PasswordEncryptor passwordEncryptor;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserServiceTest(PasswordEncryptor passwordEncryptor, UserService userService, UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.passwordEncryptor = passwordEncryptor;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    @AfterEach
    void setDown() {
        userSecurityRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void signUp_성공() {
        String userId = "testUser@test.com";
        UserDto userDto = new UserDto(
                userId,
                "testPassword123@",
                "testName",
                "testPhoneNumber",
                "testAddress",
                null,
                null
        );

        assertTrue(userService.signUp(userDto));

        UserEntity savedUser = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        assertEquals(userDto.getUserId(), savedUser.getUserId());
        assertEquals(userDto.getUserName(), savedUser.getUserName());
        assertEquals(userDto.getUserPhoneNumber(), savedUser.getUserPhoneNumber());
        assertEquals(userDto.getUserAddress(), savedUser.getUserAddress());
    }

    @Test
    @DisplayName("login 성공")
    void login_성공() {
        String userId = "testUser@test.com";
        String userPassword = "testPassword123@";
        String salt = "salt";

        String encryptedPassword = passwordEncryptor.encryptPassword(userPassword, salt);

        UserEntity userEntity = new UserEntity(
                userId,
                encryptedPassword,
                "testName",
                "testPhoneNumber",
                "testAddress"
        );
        UserSecurityEntity userSecurityEntity = new UserSecurityEntity(userId, salt);

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        String loginId = "testUser@test.com";
        String loginPassword = "testPassword123@";
        LoginDto loginDto = new LoginDto(loginId, loginPassword);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        assertTrue(userService.login(loginDto, request));
        assertNotNull(session.getAttribute("USER_KEY"));
        assertNotNull(session.getAttribute("IP_ADDRESS"));
    }

    @Test
    @DisplayName("유저 존재 여부 검증 실패")
    void login_실패_UserNotFoundException() {
        LoginDto loginDto = new LoginDto(
                "testUser@test.com",
                "testPassword123@"
        );

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        assertThrows(UserNotFoundException.class, () -> userService.login(loginDto, request));
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void login_실패_PasswordMismatchException() {
        String userId = "testUser@test.com";
        String userPassword = "testPassword123@";
        String salt = "salt";

        String encryptedPassword = passwordEncryptor.encryptPassword(userPassword, salt);
        UserEntity userEntity = new UserEntity(
                userId,
                encryptedPassword,
                "testName",
                "testPhoneNumber",
                "testAddress"
        );
        UserSecurityEntity userSecurityEntity = new UserSecurityEntity(userId, salt);

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        String loginId = "testUser@test.com";
        String loginPassword = "testPassword123!";
        LoginDto loginDto = new LoginDto(loginId, loginPassword);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        assertThrows(PasswordMismatchException.class, () -> userService.login(loginDto, request));
    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout_성공() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("USER_KEY", 1L);
        session.setAttribute("IP_ADDRESS", "112.255.167.140");
        request.setSession(session);

        userService.logout(request);

        assertTrue(session.isInvalid());
    }

}
