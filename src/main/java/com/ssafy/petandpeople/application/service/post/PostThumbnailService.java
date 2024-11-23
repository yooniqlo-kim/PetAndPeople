package com.ssafy.petandpeople.application.service.post;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.petandpeople.application.converter.post.PostThumbnailConverter;
import com.ssafy.petandpeople.application.dto.post.PostThumbnailDto;
import com.ssafy.petandpeople.config.S3Config;
import com.ssafy.petandpeople.common.exception.post.InvalidFileFormatException;
import com.ssafy.petandpeople.common.exception.post.ThumbnailNotDeletedException;
import com.ssafy.petandpeople.common.exception.post.ThumbnailNotSavedException;
import com.ssafy.petandpeople.common.exception.post.ThumbnailNotUploadedException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostThumbnailEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.post.PostThumbnailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostThumbnailService {
    private static final String AWS_BUCKET_NAME = S3Config.getBucketName();

    private final AmazonS3 amazonS3;
    private final PostThumbnailRepository postThumbnailRepository;

    public PostThumbnailService(AmazonS3 amazonS3, PostThumbnailRepository postThumbnailRepository) {
        this.amazonS3 = amazonS3;
        this.postThumbnailRepository = postThumbnailRepository;
    }

    @Transactional
    public boolean saveThumbnail(MultipartFile thumbnail, PostEntity postKey) {
        validateImageFile(thumbnail);

        PostThumbnailDto postThumbnailDto = PostThumbnailDto.from(thumbnail, postKey);
        String thumbnailPath = saveThumbnailToS3(postThumbnailDto, thumbnail);

        PostThumbnailEntity thumbnailEntity =
                PostThumbnailConverter.dtoToPostThumbnailEntity(postThumbnailDto, thumbnailPath);
        savePostThumbnailToDB(thumbnailEntity);

        return true;
    }

    @Transactional
    public boolean updateThumbnail(MultipartFile thumbnail, PostEntity postKey) {
        validateImageFile(thumbnail);

        PostThumbnailEntity savedThumbnailEntity = findSavedThumbnailEntity(postKey);
        deleteThumbnailFromS3(savedThumbnailEntity.getS3FileName());

        PostThumbnailDto postThumbnailDto = PostThumbnailDto.from(thumbnail, savedThumbnailEntity.getThumbnailKey(), postKey);
        String filePath = saveThumbnailToS3(postThumbnailDto, thumbnail);

        PostThumbnailEntity thumbnailEntity = PostThumbnailConverter.dtoToPostThumbnailEntity(postThumbnailDto, filePath);
        savePostThumbnailToDB(thumbnailEntity);

        return true;
    }

    public void deleteThumbnail(PostEntity postKey) {
        PostThumbnailEntity postThumbnailEntity = postThumbnailRepository.findByPostKey(postKey);

        deleteThumbnailFromS3(postThumbnailEntity.getS3FileName());
        postThumbnailRepository.deleteByThumbnailKey(postThumbnailEntity.getThumbnailKey());
    }

    public String findThumbnailPath(PostEntity postKey) {
        return postThumbnailRepository.findByPostKey(postKey).getThumbnailPath();
    }

    private void validateImageFile(MultipartFile thumbnail) {
        String contentType = thumbnail.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidFileFormatException();
        }
    }

    private PostThumbnailEntity findSavedThumbnailEntity(PostEntity postKey) {
        return postThumbnailRepository.findByPostKey(postKey);
    }

    private String saveThumbnailToS3(PostThumbnailDto postThumbnailDto, MultipartFile thumbnail) {
        String s3FileName = postThumbnailDto.getS3FileName();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + postThumbnailDto.getExtension());

        try {
            amazonS3.putObject(new PutObjectRequest(AWS_BUCKET_NAME, s3FileName, thumbnail.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new ThumbnailNotUploadedException(e.getMessage());
        }

        return amazonS3.getUrl(AWS_BUCKET_NAME, s3FileName).toString();
    }

    private void savePostThumbnailToDB(PostThumbnailEntity thumbnailEntity) {
        try {
            postThumbnailRepository.save(thumbnailEntity);
        } catch (Exception e) {
            deleteThumbnailFromS3(thumbnailEntity.getS3FileName());
            throw new ThumbnailNotSavedException(e.getMessage());
        }
    }

    private void deleteThumbnailFromS3(String s3FileName) {
        try {
            amazonS3.deleteObject(AWS_BUCKET_NAME, s3FileName);
        } catch (Exception e) {
            throw new ThumbnailNotDeletedException(e.getMessage());
        }
    }

}
