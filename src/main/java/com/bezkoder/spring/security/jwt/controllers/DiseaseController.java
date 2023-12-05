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

import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.payload.request.DiseaseRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DiseaseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
	@Autowired
	DiseaseService diseaseService;
	
	@GetMapping
	public ResponseEntity<List<Disease>> getAllDisease(){
		List<Disease> Diseases = diseaseService.getAllDisease();
		return new ResponseEntity<>(Diseases, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Disease> getOneDisease(@PathVariable Long id){
		Disease Disease = diseaseService.getDiseaseById(id);
		if (Disease != null) {
            return new ResponseEntity<>(Disease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/getAllActive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Disease>> getAllActiveDisease(){
		List<Disease> Diseases = diseaseService.getAllActive();
		return new ResponseEntity<>(Diseases, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Disease> activeByRoomId(@PathVariable Long id){
		Disease disease = diseaseService.active(id);
		if (disease != null) {
			return new ResponseEntity<>(disease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Disease> inactiveByRoomId(@PathVariable Long id){
		Disease disease = diseaseService.inactive(id);
		if (disease != null) {
			return new ResponseEntity<>(disease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/getbyname/{name}")
	public ResponseEntity<Disease> getOneDiseaseByName(@PathVariable String name){
		Disease Disease = diseaseService.getDiseaseByName(name);
		if (Disease != null) {
            return new ResponseEntity<>(Disease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDisease(@RequestBody DiseaseRequest diseaseRequest){
		Disease createDisease = diseaseService.createDisease(diseaseRequest);
		return ResponseEntity.ok(new MessageResponse("Add Disease successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDisease(@PathVariable Long id){
		diseaseService.deleteDiseaseById(id);
		return ResponseEntity.ok(new MessageResponse("delete Disease successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDisease(@PathVariable Long id,@RequestBody DiseaseRequest diseaseRequest){
		Disease updateDisease = diseaseService.updateDisease(id, diseaseRequest);
		if (updateDisease != null) {
            return ResponseEntity.ok(new MessageResponse("Update Disease successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
