package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.converter.PartTimeConverter;
import com.ssafy.petandpeople.application.dto.PartTimePostDto;
import com.ssafy.petandpeople.common.exception.job.PostNotAuthorizedException;
import com.ssafy.petandpeople.common.exception.job.PostNotFoundException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.PartTimeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartTimeService {

    public final PartTimeRepository partTimeRepository;
    public final UserService userService;

    private PartTimeService(PartTimeRepository partTimeRepository, UserService userService) {
        this.partTimeRepository = partTimeRepository;
        this.userService = userService;
    }

    public Boolean createPartTimePost(PartTimePostDto partTimePostDto, HttpServletRequest request) {
        UserEntity foundUser = userService.findAllByUserKey(request);

        PartTimePostEntity partTimePostEntity = PartTimeConverter.dtoToEntity(partTimePostDto, foundUser);

        partTimeRepository.save(partTimePostEntity);

        return true;
    }

    public Boolean updatePartTimePost(Long postKey, PartTimePostDto partTimePostDto, HttpServletRequest request) {
        UserEntity userEntity = userService.findByUserKey(request);

        PartTimePostEntity selectedPartTimePostEntity = findPartTimePostByUser(postKey, userEntity);

        PartTimePostEntity updatePartTimePostEntity = PartTimeConverter.dtoToEntity(partTimePostDto);

        updatePartTimePostEntity.setPostKey(selectedPartTimePostEntity.getPostKey());
        updatePartTimePostEntity.setUserKey(selectedPartTimePostEntity.getUserKey());
        updatePartTimePostEntity.setManagerName(selectedPartTimePostEntity.getManagerName());
        updatePartTimePostEntity.setManagerPhoneNumber(selectedPartTimePostEntity.getManagerPhoneNumber());

        partTimeRepository.save(updatePartTimePostEntity);

        return true;
    }

    private PartTimePostEntity findPartTimePostByUser(Long postKey, UserEntity userKey) {
        Optional<PartTimePostEntity> partTimePostEntity = partTimeRepository.findByPostKeyAndUserKey(postKey, userKey);

        return partTimePostEntity.orElseThrow(PostNotFoundException::new);
    }

    public PartTimePostDto findPartTimePostByPostKey(Long postKey) {
        return partTimeRepository.findById(postKey)
                .map(PartTimeConverter::entityToDto)
                .orElseGet(PartTimePostDto::new);
    }

    public List<PartTimePostDto> findPartTimePostByUserKey(HttpServletRequest request) {
        UserEntity userKey = userService.findByUserKey(request);

        return partTimeRepository.findByUserKey(userKey).stream()
                .map(PartTimeConverter::entityToDto)
                .toList();
    }

    public List<PartTimePostDto> findAllPartTimePost() {
        List<PartTimePostEntity> partTimePostEntities = partTimeRepository.findAll();

        return partTimePostEntities.stream()
                .map(PartTimeConverter::entityToDto)
                .toList();
    }

    public boolean deletePartTimePost(Long postKey, HttpServletRequest request) {
        UserEntity userKey = userService.findByUserKey(request);

        checkPartTimePostOwnership(postKey, userKey);

        partTimeRepository.deleteById(postKey);

        return true;
    }

    private void checkPartTimePostOwnership(Long postKey, UserEntity userKey) {
        if(partTimeRepository.findByPostKeyAndUserKey(postKey, userKey).isPresent()) {
            return;
        }

        throw new PostNotAuthorizedException();
    }

}