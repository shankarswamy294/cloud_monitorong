package com.fabrikam.testAzureApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.microsoft.azure.CloudException;

import net.sf.json.JSONObject;

public class Get_cost {
	public ArrayList get_cost(String start_time,String end_time) throws CloudException, InterruptedException, ExecutionException, IOException{
			
			ArrayList<HashMap> return_list=new ArrayList<HashMap>();
			Billing billing = new Billing();
			Usage usage = new Usage();
			ArrayList bill = billing.Billing_info();
			ArrayList use = usage.UsageInfo(start_time,end_time);
			
			for(Object item:bill){
				HashMap bill01=(HashMap) item;
			for(Object each:use){
				try{
				HashMap use01=(HashMap) each;
				if(bill01.get("meterId").equals(use01.get("meterId"))){
					JSONObject rate=(JSONObject)bill01.get("MeterRates");
				//------------------
					Set<String> keys = rate.keySet();
					List key_list=new ArrayList();
					key_list.addAll(keys);
					System.out.println(key_list);
				//------------------
					
					
					Double quant=((Double) use01.get("quantity"));
					if(key_list.size()==1){
					
					Double cost_each=(Double) rate.get("0");
					System.out.println("cost = "+cost_each+"     quant = "+quant);
					Double cost=quant*cost_each;
					System.out.println(use01.get("meterId")+"  ------  "+ cost);
					use01.put("cost", cost);
					}else{					
						Double total_cost=0.00;
						int i=0;
						Double quant_left=0.00;
						while(i<key_list.size()-1){
							int end= Integer.parseInt((String) key_list.get(i+1));
							int start=Integer.parseInt((String) key_list.get(i));
							Double cost=0.00;
							Double c=(Double) rate.get(Integer.toString(start));
							Double interval=(double) (end-start-1);
							
							if(interval<quant){
								cost=(interval)*c;
								quant_left=quant_left-interval;
							}else{
								cost=(quant)*c;
								quant_left-=quant;
							}
							
							System.out.println("Cost for interval "+ start +" to "+ end +"is "+cost );
							total_cost+=cost;
							
							if(quant_left<0)
								break;
							
							i++;
						}
						System.out.println(use01.get("meterId")+"  ------  "+ total_cost);
						use01.put("cost", total_cost);
					}
					return_list.add(use01);
				}
			
			}catch(Exception e){
				System.out.println("error : " + e);
			}
			}
		}
		
		return return_list;
	}
}
