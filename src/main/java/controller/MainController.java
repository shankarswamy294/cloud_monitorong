package controller;
import java.io.File;

import com.fabrikam.testAzureApp.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import com.fabrikam.testAzureApp.*;

import org.apache.log4j.Logger;
//import javax.validation.Valid;
import org.hibernate.Session;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.microsoft.azure.CloudException;

//import net.minidev.json.parser.JSONParser;
//import net.minidev.json.parser.ParseException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;
import net.sf.json.JSON;
import org.hibernate.SessionFactory;


//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;



import com.fabrikam.dao.DataDao;
@Controller

public class MainController {
	
	@Autowired
	DataDao data;
	
	/*
	public ArrayList<JSONObject> getdetails(){
		ArrayList<JSONObject> json_array = new ArrayList<JSONObject>();
		List<Info> ResourseList = data.getList();
		  for(Object str:ResourseList){
			  Info json_collection=new Info();
			  json_collection=(Info) str;
			  JsonConverter newj=new JsonConverter();
			  //Map<String, String> map_json=newj.call_json_object(json_collection.getJsonElement());
			  JSON jsonObject = JSONSerializer.toJSON(json_collection.getJsonElement());
			  System.out.println(jsonObject);
			  json_array.add((JSONObject) jsonObject);
		  }
		  return json_array;
	}*/

	
	
	
	//welcome page calls this request map
	@RequestMapping("db")  
	    public ModelAndView helloWorld() {  
	        return new ModelAndView("db");  
	    }
	
	
	//this request map is active when user clicks on "Discover Resources" in testAzureApp/db and it adds or updates data in database and redirects to mainList page
	@RequestMapping("push")  
	 public ModelAndView registerUser() {  
		 App resourceCollectionMethod=new App();
		 ArrayList<Info> ResourceCollection=new ArrayList<Info>();
		 ResourceCollection=resourceCollectionMethod.details();
			for(Info res:ResourceCollection){
				try{
				 data.insertRow(res);  
				 }
				 catch(Exception e){
					 System.out.println("error : "+e);
				 }
			 }
		 return new ModelAndView("redirect:mainList");  
	}
	
	
	int c2=0;
	
	//this request map retrives data from database and gives list of instances and resources with there count
	@RequestMapping("mainList")
	public ModelAndView getMainList(){
		ArrayList<JSONObject> mainList_array = new ArrayList<JSONObject>();//global
		HashMap xx=data.getList_count();
		return new ModelAndView("mainList","currect_list",xx);
	}
	

	int c=0;
	//this request map gives the list and details of a particular instance or resource 
	@RequestMapping("list")  
	 public ModelAndView getList(@RequestParam String res_name) throws JsonParseException, IOException{  
		ArrayList<JSONObject> List_array = new ArrayList<JSONObject>();
		//if(c==0){
		ArrayList<Info> list = new ArrayList<Info>();
		list = data.res_list(res_name);
		List<Final_list> final_list=new ArrayList<Final_list>();
		for(Info json_d:list){
			JsonConverter newj=new JsonConverter();
			Final_list model = new Final_list();
			Map<String, String> map_json=newj.call_json_object(json_d.getJsonElement());
			model.setType(json_d.getType());
			model.setName(json_d.getName());
			model.setDetails(map_json);
			final_list.add(model);
		}
		System.out.println(final_list.get(0).getType());
		System.out.println(list);
	return new ModelAndView("list", "model", final_list);  
	 }
	
	
	//-----------------------------------------------------monitoring------------------------------------------------------------//
	
	//this request map gives the list of instances and resources with there count
	@RequestMapping("monitor_list")
	public ModelAndView getMonitorList(){
		ArrayList<JSONObject> mainList_array = new ArrayList<JSONObject>();//global
		HashMap xx=data.getList_count();
		return new ModelAndView("monitor_list","currect_list",xx);
	}
	
	//this request map gives the list of name of items in particular instance or resource
	//here i get resource type as a url parameter using which i am get the list of items of that resource type
	@RequestMapping("monitor_type")  
	 public ModelAndView getListToMonitor(@RequestParam String res_name) throws JsonParseException, IOException{  
		ArrayList<JSONObject> List_array = new ArrayList<JSONObject>();
		//if(c==0){
		ArrayList<Info> list = new ArrayList<Info>();
		list = data.res_list(res_name);
		
		List<Final_list> final_list=new ArrayList<Final_list>();
		
		for(Info json_d:list){
			JsonConverter newj=new JsonConverter();
			Final_list model = new Final_list();
			Map<String, String> map_json=newj.call_json_object(json_d.getJsonElement());
			model.setType(json_d.getType());
			model.setName(json_d.getName());
			model.setDetails(map_json);
			model.setSub_id(json_d.getSub_id());
			final_list.add(model);
		}
		System.out.println(final_list.get(0).getType());
		System.out.println(list);
	return new ModelAndView("monitor_type", "model", final_list);  
	 }
	
	int c3=0;
	
