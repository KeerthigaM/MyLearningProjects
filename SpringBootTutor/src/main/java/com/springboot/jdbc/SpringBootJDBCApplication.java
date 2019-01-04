package com.springboot.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJDBCApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootJDBCApplication.class, args);
		System.out.println("Spring Boot Application Started Successfully!!!! Please launch below URL in browser");
		System.out.println("http://localhost:8081/");
	}
}
