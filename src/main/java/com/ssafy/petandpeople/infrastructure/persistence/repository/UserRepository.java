package com.ssafy.petandpeople.infrastructure.persistence.repository;

import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findAllByUserKey(Long userKey);

}
