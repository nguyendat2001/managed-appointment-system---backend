package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.models.Disease;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	List<Doctor> findByDepartment(Department department);
	List<Doctor> findByAccount(Account account);
	List<Doctor> findByIsActive(Boolean isActive);
	List<Doctor> findByDepartmentAndIsActive(Department department,Boolean isActive);
}
