package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.Room;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Long>{
	List<Room> findByIsActive(Boolean isActive);

}
