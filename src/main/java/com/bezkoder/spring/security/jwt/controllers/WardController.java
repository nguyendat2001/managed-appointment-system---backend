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

import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.payload.request.WardRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.WardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wards")
public class WardController {
	@Autowired
	WardService WardService;
	
	@GetMapping
	public ResponseEntity<List<Ward>> getAllWard(){
		List<Ward> Wards = WardService.getAllWard();
		return new ResponseEntity<>(Wards, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActiveWard")
	public ResponseEntity<List<Ward>> getAllAvailableWard(){
		List<Ward> Wards = WardService.getAllIsactive();
		return new ResponseEntity<>(Wards, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByDistrict/{id}")
	public ResponseEntity<List<Ward>> getAllAvailableWard(@PathVariable Long id){
		List<Ward> Wards = WardService.getAllByDistrict(id);
		return new ResponseEntity<>(Wards, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ward> getOneWard(@PathVariable Long id){
		Ward Ward = WardService.getWardById(id);
		if (Ward != null) {
            return new ResponseEntity<>(Ward, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Ward> activeByRoomId(@PathVariable Long id){
		Ward Ward = WardService.activeWard(id);
		if (Ward != null) {
			return new ResponseEntity<>(Ward, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Ward> inactiveByRoomId(@PathVariable Long id){
		Ward Ward = WardService.inactiveWard(id);
		if (Ward != null) {
			return new ResponseEntity<>(Ward, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createWard(@RequestBody WardRequest WardRequest){
		Ward createWard = WardService.createWard(WardRequest);
		return ResponseEntity.ok(new MessageResponse("Add Ward successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteWard(@PathVariable Long id){
		WardService.deleteWardById(id);
		return ResponseEntity.ok(new MessageResponse("delete Ward successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateWard(@PathVariable Long id,@RequestBody WardRequest WardRequest){
		Ward updateWard = WardService.updateWard(id, WardRequest);
		if (updateWard != null) {
            return ResponseEntity.ok(new MessageResponse("Update Ward successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
