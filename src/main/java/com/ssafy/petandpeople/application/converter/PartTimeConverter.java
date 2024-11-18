package com.ssafy.petandpeople.application.converter;

import com.ssafy.petandpeople.application.dto.PartTimeDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimeEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;

public class PartTimeConverter {

    public static PartTimeDto entityToDto(PartTimeEntity partTimeEntity) {
        return new PartTimeDto(
                partTimeEntity.getPostTitle(),
                partTimeEntity.getPartTimeDeadline(),
                partTimeEntity.getPartTimeCount(),
                partTimeEntity.getPartTimeSalary(),
                partTimeEntity.getPartTimeAge(),
                partTimeEntity.getPartTimePeriod(),
                partTimeEntity.getPartTimeDays(),
                partTimeEntity.getPartTimeHours(),
                partTimeEntity.getPartTimeAddress(),
                partTimeEntity.getPostContent(),
                partTimeEntity.getManagerName(),
                partTimeEntity.getManagerPhoneNumber()
        );
    }

    public static PartTimeEntity dtoToEntity(PartTimeDto partTimeDto, UserEntity userEntity) {
        return new PartTimeEntity(
                userEntity,
                partTimeDto.getPostTitle(),
                partTimeDto.getPartTimeDeadline(),
                partTimeDto.getPartTimeCount(),
                partTimeDto.getPartTimeSalary(),
                partTimeDto.getPartTimeAge(),
                partTimeDto.getPartTimePeriod(),
                partTimeDto.getPartTimeDays(),
                partTimeDto.getPartTimeHours(),
                partTimeDto.getPartTimeAddress(),
                partTimeDto.getPostContent(),
                partTimeDto.getManagerName(),
                partTimeDto.getManagerPhoneNumber()
        );
    }

}
