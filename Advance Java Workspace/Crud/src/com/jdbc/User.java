package com.jdbc;



public class User {

	private int User_id;
	private String Username;
	private String Pswd;
	private String email;
	private String address;
	private String role;
	

	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword1() {
		return Pswd;
	}
	public void setPassword1(String password1) {
		Pswd = password1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	
	public User(int user_id, String username, String password1, String email, String address, String role) {
		super();
		User_id = user_id;
		Username = username;
		Pswd = password1;
		this.email = email;
		this.address = address;
		this.role = role;
	}
	
	
	
	public User(String username, String pswd, String email, String address, String role) {
		super();
		Username = username;
		Pswd = pswd;
		this.email = email;
		this.address = address;
		this.role = role;
	}



	public User() { //We are using parameterized constructor.So frameworks like Hibernate and spring use 
		//default constructor use reflection to create objects. 
		super();
	}


	
	

	@Override
	public String toString() {
		return "User [User_id=" + User_id + ", Username=" + Username + ", Pswd=" + Pswd + ", email=" + email
				+ ", address=" + address + ", role=" + role + "]";
	}
	
	
	
}
