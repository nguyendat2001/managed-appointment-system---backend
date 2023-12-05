package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class DoctorRequest {
	
//	@NotBlank
//	@Size(max = 20)
	private LocalDate birthday;
	
	@NotBlank
	@Size(max = 100)
	private String fullname;
	
	@NotBlank
	@Size(max = 20)
	private String phone;
	
	private Float appointmentPrice;
	
	@NotBlank
	@Size(max = 100)
	private String address;
	
//	@NotBlank
	private Boolean gender;
	

	private Long degree;
	
	@NotBlank
	@Size(max = 100)
	private String experience;

	private Long account;
	
	private Long department;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Float getAppointmentPrice() {
		return appointmentPrice;
	}

	public void setAppointmentPrice(Float appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Long getDegree() {
		return degree;
	}

	public void setDegree(Long degree) {
		this.degree = degree;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

//	public MultipartFile getAvatar() {
//		return avatar;
//	}
//
//	public void setAvatar(MultipartFile avatar) {
//		this.avatar = avatar;
//	}
	
	

}
