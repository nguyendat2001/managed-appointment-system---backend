package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	List<Department> findByIsActive(Boolean isActive);
}
