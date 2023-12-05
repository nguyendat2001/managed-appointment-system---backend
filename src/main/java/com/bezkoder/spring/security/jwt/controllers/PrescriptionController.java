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

import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.PrescriptionDetails;
import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.payload.request.PrescriptionRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.PrescriptionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
	@Autowired
	PrescriptionService prescriptionService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getAllPrescription(){
		List<Prescription> Prescriptions = prescriptionService.getAllPrescription();
		return new ResponseEntity<>(Prescriptions, HttpStatus.OK);
	}
	
	@GetMapping("/getallactive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getAllActivePrescription(){
		List<Prescription> Prescriptions = prescriptionService.getActivePrescription();
		return new ResponseEntity<>(Prescriptions, HttpStatus.OK);
	}
	
	@GetMapping("/getByDoctorAndActive/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getAllDoctorActivePrescription(@PathVariable Long id){
		List<Prescription> Prescriptions = prescriptionService.findbyDoctorAndActive(id);
		return new ResponseEntity<>(Prescriptions, HttpStatus.OK);
	}
	
	@GetMapping("/getByUserAndActive/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getAllUserActivePrescription(@PathVariable Long id){
		List<Prescription> Prescriptions = prescriptionService.findbyUserAndActive(id);
		return new ResponseEntity<>(Prescriptions, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> activePrescription(@PathVariable Long id){
		prescriptionService.activePrescription(id);
		return ResponseEntity.ok(new MessageResponse("Active Prescription successfully!"));
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> inactivePrescription(@PathVariable Long id){
		prescriptionService.inactivePrescription(id);
		return ResponseEntity.ok(new MessageResponse("Inactive Prescription successfully!"));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Prescription> getOnePrescription(@PathVariable Long id){
		Prescription prescription = prescriptionService.getPrescriptionById(id);
		if (prescription != null) {
            return new ResponseEntity<>(prescription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-doctorId/{doctorId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getPrescriptionByDoctorId(@PathVariable Long doctorId){
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptionByDoctorId(doctorId);
		if (prescriptions != null) {
			return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-userId/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Prescription>> getPrescriptionByUserId(@PathVariable Long userId){
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptionByUserId(userId);
		if (prescriptions != null) {
			return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest){
		prescriptionService.createPrescription(prescriptionRequest);
		return ResponseEntity.ok(new MessageResponse("Add Prescription successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deletePrescription(@PathVariable Long id){
		prescriptionService.deletePrescriptionById(id);
		return ResponseEntity.ok(new MessageResponse("delete Prescription successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updatePrescription(@PathVariable Long id,@RequestBody PrescriptionRequest prescriptionRequest){
		List<PrescriptionDetails> updatePrescription = prescriptionService.updatePrescription(id, prescriptionRequest);
		if (updatePrescription != null) {
            return ResponseEntity.ok(new MessageResponse("Update Prescription successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
