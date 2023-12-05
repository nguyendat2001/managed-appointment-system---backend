package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.HyperParamResult;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.models.HyperParam;
import com.bezkoder.spring.security.jwt.payload.request.HyperParamResultRequest;
import com.bezkoder.spring.security.jwt.repository.HyperParamResultRepository;
import com.bezkoder.spring.security.jwt.repository.ModelRepository;
import com.bezkoder.spring.security.jwt.repository.HyperParamRepository;

@Service
public class HyperParamResultService {
	@Autowired
	HyperParamResultRepository hyperParamResultRepository;
	
	@Autowired
	HyperParamRepository hyperParamRepository;
	
	@Autowired
	ModelRepository modelRepository;
	
	public List<HyperParamResult> getAllHyperParamResult(){
		return hyperParamResultRepository.findAll();
	}

	public List<HyperParamResult> getAllHyperParamResultByModel(Long id){
		Model model = modelRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		return hyperParamResultRepository.findByModel(model);
	}
	
	
	public HyperParamResult getHyperParamResultById(Long id) {
		return hyperParamResultRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public HyperParamResult createHyperParamResult(HyperParamResultRequest hyperParamResultRequest) {
		System.out.println(hyperParamResultRequest.getHyperParam());
		HyperParam hyperParam = hyperParamRepository.findById(hyperParamResultRequest.getHyperParam()).orElseThrow(() 
				-> new RuntimeException("Error: HyperParam is not found."));
		
		Model model = modelRepository.findById(hyperParamResultRequest.getModel()).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		
		HyperParamResult hyperParamResult = new HyperParamResult( hyperParamResultRequest.getValue());
		hyperParamResult.setHyperParam(hyperParam);
		hyperParamResult.setModel(model);
		return hyperParamResultRepository.save(hyperParamResult);
	}
	
	@Transactional
	public void deleteHyperParamResultById(Long id) {
		hyperParamResultRepository.deleteById(id);
	}
	
	@Transactional
    public HyperParamResult updateHyperParamResult(Long id, HyperParamResultRequest hyperParamResultRequest) {
        Optional<HyperParamResult> HyperParamResultOptional = hyperParamResultRepository.findById(id);
        if (HyperParamResultOptional.isPresent()) {
        	HyperParamResult existingHyperParamResult = HyperParamResultOptional.get();
        	
        	HyperParam hyperParam = hyperParamRepository.findById(hyperParamResultRequest.getHyperParam()).orElseThrow(() 
    				-> new RuntimeException("Error: HyperParam is not found."));
    		
    		Model model = modelRepository.findById(hyperParamResultRequest.getModel()).orElseThrow(() 
    				-> new RuntimeException("Error: Model is not found."));
       		existingHyperParamResult.setHyperParam(hyperParam);
    		existingHyperParamResult.setModel(model);
    		existingHyperParamResult.setValue(hyperParamResultRequest.getValue());

            return hyperParamResultRepository.save(existingHyperParamResult);
        }
        return null;
    }
}
