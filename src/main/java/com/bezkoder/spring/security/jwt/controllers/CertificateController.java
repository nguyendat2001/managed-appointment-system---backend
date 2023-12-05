package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.bezkoder.spring.security.jwt.models.Certificate;
import com.bezkoder.spring.security.jwt.payload.request.CertificateRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.CertificateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
	@Autowired
	CertificateService certificateService;

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Certificate>> getAllCertificate(){
		List<Certificate> Certificates = certificateService.getAllCertificate();
		return new ResponseEntity<>(Certificates, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Certificate> getOneCertificate(@PathVariable Long id){
		Certificate Certificate = certificateService.getCertificateById(id);
		if (Certificate != null) {
            return new ResponseEntity<>(Certificate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/findallbydoctor/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Certificate>> getdoctorCertificate(@PathVariable Long id){
		List<Certificate> Certificates = certificateService.getAllCertificateByDoctor(id);
		if (Certificates != null) {
            return new ResponseEntity<>(Certificates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	
	@GetMapping("/findallbyward/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Certificate>> getwardCertificate(@PathVariable Long id){
		List<Certificate> Certificates = certificateService.getAllCertificateByWard(id);
		if (Certificates != null) {
            return new ResponseEntity<>(Certificates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Certificate> createCertificate(@RequestBody CertificateRequest CertificateRequest){
//		certificateService.createCertificate(CertificateRequest);
		Certificate certificate = certificateService.createCertificate(CertificateRequest);
		if (certificate != null) {
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//		return ResponseEntity.ok(new MessageResponse("Add Certificate successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteCertificate(@PathVariable Long id){
		certificateService.deleteCertificateById(id);
		return ResponseEntity.ok(new MessageResponse("delete Certificate successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Certificate> updateCertificate(@PathVariable Long id, @RequestBody CertificateRequest CertificateRequest){
		Certificate certificate = certificateService.updateCertificate(id,CertificateRequest);
		if (certificate != null) {
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/upload_file/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> uploadCertificate(@PathVariable Long id,@RequestParam("file") MultipartFile file){
//		certificateService.createCertificate(CertificateRequest);
		Certificate certificate = certificateService.uploadfile(id,file);
		return ResponseEntity.ok(new MessageResponse("upload Certificate successfully!"));
	}
	
	@PostMapping("/update_file/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateCertificate(@PathVariable Long id,@RequestParam("file") MultipartFile file){
//		certificateService.createCertificate(CertificateRequest);
		Certificate certificate = certificateService.updatefile(id,file);
		return ResponseEntity.ok(new MessageResponse("update Certificate successfully!"));
	}
}
