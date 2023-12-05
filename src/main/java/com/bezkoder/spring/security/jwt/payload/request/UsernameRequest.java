package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;

public class UsernameRequest {
	  @NotBlank
	  private String username;
	
	  public String getUsername() {
	    return username;
	  }
	
	  public void setUsername(String username) {
	    this.username = username;
	  }
}
