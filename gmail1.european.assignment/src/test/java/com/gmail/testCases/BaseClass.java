package com.gmail.testCases;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentManager;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.pageObjectsAndMethods.LoginPage;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.ExcelReadWrite_API;
import com.gmail.utilities.ReadConfig;
import com.gmail.utilities.TestFunctionsFactory;

public class BaseClass{ 
	
	ReadConfig readConfig=new ReadConfig();
	
	public String URL=readConfig.getURL();
	public WebDriver driver;
	public String TC;
	
	public  HashMap<String, String> mapref;
	public ExcelReadWrite_API excelObj;

	@BeforeSuite
	public void intialSetup()
	{
		ExtentManager.createInstance();
	}
	
	@Parameters({"browser","TestCase_Name1"})
	@BeforeClass
	public void setup(String browser,String tc,ITestContext testContext) throws Exception 
	{
		System.out.println("test1");
		try {
						
		switch (browser.toUpperCase()) {
		
		case "CHROME":
			System.out.println("chrome");
			System.setProperty("webdriver.chrome.driver",readConfig.getChromepath());
			driver =new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get(URL);
			break;
		case "FIREFOX":
			
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxpath());
			driver =new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get(URL);
			break;
		case "INTERNET EXPLORER":
		case "IE":
			
			System.setProperty("webdriver.ie.driver",readConfig.getIEpath());
			driver =new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get(URL);
			break;
		default:
			System.out.println("Given broswer name is not a valid one");
			
		}
		testContext.setAttribute("driver", driver);//passing the driver to TestNG 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.PASS,"login not successfull");
		}
	}

	
	@AfterClass
	public void teardown() throws CustomisedException
	{
		try {
		LoginPage loginPage=new LoginPage(driver);
		//logout
		loginPage.logout(driver);
		driver.quit();
		ExtentTestManager.endTest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL,"logout not successfull");
		}
	}
		
	
	
}