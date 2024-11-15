package com.ssafy.petandpeople.application.converter;

import com.ssafy.petandpeople.application.dto.PartTimePostDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;

public class PartTimeConverter {

    public static PartTimePostEntity dtoToEntity(PartTimePostDto partTimePostDto, UserEntity userEntity) {
        return new PartTimePostEntity(
                userEntity,
                partTimePostDto.getPostTitle(),
                partTimePostDto.getPartTimeDeadline(),
                partTimePostDto.getPartTimeCount(),
                partTimePostDto.getPartTimeSalary(),
                partTimePostDto.getPartTimeAge(),
                partTimePostDto.getPartTimePeriod(),
                partTimePostDto.getPartTimeDays(),
                partTimePostDto.getPartTimeHours(),
                partTimePostDto.getPartTimeAddress(),
                partTimePostDto.getPostContent(),
                partTimePostDto.getManagerName(),
                partTimePostDto.getManagerPhoneNumber()
        );
    }

    public static PartTimePostEntity dtoToEntity(PartTimePostDto partTimePostDto) {
        return new PartTimePostEntity(
                partTimePostDto.getPostTitle(),
                partTimePostDto.getPartTimeDeadline(),
                partTimePostDto.getPartTimeCount(),
                partTimePostDto.getPartTimeSalary(),
                partTimePostDto.getPartTimeAge(),
                partTimePostDto.getPartTimePeriod(),
                partTimePostDto.getPartTimeDays(),
                partTimePostDto.getPartTimeHours(),
                partTimePostDto.getPartTimeAddress(),
                partTimePostDto.getPostContent()
        );
    }

    public static PartTimePostDto entityToDto(PartTimePostEntity partTimePostEntity) {
        return new PartTimePostDto(
                partTimePostEntity.getPostTitle(),
                partTimePostEntity.getPartTimeDeadline(),
                partTimePostEntity.getPartTimeCount(),
                partTimePostEntity.getPartTimeSalary(),
                partTimePostEntity.getPartTimeAge(),
                partTimePostEntity.getPartTimePeriod(),
                partTimePostEntity.getPartTimeDays(),
                partTimePostEntity.getPartTimeHours(),
                partTimePostEntity.getPartTimeAddress(),
                partTimePostEntity.getPostContent(),
                partTimePostEntity.getManagerName(),
                partTimePostEntity.getManagerPhoneNumber()
        );
    }

}
