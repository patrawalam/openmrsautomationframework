package com.openmrs.constants;

public class Constants {

	//Extent Report Constants
	public static final String EXTENTREPORTPATH=System.getProperty("user.dir")+"\\Reports\\ExtentReports\\index.html"; 
	public static final String EXTENTCONFIGFILEPATH=System.getProperty("user.dir")+"\\src\\test\\resources\\extentreport.xml";
	
	//Project Properties Constants
	public static final String PROJECTPROPERTIESPATH=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
	public static final Integer IMPLICITWAIT=10;
	public static final Integer EXPLICITWAIT=30;
	public static final String SCREENSHOTPATH=System.getProperty("user.dir")+"\\Screenshots\\";
	public static final String TESTDATAPATH=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx";
	
	public static final String CHROMEDRIVERPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe";
	public static final String GECKODRIVERPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\geckodriver.exe";
	public static final String EXCELPATH=System.getProperty("user.dir")+"\\src\\test\\resources";
	public static final String EXCELREPORT_FOLDER = System.getProperty("user.dir") + "//Reports//ExcelReports//"; 
	public static final String EXCELREPORT_PATH =	"//Reports.xlsx";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"//src//test//resources//TestData.xlsx";
	
	//Properties File Constants
	public static final String BROWSER = "browser";
	public static final String URL = "url";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	//Excel Constants
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
}
