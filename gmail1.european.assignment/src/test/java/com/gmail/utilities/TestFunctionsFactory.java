package com.gmail.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.gmail.ReportConfiguration.ExtentTestManager;
import com.google.common.primitives.Bytes;

public class TestFunctionsFactory {

	
	CustomisedException obj;

	
	/* @param element
	 * 
	 * @param text
	 * 
	 * @throws CustomisedException
	 */
	public static void webEditText(WebElement element, String text,String name) throws CustomisedException {
		try {
			element.clear();
			element.sendKeys(text);
			ExtentTestManager.getTest().log(Status.PASS, "Entered "+text+" on "+name);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Enter data on "+name);
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}

	public void webEditText1(WebElement element, String text) throws CustomisedException {
		try {
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}
	
	/* 
	 * @param element
	 * 
	 * @throws CustomisedException
	 */
	public static void waitForElementVisible(WebDriver driver,WebElement element,int waitTime) throws CustomisedException {
		try {
	
			WebDriverWait wait=new WebDriverWait(driver,waitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Element is not displayed");
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
	
			throw obj;
		}
	}
	/*
	 * @param element
	 * 
	 * @throws CustomisedException
	 */
	public static void webClick(String actionName,WebElement element) throws CustomisedException {
		try {
			 element.click();
			 ExtentTestManager.getTest().log(Status.PASS, "Element " +actionName+" is clicked");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Element " +actionName+" is not displayed");
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}

	
	/*
	 * 
	 * @param element
	 * 
	 * @throws CustomisedException
	 */
	public static void javaScriptClick(WebDriver driver,WebElement element) throws CustomisedException {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

		} catch (Exception e) {
			e.printStackTrace();

			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}

	/*
	 * 
	 * @param element
	 * 
	 * @throws CustomisedException
	 */
	public static void doubleClick(WebDriver driver,WebElement element) throws CustomisedException {
		try {
			Actions obj = new Actions(driver);
			obj.doubleClick(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}

	/*
	 * @param element
	 * 
	 * @throws CustomisedException
	 */
	public static boolean verifyElementdisplayed(String name,WebElement element) throws CustomisedException {
		try {

			element.isDisplayed();
			ExtentTestManager.getTest().log(Status.PASS,"Element "+name+" is displayed");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Element "+name+" is not displayed");
			CustomisedException obj = new CustomisedException(element.toString(), e.getMessage().toString());
			throw obj;
		}

	}
	
	/*
	 * @param driver
	 * 
	 * @throws CustomisedException
	 */
	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition expectation = new ExpectedCondition() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}

			@Override
			public Object apply(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NullPointerException.class);
		try {
			wait.until(expectation);
			
		} catch (Throwable error) {
			// logger.error("Timeout waiting for Page Load Request to
			// complete.");
		}

	}


	   
	}


