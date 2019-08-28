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

public class Azure 
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
	public static boolean loginToURL(WebDriver driver, String pass)
	{	
		String url="https://portal.azure.com/#@mnscorp.onmicrosoft.com/resource/subscriptions/34174bf8-c044-4cab-874d-267cd3152ae0/resourceGroups/fod-eun-nprod-rda-rg-0001/providers/Microsoft.Web/sites/fodendvlasrda01/networking";
	    driver.navigate().to(url);
	    WebElement we = null;
	   /* we=driver.findElement(By.xpath("//*[@id="i0116"]"));
	    we.sendKeys("keerthiga.margesan@mnscorp.net");
	    we.sendKeys(Keys.ENTER);
	    we=driver.findElement(By.xpath("//*[@id="i0118"]"));
	    we.sendKeys(pass);
	    we.sendKeys(Keys.ENTER);
	    we=driver.findElement(By.xpath("//*[@id="idBtn_Back"]"));
	    we.sendKeys(Keys.ENTER);
	    WebDriverWait wait = new WebDriverWait(driver, 50);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"allTab2\"]")));
	    we=driver.findElement(By.xpath("//*[@id=\"allTab2\"]"));
	    we.click();*/
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