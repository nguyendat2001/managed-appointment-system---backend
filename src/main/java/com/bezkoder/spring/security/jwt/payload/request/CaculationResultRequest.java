package com.bezkoder.spring.security.jwt.payload.request;

public class CaculationResultRequest {
	private Long model;
	
	private Long caculation;
	
	private Float valueOnTrain;
	
	private Float valueOnTest;

	public Long getModel() {
		return model;
	}

	public void setModel(Long model) {
		this.model = model;
	}

	public Long getCaculation() {
		return caculation;
	}

	public void setCaculation(Long caculation) {
		this.caculation = caculation;
	}

	public Float getValueOnTrain() {
		return valueOnTrain;
	}

	public void setValueOnTrain(Float valueOnTrain) {
		this.valueOnTrain = valueOnTrain;
	}

	public Float getValueOnTest() {
		return valueOnTest;
	}

	public void setValueOnTest(Float valueOnTest) {
		this.valueOnTest = valueOnTest;
	}
	
	
}
