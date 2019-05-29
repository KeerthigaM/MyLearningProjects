package com.rda;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;

public class Security 
{
	public static void main(String[] args) throws InterruptedException, IOException
	{
		WebDriver driver=getWebDriver();
		boolean loginStatus=loginToURL(driver);
		System.out.println(loginStatus);
		if(loginStatus)
		{
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		}
		else
		{
			System.out.println();			
		}
		driver.close();
	}
	
	private static boolean loginToURL(WebDriver driver)
	{
		String url="https://auth.ultimatix.net/utxLogin/login?TYPE=33554432&REALMOID=06-6f590042-dbe7-478f-b9f3-bcfbc15196fc&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=-SM-UcQYV3Q7gqGgCuH1i3hj4UIAEEemC4D39fT%2fFWywxSgIlxgXu2vMiGiIVefpZLJv&TARGET=-SM-HTTPS%3a%2f%2fenterprisesearch%2eultimatix%2enet%2fenterprisesearch%2fredirectToHomepage%3fTARGET%3dhttps-%3a-%2f-%2fenterprisesearch%2eultimatix%2enet-%2fenterprisesearch-%2frest";
	    driver.navigate().to(url);
	    WebElement we = null;
	    we=driver.findElement(By.xpath("//*[@id=\"USER\"]"));
	    we.sendKeys("k130593");
	    we=driver.findElement(By.xpath("//*[@id=\"PASSWORD\"]"));
	    we.sendKeys("February@19");
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