package com.ssafy.petandpeople.application.service.user;

import com.ssafy.petandpeople.application.converter.user.UserConverter;
import com.ssafy.petandpeople.application.converter.user.UserSecurityConverter;
import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.common.exception.user.SaltNotFoundException;
import com.ssafy.petandpeople.common.exception.user.UserNotSignUpException;
import com.ssafy.petandpeople.common.utils.PasswordEncryptor;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserSecurityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final LoginAttemptManager loginAttemptManager;

    public UserService(UserRepository userRepository, UserSecurityRepository userSecurityRepository, LoginAttemptManager loginAttemptManager) {
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
        this.loginAttemptManager = loginAttemptManager;
    }

    public Boolean signUp(UserDto userDto) {
        String salt = PasswordEncryptor.generateSalt();
        User user = createEncryptedUser(userDto, salt);

        UserEntity userEntity = UserConverter.domainToEntity(user);
        UserSecurityEntity userSecurityEntity = UserSecurityConverter.toEntity(user, salt);

        try {
            userRepository.save(userEntity);
            userSecurityRepository.save(userSecurityEntity);
        } catch(Exception e) {
            throw new UserNotSignUpException(e.getMessage());
        }

        return true;
    }

    private User createEncryptedUser(UserDto userDto, String salt) {
        User user = UserConverter.dtoToDomain(userDto);
        user.encryptPassword(salt);

        return user;
    }

    public Boolean login(LoginDto loginDto) {
        UserEntity userEntity = validateUserExists(loginDto);

        loginAttemptManager.validateLoginAttempts(loginDto.getUserId());

        validatePassword(loginDto, userEntity);

        loginAttemptManager.clearUserLoginAttempt(loginDto.getUserId());

        return true;
    }

    public UserEntity validateUserExists(LoginDto loginDto) {
        return userRepository.findByUserId(loginDto.getUserId()).orElseThrow(UserNotFoundException::new);
    }

    private void validatePassword(LoginDto loginDto, UserEntity userEntity) {
        String salt = getSalt(loginDto);
        User user = createEncryptedUser(loginDto, salt);
        String storedPassword = userEntity.getUserPassword();

        user.validatePasswordMatch(storedPassword);
    }

    private String getSalt(LoginDto loginDto) {
        UserSecurityEntity userSecurityEntity = userSecurityRepository.findById(loginDto.getUserId()).orElseThrow(SaltNotFoundException::new);

        return userSecurityEntity.getSalt();
    }

    private User createEncryptedUser(LoginDto loginDto, String salt) {
        User user = UserConverter.loginDtoToDomain(loginDto);
        user.encryptPassword(salt);

        return user;
    }

}
