package com.gmail.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverSupplier {  
	public  RemoteWebDriver driver;
 
	
	/* ChromeDriver
	 */
	public WebDriver driverChrome() {

		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();

        
        options.addArguments("--start-maximized"); 
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");

        options.addArguments("disable-infobars"); 
        
     options.addArguments("--disable-extensions");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.binary", "");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		driver = new ChromeDriver(options);
		return driver;
 
	}


	/* @return
	 */
	public WebDriver driverIE() { 
	    /*String userProfile= "	C:\\Users\\kk.AP.000\\AppData\\Roaming\\Microsoft\\Internet Explorer\\UserData";
	
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver",
				"Resources//IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		return driver;*/
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//IEDriverServer.exe");
		driver =new InternetExplorerDriver();
		return driver;

	} 
	public WebDriver driverFirfox() { 
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
		DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
		firefoxCapabilities.setCapability("marionette", true);
	   driver=new FirefoxDriver(); 
	   
		return driver;

	} 
	
	
}


