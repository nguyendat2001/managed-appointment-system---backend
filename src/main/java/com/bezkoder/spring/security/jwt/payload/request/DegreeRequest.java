package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;

public class DegreeRequest {
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
