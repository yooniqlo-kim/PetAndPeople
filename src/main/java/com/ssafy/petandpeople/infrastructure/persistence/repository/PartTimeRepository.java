package com.ssafy.petandpeople.infrastructure.persistence.repository;

import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTimeRepository extends JpaRepository<PartTimeEntity, Long> {

}
