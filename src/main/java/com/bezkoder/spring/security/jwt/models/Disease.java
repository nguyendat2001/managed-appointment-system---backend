package com.bezkoder.spring.security.jwt.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "diseases", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name"),
		})
public class Disease {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotBlank
	@Size(max = 1000)
	private String description;
	
	@NotBlank
	@Size(max = 1000)
	private String symptom;
	
	@NotBlank
	@Size(max = 1000)
	private String reason;
	
	@NotBlank
	@Size(max = 1000)
	private String treaments;
	
	@NotBlank
	@Size(max = 1000)
	private String prevent;
	
	private Boolean isActive;
	
	@OneToMany(mappedBy="disease",fetch = FetchType.LAZY)
    private List<Diagnoses> diagnoses;
	
	public Disease() {
		super();
	}

	public Disease(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 1000) String description,
			@NotBlank @Size(max = 1000) String symptom, @NotBlank @Size(max = 1000) String reason,
			@NotBlank @Size(max = 1000) String treaments, @NotBlank @Size(max = 1000) String prevent) {
		super();
		this.name = name;
		this.description = description;
		this.symptom = symptom;
		this.reason = reason;
		this.treaments = treaments;
		this.prevent = prevent;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTreaments() {
		return treaments;
	}

	public void setTreaments(String treaments) {
		this.treaments = treaments;
	}

	public String getPrevent() {
		return prevent;
	}

	public void setPrevent(String prevent) {
		this.prevent = prevent;
	}
	
	
}
