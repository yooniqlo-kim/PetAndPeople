package com.ssafy.petandpeople.domain.post;

import org.springframework.web.multipart.MultipartFile;

public class PostThumbnail {

    private final String thumbnailKey;
    private FileName fileName;
    private String s3FileName;

    private PostThumbnail(String thumbnailKey, FileName fileName, String s3FileName) {
        this.thumbnailKey = thumbnailKey;
        this.fileName = fileName;
        this.s3FileName = s3FileName;
    }

    public String getThumbnailKey() {
        return thumbnailKey;
    }

    public FileName getFileName() {
        return fileName;
    }

    public String getS3FileName() {
        return s3FileName;
    }

    public static PostThumbnail from(String thumbnailKey, MultipartFile thumbnail) {
        String originalFileName = thumbnail.getOriginalFilename();

        return new PostThumbnail(thumbnailKey, new FileName(originalFileName),thumbnailKey + "_" + originalFileName);
    }

}
