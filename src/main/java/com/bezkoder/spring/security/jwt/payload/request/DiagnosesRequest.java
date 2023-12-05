package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bezkoder.spring.security.jwt.models.Doctor;
import com.bezkoder.spring.security.jwt.models.User;

public class DiagnosesRequest {
	@NotBlank
	@Size(max = 100)
	private String technique;
	
	@NotBlank
	@Size(max = 100)
	private String result;
	
	@NotBlank
	@Size(max = 1000)
	private String describe;
		
	private Long doctor;

	private Long user;
	
	private Long disease;
	
	

	public Long getDisease() {
		return disease;
	}

	public void setDisease(Long disease) {
		this.disease = disease;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
	
	
}
