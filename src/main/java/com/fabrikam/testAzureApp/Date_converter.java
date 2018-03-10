package com.fabrikam.testAzureApp;

public class Date_converter {
	public String date_converter(String start){
        String final_start;
        //start date and time
        String[] part = start.split("/");
        String[] part01=part[2].split(" ");
        System.out.println(part[0]);
        if(part01[2]=="PM"){
        	String[] part02 = part01[1].split(":");
        	String hr=String.valueOf(Integer.parseInt(part02[0])+12);
        	
        	final_start=part01[0]+"-"+part[0]+"-"+part[1]+"T"+hr+":"+part02[1]+":00Z";
        	System.out.println(final_start);
        	
        }else{
        	final_start=part01[0]+"-"+part[0]+"-"+part[1]+"T"+part01[1]+":00Z";
        	System.out.println(final_start);
        	
        }
       return final_start;
	}
}
