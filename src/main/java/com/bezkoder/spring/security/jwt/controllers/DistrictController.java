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

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.payload.request.DistrictRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DistrictService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/districts")
public class DistrictController {
	@Autowired
	DistrictService districtService;
	
	@GetMapping
	public ResponseEntity<List<District>> getAllDistrict(){
		List<District> Districts = districtService.getAllDistrict();
		return new ResponseEntity<>(Districts, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActiveDistrict")
	public ResponseEntity<List<District>> getAllAvailableDistrict(){
		List<District> Districts = districtService.getAllIsactive();
		return new ResponseEntity<>(Districts, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByProvince/{id}")
	public ResponseEntity<List<District>> getAllAvailableDistrict(@PathVariable Long id){
		List<District> Districts = districtService.getAllByProvince(id);
		return new ResponseEntity<>(Districts, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<District> getOneDistrict(@PathVariable Long id){
		District District = districtService.getDistrictById(id);
		if (District != null) {
            return new ResponseEntity<>(District, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<District> activeByRoomId(@PathVariable Long id){
		District District = districtService.activeDistrict(id);
		if (District != null) {
			return new ResponseEntity<>(District, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<District> inactiveByRoomId(@PathVariable Long id){
		District District = districtService.inactiveDistrict(id);
		if (District != null) {
			return new ResponseEntity<>(District, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createDistrict(@RequestBody DistrictRequest DistrictRequest){
		District createDistrict = districtService.createDistrict(DistrictRequest);
		return ResponseEntity.ok(new MessageResponse("Add District successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteDistrict(@PathVariable Long id){
		districtService.deleteDistrictById(id);
		return ResponseEntity.ok(new MessageResponse("delete District successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateDistrict(@PathVariable Long id,@RequestBody DistrictRequest DistrictRequest){
		District updateDistrict = districtService.updateDistrict(id, DistrictRequest);
		if (updateDistrict != null) {
            return ResponseEntity.ok(new MessageResponse("Update District successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
