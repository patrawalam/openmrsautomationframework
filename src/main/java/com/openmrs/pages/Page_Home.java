package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openmrs.browser.DriverManager;

public class Page_Home extends BasePage {
	
	@FindBy(xpath = "//a[contains(@href,'registerPatient')]")
	private WebElement lnkRegisterAPatient;
	
	@FindBy(xpath = "//a[contains(@href,'coreapps.findPatient')]")
	private WebElement lnkFindPatient;
	
	@FindBy(xpath = "//a[contains(@href,'vitals')]")
	private WebElement lnkCaptureVitals;
	
	@FindBy(xpath = "//a[contains(@href,'appointmentscheduling')]")
	private WebElement lnkAppointScheduling;
	
	By isGivenNamePresent = By.name("givenName");	//Referencing Element from Register a Patient Page
	By isSearchBoxPresent = By.id("patient-search");	//Referencing Element from Find a Patient and Capture Vitals for Patient Page
	By isManageServiceTypePresent = By.xpath("//a[contains(@href,'manageAppointment')]");	//Reference Element from Appointment Scheduling Page

	public Page_Home() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public Object navigateToRegisterAPatient() {
		click(lnkRegisterAPatient);
		if(!(isElementPresent(isGivenNamePresent)))
			return this;
		else
			return new Page_RegisterAPatient();
	}

	public Object navigateToFindAPatient() {
		click(lnkFindPatient);
		if(!(isElementPresent(isSearchBoxPresent)))
			return this;
		else
			return new Page_FindPatientRecord();
	}
	
	public Object navigateToCaptureVitals() {
		click(lnkCaptureVitals);
		if(!(isElementPresent(isSearchBoxPresent)))
			return this;
		else
			return new Page_FindPatientRecord();
	}
	
	public Object navigateToAppointmentScheduling() {
		click(lnkAppointScheduling);
		if(!(isElementPresent(isManageServiceTypePresent)))
			return this;
		else
			return new Page_AppointScheduling();
	}
}
