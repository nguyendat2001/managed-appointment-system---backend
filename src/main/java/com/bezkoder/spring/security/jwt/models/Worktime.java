package com.bezkoder.spring.security.jwt.models;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "worktimes")
public class Worktime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalTime time;
    
    private Boolean isAvailable;
	
    @ManyToOne
    @JoinColumn(name="workday_id", nullable=false)
    private Workday workday;
    
    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;
    
    @OneToOne(mappedBy="worktime",fetch = FetchType.LAZY)
    private Appointment appointment;
    
	public Worktime() {
		super();
	}

	public Worktime(LocalTime time) {
		super();
		this.time = time;
		this.isAvailable = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Workday getworkdays() {
		return workday;
	}

	public void setworkdays(Workday workday) {
		this.workday = workday;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Workday getWorkday() {
		return workday;
	}

	public void setWorkday(Workday workday) {
		this.workday = workday;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
	
}
