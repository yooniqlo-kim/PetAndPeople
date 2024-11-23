package com.ssafy.petandpeople.application.converter.post;

import com.ssafy.petandpeople.application.dto.post.PostThumbnailDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostThumbnailEntity;

public class PostThumbnailConverter {

    public static PostThumbnailEntity dtoToPostThumbnailEntity(PostThumbnailDto postThumbnailDto, String filePath) {
        return new PostThumbnailEntity(
                postThumbnailDto.getThumbnailKey(),
                postThumbnailDto.getPostKey(),
                postThumbnailDto.getOriginalFileName(),
                postThumbnailDto.getS3FileName(),
                filePath
        );
    }

}
