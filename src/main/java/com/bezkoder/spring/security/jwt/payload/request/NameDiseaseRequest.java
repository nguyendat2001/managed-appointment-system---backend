package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;

public class NameDiseaseRequest {
	  @NotBlank
	  private String name;
	
	  public String getname() {
	    return name;
	  }
	
	  public void setname(String name) {
	    this.name = name;
	  }
}
