package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.common.exception.user.UserNotFoundException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findAllByUserKey(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userKey = (Long) session.getAttribute("userKey");

        UserEntity foundUser = userRepository.findAllByUserKey(userKey);
        checkUserFound(foundUser);

        return foundUser;
    }

    private void checkUserFound(UserEntity userEntity) {
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
    }

}
