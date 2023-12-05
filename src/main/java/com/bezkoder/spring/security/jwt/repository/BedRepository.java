package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.Room;

@Repository
public interface BedRepository extends JpaRepository<Bed,Long> {
	List<Bed> findByRoom(Room room);
	void deleteByRoom(Room existingRoom);
	
	List<Bed> findByIsAvailable(Boolean isAvailable);
}
