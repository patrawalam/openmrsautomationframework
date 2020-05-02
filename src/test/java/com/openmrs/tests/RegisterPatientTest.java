package com.openmrs.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.openmrs.pages.Page_FindPatientRecord;
import com.openmrs.pages.Page_Home;
import com.openmrs.pages.Page_PatientDetails;
import com.openmrs.pages.Page_RegisterAPatient;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

//@Test()
public class RegisterPatientTest extends BaseTest {
	
	WebDriver driver;
	//String testCaseName="LoginTest";
	
	@Test(description = "Register a patient", groups = {"registerpatient"}) 	//, dependsOnGroups = {"login"}
	public void T_003_RegisterNewPatient(Hashtable<String,String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		suite = testContext.getSuite();
		
		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();
		
		LogStatus.info(methodName.getName() + " :: Navigating to Register a Patient page");
		
		Object obj = homePage.navigateToRegisterAPatient();
		if(obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Register a patient page");
		else
			LogStatus.takeScreenShot();
		
		Page_RegisterAPatient registerAPatient = (Page_RegisterAPatient) obj;
		LogStatus.info(methodName.getName() + " :: Registering a patient");
		
		obj = registerAPatient.fillDetailsOfPatient(data.get("GivenName"), data.get("FamilyName"),
				data.get("Gender"), data.get("Day"), data.get("Month"), data.get("Year"), data.get("Address") );
		if(!(obj instanceof Page_PatientDetails))
			reportFailure(methodName.getName() + " :: Unable to Register a patient");
		else
			LogStatus.takeScreenShot();
		
		LogStatus.info(methodName.getName() + " :: Verifying patient details");
		
		Page_PatientDetails patientDetails = (Page_PatientDetails)obj;
		boolean actualResult = patientDetails.verifyRegisteredPatientDetails(data.get("GivenName"), data.get("FamilyName"),
				data.get("Gender"), data.get("Day"), data.get("Month"), data.get("Year"), data.get("Address"), suite);	
		
		if (!actualResult)
			reportFailure(methodName.getName() + " :: Patient info does not match");
		else
			LogStatus.takeScreenShot();
		
		patientDetails.getMenu().logout();
		LogStatus.takeScreenShot();
		
		LogStatus.pass(methodName.getName() + " :: Patient details match");
	}
	
	@Test(dependsOnGroups = {"registerpatient"}, description = "Confirm patient registered using Patient ID")	//dependsOnMethods = {"T_003_RegisterNewPatient"}, 
	public void T_004_ConfirmPatientRegisteredUsingPatientID(Hashtable<String,String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();
		
		LogStatus.info(methodName.getName() + " :: Navigating to Find a Patient page");
		Object obj = homePage.navigateToFindAPatient();
		
		if(obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Find a Patient");
		
		Page_FindPatientRecord findAPatient = (Page_FindPatientRecord) obj;
		obj = findAPatient.searchAndSelectPatientByID((String)suite.getAttribute("PatientID"));
		
		if(obj instanceof Boolean && !(obj instanceof Page_PatientDetails))
			reportFailure(methodName.getName() + " :: Unable to Find Patient by ID");
		
		Page_PatientDetails patientDetails = (Page_PatientDetails) obj;
		boolean actualResult = patientDetails.verifyRegisteredPatientDetails(data.get("GivenName"), data.get("FamilyName"),
				data.get("Gender"), data.get("Day"), data.get("Month"), data.get("Year"), data.get("Address"), suite);
		
		if (!actualResult)
			reportFailure(methodName.getName() + " :: Patient info does not match");
		else
			LogStatus.takeScreenShot();
		
		patientDetails.getMenu().logout();
		LogStatus.takeScreenShot();
		
		LogStatus.pass(methodName.getName() + " :: Patient details searched with ID matches");
	}

	@Test(description = "Confirm patient registered using Patient Name", dependsOnGroups = {"registerpatient"})	//dependsOnMethods = {"T_003_RegisterNewPatient"}
	public void T_005_ConfirmPatientRegisteredUsingPatientName(Hashtable<String,String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();
		
		LogStatus.info(methodName.getName() + " :: Navigating to Find a Patient page");
		Object obj = homePage.navigateToFindAPatient();
		
		if(obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Find a Patient");
		
		Page_FindPatientRecord findAPatient = (Page_FindPatientRecord) obj;
		obj = findAPatient.searchAndSelectPatientByName((String)suite.getAttribute("PatientName"));
		
		if(obj instanceof Boolean && !(obj instanceof Page_PatientDetails))
			reportFailure(methodName.getName() + " :: Unable to Find Patient by Patient Name");
		
		Page_PatientDetails patientDetails = (Page_PatientDetails) obj;
		boolean actualResult = patientDetails.verifyRegisteredPatientDetails(data.get("GivenName"), data.get("FamilyName"),
				data.get("Gender"), data.get("Day"), data.get("Month"), data.get("Year"), data.get("Address"), suite);
		
		if (!actualResult)
			reportFailure(methodName.getName() + " :: Patient info does not match");
		else
			LogStatus.takeScreenShot();
		
		patientDetails.getMenu().logout();
		LogStatus.takeScreenShot();
		
		LogStatus.pass(methodName.getName() + " :: Patient details searched with Patient name matches");
	
	}
}
