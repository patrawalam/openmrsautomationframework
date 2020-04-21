package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;
import com.openmrs.reports.LogStatus;

public class ZPage_LoginPage extends BasePage{

	@FindBy(xpath = "//iframe[@id='gsft_main']")
	WebElement frameLogin; 
	
	@FindBy(xpath = "//input[@id='user_name']")
	WebElement txtUsername;
	
	@FindBy(xpath = "//input[@id='user_password']")
	WebElement txtPassword;
	
	@FindBy(id = "sysverb_login")
	WebElement btnLogin;
	
	By byIframeLogin = By.xpath("//iframe[@id='gsft_main']");
	By byTxtUsername = By.id("user_name");
	By byLblInvalidLogin = By.xpath("//div[@class='dp-invalid-login']");
	By byLnkAllApplications = By.xpath("//a[@data-original-title='All applications']");

	
	  public ZPage_LoginPage() { 
		  super();
		  PageFactory.initElements(DriverManager.getDriver(), this); 
	  }
	 
	
	public Object doLogin(String uName, String pwd) {
		
		if (!isElementPresent(byIframeLogin))
			LogStatus.fail("Unable to find element :: frameLogin");
		DriverManager.getDriver().switchTo().frame(DriverManager.getDriver().findElement(By.xpath("//iframe[@id='gsft_main']")));
		if (!isElementPresent(byTxtUsername))
			LogStatus.fail("Unable to find element :: byTxtUsername");
		
		sendkeys(txtUsername, uName);
		sendkeys(txtPassword, pwd);
		click(btnLogin);
		
		if(isElementPresent(byLnkAllApplications))
			return new ZHomePage();
		else if(isElementPresent(byLblInvalidLogin))
			return this;
		else
			return null;
		
	}
	
}
