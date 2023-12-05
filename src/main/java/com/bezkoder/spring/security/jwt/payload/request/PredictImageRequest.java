package com.bezkoder.spring.security.jwt.payload.request;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.bezkoder.spring.security.jwt.models.User;

public class PredictImageRequest {
	@Size(max = 500)
	private String predictPathImage;
	
	private Long user;

	public String getPredictPathImage() {
		return predictPathImage;
	}

	public void setPredictPathImage(String predictPathImage) {
		this.predictPathImage = predictPathImage;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
	
	
}
