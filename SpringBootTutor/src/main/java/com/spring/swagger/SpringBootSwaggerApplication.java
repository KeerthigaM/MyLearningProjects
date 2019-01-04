package com.spring.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSwaggerApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
		System.out.println("open this in browser: http://localhost:8081/swagger-ui.html");
	}
}