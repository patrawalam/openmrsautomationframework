package com.openmrs.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;

public class Page_ManageServiceType extends BasePage{

	@FindBy(className = "icon-plus")
	private WebElement btnNewServiceTypes;
	
	@FindBy(xpath = "//tr/td[1]")
	private List<WebElement> allServiceTypeNames;
	
	@FindBy(xpath = "//tr/td[2]")
	private List<WebElement> allServiceTypeDuration;
	
	@FindBy(xpath = "//tr/td[3]")
	private List<WebElement> allServiceTypeDescription;
	
	@FindBy(how = How.ID, using = "appointmentTypesTable_next")
	private WebElement lnkNext;
	
	By isNamePresent = By.id("name-field");	//Referencing element from Manage Service type Page
	
	public Page_ManageServiceType() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public Object gotoServiceTypes() {
		btnNewServiceTypes.click();
		if(!(isElementPresent(isNamePresent)))
			return this;
		else
			return new Page_ServiceType();
	}
	
	public List<String> checkIfServiceTypeExist(String serviceType) {
		ArrayList<String> al= new ArrayList<String>();
		boolean ifElementNotFound = true;
		
		while(ifElementNotFound) {
			for (int i = 0;i<allServiceTypeNames.size();i++) {
				if (allServiceTypeNames.get(i).getText().equals(serviceType)) {
					al.add(allServiceTypeNames.get(i).getText());
					al.add(allServiceTypeDuration.get(i).getText());
					al.add(allServiceTypeDescription.get(i).getText());
					ifElementNotFound = false;
					break;
				}
			if(ifElementNotFound && lnkNext.isEnabled()) {
				lnkNext.click();
			}
			else 
				ifElementNotFound = false;
		}
	}
		if(al.size() > 0)
			return al;
		else {
			al.add("Not Found");
			return al;
		}
	}
}
