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

import com.bezkoder.spring.security.jwt.models.Degree;
import com.bezkoder.spring.security.jwt.payload.request.DegreeRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DegreeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/degrees")
public class DegreeController {
	@Autowired
	DegreeService degreeService;
	
	@GetMapping
	public ResponseEntity<List<Degree>> getAllDegree(){
		List<Degree> Degrees = degreeService.getAllDegree();
		return new ResponseEntity<>(Degrees, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActiveDegree")
	public ResponseEntity<List<Degree>> getAllAvailableDegree(){
		List<Degree> Degrees = degreeService.getAllIsActive();
		return new ResponseEntity<>(Degrees, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Degree> getOneDegree(@PathVariable Long id){
		Degree Degree = degreeService.getDegreeById(id);
		if (Degree != null) {
            return new ResponseEntity<>(Degree, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Degree> activeByRoomId(@PathVariable Long id){
		Degree degree = degreeService.activeDegree(id);
		if (degree != null) {
			return new ResponseEntity<>(degree, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Degree> inactiveByRoomId(@PathVariable Long id){
		Degree degree = degreeService.inactiveDegree(id);
		if (degree != null) {
			return new ResponseEntity<>(degree, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDegree(@RequestBody DegreeRequest DegreeRequest){
		Degree createDegree = degreeService.createDegree(DegreeRequest);
		return ResponseEntity.ok(new MessageResponse("Add Degree successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDegree(@PathVariable Long id){
		degreeService.deleteDegreeById(id);
		return ResponseEntity.ok(new MessageResponse("delete Degree successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDegree(@PathVariable Long id,@RequestBody DegreeRequest DegreeRequest){
		Degree updateDegree = degreeService.updateDegree(id, DegreeRequest);
		if (updateDegree != null) {
            return ResponseEntity.ok(new MessageResponse("Update Degree successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
