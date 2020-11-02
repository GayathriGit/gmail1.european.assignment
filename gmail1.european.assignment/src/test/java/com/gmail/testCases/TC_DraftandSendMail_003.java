package com.gmail.testCases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.pageObjectsAndMethods.ComposePage;
import com.gmail.pageObjectsAndMethods.LoginPage;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.ExcelReadWrite_API;
import com.gmail.utilities.TestFunctionsFactory;


public class TC_DraftandSendMail_003 extends BaseClass{

	@Parameters({"TestCase_Name3"})
	@Test
	public void composeMail(String tc) throws Exception
	{
		try {
		TC=tc;
		ExtentTestManager.startTest(TC);
		mapref=new HashMap<String, String>();
		excelObj=new ExcelReadWrite_API();

		mapref=excelObj.excelReadWrite(TC);
		
			//Login		
			LoginPage loginPage=new LoginPage(driver);
			loginPage.login(driver, mapref);
			
			//Compose mail
			ComposePage composePage=new ComposePage(driver);
			composePage.Draftmail(driver, mapref, "Draft");
			
			TestFunctionsFactory.waitForPageLoaded(driver);
			
			//Sent Item Verification
			composePage.actions("SentItem");
			boolean sentItemVerification = composePage.findMail("Draft Mail", "Draft Mail");
			
			//Inbox Verification
			composePage.actions("Inbox");
			TestFunctionsFactory.waitForPageLoaded(driver);
			boolean blnPass = composePage.findMail("Draft Mail", "Draft Mail");
			
			if (blnPass==true || sentItemVerification==true)
				ExtentTestManager.getTest().log(Status.PASS,"Draft,sending and Receiving mail is successfull");		
			else
				ExtentTestManager.getTest().log(Status.FAIL,"Draft,sending and Receiving mail not successfull");
			
			
		
	} catch (CustomisedException e) {
	
		// TODO Auto-generated catch block
	ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
	ExtentTestManager.getTest().log(Status.FAIL,"Draft,sending and Receiving mail not successfull");
	
	}
	}
	
}
