package com.rda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Fillo;

public class JSONtoXLS {
	public static String filloquery = "INSERT INTO T0020002(A,B,C,D,E,F,G,H,I,J,K) VALUES(";
	private static final String filepath="D:/Users/886758/Documents/Keerthiga_886758/M&S/RDA/Mule_data.xlsx";	
	public static void main(String[] args) throws IOException, FilloException, InterruptedException {			
		jsonReader(filepath+"T0020002_Support.json");
		System.out.println("DONE");
	}
	private static void jsonReader(String filename) {
		JSONParser parser = new JSONParser();		 
        try 
        { 
            Object object= parser.parse(new BufferedReader(new FileReader(filename)));
            JSONArray jsonArray = (JSONArray) object;
            ArrayList<String> jsonTag = new ArrayList<String>();
            for(int i = 0; i < jsonArray.size(); i++)
            {	
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	if(i == 0)
            	{
            		jsonTag = getJSONTagNames(jsonObject);
            	}
            	String data ="'";
            	for (String tagName : jsonTag)
            	{
            		String val = jsonObject.get(tagName).toString().replace("'", "-");
            		data = data + val + "','";
            	}
            	data = data.substring(0, data.length()-2)+ ")";
            	//System.out.println(data);
            	importToExcel(filloquery+data);
            }             
        } catch (Exception e) {
            e.printStackTrace();
        }
	}	
	private static ArrayList<String> getJSONTagNames(JSONObject jsonObject) throws FilloException {
		Set<String> keys= jsonObject.keySet();
		Iterator value = keys.iterator(); 
		ArrayList<String> jsonTag = new ArrayList<String>();
		String data = "'";
		while (value.hasNext()) { 
			String val = value.next().toString();
			jsonTag.add(val);
			data = data + val + "','";			
        } 
		data = data.substring(0, data.length()-2)+ ")";
		//System.out.println(data);
		importToExcel(filloquery+data);
		return jsonTag;
	}	
	private static void importToExcel(String query) throws FilloException {
		Fillo fillo=new Fillo();
		com.codoid.products.fillo.Connection connection=fillo.getConnection(filepath);
		connection.executeUpdate(query);
		connection.close();	
	}
}
