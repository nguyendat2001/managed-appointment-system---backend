package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.DiagnosesImage;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesImageRequest;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesRequest;
import com.bezkoder.spring.security.jwt.repository.DiagnosesImageRepository;
import com.bezkoder.spring.security.jwt.repository.DiagnosesRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

@Service
public class DiagnosesImageService {
	@Autowired
	DiagnosesRepository diagnosesRepository;
	
	@Autowired
	DiagnosesImageRepository diagnosesImageRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	public List<DiagnosesImage> getAllDiagnosesImage(){
		return diagnosesImageRepository.findAll();
	}
	
	public List<DiagnosesImage> getAllByDiagnoses(Long id){
		Diagnoses diagnoses = diagnosesRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return diagnosesImageRepository.findAllByDiagnoses(diagnoses);
	}
	
	public DiagnosesImage getDiagnosesById(Long id) {
		
		return diagnosesImageRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public DiagnosesImage createDiagnosesImage(DiagnosesImageRequest diagnosesImageRequest) {
		Diagnoses diagnoses = diagnosesRepository.findById(diagnosesImageRequest.getDiagnoses()).orElseThrow(() 
				-> new RuntimeException("Error: Diagnoses is not found."));
		DiagnosesImage diagnosesImage = new DiagnosesImage();
		diagnosesImage.setDiagnoses(diagnoses);
		return diagnosesImageRepository.save(diagnosesImage);
	}
	
	@Transactional
	public void deleteDiagnosesImageById(Long id) {
		DiagnosesImage diagnosesImage = diagnosesImageRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: DiagnosesImage is not found."));
		storageService.delete_diag(diagnosesImage.getPath());
		diagnosesImageRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteAllByDiagnoses(Long id) {
		Diagnoses diagnoses = diagnosesRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Diagnoses is not found."));
		List<DiagnosesImage> diagnosesImages = diagnosesImageRepository.findAllByDiagnoses(diagnoses);
		for (DiagnosesImage obj : diagnosesImages) {
			storageService.delete_diag(obj.getPath());
		}
		diagnosesImageRepository.deleteAllByDiagnoses(diagnoses);
	}
	
	@Transactional
	public DiagnosesImage uploadFile(Long id, MultipartFile file) {
		Optional<DiagnosesImage> doctorOptional = diagnosesImageRepository.findById(id);
		if(doctorOptional.isPresent()) {
			DiagnosesImage exsitDiagnosesImage = doctorOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.save_diag(file,newfileName);
			exsitDiagnosesImage.setPath(newfileName);
			return diagnosesImageRepository.save(exsitDiagnosesImage);
		}
		return null;
	}
	
	@Transactional
	public DiagnosesImage updateFile(Long id, MultipartFile file) {
		Optional<DiagnosesImage> doctorOptional = diagnosesImageRepository.findById(id);
		if(doctorOptional.isPresent()) {
			DiagnosesImage exsitDiagnosesImage = doctorOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.delete_diag(exsitDiagnosesImage.getPath());
			storageService.save_diag(file,newfileName);
			exsitDiagnosesImage.setPath(newfileName);
			return diagnosesImageRepository.save(exsitDiagnosesImage);
		}
		return null;
	}
}
