package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Province;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
	List<District> findByIsActive(Boolean isActive);
	List<District> findAllByProvinceAndIsActive(Province province,Boolean isActive);
}
