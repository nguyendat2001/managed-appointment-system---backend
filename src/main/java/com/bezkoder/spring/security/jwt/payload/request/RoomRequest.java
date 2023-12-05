package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RoomRequest {
	@NotBlank
	@Size(max = 20)
	private String name;
	
	private Integer capacity;
	
//	@NotBlank
	private Boolean isPresidentRoom;

	public Boolean getIsPresidentRoom() {
		return isPresidentRoom;
	}

	public void setIsPresidentRoom(Boolean isPresidentRoom) {
		this.isPresidentRoom = isPresidentRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

}
