package com.ssafy.petandpeople.infrastructure.persistence.repository;

import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartTimeRepository extends JpaRepository<PartTimePostEntity, Long> {

    Optional<PartTimePostEntity> findByPostKeyAndUserKey(Long postKey, UserEntity userKey);

    List<PartTimePostEntity> findByUserKey(UserEntity userKey);

}
