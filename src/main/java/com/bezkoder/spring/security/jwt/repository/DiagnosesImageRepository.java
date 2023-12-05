package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.DiagnosesImage;

@Repository
public interface DiagnosesImageRepository  extends JpaRepository<DiagnosesImage, Long>{
	List<DiagnosesImage> findAllByDiagnoses(Diagnoses diagnoses);
	void deleteAllByDiagnoses(Diagnoses diagnoses);
}
