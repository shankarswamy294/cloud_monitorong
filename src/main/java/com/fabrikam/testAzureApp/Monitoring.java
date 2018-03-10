package com.fabrikam.testAzureApp;

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
//import com.microsoft.applicationinsights.TelemetryClient;
public class Monitoring {

private Object URL;

public ArrayList details(){
	
	
	//JSONArray jsonArray = new JSONArray();
	ArrayList<Info> jsonArray= new ArrayList<Info>();
	Info info=new Info();
	try {    
	    final File credFile = new File("H:\\opsRamp\\workspace01\\testAzureApp\\src\\main\\resources\\azureauth.properties");
	    String str = FileUtils.readFileToString(credFile);
	    System.out.println(str);
	    Azure azure = Azure.configure()
	            .withLogLevel(LogLevel.BASIC)
	            .authenticate(credFile)
	            .withDefaultSubscription();
	    
	   azure.getCurrentSubscription();
	   
	   
	    ClientCredential credential = new ClientCredential("54a390ee-fdc5-472c-beea-3bd14369fbf5", "Dri9k5D5mNynBLLHYCJ0pnil3xmaHIk5Y2dNHNWcGZk=");
	    ExecutorService service = Executors.newFixedThreadPool(1);
        AuthenticationContext context = new AuthenticationContext("https://login.windows.net/"+"ratnavistarait.onmicrosoft.com"+"/oauth2/token",true,service);
        Future<AuthenticationResult> futureResult = context.acquireToken("https://management.core.windows.net/", credential, null);
        String access_key = futureResult.get().getAccessToken();
	  
	    System.out.println(access_key);
	    
	    
	   // URL url=new URL("https://management.azure.com/subscriptions/54a390ee-fdc5-472c-beea-3bd14369fbf5/resourceGroups/test-group/providers/Microsoft.ClassicCompute/virtualMachines/democlassicvm/providers/microsoft.insights/metricdefinitions?api-version=2015-07-01"); 
	    //https://management.azure.com/subscriptions/<subscription_id>/resourceGroups/<resourceGroups/>/providers/Microsoft.ClassicCompute/virtualMachines/<Virutal name>/providers/microsoft.insights/metricdefinitions?api-version=2015-07-01
	    //https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/resourceGroups/test-group/providers/Microsoft.ClassicCompute/virtualMachines/TestLinuxVM/providers/microsoft.insights/metricdefinitions?api-version=2015-07-01
	   // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    //System.out.println(conn.setRequestMethod("GET"));
	    //InputStream is = conn.getInputStream();
	   // https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/resourceGroups/test-group/providers/microsoft.classiccompute/virtualMachines/{vm-id}/providers/microsoft.insights/metricdefinitions?api-version=2015-07-01

	    
	    //https://login.microsoftonline.com/{tenant-id}/OAuth2/Token
	  //  	https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/resourceGroups/test-group/providers/Microsoft.Compute/virtualMachines/TestLinuxVM/providers/microsoft.insights/metricdefinitions?api-version=2015-07-01/?code="+acc	    	
	   //https://management.azure.com"+ entry.id() +"/providers/microsoft.insights/metrics?timespan=2018-02-4T02:20:00Z/2018-02-6T04:20:00Z&interval=PT1M&metric=Availability,UsedCapacity&aggregation=Average&api-version=2017-05-01-preview		 
	   		
	   		
	   		
	    	
	   GenericResources Gresources=azure.genericResources();
	    for (GenericResource entry : Gresources.list()) {
	    
	    	try{
	    		URL url=new URL("https://management.azure.com"+entry.id()+"/providers/microsoft.insights/metricDefinitions?api-version=2017-05-01-preview");
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
	 				Object aggregate=def_values.get("value["+i+"].primaryAggregationType");
	 				String start_time="2018-01-15T02:20:00Z";
	 				String end_time="2018-02-8T04:20:00Z";
	 				System.out.println("====="+aggregate);
	 				System.out.println("Metric-Name "+name);
	 				URL url01=new URL("https://management.azure.com"+ id_val +"/providers/microsoft.insights/metrics?metric="+name+"&interval=PT5M&aggregation="+aggregate+"&api-version=2017-05-01-preview");
	 				HttpURLConnection conn01 = ((HttpURLConnection) url01.openConnection());
			    	 conn01.setDoOutput(true);
			 		 conn01.setRequestMethod("GET");
			 		 conn01.setRequestProperty("Content-Type", "application/json");
			    	 conn01.setRequestProperty("Authorization", "Bearer "+access_key);
	 				 BufferedReader br01 = new BufferedReader(new InputStreamReader((conn01.getInputStream())));
	 				System.out.println("type "+entry.type());
	 				 System.out.println("Metric-Name "+name);
	 				 String output01;
			 		 while ((output01 = br01.readLine()) != null) {
			 			 System.out.println(output01);
			 			System.out.println("\n\n");
			 		 }
			 		conn01.disconnect();
	 				}
	 			 }
	 		}
	 		conn.disconnect();
	 	  } catch (Exception e) {
	 		e.printStackTrace();
	 	  }

	    	
	    	/*info=new Info();
	    	System.out.println(entry.type());
	    	String r_name=entry.type();
	    	System.out.println();
	    	String[] parts = r_name.split("/");
	    	JSONObject final_obj=new JSONObject();
	    	JSONObject obj = (JSONObject) JSONSerializer.toJSON(Gresources.getById(entry.id()).properties());	    
	    	GenericResource duo = Gresources.getById(entry.id());
	    	info.setJsonElement(obj.toString());
	    	info.setName(entry.name());
	    	info.setType(parts[1]);
	    	jsonArray.add(info);*/
	    }
	    
	   
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	 return jsonArray;
}

public static void main(String args[]){
	Monitoring x=new Monitoring();
	x.details();
}
}

























//usage aggregate
//working url
//https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/providers/Microsoft.Commerce/UsageAggregates?api-version=2015-06-01-preview&reportedStartTime=2018-01-15T09%3A00%3A00&reportedEndTime=2018-02-01T09%3A00%3A00&aggregationGranularity=Hourly
//https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/providers/Microsoft.Commerce/UsageAggregates?api-version=2015-06-01-preview&reportedStartTime=2018-01-15T00:00:00Z&reportedEndTime=2018-02-01T00:00:00Z&aggregationGranularity=Daily


//https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/providers/Microsoft.Commerce/UsageAggregates?api-version=2015-06-01-preview&reportedStartTime=2018-01-10T00:00:00Z&reportedEndTime=2018-02-10T00:00:00Z&aggregationGranularity=Daily
