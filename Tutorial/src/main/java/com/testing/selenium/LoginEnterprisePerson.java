package com.testing.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.ResourceUtils;

public class LoginEnterprisePerson 
{
	public static void main(String[] args) throws InterruptedException, IOException
	{
		WebDriver driver=getWebDriver();		
		boolean loginStatus=loginToURL(driver,"July@072019");		
		if(loginStatus)
		{
			System.out.println(loginStatus);
		}
		driver.close();
	}
	
	public static String searchwithEmployeeID(WebDriver driver, String empID) throws InterruptedException 
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
	
	public static boolean loginToURL(WebDriver driver, String pass)
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
	    we.sendKeys(pass);
	    we.sendKeys(Keys.ENTER);	    
	    WebDriverWait wait = new WebDriverWait(driver, 50);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"allTab2\"]")));
	    we=driver.findElement(By.xpath("//*[@id=\"allTab2\"]"));
	    we.click();
	    return we.isDisplayed();
	}
	public static WebDriver getWebDriver() throws FileNotFoundException
	{
		File file = ResourceUtils.getFile("classpath:chromeDriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());	 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--log-level=3");	
		options.addArguments("--silent");	
	    WebDriver driver=new ChromeDriver(options);    
		return driver;
	}
}