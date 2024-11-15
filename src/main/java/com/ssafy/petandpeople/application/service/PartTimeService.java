package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.converter.PartTimeConverter;
import com.ssafy.petandpeople.application.dto.PartTimePostDto;
import com.ssafy.petandpeople.common.exception.job.PostNotFoundException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.PartTimeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartTimeService {

    public final PartTimeRepository partTimeRepository;
    public final UserService userService;

    private PartTimeService(PartTimeRepository partTimeRepository, UserService userService) {
        this.partTimeRepository = partTimeRepository;
        this.userService = userService;
    }

    public Boolean createPartTimePost(PartTimeDto partTimeDto, HttpServletRequest request)  {
        UserEntity foundUser = userService.findAllByUserKey(request);

        PartTimeEntity partTimeEntity = PartTimeConverter.dtoToEntity(partTimeDto, foundUser);
        partTimeRepository.save(partTimeEntity);

        return true;
    }

    public Boolean updatePartTimePost(Long postKey, PartTimePostDto partTimePostDto, HttpServletRequest request) {
        UserEntity userEntity = userService.findByUserKey(request);

        PartTimePostEntity selectedPartTimePostEntity = selectPartTimePostByPostKeyAndUserKey(postKey, userEntity);

        PartTimePostEntity updatePartTimePostEntity = PartTimeConverter.dtoToEntity(partTimePostDto);

        updatePartTimePostEntity.setPostKey(selectedPartTimePostEntity.getPostKey());
        updatePartTimePostEntity.setUserKey(selectedPartTimePostEntity.getUserKey());
        updatePartTimePostEntity.setManagerName(selectedPartTimePostEntity.getManagerName());
        updatePartTimePostEntity.setManagerPhoneNumber(selectedPartTimePostEntity.getManagerPhoneNumber());

        partTimeRepository.save(updatePartTimePostEntity);

        return true;
    }

    public PartTimePostEntity selectPartTimePostByPostKeyAndUserKey(Long postKey, UserEntity userKey) {
        Optional<PartTimePostEntity> partTimePostEntity = partTimeRepository.findByPostKeyAndUserKey(postKey, userKey);

        return partTimePostEntity.orElseThrow(PostNotFoundException::new);
    }

}