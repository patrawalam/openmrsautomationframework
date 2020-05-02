package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;

public class Page_AppointScheduling extends BasePage{

	@FindBy(xpath = "//a[contains(@href,'manageAppointment')]")
	private WebElement lnkManageServiceTypes;
	
	By isManageServiceTypeHeadingPresent = By.xpath("//h1");	//Referencing element from Manage Service type Page
	
	public Page_AppointScheduling() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public Object gotoManageServiceTypes() {
		click(lnkManageServiceTypes);
		if(!(isElementPresent(isManageServiceTypeHeadingPresent)))
			return this;
		else
			return new Page_ManageServiceType();
	}
}
