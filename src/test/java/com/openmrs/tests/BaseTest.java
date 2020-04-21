package com.openmrs.tests;

import java.awt.Desktop;
import java.io.File;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.openmrs.browser.Driver;
import com.openmrs.browser.DriverManager;
import com.openmrs.constants.Constants;
import com.openmrs.reports.ExtentManager;
import com.openmrs.reports.ExtentReport;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;
import com.openmrs.pages.Page_Home;



public class BaseTest {
	
	protected ISuite suite;
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		//System.out.println("In before Suite");
		ExtentReport.initialize();
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		//System.out.println("In after Suite");
			ExtentReport.report.flush();
			File htmlFile = new File(Constants.EXTENTREPORTPATH);
			Desktop.getDesktop().browse(htmlFile.toURI());
	}

	
	@BeforeMethod()
	public void setUp() {
		//System.out.println("In before Method");
		try {
			//System.out.println("Trying to initialize Driver");
			Driver.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@AfterMethod
	public void wrapUp() {
		//System.out.println("In after Method");
		//DriverManager.getDriver().close();
		DriverManager.getDriver().quit();
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		ExtentReport.report.flush();
		
	}

	public void reportFailure(String failureMessage){
		LogStatus.fail(failureMessage);
		//LogStatus.takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	/*
	 * Application Specific functions
	 * 
	 */
	
	public Page_Home doDefaultLogin() {
		DriverManager.getDriver().findElement(By.name("username")).sendKeys(TestUtils.getValue("username"));
		DriverManager.getDriver().findElement(By.name("password")).sendKeys(TestUtils.getValue("password"));
		
		DriverManager.getDriver().findElement(By.id("Registration Desk")).click();
		DriverManager.getDriver().findElement(By.id("loginButton")).click();
		return new Page_Home();
		
	}
}
