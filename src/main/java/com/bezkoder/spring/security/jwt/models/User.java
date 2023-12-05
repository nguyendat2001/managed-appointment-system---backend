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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String fullname;
	
	@NotBlank
	@Size(max = 100)
	private String phone;
	
//	@NotBlank
//	@Size(max = 20)
	private LocalDate birthday;
	
	@NotBlank
	@Size(max = 100)
	private String address;
	
//	@NotBlank
//	@Size(max = 100)
	private Boolean gender;
	
	private boolean  isActive;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_account", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "account_id"))
	private Set<Account> account = new HashSet<>();

	@OneToMany(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private List<Appointment> appointment;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private List<Prescription> prescription;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private List<PredictImage> predictImage;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private List<Reservation> reservations;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private List<Diagnoses> diagnose;
	
	public User() {
		super();
	}

	
	public User(@NotBlank @Size(max = 100) String fullname, @NotBlank @Size(max = 100) String phone, LocalDate birthday,
			@NotBlank @Size(max = 100) String address, Boolean gender) {
		super();
		this.fullname = fullname;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.isActive = true;
	}


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
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
	
	
}
