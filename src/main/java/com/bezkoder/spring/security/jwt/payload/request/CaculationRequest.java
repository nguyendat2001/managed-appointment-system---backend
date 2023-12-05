package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CaculationRequest {
	@NotBlank
	@Size(max = 100)
	private String name;
	
	@NotBlank
//	@Size(max = 500)
	private String describe;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
