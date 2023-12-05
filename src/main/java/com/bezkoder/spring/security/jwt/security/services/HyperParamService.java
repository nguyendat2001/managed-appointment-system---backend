package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.HyperParam;
import com.bezkoder.spring.security.jwt.payload.request.HyperParamRequest;
import com.bezkoder.spring.security.jwt.repository.HyperParamRepository;

@Service
public class HyperParamService {
	@Autowired
	HyperParamRepository hyperParamRepository;
	
	public List<HyperParam> getAllHyperParam(){
		return hyperParamRepository.findAll();
	}
	
	public HyperParam getHyperParamById(Long id) {
		return hyperParamRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public HyperParam createHyperParam(HyperParamRequest hyperParamRequest) {
		HyperParam hyperParam = new HyperParam(hyperParamRequest.getName(),hyperParamRequest.getNote());
		return hyperParamRepository.save(hyperParam);
	}
	
	@Transactional
	public void deleteHyperParamById(Long id) {
		hyperParamRepository.deleteById(id);
	}
	
	@Transactional
    public HyperParam updateHyperParam(Long id, HyperParamRequest hyperParamRequest) {
        Optional<HyperParam> HyperParamOptional = hyperParamRepository.findById(id);
        if (HyperParamOptional.isPresent()) {
        	HyperParam existingHyperParam = HyperParamOptional.get();
        	existingHyperParam.setName(hyperParamRequest.getName());
        	existingHyperParam.setNote(hyperParamRequest.getNote());
            return hyperParamRepository.save(existingHyperParam);
        }
        return null;
    }
	
}
