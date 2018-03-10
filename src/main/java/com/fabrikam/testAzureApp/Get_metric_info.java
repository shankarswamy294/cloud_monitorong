package com.fabrikam.testAzureApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.ui.ModelMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;

public class Get_metric_info {
	public ArrayList metric_info(ModelMap model) throws MalformedURLException, InterruptedException, ExecutionException, UnsupportedEncodingException{
		String start=(String) model.get("startDateTime");
		String end=(String) model.get("endDateTime");
		String metric_type=(String) model.get("metricType");
		String id=(String) model.get("sub_id");
		String AggregationType=(String) model.get("AggregationType");
		String interval=(String) model.get("interval");
		 ClientCredential credential = new ClientCredential("54a390ee-fdc5-472c-beea-3bd14369fbf5", "Dri9k5D5mNynBLLHYCJ0pnil3xmaHIk5Y2dNHNWcGZk=");
		 ExecutorService service = Executors.newFixedThreadPool(1);
	     AuthenticationContext context = new AuthenticationContext("https://login.windows.net/"+"ratnavistarait.onmicrosoft.com"+"/oauth2/token",true,service);
	     Future<AuthenticationResult> futureResult = context.acquireToken("https://management.core.windows.net/", credential, null);
	     String access_key = futureResult.get().getAccessToken();
		
	     ArrayList met_info=new ArrayList();
	     
	     String uurl="https://management.azure.com"+ id +"/providers/microsoft.insights/metrics?metric="+metric_type+"&interval="+interval+"&timespan="+start+"/"+end+"&aggregation="+AggregationType+"&api-version=2017-05-01-preview";
	     String newurl=uurl.replaceAll(" ","%20");
	     URL url = new URL(newurl);
	     
	     try{
	    	 URL url01=new URL(newurl);
				HttpURLConnection conn01 = ((HttpURLConnection) url01.openConnection());
		    	 conn01.setDoOutput(true);
		 		 conn01.setRequestMethod("GET");
		 		 conn01.setRequestProperty("Content-Type", "application/json");
		    	 conn01.setRequestProperty("Authorization", "Bearer "+access_key);
		    	 conn01.connect();
		    	 
		    	 //BufferedReader br01 = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(uurl)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
				 BufferedReader br01 = new BufferedReader(new InputStreamReader((conn01.getInputStream())));
				 String output01;
		 		 while ((output01 = br01.readLine()) != null) {
		 			 
		 			 JSONObject xx = (JSONObject)JSONSerializer.toJSON(output01);
		 			 
		 			Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 			JsonParser jp = new JsonParser();
		 			JsonElement je = jp.parse(output01);
		 			String prettyJsonString = gson.toJson(je);
		 			// System.out.println(prettyJsonString.valueOf("data"));
		 			met_info.add(prettyJsonString); 
		 			
		 			 System.out.println(output01);
		 			 System.out.println("\n\n");
		 		 }
		 		conn01.disconnect();
				}catch(Exception e){
					e.printStackTrace();
	     }
		
		return met_info;
	}
}
