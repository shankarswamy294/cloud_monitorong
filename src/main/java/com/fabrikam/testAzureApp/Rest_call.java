package com.fabrikam.testAzureApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Rest_call {
	public BufferedReader get_rest_call(URL url,String access_key) throws IOException{
			
			HttpURLConnection conn = ((HttpURLConnection) url.openConnection());
   	 		conn.setDoOutput(true);
   	 		conn.setRequestMethod("POST");
   	 		conn.setRequestProperty("Content-Type", "application/json");
   	 		conn.setRequestProperty("Authorization", "Bearer "+access_key);
   	 		OutputStream os = conn.getOutputStream();
   	 		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
   	 		//conn.disconnect();
   	 		return br;
   	 		
			
	}
}
