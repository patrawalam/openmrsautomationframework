package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.browser.DriverManager;

public class Page_VisitsDetailsPage extends BasePage {
	
	private WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//a[contains(@href,'vitals')]")
	private WebElement lnkCaptureVitals;
	
	@FindBy(xpath = "//a[contains(@href,'EndVisit')]")
	private WebElement lnkEndVisit;
	
	@FindBy(xpath = "//div[@id='end-visit-dialog']/descendant::button[text()='Yes']")
	private WebElement btnYes;
	
	//@FindBy(xpath = "//h4[text()='No active visit']")
	//private WebElement lblNoActiveVisit;
	 
	By txtHeight = By.id("w8");		//Referencing element from Page_CaptureVitals
	By lblVitals = By.xpath("//div[text()='Vitals']"); 	//Referencing element from Page_PatientDetails
	By lblNoActiveVisit = By.xpath("//h4[text()='No active visit']");	//Referencing same page element

	public Page_VisitsDetailsPage() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), 10);
	}

	public Object navigateToCaptureVitals() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkCaptureVitals));
		click(lnkCaptureVitals);
		
		if(isElementPresent(txtHeight))
			return new Page_CaptureVitals();
		else
			return this;
		
	}
	
	public Object endVisit() {
		
		wait.until(ExpectedConditions.elementToBeClickable(lnkEndVisit));
		click(lnkEndVisit);
		wait.until(ExpectedConditions.elementToBeClickable(btnYes));
		click(btnYes);
		
		if(isElementPresent(lblNoActiveVisit)) 
			return this;
		else
			return false;
		
	}

}
