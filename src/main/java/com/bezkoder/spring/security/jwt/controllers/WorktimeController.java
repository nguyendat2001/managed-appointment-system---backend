package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.Worktime;
import com.bezkoder.spring.security.jwt.payload.request.WorktimeRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.WorktimeService;
import com.bezkoder.spring.security.jwt.security.services.WardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/worktimes")
public class WorktimeController {
	@Autowired
	WorktimeService worktimeService;
	
	@GetMapping
	public ResponseEntity<List<Worktime>> getAllWorktime(){
		List<Worktime> Worktimes = worktimeService.getAllWorktime();
		return new ResponseEntity<>(Worktimes, HttpStatus.OK);
	}
	
	@GetMapping("/getAllAvailableWorktime")
	public ResponseEntity<List<Worktime>> getAllAvailableWorktime(){
		List<Worktime> Worktimes = worktimeService.getAllAvailableWorktime();
		return new ResponseEntity<>(Worktimes, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByDoctor/{id}")
	public ResponseEntity<List<Worktime>> getAllByDoctor(@PathVariable Long id){
		List<Worktime> Worktimes = worktimeService.getAllByDoctor(id);
		return new ResponseEntity<>(Worktimes, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByWorkdate/{id}")
	public ResponseEntity<List<Worktime>> getAllByWorkdate(@PathVariable Long id){
		List<Worktime> Worktimes = worktimeService.getAllByWorkday(id);
		return new ResponseEntity<>(Worktimes, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByWorkdateAndIsAvailable/{id}")
	public ResponseEntity<List<Worktime>> getAllByWorkdateAndIsAvailable(@PathVariable Long id){
		List<Worktime> Worktimes = worktimeService.getAllByWorkdayAndIsAvailable(id);
		return new ResponseEntity<>(Worktimes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Worktime> getOneWorktime(@PathVariable Long id){
		Worktime Worktime = worktimeService.getWorktimeById(id);
		if (Worktime != null) {
            return new ResponseEntity<>(Worktime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

}
