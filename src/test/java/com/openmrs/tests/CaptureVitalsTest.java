package com.openmrs.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.openmrs.pages.Page_CaptureVitals;
import com.openmrs.pages.Page_FindPatientRecord;
import com.openmrs.pages.Page_Home;
import com.openmrs.pages.Page_PatientDetails;
import com.openmrs.pages.Page_VisitsDetailsPage;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

//@Test()
public class CaptureVitalsTest extends BaseTest {

	WebDriver driver;
	// String testCaseName="LoginTest";

	@Test(description = "Create a visit and capture vitals" , dependsOnGroups = {"registerpatient"})	// "login", 
	public void T_006_CreateAVisitAndCaptureVitals(Hashtable<String, String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		suite = testContext.getSuite();
		

		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();

		LogStatus.info(methodName.getName() + " :: Navigating to Find a Patient page");
		Object obj = homePage.navigateToFindAPatient();

		if (obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Find a Patient");
		else
			LogStatus.takeScreenShot();

		Page_FindPatientRecord findAPatient = (Page_FindPatientRecord) obj;
		obj = findAPatient.searchAndSelectPatientByID((String) suite.getAttribute("PatientID"));

		if (obj instanceof Boolean && !(obj instanceof Page_PatientDetails))
			reportFailure(methodName.getName() + " :: Unable to Find Patient by ID");
		else
			LogStatus.takeScreenShot();

		Page_PatientDetails patientDetails = (Page_PatientDetails) obj;
		obj = patientDetails.navigateToStartAVisit();
		
		if(!(obj instanceof Page_VisitsDetailsPage))
			reportFailure(methodName.getName() + " :: Unable to navigate to Visits Details Page");
		else
			LogStatus.takeScreenShot();
		
		Page_VisitsDetailsPage visitsDetailsPage = (Page_VisitsDetailsPage) obj;
		obj = visitsDetailsPage.navigateToCaptureVitals();
		
		if(!(obj instanceof Page_CaptureVitals))
			reportFailure(methodName.getName() + " :: Unable to navigate to Capture Vitals Details Page");
		else
			LogStatus.takeScreenShot();
		
		Page_CaptureVitals captureDetailsPage = (Page_CaptureVitals) obj;
		obj = captureDetailsPage.captureVitals(data.get("Height"), data.get("Weight"), data.get("Temperature"), data.get("Pulse"), 
				data.get("Systolic"), data.get("Diastolic"));
		
		if(!(obj instanceof Page_VisitsDetailsPage))
			reportFailure(methodName.getName() + " :: Unable to navigate back to Visits Details Page");
		else
			LogStatus.takeScreenShot();
		
		visitsDetailsPage = (Page_VisitsDetailsPage) obj;
		obj = visitsDetailsPage.endVisit();
		
		if(!(obj instanceof Page_VisitsDetailsPage))
			reportFailure(methodName.getName() + " :: Unable to navigate back to Visits Details Page");
		else
			LogStatus.takeScreenShot();
		
		visitsDetailsPage = (Page_VisitsDetailsPage) obj;
		visitsDetailsPage.getMenu().logout();
		
		LogStatus.pass(methodName.getName() + " :: Vitals captured");
		
	}

	@Test(description = "Verify Vitals", dependsOnGroups = {"registerpatient"})	//dependsOnMethods = {"T_006_CreateAVisitAndCaptureVitals"}
	public void T_007_VerifyVitals(Hashtable<String, String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		suite = testContext.getSuite();
		

		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();

		LogStatus.info(methodName.getName() + " :: Navigating to Find a Patient page");
		Object obj = homePage.navigateToFindAPatient();

		if (obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Find a Patient");
		else
			LogStatus.takeScreenShot();

		Page_FindPatientRecord findAPatient = (Page_FindPatientRecord) obj;
		obj = findAPatient.searchAndSelectPatientByID((String) suite.getAttribute("PatientID"));   

		if (obj instanceof Boolean && !(obj instanceof Page_PatientDetails))
			reportFailure(methodName.getName() + " :: Unable to Find Patient by ID");
		else
			LogStatus.takeScreenShot();

		Page_PatientDetails patientDetails = (Page_PatientDetails) obj;
		boolean actualResultBoolean = patientDetails.verifyVitalsCaptured(data.get("Height"), data.get("Weight"), data.get("Temperature"), 
					data.get("Pulse"), data.get("Systolic"), data.get("Diastolic"), data.get("ExpectedBMI"));
		
		String actualResult = "";
		if(actualResultBoolean)
			actualResult = "Successful";
		else
			actualResult = "Unsuccessful";
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure(methodName.getName() + " :: Vitals Do Not Match");
		else 
			LogStatus.takeScreenShot();
		
		LogStatus.pass(methodName.getName() + " :: Vitals match");
	}
	
}
