package com.fabrikam.testAzureApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="billing")
public class Billing_model {
	@Id 
	 @Column(name="id") 
	 private int id;
	
	@Column(name="billing_info")
	private String billing_json;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getBilling_json() {
		return billing_json;
	}

	public void setBilling_json(String billing_json) {
		this.billing_json = billing_json;
	}
}
