package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.models.Worktime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	List<Appointment> findByDoctor(Doctor doctor);
	
	List<Appointment> findByUser(User user);
	
	List<Appointment> findByWorktime(Worktime worktime);
}
