package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.application.converter.user.UserConverter;
import com.ssafy.petandpeople.application.converter.user.UserSecurityConverter;
import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.common.exception.user.InvalidSessionException;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.common.utils.UUIDGenerator;
import com.ssafy.petandpeople.domain.user.LoginAttempt;
import com.ssafy.petandpeople.domain.user.PasswordEncryptor;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserSecurityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final PasswordEncryptor passwordEncryptor;
    private final LoginAttempt loginAttempt;
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    public UserService(PasswordEncryptor passwordEncryptor, LoginAttempt loginAttempt, UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.passwordEncryptor = passwordEncryptor;
        this.loginAttempt = loginAttempt;
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    @Transactional
    public Boolean signUp(UserDto userDto) {
        String salt = generateRandomSalt();
        String rawPassword = userDto.getUserPassword();
        String encryptedPassword = passwordEncryptor.encryptPassword(rawPassword, salt);

        UserEntity userEntity = UserConverter.dtoToEntity(userDto, encryptedPassword);
        UserSecurityEntity userSecurityEntity = UserSecurityConverter.toEntity(userEntity, salt);

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        return true;
    }

    @Transactional
    public Boolean login(LoginDto loginDto, HttpServletRequest request) {
        String userId = loginDto.getUserId();

        UserEntity userEntity = findLoginUser(userId);

        loginAttempt.validateUserLock(userId);
        loginAttempt.increaseLoginAttempt(userId);

        validateLoginPassword(loginDto, userEntity);

        loginAttempt.resetLoginAttempt(userId);
        updateLoginTime(userEntity);
        saveLoginUserInSession(userEntity.getUserKey(), request);

        return true;
    }

    public void checkPasswordMatch(LoginDto loginDto) {
        String userId = loginDto.getUserId();

        UserEntity userEntity = findLoginUser(userId);

        validateLoginPassword(loginDto, userEntity);
    }

    public UserDto getDetailAboutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Long userKey = (long) session.getAttribute("USER_KEY");

        UserEntity savedUser = userRepository.findByUserKey(userKey);

        return UserConverter.entityToDto(savedUser);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        validateSession(session);

        session.invalidate();
    }

    public void checkUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        validateSession(session);

        String ipAddress = request.getRemoteAddr();

        if(!ipAddress.equals(session.getAttribute("IP_ADDRESS"))) {
            throw new InvalidSessionException();
        }
    }

    private static String generateRandomSalt() {
        return UUIDGenerator.generateUUIDtoString();
    }

    private UserEntity findLoginUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void validateLoginPassword(LoginDto loginDto, UserEntity userEntity) {
        String rawLoginPassword = loginDto.getUserPassword();
        String salt = findSalt(userEntity.getUserId());
        String encryptedLoginPassword = passwordEncryptor.encryptPassword(rawLoginPassword, salt);

        User savedUser = UserConverter.entityToDomain(userEntity);

        savedUser.validatePasswordMatch(encryptedLoginPassword);
    }

    private String findSalt(String userId) {
        UserSecurityEntity userSecurityEntity = userSecurityRepository.findByUserId(userId);

        return userSecurityEntity.getSalt();
    }

    private void updateLoginTime(UserEntity userEntity) {
        userRepository.updateLastLoginAt(userEntity.getUserKey(), LocalDateTime.now());
    }

    private void saveLoginUserInSession(Long userKey, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();

        HttpSession session = request.getSession();

        session.setAttribute("USER_KEY", userKey);
        session.setAttribute("IP_ADDRESS", ipAddress);
    }

    private void validateSession(HttpSession session) {
        if (session == null || session.getAttribute("USER_KEY") == null || session.getAttribute("IP_ADDRESS") == null) {
            throw new InvalidSessionException();
        }
    }

}