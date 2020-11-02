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

public class TC_ReplyMail_002 extends BaseClass{
	@Parameters({"TestCase_Name2"})
	@Test
	public void replyMail(String tc) throws Exception
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
			
			//Replying Mail
			ComposePage composePage=new ComposePage(driver);
			composePage.replyMail("First Mail", "Replying inbox mail");
			Thread.sleep(1000);
			composePage.enterDetails("EmailBody", mapref.get("EmailContent"));			
			composePage.actions("Send");
		
			Thread.sleep(7000);
			composePage.actions("SentItem");
			boolean sentItemVerification = composePage.findMail("Received Mail", "First Mail");
			
			Thread.sleep(7000);
			composePage.actions("Inbox");
			Thread.sleep(1000);
			boolean blnPass = composePage.findMail("Received Mail", "First Mail");
			

			if (blnPass==true )
				ExtentTestManager.getTest().log(Status.PASS,"Reply mail is successfull");		
			else
				ExtentTestManager.getTest().log(Status.FAIL,"Reply mail not successfull");
		    
			//logout
			loginPage.logout(driver);
			
	} catch (CustomisedException e) {
	
		// TODO Auto-generated catch block
		e.printStackTrace();
	ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
	ExtentTestManager.getTest().log(Status.FAIL,"Sending mail not successfull");
	
	}
	}
	
}
