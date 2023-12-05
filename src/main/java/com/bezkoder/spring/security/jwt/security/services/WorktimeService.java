package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Workday;
import com.bezkoder.spring.security.jwt.models.Worktime;
import com.bezkoder.spring.security.jwt.payload.request.WorktimeRequest;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.WorkdayRepository;
import com.bezkoder.spring.security.jwt.repository.WorktimeRepository;

@Service
public class WorktimeService {
	@Autowired
	WorktimeRepository worktimeRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	WorkdayRepository workdayRepository;
	
	public List<Worktime> getAllWorktime(){
		return worktimeRepository.findAll();
	}
	
	public List<Worktime> getAllAvailableWorktime(){
		return worktimeRepository.findAllByIsAvailable(true);
	}
	
	public List<Worktime> getAllByDoctor(Long id){
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return worktimeRepository.findAllByDoctor(doctor);
	}
	
	public List<Worktime> getAllByWorkday(Long id){
		Workday workday = workdayRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Workday is not found."));
		return worktimeRepository.findAllByWorkday(workday);
	}
	
	public List<Worktime> getAllByWorkdayAndIsAvailable(Long id){
		Workday workday = workdayRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Workday is not found."));
		return worktimeRepository.findAllByWorkdayAndIsAvailable(workday,true);
	}
	
	public Worktime getWorktimeById(Long id) {
		return worktimeRepository.findById(id).orElse(null);
	}
	
	
	@Transactional
    public Worktime inactiveWorktime(Long id) {
        Optional<Worktime> worktimeOptional = worktimeRepository.findById(id);
        if (worktimeOptional.isPresent()) {
        	Worktime existingWorktime = worktimeOptional.get();
        	existingWorktime.setIsAvailable(false);
            return worktimeRepository.save(existingWorktime);
        }
        return null;
    }
	
	@Transactional
    public Worktime activeWorktime(Long id) {
        Optional<Worktime> worktimeOptional = worktimeRepository.findById(id);
        if (worktimeOptional.isPresent()) {
        	Worktime existingWorktime = worktimeOptional.get();
        	existingWorktime.setIsAvailable(true);
            return worktimeRepository.save(existingWorktime);
        }
        return null;
    }
	
}
