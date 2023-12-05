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

import com.bezkoder.spring.security.jwt.models.CaculationResult;
import com.bezkoder.spring.security.jwt.payload.request.CaculationResultRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.CaculationResultService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/caculationresults")
public class CaculationResultController {
	@Autowired
	CaculationResultService caculationResultService;
	
	@GetMapping
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<CaculationResult>> getAllCaculationResult(){
		List<CaculationResult> caculationResults = caculationResultService.getAllCaculationResult();
		return new ResponseEntity<>(caculationResults, HttpStatus.OK);
	}
	
	@GetMapping("/getallbymodel/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<CaculationResult>> getAllActiveCaculationResult(@PathVariable Long id){
		List<CaculationResult> caculationResults = caculationResultService.getAllCaculationResultByModel(id);
		return new ResponseEntity<>(caculationResults, HttpStatus.OK);
	}

	
	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<CaculationResult> getOneCaculationResult(@PathVariable Long id){
		CaculationResult caculationResult = caculationResultService.getCaculationResultById(id);
		if (caculationResult != null) {
            return new ResponseEntity<>(caculationResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createCaculationResult(@RequestBody CaculationResultRequest caculationResultRequest){
		CaculationResult createCaculationResult = caculationResultService.createCaculationResult(caculationResultRequest);
		return ResponseEntity.ok(new MessageResponse("Add caculationResult successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteCaculationResult(@PathVariable Long id){
		caculationResultService.deleteCaculationResultById(id);
		return ResponseEntity.ok(new MessageResponse("delete caculationResult successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateCaculationResult(@PathVariable Long id,@RequestBody CaculationResultRequest caculationResultRequest){
		CaculationResult updateCaculationResult = caculationResultService.updateCaculationResult(id, caculationResultRequest);
		if (updateCaculationResult != null) {
            return ResponseEntity.ok(new MessageResponse("Update caculationResult successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
