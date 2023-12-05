package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(	name = "workdays")
public class Workday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate day;
	
	@OneToMany(mappedBy="workday",fetch = FetchType.LAZY)
    private List<Worktime> Worktimes;
	
	@ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	public Workday() {
		super();
	}

	public Workday(LocalDate day) {
		super();
		this.day = day;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}
