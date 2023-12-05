package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Caculation;
import com.bezkoder.spring.security.jwt.payload.request.CaculationRequest;
import com.bezkoder.spring.security.jwt.repository.CaculationRepository;

@Service
public class CaculationService {
	@Autowired
	CaculationRepository caculationRepository;
	
	public List<Caculation> getAllCaculation(){
		return caculationRepository.findAll();
	}
	
	public Caculation getCaculationById(Long id) {
		return caculationRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Caculation createCaculation(CaculationRequest caculationRequest) {
		Caculation caculation = new Caculation(caculationRequest.getName(),caculationRequest.getDescribe());
		return caculationRepository.save(caculation);
	}
	
	@Transactional
	public void deleteCaculationById(Long id) {
		caculationRepository.deleteById(id);
	}
	
	@Transactional
    public Caculation updateCaculation(Long id, CaculationRequest caculationRequest) {
        Optional<Caculation> CaculationOptional = caculationRepository.findById(id);
        if (CaculationOptional.isPresent()) {
        	Caculation existingCaculation = CaculationOptional.get();
        	existingCaculation.setName(caculationRequest.getName());
        	existingCaculation.setDescribe(caculationRequest.getDescribe());
            return caculationRepository.save(existingCaculation);
        }
        return null;
    }
}
