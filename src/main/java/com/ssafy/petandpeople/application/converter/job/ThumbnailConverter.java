package com.ssafy.petandpeople.application.converter.job;

import com.ssafy.petandpeople.application.dto.job.ThumbnailDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostThumbnailEntity;

public class ThumbnailConverter {

    public static PartTimePostThumbnailEntity dtoToPartTimePostThumbnailEntity(ThumbnailDto thumbnailDto, String filePath) {
        return new PartTimePostThumbnailEntity(
                thumbnailDto.getThumbnailKey(),
                thumbnailDto.getPostKey(),
                thumbnailDto.getOriginalFileName(),
                thumbnailDto.getS3FileName(),
                filePath
        );
    }

}
