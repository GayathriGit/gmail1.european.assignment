package com.gmail.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.gmail.ReportConfiguration.ExtentTestManager;

public class ScreenShot { public void screenShotCapture(ITestResult result) {
	 
//	 System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//		System.out.println((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");

		String targetLocation = null;
        
		//String testClassName = getTestClassName(result.getInstanceName()).trim();
        ITestContext testContext=Reporter.getCurrentTestResult().getTestContext();;
		String testClassName = testContext.getName();
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("Eyyyy.MM.dd'_'hh:mm:ssa");
		String timeStamp = ft.format(dNow).replaceAll(":", "_");
		String testMethodName = result.getMethod().getMethodName();
		String screenShotName = testMethodName + timeStamp.replaceAll(" ", "").trim() + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReports" + fileSeperator
				+ "screenshots";
		System.out.println("Screen shots reports path - " + reportsPath);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
																				// screenshots
																				// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					System.out.println("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + testClassName.trim()+ fileSeperator + screenShotName;// define
																											// location
			File targetFile = new File(targetLocation);
			System.out.println("Screen shot file location - " + screenshotFile.getAbsolutePath());
			System.out.println("Target File location - " + targetFile.getAbsolutePath());
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An exception occurred while taking screenshot " + e.getLocalizedMessage());
		}

		// attach screenshots to report
		try {
			if(result.getStatus()!=1) {
			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());}
			else {
				ExtentTestManager.getTest().pass("Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());}
				
			
		} catch (IOException e) {
			System.out.println("An exception occured while taking screenshot " + e.getLocalizedMessage());
		}

}


}
