package com.bezkoder.spring.security.jwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Department;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.Degree;
import com.bezkoder.spring.security.jwt.payload.request.DoctorRequest;
import com.bezkoder.spring.security.jwt.repository.AccountRepository;
import com.bezkoder.spring.security.jwt.repository.DegreeRepository;
import com.bezkoder.spring.security.jwt.repository.DepartmentRepository;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DegreeRepository degreeRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	public List<Doctor> getAllDoctor(){
		return doctorRepository.findAll();
	}
	
	public List<Doctor> getAllDoctorByDepartmentAndIsActive(Long id){
		Department departmentDoctor = departmentRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Department is not found."));
		return doctorRepository.findByDepartmentAndIsActive(departmentDoctor,true);
	}
	
	public List<Doctor> getAllActiveDoctor(){
		return doctorRepository.findByIsActive(true);
	}
	
	public List<Doctor> getAllInactiveDoctor(){
		return doctorRepository.findByIsActive(false);
	}
	
	@Transactional
    public Doctor activeDoctor(Long id) {
        Optional<Doctor> DoctorOptional = doctorRepository.findById(id);
        if (DoctorOptional.isPresent()) {
        	Doctor existingDoctor = DoctorOptional.get();
        	existingDoctor.setActive(true);
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }
	
	@Transactional
    public Doctor inactiveDoctor(Long id) {
        Optional<Doctor> DoctorOptional = doctorRepository.findById(id);
        if (DoctorOptional.isPresent()) {
        	Doctor existingDoctor = DoctorOptional.get();
        	existingDoctor.setActive(false);
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }
	
	public List<Doctor> getAllDoctorbyDepartment(Long departmentId){
		Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
		if(optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			return doctorRepository.findByDepartment(department);
		}
		return null;
	}
	
	public List<Doctor> getAllDoctorbyAccount(Long accountId){
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			return doctorRepository.findByAccount(account);
		}
		return null;
	}
	
	public Optional<Doctor> getOneDoctor(Long id){
		return doctorRepository.findById(id);
	}
	
	@Transactional
	public Doctor createDoctor(DoctorRequest doctorRequest) {
//		Set<Department> department = new HashSet<>();
		Set<Account> account = new HashSet<>();
		
		Account accountDoctor = accountRepository.findById(doctorRequest.getAccount()).orElseThrow(() 
				-> new RuntimeException("Error: Account is not found."));
		Department departmentDoctor = departmentRepository.findById(doctorRequest.getDepartment()).orElseThrow(() 
				-> new RuntimeException("Error: Department is not found."));
		
		Degree degree = degreeRepository.findById(doctorRequest.getDegree()).orElseThrow(() 
				-> new RuntimeException("Error: Department is not found."));
		Doctor doctor = new Doctor(doctorRequest.getFullname(),doctorRequest.getPhone(), doctorRequest.getBirthday(),
				doctorRequest.getAddress(), doctorRequest.getGender(),
				 doctorRequest.getExperience(),doctorRequest.getAppointmentPrice());
		
//		storageService.save(doctorRequest.getAvatar());
//		
//		doctor.setAvatar(doctorRequest.getAvatar().getOriginalFilename());
		account.add(accountDoctor);
		doctor.setDepartment(departmentDoctor);
		doctor.setDegree(degree);
		doctor.setAccount(account);
		return doctorRepository.save(doctor);
	}
	
	@Transactional
	public Doctor updateDoctor(Long id, DoctorRequest doctorRequest) {
		Optional<Doctor> doctorOptional = doctorRepository.findById(id);
		if(doctorOptional.isPresent()) {
//			Doctor exsitDoctor = doctorOptional.get();
			
			Doctor updateOneDoctor = new Doctor( doctorRequest.getFullname(),doctorRequest.getPhone(),doctorRequest.getBirthday(),
					doctorRequest.getAddress(), doctorRequest.getGender(),
					 doctorRequest.getExperience(),doctorRequest.getAppointmentPrice());
			
//			Set<Account> account = new HashSet<>();
			
			Degree degree = degreeRepository.findById(doctorRequest.getDegree()).orElseThrow(() 
					-> new RuntimeException("Error: Department is not found."));
			
			
			Department departmentDoctor = departmentRepository.findById(doctorRequest.getDepartment()).orElseThrow(() 
					-> new RuntimeException("Error: Department is not found."));
						
//			storageService.delete(doctorOptional.get().getAvatar());
//			storageService.save(doctorRequest.getAvatar());
			updateOneDoctor.setAccount(doctorOptional.get().getAccount());
			updateOneDoctor.setId(doctorOptional.get().getId());
			updateOneDoctor.setDepartment(departmentDoctor);
			updateOneDoctor.setDegree(degree);
			updateOneDoctor.setAvatar(doctorOptional.get().getAvatar());
//			updateOneDoctor.setAvatar(file.getOriginalFilename());
			return doctorRepository.save(updateOneDoctor);
		}
		return null;
	}
	
	@Transactional
	public Doctor uploadAvatarDoctor(Long id, MultipartFile file) {
		Optional<Doctor> doctorOptional = doctorRepository.findById(id);
		if(doctorOptional.isPresent()) {
			Doctor exsitDoctor = doctorOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.save(file,newfileName);
			exsitDoctor.setAvatar(newfileName);
			return doctorRepository.save(exsitDoctor);
		}
		return null;
	}
	
	@Transactional
	public Doctor updateAvatarDoctor(Long id, MultipartFile file) {
		Optional<Doctor> doctorOptional = doctorRepository.findById(id);
		if(doctorOptional.isPresent()) {
			Doctor exsitDoctor = doctorOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.delete(exsitDoctor.getAvatar());
			
			storageService.save(file,newfileName);
			exsitDoctor.setAvatar(newfileName);
			return doctorRepository.save(exsitDoctor);
		}
		return null;
	}
	
	@Transactional
	public void deleteDoctorById(Long id) {
		doctorRepository.deleteById(id);
	}
}
