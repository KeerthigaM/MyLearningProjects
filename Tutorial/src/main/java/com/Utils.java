package com;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.util.ResourceUtils;


public class Utils
{
	public static void main(String[] args) throws IOException, ParseException, InterruptedException 
	{
		File file = ResourceUtils.getFile("classpath:chromeDriver/chromedriver.exe");
		File file1 = new File("chromeDriver/chromedriver.exe");
		System.out.println(file.getAbsolutePath());
		System.out.println(file1.getAbsolutePath());
		
		
	}
}