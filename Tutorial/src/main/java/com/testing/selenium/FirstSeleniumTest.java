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
			String AssociateEmpID="393041;378766;366291;836134;832904;833190;829228;584380;583989;346139;553943;533297;560297;549500;336032;325057;1019784;679623;680556;690935;917360;668523;844051;843559;557949;548637;546948;549468;554124;557815;550568;543932;517243;515644;551332;543909;510375;553498;555560;520463;558279;840452;393056;591647;473275;475988;341139;555957;886242;536946;584792;924061;592409;585861;588460;987520;1011480;471429;669666;598878;598943;865280;877868;893454;886332;886341;598748;719582;555518;521409;494659;883845;732435;886169;547236;665897;551808;552414;557509;922780;586588;591753;536926;559769;886758;536934;597826;866143;662442;332955;588991;596141;670860;730559;390405;671896;596703;596743;550463;587525;522478;514734;576878;590276;584528;582666;582712;593490;582772;392442;599668;662584;590753;736255;367254;344521;318974;332942;571483;454362;739916;747856;685153;736325;383784;384932;468400;764910;758148;830939;836774;785857;760352;683906;341939;594345;585810;494909;598331;460220;592766;592804;595975;590225;592490;757820;586882;758630;758673;585631;758095;734385;662555;370322;558387;666151;844311;849217;898548;908766;758238;881315;391519;395817;663432;666309;844081;756693;676764;786699;468494;376918;717483;733132;734523;588938;542672;598611;596969;671189;588688;665064;550655;372706;883653;663483;346301;554796;660873;545210;555034;752360;523674;543899;833363;496678;545300;596029;338294;353713;476728;597351;729789;550585;526845;840676;367038;677352;341766;886153;533061;527196;510454;843747;833168;734044;886179;758755;734811;739385;390478;840745;468442;330998;392409;559400;669208;674174;351905;758874;342823;370285;552745;512725;597662;591303;597190;751558;552365;370412;841301;663343;528280;334515;334551;533154;848965;849366;346824;387517;546707;759337;565004;829074;598880;533134;598823;883524;554144;565691;589700;729245;555536;528164;350904;584343;533701;851687;385819;669602;837740;668097;849357;594178;671246;895169;661072;755022;543931;367045;";
			ArrayList<String> AssociateEmpIDList=getEmpIds(AssociateEmpID);
			importToTxt(driver,AssociateEmpIDList,"AssociateEmailList.txt");
			System.out.println("******************************************");
			/*String SupervisorEmpID="662555;171822;137252;137252;190594;190594;210056;210056;210056;342823;210056;210056;210056;326088;326088;210056;121918;121918;121918;121918;743659;743659;743659;526035;526035;526035;526035;526035;526035;526035;526035;526035;526035;526035;209951;209951;209951;209951;209951;209951;209951;209951;209951;209951;209951;209951;209951;538630;538630;538630;455321;455321;455321;455321;455321;455321;162023;162023;162023;162023;245683;133957;133957;245683;189215;53306;189215;533061;189215;115281;1460218;1460218;396704;1464814;";
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
	    we.sendKeys("May@2019");
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