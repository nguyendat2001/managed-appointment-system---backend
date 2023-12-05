package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.payload.request.WardRequest;
import com.bezkoder.spring.security.jwt.repository.WardRepository;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;

@Service
public class WardService {
	@Autowired
	WardRepository wardRepository;
	
	@Autowired
	DistrictRepository districtRepository;
	
	public List<Ward> getAllWard(){
		return wardRepository.findAll();
	}
	
	public Ward getWardById(Long id) {
		return wardRepository.findById(id).orElse(null);
	}
	
	public List<Ward> getAllIsactive() {
		return wardRepository.findByIsActive(true);
	}
	
	public List<Ward> getAllByDistrict(Long Districtid) {
		District district = districtRepository.findById(Districtid).orElseThrow(() 
				-> new RuntimeException("Error: District is not found."));
		return wardRepository.findAllByDistrictAndIsActive(district,true);
	}
	
	@Transactional
	public Ward createWard(WardRequest WardRequest) {
		District District = districtRepository.findById(WardRequest.getDistrict()).orElseThrow(() 
				-> new RuntimeException("Error: District is not found."));
		
		Ward Ward = new Ward(WardRequest.getName());
		Ward.setDistrict(District);
		return wardRepository.save(Ward);
	}
	
	@Transactional
	public void deleteWardById(Long id) {
		wardRepository.deleteById(id);
	}
	
	@Transactional
    public Ward activeWard(Long id) {
        Optional<Ward> WardOptional = wardRepository.findById(id);
        if (WardOptional.isPresent()) {
        	Ward existingWard = WardOptional.get();
        	existingWard.setIsActive(true);
            return wardRepository.save(existingWard);
        }
        return null;
	}
	
	@Transactional
    public Ward inactiveWard(Long id) {
        Optional<Ward> WardOptional = wardRepository.findById(id);
        if (WardOptional.isPresent()) {
        	Ward existingWard = WardOptional.get();
        	existingWard.setIsActive(false);
            return wardRepository.save(existingWard);
        }
        return null;
	}
	
	@Transactional
    public Ward updateWard(Long id, WardRequest WardRequest) {
        Optional<Ward> WardOptional = wardRepository.findById(id);
        if (WardOptional.isPresent()) {
        	Ward existingWard = WardOptional.get();
        	existingWard.setName(WardRequest.getName());
            return wardRepository.save(existingWard);
        }
        return null;
	}
}
