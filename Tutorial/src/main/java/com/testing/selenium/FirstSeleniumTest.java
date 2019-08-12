package com.testing.selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.ResourceUtils;

public class FirstSeleniumTest 
{
	public static void main(String[] args) throws InterruptedException, IOException
	{
		WebDriver driver=getWebDriver();
		boolean loginStatus=loginToURL(driver);
		System.out.println(loginStatus);
		if(loginStatus)
		{
			String AssociateEmpID="181771;";
			ArrayList<String> AssociateEmpIDList=getEmpIds(AssociateEmpID);
			importToTxt(driver,AssociateEmpIDList,"AssociateEmailList.txt");
			System.out.println("******************************************");
			/*String SupervisorEmpID="396704;1464814";
			ArrayList<String> SupervisorEmpIDList=getEmpIds(SupervisorEmpID);	
			SupervisorEmpIDList=rmvDupFromSupID(SupervisorEmpIDList,AssociateEmpIDList);
			importToTxt(driver,SupervisorEmpIDList,"SupervisorEmailList.txt");*/
		}
		driver.close();
	}	
	private static ArrayList<String> rmvDupFromSupID(ArrayList<String> SupervisorIDsList, ArrayList<String> associateIDsList)
	{		
		ArrayList<String> FinalSupervisorIDsList = new ArrayList<String>();
		for (String id : SupervisorIDsList) {
			if (!associateIDsList.contains(id)) {
				FinalSupervisorIDsList.add(id);
				System.out.println(id);
			}
		}
		return FinalSupervisorIDsList;
	}
	private static void importToTxt(WebDriver driver, ArrayList<String> empIDList, String filename) throws InterruptedException, IOException 
	{
		File fout = new File(filename);
		FileOutputStream fos = new FileOutputStream(fout);		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for(String empID:empIDList)
		{
			String email=searchwithEmployeeID(driver,empID);
			bw.write(email);
			bw.newLine();
		}
		bw.close();		
	}
	private static ArrayList<String> getEmpIds(String empIDs) 
	{		
		String[] empIDArr=empIDs.split(";");
		ArrayList<String> empIDList1 = new ArrayList<String>(Arrays.asList(empIDArr));	
		ArrayList<String> empIDList = new ArrayList<String>();
		for (String id : empIDList1) {
			if (!empIDList.contains(id)) {
				System.out.println(id);
				empIDList.add(id);
			}
		}
		return empIDList;
	}
	private static String searchwithEmployeeID(WebDriver driver, String empID) throws InterruptedException 
	{
		WebElement we = null;
		we=driver.findElement(By.xpath("//*[@id=\"search\"]"));
		we.clear();
		we.sendKeys(empID);
		we.sendKeys(Keys.ENTER);
		/*we=driver.findElement(By.xpath("//*[@id=\"searchLink\"]/img"));
		we.click();	*/	
		Thread.sleep(10000);
		String searchResult="//*[@id=\"resultsTable\"]/table/tbody/tr[4]/td/table[2]/tbody/tr/td/div/div/div/table/tbody/tr[1]/td/div/table/tbody/tr/td[2]/div/table[1]/tbody/tr[3]/td/div[2]";
		WebDriverWait wait = new WebDriverWait(driver, 50);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResult)));
		we=driver.findElement(By.xpath(searchResult+"/span"));		
		return we.getText();
	}
	private static boolean loginToURL(WebDriver driver)
	{
		String url="https://auth.ultimatix.net/utxLogin/login?TYPE=33554432&REALMOID=06-6f590042-dbe7-478f-b9f3-bcfbc15196fc&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=-SM-UcQYV3Q7gqGgCuH1i3hj4UIAEEemC4D39fT%2fFWywxSgIlxgXu2vMiGiIVefpZLJv&TARGET=-SM-HTTPS%3a%2f%2fenterprisesearch%2eultimatix%2enet%2fenterprisesearch%2fredirectToHomepage%3fTARGET%3dhttps-%3a-%2f-%2fenterprisesearch%2eultimatix%2enet-%2fenterprisesearch-%2frest";
	    driver.navigate().to(url);
	    WebElement we = null;
	    we=driver.findElement(By.xpath("//*[@id=\"form1\"]"));
	    we.sendKeys("k130593");
	    we.sendKeys(Keys.ENTER);	
	    we=driver.findElement(By.xpath("//*[@id=\"password-btn\"]"));
	    we.click();
	    we=driver.findElement(By.xpath("//*[@id=\"password-login\"]"));
	    we.sendKeys("July@072019");
	    we.sendKeys(Keys.ENTER);	    
	    WebDriverWait wait = new WebDriverWait(driver, 50);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ng-app\"]/body/div[12]/div[3]/div[2]/span[1]/img")));
	    we=driver.findElement(By.xpath("//*[@id=\"allTab2\"]"));
	    we.click();
	    return we.isDisplayed();
	}
	private static WebDriver getWebDriver() throws FileNotFoundException
	{
		File file = ResourceUtils.getFile("classpath:chromeDriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());	 
	    WebDriver driver=new ChromeDriver();  
		return driver;
	}
}