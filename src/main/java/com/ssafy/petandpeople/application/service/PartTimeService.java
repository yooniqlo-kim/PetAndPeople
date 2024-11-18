package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.converter.PartTimeConverter;
import com.ssafy.petandpeople.application.dto.PartTimeDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimeEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.PartTimeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class PartTimeService {

    public final PartTimeRepository partTimeRepository;
    public final UserService userService;

    private PartTimeService(PartTimeRepository partTimeRepository, UserService userService) {
        this.partTimeRepository = partTimeRepository;
        this.userService = userService;
    }

    public Boolean createPartTimePost(PartTimeDto partTimeDto, HttpServletRequest request)  {
        UserEntity foundUser = userService.findByUserKey(request);

        PartTimeEntity partTimeEntity = PartTimeConverter.dtoToEntity(partTimeDto, foundUser);
        partTimeRepository.save(partTimeEntity);

        return true;
    }

}