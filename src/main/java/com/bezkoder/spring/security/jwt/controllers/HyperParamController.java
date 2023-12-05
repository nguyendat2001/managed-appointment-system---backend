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

import com.bezkoder.spring.security.jwt.models.HyperParam;
import com.bezkoder.spring.security.jwt.payload.request.HyperParamRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.HyperParamService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hyperparams")
public class HyperParamController {
	@Autowired
	HyperParamService hyperParamService;
	
	@GetMapping
	public ResponseEntity<List<HyperParam>> getAllHyperParam(){
		List<HyperParam> hyperParams = hyperParamService.getAllHyperParam();
		return new ResponseEntity<>(hyperParams, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HyperParam> getOneHyperParam(@PathVariable Long id){
		HyperParam hyperParam = hyperParamService.getHyperParamById(id);
		if (hyperParam != null) {
            return new ResponseEntity<>(hyperParam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createHyperParam(@RequestBody HyperParamRequest hyperParamRequest){
		HyperParam createHyperParam = hyperParamService.createHyperParam(hyperParamRequest);
		return ResponseEntity.ok(new MessageResponse("Add hyperParam successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteHyperParam(@PathVariable Long id){
		hyperParamService.deleteHyperParamById(id);
		return ResponseEntity.ok(new MessageResponse("delete hyperParam successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateHyperParam(@PathVariable Long id,@RequestBody HyperParamRequest hyperParamRequest){
		HyperParam updateHyperParam = hyperParamService.updateHyperParam(id, hyperParamRequest);
		if (updateHyperParam != null) {
            return ResponseEntity.ok(new MessageResponse("Update hyperParam successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
