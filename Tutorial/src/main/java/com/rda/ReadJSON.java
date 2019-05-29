package com.rda;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mns.domain.Category;
import com.mns.domain.Workflow;

public class ReadJSON {	
		
	public static void main(String[] args) {
		//readCategoryJson("FOOD");
		//readWorkflowJson("F01,F02,F03");
		//test();

	}
	private static void test() 
	{
		//List<Workflow> modifiedWorkflowList = workflowList.stream().map(u->populateWorkflowWithUserName(u, usersForCategory)).collect(Collectors.toList());
		
	}
	public static List<Category> readCategoryJson(final String dept) {
		List<Category> categoriesList = new ArrayList<Category>();
		final JSONParser parser = new JSONParser();
		try {	
			Resource resource = new ClassPathResource("json/categories.json");
			final InputStream is = resource.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);			
			final Object obj = parser.parse(new BufferedReader(isr));			
			final JSONObject jsonObject = (JSONObject) obj;			
			final Object o = jsonObject.get(dept);
			final ObjectMapper mapper = new ObjectMapper();		
			categoriesList = mapper.readValue(o.toString(), mapper.getTypeFactory().constructCollectionType(List.class, Category.class));
			System.out.println(categoriesList.size());
		} catch (final Exception e) {
			System.out.print("Exception: {}"+e.getMessage());		
		}
		return categoriesList;
	}
	
	public static List<Workflow> readWorkflowJson(final String id) {
		List<Workflow> workflowList = new ArrayList<Workflow>();
		final JSONParser parser = new JSONParser();
		try {
			Resource resource = new ClassPathResource("json/workflow.json");
			final InputStream is = resource.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final Object obj = parser.parse(new BufferedReader(isr));
			final JSONObject jsonObject = (JSONObject) obj;
			final Object o = jsonObject.get(id);
			final ObjectMapper mapper = new ObjectMapper();
			workflowList = mapper.readValue(o.toString(), mapper.getTypeFactory().constructCollectionType(List.class, Workflow.class));
			System.out.println(workflowList.size());			
		} catch (final Exception e) {
			System.out.print("Exception: {}"+e.getMessage());
		}
		return workflowList;
	}
	
	public static List<Workflow> readWorkflowJson1(final String workflow) {
		List<Workflow> workflowList = new ArrayList<Workflow>();
		final JSONParser parser = new JSONParser();
		try {
			Resource resource = new ClassPathResource("json/PendingWorkflow.json");
			final InputStream is = resource.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final Object obj = parser.parse(new BufferedReader(isr));
			final JSONObject jsonObject = (JSONObject) obj;
			final Object o = jsonObject.get(workflow);
			final ObjectMapper mapper = new ObjectMapper();
			workflowList = mapper.readValue(o.toString(), mapper.getTypeFactory().constructCollectionType(List.class, Workflow.class));
			System.out.println(workflowList.size());			
		} catch (final Exception e) {
			System.out.print("Exception: {}"+e.getMessage());
		}
		return workflowList;
	}
}
