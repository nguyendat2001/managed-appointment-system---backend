package com.bezkoder.spring.security.jwt.payload.request;

public class HyperParamResultRequest {
	private Long model;
	
	private Long hyperParam;
	
	private Float value;

	
	
	public Long getHyperParam() {
		return hyperParam;
	}

	public void setHyperParam(Long hyperParam) {
		this.hyperParam = hyperParam;
	}

	public Long getModel() {
		return model;
	}

	public void setModel(Long model) {
		this.model = model;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
	
	
}
