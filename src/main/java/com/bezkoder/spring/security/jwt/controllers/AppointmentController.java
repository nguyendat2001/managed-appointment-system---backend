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

import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.payload.request.AppointmentRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.AppointmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Appointment>> getAllAppointment(){
		List<Appointment> appointments = appointmentService.getAllAppointment();
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Appointment> getOneAppointment(@PathVariable Long id){
		Appointment appointment = appointmentService.getAppointmentById(id);
		if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-doctorId/{doctorId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Appointment>> getAppointmentByDoctorId(@PathVariable Long doctorId){
		List<Appointment> Appointments = appointmentService.getAllAppointmentByDoctorId(doctorId);
		System.out.println(Appointments);
		if (Appointments != null) {
			return new ResponseEntity<>(Appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-userId/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Appointment>> getAppointmentByUserId(@PathVariable Long userId){
		List<Appointment> Appointments = appointmentService.getAllAppointmentByUserId(userId);
		if (Appointments != null) {
			return new ResponseEntity<>(Appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest){
		Appointment createAppointment = appointmentService.createAppointment(appointmentRequest);
		if (createAppointment != null) {
            return ResponseEntity.ok(new MessageResponse("Add Appointment successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//		return ResponseEntity.ok(new MessageResponse("Add Appointment successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteAppointment(@PathVariable Long id){
		appointmentService.deleteAppointmentById(id);
		return ResponseEntity.ok(new MessageResponse("delete Appointment successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateAppointment(@PathVariable Long id,@RequestBody AppointmentRequest appointmentRequest){
		Appointment updateAppointment = appointmentService.updateAppointment(id, appointmentRequest);
		if (updateAppointment != null) {
            return ResponseEntity.ok(new MessageResponse("Update Appointment successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/upload-file/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){
		System.out.println("upload proof");
		Appointment appointment = appointmentService.uploadFile(id, file);
		if (appointment != null) {
            return ResponseEntity.ok(new MessageResponse("Upload File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/update-file/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateFile(@PathVariable Long id,@RequestParam("file") MultipartFile file){
		Appointment appointment = appointmentService.updateFile(id, file);
		if (appointment != null) {
            return ResponseEntity.ok(new MessageResponse("Update File successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
