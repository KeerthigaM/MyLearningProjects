package dependencyInjectionBySetters;

import java.util.Map.Entry;
import dependencyInjectionBySetters.Projects;

import java.util.*;

public class Employee 
{
	private int id;
	private String name;
	private Address address;
	private List<String> skillSet;
	private List<Projects> projectsWorked;
	private Map<Projects,String> projectTechnology;
			
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<String> getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(List<String> skillSet) {
		this.skillSet = skillSet;
	}
	
	public List<Projects> getProjectsWorked() {
		return projectsWorked;
	}
	public void setProjectsWorked(List<Projects> projectsWorked) {
		this.projectsWorked = projectsWorked;
	}
	
	public Map<Projects, String> getProjectTechnology() {
		return projectTechnology;
	}
	public void setProjectTechnology(Map<Projects, String> projectTechnology) {
		this.projectTechnology = projectTechnology;
	}
	
	void show() {
		System.out.println("ID: " + id + ", Name: " + name);
		System.out.println("Address Details : "+ address);
		
		System.out.println("SkillSet : ");
		Iterator<String> it=skillSet.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		System.out.println("Project Worked");
		Iterator<Projects> itp=projectsWorked.iterator();
		while(itp.hasNext())
		{
			System.out.println(itp.next());
		}
		
		System.out.println("Project Technology");
		Set<Entry<Projects, String>> set=projectTechnology.entrySet();  
	    Iterator<Entry<Projects, String>> itr=set.iterator();  
	    while(itr.hasNext()){  
	        Entry<Projects,String> entry=itr.next();  
	        System.out.println("Project Details : "+entry.getKey()+", Technology Used:"+entry.getValue());  
	    }
	}
}
