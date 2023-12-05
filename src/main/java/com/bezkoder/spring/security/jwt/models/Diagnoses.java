package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "diagnoses")
public class Diagnoses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String techniqueDiagnoses;
	
	@NotBlank
	@Size(max = 100)
	private String resultDiagnoses;
	
	@NotBlank
	@Size(max = 1000)
	private String describeDiagnoses;
	
	private LocalDateTime createAt;
	
	private Boolean isActive;
	
	@ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
    @JoinColumn(name="disease_id", nullable=false)
	private Disease disease;
	
	@OneToMany(mappedBy="diagnoses",fetch = FetchType.LAZY)
	private List<DiagnosesImage> diagnosesImage;

	public Diagnoses(@NotBlank @Size(max = 100) String technique, @NotBlank @Size(max = 100) String result,
			@NotBlank @Size(max = 1000) String describe) {
		super();
		this.techniqueDiagnoses = technique;
		this.resultDiagnoses = result;
		this.describeDiagnoses = describe;
		this.createAt = LocalDateTime.now();
		this.isActive = true;
	}

	public Diagnoses() {
		super();
	}
	
	

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTechnique() {
		return techniqueDiagnoses;
	}

	public void setTechnique(String technique) {
		this.techniqueDiagnoses = technique;
	}

	public String getResult() {
		return resultDiagnoses;
	}

	public void setResult(String result) {
		this.resultDiagnoses = result;
	}

	public String getDescribe() {
		return describeDiagnoses;
	}

	public void setDescribe(String describe) {
		this.describeDiagnoses = describe;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
}
