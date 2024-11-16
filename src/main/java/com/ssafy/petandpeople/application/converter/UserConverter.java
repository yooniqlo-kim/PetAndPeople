package com.ssafy.petandpeople.application.converter;

import com.ssafy.petandpeople.application.dto.UserDto;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;

public class UserConverter {

    public static User dtoToDomain(UserDto userDto, String salt) {
        return User.createUserWithEncryptedPassword(
                userDto.getUserId(),
                userDto.getUserPassword(),
                salt,
                userDto.getUserName(),
                userDto.getUserPhoneNumber(),
                userDto.getUserAddress(),
                userDto.getRegisteredAt(),
                userDto.getLastLoginedAt()
        );
    }

    public static UserEntity domainToEntity(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getPassword().getValue(),
                user.getUserName(),
                user.getUserPhoneNumber(),
                user.getUserAddress()
        );
    }

}
