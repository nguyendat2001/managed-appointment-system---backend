package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Worktime;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Workday;

@Repository
public interface WorktimeRepository extends JpaRepository<Worktime, Long>{
	List<Worktime> findAllByWorkday(Workday workDay);
	List<Worktime> findAllByWorkdayAndIsAvailable(Workday workDay,Boolean isAvailable);
	List<Worktime> findAllByDoctor(Doctor doctor);
	List<Worktime> findAllByIsAvailable(Boolean isAvailable);
	void deleteAllByWorkday(Workday workday);
}
