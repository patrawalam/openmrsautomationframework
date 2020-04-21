package com.openmrs.browser;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.openmrs.constants.Constants;
import com.openmrs.utils.TestUtils;

public class Driver {

	
	public WebDriver driver=null;
	public DesiredCapabilities capability;


	private Driver() {
			String localOrRemote = TestUtils.getValue("executionOn");
			String browser=TestUtils.getValue("browser");
	try {
		if(localOrRemote.equalsIgnoreCase("local")) {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
				driver=new ChromeDriver();
				//System.out.println("ChromeDriver created");
			}
			else if(browser.equalsIgnoreCase("firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVERPATH);
				driver= new FirefoxDriver();
				//System.out.println("FirefoxDriver created");
			}
		}
		else if (localOrRemote.equalsIgnoreCase("remote")) {
			String host = "localhost";
			DesiredCapabilities dc;
					
			//If User provides values, we can change else use the same.
			if(browser.equalsIgnoreCase("firefox"))
				dc = DesiredCapabilities.firefox();
			else 
				dc = DesiredCapabilities.chrome();
					
			if(System.getProperty("HUB_HOST")!=null)
				host = System.getProperty("HUB_HOST");
			
			String URL = "http://" + host + ":4444/wd/hub";
			//dc.setCapability("name", itc.getCurrentXmlTest().getName());
			driver = new RemoteWebDriver(new URL(URL),dc);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICITWAIT, TimeUnit.SECONDS);
		
		try {
			driver.get(TestUtils.getValue("url"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		DriverManager.setWebDriver(driver);
	}

	public static void initialize() {
		if(DriverManager.getDriver()==null)
			System.out.println("Driver is Null");
			try 
		{
				System.out.println("About to create new Driver");
				new Driver();
		}
		catch(Exception e) 
		{

		}

	}

}
