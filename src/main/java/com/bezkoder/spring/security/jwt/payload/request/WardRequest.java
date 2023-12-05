package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WardRequest {
	@NotBlank
	@Size(max = 20)
	private String name;
	
	private Long district;

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
