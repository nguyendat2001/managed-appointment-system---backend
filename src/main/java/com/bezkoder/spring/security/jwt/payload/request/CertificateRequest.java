package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CertificateRequest {

	private String name;
	
	private String certificationUnit;
	
	private LocalDate validFrom;
	
	private Long ward;
	
	private Long doctor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}

	public String getCertificationUnit() {
		return certificationUnit;
	}

	public void setCertificationUnit(String certificationUnit) {
		this.certificationUnit = certificationUnit;
	}

	public LocalDate validFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public Long getWard() {
		return ward;
	}

	public void setWard(Long ward) {
		this.ward = ward;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}
	
	
}
