package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Workday;
import com.bezkoder.spring.security.jwt.models.Worktime;

@Repository
public interface WorkdayRepository extends JpaRepository<Workday, Long>{
	List<Workday> findAllByDoctor(Doctor doctor);
}
