package com.gmail.testCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.pageObjectsAndMethods.LoginPage;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.ExcelReadWrite_API;
import com.gmail.utilities.TestFunctionsFactory;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void loginTest() throws Exception
	{try {
			
			
		LoginPage loginPage=new LoginPage(driver);
		 
			loginPage.enterDetails("emailId", mapref.get("Email_Id"));
			loginPage.actions("Next");
			TestFunctionsFactory.waitForPageLoaded(driver);
			loginPage.enterDetails("password", mapref.get("Password"));
			TestFunctionsFactory.waitForPageLoaded(driver);
			loginPage.actions("submit");
			TestFunctionsFactory.waitForPageLoaded(driver);
			boolean blnPass = loginPage.isDisplayed("Compose");
			
			if (blnPass=true)
				ExtentTestManager.getTest().log(Status.PASS,"Login successfull");		
					
		
	} catch (CustomisedException e) {
	
		// TODO Auto-generated catch block
	ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
	ExtentTestManager.getTest().log(Status.FAIL,"Login not successfull");
	
	}
	}
		
}
