package com.fabrikam.testAzureApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.CloudException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Billing {
	public ArrayList Billing_info() throws InterruptedException, ExecutionException, CloudException, IOException{
		
		  //generate token
			ClientCredential credential = new ClientCredential("54a390ee-fdc5-472c-beea-3bd14369fbf5", "Dri9k5D5mNynBLLHYCJ0pnil3xmaHIk5Y2dNHNWcGZk=");
		    ExecutorService service = Executors.newFixedThreadPool(1);
		    AuthenticationContext context = new AuthenticationContext("https://login.windows.net/"+"ratnavistarait.onmicrosoft.com"+"/oauth2/token",true,service);
		    Future<AuthenticationResult> futureResult = context.acquireToken("https://management.core.windows.net/", credential, null);
		    String access_key = futureResult.get().getAccessToken();
		    System.out.println(access_key);
		    
			
			
		String sub_id="9ee6993f-a036-4118-9eab-c66d9fda1ef3";
		String api_version="2015-06-01-preview";
		JSONObject Billing_info=new JSONObject();
	    try{
			String uurl="https://management.azure.com/subscriptions/"+sub_id+"/providers/Microsoft.Commerce/RateCard?api-version="+api_version+"&$filter=OfferDurableId eq 'MS-AZR-0003p' and Currency eq 'USD' and Locale eq 'en-US' and RegionInfo eq 'US'";
	    	System.out.println(uurl); 
	    	String newurl=uurl.replaceAll(" ","%20");
		    URL url = new URL(newurl);
			HttpURLConnection conn = ((HttpURLConnection) url.openConnection());
	 		 conn.setRequestMethod("GET");
	 		 conn.setRequestProperty("Content-Type", "application/json");
	    	 conn.setRequestProperty("Authorization", "Bearer "+access_key);
	 		 BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	 		 String output;
	 		 int i=0;
	 		 while ((output = br.readLine()) != null) {
	 			System.out.println(output + "\n\n");
	 			Billing_info=(JSONObject) JSONSerializer.toJSON(output);
	 			i++;
	 		 }
	 		 System.out.println(i);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    ArrayList bb=new ArrayList<HashMap>();
	    List meters=(List) Billing_info.get("Meters");
	    
	    for (Object each:meters){
	    	Map x= new HashMap();
	    	JSONObject element=(JSONObject) JSONSerializer.toJSON(each);
	    	String meterId=(String) element.get("MeterId");
	    	JSONObject MeterRates=element.getJSONObject("MeterRates");
	    	x.put("meterId", meterId);
	    	x.put("MeterRates",MeterRates);
	    	bb.add(x);
	    }
	    
	    for(Object i:bb){
	    	HashMap xx=(HashMap) i;
	    	System.out.println(xx.get("MeterRates"));
	    	System.out.println(i);
	    }
	    
	    return bb;
	    }
	
	
//	public static void main(String args[]) throws CloudException, InterruptedException, ExecutionException, IOException{
//		Billing xx = new Billing();
//	//	xx.Billing_info();
//		xx.getcost();
//		
//	}
}
