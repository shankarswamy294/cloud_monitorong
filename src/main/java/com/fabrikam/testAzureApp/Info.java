package com.fabrikam.testAzureApp;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSON; 

@Entity  
@Table(name="AzureDetails")
public class Info {
	@Id 
	 @Column(name="id") 
	 private int id;
	@Column(name="type")
	 private String type;
	@Column(name="sub_id")
	 private String sub_id;
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	@Column(name="name")
	 private String name;
	 public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "jsonElement")
	private String jsonElement;
	 public String getJsonElement() {
		return jsonElement;
	}
	public void setJsonElement(String jsonElement) {
		this.jsonElement = jsonElement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
}
