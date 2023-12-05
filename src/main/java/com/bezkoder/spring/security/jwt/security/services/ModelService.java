package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Dataset;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.payload.request.ModelRequest;
import com.bezkoder.spring.security.jwt.repository.DatasetRepository;
import com.bezkoder.spring.security.jwt.repository.ModelRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

@Service
public class ModelService {
	@Autowired
	ModelRepository modelRepository;
	
	@Autowired
	DatasetRepository datasetRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	public List<Model> getAllModel(){
		return modelRepository.findAll();
	}
	
	
	public Model getModelById(Long id) {
		return modelRepository.findById(id).orElse(null);
	}
	
	public List<Model> getSegmentationModel() {
		return modelRepository.findAllByIsSegmentation(true);
	}
	
	public List<Model> getClassificationModel() {
		return modelRepository.findAllByIsSegmentation(false);
	}
	
	public List<Model> getModelByDataset(Long id) {
		Dataset dataset = datasetRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Dataset is not found."));
		return modelRepository.findAllByDataset(dataset);
	}
	
	@Transactional
	public Model createModel(ModelRequest modelRequest) {
		Model model = new Model(modelRequest.getName(),modelRequest.getPath(),modelRequest.getLabels(),modelRequest.getInputSize(),modelRequest.getIsSegmetation());
		Dataset dataset = datasetRepository.findById(modelRequest.getDataset()).orElseThrow(() 
				-> new RuntimeException("Error: Dataset is not found."));
		model.setDataset(dataset);
		
		return modelRepository.save(model);
	}

	@Transactional
	public void deleteModelById(Long id) {
		modelRepository.deleteById(id);
	}
	
	@Transactional
    public Model updateModel(Long id, ModelRequest modelRequest) {
        Optional<Model> ModelOptional = modelRepository.findById(id);
        if (ModelOptional.isPresent()) {
        	Model existingModel = ModelOptional.get();
        	Dataset dataset = datasetRepository.findById(modelRequest.getDataset()).orElseThrow(() 
    				-> new RuntimeException("Error: Dataset is not found."));
        	existingModel.setName(modelRequest.getName());
        	existingModel.setPath(modelRequest.getPath());
        	existingModel.setInputSize(modelRequest.getInputSize());
        	existingModel.setIsSegmentation(modelRequest.getIsSegmetation());
        	existingModel.setDataset(dataset);
        	existingModel.setLabels(modelRequest.getLabels());
            return modelRepository.save(existingModel);
        }
        return null;
    }
	
	@Transactional
    public Model active(Long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
        	Model existingModel = modelOptional.get();
        	existingModel.setIsActive(true);
            return modelRepository.save(existingModel);
        }
        return null;
	}
	
	@Transactional
    public Model inactive(Long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
        	Model existingModel = modelOptional.get();
        	existingModel.setIsActive(false);
            return modelRepository.save(existingModel);
        }
        return null;
	}
	
	public List<Model> getAllActive(){
		return modelRepository.findByIsActive(true);
	}
}

