package com.openmrs.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.openmrs.pages.ZHomePage;
import com.openmrs.pages.ZPage_LoginPage;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

public class ZServiceNowTests extends BaseTest {

	ZHomePage homepage;
	ZPage_LoginPage loginpage;
	
	@Test(description = "To test Login scenarios")
	public void LoginTest(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		loginpage = new ZPage_LoginPage();
		Object obj = loginpage.doLogin(data.get("Username"), data.get("Password"));
		if (obj instanceof ZHomePage)
			LogStatus.pass("Login successful with username :: \'"+data.get("username")+ "\' and password :: \'"+data.get("password")+"\'");
		else if((obj instanceof ZPage_LoginPage) && data.get("ExpectedResult").equalsIgnoreCase("Unsuccessful"))
			LogStatus.pass("Login unsuccessful with username :: \'"+data.get("username")+ "\' and password :: \'"+data.get("password")+"\'");
		else if (obj == null)
		{
			LogStatus.fail("Unknown Page returned");
			Assert.fail("Unknown Page returned");
		}
	}
	
	@Test(description = "To test Negative Login scenarios", dependsOnMethods = "LoginTest")
	public void LoginNegativeTest(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
		loginpage = new ZPage_LoginPage();
		Object obj = loginpage.doLogin(data.get("Username"), data.get("Password"));
		if (obj instanceof ZHomePage)
			LogStatus.pass("Login successful with username :: \'"+data.get("username")+ "\' and password :: \'"+data.get("password")+"\'");
		else if((obj instanceof ZPage_LoginPage) && data.get("ExpectedResult").equalsIgnoreCase("Unsuccessful"))
			LogStatus.pass("Login unsuccessful with username :: \'"+data.get("username")+ "\' and password :: \'"+data.get("password")+"\'");
		else if (obj == null)
			LogStatus.fail("Unknown Page returned");
	}
	
	@Test(description = "Test Case 03")
	public void tc03(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
	}
	
	@Test(description = "Test Case 04")
	public void tc04(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
	}
	
	@Test(description = "Test Case 05")
	public void tc05(Hashtable<String,String> data, Method methodName) {
		LogStatus.info(data.toString());
		TestUtils.validateRunMode(TestUtils.getXlsReaderObject(), data, methodName);
		
	}
	
}
