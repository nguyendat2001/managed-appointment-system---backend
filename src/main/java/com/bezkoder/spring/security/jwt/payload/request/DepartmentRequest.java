package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartmentRequest {
	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 1000)
	private String describeDepartment;
	

	public String getDescribeDepartment() {
		return describeDepartment;
	}

	public void setDescribeDepartment(String describeDepartment) {
		this.describeDepartment = describeDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
