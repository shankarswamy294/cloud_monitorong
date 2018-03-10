

package com.fabrikam.testAzureApp;
import java.io.File;
//import com.microsoft.azure.management.storage.StorageService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.resources.GenericResources;
import com.microsoft.azure.management.storage.StorageUsage;
import com.microsoft.azure.management.storage.Usages;
import com.microsoft.rest.LogLevel;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class App {

public ArrayList details(){
	
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
	    
	    String[] x = credFile.list();
	    System.out.println(x);
	    Usages usage = azure.storageUsages();
	    PagedList<StorageUsage> xx = usage.list();
	    System.out.println(xx.size());
	    System.out.println((xx.get(0)).currentValue());
	    System.out.println((xx.get(0)).limit());
	    System.out.println((xx.get(0)).name());
	    
	    
	    for(Object ele:xx){
	    	System.out.println(ele);
	    }
	    
	    //working
	    //https://management.azure.com/subscriptions/9ee6993f-a036-4118-9eab-c66d9fda1ef3/resourceGroups/test-group/providers/Microsoft.ClassicCompute/virtualMachines/democlassicvm/providers/microsoft.insights/metrics?api-version=2017-05-01-preview
	    
	    GenericResources Gresources=azure.genericResources();
	   
	    for (GenericResource entry : Gresources.list()) {
	    	
	    	info=new Info();
	    	System.out.println(entry.type());
	    	String r_name=entry.type();
	    //	result.putAll((Map) entry);
	    	System.out.println();
	    	String[] parts = r_name.split("/");
	    	System.out.println(entry.id()+"  "+entry.resourceGroupName());
	    	JSONObject final_obj=new JSONObject();
	    	JSONObject obj = (JSONObject) JSONSerializer.toJSON(Gresources.getById(entry.id()).properties());
	    	info.setJsonElement(obj.toString());
	    	info.setName(entry.name());
	    	info.setType(parts[1]);
	    	info.setSub_id(entry.id());
	    	jsonArray.add(info);
	    }
	    
	   
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	 return jsonArray;
}

public static void main(String args[]){
	App x=new App();
	x.details();
}
}
