package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long>{
	List<Ward> findByIsActive(Boolean isActive);
	List<Ward> findAllByDistrictAndIsActive(District district,Boolean isActive);
}
