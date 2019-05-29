package com.rda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Workflow
{  
    String creationDate;  
    
    public Workflow(String creationDate) 
    {  
        this.creationDate = creationDate;
    }  
    public String toString() {  
        return this.creationDate; 
    }  
} 
public class PendingWorkflow 
{	
	static String muleDatePattern = "yyyy/MM/dd";
	private static final DateTimeFormatter mulePatternFormat = DateTimeFormat.forPattern(muleDatePattern);
	
	public static void main(String[] args) 
	{
		pending();		
	}
	private static void pending() {
		List<Workflow> baseList = new ArrayList<Workflow>();
		baseList.add(new Workflow("2019/02/10"));
		baseList.add(new Workflow("2019/01/10"));
		baseList.add(new Workflow("2019/02/11"));
		baseList.add(new Workflow("2019/02/12"));
		baseList.add(new Workflow("2019/02/15"));
		baseList.add(new Workflow("2019/02/14"));
		baseList.add(new Workflow("2019/02/18"));
		baseList.add(new Workflow("2019/02/16"));
		baseList.add(new Workflow("2019/02/19"));
		baseList.add(new Workflow("2019/02/17"));
		baseList.add(new Workflow("2019/02/13"));
		System.out.println("Before "+baseList);		
		baseList=baseList.stream()
				.filter(w -> checkDateEarlierThanXDays(w.creationDate, 0))
				.sorted((w1, w2) -> w1.creationDate.compareTo(w2.creationDate))				
				.collect(Collectors.toList());
		System.out.println("After "+baseList);			
	}

	private static boolean checkDateEarlierThanXDays(String creationDate, int Xdays) {
		return LocalDate.parse(creationDate, mulePatternFormat).isBefore(LocalDate.now().minusDays(Xdays));				
	}
}
