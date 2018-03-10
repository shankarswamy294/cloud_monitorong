package com.fabrikam.testAzureApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.resources.GenericResources;
import com.microsoft.rest.LogLevel;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Usage {

	
	public ArrayList UsageInfo(String StartTime, String EndTime) throws InterruptedException, ExecutionException, CloudException, IOException{
	
		
		final File credFile = new File("H:\\opsRamp\\workspace01\\testAzureApp\\src\\main\\resources\\azureauth.properties");
	    String str = FileUtils.readFileToString(credFile);
	    System.out.println(str);
	    Azure azure = Azure.configure()
	            .withLogLevel(LogLevel.BASIC)
	            .authenticate(credFile)
	            .withDefaultSubscription();
		
	    //generate token
		ClientCredential credential = new ClientCredential("54a390ee-fdc5-472c-beea-3bd14369fbf5", "Dri9k5D5mNynBLLHYCJ0pnil3xmaHIk5Y2dNHNWcGZk=");
	    ExecutorService service = Executors.newFixedThreadPool(1);
	    AuthenticationContext context = new AuthenticationContext("https://login.windows.net/"+"ratnavistarait.onmicrosoft.com"+"/oauth2/token",true,service);
	    Future<AuthenticationResult> futureResult = context.acquireToken("https://management.core.windows.net/", credential, null);
	    String access_key = futureResult.get().getAccessToken();
	    System.out.println(access_key);
	    
		
		String sub_id="9ee6993f-a036-4118-9eab-c66d9fda1ef3";
		String api_version="2015-06-01-preview";
		StartTime="2018-02-10T00:00:00Z";
		EndTime="2018-02-11T00:00:00Z";
		String aggregation="Daily"; //or Hourly
	
	
	JSONObject usage_info=new JSONObject();
	
	
    try{
		URL url=new URL("https://management.azure.com/subscriptions/"+sub_id+"/providers/Microsoft.Commerce/UsageAggregates?api-version="+api_version+"&reportedStartTime="+StartTime+"&reportedEndTime="+EndTime+"&aggregationGranularity="+aggregation);
    	System.out.println(url); 
		HttpURLConnection conn = ((HttpURLConnection) url.openConnection());
    	 conn.setInstanceFollowRedirects(false); 
 		 conn.setRequestMethod("GET");
 		 conn.setRequestProperty("Content-Type", "application/json");
    	 conn.setRequestProperty("Authorization", "Bearer "+access_key);
    	 conn.setRequestProperty("charset", "utf-8");
 		 BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
 		 String output;
 		 int i=0;
 		 while ((output = br.readLine()) != null) {
 			 i+=1;
 			System.out.println(output + "\n\n");
 			usage_info=(JSONObject) JSONSerializer.toJSON(output);
 		 }
 		 System.out.println(i);
    }catch(Exception e){
    	e.printStackTrace();
    }
    List value= (List) usage_info.get("value");
    ArrayList usage_id_quantity=new ArrayList<HashMap>();
    
    for(Object each:value){
    	Map id_quantity=new HashMap();
    	JSONObject element=(JSONObject) JSONSerializer.toJSON(each);
	    JSONObject properties=(JSONObject) element.get("properties");
	    String meterId= (String) properties.get("meterId");
	    Double quantity = (Double) properties.get("quantity");
	    String startTime=properties.getString("usageStartTime");
	    String endTime=properties.getString("usageEndTime");
	    String category=properties.getString("meterCategory");
	    JSONObject instanceData=properties.getJSONObject("instanceData");
	    JSONObject infoFields=properties.getJSONObject("infoFields");
	    String location;
	    if(instanceData.size()>0){
	    	JSONObject Resources=instanceData.getJSONObject("Microsoft.Resources");
	    	location=(String) Resources.get("location");
	    	String name=(String) Resources.get("resourceUri");
	    	String[] resourceUri_list = name.split("/");
	    	String Resource_name = resourceUri_list[4];
	    	id_quantity.put("Resource_name", Resource_name);
	    }else{
	    	location=(String) infoFields.get("meteredRegion");
	    	String Resource_name = (String) infoFields.get("project");
	    	id_quantity.put("Resource_name", Resource_name);
	    }
	    id_quantity.put("meterId", meterId);
	    id_quantity.put("quantity", quantity);
	    id_quantity.put("startTime", startTime);
	    id_quantity.put("endTime", endTime);
	    id_quantity.put("meterCategory", category);
	    id_quantity.put("location", location);
	    usage_id_quantity.add(id_quantity);
    }
    
    for(Object i:usage_id_quantity){
    	System.out.println(i);
    }
    return usage_id_quantity;
	}
	
	
	/*public static void main(String args[]) throws CloudException, InterruptedException, ExecutionException, IOException{
		Usage xx = new Usage();
		xx.UsageInfo();
	}*/
}
