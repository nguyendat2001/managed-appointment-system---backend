package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.DistrictRequest;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;
import com.bezkoder.spring.security.jwt.repository.ProvinceRepository;

@Service
public class DistrictService {
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	ProvinceRepository provinceRepository;
	
	public List<District> getAllDistrict(){
		return districtRepository.findAll();
	}
	
	public District getDistrictById(Long id) {
		return districtRepository.findById(id).orElse(null);
	}
	
	public List<District> getAllIsactive() {
		return districtRepository.findByIsActive(true);
	}
	
	public List<District> getAllByProvince(Long provinceid) {
		Province province = provinceRepository.findById(provinceid).orElseThrow(() 
				-> new RuntimeException("Error: Province is not found."));
		return districtRepository.findAllByProvinceAndIsActive(province,true);
	}
	
	@Transactional
	public District createDistrict(DistrictRequest districtRequest) {
		Province province = provinceRepository.findById(districtRequest.getProvince()).orElseThrow(() 
				-> new RuntimeException("Error: Province is not found."));
		
		District district = new District(districtRequest.getName());
		district.setProvince(province);
		return districtRepository.save(district);
	}
	
	@Transactional
	public void deleteDistrictById(Long id) {
		districtRepository.deleteById(id);
	}
	
	@Transactional
    public District activeDistrict(Long id) {
        Optional<District> DistrictOptional = districtRepository.findById(id);
        if (DistrictOptional.isPresent()) {
        	District existingDistrict = DistrictOptional.get();
        	existingDistrict.setIsActive(true);
            return districtRepository.save(existingDistrict);
        }
        return null;
	}
	
	@Transactional
    public District inactiveDistrict(Long id) {
        Optional<District> DistrictOptional = districtRepository.findById(id);
        if (DistrictOptional.isPresent()) {
        	District existingDistrict = DistrictOptional.get();
        	existingDistrict.setIsActive(false);
            return districtRepository.save(existingDistrict);
        }
        return null;
	}
	
	@Transactional
    public District updateDistrict(Long id, DistrictRequest DistrictRequest) {
        Optional<District> DistrictOptional = districtRepository.findById(id);
        if (DistrictOptional.isPresent()) {
        	District existingDistrict = DistrictOptional.get();
        	existingDistrict.setName(DistrictRequest.getName());
            return districtRepository.save(existingDistrict);
        }
        return null;
	}
}
