package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.payload.request.BedRequest;
import com.bezkoder.spring.security.jwt.repository.BedRepository;
import com.bezkoder.spring.security.jwt.repository.RoomRepository;

@Service
public class BedService {
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	public List<Bed> getAllBed(){
		return bedRepository.findAll();
	}

	public List<Bed> getAllBedByRoomId(Long roomId){
		Room room = roomRepository.findById(roomId).orElseThrow(() 
				-> new RuntimeException("Error: Room is not found."));
		return bedRepository.findByRoom(room);
	}
	
	public List<Bed> getAllBedByAvailable(){
		return bedRepository.findByIsAvailable(true);
	}
	
	
	public Bed getBedById(Long id) {
		return bedRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Bed createBed(BedRequest BedRequest) {
		Room room = roomRepository.findById(BedRequest.getRoom()).orElseThrow(() 
				-> new RuntimeException("Error: Room is not found."));
		
		Bed bed = new Bed( BedRequest.getName());
		bed.setRoom(room);
		return bedRepository.save(bed);
	}
	
	@Transactional
	public void deleteBedById(Long id) {
		bedRepository.deleteById(id);
	}
	
	@Transactional
    public Bed reservatedBed(Long id) {
        Optional<Bed> BedOptional = bedRepository.findById(id);
        if (BedOptional.isPresent()) {
        	Bed existingBed = BedOptional.get();
        	
        	existingBed.setIsAvailable(false);
            return bedRepository.save(existingBed);
        }
        return null;
    }
	
	@Transactional
    public Bed unreservatedBed(Long id) {
        Optional<Bed> BedOptional = bedRepository.findById(id);
        if (BedOptional.isPresent()) {
        	Bed existingBed = BedOptional.get();
        	
        	existingBed.setIsAvailable(true);
            return bedRepository.save(existingBed);
        }
        return null;
    }
	
	
	@Transactional
    public Bed updateBed(Long id, BedRequest BedRequest) {
        Optional<Bed> BedOptional = bedRepository.findById(id);
        if (BedOptional.isPresent()) {
        	Bed existingBed = BedOptional.get();
        	Room room = roomRepository.findById(BedRequest.getRoom()).orElseThrow(() 
    				-> new RuntimeException("Error: Room is not found."));
        	existingBed.setName(BedRequest.getName());
        	existingBed.setIsAvailable(BedRequest.getIsAvailable());
        	existingBed.setRoom(room);
            return bedRepository.save(existingBed);
        }
        return null;
    }
}
