package com.bezkoder.spring.security.jwt.security.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Workday;
import com.bezkoder.spring.security.jwt.models.Worktime;
import com.bezkoder.spring.security.jwt.payload.request.WorkdayRequest;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.WorkdayRepository;
import com.bezkoder.spring.security.jwt.repository.WorktimeRepository;
@Service
public class WorkdayService {
	@Autowired
	WorkdayRepository workdayRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	WorktimeRepository worktimeRepository;
	
	public List<Workday> getAllWorkday(){
		return workdayRepository.findAll();
	}
	
	public Workday getWorkdayById(Long id) {
		return workdayRepository.findById(id).orElse(null);
	}
	
	public List<Workday> getWorkdayByDoctor(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return workdayRepository.findAllByDoctor(doctor);
	}
	
	@Transactional
	public Workday createWorkday(WorkdayRequest workdayRequest) {
		Doctor doctor = doctorRepository.findById(workdayRequest.getDoctor()).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		
		Workday workday = new Workday(workdayRequest.getDay());
		workday.setDoctor(doctor);		
		Workday saveWorkday = workdayRepository.save(workday);
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalTime time = LocalTime.parse("10:00"); 
		LocalTime end = LocalTime.parse("17:20"); 
		while ( time.compareTo(end) != 0) {
//			time.plusMinutes(20);
//			System.out.println(time);
			Worktime worktime = new Worktime(time);
			worktime.setDoctor(doctor);
			worktime.setWorkday(saveWorkday);
			time = time.plusMinutes(20);
			worktimeRepository.save(worktime);
		}

		return saveWorkday;
	}
	
	@Transactional
	public void deleteWorkdayById(Long id) {
		Workday workday = workdayRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Workday is not found."));
		worktimeRepository.deleteAllByWorkday(workday);
		workdayRepository.deleteById(id);
	}
	
}
