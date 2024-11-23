package com.ssafy.petandpeople.infrastructure.persistence.repository.post;

import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostThumbnailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostThumbnailRepository extends JpaRepository<PostThumbnailEntity, String> {

    PostThumbnailEntity findByPostKey(PostEntity postKey);

    PostThumbnailEntity findByThumbnailKey(String thumbnailKey);

    void deleteByThumbnailKey(String thumbnailKey);

}