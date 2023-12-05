package com.bezkoder.spring.security.jwt.security.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.Reservation;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.ReservationRequest;
import com.bezkoder.spring.security.jwt.repository.BedRepository;
import com.bezkoder.spring.security.jwt.repository.ReservationRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.security.services.mailService.EmailSenderService;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	BedService bedService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	public List<Reservation> getAllReservation(){
		return reservationRepository.findAll();
	}

	public List<Reservation> getAllReservationByUserId(Long userId){
		User user = userRepository.findById(userId).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		return reservationRepository.findByUser(user);
	}
	
	public List<Reservation> getAllReservationByBedId(Long bedId){
		Bed bed = bedRepository.findById(bedId).orElseThrow(() 
				-> new RuntimeException("Error: Bed is not found."));
		return reservationRepository.findByBed(bed);
	}
	
	public Reservation getReservationById(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Reservation createReservation(ReservationRequest reservationRequest) {
		User user = userRepository.findById(reservationRequest.getUser()).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		
		Bed bed = bedRepository.findById(reservationRequest.getBed()).orElseThrow(() 
				-> new RuntimeException("Error: Bed is not found."));
		
//		bedService.reservatedBed(reservationRequest.getBed());
		
		Reservation reservation = new Reservation( reservationRequest.getCheckInDate());
		reservation.setUser(user);
		reservation.setBed(bed);
		return reservationRepository.save(reservation);
	}
	
	@Transactional
	public void deleteReservationById(Long id) {
		reservationRepository.deleteById(id);
	}
	
	@Transactional
    public Reservation updateReservation(Long id, ReservationRequest reservationRequest) {
		System.out.println(reservationRequest.getCheckOutDate());
		Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
        	Reservation existingReservation = reservationOptional.get();
        	
//        	bedService.unreservatedBed(existingReservation.getBed().getId());
        	
        	User user = userRepository.findById(reservationRequest.getUser()).orElseThrow(() 
    				-> new RuntimeException("Error: User is not found."));
    		
    		Bed bed = bedRepository.findById(reservationRequest.getBed()).orElseThrow(() 
    				-> new RuntimeException("Error: Bed is not found."));
    		
    		
    		existingReservation.setCheckInDate(reservationRequest.getCheckInDate());
    		existingReservation.setCheckOutDate(reservationRequest.getCheckOutDate());
    		if(reservationRequest.getCheckOutDate() != null) {
    			bedService.unreservatedBed(reservationRequest.getBed());
    		}
    		existingReservation.setUser(user);
    		existingReservation.setBed(bed);
    		return reservationRepository.save(existingReservation);
        }
        return null;
    }
	
	@Transactional
    public Reservation setAcceptReservation(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
        	Reservation existingReservation = reservationOptional.get();
        	
        	if(existingReservation.getBed().getIsAvailable()==true) {
//            	User user = userRepository.findById(existingReservation.get)
            	Set<Account> account = existingReservation.getUser().getAccount();
            	
            	String mailTo = "";
            	String userName = "";
            	
            	for (Account i : account) {
            		mailTo =i.getEmail();
            		userName = i.getUsername();
            	}        	
//            	Map<String, Object> properties = new HashMap<>();
//            	
//            	properties.put("userName", userName);
//            	properties.put("email", mailTo );
//            	properties.put("roomName", existingReservation.getBed().getRoom().getName());
//            	properties.put("bedName", existingReservation.getBed().getName());
//            	properties.put("checkInDate", existingReservation.getCheckInDate());
//            	properties.put("isPresidentRoom", existingReservation.getBed().getRoom().getIsPresidentRoom());
//            	properties.put("address", existingReservation.getUser().getAddress());
            	        	
//            	emailSenderService.sendMail(existingReservation.getUser().getAddress(), userName, mailTo, 
//            			existingReservation.getBed().getRoom().getName(), existingReservation.getBed().getName(), 
//                		existingReservation.getCheckInDate(), existingReservation.getBed().getRoom().getIsPresidentRoom());
            	
        		existingReservation.setIsAccept(true);
        		bedService.reservatedBed(existingReservation.getBed().getId());
        		return reservationRepository.save(existingReservation);
        	}
        	return null;
        }
        return null;
    }
}
