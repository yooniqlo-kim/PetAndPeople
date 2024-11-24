package com.ssafy.petandpeople.application.dto.post;

import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;

public class PostThumbnailDto {

    private final String originalFileName;
    private final String s3FileName;
    private final String extension;

    public PostThumbnailDto(
            String originalFileName,
            String s3FileName,
            String extension) {
        this.originalFileName = originalFileName;
        this.s3FileName = s3FileName;
        this.extension = extension;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getS3FileName() {
        return s3FileName;
    }

    public String getExtension() {
        return extension;
    }

}
