package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;
import java.util.Optional;

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

import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.UserRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> Users = userService.getAllUser();
		return new ResponseEntity<>(Users, HttpStatus.OK);
	}
	
	@GetMapping("/getallactive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getActiveUser(){
		List<User> Users = userService.getActiveUser();
		return new ResponseEntity<>(Users, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> activeUser(@PathVariable Long id){
		userService.activeUser(id);
		return ResponseEntity.ok(new MessageResponse("Active User successfully!"));
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> inactiveUser(@PathVariable Long id){
		userService.inactiveUser(id);
		return ResponseEntity.ok(new MessageResponse("Inactive User successfully!"));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<User> getOneUser(@PathVariable Long id){
		Optional<User> User = userService.getOneUser(id);
		if (User != null) {
            return new ResponseEntity<>(User.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/by-Account/{accountId}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUserByAccount(@PathVariable Long accountId){
		List<User> Users = userService.getAllUserbyAccount(accountId);
		if (Users != null) {
            return new ResponseEntity<>(Users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest UserRequest){
//		System.out.println(UserRequest);
		userService.createUser(UserRequest);
		return ResponseEntity.ok(new MessageResponse("Add User successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id){
		userService.deleteUserById(id);
		return ResponseEntity.ok(new MessageResponse("delete User successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateUser(@PathVariable Long id,@RequestBody UserRequest UserRequest){
		User updateUser = userService.updateUser(id, UserRequest);
		if (updateUser != null) {
            return ResponseEntity.ok(new MessageResponse("Update User successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
