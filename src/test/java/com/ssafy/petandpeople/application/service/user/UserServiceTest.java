package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.common.exception.user.PasswordMismatchException;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.domain.user.Password;
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

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    @AfterEach
    void tearDown() {
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

        UserEntity userEntity = userRepository.findUserByUserId(userId).orElseThrow(UserNotFoundException::new);
        assertEquals(userDto.getUserId(), userEntity.getUserId());
        assertEquals(userDto.getUserName(), userEntity.getUserName());
        assertEquals(userDto.getUserPhoneNumber(), userEntity.getUserPhoneNumber());
    }

    @Test
    @DisplayName("login 성공")
    void login_성공() {
        String userId = "testUser@test.com";
        String userPassword = "testPassword123@";
        String salt = "salt";

        Password password = Password.wrap(userPassword);
        String encryptPassword = password.encrypt(salt).getValue();

        UserEntity userEntity = new UserEntity(
                userId,
                encryptPassword,
                "testName",
                "testPhoneNumber",
                "testAddress"
        );
        UserSecurityEntity userSecurityEntity = new UserSecurityEntity(
                userId,
                salt
        );

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        LoginDto loginDto = new LoginDto(userId, userPassword);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        assertTrue(userService.login(loginDto, request));
        assertEquals(userId, session.getAttribute("USER_ID"));
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void login_실패_PasswordMismatchException() {
        String userId = "testUser@test.com";
        String userPassword = "wrongPassword";
        String salt = "salt";

        String correctPassword = "correctPassword";
        UserEntity userEntity = new UserEntity(
                userId,
                correctPassword,
                "testName",
                "testPhoneNumber",
                "testAddress"
        );
        userRepository.save(userEntity);

        UserSecurityEntity userSecurityEntity = new UserSecurityEntity(userId, salt);
        userSecurityRepository.save(userSecurityEntity);

        LoginDto loginDto = new LoginDto(userId, userPassword);
        MockHttpServletRequest request = new MockHttpServletRequest();

        assertThrows(PasswordMismatchException.class, () -> userService.login(loginDto, request));
    }

    @Test
    @DisplayName("유저 존재 여부 검증 실패")
    void validateUserExists_실패_UserNotFoundException() {
        LoginDto loginDto = new LoginDto(
                "nonExistUser@ssafy.com",
                "testPassword123@"
        );

        assertThrows(UserNotFoundException.class, () -> userService.validateUserExists(loginDto.getUserId()));
    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout_성공() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        userService.logout(request);

        assertTrue(session.isInvalid());
    }

    @Test
    @DisplayName("회원가입 후 로그인 성공 통합 테스트")
    void signUpAndLogin_성공() {
        String userId = "testUser@test.com";
        String userPassword = "TestPassword123@";
        UserDto userDto = new UserDto(
                userId,
                userPassword,
                "testName",
                "testPhoneNumber",
                "testAddress",
                null,
                null
        );

        assertTrue(userService.signUp(userDto));

        LoginDto loginDto = new LoginDto(userId, userPassword);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        assertTrue(userService.login(loginDto, request));
        assertNotNull(session.getAttribute("USER_KEY"));
    }
}
