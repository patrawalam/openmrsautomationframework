package com.openmrs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.SkipException;

import com.openmrs.browser.DriverManager;
import com.openmrs.constants.Constants;
import com.openmrs.listeners.CustomListener;

public class TestUtils {
	
	private static Properties prop = null;
	private static FileInputStream fis = null;
	private static File file  = null;
	private static Xls_Reader xls = null;

	
	public static String getValue(String value)    {
		
		if(prop==null && file==null && fis==null) {
			prop = new Properties();
			file = new File(Constants.PROJECTPROPERTIESPATH);
			
			try {
			fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return prop.getProperty(value);
	}
	
	public static Xls_Reader getXlsReaderObject () {
		
		if(xls == null) {
			xls = new Xls_Reader(Constants.DATA_XLS_PATH);
		}
		
		return xls;
	}

	
	public static void validateRunMode(Xls_Reader xls, Hashtable<String,String> data, Method methodName) {
		if(!DataUtil.isTestExecutable(xls, methodName.getName()) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			throw new SkipException(methodName.getName() + " :: Skipping the test as Runmode is N");
		}
		
	}
	
	
	/*
	 * Captures screenshot and returns the screenshot path
	 */
	public static String pullScreenshotPath()  {

		String destination=null;
	
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			try {
					destination=Constants.SCREENSHOTPATH +"\\"+CustomListener.getTestcaseName()+"\\"+ System.currentTimeMillis()+".png";
					FileUtils.copyFile(scrFile, new File(destination));
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		return destination;

	}

	/*
	 * Gives a base64 image which is used to append the screenshots in the extent report.
	 * Converting to base64 format avoids screenshots broken image if sent the extent report through email.
	 */
	public static String getBase64Image(String screenshotpath) {
		String base64 = null;
		try {
			InputStream is= new FileInputStream(screenshotpath);
			byte[] imageBytes = IOUtils.toByteArray(is);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		catch (Exception e) {

		}
		return base64;

	}

	

}
