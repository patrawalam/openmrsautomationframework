package com.openmrs.browser;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		//System.out.println("Getting WebDriver");
		return dr.get();

	}

	public static void setWebDriver(WebDriver driver) {
		//System.out.println("Setting WebDriver");
		dr.set(driver);
	}

}
