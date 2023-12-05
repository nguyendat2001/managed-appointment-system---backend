package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Dataset;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset,Long> {
	List<Dataset> findByIsActive(Boolean isActive);
}
