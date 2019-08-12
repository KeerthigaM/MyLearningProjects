package com;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONReader
{
	public static void main(String[] args) throws IOException 
	{	
		String filepath="D:\\Users\\886758\\Desktop\\T0020001.json";
		File file = ResourceUtils.getFile(filepath);
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(file);
		while (!parser.isClosed()) 
		{			
			JsonToken token = parser.nextToken();// read the next element			
			if (token == null)// if the call to nextToken returns null, the end of the file has been reached
				break;			
			String indent = "";			
			processJSONValue(token, parser, indent);// Process the element
		}
	}
	private static void processJSONObject(JsonParser parser, String indent) throws IOException 
	{		
		indent += " ";
		System.out.println(indent + "{");
		while (!parser.isClosed()) 
		{
			JsonToken token = parser.nextToken();
			if (JsonToken.END_OBJECT.equals(token))// The end of the JSON object has been reached 
			{
				System.out.println(indent + "}");	break;
		    }
			if (!JsonToken.FIELD_NAME.equals(token)) 
			{
				System.out.println("Error. Expected a field name");
				break;
			}
			System.out.print(indent+parser.getCurrentName()+" : ");
			//if(parser.getCurrentName().equals("productId")){token = parser.nextToken();System.out.println(parser.getValueAsString());}
			//else {token = parser.nextToken();}
			processJSONValue(token, parser, indent);
		}
	}
	private static void processJSONArray(JsonParser parser, String indent) throws IOException 
	{		
		indent += " ";
		System.out.println(indent + "[");
		while (!parser.isClosed()) {
			JsonToken token = parser.nextToken();
			if (JsonToken.END_ARRAY.equals(token))// The end of the array has been reached 
			{
				System.out.println(indent + "]");	break;
			}
			processJSONValue(token, parser, indent);
		}
	}
	private static void processJSONValue(JsonToken token, JsonParser parser, String indent) throws IOException 
	{ 
		if (JsonToken.START_OBJECT.equals(token)) {
			processJSONObject(parser, indent);
		} else if (JsonToken.START_ARRAY.equals(token)) {
			processJSONArray(parser, indent);
		} else {
			System.out.println(parser.getValueAsString());
		}		
	}
}
