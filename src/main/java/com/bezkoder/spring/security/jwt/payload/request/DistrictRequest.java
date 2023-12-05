package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DistrictRequest {
	@NotBlank
	@Size(max = 20)
	private String name;
	
	private Long province;

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
