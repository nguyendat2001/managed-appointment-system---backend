package com.bezkoder.spring.security.jwt.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.PrescriptionDetails;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.models.Medicine;
import com.bezkoder.spring.security.jwt.payload.request.PrescriptionRequest;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.MedicineRepository;
import com.bezkoder.spring.security.jwt.repository.PrescriptionDetailRepository;
import com.bezkoder.spring.security.jwt.repository.PrescriptionRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;

@Service
public class PrescriptionService {
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	@Autowired
	PrescriptionDetailRepository prescriptionDetailRepository;
	
	public List<Prescription> getAllPrescription(){
		return prescriptionRepository.findAll();
	}
	
	public List<Prescription> getActivePrescription(){
		return prescriptionRepository.findByIsActive(true);
	}

	public List<Prescription> getAllPrescriptionByUserId(Long userId){
		User user = userRepository.findById(userId).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		return prescriptionRepository.findByUser(user);
	}
	
	public List<Prescription> getAllPrescriptionByDoctorId(Long doctorId){
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		return prescriptionRepository.findByDoctor(doctor);
	}
	
	public Prescription getPrescriptionById(Long id) {
		return prescriptionRepository.findById(id).orElse(null);
	}
	
	public List<Prescription> findbyDoctorAndActive(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
        return prescriptionRepository.findByDoctorAndIsActive(doctor,true);
	}
	
	public List<Prescription> findbyUserAndActive(Long id) {
		User user = userRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
        return prescriptionRepository.findByUserAndIsActive(user,true);
	}
	
	@Transactional
	public Prescription activePrescription(Long id) {
		Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
        	
    		Prescription prescription = prescriptionOptional.get();
    		prescription.setIsActive(true);
    		
    		return prescriptionRepository.save(prescription);
        }
        return null;
	}
	
	@Transactional
	public Prescription inactivePrescription(Long id) {
		Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
        	
    		Prescription prescription = prescriptionOptional.get();
    		prescription.setIsActive(false);
    		
    		return prescriptionRepository.save(prescription);
        }
        return null;
	}
	
	@Transactional
	public List<PrescriptionDetails> createPrescription(PrescriptionRequest prescriptionRequest) {
		Doctor doctor = doctorRepository.findById(prescriptionRequest.getDoctor()).orElseThrow(() 
				-> new RuntimeException("Error: Doctor is not found."));
		
		User user = userRepository.findById(prescriptionRequest.getUser()).orElseThrow(() 
				-> new RuntimeException("Error: User is not found."));
		
		Prescription prescription = new Prescription( prescriptionRequest.getDiagnostic(),prescriptionRequest.getDescription(),
				prescriptionRequest.getAdvice());
		prescription.setDoctor(doctor);
		prescription.setUser(user);
		
		Prescription prescriptionResult = prescriptionRepository.save(prescription);
		for (int i = 0; i < prescriptionRequest.getMedicines().size(); i++) {
	            Medicine medicine = medicineRepository.findById(prescriptionRequest.getMedicines().get(i)).orElseThrow(() 
	    				-> new RuntimeException("Error: Medicine is not found."));
	            
	            PrescriptionDetails prescriptionDetail = new PrescriptionDetails();
	            
	            prescriptionDetail.setMedicine(medicine);
	            prescriptionDetail.setNumber(prescriptionRequest.getMedicinesNumber().get(i));
	            prescriptionDetail.setPresciption(prescriptionResult);
	            prescriptionDetailRepository.save(prescriptionDetail);
	       }
		
		List<PrescriptionDetails> prescriptionDetailResult = prescriptionDetailRepository.findByPrescription(prescriptionResult);
		
		return prescriptionDetailResult;
	}
	
	@Transactional
	public void deletePrescriptionById(Long id) {
		prescriptionRepository.deleteById(id);
	}
	
	@Transactional
    public List<PrescriptionDetails> updatePrescription(Long id, PrescriptionRequest prescriptionRequest) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
        	Doctor doctor = doctorRepository.findById(prescriptionRequest.getDoctor()).orElseThrow(() 
    				-> new RuntimeException("Error: Doctor is not found."));
    		
    		User user = userRepository.findById(prescriptionRequest.getUser()).orElseThrow(() 
    				-> new RuntimeException("Error: User is not found."));
    		
    		Prescription prescription = new Prescription( prescriptionRequest.getDiagnostic(),prescriptionRequest.getDescription(),
    				prescriptionRequest.getAdvice());
    		
    		Prescription existPrescription = prescriptionOptional.get();
    		
    		prescriptionDetailRepository.deleteByPrescription(existPrescription);
    		
    		prescription.setDoctor(doctor);
    		prescription.setUser(user);
    		prescription.setId(id);
    		Prescription prescriptionResult = prescriptionRepository.save(prescription);
    		for (int i = 0; i < prescriptionRequest.getMedicines().size(); i++) {
    	            Medicine medicine = medicineRepository.findById(prescriptionRequest.getMedicines().get(i)).orElseThrow(() 
    	    				-> new RuntimeException("Error: Medicine is not found."));
    	            
    	            PrescriptionDetails prescriptionDetail = new PrescriptionDetails();
    	            
    	            prescriptionDetail.setMedicine(medicine);
    	            prescriptionDetail.setNumber(prescriptionRequest.getMedicinesNumber().get(i));
    	            prescriptionDetail.setPresciption(prescriptionResult);
    	            prescriptionDetailRepository.save(prescriptionDetail);
    	       }
    		
    		List<PrescriptionDetails> prescriptionDetailResult = prescriptionDetailRepository.findByPrescription(prescriptionResult);
    		
    		return prescriptionDetailResult;
        }
        return null;
    }
}
