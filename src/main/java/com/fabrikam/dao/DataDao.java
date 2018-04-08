package com.fabrikam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fabrikam.testAzureApp.Billing_model;
import com.fabrikam.testAzureApp.*;


public interface DataDao {

	public int insertRow(Info info);  

	public List<Info> getList();  

	public Info getRowById(int id);  

	public int updateRow(Info info);  

	public int deleteRow(int id);  
	
	public HashMap getList_count();
	
	public ArrayList res_list(String name);
	
	//billing----------
	
	public int insertRow_billing(Billing_model billing_model);
	
	public int insertUser(User user);
	
	public List<User> getUserList();
}
