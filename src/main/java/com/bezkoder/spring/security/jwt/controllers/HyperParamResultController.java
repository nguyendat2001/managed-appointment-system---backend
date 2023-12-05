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

import com.bezkoder.spring.security.jwt.models.HyperParamResult;
import com.bezkoder.spring.security.jwt.payload.request.HyperParamResultRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.HyperParamResultService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hyperparamsresult")
public class HyperParamResultController {
	@Autowired
	HyperParamResultService hyperParamResultService;
	
	@GetMapping
	public ResponseEntity<List<HyperParamResult>> getAllHyperParamResult(){
		List<HyperParamResult> hyperParamResults = hyperParamResultService.getAllHyperParamResult();
		return new ResponseEntity<>(hyperParamResults, HttpStatus.OK);
	}
	
	@GetMapping("/getallbymodel/{id}")
	public ResponseEntity<List<HyperParamResult>> getAllActiveHyperParamResult(@PathVariable Long id){
		List<HyperParamResult> hyperParamResults = hyperParamResultService.getAllHyperParamResultByModel(id);
		return new ResponseEntity<>(hyperParamResults, HttpStatus.OK);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<HyperParamResult> getOneHyperParamResult(@PathVariable Long id){
		HyperParamResult hyperParamResult = hyperParamResultService.getHyperParamResultById(id);
		if (hyperParamResult != null) {
            return new ResponseEntity<>(hyperParamResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createHyperParamResult(@RequestBody HyperParamResultRequest hyperParamResultRequest){
		HyperParamResult createHyperParamResult = hyperParamResultService.createHyperParamResult(hyperParamResultRequest);
		return ResponseEntity.ok(new MessageResponse("Add hyperParamResult successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteHyperParamResult(@PathVariable Long id){
		hyperParamResultService.deleteHyperParamResultById(id);
		return ResponseEntity.ok(new MessageResponse("delete hyperParamResult successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateHyperParamResult(@PathVariable Long id,@RequestBody HyperParamResultRequest hyperParamResultRequest){
		HyperParamResult updateHyperParamResult = hyperParamResultService.updateHyperParamResult(id, hyperParamResultRequest);
		if (updateHyperParamResult != null) {
            return ResponseEntity.ok(new MessageResponse("Update hyperParamResult successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
