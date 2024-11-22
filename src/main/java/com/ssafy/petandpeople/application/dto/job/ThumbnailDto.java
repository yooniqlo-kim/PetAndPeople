package com.ssafy.petandpeople.application.dto.job;

import com.ssafy.petandpeople.common.exception.job.InvalidThumbnailExtensionException;
import com.ssafy.petandpeople.common.exception.job.OriginalFileNameIsEmptyException;
import com.ssafy.petandpeople.common.utils.UUIDGenerator;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ThumbnailDto {

    private final String thumbnailKey;
    private final PartTimePostEntity postKey;
    private final String originalFileName;
    private final String s3FileName;
    private final String extension;


    private ThumbnailDto(String thumbnailKey, PartTimePostEntity postKey, String originalFileName, String s3FileName, String extension) {
        this.thumbnailKey = thumbnailKey;
        this.originalFileName = originalFileName;
        this.s3FileName = s3FileName;
        this.extension = extension;
        this.postKey = postKey;
    }

    public static ThumbnailDto create(MultipartFile thumbnail, PartTimePostEntity postKey) {
        String originalFileName = thumbnail.getOriginalFilename();

        validateOriginalFileName(originalFileName);

        String thumbnailKey = createThumbnailKey();

        String s3FileName = thumbnailKey + "_" + originalFileName;

        String extension = extractExtension(originalFileName);

        return new ThumbnailDto(thumbnailKey, postKey, originalFileName, s3FileName, extension);
    }

    public static ThumbnailDto create(MultipartFile thumbnail, String thumbnailKey, PartTimePostEntity postKey) {
        String originalFileName = thumbnail.getOriginalFilename();

        validateOriginalFileName(originalFileName);

        String s3FileName = thumbnailKey + "_" + originalFileName;

        String extension = extractExtension(originalFileName);

        return new ThumbnailDto(thumbnailKey, postKey, originalFileName, s3FileName, extension);
    }

    private static String createThumbnailKey() {
        return UUIDGenerator.generateUUIDtoString();
    }

    private static void validateOriginalFileName(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new OriginalFileNameIsEmptyException();
        }
    }

    private static String extractExtension(String originalFileName) {
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == originalFileName.length() - 1) {
            throw new InvalidThumbnailExtensionException();
        }
        return originalFileName.substring(lastDotIndex + 1);
    }

    public String getThumbnailKey() {
        return thumbnailKey;
    }

    public PartTimePostEntity getPostKey() {
        return postKey;
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
