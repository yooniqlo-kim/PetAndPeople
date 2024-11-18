package com.ssafy.petandpeople.application.converter.user;

import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;

public class UserSecurityConverter {

    public static UserSecurityEntity toEntity(User user, String salt) {
        return new UserSecurityEntity(
                user.getUserId(),
                salt
        );
    }

}
