package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "certificates")
public class Certificate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	private String path;
	
	@NotBlank
	@Size(max = 100)
	private String certificationUnit;
	
	private LocalDate validFrom;
	
	@ManyToOne
    @JoinColumn(name="ward_id", nullable=false)
	private Ward ward;
	
	@ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;

	
	
	public Certificate() {
		super();
	}

	public Certificate( @NotBlank @Size(max = 100) String name,
			@NotBlank @Size(max = 100) String certificationUnit, LocalDate validFrom) {
		super();
		this.name = name;
		this.certificationUnit = certificationUnit;
		this.validFrom = validFrom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCertificationUnit() {
		return certificationUnit;
	}

	public void setCertificationUnit(String certificationUnit) {
		this.certificationUnit = certificationUnit;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}