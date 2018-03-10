package com.fabrikam.testAzureApp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;



import java.io.BufferedReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//import com.microsoft.azure.management.storage.StorageService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.AccessManagement;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.appservice.implementation.AppServiceManager;
import com.microsoft.azure.management.compute.ComputeUsage;
import com.microsoft.azure.management.compute.ComputeUsageUnit;
import com.microsoft.azure.management.compute.ComputeUsages;
import com.microsoft.azure.management.compute.implementation.UsageInner;
import com.microsoft.azure.management.graphrbac.ActiveDirectoryApplications;
import com.microsoft.azure.management.graphrbac.RoleAssignments;
import com.microsoft.azure.management.graphrbac.implementation.ApplicationsInner;
import com.microsoft.azure.management.network.NetworkUsage;
import com.microsoft.azure.management.network.NetworkUsages;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.resources.GenericResources;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.implementation.ResourceManager;
import com.microsoft.azure.management.storage.StorageUsage;
import com.microsoft.azure.management.storage.UsageName;
import com.microsoft.azure.management.storage.Usages;
import com.microsoft.rest.LogLevel;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class Metric_defination {
	public ArrayList metric_def(String sub_id) throws MalformedURLException, InterruptedException, ExecutionException{
		
	 ClientCredential credential = new ClientCredential("54a390ee-fdc5-472c-beea-3bd14369fbf5", "Dri9k5D5mNynBLLHYCJ0pnil3xmaHIk5Y2dNHNWcGZk=");
	 ExecutorService service = Executors.newFixedThreadPool(1);
     AuthenticationContext context = new AuthenticationContext("https://login.windows.net/"+"ratnavistarait.onmicrosoft.com"+"/oauth2/token",true,service);
     Future<AuthenticationResult> futureResult = context.acquireToken("https://management.core.windows.net/", credential, null);
     String access_key = futureResult.get().getAccessToken();
     
     ArrayList met=new ArrayList();
	
     try{
 		URL url=new URL("https://management.azure.com"+ sub_id +"/providers/microsoft.insights/metricDefinitions?api-version=2017-05-01-preview");
	    	 //URL url=new URL("https://management.azure.com"+ entry.id() +"/providers/microsoft.insights/metrics?api-version=2017-05-01-preview"); 
	    	 HttpURLConnection conn = ((HttpURLConnection) url.openConnection());
	    	 conn.setDoOutput(true);
	 		 conn.setRequestMethod("GET");
	 		 conn.setRequestProperty("Content-Type", "application/json");
	    	 conn.setRequestProperty("Authorization", "Bearer "+access_key);
	    	 OutputStream os = conn.getOutputStream();
	 		 BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	 		 
	 		 String output;
	 		 while ((output = br.readLine()) != null) {
			 JSON obj = JSONSerializer.toJSON(output);
			 JsonConverter convert=new JsonConverter();
			 Map<String, String> def_values = convert.call_json_object(output);
			 for(int i=0;i<def_values.size();i++){
				Object id_val = def_values.get("value["+i+"].resourceId");
				if(id_val!=null){
				Object name = def_values.get("value["+i+"].name.value");
				met.add(name);
				}
			 }
	

	}
     }catch(Exception e){
    	 System.out.println("error : "+e);
     }
     return met;
	}
	
}
