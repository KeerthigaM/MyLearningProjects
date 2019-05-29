package com;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.util.ResourceUtils;


public class Test1
{
	public static void main(String[] args) throws IOException, ParseException, InterruptedException 
	{
		File file1 = new File("chromeDriver/chromedriver.exe");
		String absolutePath = file1.getAbsolutePath();
		System.out.println(absolutePath);
		
		File file = ResourceUtils.getFile("classpath:chromeDriver\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		System.out.println(file.getAbsolutePath());
	}
}