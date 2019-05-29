package com.development.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController 
{
	@Autowired
	DerbyConfig derbyconfig;
	
	@RequestMapping("/hello")
	public String hello()
	{
		System.out.println(derbyconfig.getDerbyURL());
		return "Hello";
	}
}
