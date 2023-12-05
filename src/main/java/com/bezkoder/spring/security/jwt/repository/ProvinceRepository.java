package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Province;

@Repository
public interface ProvinceRepository  extends JpaRepository<Province, Long>{
	List<Province> findByIsActive(Boolean isActive);
}
