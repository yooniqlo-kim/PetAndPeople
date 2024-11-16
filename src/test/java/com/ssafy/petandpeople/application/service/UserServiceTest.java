package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.dto.UserDto;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("회원가입 성공")
    void signUp_성공() {
        UserDto userDto = new UserDto(
                "ssafy@ssafy.com",
                "SecurePassword123@",
                "김싸피",
                "010-1234-5678",
                "서울시 강남구 테헤란로 212",
                null,
                null
        );

        assertTrue(userService.signUp(userDto));
    }
}
