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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.DiagnosesImage;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesImageRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DiagnosesImageService;

@CrossOrigin(origins = "*", maxAge = 3600000)
@RestController
@RequestMapping("/api/diagnoses_image")
public class DiagnosesImageController {
	@Autowired
	DiagnosesImageService diagnosesImageService;
	
	@GetMapping
	public ResponseEntity<List<DiagnosesImage>> getAllDiagnosesImage(){
		List<DiagnosesImage> diagnosesImages = diagnosesImageService.getAllDiagnosesImage();
		return new ResponseEntity<>(diagnosesImages, HttpStatus.OK);
	}
	
	@GetMapping("/getbydiagnoses/{id}")
	public ResponseEntity<List<DiagnosesImage>> getAllDiagnosesImage(@PathVariable Long id){
		List<DiagnosesImage> diagnosesImages = diagnosesImageService.getAllByDiagnoses(id);
		return new ResponseEntity<>(diagnosesImages, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DiagnosesImage> getOneDiagnosesImage(@PathVariable Long id){
		DiagnosesImage diagnosesImage = diagnosesImageService.getDiagnosesById(id);
		if (diagnosesImage != null) {
            return new ResponseEntity<>(diagnosesImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<DiagnosesImage> createDiagnosesImage(@RequestBody DiagnosesImageRequest diagnosesImageRequest){
		DiagnosesImage createDiagnosesImage = diagnosesImageService.createDiagnosesImage(diagnosesImageRequest);
		
		if (createDiagnosesImage != null) {
            return new ResponseEntity<>(createDiagnosesImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDiagnosesImage(@PathVariable Long id){
		diagnosesImageService.deleteDiagnosesImageById(id);
		return ResponseEntity.ok(new MessageResponse("delete diagnosesImage successfully!"));
	}
	
	@PostMapping("/upload-file/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){

		DiagnosesImage diagnosesImage = diagnosesImageService.uploadFile(id, file);
		if (diagnosesImage != null) {
            return ResponseEntity.ok(new MessageResponse("Upload File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/update-file/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){
		DiagnosesImage diagnosesImage = diagnosesImageService.updateFile(id, file);
		if (diagnosesImage != null) {
            return ResponseEntity.ok(new MessageResponse("Update File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
