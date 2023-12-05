package com.bezkoder.spring.security.jwt.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.payload.request.DoctorRequest;
import com.bezkoder.spring.security.jwt.payload.request.MailToDoctorRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DoctorService;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;
import com.bezkoder.spring.security.jwt.security.services.mailService.EmailSenderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@Autowired
	EmailSenderService emailSenderService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getAllDoctor(){
		List<Doctor> Doctors = doctorService.getAllDoctor();
		return new ResponseEntity<>(Doctors, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Doctor> getOneDoctor(@PathVariable Long id){
		Optional<Doctor> Doctor = doctorService.getOneDoctor(id);
		if (Doctor != null) {
            return new ResponseEntity<>(Doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/findbyDepartmentandisactive/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getByDepartmentIsActive(@PathVariable Long id){
		List<Doctor> Doctors = doctorService.getAllDoctorByDepartmentAndIsActive(id);
		if (Doctors != null) {
            return new ResponseEntity<>(Doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/findallactive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getActiveDoctor(){
		List<Doctor> Doctors = doctorService.getAllActiveDoctor();
		if (Doctors != null) {
            return new ResponseEntity<>(Doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/findallinactive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getInactiveDoctor(){
		List<Doctor> Doctors = doctorService.getAllInactiveDoctor();
		if (Doctors != null) {
            return new ResponseEntity<>(Doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> ActiveDoctor(@PathVariable Long id){
		Doctor Doctors = doctorService.activeDoctor(id);
		if (Doctors != null) {
            return ResponseEntity.ok(new MessageResponse("active Doctor successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> InactiveDoctor(@PathVariable Long id){
		Doctor Doctors = doctorService.inactiveDoctor(id);
		if (Doctors != null) {
            return ResponseEntity.ok(new MessageResponse("inactive Doctor successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-Department/{departmentId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getDoctorByDepartment(@PathVariable Long departmentId){
		List<Doctor> Doctors = doctorService.getAllDoctorbyDepartment(departmentId);
		if (Doctors != null) {
            return new ResponseEntity<>(Doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-Account/{accountId}")
//	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Doctor>> getDoctorByAccount(@PathVariable Long accountId){
		List<Doctor> Doctors = doctorService.getAllDoctorbyAccount(accountId);
		if (Doctors != null) {
            return new ResponseEntity<>(Doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/mailtodoctor")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> mailToDoctor(@RequestBody MailToDoctorRequest mailToDoctorRequest){
		emailSenderService.sendMailText(mailToDoctorRequest.getUsername(), mailToDoctorRequest.getEmail(),
				mailToDoctorRequest.getPassword(), mailToDoctorRequest.getFullname());
		return ResponseEntity.ok(new MessageResponse("mail to doctor successfully!"));
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDoctor(@RequestBody DoctorRequest doctorRequest
//			@RequestPart("avatar") MultipartFile avatar,
//			@RequestPart("birthday") LocalDate birthday,@RequestPart("address") String address,
//			@RequestPart("gender") Boolean gender,@RequestPart("degree") String degree,@RequestPart("experience") String experience,
//			@RequestPart("account") Long account,@RequestPart("department") Long department
			){
		System.out.println(doctorRequest.getDegree());
		doctorService.createDoctor(doctorRequest);
		return ResponseEntity.ok(new MessageResponse("Add Doctor successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDoctor(@PathVariable Long id){
		doctorService.deleteDoctorById(id);
		return ResponseEntity.ok(new MessageResponse("delete Doctor successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDoctor(@PathVariable Long id,@RequestBody DoctorRequest DoctorRequest){
		Doctor updateDoctor = doctorService.updateDoctor(id, DoctorRequest);
		if (updateDoctor != null) {
            return ResponseEntity.ok(new MessageResponse("Update Doctor successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/upload-avatar/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> uploadAvatarDoctor(@PathVariable Long id,@RequestParam("file") MultipartFile file){
//		file
//		String uniqueID = UUID.randomUUID().toString();
		Doctor updateDoctor = doctorService.uploadAvatarDoctor(id, file);
		if (updateDoctor != null) {
            return ResponseEntity.ok(new MessageResponse("Upload avatar Doctor successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/update-avatar/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateAvatarDoctor(@PathVariable Long id,@RequestParam("file") MultipartFile file){
		Doctor updateDoctor = doctorService.updateAvatarDoctor(id, file);
		if (updateDoctor != null) {
            return ResponseEntity.ok(new MessageResponse("Update avatar Doctor successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
