package com.openmrs.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.openmrs.browser.DriverManager;
import com.openmrs.reports.LogStatus;

public class Page_RegisterAPatient extends BasePage {
	
	private WebDriverWait wait;
	
	@FindBy(name = "givenName")
	private WebElement txtGivenName;
	
	@FindBy(name = "familyName")
	private WebElement txtFamilyName;
	
	@FindBy(id = "genderLabel")
	private WebElement lblGender;
	
	@FindBy(name = "gender")
	private WebElement selGender;
	
	@FindBy(xpath = "//span[@id='birthdateLabel']")
	private WebElement lblBirthdateLabel;
	
	@FindBy(name = "birthdateDay")
	private WebElement txtBirthdateDay;
	
	@FindBy(name = "birthdateMonth")
	private WebElement selBirthdateMonth;
	
	@FindBy(name = "birthdateYear")
	private WebElement txtBirthdateYear;
	
	@FindBy(xpath = "//span[text()='Address']")
	private WebElement lblAddress;
	
	@FindBy(id = "address1")
	private WebElement txtAddress1;

	@FindBy(id = "confirmation_label")
	private WebElement lblConfirmation;
	
	@FindBy(id = "submit")
	private WebElement btnSubmit;
	
	By txtPersonGivenName = By.xpath("//span[@class='PersonName-givenName']");
	
	public Page_RegisterAPatient() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(),10);
	}
	
	public Object fillDetailsOfPatient(String fName, String lName, String gender, String day, String Month, String Year, String address1) {
		txtGivenName.sendKeys(fName);
		txtFamilyName.sendKeys(lName);
		txtFamilyName.sendKeys(Keys.ENTER);
		lblGender.click();
		
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		Select sel = new Select(selGender);
		sel.selectByVisibleText(gender);
		
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		lblBirthdateLabel.click();
		
		txtBirthdateDay.sendKeys(String.valueOf((int) Double.parseDouble(day)));
		sel = new Select(selBirthdateMonth);
		sel.selectByVisibleText(Month);
		txtBirthdateYear.sendKeys(String.valueOf((int) Double.parseDouble(Year)));
		txtBirthdateYear.sendKeys(Keys.ENTER);
		lblAddress.click();
		
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		txtAddress1.sendKeys(address1);
		lblConfirmation.click();
		LogStatus.takeScreenShot();
		
		btnSubmit.click();
		
		if(isElementPresent(txtPersonGivenName))
			return new Page_PatientDetails();
		else
			return this;
	}
}
