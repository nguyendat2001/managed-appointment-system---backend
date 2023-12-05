package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;

public class WorkdayRequest {
	private LocalDate day;
	
	private Long doctor;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}
	
	
}
