package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	List<Medicine> findByIsActive(Boolean isActive);
}
