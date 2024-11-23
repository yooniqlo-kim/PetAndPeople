package com.ssafy.petandpeople.infrastructure.persistence.repository.user;

import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);

    UserEntity findByUserKey(Long id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLoginAt = :lastLoginAt WHERE u.userKey = :userKey")
    void updateLastLoginAt(@Param("userKey") Long userKey, @Param("lastLoginAt") LocalDateTime lastLoginAt);

}
