package com.bezkoder.spring.security.jwt.models;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 1000)
	private String describeDepartment;
	
	private Boolean isActive;
	
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY)
    private List<Doctor> doctors;
	
	public Department() {
		super();
	}

	public Department(@NotBlank @Size(max = 20) String name,@NotBlank
			@Size(max = 1000)
			String describeDepartment) {
		super();
		this.name = name;
		this.describeDepartment = describeDepartment;
		this.isActive = true;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public String getDescribeDepartment() {
		return describeDepartment;
	}

	public void setDescribeDepartment(String describeDepartment) {
		this.describeDepartment = describeDepartment;
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
	
	
}
