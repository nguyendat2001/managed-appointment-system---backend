package com.bezkoder.spring.security.jwt.controllers;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/loads")
public class AvatarController {
	@Autowired
	FilesStorageService storageService;
	
	 @GetMapping("avatars/files/{filename:.+}")
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	 
	 @GetMapping("avatars/avatar/{filename:.+}")
	  public ResponseEntity<ByteArrayResource> getAvatar(@PathVariable String filename) {
		 try {
			 Path file = Paths.get("uploads",filename);
//			 ByteArrayResurce file = storageService.getImage(filename);
		    byte[] buffer = Files.readAllBytes(file);  
		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		      if (byteArrayResource.exists() ) {
		        return ResponseEntity.ok()
		    	        .contentLength(buffer.length)
		    	        .contentType(MediaType.parseMediaType("image/png"))
		    	        .body(byteArrayResource);
		      } else {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		 }catch(Exception e) {
			 return null;
		 }
	  }
	 
	 @GetMapping("/certificates/{filename}")
	  public ResponseEntity<Resource> getCertifi(@PathVariable String filename) {
	    Resource file = storageService.load_cer(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	 
	 @GetMapping("/certificates_get/{filename}")
	  public ResponseEntity<ByteArrayResource> getcertifi(@PathVariable String filename) {
		 try {
			 Path file = Paths.get("certificates",filename);
		    byte[] buffer = Files.readAllBytes(file);  
		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		      if (byteArrayResource.exists() ) {
		        return ResponseEntity.ok()
		    	        .contentLength(buffer.length)
		    	        .contentType(MediaType.parseMediaType("application/pdf"))
		    	        .body(byteArrayResource);
		      } else {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		 }catch(Exception e) {
			 return null;
		 }
	  }
	 
//	 @GetMapping("/models/{filename}")
//	  public ResponseEntity<Resource> getModel(@PathVariable String filename) {
//	    Resource file = storageService.load_model(filename);
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	  }
	 
//	 @GetMapping("/models_get/{filename}")
//	  public ResponseEntity<ByteArrayResource> getModel_get(@PathVariable String filename) {
//		 try {
//			 Path file = Paths.get("models",filename);
//		    byte[] buffer = Files.readAllBytes(file);  
//		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
//		      if (byteArrayResource.exists() ) {
//		        return ResponseEntity.ok()
//		    	        .contentLength(buffer.length)
//		    	        .contentType(MediaType.parseMediaType("application/json"))
//		    	        .body(byteArrayResource);
//		      } else {
//		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		 }catch(Exception e) {
//			 return null;
//		 }
//	  }
	 
	 @GetMapping("/diagnoses/{filename}")
	  public ResponseEntity<Resource> getDiagnoses(@PathVariable String filename) {
	    Resource file = storageService.load_diag(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	 
	 @GetMapping("/diagnoses_get/{filename}")
	  public ResponseEntity<ByteArrayResource> getDiagnoses_get(@PathVariable String filename) {
		 try {
			 Path file = Paths.get("diagnoses",filename);
		    byte[] buffer = Files.readAllBytes(file);  
		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		      if (byteArrayResource.exists() ) {
		        return ResponseEntity.ok()
		    	        .contentLength(buffer.length)
		    	        .contentType(MediaType.parseMediaType("image/png"))
		    	        .body(byteArrayResource);
		      } else {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		 }catch(Exception e) {
			 return null;
		 }
	  }
	 
	 @GetMapping("/predicts/{filename}")
	  public ResponseEntity<Resource> getPredicts(@PathVariable String filename) {
	    Resource file = storageService.load_predict(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	 
	 @GetMapping("/predicts_get/{filename}")
	  public ResponseEntity<ByteArrayResource> getPredicts_get(@PathVariable String filename) {
		 try {
			 Path file = Paths.get("predicts",filename);
		    byte[] buffer = Files.readAllBytes(file);  
		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		      if (byteArrayResource.exists() ) {
		        return ResponseEntity.ok()
		    	        .contentLength(buffer.length)
		    	        .contentType(MediaType.parseMediaType("image/png"))
		    	        .body(byteArrayResource);
		      } else {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		 }catch(Exception e) {
			 return null;
		 }
	  }
	 
	 @GetMapping("/proofs/{filename}")
	  public ResponseEntity<Resource> getProofs(@PathVariable String filename) {
	    Resource file = storageService.load_proof(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	 
	 @GetMapping("/proofs_get/{filename}")
	  public ResponseEntity<ByteArrayResource> getProof_get(@PathVariable String filename) {
		 try {
			 Path file = Paths.get("appointments",filename);
		    byte[] buffer = Files.readAllBytes(file);  
		      ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		      if (byteArrayResource.exists() ) {
		        return ResponseEntity.ok()
		    	        .contentLength(buffer.length)
		    	        .contentType(MediaType.parseMediaType("image/png"))
		    	        .body(byteArrayResource);
		      } else {
		    	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		 }catch(Exception e) {
			 return null;
		 }
	  }
}
