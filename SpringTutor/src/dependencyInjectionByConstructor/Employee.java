package dependencyInjectionByConstructor;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.*;

public class Employee 
{
	private int id;
	private String name;
	private Address address;
	private List<String> skillSet;
	private List<Projects> projectsWorked;
	private Map<Projects,String> projectTechnology;

	public Employee(){
	}
	public Employee(int id) {
		this.id = id;
	}
	public Employee(String name) {
		this.name = name;
	}
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Employee(int id, String name, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;	
	}
	public Employee(int id, String name, Address address,List<String> skillSet) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.skillSet=skillSet;
	}
	public Employee(int id, String name, Address address,List<String> skillSet,List<Projects> projectsWorked) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.skillSet=skillSet;
		this.projectsWorked=projectsWorked;
	}
	public Employee(int id, String name, Address address,List<String> skillSet,List<Projects> projectsWorked,Map<Projects, String> projectTechnology) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.skillSet=skillSet;
		this.projectsWorked=projectsWorked;
		this.projectTechnology=projectTechnology;
	}
	void show() {
		System.out.println("ID: " + id + ", Name: " + name);
		
		System.out.println(address.toString());
		
		System.out.println("SkillSets: ");		
		Iterator<String> it=skillSet.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		System.out.println("ProjectsWorked: ");
		Iterator<Projects> itp=projectsWorked.iterator();
		while(itp.hasNext()){
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
