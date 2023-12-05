package com.bezkoder.spring.security.jwt.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String fullname;
	
	@NotBlank
	@Size(max = 20)
	private String phone;
	
//	@NotBlank
//	@Size(max = 20)
	private LocalDate birthday;
	
//	@NotBlank
//	@Size(max = 100)
	private String avatar;
	
	@NotBlank
	@Size(max = 100)
	private String address;
	
//	@NotBlank
//	@Size(max = 100)
	private Boolean gender;
	
	private Float appointmentPrice;
	
//	private String degree;
	
	@NotBlank
	@Size(max = 100)
	private String experience;
	
	private boolean  isActive;
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "doctors_department", 
//				joinColumns = @JoinColumn(name = "doctor_id"), 
//				inverseJoinColumns = @JoinColumn(name = "department_id"))
	@ManyToOne
    @JoinColumn(name="department_id", nullable=false)
	private Department department;
	
	@ManyToOne
    @JoinColumn(name="degree_id", nullable=false)
	private Degree degree;
	
	@OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
    private List<Worktime> worktimes;
	
	@OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
    private List<Workday> Workdays;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "doctor_account", 
				joinColumns = @JoinColumn(name = "doctor_id"), 
				inverseJoinColumns = @JoinColumn(name = "account_id"))
	private Set<Account> account = new HashSet<>();
	
	@OneToMany(mappedBy = "doctor")
    @PrimaryKeyJoinColumn
    private List<Appointment> appointment;
	
	@OneToMany(mappedBy = "doctor")
    @PrimaryKeyJoinColumn
    private List<Certificate> certificate;
	
	@OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
	private List<Prescription> prescription;
	
	@OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
    private List<Diagnoses> diagnose;
	
	public Doctor() {
		super();
	}

	
	
	public Doctor(@NotBlank @Size(max = 100) String fullname, @NotBlank @Size(max = 20) String phone , LocalDate birthday,
			@NotBlank @Size(max = 100) String address, Boolean gender,
			@NotBlank @Size(max = 100) String experience, Float appointmentPrice) {
		super();
		this.fullname = fullname;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.experience = experience;
		this.appointmentPrice = appointmentPrice;
		this.isActive = true;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Float getAppointmentPrice() {
		return appointmentPrice;
	}

	public void setAppointmentPrice(Float appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}
	
	
}
