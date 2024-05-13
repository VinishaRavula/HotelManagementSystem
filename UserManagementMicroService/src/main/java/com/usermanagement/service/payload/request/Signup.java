package com.usermanagement.service.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Signup {

  	@Size(min = 3, max = 20, message = "Username must be in between 3 & 20")
	private String username;

 	@Size(min = 12, max = 30, message = "Email must be in between 12 & 30")
	@Email
	private String email;

 	@Size(min = 6, max = 15, message = "Password must be in between 6 & 15")
	private String password;
	
	private Set<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Signup() {
		super();
	}

}