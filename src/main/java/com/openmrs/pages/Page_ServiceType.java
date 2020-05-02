package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;

public class Page_ServiceType extends BasePage {

	@FindBy(how = How.ID, using = "name-field")
	private WebElement txtName;
	
	@FindBy(how = How.ID, using = "duration-field")
	private WebElement txtDuration;
	
	@FindBy(how = How.ID, using = "description-field")
	private WebElement txtDescription;
	
	@FindBy(how = How.ID, using = "save-button")
	private WebElement btnSave;
	
	By isNewServiceTypePresent = By.cssSelector("i[class=icon-plus]");
	
	public Page_ServiceType() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public Object createNewServiceType(String name, String duration, String desc) {
		clear(txtName);
		sendkeys(txtName, name);
		clear(txtDuration);
		sendkeys(txtDuration, String.valueOf((int) Double.parseDouble(duration)));
		clear(txtDescription);
		sendkeys(txtDescription, desc);
		click(btnSave);
		if(!(isElementPresent(isNewServiceTypePresent)))
			return this;
		else 
			return new Page_ManageServiceType();
	}
}
