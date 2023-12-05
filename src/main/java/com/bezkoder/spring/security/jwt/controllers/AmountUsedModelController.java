package com.bezkoder.spring.security.jwt.controllers;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.AmountUsedModel;
import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.payload.request.AmoutUsedModelRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.AmoutUsedModelService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/amountusedmodels")
public class AmountUsedModelController {
	@Autowired
	AmoutUsedModelService amountUsedModelService;
	
	@GetMapping
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AmountUsedModel>> getAllAppointment(){
		List<AmountUsedModel> amountUsedModel = amountUsedModelService.getAll();
		return new ResponseEntity<>(amountUsedModel, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<AmountUsedModel> getOneAppointment(@PathVariable Long id){
		AmountUsedModel amountUsedModel = amountUsedModelService.getById(id);
		if (amountUsedModel != null) {
            return new ResponseEntity<>(amountUsedModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-model/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<AmountUsedModel>> getAppointmentByDoctorId(@PathVariable Long id){
		List<AmountUsedModel> amountUsedModel = amountUsedModelService.getByModel(id);
		return new ResponseEntity<>(amountUsedModel, HttpStatus.OK);
	}
	
	@GetMapping("/by-YearMonth/{YearMonth}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<AmountUsedModel>> getbyYearMonth(@PathVariable YearMonth YearMonth){
		List<AmountUsedModel> amountUsedModel = amountUsedModelService.getByYearMonth(YearMonth);
		if (amountUsedModel != null) {
			return new ResponseEntity<>(amountUsedModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/by-YearMonthAndModel")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<AmountUsedModel> getbyYearMonthandmodel(@RequestBody AmoutUsedModelRequest amoutUsedModelRequest){
		AmountUsedModel amountUsedModel = amountUsedModelService.getByModelAndLocalDate(amoutUsedModelRequest);
		return new ResponseEntity<>(amountUsedModel, HttpStatus.OK);
        
	}
	
	@PostMapping
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createAppointment(@RequestBody AmoutUsedModelRequest amoutUsedModelRequest){
		AmountUsedModel amountUsedModel = amountUsedModelService.createAmountUsedModel(amoutUsedModelRequest);
		return ResponseEntity.ok(new MessageResponse("Add AmountUsedModel successfully!"));
	}
	
	@PostMapping("/otherAmountUsedModel/")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createnewAmountUsedModel(@RequestBody AmoutUsedModelRequest amoutUsedModelRequest){
		AmountUsedModel amountUsedModel = amountUsedModelService.create2AmountUsedModel(amoutUsedModelRequest);
		return ResponseEntity.ok(new MessageResponse("Add AmountUsedModel successfully!"));
	}
}
