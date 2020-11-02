package com.gmail.pageObjectsAndMethods;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.gmail.utilities.CustomisedException;
import com.gmail.utilities.TestFunctionsFactory;

public class ComposePage {

	WebDriver Composedriver;
	//Constructor for ComposePage
		public ComposePage(WebDriver driver) {
			Composedriver=driver;
			PageFactory.initElements(driver, this);
		}
	
		//Compose button
		@FindBy(xpath="//div[contains(text(),'Compose')]")
		@CacheLookup
		WebElement btnCompose;
		
		//New Message Window
		@FindBy(xpath="//div[contains(text(),\"New Message\")]")
		@CacheLookup
		WebElement msgWindow;
		
		//Text Space To
		@FindBy(xpath="//textarea[@name=\"to\"]")
		@CacheLookup
		WebElement txtSpaceTo;
		
		//Text Space Subject
		@FindBy(xpath="//input[@name='subjectbox']")
		@CacheLookup
		WebElement txtSpaceSubject;
		
		//Email Body
		@FindBy(xpath="//div[@aria-label=\"Message Body\"]")		
		@CacheLookup
		WebElement txtEmailBody;		
		
		//Send Button
		//@FindBy(xpath="//div[contains(text(),'Send']")
		@FindBy(xpath="//div[@class='dC']")
		@CacheLookup
		WebElement btnSend;
		
		//Mails in Inbox
		@FindBy(xpath="//span[@class='bog']/span")		
		@CacheLookup
		List<WebElement> emailThreads;
		
		
		//ReplyButton
		//@FindBy(xpath="//span[contains(text(),'Reply')]")
		@FindBy(xpath="//span[@class='ams bkH']")	
		@CacheLookup
		WebElement btnReply;
		
		
		//Sent Items
		@FindBy(xpath = "//a[text()='Sent']")
		@CacheLookup
		WebElement linkSent;
		
		//Inbox
		@FindBy(xpath = "//a[text()='Inbox']")
		@CacheLookup
		WebElement linkInbox;
		
		
		@FindBy(xpath ="//a[contains(text(),'Drafts')]")
		@CacheLookup
		WebElement linkDraftItems;
		
		@FindBy(xpath ="//img[@aria-label='Save & close']")
		@CacheLookup
		WebElement btnSaveandClose;
		
		//enter in compose,reply and draft page
	public void enterDetails(String fieldName,String Data) throws CustomisedException {
		
		try {
		switch(fieldName) {
		case "To_TextBox": 
			TestFunctionsFactory.waitForElementVisible(Composedriver,txtSpaceTo, 60);
			TestFunctionsFactory.webEditText(txtSpaceTo, Data,fieldName);
			break;
		case "Subject":
			TestFunctionsFactory.waitForElementVisible(Composedriver,txtSpaceSubject, 60);
			TestFunctionsFactory.webEditText(txtSpaceSubject, Data,fieldName);
			break;
		case "EmailBody":
			TestFunctionsFactory.waitForElementVisible(Composedriver,txtEmailBody, 60);
			TestFunctionsFactory.webEditText(txtEmailBody, Data,fieldName);
			break;
		default:
			throw new CustomisedException(fieldName,"The fieldName you provided is not available in method");
		
		}
		}
		catch(CustomisedException e ) {
			throw new CustomisedException(fieldName, "The following error occured in Compose Page"+e.getErrorMessage());
		}
		
	}
	
	//actions in compose,reply and Draft page
	public void actions(String actionName) throws CustomisedException, InterruptedException {
		
		try {
		switch(actionName) {
		case "Compose":
			TestFunctionsFactory.waitForElementVisible(Composedriver,btnCompose, 60);
			TestFunctionsFactory.webClick(actionName,btnCompose);
			//btnCompose.click();
			break;
		case "Send":
			TestFunctionsFactory.waitForElementVisible(Composedriver,btnSend, 60);
			TestFunctionsFactory.webClick(actionName,btnSend);
			break;
		case "SentItem":
			TestFunctionsFactory.webClick(actionName,linkSent);
			break; 
			
		case "Inbox":
			TestFunctionsFactory.webClick(actionName,linkInbox);
			break; 
			
		case "Draft":
			TestFunctionsFactory.webClick(actionName,linkDraftItems);
			break;
			
		case "SaveandClose":
			TestFunctionsFactory.webClick(actionName,btnSaveandClose);
			break;
		}
		
		}
		catch(CustomisedException e ) {
			throw new CustomisedException(actionName, ""+e.getErrorMessage());
		}
	}
	
