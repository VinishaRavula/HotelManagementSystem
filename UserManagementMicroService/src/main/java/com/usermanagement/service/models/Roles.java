package com.usermanagement.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Roles")
public class Roles {
	@Id
	private String id;
	
	private EnumRole name;
	
	public Roles(EnumRole name) {
		super();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumRole getName() {
		return name;
	}

	public void setName(EnumRole name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + "]";
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
