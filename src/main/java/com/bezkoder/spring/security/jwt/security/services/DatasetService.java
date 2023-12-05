package com.bezkoder.spring.security.jwt.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.payload.request.DatasetRequest;
import com.bezkoder.spring.security.jwt.repository.DatasetRepository;
import com.bezkoder.spring.security.jwt.repository.DiseaseRepository;

@Service
public class DatasetService {
	@Autowired
	DatasetRepository datasetRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	public List<Dataset> getAllDataset(){
		return datasetRepository.findAll();
	}
	
	
	public Dataset getDatasetById(Long id) {
		return datasetRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Dataset createDataset(DatasetRequest datasetRequest) {
		Set<Disease> diseases = new HashSet<>();

		for (Long obj : datasetRequest.getDiseases()) {
	            Disease disease = diseaseRepository.findById(obj).orElseThrow(() 
	    				-> new RuntimeException("Error: Disease is not found. Disease Id:"+ obj));
	            diseases.add(disease);
	       }
		
		Dataset dataset = new Dataset( datasetRequest.getName(),datasetRequest.getSampleTrain(),datasetRequest.getSampleTest()
				,datasetRequest.getSampleVal(),datasetRequest.getOriginalSize());
		
		dataset.setDiseases(diseases);
		return datasetRepository.save(dataset);
	}
	
	@Transactional
	public void deleteDatasetById(Long id) {
		Dataset datasetOptional = datasetRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: dataset is not found."));
		Set<Disease> diseases = new HashSet<>();
		datasetOptional.setDiseases(diseases);
		datasetRepository.save(datasetOptional);
		datasetRepository.deleteById(id);
	}
	
	
	@Transactional
    public Dataset updateDataset(Long id, DatasetRequest datasetRequest) {
        Optional<Dataset> datasetOptional = datasetRepository.findById(id);
        if (datasetOptional.isPresent()) {
        	Dataset existingDataset = datasetOptional.get();
        	Set<Disease> diseases = new HashSet<>();

    		for (Long obj : datasetRequest.getDiseases()) {
    	            Disease disease = diseaseRepository.findById(obj).orElseThrow(() 
    	    				-> new RuntimeException("Error: Disease is not found. Disease Id:"+ obj));
    	            diseases.add(disease);
    	       }
    		
    		existingDataset.setDiseases(diseases);
    		existingDataset.setName(datasetRequest.getName());
    		existingDataset.setOriginalSize(datasetRequest.getOriginalSize());
    		existingDataset.setSampleTest(datasetRequest.getSampleTest());
    		existingDataset.setSampleTrain(datasetRequest.getSampleTrain());
    		existingDataset.setSampleVal(datasetRequest.getSampleVal());
            return datasetRepository.save(existingDataset);
        }
        return null;
    }
	
	@Transactional
    public Dataset active(Long id) {
        Optional<Dataset> datasetOptional = datasetRepository.findById(id);
        if (datasetOptional.isPresent()) {
        	Dataset existingDataset = datasetOptional.get();
        	existingDataset.setIsActive(true);
            return datasetRepository.save(existingDataset);
        }
        return null;
	}
	
	@Transactional
    public Dataset inactive(Long id) {
        Optional<Dataset> datasetOptional = datasetRepository.findById(id);
        if (datasetOptional.isPresent()) {
        	Dataset existingDataset = datasetOptional.get();
        	existingDataset.setIsActive(false);
            return datasetRepository.save(existingDataset);
        }
        return null;
	}
	
	public List<Dataset> getAllActive(){
		return datasetRepository.findByIsActive(true);
	}
}
