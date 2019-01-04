package com.springboot.jpa;

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  

@SpringBootApplication  
public class SpringBootJPAApplication 
{  	
	public static void main(String[] args) 
    {  
        SpringApplication.run(SpringBootJPAApplication.class, args);
        System.out.println("Please refer Below URL for reference on how to run the application using POSTMAN");
        System.out.println("https://www.javatpoint.com/spring-boot-jpa");
    }    
}
/*
NOTE:
In this application, default java derby db is used. So no external/USER datasource is attached. 
So data will be deleted every time when you restart/rerun the application. 

HOW TO USE POSTMAN:
1.Open Postman desktop application or please add postman extension in chrome and open it.
2.Enter http://localhost:8081/getAllUser on URL textbox and select GET from dropdown and press send/submit
3.You will get empty array([]) as output
4.Enter http://localhost:8081/add-user on URL textbox and select POST from dropdown
5.click raw button and select JSON in dropdown and enter following JSON input in textarea below and click send/submit.   
{
"id": 1,
"name": "kirthi",
"email": "kirthi@gmail.com"
}
*/

