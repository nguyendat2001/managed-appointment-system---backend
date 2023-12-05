package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByAccount(Account account);
	List<User> findByIsActive(Boolean isActive);
}
