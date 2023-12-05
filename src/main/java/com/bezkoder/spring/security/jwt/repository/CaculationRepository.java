package com.bezkoder.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Caculation;

@Repository
public interface CaculationRepository extends JpaRepository<Caculation,Long> {
	
}
