package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.payload.request.BedRequest;
import com.bezkoder.spring.security.jwt.payload.request.BedRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.BedService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/beds")

public class BedController {
	@Autowired
	BedService bedService;
	
	@GetMapping
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Bed>> getAllBed(){
		List<Bed> Beds = bedService.getAllBed();
		return new ResponseEntity<>(Beds, HttpStatus.OK);
	}
	
//	@GetMapping("/getAllActiveBed")
//	public ResponseEntity<List<Bed>> getAllActiveBed(){
//		List<Bed> Beds = bedService.getAllBedByIsActive();
//		return new ResponseEntity<>(Beds, HttpStatus.OK);
//	}
	
	@GetMapping("/getAllAvailableBed")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Bed>> getAllAvailableBed(){
		List<Bed> Beds = bedService.getAllBedByAvailable();
		return new ResponseEntity<>(Beds, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Bed> getOneBed(@PathVariable Long id){
		Bed Bed = bedService.getBedById(id);
		if (Bed != null) {
            return new ResponseEntity<>(Bed, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-roomId/{roomId}")
//	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Bed>> getBedByRoomId(@PathVariable Long roomId){
		List<Bed> Beds = bedService.getAllBedByRoomId(roomId);
		if (Beds != null) {
			return new ResponseEntity<>(Beds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@GetMapping("/reservated-bed/{Id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> reservateByRoomId(@PathVariable Long Id){
		Bed bed = bedService.reservatedBed(Id);
		if (bed != null) {
			return ResponseEntity.ok(new MessageResponse("Reservate Bed successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createBed(@RequestBody BedRequest bedRequest){
		Bed createBed = bedService.createBed(bedRequest);
		return ResponseEntity.ok(new MessageResponse("Add Bed successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteBed(@PathVariable Long id){
		bedService.deleteBedById(id);
		return ResponseEntity.ok(new MessageResponse("delete Bed successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateBed(@PathVariable Long id,@RequestBody BedRequest bedRequest){
		Bed updateBed = bedService.updateBed(id, bedRequest);
		if (updateBed != null) {
            return ResponseEntity.ok(new MessageResponse("Update Bed successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
