package com.bezkoder.spring.security.jwt.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.UserRequest;
import com.bezkoder.spring.security.jwt.repository.AccountRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;

@Service
public class UserService {
	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public List<User> getActiveUser(){
		return userRepository.findByIsActive(true);
	}
	
	public List<User> getAllUserbyAccount(Long accountId){
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			return userRepository.findByAccount(account);
		}
		return null;
	}
	
	public Optional<User> getOneUser(Long id){
		return userRepository.findById(id);
	}
	
	@Transactional
	public User createUser(UserRequest userRequest) {
//		Set<Department> department = new HashSet<>();
		Set<Account> account = new HashSet<>();

//		System.out.println(userRequest.getAccount());
		
		Account accountUser = accountRepository.findById(userRequest.getAccount()).orElseThrow(() 
				-> new RuntimeException("Error: Account is not found."));
		
//		System.out.println(accountUser.getEmail());
		
		User User = new User(userRequest.getFullname(),userRequest.getPhone(), userRequest.getBirthday(),
				userRequest.getAddress(), userRequest.getGender());
		
		account.add(accountUser);
		User.setAccount(account);
		
		return userRepository.save(User);
	}
	
	@Transactional
	public User updateUser(Long id, UserRequest userRequest) {
		Optional<User> UserOptional = userRepository.findById(id);
		if(UserOptional.isPresent()) {
//			User exsitUser = UserOptional.get();
			User updateOneUser = new User(userRequest.getFullname(),userRequest.getPhone(), userRequest.getBirthday(),
					userRequest.getAddress(), userRequest.getGender());
			
			Set<Account> account = new HashSet<>();
			
			Account accountUser = accountRepository.findById(userRequest.getAccount()).orElseThrow(() 
					-> new RuntimeException("Error: Account is not found."));

			account.add(accountUser);
			
			updateOneUser.setAccount(account);
			updateOneUser.setId(UserOptional.get().getId());
			return userRepository.save(updateOneUser);
		}
		return null;
	}
	
	@Transactional
	public User activeUser(Long id) {
		Optional<User> UserOptional = userRepository.findById(id);
		if(UserOptional.isPresent()) {
			User user = UserOptional.get();
			
			user.setActive(true);
			return userRepository.save(user);
		}
		return null;
	}
	
	@Transactional
	public User inactiveUser(Long id) {
		Optional<User> UserOptional = userRepository.findById(id);
		if(UserOptional.isPresent()) {
			User user = UserOptional.get();
			
			user.setActive(false);
			return userRepository.save(user);
		}
		return null;
	}
	
	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
}
