package com.d3vlin13.realtorreportgenerator.model;

import com.d3vlin13.realtorreportgenerator.model.Enums.EUserType;

public final class User {
	private EUserType type;
	private String username;
	private String password;
	
	public User() {

	}

	public User(EUserType type, String username, String password) {
		super();
		this.type = type;
		this.username = username;
		this.password = password;
	}

	public EUserType getType() {
		return type;
	}

	public void setType(EUserType type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
