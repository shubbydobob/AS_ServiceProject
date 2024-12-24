package com.project.dto;

public class User {
	
	private String id;
	private String password;
	
	
	// Constructor accepting id and password
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // Default constructor (if needed)
    public User() {
        // Initialize fields if necessary
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + "]";
	}
	
	

}
