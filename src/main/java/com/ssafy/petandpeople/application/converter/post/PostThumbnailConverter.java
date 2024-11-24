package com.ssafy.petandpeople.application.converter.post;

import com.ssafy.petandpeople.application.dto.post.PostThumbnailDto;
import com.ssafy.petandpeople.domain.post.PostThumbnail;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostThumbnailEntity;

public class PostThumbnailConverter {

    public static PostThumbnailEntity domainToEntity(PostThumbnail postThumbnail, PostEntity postKey) {
        return new PostThumbnailEntity(
                postThumbnail.getThumbnailKey(),
                postKey,
                postThumbnail.getFileName().getOriginalFileName(),
                postThumbnail.getS3FileName(),
                postThumbnail.getFileName().getExtension()
        );
    }

}
