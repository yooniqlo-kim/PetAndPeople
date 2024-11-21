package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.application.converter.user.UserConverter;
import com.ssafy.petandpeople.application.converter.user.UserSecurityConverter;
import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.common.exception.user.InvalidSessionException;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.common.utils.PasswordEncryptor;
import com.ssafy.petandpeople.domain.user.LoginAttempt;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserSecurityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final LoginAttempt loginAttempt;
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    public UserService(LoginAttempt loginAttempt, UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.loginAttempt = loginAttempt;
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    public Boolean signUp(UserDto userDto) {
        String salt = PasswordEncryptor.generateSalt();
        User encryptedUser = createEncryptedUser(userDto, salt);

        UserEntity userEntity = UserConverter.domainToEntity(encryptedUser);
        UserSecurityEntity userSecurityEntity = UserSecurityConverter.toEntity(encryptedUser, salt);

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        return true;
    }

    public Boolean login(LoginDto loginDto, HttpServletRequest request) {
        String userId = loginDto.getUserId();

        loginAttempt.validateUserLock(userId);
        loginAttempt.increaseLoginAttempt(userId);

        UserEntity userEntity = findLoginUser(userId);
        validateLoginPassword(loginDto, userEntity);

        loginAttempt.resetLoginAttempt(userId);

        saveLoginUserInSession(userEntity.getUserKey(), request);

        return true;
    }

    public UserEntity findLoginUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        validateSessionExists(session);

        session.invalidate();
    }

    private User createEncryptedUser(UserDto userDto, String salt) {
        User user = UserConverter.dtoToDomain(userDto);
        user.encryptPassword(salt);

        return user;
    }

    private void validateLoginPassword(LoginDto loginDto, UserEntity userEntity) {
        String salt = findSaltForLoginUser(loginDto.getUserId());
        String encryptedInputPassword = encryptLoginUserPassword(loginDto.getUserPassword(), salt);

        User validatedUser = UserConverter.entityToDomain(userEntity);
        validatedUser.validatePasswordMatch(encryptedInputPassword);
    }

    private String findSaltForLoginUser(String userId) {
        UserSecurityEntity userSecurityEntity = userSecurityRepository.findByUserId(userId);

        return userSecurityEntity.getSalt();
    }

    private String encryptLoginUserPassword(String loginUserPassword, String salt) {
        byte[] hashedPassword = PasswordEncryptor.hashWithSHA256(loginUserPassword, salt);

        return PasswordEncryptor.generateEncryptedPassword(hashedPassword);
    }

    private void saveLoginUserInSession(Long userKey, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();

        HttpSession session = request.getSession();
        session.setAttribute("USER_KEY", userKey);
        session.setAttribute("IP_ADDRESS", ipAddress);
    }

    private void validateSessionExists(HttpSession session) {
        if (session == null) {
            throw new InvalidSessionException();
        }
    }

}
