package com.openmrs.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.openmrs.browser.DriverManager;
import com.relevantcodes.extentreports.ExtentTest;

public class Page_TopMenu {

	protected WebDriver driver;
	protected ExtentTest test;
	protected WebDriverWait wait;
	
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement lnkLogout;
	
	By isPresenttxtUserName = By.name("username");
	

	
	public Page_TopMenu() {
		this.driver = DriverManager.getDriver();
		this.wait= new WebDriverWait(driver, 30);
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public Object logout() {
		//wait.until(ExpectedConditions.elementToBeClickable(lnkLogout));
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		BasePage.explicitlyWaitForElementClickable(lnkLogout);
		lnkLogout.click();
		if(!(isElementPresent(isPresenttxtUserName))){
			return new Page_Home();
		}
		else
			return new Page_Login();
		
	}
	
	public void changeSession() {
		
	}
	
	
	//Helper Functions
	
	public boolean isElementPresent(By ele) {
		boolean flag = false; 
		
		if(driver.findElements(ele).size() > 0 ) 
			return true;
				else
			return flag;
	}
	
	
}
