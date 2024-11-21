package com.ssafy.petandpeople.infrastructure.persistence.repository.user;

import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserKey(Long id);

}