	//find mail
	public WebElement firstMatchMail;
	public boolean findMail(String fieldName,String subject)throws CustomisedException{
		boolean blnFind=false;
	
		try {
		/*for(int i=0;i<emailThreads.size();i++)
		{			
			if(emailThreads.get(i).getText().contains(subject)) {
				System.out.println(emailThreads.get(i).getText());
			blnFind=true;	
			
			break;
			}*/
			List<WebElement> emailTh= Composedriver.findElements(By.xpath("//span[@class='bog']/span"));
			for(int i=0;i<emailTh.size();i++)
			{			
				if(emailTh.get(i).getText().contains(subject)) {
					System.out.println(emailTh.get(i).getText());
					blnFind=true;	
				//firstMatchMail=emailThreads.get(i).click();
				break;
				}
			}
		
		}
		
		catch(Exception e ) {
			CustomisedException.setFieldValue(fieldName);
			CustomisedException.setErrorMessage(e.getMessage());
		}
	return blnFind;	
	}
	
	//reply mail
	public void replyMail(String subject,String replyMessage) throws CustomisedException {
		try {
		//this.findMail("receivedMailClick", subject);
			List<WebElement> emailTh= Composedriver.findElements(By.xpath("//span[@class='bog']/span"));
			for(int i=0;i<emailTh.size();i++)
			{			
				if(emailTh.get(i).getText().contains(subject)) {
					System.out.println(emailTh.get(i).getText());
					emailTh.get(i).click();
				//firstMatchMail=emailThreads.get(i).click();
				break;
				}
			}
		Thread.sleep(1000);
		//TestFunctionsFactory.webClick("receivedMailClick", firstMatchMail);
		//TestFunctionsFactory.waitForPageLoaded((WebDriver) this);
		TestFunctionsFactory.webClick("reply", btnReply);
		}
		catch(Exception e ) {
			e.printStackTrace();
			CustomisedException.setFieldValue("receivedMailClick");
			CustomisedException.setErrorMessage(e.getMessage());
		}
		
	}
	
	//Compose mail
	public void composemail(WebDriver driver, HashMap<String, String> mapRef) throws InterruptedException, CustomisedException { 
		this.actions("Compose");
		this.enterDetails("To_TextBox", mapRef.get("To_EmailID"));
		this.enterDetails("Subject", mapRef.get("Subject"));
		this.enterDetails("EmailBody", mapRef.get("EmailContent"));			
		this.actions("Send");
	}
	
	//Draft mail
		public void Draftmail(WebDriver driver, HashMap<String, String> mapRef,String subject) throws InterruptedException, CustomisedException { 
		try {
			this.actions("Compose");
			this.enterDetails("To_TextBox", mapRef.get("To_EmailID"));
			this.enterDetails("Subject", mapRef.get("Subject"));
			this.enterDetails("EmailBody", mapRef.get("EmailContent"));
			this.actions("SaveandClose");
			TestFunctionsFactory.waitForPageLoaded(driver);
			this.actions("Draft");
			TestFunctionsFactory.waitForPageLoaded(driver);
			//this.findMail("DraftMailClick", subject);
			
			List<WebElement> emailTh= Composedriver.findElements(By.xpath("//span[@class='bog']/span"));
			for(int i=0;i<emailTh.size();i++)
			{			
				if(emailTh.get(i).getText().contains(subject)) {
					System.out.println(emailTh.get(i).getText());
					emailTh.get(i).click();
				//firstMatchMail=emailThreads.get(i).click();
				break;
				}
			}
			
			TestFunctionsFactory.waitForPageLoaded(driver);
			//TestFunctionsFactory.webClick("DraftMailClick", firstMatchMail);
			//TestFunctionsFactory.waitForPageLoaded((WebDriver) this);
			//TestFunctionsFactory.waitForPageLoaded(driver);			
			this.actions("Send");
		}
		catch(CustomisedException e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "DraftMail"+" :"+e.getErrorMessage());
			ExtentTestManager.getTest().log(Status.FAIL,"Sending mail not successfull");
			CustomisedException obj = new CustomisedException("DraftMail", e.getMessage().toString());
			throw obj;
		}
		}
	
}
