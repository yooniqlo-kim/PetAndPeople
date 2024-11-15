package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
