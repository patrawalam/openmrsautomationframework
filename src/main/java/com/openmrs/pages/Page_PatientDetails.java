package com.openmrs.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuite;

import com.openmrs.browser.DriverManager;

public class Page_PatientDetails extends BasePage {

	private WebDriverWait wait;
	
	@FindBy(xpath = "//a/span[@class='show']")
	private WebElement lnkShowContactInfo;

	@FindBy(xpath = "//span[@class='PersonName-givenName']")
	private WebElement txtPersonGivenName;

	@FindBy(xpath = "//span[@class='PersonName-familyName']")
	private WebElement txtPersonFamilyName;

	@FindBy(xpath = "//span[@class='gender-age']/child::span[1]")
	private WebElement txtGender;

	@FindBy(xpath = "//span[@class='gender-age']/child::span[2]")
	private WebElement txtDOB;

	@FindBy(xpath = "//div[@id='contactInfoContent']/descendant::span[1]")
	private WebElement txtAddress;

	@FindBy(xpath = "//div[@class='identifiers']/span")
	private WebElement txtPatientID;

	@FindBy(xpath = "//a[contains(@id,'createVisit')]")
	private WebElement lnkStartVisit;

	@FindBy(id = "start-visit-with-visittype-confirm")
	private WebElement btnConfirm;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='height']/span[@class='value']")
	private WebElement lblHeight ;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='weight']/span[@class='value']")
	private WebElement lblWeight;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='calculated-bmi-wrapper']/span[@id='calculated-bmi']")
	private WebElement lblBMICalculated;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='temperature']/span[@class='value']")
	private WebElement lblTemperature;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='heart_rate']/span[@class='value']")
	private WebElement lblPulse;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='bp_systolic']/span[@class='value']")
	private WebElement lblSystolic ;
	
	@FindBy(xpath = "//section[@id='vitals']/fieldset/descendant::span[@id='bp_diastolic']/span[@class='value']")
	private WebElement lblDiastolic;
	
	
	By lnkAttachments = By.xpath("//a[contains(@href,'attachments')]");

	public Page_PatientDetails() {
		super();
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), 10);
	}

	public boolean verifyRegisteredPatientDetails(String fName, String lName, String gender, String day, String month,
			String year, String address1, ISuite suite) {

		wait.until(ExpectedConditions.elementToBeClickable(lnkShowContactInfo));
		click(lnkShowContactInfo);
		
		boolean verifyFirstName = txtPersonGivenName.getText().equals(fName);
		boolean verifyLastName = txtPersonFamilyName.getText().equals(lName);
		boolean verifyGender = txtGender.getText().split(" ")[0].trim().contains(gender);

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		Date d = null;
		try {
			d = sdf.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf = new SimpleDateFormat("MMM");
		String modifiedMonth = sdf.format(d);
		String daymodifiedMonthYear = (int) Double.parseDouble(day) + "." + modifiedMonth + "."
				+ (int) Double.parseDouble(year);

		// Logic to get daymonthyear from application
		boolean verifyDayMonthYear = txtDOB.getText().split(" ")[2].split("[\\(\\)]")[1].equals(daymodifiedMonthYear);

		// Not verifying Address field as it is complicated

		suite.setAttribute("PatientID", txtPatientID.getText());
		suite.setAttribute("PatientName", fName + " " + lName);
		if (verifyFirstName && verifyLastName && verifyGender && verifyDayMonthYear)
			return true;
		else
			return false;
	}

	public Object navigateToStartAVisit() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkStartVisit));
		click(lnkStartVisit);
		click(btnConfirm);
		
		if(isElementPresent(lnkAttachments))
			return new Page_VisitsDetailsPage();
		else
			return this;
		
	}
	
	public boolean verifyVitalsCaptured(String height, String weight, String temperature, 
			String pulse, String systolic, String diastolic, String expectedBMI) {
		
		boolean verifyHeight = lblHeight.getText().equals(height);
		boolean verifyweight = lblWeight.getText().equals(weight);
		boolean verifytemperature = lblTemperature.getText().equals(temperature);
		boolean verifypulse = lblPulse.getText().equals(pulse);
		boolean verifySystolic = lblSystolic.getText().equals(height);
		boolean verifyDiastolic = lblDiastolic.getText().equals(diastolic);
		boolean verifyBMI = lblBMICalculated.getText().equals(expectedBMI);
		
		if(verifyHeight && verifyweight && verifytemperature && verifypulse && verifySystolic && verifyDiastolic && verifyBMI)
			return false;
		else 
			return true;
	}

}
