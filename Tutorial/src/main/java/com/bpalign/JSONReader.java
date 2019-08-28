package com.bpalign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONReader
{
	private static final String filepath="D:\\Users\\886758\\Documents\\Keerthiga_886758\\M&S\\BP Files\\JSON files\\";	
	public static void main(String[] args) throws IOException 
	{
		//LocationJsonReader(filepath+"location.json");
		//PriceJsonReader(filepath+"extract20190121-141701.json");
		ProductJsonReader(filepath+"foodproduct-05022019.json");
		System.out.println("COMPLETED");
    }
	
	private static void LocationJsonReader(String filename) {
		JSONParser parser = new JSONParser();		 
        try 
        { 
            Object object= parser.parse(new BufferedReader(new FileReader(filename)));            
            JSONArray jsonArray = (JSONArray) object;             
            for(int i = 0; i < jsonArray.size(); i++)
            {	
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	String locationName = jsonObject.get("locationName").toString();
            	System.out.print(i+" "+locationName);
            	String SapsiteCode = jsonObject.get("sapSiteCode").toString();
            	if(SapsiteCode != null)
            	{   
            		System.out.println(SapsiteCode);
            	}
            }             
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ProductJsonReader(String filename) {
		JSONParser parser = new JSONParser();		 
        try 
        { 
            Object object= parser.parse(new BufferedReader(new FileReader(filename)));            
            JSONArray jsonArray = (JSONArray) object; 
            System.out.println(jsonArray.size());
            for(int i = 0; i < jsonArray.size(); i++)
            {
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	System.out.println(jsonObject.get("productId"));
            	/*JSONObject jsonObject1  = (JSONObject) jsonObject.get("barcodes");
        		System.out.println("UPC : "+jsonObject1.get("UPC"));
        		JSONArray jsonArray1  = (JSONArray) jsonObject.get("productDescriptions");                
        		for(int j = 0; j < jsonArray1.size(); j++)
        		{
        			JSONObject jsonObject2 = (JSONObject) jsonArray1.get(j);
            		System.out.println("Product Description : "+jsonObject2.get("textDesc")); 
        		}
                JSONArray jsonArray2  = (JSONArray) jsonObject.get("version");  
                		for(int j = 0; j < jsonArray2.size(); j++)
                		{
                			JSONObject jsonObject3 = (JSONObject) jsonArray2.get(j);
	                		System.out.println("Drained Weight : "+jsonObject3.get("drainedWeight")+" Unit Of Weight : "+jsonObject3.get("unitsOfWeight")); 
                		}*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}

	private static void PriceJsonReader(String filename) {
		JSONParser parser = new JSONParser();		 
        try 
        { 
            Object object= parser.parse(new BufferedReader(new FileReader(filename)));            
            JSONArray jsonArray = (JSONArray) object;  
            System.out.println(jsonArray.size());
            for(int i = 0; i < jsonArray.size(); i++)
            {
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	System.out.println(jsonObject.get("productId"));
               /* JSONArray jsonArray1  = (JSONArray) jsonObject.get("priceHierarchies");                
            		JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);
            		JSONArray jsonArray2  = (JSONArray) jsonObject1.get("priceByCurrency");
                	JSONObject jsonObject2 = (JSONObject) jsonArray2.get(0);
                	JSONArray jsonArray3  = (JSONArray) jsonObject2.get("priceInfoList");
                		for(int j = 0; j < jsonArray3.size(); j++)
                		{
                			JSONObject jsonObject3 = (JSONObject) jsonArray3.get(j);
	                		System.out.println(
	                				jsonObject3.get("validFrom")
	                		+" || "+jsonObject3.get("validTo")
	                		+" || "+jsonObject3.get("amount")
	                		+" || "+jsonObject3.get("conditionType")
	                				); 
                		}	*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
		
	}
}
