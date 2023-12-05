package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
	List<Prescription> findByDoctor(Doctor doctor);
	List<Prescription> findByUser(User user);
	List<Prescription> findByIsActive(Boolean isActive);
	List<Prescription> findByDoctorAndIsActive(Doctor doctor,Boolean isActive);
	List<Prescription> findByUserAndIsActive(User user,Boolean isActive);
}
