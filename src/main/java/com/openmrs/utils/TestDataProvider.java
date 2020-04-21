package com.openmrs.utils;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;



public class TestDataProvider  {
	
	@DataProvider(name="getData", parallel = true) //
	public Object[][] getData(ITestNGMethod testContext){
		return DataUtil.getData(TestUtils.getXlsReaderObject(), testContext.getMethodName());
	}
}
