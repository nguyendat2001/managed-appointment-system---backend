package com.bezkoder.spring.security.jwt.payload.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PrescriptionRequest {
	@NotBlank
	@Size(max = 50)
	private String diagnostic;
	
	@NotBlank
	@Size(max = 1000)
	private String description;
	
	@NotBlank
	@Size(max = 1000)
	private String advice;
	
	private Long user;
	
	private Long doctor;
	
	private List<Long> medicines;
	
	private List<Integer> medicinesNumber;

	public List<Integer> getMedicinesNumber() {
		return medicinesNumber;
	}

	public void setMedicinesNumber(List<Integer> medicinesNumber) {
		this.medicinesNumber = medicinesNumber;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
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

	public List<Long> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Long> medicines) {
		this.medicines = medicines;
	}
	
	
}
