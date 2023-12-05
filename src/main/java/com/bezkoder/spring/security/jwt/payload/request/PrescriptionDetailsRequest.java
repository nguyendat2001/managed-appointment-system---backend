package com.bezkoder.spring.security.jwt.payload.request;

public class PrescriptionDetailsRequest {
	private Long prescription;
	
	private Long medicine;
	
	private Integer number;

	public Long getPrescription() {
		return prescription;
	}

	public void setPrescription(Long prescription) {
		this.prescription = prescription;
	}

	public Long getMedicine() {
		return medicine;
	}

	public void setMedicine(Long medicine) {
		this.medicine = medicine;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
