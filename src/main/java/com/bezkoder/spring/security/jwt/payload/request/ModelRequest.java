package com.bezkoder.spring.security.jwt.payload.request;

public class ModelRequest {
	private String name;

	private Integer inputSize;
	
	private String path;
	
	private Long dataset;
	
	private Boolean isSegmetation;	
	
	private String labels;

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsSegmetation() {
		return isSegmetation;
	}

	public void setIsSegmetation(Boolean isSegmetation) {
		this.isSegmetation = isSegmetation;
	}

	public Long getDataset() {
		return dataset;
	}

	public void setDataset(Long dataset) {
		this.dataset = dataset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInputSize() {
		return inputSize;
	}

	public void setInputSize(Integer inputSize) {
		this.inputSize = inputSize;
	}
	
	
}
