package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BedRequest {
	@NotBlank
	@Size(max = 20)
	private String name;

	private Boolean isAvailable;
	
	private Long room;
	
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Long getRoom() {
		return room;
	}

	public void setRoom(Long room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
