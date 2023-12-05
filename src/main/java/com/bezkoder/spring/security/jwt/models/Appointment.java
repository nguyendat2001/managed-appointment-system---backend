package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	
	private Long price;
	
	private String bankName;
	
	private String creditNumber;
	
	private LocalDateTime createdAt;
	
	private String proofImage;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	@OneToOne
    @JoinColumn(name="worktime_id", nullable=false)
	private Worktime worktime;

	public Appointment() {
		super();
	}

	public Appointment(String message, Long price, String bankName, String creditNumber) {
		super();
		this.message = message;
		this.price = price;
		this.creditNumber = creditNumber;
		this.bankName = bankName;
		this.createdAt = LocalDateTime.now();
	}

	
	
	public String getProofImage() {
		return proofImage;
	}

	public void setProofImage(String proofImage) {
		this.proofImage = proofImage;
	}

	public Long getPrice() {
		return price;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Worktime getWorktime() {
		return worktime;
	}

	public void setWorktime(Worktime worktime) {
		this.worktime = worktime;
	}
	
	
}
