package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.Reservation;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	List<Reservation> findByUser(User user);
	List<Reservation> findByBed(Bed bed);
}
