package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.User;

public class AppointmentRequest {
	
	
	private String message;
	
	private Long price;
	
	private String bankName;
	
	private String creditNumber;
		
	private Long user;
	
	private Long doctor;

	private Long worktime;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public Long getWorktime() {
		return worktime;
	}

	public void setWorktime(Long worktime) {
		this.worktime = worktime;
	}
	
	
}
