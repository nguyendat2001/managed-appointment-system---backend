package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "prescriptions")
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String diagnostic;
	
	@NotBlank
	@Size(max = 1000)
	private String description;
	
	@NotBlank
	@Size(max = 1000)
	private String advice;
	
//	@NotBlank
	private LocalDateTime createAt;
	
	private Boolean isActive;
	
	@OneToMany(mappedBy="prescription",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrescriptionDetails> prescriptionDetail;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;

	public Prescription() {
		super();
	}

	public Prescription(@NotBlank @Size(max = 50) String diagnostic, @NotBlank @Size(max = 1000) String description,
			@NotBlank @Size(max = 1000) String advice) {
		super();
		this.diagnostic = diagnostic;
		this.description = description;
		this.advice = advice;
		this.createAt = LocalDateTime.now();
		this.isActive = true;
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

//	public Set<Medicine> getMedicines() {
//		return medicines;
//	}
//
//	public void setMedicines(Set<Medicine> medicines) {
//		this.medicines = medicines;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	
}
