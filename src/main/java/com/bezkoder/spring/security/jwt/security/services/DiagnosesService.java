package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.Disease;
import com.bezkoder.spring.security.jwt.models.Diagnoses;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.DiagnosesRequest;
import com.bezkoder.spring.security.jwt.repository.DiagnosesRepository;
import com.bezkoder.spring.security.jwt.repository.DiseaseRepository;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;

@Service
public class DiagnosesService {
	@Autowired
	DiagnosesRepository diagnosesRepository;
	
	@Autowired
	DiagnosesImageService diagnosesImageService;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Diagnoses> getAllDiagnoses(){
		return diagnosesRepository.findAll();
	}
	
	public List<Diagnoses> getAllByUser(Long id){
		User user = userRepository.findById(id).orElseThrow(() 
					-> new RuntimeException("Error: User is not found."));
		return diagnosesRepository.findAllByUser(user);
	}
	
	public List<Diagnoses> getAllByDoctor(Long id){
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return diagnosesRepository.findAllByDoctor(doctor);
	}
	
	public Diagnoses getDiagnosesById(Long id) {
		return diagnosesRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Diagnoses createDiagnoses(DiagnosesRequest diagnosesRequest) {
		Diagnoses diagnoses = new Diagnoses(diagnosesRequest.getTechnique(),diagnosesRequest.getResult(),diagnosesRequest.getDescribe());;
		Doctor doctor = doctorRepository.findById(diagnosesRequest.getDoctor()).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		User user = userRepository.findById(diagnosesRequest.getUser()).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		Disease disease = diseaseRepository.findById(diagnosesRequest.getDisease()).orElseThrow(() 
				-> new RuntimeException("Error: Disease is not found."));
		diagnoses.setDisease(disease);
		diagnoses.setDoctor(doctor);
		diagnoses.setUser(user);
		return diagnosesRepository.save(diagnoses);
	}
	
	@Transactional
	public void deleteDiagnosesById(Long id) {
		diagnosesImageService.deleteAllByDiagnoses(id);
		diagnosesRepository.deleteById(id);
	}
	
	@Transactional
    public Diagnoses updateDiagnoses(Long id, DiagnosesRequest diagnosesRequest) {
        Optional<Diagnoses> DiagnosesOptional = diagnosesRepository.findById(id);
        if (DiagnosesOptional.isPresent()) {
        	Diagnoses existingDiagnoses = DiagnosesOptional.get();
        	Doctor doctor = doctorRepository.findById(diagnosesRequest.getDoctor()).orElseThrow(() 
    				-> new RuntimeException("Error: Doctor is not found."));
    		User user = userRepository.findById(diagnosesRequest.getUser()).orElseThrow(() 
    				-> new RuntimeException("Error: User is not found."));
    		Disease disease = diseaseRepository.findById(diagnosesRequest.getDisease()).orElseThrow(() 
    				-> new RuntimeException("Error: Disease is not found."));
    		existingDiagnoses.setDisease(disease);
    		existingDiagnoses.setDoctor(doctor);
    		existingDiagnoses.setUser(user);
    		existingDiagnoses.setDescribe(diagnosesRequest.getDescribe());
    		existingDiagnoses.setTechnique(diagnosesRequest.getTechnique());
    		existingDiagnoses.setResult(diagnosesRequest.getResult());
            return diagnosesRepository.save(existingDiagnoses);
        }
        return null;
    }
	
	@Transactional
    public Diagnoses active(Long id) {
        Optional<Diagnoses> diagnosesOptional = diagnosesRepository.findById(id);
        if (diagnosesOptional.isPresent()) {
        	Diagnoses existingDiagnoses = diagnosesOptional.get();
        	existingDiagnoses.setIsActive(true);
            return diagnosesRepository.save(existingDiagnoses);
        }
        return null;
	}
	
	@Transactional
    public Diagnoses inactive(Long id) {
        Optional<Diagnoses> diagnosesOptional = diagnosesRepository.findById(id);
        if (diagnosesOptional.isPresent()) {
        	Diagnoses existingDiagnoses = diagnosesOptional.get();
        	existingDiagnoses.setIsActive(false);
            return diagnosesRepository.save(existingDiagnoses);
        }
        return null;
	}
	
	public List<Diagnoses> getAllActive(){
		return diagnosesRepository.findByIsActive(true);
	}
	
	public List<Diagnoses> findbyDisease(Long id) {
		Disease disease = diseaseRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Disease is not found."));
        return diagnosesRepository.findByDisease(disease);
	}
	
	public List<Diagnoses> findbyDoctorAndActive(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
        return diagnosesRepository.findAllByDoctorAndIsActive(doctor,true);
	}
	
	public List<Diagnoses> findbyUserAndActive(Long id) {
		User user = userRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
        return diagnosesRepository.findAllByUserAndIsActive(user,true);
	}
}
