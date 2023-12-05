package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.Medicine;
import com.bezkoder.spring.security.jwt.models.PrescriptionDetails;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetails, Long>{
	List<PrescriptionDetails> findByPrescription(Prescription prescription);
	List<PrescriptionDetails> findByMedicine(Medicine medicine);
	void deleteByPrescription(Prescription prescription);
}
