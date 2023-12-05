package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.HyperParamResult;
import com.bezkoder.spring.security.jwt.models.Model;

@Repository
public interface HyperParamResultRepository extends JpaRepository<HyperParamResult, Long>{
	List<HyperParamResult> findByModel(Model model);
}
