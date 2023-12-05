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

import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.payload.request.DepartmentRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DepartmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Department>> getAllDepartment(){
		List<Department> departments = departmentService.getAllDepartment();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@GetMapping("/getallactive")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Department>> getAllActiveDepartment(){
		List<Department> departments = departmentService.getAllActiveDepartment();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@GetMapping("/getallinactive")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Department>> getAllInactiveDepartment(){
		List<Department> departments = departmentService.getAllInactiveDepartment();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> InactiveDepartment(@PathVariable Long id){
		departmentService.inactiveDepartment(id);
		return ResponseEntity.ok(new MessageResponse("inactive department successfully!"));
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> activeDepartment(@PathVariable Long id){
		departmentService.activeDepartment(id);
		return ResponseEntity.ok(new MessageResponse("active department successfully!"));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Department> getOneDepartment(@PathVariable Long id){
		Department department = departmentService.getDepartmentById(id);
		if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest){
		Department createDepartment = departmentService.createDepartment(departmentRequest);
		return ResponseEntity.ok(new MessageResponse("Add department successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDepartment(@PathVariable Long id){
		departmentService.deleteDepartmentById(id);
		return ResponseEntity.ok(new MessageResponse("delete department successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDepartment(@PathVariable Long id,@RequestBody DepartmentRequest departmentRequest){
		Department updateDepartment = departmentService.updateDepartment(id, departmentRequest);
		if (updateDepartment != null) {
            return ResponseEntity.ok(new MessageResponse("Update department successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
}
