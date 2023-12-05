package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.bezkoder.spring.security.jwt.models.Bed;
import com.bezkoder.spring.security.jwt.models.User;

public class ReservationRequest {

	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Boolean isAccept;
	
	private Long bed;
	
	private Long user;

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}

	public Long getBed() {
		return bed;
	}

	public void setBed(Long bed) {
		this.bed = bed;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
	
	
}
