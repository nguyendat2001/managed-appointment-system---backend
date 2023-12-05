package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.Workday;
import com.bezkoder.spring.security.jwt.payload.request.WorkdayRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.WorkdayService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/workdays")
public class WorkdayController {
	@Autowired
	WorkdayService workdayService;
	
	@GetMapping
	public ResponseEntity<List<Workday>> getAllWorkday(){
		List<Workday> Workdays = workdayService.getAllWorkday();
		return new ResponseEntity<>(Workdays, HttpStatus.OK);
	}
	
	@GetMapping("/findByDoctor/{id}")
	public ResponseEntity<List<Workday>> getOneWorkdayByDoctor(@PathVariable Long id){
		List<Workday> Workdays = workdayService.getWorkdayByDoctor(id);
		if (Workdays != null) {
            return new ResponseEntity<>(Workdays, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Workday> getOneWorkday(@PathVariable Long id){
		Workday Workday = workdayService.getWorkdayById(id);
		if (Workday != null) {
            return new ResponseEntity<>(Workday, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createWorkday(@RequestBody WorkdayRequest WorkdayRequest){
		Workday createWorkday = workdayService.createWorkday(WorkdayRequest);
		return ResponseEntity.ok(new MessageResponse("Add Workday successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteWorkday(@PathVariable Long id){
		workdayService.deleteWorkdayById(id);
		return ResponseEntity.ok(new MessageResponse("delete Workday successfully!"));
	}
	
}
