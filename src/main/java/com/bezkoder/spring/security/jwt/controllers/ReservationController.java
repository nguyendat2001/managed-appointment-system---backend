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

import com.bezkoder.spring.security.jwt.models.Reservation;
import com.bezkoder.spring.security.jwt.payload.request.ReservationRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.ReservationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Reservation>> getAllReservation(){
		List<Reservation> Reservations = reservationService.getAllReservation();
		return new ResponseEntity<>(Reservations, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Reservation> getOneReservation(@PathVariable Long id){
		Reservation Reservation = reservationService.getReservationById(id);
		if (Reservation != null) {
            return new ResponseEntity<>(Reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-user/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Reservation>> getReservationByUserId(@PathVariable Long userId){
		List<Reservation> reservations = reservationService.getAllReservationByUserId(userId);
		if (reservations != null) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@GetMapping("/by-bed/{bedId}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Reservation>> getReservationByBedId(@PathVariable Long bedId){
		List<Reservation> reservations = reservationService.getAllReservationByBedId(bedId);
		if (reservations != null) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/acceptReservation/{Id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> acceptReservation(@PathVariable Long Id){
		Reservation Reservation = reservationService.setAcceptReservation(Id);
		if (Reservation != null) {
			return ResponseEntity.ok(new MessageResponse("Reservate Reservation successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createReservation(@RequestBody ReservationRequest reservationRequest){
		Reservation createReservation = reservationService.createReservation(reservationRequest);
		return ResponseEntity.ok(new MessageResponse("Add Reservation successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteReservation(@PathVariable Long id){
		reservationService.deleteReservationById(id);
		return ResponseEntity.ok(new MessageResponse("delete Reservation successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateReservation(@PathVariable Long id,@RequestBody ReservationRequest reservationRequest){
		Reservation updateReservation = reservationService.updateReservation(id, reservationRequest);
		if (updateReservation != null) {
            return ResponseEntity.ok(new MessageResponse("Update Reservation successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
