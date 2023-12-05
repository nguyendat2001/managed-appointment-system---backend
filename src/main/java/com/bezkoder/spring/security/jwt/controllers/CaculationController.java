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

import com.bezkoder.spring.security.jwt.models.Caculation;
import com.bezkoder.spring.security.jwt.payload.request.CaculationRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.CaculationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/calculations")
public class CaculationController {
	@Autowired
	CaculationService caculationService;
	
	@GetMapping
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Caculation>> getAllCaculation(){
		List<Caculation> caculations = caculationService.getAllCaculation();
		return new ResponseEntity<>(caculations, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Caculation> getOneCaculation(@PathVariable Long id){
		Caculation caculation = caculationService.getCaculationById(id);
		if (caculation != null) {
            return new ResponseEntity<>(caculation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createCaculation(@RequestBody CaculationRequest caculationRequest){
		Caculation createCaculation = caculationService.createCaculation(caculationRequest);
		return ResponseEntity.ok(new MessageResponse("Add caculation successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteCaculation(@PathVariable Long id){
		caculationService.deleteCaculationById(id);
		return ResponseEntity.ok(new MessageResponse("delete caculation successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateCaculation(@PathVariable Long id,@RequestBody CaculationRequest caculationRequest){
		Caculation updateCaculation = caculationService.updateCaculation(id, caculationRequest);
		if (updateCaculation != null) {
            return ResponseEntity.ok(new MessageResponse("Update caculation successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
