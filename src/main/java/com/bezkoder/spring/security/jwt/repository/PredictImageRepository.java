package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.PredictImage;
import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface PredictImageRepository  extends JpaRepository<PredictImage, Long>{
	List<PredictImage> findByUser(User user);
}
