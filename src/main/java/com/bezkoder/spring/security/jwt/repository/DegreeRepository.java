package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree,Long> {
	List<Degree> findByIsActive(Boolean isActive);
}
