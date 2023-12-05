package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.ProvinceRequest;
import com.bezkoder.spring.security.jwt.repository.ProvinceRepository;
import com.bezkoder.spring.security.jwt.repository.PrescriptionRepository;

@Service
public class ProvinceService {
	@Autowired
	ProvinceRepository provinceRepository;
	
	public List<Province> getAllProvince(){
		return provinceRepository.findAll();
	}
	
	public Province getProvinceById(Long id) {
		return provinceRepository.findById(id).orElse(null);
	}
	
	public List<Province> getAllIsactive() {
		return provinceRepository.findByIsActive(true);
	}
	
	@Transactional
	public Province createProvince(ProvinceRequest ProvinceRequest) {
		Province province = new Province(ProvinceRequest.getName());
		return provinceRepository.save(province);
	}
	
	@Transactional
	public void deleteProvinceById(Long id) {
		provinceRepository.deleteById(id);
	}
	
	@Transactional
    public Province activeProvince(Long id) {
        Optional<Province> provinceOptional = provinceRepository.findById(id);
        if (provinceOptional.isPresent()) {
        	Province existingProvince = provinceOptional.get();
        	existingProvince.setIsActive(true);
            return provinceRepository.save(existingProvince);
        }
        return null;
	}
	
	@Transactional
    public Province inactiveProvince(Long id) {
        Optional<Province> provinceOptional = provinceRepository.findById(id);
        if (provinceOptional.isPresent()) {
        	Province existingProvince = provinceOptional.get();
        	existingProvince.setIsActive(false);
            return provinceRepository.save(existingProvince);
        }
        return null;
	}
	
	@Transactional
    public Province updateProvince(Long id, ProvinceRequest provinceRequest) {
        Optional<Province> provinceOptional = provinceRepository.findById(id);
        if (provinceOptional.isPresent()) {
        	Province existingProvince = provinceOptional.get();
        	existingProvince.setName(provinceRequest.getName());
            return provinceRepository.save(existingProvince);
        }
        return null;
	}
}
