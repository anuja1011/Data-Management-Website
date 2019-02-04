package com.data_management.bean;

public class UserPOJO {
	 
	private String userId;
	private String fullName;
	private String username;
	private String email;
	private String password;
	private String type;
	
	public UserPOJO(String userId, String fullName, String username, String email, String password, String type) {

		this.userId = userId;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public UserPOJO() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
