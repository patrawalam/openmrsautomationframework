package roughwork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.openmrs.browser.DriverManager;

public class ChromeDebugger {

	static WebDriver driver;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9014");
		driver = new ChromeDriver(options);
		
		
		//driver.get("https://dev60329.service-now.com/");
		/*
		WebElement iFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iFrame);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Abcd1234$");
		driver.findElement(By.id("sysverb_login")).click();
		*/
		
		
		
		highlightElement(driver.findElement(By.xpath("//a[contains(@href,'coreapps.findPatient')]")));
	}
	
	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
}
