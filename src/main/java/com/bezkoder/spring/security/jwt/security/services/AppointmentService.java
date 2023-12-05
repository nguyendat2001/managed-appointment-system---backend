package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.Appointment;
import com.bezkoder.spring.security.jwt.models.Room;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.models.Worktime;
import com.bezkoder.spring.security.jwt.payload.request.AppointmentRequest;
import com.bezkoder.spring.security.jwt.repository.AccountRepository;
import com.bezkoder.spring.security.jwt.repository.AppointmentRepository;
import com.bezkoder.spring.security.jwt.repository.DoctorRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.repository.WorktimeRepository;
import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;
import com.bezkoder.spring.security.jwt.security.services.mailService.EmailSenderService;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	WorktimeRepository worktimeRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	public List<Appointment> getAllAppointment(){
		return appointmentRepository.findAll();
	}

	public List<Appointment> getAllAppointmentByDoctorId(Long doctorId){
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() 
				-> new RuntimeException("Error: doctor is not found."));
//		System.out.println(doctorId);
		List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
//		System.out.println(appointments.toString());
		return appointments;
	}
	
	public List<Appointment> getAllAppointmentByUserId(Long userId){
		User user = userRepository.findById(userId).orElseThrow(() 
				-> new RuntimeException("Error: user is not found."));
		return appointmentRepository.findByUser(user);
	}
	
	public List<Appointment> getAllAppointmentByWorktimeId(Long worktimeId){
		Worktime worktime = worktimeRepository.findById(worktimeId).orElseThrow(() 
				-> new RuntimeException("Error: user is not found."));
		return appointmentRepository.findByWorktime(worktime);
	}
	
	public Appointment getAppointmentById(Long id) {
		return appointmentRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Appointment createAppointment(AppointmentRequest appointmentRequest) {
		Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctor()).orElseThrow(() 
				-> new RuntimeException("Error: doctor is not found."));
		
		User user = userRepository.findById(appointmentRequest.getUser()).orElseThrow(() 
				-> new RuntimeException("Error: user is not found."));
		
		Worktime worktime = worktimeRepository.findById(appointmentRequest.getWorktime()).orElseThrow(() 
				-> new RuntimeException("Error: Worktime is not found."));
		
		if(worktime.getIsAvailable() == false) {
			return null;
		}else {
			worktime.setIsAvailable(false);
			
			Appointment appointment = new Appointment(appointmentRequest.getMessage(),appointmentRequest.getPrice(),appointmentRequest.getBankName(),appointmentRequest.getCreditNumber());
			appointment.setDoctor(doctor);
			appointment.setUser(user);
			appointment.setWorktime(worktime);
			worktimeRepository.save(worktime);
			
			String mailTo = "";
        	String userName = "";
        	
        	for (Account i : user.getAccount()) {
        		mailTo =i.getEmail();
        		userName = i.getUsername();
        	}
        	
        	String mailDoctor = "";
//        	String userName = "";
        	
        	for (Account i : doctor.getAccount()) {
        		mailDoctor =i.getEmail();
//        		userName = i.getUsername();
        	}
			
			emailSenderService.sendMail(user.getFullname(), mailTo, user.getPhone(), doctor.getFullname(), mailDoctor, doctor.getPhone(), appointmentRequest.getCreditNumber(), appointmentRequest.getBankName(), worktime.getTime().toString(), worktime.getWorkday().getDay().toString(), doctor.getAppointmentPrice().toString());
			
			return appointmentRepository.save(appointment);
		}
		
	}
	
	@Transactional
	public void deleteAppointmentById(Long id) {
		appointmentRepository.deleteById(id);
	}
	
	@Transactional
    public Appointment updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Optional<Appointment> AppointmentOptional = appointmentRepository.findById(id);
        if (AppointmentOptional.isPresent()) {
        	Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctor()).orElseThrow(() 
    				-> new RuntimeException("Error: doctor is not found."));
    		
    		User user = userRepository.findById(appointmentRequest.getUser()).orElseThrow(() 
    				-> new RuntimeException("Error: user is not found."));
    		
    		Worktime worktime = worktimeRepository.findById(appointmentRequest.getWorktime()).orElseThrow(() 
    				-> new RuntimeException("Error: Worktime is not found."));
    		Appointment appointment = AppointmentOptional.get();
    		appointment.setMessage(appointmentRequest.getMessage());
    		appointment.setDoctor(doctor);
    		appointment.setUser(user);
    		appointment.setPrice(appointmentRequest.getPrice());
    		appointment.setBankName(appointmentRequest.getBankName());
    		appointment.setCreditNumber(appointmentRequest.getCreditNumber());
    		appointment.setWorktime(worktime);
    		appointment.setId(id);
            return appointmentRepository.save(appointment);
        }
        return null;
    }
	
	@Transactional
	public Appointment uploadFile(Long id, MultipartFile file) {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if(appointmentOptional.isPresent()) {
			Appointment exsitAppointmentOptional = appointmentOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() + typeFile;
//			System.out.println(newfileName);
			storageService.save_proof(file, newfileName);
			exsitAppointmentOptional.setProofImage(newfileName);
			return appointmentRepository.save(exsitAppointmentOptional);
		}
		return null;
	}
	
	@Transactional
	public Appointment updateFile(Long id, MultipartFile file) {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if(appointmentOptional.isPresent()) {
			Appointment exsitAppointmentOptional = appointmentOptional.get();
			String typeFile = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
			UUID uuid = UUID.randomUUID();
			String newfileName = uuid.toString() +typeFile;
			storageService.delete_proof(exsitAppointmentOptional.getProofImage());
			storageService.save_proof(file,newfileName);
			exsitAppointmentOptional.setProofImage(newfileName);
			return appointmentRepository.save(exsitAppointmentOptional);
		}
		return null;
	}
}
