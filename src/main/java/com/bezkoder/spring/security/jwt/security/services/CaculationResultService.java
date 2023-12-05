package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Caculation;
import com.bezkoder.spring.security.jwt.models.CaculationResult;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.payload.request.CaculationResultRequest;
import com.bezkoder.spring.security.jwt.repository.CaculationRepository;
import com.bezkoder.spring.security.jwt.repository.CaculationResultRepository;
import com.bezkoder.spring.security.jwt.repository.ModelRepository;

@Service
public class CaculationResultService {
	@Autowired
	CaculationResultRepository caculationResultRepository;
	
	@Autowired
	CaculationRepository caculationRepository;
	
	@Autowired
	ModelRepository modelRepository;
	
	public List<CaculationResult> getAllCaculationResult(){
		return caculationResultRepository.findAll();
	}

	public List<CaculationResult> getAllCaculationResultByModel(Long id){
		Model model = modelRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		return caculationResultRepository.findByModel(model);
	}
	
	
	public CaculationResult getCaculationResultById(Long id) {
		return caculationResultRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public CaculationResult createCaculationResult(CaculationResultRequest caculationResultRequest) {
		Caculation caculation = caculationRepository.findById(caculationResultRequest.getCaculation()).orElseThrow(() 
				-> new RuntimeException("Error: Caculation is not found."));
		
		Model model = modelRepository.findById(caculationResultRequest.getModel()).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		
		CaculationResult caculationResult = new CaculationResult( caculationResultRequest.getValueOnTrain(),caculationResultRequest.getValueOnTest());
		caculationResult.setCaculation(caculation);
		caculationResult.setModel(model);
		return caculationResultRepository.save(caculationResult);
	}
	
	@Transactional
	public void deleteCaculationResultById(Long id) {
		caculationResultRepository.deleteById(id);
	}
	
	@Transactional
    public CaculationResult updateCaculationResult(Long id, CaculationResultRequest caculationResultRequest) {
        Optional<CaculationResult> CaculationResultOptional = caculationResultRepository.findById(id);
        if (CaculationResultOptional.isPresent()) {
        	CaculationResult existingCaculationResult = CaculationResultOptional.get();
        	
        	Caculation caculation = caculationRepository.findById(caculationResultRequest.getCaculation()).orElseThrow(() 
    				-> new RuntimeException("Error: Caculation is not found."));
    		
    		Model model = modelRepository.findById(caculationResultRequest.getModel()).orElseThrow(() 
    				-> new RuntimeException("Error: Model is not found."));
    		
    		existingCaculationResult.setCaculation(caculation);
    		existingCaculationResult.setModel(model);
    		existingCaculationResult.setValueOnTrain(caculationResultRequest.getValueOnTrain());
    		existingCaculationResult.setValueOnTest(caculationResultRequest.getValueOnTest());

            return caculationResultRepository.save(existingCaculationResult);
        }
        return null;
    }
}
