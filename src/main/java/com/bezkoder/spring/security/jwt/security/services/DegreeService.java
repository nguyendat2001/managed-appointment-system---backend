package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Degree;
import com.bezkoder.spring.security.jwt.payload.request.DegreeRequest;
import com.bezkoder.spring.security.jwt.repository.DegreeRepository;

@Service
public class DegreeService {
	@Autowired
	DegreeRepository degreeRepository;
	
	public List<Degree> getAllDegree(){
		return degreeRepository.findAll();
	}
	
	public Degree getDegreeById(Long id) {
		return degreeRepository.findById(id).orElse(null);
	}
	
	public List<Degree> getAllIsActive() {
		return degreeRepository.findByIsActive(true);
	}
	
	@Transactional
	public Degree createDegree(DegreeRequest DegreeRequest) {
		Degree degree = new Degree(DegreeRequest.getName());
		return degreeRepository.save(degree);
	}
	
	@Transactional
	public void deleteDegreeById(Long id) {
		degreeRepository.deleteById(id);
	}
	
	@Transactional
    public Degree activeDegree(Long id) {
        Optional<Degree> degreeOptional = degreeRepository.findById(id);
        if (degreeOptional.isPresent()) {
        	Degree existingDegree = degreeOptional.get();
        	existingDegree.setIsActive(true);
            return degreeRepository.save(existingDegree);
        }
        return null;
	}
	
	@Transactional
    public Degree inactiveDegree(Long id) {
        Optional<Degree> degreeOptional = degreeRepository.findById(id);
        if (degreeOptional.isPresent()) {
        	Degree existingDegree = degreeOptional.get();
        	existingDegree.setIsActive(false);
            return degreeRepository.save(existingDegree);
        }
        return null;
	}
	
	@Transactional
    public Degree updateDegree(Long id, DegreeRequest degreeRequest) {
        Optional<Degree> degreeOptional = degreeRepository.findById(id);
        if (degreeOptional.isPresent()) {
        	Degree existingDegree = degreeOptional.get();
        	existingDegree.setName(degreeRequest.getName());
            return degreeRepository.save(existingDegree);
        }
        return null;
	}
}
