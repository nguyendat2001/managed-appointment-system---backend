package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Certificate;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Ward;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Long>  {
	List<Certificate> findByDoctor(Doctor doctor);
	List<Certificate> findByWard(Ward ward);
}
