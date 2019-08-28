package com;

public class Test {

	public static void main(String[] args) {
	int j=6;
	String append="'role_id','role_code','role_name','banner','id','name','";
	append=append.substring(0, append.length()-3);
	if(j<=6){
		for(int i=j;i<=6;i++) {
			if(i!=6)
			append=append.concat("','");
			else
			append=append.concat("')");		
		}
	}
	System.out.println(append);
	
	}

	
}