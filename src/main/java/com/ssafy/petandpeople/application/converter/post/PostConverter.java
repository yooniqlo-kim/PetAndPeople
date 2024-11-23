package com.ssafy.petandpeople.application.converter.post;

import com.ssafy.petandpeople.application.dto.post.PostDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;

public class PostConverter {

    public static PostEntity dtoToEntity(PostDto postDto) {
        return new PostEntity(
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getAnimalType(),
                postDto.getBreed(),
                postDto.getColor(),
                postDto.getLastSeenLocation(),
                postDto.getContactInfo(),
                postDto.getStatus()
        );
    }

    public static PostEntity dtoToEntity(PostDto postDto, UserEntity userEntity) {
        return new PostEntity(
                userEntity,
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getAnimalType(),
                postDto.getBreed(),
                postDto.getColor(),
                postDto.getLastSeenLocation(),
                postDto.getContactInfo(),
                postDto.getStatus()
        );
    }

    public static PostDto entityToDto(PostEntity postEntity, String thumbnailPath) {
        return new PostDto(
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getAnimalType(),
                postEntity.getBreed(),
                postEntity.getColor(),
                postEntity.getLastSeenLocation(),
                postEntity.getContactInfo(),
                postEntity.getStatus(),
                postEntity.getUpdatedAt(),
                thumbnailPath
        );
    }

}
