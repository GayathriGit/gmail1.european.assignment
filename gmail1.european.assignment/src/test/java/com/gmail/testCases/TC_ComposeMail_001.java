package com.gmail.testCases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.pageObjectsAndMethods.ComposePage;
import com.gmail.pageObjectsAndMethods.LoginPage;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.ExcelReadWrite_API;
import com.gmail.utilities.ScreenShot;
import com.gmail.utilities.TestFunctionsFactory;

public class TC_ComposeMail_001 extends BaseClass{

	
	@Parameters({"TestCase_Name1"})
	@Test
	public void composeMail(String tc) throws Exception
	{
		try {
			
		TC=tc;
		ExtentTestManager.startTest(TC);
		mapref=new HashMap<String, String>();
		excelObj=new ExcelReadWrite_API();

		mapref=excelObj.excelReadWrite(TC);
		ScreenShot screenObj=new ScreenShot();
			//Login		
			LoginPage loginPage=new LoginPage(driver);
			loginPage.login(driver, mapref);
			
			//Compose mail
			ComposePage composePage=new ComposePage(driver);
			composePage.composemail(driver, mapref);
			
			TestFunctionsFactory.waitForPageLoaded(driver);
			
			//Sent Item Verification
			composePage.actions("SentItem");
			TestFunctionsFactory.waitForPageLoaded(driver);
			boolean sentItemVerification = composePage.findMail("Received Mail", "First Mail");
			
			TestFunctionsFactory.waitForPageLoaded(driver);
			//Inbox Verification
			composePage.actions("Inbox");
			TestFunctionsFactory.waitForPageLoaded(driver);
			boolean blnPass = composePage.findMail("Received Mail", "First Mail");
			
			if (blnPass==true || sentItemVerification==true)
				ExtentTestManager.getTest().log(Status.PASS,"Sending and Receiving mail is successfull");                			
			else
				ExtentTestManager.getTest().log(Status.FAIL,"Sending mail not successfull");
			
			
		
	} catch (CustomisedException e) {
	
		// TODO Auto-generated catch block
	ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
	ExtentTestManager.getTest().log(Status.FAIL,"Sending mail not successfull");
	
	}
	}
	
	
}
