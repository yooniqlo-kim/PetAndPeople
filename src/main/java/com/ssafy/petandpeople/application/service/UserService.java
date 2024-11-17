package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.converter.UserConverter;
import com.ssafy.petandpeople.application.converter.UserSecurityConverter;
import com.ssafy.petandpeople.application.dto.UserDto;
import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.common.utils.PasswordEncryptor;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserSecurityEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserSecurityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    private UserService(UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    public Boolean signUp(UserDto userDto) {
        String salt = PasswordEncryptor.generateSalt();

        User user = UserConverter.dtoToDomain(userDto);
        user.encryptPassword(salt);

        UserEntity userEntity = UserConverter.domainToEntity(user);
        UserSecurityEntity userSecurityEntity = UserSecurityConverter.toEntity(user, salt);

        userRepository.save(userEntity);
        userSecurityRepository.save(userSecurityEntity);

        return true;
    }

    public UserEntity findAllByUserKey(HttpServletRequest request) {
        Long userKey = getUserKeyFromSession(request);

        Optional<UserEntity> foundUser = userRepository.findAllByUserKey(userKey);

        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    public UserEntity findByUserKey(HttpServletRequest request) {
        Long userKey = getUserKeyFromSession(request);

        Optional<UserEntity> foundUser = userRepository.findById(userKey );

        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    private Long getUserKeyFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return (Long) session.getAttribute("userKey");
    }

}
