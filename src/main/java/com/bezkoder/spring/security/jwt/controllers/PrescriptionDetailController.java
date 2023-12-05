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

import com.bezkoder.spring.security.jwt.models.PrescriptionDetails;
import com.bezkoder.spring.security.jwt.payload.request.PrescriptionDetailsRequest;
//import com.bezkoder.spring.security.jwt.payload.request.PrescriptionDetailRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.PrescriptionDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prescriptiondetail")
public class PrescriptionDetailController {
	@Autowired
	PrescriptionDetailService prescriptionDetailService;
	
	@GetMapping
	public ResponseEntity<List<PrescriptionDetails>> getAllPrescriptionDetails(){
		List<PrescriptionDetails> prescriptionDetails = prescriptionDetailService.getAllPrescriptionDetails();
		return new ResponseEntity<>(prescriptionDetails, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PrescriptionDetails> getbyid(@PathVariable Long id){
		PrescriptionDetails hyperParamResults = prescriptionDetailService.getPrescriptionDetailsById(id);
		return new ResponseEntity<>(hyperParamResults, HttpStatus.OK);
	}
	
	@GetMapping("/getbyprescription/{id}")
	public ResponseEntity<List<PrescriptionDetails>> getAllActivePrescriptionDetails(@PathVariable Long id){
		List<PrescriptionDetails> hyperParamResults = prescriptionDetailService.getAllByPrescription(id);
		return new ResponseEntity<>(hyperParamResults, HttpStatus.OK);
	}
//
//	
	@DeleteMapping("/deletebyprescription/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deletebyPrescriptionDetails(@PathVariable Long id){
		prescriptionDetailService.deletePrescriptionDetailsByprescription(id);
		return ResponseEntity.ok(new MessageResponse("delete prescriptionDetail successfully!"));
	}
	
	@PostMapping
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createPrescriptionDetails(@RequestBody PrescriptionDetailsRequest hyperParamResultRequest){
		PrescriptionDetails createPrescriptionDetails = prescriptionDetailService.createPrescriptionDetails(hyperParamResultRequest);
		return ResponseEntity.ok(new MessageResponse("Add prescriptionDetail successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deletePrescriptionDetails(@PathVariable Long id){
		prescriptionDetailService.deletePrescriptionDetailsById(id);
		return ResponseEntity.ok(new MessageResponse("delete prescriptionDetail successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updatePrescriptionDetails(@PathVariable Long id,@RequestBody PrescriptionDetailsRequest hyperParamResultRequest){
		PrescriptionDetails updatePrescriptionDetails = prescriptionDetailService.updatePrescriptionDetails(id, hyperParamResultRequest);
		if (updatePrescriptionDetails != null) {
            return ResponseEntity.ok(new MessageResponse("Update prescriptionDetail successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
