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

import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DiagnosesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosesController {
	@Autowired
	DiagnosesService diagnosesService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllDiagnoses(){
		List<Diagnoses> diagnosess = diagnosesService.getAllDiagnoses();
		return new ResponseEntity<>(diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/getallbydoctor/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllDiagnosesbydoctor(@PathVariable Long id){
		List<Diagnoses> diagnosess = diagnosesService.getAllByDoctor(id);
		return new ResponseEntity<>(diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/getallbyuser/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllDiagnosesbyuser(@PathVariable Long id){
		List<Diagnoses> diagnosess = diagnosesService.getAllByUser(id);
		return new ResponseEntity<>(diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Diagnoses> getOneDiagnoses(@PathVariable Long id){
		Diagnoses diagnoses = diagnosesService.getDiagnosesById(id);
		if (diagnoses != null) {
            return new ResponseEntity<>(diagnoses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDiagnoses(@RequestBody DiagnosesRequest diagnosesRequest){
		Diagnoses createDiagnoses = diagnosesService.createDiagnoses(diagnosesRequest);
		return ResponseEntity.ok(new MessageResponse("Add diagnoses successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDiagnoses(@PathVariable Long id){
		diagnosesService.deleteDiagnosesById(id);
		return ResponseEntity.ok(new MessageResponse("delete diagnoses successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDiagnoses(@PathVariable Long id,@RequestBody DiagnosesRequest diagnosesRequest){
		Diagnoses updateDiagnoses = diagnosesService.updateDiagnoses(id, diagnosesRequest);
		if (updateDiagnoses != null) {
            return ResponseEntity.ok(new MessageResponse("Update diagnoses successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/getAllActive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllActiveDiagnoses(){
		List<Diagnoses> Diagnosess = diagnosesService.getAllActive();
		return new ResponseEntity<>(Diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/getByDoctorAndActive/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllDoctorActiveDiagnoses(@PathVariable Long id){
		List<Diagnoses> Diagnosess = diagnosesService.findbyDoctorAndActive(id);
		return new ResponseEntity<>(Diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/getByUserAndActive/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Diagnoses>> getAllUserActiveDiagnoses(@PathVariable Long id){
		List<Diagnoses> Diagnosess = diagnosesService.findbyUserAndActive(id);
		return new ResponseEntity<>(Diagnosess, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Diagnoses> activeByRoomId(@PathVariable Long id){
		Diagnoses diagnoses = diagnosesService.active(id);
		if (diagnoses != null) {
			return new ResponseEntity<>(diagnoses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Diagnoses> inactiveByRoomId(@PathVariable Long id){
		Diagnoses diagnoses = diagnosesService.inactive(id);
		if (diagnoses != null) {
			return new ResponseEntity<>(diagnoses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
