package com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime;

import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartTimePostRepository extends JpaRepository<PartTimePostEntity, Long> {

    Optional<PartTimePostEntity> findByPostKeyAndUserKey(Long postKey, UserEntity userKey);

    List<PartTimePostEntity> findAllByUserKey(UserEntity userKey);

    PartTimePostEntity findByPostKey(Long postKey);

    void deleteByPostKey(Long postKey);

}
