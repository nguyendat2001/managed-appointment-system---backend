package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long>{
	Disease findByName(String name);
	List<Disease> findByIsActive(Boolean isActive);
}
