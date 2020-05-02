package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;


public class Page_Login extends BasePage{

	@FindBy(name = "username")
	private WebElement txtUsername;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='loginButton']")
	private WebElement btnloginButton;
	
	@FindBy(id = "Registration Desk")
	private WebElement selRegistrationDesk;
	
	By isPresentlnkLogout = By.xpath("//a[contains(text(),'Logout')]");
	
	public Page_Login() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	
	public Object doLogin(String uName, String pwd) {
		
		sendkeys(txtUsername, uName);
		sendkeys(txtPassword, pwd);
		click(selRegistrationDesk);
		click(btnloginButton);
		if(isElementPresent(isPresentlnkLogout))
			return new Page_Home(); 
		else 
			return this;
	}
	
	
	
}
