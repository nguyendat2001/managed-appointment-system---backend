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

import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.payload.request.DatasetRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DatasetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/datasets")
public class DatasetController {
	@Autowired
	DatasetService datasetService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Dataset>> getAllDataset(){
		List<Dataset> Datasets = datasetService.getAllDataset();
		return new ResponseEntity<>(Datasets, HttpStatus.OK);
	}
	

	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Dataset> getOneDataset(@PathVariable Long id){
		Dataset Dataset = datasetService.getDatasetById(id);
		if (Dataset != null) {
            return new ResponseEntity<>(Dataset, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/getAllActive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Dataset>> getAllActiveDataset(){
		List<Dataset> Datasets = datasetService.getAllActive();
		return new ResponseEntity<>(Datasets, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Dataset> activeByRoomId(@PathVariable Long id){
		Dataset dataset = datasetService.active(id);
		if (dataset != null) {
			return new ResponseEntity<>(dataset, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Dataset> inactiveByRoomId(@PathVariable Long id){
		Dataset dataset = datasetService.inactive(id);
		if (dataset != null) {
			return new ResponseEntity<>(dataset, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDataset(@RequestBody DatasetRequest DatasetRequest){
		Dataset createDataset = datasetService.createDataset(DatasetRequest);
		return ResponseEntity.ok(new MessageResponse("Add Dataset successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDataset(@PathVariable Long id){
		datasetService.deleteDatasetById(id);
		return ResponseEntity.ok(new MessageResponse("delete Dataset successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDataset(@PathVariable Long id,@RequestBody DatasetRequest DatasetRequest){
		Dataset updateDataset = datasetService.updateDataset(id, DatasetRequest);
		if (updateDataset != null) {
            return ResponseEntity.ok(new MessageResponse("Update Dataset successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
