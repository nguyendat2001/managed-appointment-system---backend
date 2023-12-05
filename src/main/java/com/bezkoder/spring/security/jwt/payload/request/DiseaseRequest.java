package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DiseaseRequest {
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
