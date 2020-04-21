package com.openmrs.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.openmrs.browser.DriverManager;

public class Page_FindPatientRecord extends BasePage {

	private WebDriverWait wait;
	
	@FindBy (id = "patient-search")
	private WebElement inputPatientSearch;
	
	@FindBy (xpath = "//table[@id='patient-search-results-table']/tbody/tr[1]/td[1]")
	private WebElement tblIdentifierBasedOnID;
	
	@FindBy (xpath = "//table[@id='patient-search-results-table']/tbody/tr[1]/td[2]")
	private WebElement tblIdentifierBasedOnName;
	
	//h1[contains(text(),'This patient must be checked-in before their vitals can be recorded. Send the patient to the check-in counter')]
	
	public Page_FindPatientRecord() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), 10);
	}
	
	public Object searchAndSelectPatientByID(String id) {
		inputPatientSearch.sendKeys(id);
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(tblIdentifierBasedOnID));
		if (tblIdentifierBasedOnID.getText().split(" ")[0].equals(id)) {
			tblIdentifierBasedOnID.click();
			return new Page_PatientDetails(); 
			}
		else
			return false;
	}
	
	public Object searchAndSelectPatientByName(String name) {
		inputPatientSearch.sendKeys(name);
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(tblIdentifierBasedOnName));
		if (tblIdentifierBasedOnID.getText().equalsIgnoreCase(name)) {
			tblIdentifierBasedOnID.click();
			return new Page_PatientDetails(); 
			}
		else
			return false;
	}
}
