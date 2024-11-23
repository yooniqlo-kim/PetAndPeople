package com.ssafy.petandpeople.application.converter.user;

import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.domain.user.Password;
import com.ssafy.petandpeople.domain.user.User;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;

public class UserConverter {

    public static User dtoToDomain(UserDto userDto, String encryptedPassword) {
        return new User(
                userDto.getUserId(),
                Password.from(encryptedPassword),
                userDto.getUserName(),
                userDto.getUserPhoneNumber(),
                userDto.getUserAddress(),
                userDto.getRegisteredAt(),
                userDto.getLastLoginAt()
        );
    }

    public static UserEntity dtoToEntity(UserDto userDto, String encryptedPassword) {
        return new UserEntity(
                userDto.getUserId(),
                encryptedPassword,
                userDto.getUserName(),
                userDto.getUserPhoneNumber(),
                userDto.getUserAddress()
        );
    }

    public static UserEntity domainToEntity(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getPassword(),
                user.getUserName(),
                user.getUserPhoneNumber(),
                user.getUserAddress()
        );
    }

    public static User entityToDomain(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                Password.from(userEntity.getUserPassword()),
                userEntity.getUserName(),
                userEntity.getUserPhoneNumber(),
                userEntity.getUserAddress()
        );
    }

    public static UserDto entityToDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getUserPhoneNumber(),
                userEntity.getUserAddress()
        );
    }

}
