package com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime;

import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostThumbnailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTimePostThumbnailRepository extends JpaRepository<PartTimePostThumbnailEntity, String> {

    PartTimePostThumbnailEntity findByPostKey(PartTimePostEntity postKey);

    PartTimePostThumbnailEntity findByThumbnailKey(String thumbnailKey);

    void deleteByThumbnailKey(String thumbnailKey);

}
`