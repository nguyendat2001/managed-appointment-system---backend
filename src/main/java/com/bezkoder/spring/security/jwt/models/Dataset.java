package com.bezkoder.spring.security.jwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "datasets")
public class Dataset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	private Integer sampleTrain;
	
	private Integer sampleTest;
	
	private Integer sampleVal;
	
	@NotBlank
	@Size(max = 50)
	private String originalSize;
	
	private Boolean isActive;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "dataset_disease", 
				joinColumns = @JoinColumn(name = "dataset_id"), 
				inverseJoinColumns = @JoinColumn(name = "disease_id"))
	private Set<Disease> diseases = new HashSet<>();

	
	public Dataset() {
		super();
	}

	public Dataset(@NotBlank @Size(max = 50) String name, Integer sampleTrain, Integer sampleTest, Integer sampleVal,
			@NotBlank @Size(max = 50) String originalSize) {
		super();
		this.name = name;
		this.sampleTrain = sampleTrain;
		this.sampleTest = sampleTest;
		this.sampleVal = sampleVal;
		this.originalSize = originalSize;
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

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

}
