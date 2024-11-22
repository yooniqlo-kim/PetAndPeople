package com.ssafy.petandpeople.application.service.job.parttime;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.petandpeople.application.converter.job.ThumbnailConverter;
import com.ssafy.petandpeople.application.dto.job.ThumbnailDto;
import com.ssafy.petandpeople.config.S3Config;
import com.ssafy.petandpeople.common.exception.job.InvalidFileFormatException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotDeletedException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotSavedException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotUploadedException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostThumbnailEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostThumbnailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PartTimePostThumbnailService {
    private static final String BUCKET_NAME = S3Config.getBucketName();

    private final AmazonS3 amazonS3;
    private final PartTimePostThumbnailRepository partTimePostThumbnailRepository;

    public PartTimePostThumbnailService(AmazonS3 amazonS3, PartTimePostThumbnailRepository partTimePostThumbnailRepository) {
        this.amazonS3 = amazonS3;
        this.partTimePostThumbnailRepository = partTimePostThumbnailRepository;
    }

    @Transactional
    public boolean saveThumbnail(MultipartFile thumbnail, PartTimePostEntity postKey) {
        validateImageFile(thumbnail);

        ThumbnailDto thumbnailDto = ThumbnailDto.create(thumbnail, postKey);
        String thumbnailPath = saveThumbnailToS3(thumbnailDto, thumbnail);

        PartTimePostThumbnailEntity thumbnailEntity =
                ThumbnailConverter.dtoToPartTimePostThumbnailEntity(thumbnailDto, thumbnailPath);
        savePartTimePostThumbnailToDB(thumbnailEntity);

        return true;
    }

    @Transactional
    public boolean updateThumbnail(MultipartFile thumbnail, PartTimePostEntity postKey) {
        validateImageFile(thumbnail);

        PartTimePostThumbnailEntity savedThumbnailEntity = findSavedThumbnailEntity(postKey);
        deleteThumbnailFromS3(savedThumbnailEntity.getS3FileName());

        ThumbnailDto thumbnailDto = ThumbnailDto.create(thumbnail, savedThumbnailEntity.getThumbnailKey(), postKey);
        String filePath = saveThumbnailToS3(thumbnailDto, thumbnail);

        PartTimePostThumbnailEntity thumbnailEntity = ThumbnailConverter.dtoToPartTimePostThumbnailEntity(thumbnailDto, filePath);
        savePartTimePostThumbnailToDB(thumbnailEntity);

        return true;
    }

    public void deleteThumbnail(PartTimePostEntity postKey) {
        PartTimePostThumbnailEntity partTimePostThumbnailEntity = partTimePostThumbnailRepository.findByPostKey(postKey);

        System.out.println(partTimePostThumbnailEntity.getS3FileName());

        deleteThumbnailFromS3(partTimePostThumbnailEntity.getS3FileName());
        partTimePostThumbnailRepository.deleteByThumbnailKey(partTimePostThumbnailEntity.getThumbnailKey());
    }

    public String findThumbnailPath(PartTimePostEntity postKey) {
        return partTimePostThumbnailRepository.findByPostKey(postKey).getThumbnailPath();
    }

    private void validateImageFile(MultipartFile thumbnail) {
        String contentType = thumbnail.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidFileFormatException();
        }
    }

    private PartTimePostThumbnailEntity findSavedThumbnailEntity(PartTimePostEntity postKey) {
        return partTimePostThumbnailRepository.findByPostKey(postKey);
    }

    private String saveThumbnailToS3(ThumbnailDto thumbnailDto, MultipartFile thumbnail) {
        String s3FileName = thumbnailDto.getS3FileName();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + thumbnailDto.getExtension());

        try {
            amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, s3FileName, thumbnail.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new ThumbnailNotUploadedException(e.getMessage());
        }

        return amazonS3.getUrl(BUCKET_NAME, s3FileName).toString();
    }

    private void savePartTimePostThumbnailToDB(PartTimePostThumbnailEntity thumbnailEntity) {
        try {
            partTimePostThumbnailRepository.save(thumbnailEntity);
        } catch (Exception e) {
            deleteThumbnailFromS3(thumbnailEntity.getS3FileName());
            throw new ThumbnailNotSavedException(e.getMessage());
        }
    }

    private void deleteThumbnailFromS3(String s3FileName) {
        try {
            amazonS3.deleteObject(BUCKET_NAME, s3FileName);
        } catch (Exception e) {
            throw new ThumbnailNotDeletedException(e.getMessage());
        }
    }

}
