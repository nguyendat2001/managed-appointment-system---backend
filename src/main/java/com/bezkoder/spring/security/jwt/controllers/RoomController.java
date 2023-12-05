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

import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.payload.request.RoomRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.RoomService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@GetMapping
	public ResponseEntity<List<Room>> getAllRoom(){
		List<Room> Rooms = roomService.getAllRoom();
		return new ResponseEntity<>(Rooms, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActive")
	public ResponseEntity<List<Room>> getAllActiveRoom(){
		List<Room> Rooms = roomService.getAllActiveRoom();
		return new ResponseEntity<>(Rooms, HttpStatus.OK);
	}
	
	@GetMapping("/getAllInactive")
	public ResponseEntity<List<Room>> getAllInactiveRoom(){
		List<Room> Rooms = roomService.getAllInactiveRoom();
		return new ResponseEntity<>(Rooms, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Room> getOneRoom(@PathVariable Long id){
		Room Room = roomService.getRoomById(id);
		if (Room != null) {
            return new ResponseEntity<>(Room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Room> activeRoom(@PathVariable Long id){
		Room Room = roomService.activeRoom(id);
		if (Room != null) {
            return new ResponseEntity<>(Room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Room> inActiveRoom(@PathVariable Long id){
		Room Room = roomService.inActiveRoom(id);
		if (Room != null) {
            return new ResponseEntity<>(Room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createRoom(@RequestBody RoomRequest roomRequest){
		Room createRoom = roomService.createRoom(roomRequest);
		return ResponseEntity.ok(new MessageResponse("Add Room successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteRoom(@PathVariable Long id){
		roomService.deleteRoomById(id);
		return ResponseEntity.ok(new MessageResponse("delete Room successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateRoom(@PathVariable Long id,@RequestBody RoomRequest roomRequest){
		Room updateRoom = roomService.updateRoom(id, roomRequest);
		if (updateRoom != null) {
            return ResponseEntity.ok(new MessageResponse("Update Room successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
