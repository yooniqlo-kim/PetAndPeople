package com.ssafy.petandpeople.application.converter.user;

import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;

public class UserSecurityConverter {

    public static UserSecurityEntity toEntity(UserEntity userEntity, String salt) {
        return new UserSecurityEntity(
                userEntity.getUserId(),
                salt
        );
    }

}
