package com.fabrikam.testAzureApp;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSON; 

@Entity  
@Table(name="user_details")
public class User {
	@Id 
	@Column(name="id") 
	 private int id;
	@Column(name="first_name")
	 private String first_name;
	@Column(name="last_name")
	 private String last_name;
	@Column(name="user_name")
	 private String user_name;
	@Column(name="email_id")
	 private String email_id;
	@Column(name="password")
	 private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
}
