package com.ssafy.petandpeople.application.converter;

import com.ssafy.petandpeople.application.dto.UserDto;
import com.ssafy.petandpeople.domain.user.Password;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;

public class UserConverter {

    public static User dtoToDomain(UserDto userDto) {
        return new User(
                userDto.getUserId(),
                Password.wrap(userDto.getUserPassword()),
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
