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

import com.bezkoder.spring.security.jwt.models.Medicine;
import com.bezkoder.spring.security.jwt.payload.request.MedicineRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.MedicineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
	@Autowired
	MedicineService medicineService;
	
	@GetMapping
	public ResponseEntity<List<Medicine>> getAllMedicine(){
		List<Medicine> Medicines = medicineService.getAllMedicine();
		return new ResponseEntity<>(Medicines, HttpStatus.OK);
	}
	
	@GetMapping("/getallactive")
	public ResponseEntity<List<Medicine>> getAllActiveMedicine(){
		List<Medicine> Medicines = medicineService.getAllActiveMedicine();
		return new ResponseEntity<>(Medicines, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> ActiveMedicine(@PathVariable Long id){
		medicineService.activeMedicine(id);
		return ResponseEntity.ok(new MessageResponse("Active Medicine successfully!"));
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> InactiveMedicine(@PathVariable Long id){
		medicineService.inactiveMedicine(id);
		return ResponseEntity.ok(new MessageResponse("Inactive Medicine successfully!"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medicine> getOneMedicine(@PathVariable Long id){
		Medicine Medicine = medicineService.getMedicineById(id);
		if (Medicine != null) {
            return new ResponseEntity<>(Medicine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createMedicine(@RequestBody MedicineRequest medicineRequest){
		Medicine createMedicine = medicineService.createMedicine(medicineRequest);
		return ResponseEntity.ok(new MessageResponse("Add Medicine successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteMedicine(@PathVariable Long id){
		medicineService.deleteMedicineById(id);
		return ResponseEntity.ok(new MessageResponse("delete Medicine successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateMedicine(@PathVariable Long id,@RequestBody MedicineRequest medicineRequest){
		Medicine updateMedicine = medicineService.updateMedicine(id, medicineRequest);
		if (updateMedicine != null) {
            return ResponseEntity.ok(new MessageResponse("Update Medicine successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
