package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.CaculationResult;
import com.bezkoder.spring.security.jwt.models.Model;

@Repository
public interface CaculationResultRepository extends JpaRepository<CaculationResult,Long> {
	List<CaculationResult> findByModel(Model model);
}
