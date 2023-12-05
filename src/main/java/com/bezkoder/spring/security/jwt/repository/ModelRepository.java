package com.bezkoder.spring.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.models.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
	List<Model> findAllByDataset(Dataset dataset);
	List<Model> findByIsActive(Boolean isActive);
	List<Model> findAllByIsSegmentation(Boolean isSegmentation);
}
