package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Medicine;
import com.bezkoder.spring.security.jwt.payload.request.MedicineRequest;
import com.bezkoder.spring.security.jwt.repository.MedicineRepository;

@Service
public class MedicineService {
	@Autowired
	MedicineRepository medicineRepository;
	
	public List<Medicine> getAllMedicine(){
		return medicineRepository.findAll();
	}
	
	public List<Medicine> getAllActiveMedicine(){
		return medicineRepository.findByIsActive(true);
	}
	
	public Medicine getMedicineById(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Medicine createMedicine(MedicineRequest medicineRequest) {
		Medicine medicine = new Medicine(medicineRequest.getName(),medicineRequest.getDescription(),
				medicineRequest.getIngredient(),medicineRequest.getUses());
		return medicineRepository.save(medicine);
	}
	
	@Transactional
	public Medicine activeMedicine(Long id) {
		Optional<Medicine> MedicineOptional = medicineRepository.findById(id);
        if (MedicineOptional.isPresent()) {
        	Medicine existingMedicine = MedicineOptional.get();
        	existingMedicine.setIsActive(true);
            return medicineRepository.save(existingMedicine);
        }
        return null;
	}
	
	@Transactional
	public Medicine inactiveMedicine(Long id) {
		Optional<Medicine> MedicineOptional = medicineRepository.findById(id);
        if (MedicineOptional.isPresent()) {
        	Medicine existingMedicine = MedicineOptional.get();
        	existingMedicine.setIsActive(false);
            return medicineRepository.save(existingMedicine);
        }
        return null;
	}
	
	@Transactional
	public void deleteMedicineById(Long id) {
		medicineRepository.deleteById(id);
	}
	
	@Transactional
    public Medicine updateMedicine(Long id, MedicineRequest medicineRequest) {
        Optional<Medicine> MedicineOptional = medicineRepository.findById(id);
        if (MedicineOptional.isPresent()) {
        	Medicine existingMedicine = MedicineOptional.get();
        	existingMedicine.setName(medicineRequest.getName());
        	existingMedicine.setDescription(medicineRequest.getDescription());
        	existingMedicine.setIngredient(medicineRequest.getIngredient());
        	existingMedicine.setUses(medicineRequest.getUses());
            return medicineRepository.save(existingMedicine);
        }
        return null;
    }
	
}
