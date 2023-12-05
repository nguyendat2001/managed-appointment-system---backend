package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.PredictImage;
import com.bezkoder.spring.security.jwt.payload.request.PredictImageRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.PredictImageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/predictimages")
public class PredictImageController {
	@Autowired
	PredictImageService predictImageService;
	
	@GetMapping
	public ResponseEntity<List<PredictImage>> getAllPredictImage(){
		List<PredictImage> predictImages = predictImageService.getAll();
		return new ResponseEntity<>(predictImages, HttpStatus.OK);
	}
	
	@GetMapping("/getbyuser/{id}")
	public ResponseEntity<List<PredictImage>> getAllPredictImage(@PathVariable Long id){
		List<PredictImage> predictImages = predictImageService.getAllByUser(id);
		return new ResponseEntity<>(predictImages, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PredictImage> getOnePredictImage(@PathVariable Long id){
		PredictImage predictImage = predictImageService.getById(id);
		if (predictImage != null) {
            return new ResponseEntity<>(predictImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<PredictImage> createPredictImage(@RequestBody PredictImageRequest predictImageRequest){
		PredictImage createPredictImage = predictImageService.create(predictImageRequest);
		
		if (createPredictImage != null) {
            return new ResponseEntity<>(createPredictImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/upload-file/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){

		PredictImage predictImage = predictImageService.uploadFile(id, file);
		if (predictImage != null) {
            return ResponseEntity.ok(new MessageResponse("Upload File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/update-file/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){
		PredictImage predictImage = predictImageService.updateFile(id, file);
		if (predictImage != null) {
            return ResponseEntity.ok(new MessageResponse("Update File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
