package com.bezkoder.spring.security.jwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.payload.request.DepartmentRequest;
import com.bezkoder.spring.security.jwt.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartment(){
		return departmentRepository.findAll();
	}
	
	public List<Department> getAllActiveDepartment(){
		return departmentRepository.findByIsActive(true);
	}
	
	public List<Department> getAllInactiveDepartment(){
		return departmentRepository.findByIsActive(false);
	}
	
	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Department createDepartment(DepartmentRequest departmentRequest) {
		Department department = new Department(departmentRequest.getName(),departmentRequest.getDescribeDepartment());
		return departmentRepository.save(department);
	}
	
	@Transactional
	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}
	
	@Transactional
    public Department updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Optional<Department> DepartmentOptional = departmentRepository.findById(id);
        if (DepartmentOptional.isPresent()) {
        	Department existingDepartment = DepartmentOptional.get();
        	existingDepartment.setName(departmentRequest.getName());
        	existingDepartment.setDescribeDepartment(departmentRequest.getDescribeDepartment());
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }
	
	@Transactional
    public Department activeDepartment(Long id) {
        Optional<Department> DepartmentOptional = departmentRepository.findById(id);
        if (DepartmentOptional.isPresent()) {
        	Department existingDepartment = DepartmentOptional.get();
        	existingDepartment.setIsActive(true);
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }
	
	@Transactional
    public Department inactiveDepartment(Long id) {
        Optional<Department> DepartmentOptional = departmentRepository.findById(id);
        if (DepartmentOptional.isPresent()) {
        	Department existingDepartment = DepartmentOptional.get();
        	existingDepartment.setIsActive(false);
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }
	
	
}