	/*
	@RequestMapping("result")
	public ModelAndView getResult(@RequestParam String res_name,@RequestParam int id,@RequestParam String key){
		
		ArrayList<JSONObject> result_array = new ArrayList<JSONObject>();//global
			List<Info> ResourseList = data.getList();
			  for(Object str:ResourseList){
				  Info json_collection=new Info();
				  json_collection=(Info) str;
				  JsonConverter newj=new JsonConverter();
				  //Map<String, String> map_json=newj.call_json_object(json_collection.getJsonElement());
				  JSON jsonObject = JSONSerializer.toJSON(json_collection.getJsonElement());
				  System.out.println(jsonObject);
				  JSONObject j_obj= (JSONObject) jsonObject;
				  String key_name= (String) j_obj.keys().next();
				  String[] parts = key_name.split(" ");
				  if(res_name.equals(parts[0])){
					  result_array.add((JSONObject) jsonObject);
				  }
			  }
		JsonConverter newj=new JsonConverter();
		Map<String, String> map_json=newj.call_json_object((result_array.get(id).opt(key).toString()));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("getval", map_json);
		model.put("keyname", key);
	return new ModelAndView("result","model",model);	
	}
	*/
	
	
	
	
	//this request map open form page for user to enter time stamp,metric type, interval and aggregate type
	//once clicked on submit it goes to /monitor page
	//it accepts subscription id as a url parameter and binds it to model object and send as a new model object to /monitor
	@RequestMapping("monitoring_form")
	@ModelAttribute
	public ModelAndView form(@RequestParam String sub_id,Model model) throws MalformedURLException, InterruptedException, ExecutionException{
		Metric_defination def=new Metric_defination();
		ArrayList metricNameList=def.metric_def(sub_id);
		model.addAttribute("monitoring_form_details",new monitoring_form_details());
		//model.put("monitor", new monitoring_form_details());
		model.addAttribute("name_list", metricNameList);
		model.addAttribute("sub_id", sub_id);
		return new ModelAndView ("Form_monitor","model", model);
	}
	
	
	
	
	
	
	//this request map takes the values from the form as modelAttribute and using it shows the list of time stamps of give time along with its aggregate values 
	 @RequestMapping(value = "/monitor", method = RequestMethod.POST)
	    public ModelAndView submit(@RequestParam String sub_id,@Valid @ModelAttribute("monitoring_form_details")monitoring_form_details monitoring_form_details, 
	      BindingResult result, ModelMap model) throws MalformedURLException, InterruptedException, ExecutionException, UnsupportedEncodingException {
	        model.addAttribute("startDateTime", monitoring_form_details.getStartDateTime());
	        model.addAttribute("endDateTime", monitoring_form_details.getEndDateTime());
	        model.addAttribute("metricType", monitoring_form_details.getMetricType());
	        model.addAttribute("sub_id", sub_id);
	        model.addAttribute("AggregationType",monitoring_form_details.getAggregationType());
	        model.addAttribute("interval",monitoring_form_details.getInterval());
	        
	        
	        Date_converter date_con= new Date_converter();
	        String start=(String) model.get("startDateTime");
	        String end=(String) model.get("endDateTime");
	        String final_start=date_con.date_converter(start.toString());
	        String final_end=date_con.date_converter(end.toString());
	        model.replace("startDateTime", monitoring_form_details.getStartDateTime(), final_start);
	   	 	model.replace("endDateTime", monitoring_form_details.getEndDateTime(), final_end);
	   	 	Get_metric_info get_metric_info=new Get_metric_info();
	   	 	ArrayList metric_info = get_metric_info.metric_info(model);
	   	 	JSONObject Json_object = null;
	   	 
	   	 	//json_obj is complete json object
	   	 	for(Object json_obj:metric_info){
	   	 		Json_object= (JSONObject) JSONSerializer.toJSON(json_obj);
	   	 		System.out.println(Json_object.get("value"));
	   	 	}
	   	 	
	   	 	//metric_in have value and again metric_in is assigned to timeseries
	   	 	List metric_in = (List) Json_object.get("value");
	   	 	Json_object=(JSONObject) JSONSerializer.toJSON(metric_in.get(0));
	   	    metric_in = (List) Json_object.get("timeseries");

	   	    
	   	    //now metric_in has list which contain json_object
	   	    //updating metric_in to value of data which contains timestamp information
	   	    Json_object=(JSONObject) JSONSerializer.toJSON(metric_in.get(0));
	   	    metric_in = (List) Json_object.get("data");
	   	    System.out.println(metric_in);
	        return new ModelAndView("metric_details","metric_in",metric_in);
	    }
	 
	 
	 
