package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.PrescriptionDetails;
import com.bezkoder.spring.security.jwt.payload.request.PrescriptionDetailsRequest;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.models.Prescription;
import com.bezkoder.spring.security.jwt.models.HyperParam;
import com.bezkoder.spring.security.jwt.models.Medicine;
import com.bezkoder.spring.security.jwt.repository.PrescriptionDetailRepository;
import com.bezkoder.spring.security.jwt.repository.MedicineRepository;
import com.bezkoder.spring.security.jwt.repository.ModelRepository;
import com.bezkoder.spring.security.jwt.repository.PrescriptionRepository;

@Service
public class PrescriptionDetailService {
	@Autowired
	PrescriptionDetailRepository prescriptionDetailRepository;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	ModelRepository modelRepository;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	public List<PrescriptionDetails> getAllPrescriptionDetails(){
		return prescriptionDetailRepository.findAll();
	}

	public List<PrescriptionDetails> getAllByPrescription(Long id){
		Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Prescription is not found."));
		return prescriptionDetailRepository.findByPrescription(prescription);
	}
	
	
	public PrescriptionDetails getPrescriptionDetailsById(Long id) {
		return prescriptionDetailRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public PrescriptionDetails createPrescriptionDetails(PrescriptionDetailsRequest prescriptionDetailRequest) {
		Prescription prescriptionOptional = prescriptionRepository.findById(prescriptionDetailRequest.getPrescription()).orElseThrow(() 
				-> new RuntimeException("Error: Prescription is not found."));
		
		Medicine medicine = medicineRepository.findById(prescriptionDetailRequest.getMedicine()).orElseThrow(() 
				-> new RuntimeException("Error: Medicine is not found."));
		
		PrescriptionDetails prescriptionDetail = new PrescriptionDetails( prescriptionDetailRequest.getNumber());
		prescriptionDetail.setMedicine(medicine);
		prescriptionDetail.setPresciption(prescriptionOptional);
		return prescriptionDetailRepository.save(prescriptionDetail);
	}
	
	@Transactional
	public void deletePrescriptionDetailsByprescription(Long id) {
		Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Prescription is not found."));
		prescriptionDetailRepository.deleteByPrescription(prescription);
	}
	
	@Transactional
	public void deletePrescriptionDetailsById(Long id) {
		prescriptionDetailRepository.deleteById(id);
	}
	
	@Transactional
    public PrescriptionDetails updatePrescriptionDetails(Long id, PrescriptionDetailsRequest prescriptionDetailRequest) {
        Optional<PrescriptionDetails> PrescriptionDetailsOptional = prescriptionDetailRepository.findById(id);
        if (PrescriptionDetailsOptional.isPresent()) {
        	PrescriptionDetails existingPrescriptionDetails = PrescriptionDetailsOptional.get();
        	
        	Prescription prescriptionOptional = prescriptionRepository.findById(prescriptionDetailRequest.getPrescription()).orElseThrow(() 
    				-> new RuntimeException("Error: Prescription is not found."));
    		
    		Medicine medicine = medicineRepository.findById(prescriptionDetailRequest.getMedicine()).orElseThrow(() 
    				-> new RuntimeException("Error: Medicine is not found."));
    		
    		existingPrescriptionDetails.setMedicine(medicine);
    		existingPrescriptionDetails.setPresciption(prescriptionOptional);
    		existingPrescriptionDetails.setNumber(prescriptionDetailRequest.getNumber());
            return prescriptionDetailRepository.save(existingPrescriptionDetails);
        }
        return null;
    }
}
