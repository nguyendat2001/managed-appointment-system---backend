package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.PredictImage;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesImageRequest;
import com.bezkoder.spring.security.jwt.payload.request.PredictImageRequest;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.repository.PredictImageRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

@Service
public class PredictImageService {
	@Autowired
	PredictImageRepository predictImageRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	public List<PredictImage> getAll(){
		return predictImageRepository.findAll();
	}
	
	public List<PredictImage> getAllByUser(Long id){
		User user = userRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		return predictImageRepository.findByUser(user);
	}
	
	public PredictImage getById(Long id) {
		return predictImageRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public PredictImage create(PredictImageRequest predictImageRequest) {
		User user = userRepository.findById(predictImageRequest.getUser()).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		PredictImage predictImage = new PredictImage();
		predictImage.setUser(user);
		return predictImageRepository.save(predictImage);
	}
	
	@Transactional
	public void deleteById(Long id) {
		PredictImage predictImage = predictImageRepository.findById(id).orElse(null);
		if(predictImage.getPredictPathImage() != null) {
			storageService.delete_predict(predictImage.getPredictPathImage());
		}
		predictImageRepository.deleteById(id);
	}
	
//	@Transactional
//	public void deleteAllByDiagnoses(Long id) {
//		Predict predict = predictImageRepository.findById(id).orElseThrow(() 
//				-> new RuntimeException("Error: Predict is not found."));
//		List<PredictImage> diagnosesImages = diagnosesImageRepository.findAllByDiagnoses(predict);
//		for (PredictImage obj : diagnosesImages) {
//			storageService.delete_diag(obj.getPath());
//		}
//		diagnosesImageRepository.deleteAllByDiagnoses(predict);
//	}
	
	@Transactional
	public PredictImage uploadFile(Long id, MultipartFile file) {
		Optional<PredictImage> predictImageOptional = predictImageRepository.findById(id);
		if(predictImageOptional.isPresent()) {
			PredictImage exsitPredictImageOptional = predictImageOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() + typeFile;
			storageService.save_predict(file,newfileName);
			exsitPredictImageOptional.setPredictPathImage(newfileName);
			return predictImageRepository.save(exsitPredictImageOptional);
		}
		return null;
	}
	
	@Transactional
	public PredictImage updateFile(Long id, MultipartFile file) {
		Optional<PredictImage> predictImageOptional = predictImageRepository.findById(id);
		if(predictImageOptional.isPresent()) {
			PredictImage exsitPredictImageOptional = predictImageOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.delete_predict(exsitPredictImageOptional.getPredictPathImage());
			storageService.save_predict(file,newfileName);
			exsitPredictImageOptional.setPredictPathImage(newfileName);
			return predictImageRepository.save(exsitPredictImageOptional);
		}
		return null;
	}
}
