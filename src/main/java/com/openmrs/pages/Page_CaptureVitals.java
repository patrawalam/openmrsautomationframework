package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.browser.DriverManager;

public class Page_CaptureVitals extends BasePage {
	
	private WebDriverWait wait;
	
	@FindBy(id="w8")
	private WebElement txtHeight;
	
	@FindBy(xpath = "//span[contains(text(),'Weight')]")
	private WebElement lblWeight;
	
	@FindBy(id="w10")
	private WebElement txtWeight;
	
	@FindBy(xpath = "//span[contains(text(),'(Calculated) BMI')]")
	private WebElement lblCalculatedBMI ;
	
	@FindBy(id = "calculated-bmi")
	private WebElement txtCalculatedBMI;
	
	@FindBy(xpath = "//span[contains(text(),'Temperature')]")
	private WebElement lblTemperature;
	
	@FindBy(id = "w12")
	private WebElement txtTemperature;
	
	@FindBy(xpath = "//span[contains(text(),'Pulse')]")
	private WebElement lblPulse ;
	
	@FindBy(id = "w14")
	private WebElement txtPulse ;
	
	@FindBy(xpath = "//span[contains(text(),'Blood Pressure')]")
	private WebElement lblBloodPressure;
	
	@FindBy(id = "w18")
	private WebElement txtSystolic;
	
	@FindBy(id = "w20")
	private WebElement txtDiastolic;
	
	@FindBy(xpath = "//span[contains(text(),'Confirm')]")
	private WebElement lblConfirm;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSubmit;
	
	By lnkCaptureVitals = By.xpath("//a[contains(@href,'vitals')]");	//Referencing element from Page_VisitsDetailsPage

	public Page_CaptureVitals() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), 10);
	}
	
	public Object captureVitals(String height, String weight, String temperature, 
			String pulse, String systolic, String diastolic) {
		sendkeys(txtHeight, String.valueOf((int) Double.parseDouble(height)));
		click(lblWeight);
		sendkeys(txtWeight, String.valueOf((int) Double.parseDouble(weight)));
		click(lblCalculatedBMI);
		click(lblTemperature);
		sendkeys(txtTemperature, String.valueOf((int) Double.parseDouble(temperature)));
		click(lblPulse);
		sendkeys(txtPulse, String.valueOf((int) Double.parseDouble(pulse)));
		click(lblBloodPressure);
		sendkeys(txtSystolic, String.valueOf((int) Double.parseDouble(systolic)));
		sendkeys(txtDiastolic, String.valueOf((int) Double.parseDouble(diastolic)));
		click(lblConfirm);
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		click(btnSubmit);
		
		if(isElementPresent(lnkCaptureVitals))
			return new Page_VisitsDetailsPage();
		else
			return this;
	}
}
