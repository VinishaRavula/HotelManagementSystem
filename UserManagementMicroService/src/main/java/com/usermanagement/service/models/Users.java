package com.usermanagement.service.models;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Users")
public class Users {
	@Id
	private String id;

	@NotBlank
	@Size(max = 20, message = "Username Can not be More than 20 Words")
	private String username;

	@NotBlank
	@Email
	@Size(max = 50, message = "Email Can not be More than 50 Words")
	private String email;

	@NotBlank
	@Size(max = 50, message = "Password Can me Maximum of 50 words")
	private String password;

	@DBRef
	private Set<Roles> roles;

	public Users(@NotBlank @Size(max = 20, message = "Username Can not be Mpre than 20 Words") String username,
			@NotBlank @Email @Size(max = 50, message = "Email Can not be More than 50 Words") String email,
			@NotBlank @Size(max = 50, message = "Password Can me Maximum of 50 words") String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles="
				+ roles + "]";
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String id,
			@NotBlank @Size(max = 20, message = "Username Can not be More than 20 Words") String username,
			@NotBlank @Email @Size(max = 50, message = "Email Can not be More than 50 Words") String email,
			@NotBlank @Size(max = 50, message = "Password Can me Maximum of 50 words") String password,
			Set<Roles> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}



}
