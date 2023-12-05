package com.bezkoder.spring.security.jwt.security.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Certificate;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.payload.request.CertificateRequest;
import com.bezkoder.spring.security.jwt.repository.CertificateRepository;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.RoomRepository;
import com.bezkoder.spring.security.jwt.repository.WardRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

@Service
public class CertificateService {
	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	WardRepository wardRepository;
	
	@Autowired
	FilesStorageService StorageService;
	
	public List<Certificate> getAllCertificate(){
		return certificateRepository.findAll();
	}

	public List<Certificate> getAllCertificateByDoctor(Long id){
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return certificateRepository.findByDoctor(doctor);
	}
	
	public List<Certificate> getAllCertificateByWard(Long id){
		Ward ward = wardRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Ward is not found."));
		return certificateRepository.findByWard(ward);
	}
	
	
	public Certificate getCertificateById(Long id) {
		return certificateRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Certificate createCertificate(CertificateRequest certificateRequest) {
		Doctor doctor = doctorRepository.findById(certificateRequest.getDoctor()).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		
		Ward ward = wardRepository.findById(certificateRequest.getWard()).orElseThrow(() 
				-> new RuntimeException("Error: Ward is not found."));
		
//		StorageService.save_cer(file);
		
		Certificate certificate = new Certificate( certificateRequest.getName(),certificateRequest.getCertificationUnit(),certificateRequest.getValidFrom());
//		certificate.setPath(file.getOriginalFilename());
		certificate.setDoctor(doctor);
		certificate.setWard(ward);
		return certificateRepository.save(certificate);
	}
	
	@Transactional
	public void deleteCertificateById(Long id) {
		Certificate CertificateOptional = certificateRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Certificate is not found."));
		if(CertificateOptional.getPath() != null) {
			StorageService.delete_cer(CertificateOptional.getPath());
		}
		
		certificateRepository.deleteById(id);
	}
	
	
	@Transactional
    public Certificate updateCertificate(Long id, CertificateRequest certificateRequest) {
        Optional<Certificate> CertificateOptional = certificateRepository.findById(id);
        if (CertificateOptional.isPresent()) {
        	Certificate existingCertificate = CertificateOptional.get();
        	Doctor doctor = doctorRepository.findById(certificateRequest.getDoctor()).orElseThrow(() 
    				-> new RuntimeException("Error: Doctor is not found."));
    		
    		Ward ward = wardRepository.findById(certificateRequest.getWard()).orElseThrow(() 
    				-> new RuntimeException("Error: Ward is not found."));
    		
//    		StorageService.delete_cer(existingCertificate.getPath());
//    		StorageService.save_cer(file);
    		
    		existingCertificate.setName(certificateRequest.getName());
    		existingCertificate.setCertificationUnit(certificateRequest.getCertificationUnit());
    		existingCertificate.setValidFrom(certificateRequest.getValidFrom());
    		
//    		existingCertificate.setPath(file.getOriginalFilename());
    		existingCertificate.setDoctor(doctor);
    		existingCertificate.setWard(ward);
    		return certificateRepository.save(existingCertificate);
        }
        return null;
    }
	
	@Transactional
	public Certificate uploadfile(Long id, MultipartFile file) {
		Optional<Certificate> certificateOptional = certificateRepository.findById(id);
		if(certificateOptional.isPresent()) {
			Certificate certificate = certificateOptional.get();

			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
//			System.out.println(file.getOriginalFilename().split("."));
//			System.out.println(newfileName);
			StorageService.save_cer(file,newfileName);
			certificate.setPath(newfileName);
			return certificateRepository.save(certificate);
		}
		return null;
	}
	
	@Transactional
	public Certificate updatefile(Long id, MultipartFile file) {
		Optional<Certificate> certificateOptional = certificateRepository.findById(id);
		if(certificateOptional.isPresent()) {
			Certificate certificate = certificateOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			StorageService.delete_cer(certificate.getPath());
			StorageService.save_cer(file,newfileName);
			certificate.setPath(newfileName);
			return certificateRepository.save(certificate);
		}
		return null;
	}
	
}
