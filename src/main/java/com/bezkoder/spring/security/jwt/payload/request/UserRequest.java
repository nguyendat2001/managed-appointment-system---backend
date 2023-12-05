package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserRequest {
	
	@NotBlank
	@Size(max = 100)
	private String fullname;
	
	@NotBlank
	@Size(max = 100)
	private String phone;
	
	private LocalDate birthday;
	
	@NotBlank
	@Size(max = 100)
	private String address;

	private Boolean gender;
	
	private Long account;

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

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
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

	

}
