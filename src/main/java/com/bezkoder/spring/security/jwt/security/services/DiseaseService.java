package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.payload.request.DiseaseRequest;
import com.bezkoder.spring.security.jwt.repository.DiseaseRepository;

@Service
public class DiseaseService {
	@Autowired
	DiseaseRepository diseaseRepository;
	
	public List<Disease> getAllDisease(){
		return diseaseRepository.findAll();
	}
	
	public Disease getDiseaseById(Long id) {
		return diseaseRepository.findById(id).orElse(null);
	}
	
	public Disease getDiseaseByName(String name) {
		return diseaseRepository.findByName(name);
	}
	
	@Transactional
	public Disease createDisease(DiseaseRequest diseaseRequest) {
		Disease disease = new Disease(diseaseRequest.getName(),diseaseRequest.getDescription(),
										diseaseRequest.getSymptom(),diseaseRequest.getReason(),
										diseaseRequest.getTreaments(),diseaseRequest.getPrevent());
		return diseaseRepository.save(disease);
	}
	
	@Transactional
	public void deleteDiseaseById(Long id) {
		diseaseRepository.deleteById(id);
	}
	
	@Transactional
    public Disease updateDisease(Long id, DiseaseRequest diseaseRequest) {
        Optional<Disease> DiseaseOptional = diseaseRepository.findById(id);
        if (DiseaseOptional.isPresent()) {
//        	Disease existingDisease = DiseaseOptional.get();
        	Disease disease = new Disease(diseaseRequest.getName(),diseaseRequest.getDescription(),
					diseaseRequest.getSymptom(),diseaseRequest.getReason(),
					diseaseRequest.getTreaments(),diseaseRequest.getPrevent());
        	disease.setId(id);
            return diseaseRepository.save(disease);
        }
        return null;
    }
	
	@Transactional
    public Disease active(Long id) {
        Optional<Disease> diseaseOptional = diseaseRepository.findById(id);
        if (diseaseOptional.isPresent()) {
        	Disease existingDisease = diseaseOptional.get();
        	existingDisease.setIsActive(true);
            return diseaseRepository.save(existingDisease);
        }
        return null;
	}
	
	@Transactional
    public Disease inactive(Long id) {
        Optional<Disease> diseaseOptional = diseaseRepository.findById(id);
        if (diseaseOptional.isPresent()) {
        	Disease existingDisease = diseaseOptional.get();
        	existingDisease.setIsActive(false);
            return diseaseRepository.save(existingDisease);
        }
        return null;
	}
	
	public List<Disease> getAllActive(){
		return diseaseRepository.findByIsActive(true);
	}
}
