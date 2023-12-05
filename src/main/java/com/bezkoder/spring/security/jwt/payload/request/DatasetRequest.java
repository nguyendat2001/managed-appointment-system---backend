package com.bezkoder.spring.security.jwt.payload.request;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bezkoder.spring.security.jwt.models.Disease;

public class DatasetRequest {
	@NotBlank
	@Size(max = 50)
	private String name;
	
	private Integer sampleTrain;
	
	private Integer sampleTest;
	
	private Integer sampleVal;
	
	@NotBlank
	@Size(max = 50)
	private String originalSize;
	
	private List<Long> diseases;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSampleTrain() {
		return sampleTrain;
	}

	public void setSampleTrain(Integer sampleTrain) {
		this.sampleTrain = sampleTrain;
	}

	public Integer getSampleTest() {
		return sampleTest;
	}

	public void setSampleTest(Integer sampleTest) {
		this.sampleTest = sampleTest;
	}

	public Integer getSampleVal() {
		return sampleVal;
	}

	public void setSampleVal(Integer sampleVal) {
		this.sampleVal = sampleVal;
	}

	public String getOriginalSize() {
		return originalSize;
	}

	public void setOriginalSize(String originalSize) {
		this.originalSize = originalSize;
	}

	public List<Long> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Long> diseases) {
		this.diseases = diseases;
	}
	
	
}
