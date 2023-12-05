package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface DiagnosesRepository  extends JpaRepository<Diagnoses, Long>{
	List<Diagnoses> findAllByDoctor(Doctor doctor);
	List<Diagnoses> findAllByUser(User user);
	List<Diagnoses> findAllByDoctorAndIsActive(Doctor doctor,Boolean isActive);
	List<Diagnoses> findAllByUserAndIsActive(User user,Boolean isActive);
	List<Diagnoses> findByIsActive(Boolean isActive);
	List<Diagnoses> findByDisease(Disease disease);
}
