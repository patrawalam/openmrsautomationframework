package com.openmrs.listeners;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.openmrs.constants.Constants;
import com.openmrs.reports.ExtentManager;
import com.openmrs.reports.ExtentReport;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;
import com.openmrs.utils.Xls_Reader;

public class CustomListener extends TestListenerAdapter implements ISuiteListener, ITestListener {
	
	XSSFWorkbook wb = null;
	XSSFSheet ws = null;
	FileOutputStream out = null;
	Hashtable<String, String> resultTable = null;
	String resultFolderName;
	ArrayList<String> keys;

	private static String TestcaseName;

	

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}
	
	public void onTestStart(ITestResult result) {
		TestcaseName =result.getMethod().getDescription();
		
		setTestcaseName(TestcaseName);
		ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseName));
		LogStatus.pass(TestcaseName+ " is started successfully");
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		// resultTable.put(tr.getName(), "Passed");
		report(tr.getName(), "Passed");
		LogStatus.pass(tr.getMethod().getDescription()+ " test case is passed");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// resultTable.put(tr.getName(), tr.getThrowable().getMessage());
		report(tr.getName(), tr.getThrowable().getMessage());
		LogStatus.fail(tr.getMethod().getDescription()+ " is failed");
		LogStatus.fail(tr.getThrowable().toString());
		LogStatus.fail("Failed",TestUtils.pullScreenshotPath());
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// resultTable.put(tr.getName(), tr.getThrowable().getMessage());
		report(tr.getName(), tr.getThrowable().getMessage());
		LogStatus.skip(tr.getMethod().getDescription()+ " is skipped");
		
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onStart(ISuite suite) {
		// System.out.println("Suite name start :: " + suite.getName());
		File f = null;

		if (resultTable == null) {
			resultTable = new Hashtable<String, String>();
			keys = new ArrayList<String>();
		}

		if (resultFolderName == null) {
			Date d = new Date();
			resultFolderName = d.toString().replaceAll(":", "_").replaceAll(" ", "_");

			File reportsDirPresent = new File(Constants.EXCELREPORT_FOLDER);

			if (!reportsDirPresent.exists()) {
				f = new File(Constants.EXCELREPORT_FOLDER);
				f.mkdir();
			}

			f = new File(Constants.EXCELREPORT_FOLDER + resultFolderName);
			f.mkdir();
		}

		// Create Blank workbook
		 wb = new XSSFWorkbook();
		 ws = wb.createSheet("Sheet1");
		 try {
		// Create file system using specific name
		out = new FileOutputStream(new File(Constants.EXCELREPORT_FOLDER + resultFolderName + Constants.EXCELREPORT_PATH));

		// write operation workbook using file out object
		wb.write(out);
		out.close();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }

	}

	public void onFinish(ISuite suite) {
		// System.out.println("Suite name finish :: " + suite.getName());
		System.out.println(resultTable);
		Xls_Reader xls = null;
		if (resultTable != null) {

			xls = new Xls_Reader(Constants.EXCELREPORT_FOLDER + resultFolderName + Constants.EXCELREPORT_PATH);
			xls.removeSheet("Sheet1");
			xls.addSheet(suite.getName());
			xls.setCellData(suite.getName(), 0, 1, "Test Cases");
			xls.setCellData(suite.getName(), 1, 1, "Result");

			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = resultTable.get(key);

				xls.setCellData(suite.getName(), 0, i + 2, key);
				xls.setCellData(suite.getName(), 1, i + 2, value);
			}
		}
		xls = null;
		resultTable = null;
		keys = null;
		wb=null;
		ws=null;
	}

	public void report(String testName, String message) {
		int iteration_number = 1;
		while (resultTable.containsKey(testName + " Iteration " + iteration_number))
			iteration_number++;

		keys.add(testName + " Iteration " + iteration_number);
		resultTable.put(testName + " Iteration " + iteration_number, message);
	}
	
	
	
	/*
	 * Implements ITestListener specifically for ExtentReport.
	 * All the methods below are implemented for ExtentReport 
	 */
	
	
}
