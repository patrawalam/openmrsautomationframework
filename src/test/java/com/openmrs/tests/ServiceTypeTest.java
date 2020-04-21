package com.openmrs.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.openmrs.pages.Page_AppointScheduling;
import com.openmrs.pages.Page_Home;
import com.openmrs.pages.Page_ManageServiceType;
import com.openmrs.pages.Page_ServiceType;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

public class ServiceTypeTest extends BaseTest {

	@Test(description = "Adding Service Type", groups = {"servicetype"}) 	//, dependsOnGroups = {"login"}
	public void T_008_CreateServiceTypes(Hashtable<String,String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		suite = testContext.getSuite();
		

		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();
		
		LogStatus.info(methodName.getName() + " :: Navigating to Appointment Scheduling");
		
		Object obj = homePage.navigateToAppointmentScheduling();
		if(obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Appointment Scheduling page");
		else
			LogStatus.takeScreenShot();
		
		Page_AppointScheduling appScheduling = (Page_AppointScheduling) obj;
		LogStatus.info(methodName.getName() + " :: Navigating to Manage Service Type page");
		
		obj = appScheduling.gotoManageServiceTypes();
				
		if (obj instanceof Page_AppointScheduling)
			reportFailure(methodName.getName() + " :: Unable to navigate to Manage Service Type page");
		else
			LogStatus.takeScreenShot();
		
		LogStatus.info(methodName.getName() + " :: Navigate to create new Service Type");
		
		Page_ManageServiceType manageServiceType = (Page_ManageServiceType)obj;
		obj = manageServiceType.gotoServiceTypes();
		
		if(obj instanceof Page_ManageServiceType)
			reportFailure(methodName.getName() + " :: Unable to navigate to Service Type page");
		else
			LogStatus.takeScreenShot();
		
		Page_ServiceType serviceType = (Page_ServiceType) obj;
		
		LogStatus.info(methodName.getName() + " :: Creating new Service Type");
		
		obj = serviceType.createNewServiceType(data.get("Name"), data.get("Duration"), data.get("Description"));
		
		if(obj instanceof Page_ServiceType)
			reportFailure(methodName.getName() + " :: Unable to create a new Service Type page");
		else
			LogStatus.takeScreenShot();
		
		manageServiceType = (Page_ManageServiceType)obj;
		manageServiceType.getMenu().logout();
		LogStatus.takeScreenShot();
		
		LogStatus.pass(methodName.getName() + " :: Create Service Type successful");
	}
	
	@Test(description = "Verifying Service Type", groups = {"servicetype"}) 	//, dependsOnGroups = {"login"}
	public void T_009_VerifyServiceTypes(Hashtable<String,String> data, Method methodName, ITestContext testContext) {
		
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		suite = testContext.getSuite();
		

		LogStatus.info(methodName.getName() + " :: Logging in with Default Login");
		Page_Home homePage = doDefaultLogin();
		
		LogStatus.info(methodName.getName() + " :: Navigating to Appointment Scheduling");
		
		Object obj = homePage.navigateToAppointmentScheduling();
		if(obj instanceof Page_Home)
			reportFailure(methodName.getName() + " :: Unable to navigate to Appointment Scheduling page");
		else
			LogStatus.takeScreenShot();
		
		Page_AppointScheduling appScheduling = (Page_AppointScheduling) obj;
		LogStatus.info(methodName.getName() + " :: Navigating to Manage Service Type page");
		
		obj = appScheduling.gotoManageServiceTypes();
				
		if (obj instanceof Page_AppointScheduling)
			reportFailure(methodName.getName() + " :: Unable to navigate to Manage Service Type page");
		else
			LogStatus.takeScreenShot();
		
		Page_ManageServiceType manageServiceType = (Page_ManageServiceType) obj;
		List<String> al = manageServiceType.checkIfServiceTypeExist(data.get("Name"));
		String actualResultName = null, actualResultDuration = null, actualResultDescription = null;
		if(al.get(0).equalsIgnoreCase("Not Found") )
			reportFailure(methodName.getName() + " :: Service Type not found");
		else {
			actualResultName = al.get(0);
			actualResultDuration = al.get(1);
			actualResultDescription = al.get(2);
		}
		Assert.assertEquals(actualResultName, data.get("Name"));
		Assert.assertEquals(actualResultDuration, String.valueOf((int) Double.parseDouble(data.get("Duration"))));
		Assert.assertEquals(actualResultDescription, data.get("Description"));
		LogStatus.takeScreenShot();
		
		manageServiceType.getMenu().logout();
		LogStatus.pass(methodName.getName() + " :: Service type validation successful");
	}
	//
}
