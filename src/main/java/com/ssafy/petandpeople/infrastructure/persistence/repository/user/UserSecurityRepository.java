package com.ssafy.petandpeople.infrastructure.persistence.repository.user;

import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserSecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurityEntity, String> {

}