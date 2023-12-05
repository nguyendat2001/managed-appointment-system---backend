package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(	name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Boolean isAccept;
		
	@ManyToOne
	@JoinColumn(name="bed_id", nullable=false)
	private Bed bed;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public Reservation(@NotBlank LocalDate checkInDate) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = null;
		this.isAccept = false;
	}

	public Reservation() {
		super();
	}

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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
	
	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
