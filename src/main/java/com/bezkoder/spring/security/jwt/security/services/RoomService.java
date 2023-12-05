package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.payload.request.RoomRequest;
import com.bezkoder.spring.security.jwt.repository.BedRepository;
import com.bezkoder.spring.security.jwt.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	BedRepository bedRepository;
	
	public List<Room> getAllRoom(){
		return roomRepository.findAll();
	}
	
	public List<Room> getAllActiveRoom(){
		return roomRepository.findByIsActive(true);
	}
	
	public List<Room> getAllInactiveRoom(){
		return roomRepository.findByIsActive(false);
	}
	
	public Room getRoomById(Long id) {
		return roomRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Room createRoom(RoomRequest roomRequest) {
		Room room = new Room(roomRequest.getName(),roomRequest.getCapacity(),roomRequest.getIsPresidentRoom());
		Room createdRoom = roomRepository.save(room);
		for (int i = 0; i < roomRequest.getCapacity(); i++) {
			String name = roomRequest.getName() + "-B"+ (i+1);
			Bed bed = new Bed(name);
			bed.setRoom(createdRoom);
			bedRepository.save(bed);
		}
		return createdRoom;
	}
	
	@Transactional
	public void deleteRoomById(Long id) {
		Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
        	Room existingRoom = roomOptional.get();
        	bedRepository.deleteByRoom(existingRoom);
        	roomRepository.deleteById(id);
        }
		
	}
	
	@Transactional
    public Room updateRoom(Long id, RoomRequest roomRequest) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
        	Room existingRoom = roomOptional.get();
        	
        	bedRepository.deleteByRoom(existingRoom);
        	
        	existingRoom.setName(roomRequest.getName());
        	existingRoom.setCapacity(roomRequest.getCapacity());
        	existingRoom.setIsPresidentRoom(roomRequest.getIsPresidentRoom());
        	
        	for (int i = 0; i < roomRequest.getCapacity(); i++) {
    			String name = roomRequest.getName() + "-B"+ (i+1);
    			Bed bed = new Bed(name);
    			bed.setRoom(existingRoom);
    			bedRepository.save(bed);
    		}
        	
            return roomRepository.save(existingRoom);
        }
        return null;
    }
	
	@Transactional
    public Room activeRoom(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
        	Room existingRoom = roomOptional.get();
        	existingRoom.setActive(true);
            return roomRepository.save(existingRoom);
        }
        return null;
    }
	
	@Transactional
    public Room inActiveRoom(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
        	Room existingRoom = roomOptional.get();
        	existingRoom.setActive(false);
            return roomRepository.save(existingRoom);
        }
        return null;
    }
}
