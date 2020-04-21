package com.openmrs.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.openmrs.pages.Page_Home;
import com.openmrs.pages.Page_Login;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

//@Test()
public class LoginTest extends BaseTest {
	
	WebDriver driver;
	
	@Test(description = "Testing Login scenario")	//, groups = {"login"}
	public void T_001_LoginTest(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		Page_Login login = new Page_Login();
		
		LogStatus.info(methodName.getName() + " :: Navigated to Website");
		LogStatus.info(methodName.getName() +" :: Entering Credentials");
		
		Object loginObj = login.doLogin(data.get("Username"),data.get("Password"));
		String actualResult="";
		
		if(loginObj instanceof Page_Home)
			actualResult="Successful";
		else
			actualResult="Unsuccessful";
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure(methodName.getName() + " Unable to login");
		else
		{
			LogStatus.takeScreenShot();
			LogStatus.pass(methodName.getName() + " :: Login Test Passed");
		}
		
	}
	
	@Test(description = "Testing Logout scenario") //dependsOnMethods = {"T_001_LoginTest"},
	public void T_002_LogoutTest(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		Page_Login login = new Page_Login();
		
		LogStatus.info(methodName.getName() + " :: Navigated to Website");
		LogStatus.info(methodName.getName() +" :: Entering Credentials");
		
		Object loginObj = login.doLogin(data.get("Username"),data.get("Password"));
		String actualResult="";
		
		if(!(loginObj instanceof Page_Home))
			reportFailure(methodName.getName() + " Unable to login");
		
		LogStatus.takeScreenShot();
		Page_Home homePage = (Page_Home) loginObj;
		Object logoutObj = homePage.getMenu().logout();
		
		if(logoutObj instanceof Page_Login)
			actualResult="Successful";
		else
			actualResult="Unsuccessful";
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure(methodName.getName() + " Unable to logout");
		else
		{
			LogStatus.takeScreenShot();
			LogStatus.pass(methodName.getName() + " :: Loout Test Passed");
		}
		
	}
	
}