	 @RequestMapping("billing_form")
		@ModelAttribute
		public ModelAndView form(Model model) throws MalformedURLException, InterruptedException, ExecutionException{
			//model.addAttribute("Billing_form_model",new Billing_form_model());
			//model.put("monitor", new monitoring_form_details());
			return new ModelAndView ("form_billing","Billing_form_model", new Billing_form_model());
		}
	 
	 
	 @RequestMapping(value = "/billing", method = RequestMethod.POST)
	    public ModelAndView submit(@Valid @ModelAttribute("Billing_form_model")Billing_form_model Billing_form_model, 
	      BindingResult result, ModelMap model) throws InterruptedException, ExecutionException, CloudException, IOException {
	        model.addAttribute("startDateTime", Billing_form_model.getReportedStartDateTime());
	        model.addAttribute("endDateTime", Billing_form_model.getReportedEndDateTime());
	        
	        
	        Date_converter date_con= new Date_converter();
	        String start=(String) model.get("startDateTime");
	        String end=(String) model.get("endDateTime");
	        String final_start=date_con.date_converter(start.toString());
	        String final_end=date_con.date_converter(end.toString());
	        model.replace("startDateTime", Billing_form_model.getReportedStartDateTime(), final_start);
	   	 	model.replace("endDateTime", Billing_form_model.getReportedEndDateTime(), final_end);
	   	 	
	   	 	Get_cost get_cost=new Get_cost();
	   	 	
	   	 	ArrayList billing_result=get_cost.get_cost(final_start,final_end);
	   	 
	   	 	return new ModelAndView("billing_details","billing_result",billing_result);
	    }

	 
	/* 
	 @RequestMapping("billing_list")
		public ModelAndView getBillingList() throws CloudException, InterruptedException, ExecutionException, IOException{
			ArrayList billing_info=new ArrayList();
			Billing billing=new Billing();
			billing_info=billing.Billing_info();
			for(Object each:billing_info){
				data.insertRow_billing((Billing_model) each);
			}
			return new ModelAndView("billing_list","billing_info",billing_info);
		}
	 
	 @RequestMapping("usage_list")
		public ModelAndView getUsageList() throws CloudException, InterruptedException, ExecutionException, IOException{
		 	ArrayList usage_info=new ArrayList();
		 	Usage usage=new Usage();
		 	usage_info=usage.UsageInfo();
			return new ModelAndView("usage_list","usage_info",usage_info);
		}*/
	 
	 @RequestMapping("login_form")
		@ModelAttribute
		public ModelAndView login_form(Model model) throws MalformedURLException, InterruptedException, ExecutionException{
			//model.addAttribute("Billing_form_model",new Billing_form_model());
			//model.put("monitor", new monitoring_form_details());
			return new ModelAndView ("login_form","User", new User());
		}
	 
	 
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ModelAndView submit(@Valid @ModelAttribute("User")User User, 
	      BindingResult result, ModelMap model) throws InterruptedException, ExecutionException, CloudException, IOException {
	        model.addAttribute("user_name", User.getUser_name());
	        model.addAttribute("password", User.getPassword());
	        
	   	 	//datbase query to get complete list of user name and gmail.id in data base
	        List<User> user_list = data.getUserList();
	        int flag = 0;
	        for(User each:user_list) {
	        	System.out.println(each.getUser_name());
	        	System.out.println(model.get("user_name"));
	        	if(each.getUser_name().equals(model.get("user_name")) && each.getPassword().equals(model.get("password"))) {
	        		flag=1;
	        		break;
	        	}
	        }
	        if(flag==1) {
	   	 	System.out.println(model);
	   	 	return new ModelAndView("db");
	        }else {
	        	return new ModelAndView("login_form");
	        }
	    }
	 
	 @RequestMapping("signup_form")
		@ModelAttribute
		public ModelAndView signup_form(Model model) throws MalformedURLException, InterruptedException, ExecutionException{
			//model.addAttribute("Billing_form_model",new Billing_form_model());
			//model.put("monitor", new monitoring_form_details());
			return new ModelAndView ("signup_form","User", new User());
		}
	 
	 
	 @RequestMapping(value = "/signup", method = RequestMethod.POST)
	    public ModelAndView signup(@Valid @ModelAttribute("User")User User, 
	      BindingResult result, ModelMap model) throws InterruptedException, ExecutionException, CloudException, IOException {
		 	model.addAttribute("first_name",User.getFirst_name());
		 	model.addAttribute("last_name",User.getLast_name());
	        model.addAttribute("user_name", User.getUser_name());
	        model.addAttribute("email_id",User.getEmail_id());
	        model.addAttribute("password", User.getPassword());
	        
	        User new_user = new User();
	        String first_name = (String) model.get("first_name");
	        String last_name = (String) model.get("last_name");
	        String user_name = (String) model.get("user_name");
	        String email_id = (String) model.get("email_id");
	        String password = (String) model.get("password");
	        
	        new_user.setFirst_name(first_name);
	        new_user.setLast_name(last_name);
	        new_user.setUser_name(user_name);
	        new_user.setEmail_id(email_id);
	        new_user.setPassword(password);
	        
	        data.insertUser(new_user);
	        System.out.println("SUCCESS");
	        
	   	 	//datbase query to get complete list of user name and gmail.id in data base
	   	 	System.out.println(model);
	   	 	return new ModelAndView("db");
	    }
}


