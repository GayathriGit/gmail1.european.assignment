package com.gmail.pageObjectsAndMethods;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.TestFunctionsFactory;

public class LoginPage {

	WebDriver ldriver;
	//Constructor for LoginPage
	public LoginPage(WebDriver driver) {

			ldriver=driver;
			PageFactory.initElements(driver, this);
	}

	//Email Text box
	@FindBy(xpath="//input[@type='email']")
	@CacheLookup
	WebElement txtEmailId;
		
	//Next Button
	@FindBy(xpath="//*[@class='VfPpkd-RLmnJb']")
	@CacheLookup
	WebElement btnNext;

	//Password text box
	@FindBy(xpath="//input[@type='password']")
	@CacheLookup
	WebElement txtPassword;
	
	//Next Button in password page
	@FindBy(id="passwordNext")
	@CacheLookup
	WebElement btnPwdNext;
	
	//Compose button
	@FindBy(xpath="//div[contains(text(),\"Compose\")]")
	@CacheLookup
	WebElement btnCompose;
	
	@FindBy(xpath="//a[contains(@href,'SignOutOptions')]")
	@CacheLookup
	WebElement linkSignout;
	
	@FindBy(xpath="//a[contains(text(),'Sign out')]")
	@CacheLookup
	WebElement Signout;
	
	
			
	public void enterDetails(String fieldName,String Data) throws CustomisedException {
		
		try {
		switch(fieldName) {
		case "emailId": 
			TestFunctionsFactory.webEditText(txtEmailId, Data,fieldName);
			
			break;
		case "password":
			TestFunctionsFactory.webEditText(txtPassword, Data,fieldName);
			break;
		default:
			throw new CustomisedException(fieldName,"The fieldName you provided is not available in method");
		
		}
		}
		catch(CustomisedException e ) {
			throw new CustomisedException(fieldName, "The following error occured in Login Page"+e.getErrorMessage());
		}
		
	}
	
	public void actions(String actionName) throws CustomisedException {
		
		try {
		switch(actionName) {
		case "Next":
			TestFunctionsFactory.webClick(actionName,btnNext);;
			break;
		case "submit":
			TestFunctionsFactory.webClick(actionName,btnPwdNext);
			break;
		case "linkSignOut":
			TestFunctionsFactory.webClick(actionName,linkSignout);
			break;
		case "SignOut":
			TestFunctionsFactory.webClick(actionName,Signout);
			break;
		}
		}
		catch(CustomisedException e ) {
			throw new CustomisedException("The "+actionName+" action encountered the following error", ""+e.getErrorMessage());
		}
		
	}
	
	 public void login(WebDriver driver, HashMap<String, String> mapRef) throws InterruptedException, CustomisedException {
		try { 
			this.enterDetails("emailId", mapRef.get("Email_Id"));
			txtEmailId.sendKeys(Keys.ENTER);
			
			//this.actions("Next");
			TestFunctionsFactory.waitForPageLoaded(driver);
			this.enterDetails("password", mapRef.get("Password"));
			TestFunctionsFactory.waitForPageLoaded(driver);
			txtPassword.sendKeys(Keys.ENTER);
			//this.actions("submit");
			TestFunctionsFactory.waitForPageLoaded(driver);
			boolean blnPass = this.isDisplayed("Compose");
			
			if (blnPass=true)
				ExtentTestManager.getTest().log(Status.PASS,"Login successfull");
		}
		catch(CustomisedException e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
			ExtentTestManager.getTest().log(Status.FAIL,"Login not successfull");
		}
		
	 }
	 
	 public void logout(WebDriver driver) throws CustomisedException {
		 try {
		 this.actions("linkSignOut");
		 this.actions("SignOut");
		 }
		 catch(CustomisedException e)
		 {
				e.printStackTrace();
				ExtentTestManager.getTest().log(Status.FAIL, e.getFieldValue()+" :"+e.getErrorMessage());
				ExtentTestManager.getTest().log(Status.FAIL,"Logout not successfull");
		  }
			 
		 }
	 
	
	public boolean isDisplayed(String fieldName) throws CustomisedException {	
		
			try {
			
				boolean blnVisible = TestFunctionsFactory.verifyElementdisplayed(fieldName,btnCompose);
				if (blnVisible=true)
					return true;
				else
					return false;		
		
			}
			catch(CustomisedException e ) {
				throw new CustomisedException("The "+fieldName+"encountered the following error", ""+e.getErrorMessage());
			}
			
	}
	
}