package com.fabrikam.dao;   
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;  
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.fabrikam.testAzureApp.*;
public class DataDaoImpl implements DataDao {  
  
 @Autowired  
 SessionFactory sessionFactory;
 
 
// @Override  
 @Transactional  
 public int insertRow(Info info) {  
  Session session = sessionFactory.openSession();  
  Transaction tx = session.beginTransaction();  
  String query = "SELECT id,type,name,jsonElement,sub_id from Info where type='" + info.getType() + "' and name='"+ info.getName() +"'";
  System.out.println(query);
  Query result = session.createQuery(query);
  List<?> list1 = result.list();
  
  if(list1.size()>0) {
	  System.out.println((list1.get(0)));
	  Info model=new Info();
	   Object[] row = (Object[]) list1.get(0);
	   model.setId((Integer) row[0]);
	   model.setType((String) row[1]);
	   model.setName((String) row[2]);
	   model.setJsonElement((String) row[3]);
	   model.setSub_id((String) row[4]);
	   info=model;
	   session.update(info);
	  }
  
  else
	  session.saveOrUpdate(info);  
  tx.commit();  
  Serializable id = session.getIdentifier(info);  
  session.close();  
  return (Integer) id;
 
 }  
  
 
 public int insertUser(User user) {
	 Session session = sessionFactory.openSession();  
	  Transaction tx = session.beginTransaction();  
	   session.saveOrUpdate(user);  
	  tx.commit();  
	  Serializable id = session.getIdentifier(user);  
	  session.close();  
	  return (Integer) id;
	 
 }
 
 
// @Override  
 public List<Info> getList() {  
  Session session = sessionFactory.openSession();  
  @SuppressWarnings("unchecked")  
  List<Info> ResourseList = session.createQuery("from Info")  
    .list();  
  session.close();  
  return ResourseList;  
 }  
  
 //@Override  
 public Info getRowById(int id) {  
  Session session = sessionFactory.openSession();  
  Info info = (Info) session.load(Info.class, id);  
  return info;  
 }  
  

//@Override
public int updateRow(Info info) 
{
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	session.saveOrUpdate(info);
	tx.commit();
	Serializable id = session.getIdentifier(info);	
	session.close();
	//deleteRow((Integer) id);
	return (Integer) id;
}
  
 //@Override  
 public int deleteRow(int id) 
 {  
	  Session session = sessionFactory.openSession();  
	  Transaction tx = session.beginTransaction();  
	  Info info = (Info) session.load(Info.class, id);  
	  session.delete(info);  
	  tx.commit();  
	  Serializable ids = session.getIdentifier(info);  
	  session.close();  
	  return (Integer) ids;  
 }  
  
 
 public HashMap<String, Long> getList_count(){
	 Session session = sessionFactory.openSession();  
	  Transaction tx = session.beginTransaction();  
	  String query = "SELECT type,count(type) FROM Info group by type";
	  Query result = session.createQuery(query);
	  HashMap<String, Long> model = new HashMap<String, Long>();	  
	  List<?> list = result.list();
	  for(int i=0; i<list.size(); i++) {
	   Object[] row = (Object[]) list.get(i);
	   model.put((String) row[0],(Long) row[1]);
	  }
	  session.close();
	  return model;
 }
 
 public ArrayList res_list(String name){
	  ArrayList<Info> list=new ArrayList<Info>();
	  Session session = sessionFactory.openSession();  
	  Transaction tx = session.beginTransaction();  
	  
	  String query = "SELECT type,name,jsonElement,sub_id FROM Info where type='" + name + "'";
	  Query result = session.createQuery(query);
	  List<?> list1 = result.list();
	  ArrayList<Info> l_list=new ArrayList<Info>();
	 
	  
	  for(int i=0; i<list1.size(); i++) {
		  Info xy=new Info();
		   Object[] row = (Object[]) list1.get(i);
		   xy.setType((String) row[0]);
		   xy.setName((String) row[1]);
		   xy.setJsonElement((String) row[2]);
		   xy.setSub_id((String) row[3]);
		   l_list.add(xy);
		  }
	 return l_list;
 }
 
 
 
 
 
 @Transactional  
 public int insertRow_billing(Billing_model Billing_model) {  
  Session session = sessionFactory.openSession();  
  Transaction tx = session.beginTransaction();  
   session.saveOrUpdate(Billing_model);  
  tx.commit();  
  Serializable id = session.getIdentifier(Billing_model);  
  session.close();  
  return (Integer) id;
 }
 
 
 public List<User> getUserList()
 {
	// ArrayList<Info> list=new ArrayList<Info>();
	  Session session = sessionFactory.openSession();  
	  Transaction tx = session.beginTransaction();  
	  
	  String query = "SELECT first_name,last_name,user_name,email_id,password FROM User";
	  Query result = session.createQuery(query);
	  List<?> list1 = result.list();
	  ArrayList<User> l_list=new ArrayList<User>();
	 
	  
	  for(int i=0; i<list1.size(); i++) {
		  User xy=new User();
		   Object[] row = (Object[]) list1.get(i);
		   xy.setFirst_name((String) row[0]);
		   xy.setLast_name((String) row[1]);
		   xy.setUser_name((String) row[2]);
		   xy.setEmail_id((String) row[3]);
		   xy.setPassword((String) row[4]);
		   
		   l_list.add(xy);
		  }
	 return l_list;
	 
 }
 
}  
