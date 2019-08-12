package com.testing.selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import com.codoid.products.exception.FilloException;

public class GetEmpMailFromID 
{
	public static void main(String[] args) throws InterruptedException, IOException, FilloException
	{
		String filepath="M&S Employees.xlsx";
		ArrayList<String> empID = new ArrayList<String>();
		ArrayList<String> empMailID = new ArrayList<String>();
		empID = CRUDFromExcel.readFromExcel(filepath);
		
		WebDriver driver=LoginEnterprisePerson.getWebDriver();		
		boolean loginStatus=LoginEnterprisePerson.loginToURL(driver,"July@072019");		
		
		if(loginStatus && empID != null){
			for(String id:empID) {				
				empMailID.add(toTxt(driver,id));
			}
		}
		driver.close();
		if(empMailID != null && empID != null)
		{
			for(int i=0;i<=empMailID.size();i++)
			{
				System.out.println(empID.get(i)+" , "+empMailID.get(i));
				//CRUDFromExcel.updateExcel(filepath,empID.get(i),empMailID.get(i));
			}
		}
	System.out.println("DONE");
	}
	private static String toTxt(WebDriver driver, String id) throws FileNotFoundException 
	{	
		File fout = new File("AssociateEmailList.txt");
		FileOutputStream fos = new FileOutputStream(fout);		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		String mail = null;
		try {
			mail = LoginEnterprisePerson.searchwithEmployeeID(driver,id);
			bw.write(mail);
			bw.newLine();
		}
		catch(Exception e ) {}
		finally
		{
			try {
				bw.close();
			} catch (IOException e) {}
		}
		return mail;
	}
		
}