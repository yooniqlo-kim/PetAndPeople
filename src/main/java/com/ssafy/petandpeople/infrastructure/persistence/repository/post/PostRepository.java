package com.ssafy.petandpeople.infrastructure.persistence.repository.post;

import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Optional<PostEntity> findByPostKeyAndUserKey(Long postKey, UserEntity userKey);

    List<PostEntity> findAllByUserKey(UserEntity userKey);

    PostEntity findByPostKey(Long postKey);

    void deleteByPostKey(Long postKey);

}
