package com.bezkoder.spring.security.jwt.payload.request;

import java.time.YearMonth;

public class AmoutUsedModelRequest {
	
	private YearMonth createdAt;

	private Long model;

	public YearMonth getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(YearMonth createdAt) {
		this.createdAt = createdAt;
	}

	public Long getModel() {
		return model;
	}

	public void setModel(Long model) {
		this.model = model;
	}

	
	
}
