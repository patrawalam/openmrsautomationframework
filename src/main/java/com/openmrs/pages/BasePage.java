package com.openmrs.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.openmrs.browser.DriverManager;
import com.openmrs.constants.Constants;
import com.openmrs.reports.LogStatus;
import com.openmrs.utils.TestUtils;

public class BasePage {

	private Page_TopMenu topMenu;

	protected BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		topMenu = new Page_TopMenu();
	}

	public Page_TopMenu getMenu() {
		return topMenu;
	}

	public void reportFailure(String failureMessage) {
		LogStatus.fail(failureMessage);
		LogStatus.takeScreenShot();
		Assert.fail(failureMessage);
	}

	public static void click(WebElement element) {
		explicitlyWait(element);
		if(TestUtils.getValue("highlightElement").equalsIgnoreCase("yes"))
			highlightElement(element);
		element.click();
		LogStatus.pass("Clicking is successfull on '/" + element + "'/");
	}

	public static void switchToNewWindow() {
		String parentWinHandle = DriverManager.getDriver().getWindowHandle();
		Set<String> winHandles = DriverManager.getDriver().getWindowHandles();
		for (String temp : winHandles) {
			if (!temp.equalsIgnoreCase(parentWinHandle)) {
				DriverManager.getDriver().switchTo().window(temp);
			}
		}
	}

	public static void switchToFrame(WebElement el) {
		DriverManager.getDriver().switchTo().frame(el);
	}

	public static void selectByValue(WebElement element, String text) {
		new Select(element).selectByValue(text);
	}

	public static void selectByVisibleText(WebElement element, String text) {
		new Select(element).selectByVisibleText(text);
		LogStatus.pass("\'" + text + "\'" + " is selected in the " + element);
	}

	public static void click(By by) {
		click(DriverManager.getDriver().findElement(by));
	}

	private static void explicitlyWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void explicitlyWaitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void clear(WebElement element) {
		element.clear();
	}

	public static void sendkeys(WebElement element, String text) {
		if(TestUtils.getValue("highlightElement").equalsIgnoreCase("yes"))
			highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass("\'" + text + "\'" + " is entered in to the " + element);
	}

	public static void sendkeys(By by, String text) {
		sendkeys(DriverManager.getDriver().findElement(by), text);
	}

	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}

	public static void moveToElement(By by) {
		moveToElement(DriverManager.getDriver().findElement(by));
	}

	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public boolean isElementPresent(By ele) {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Constants.EXPLICITWAIT);
		try {
			wait.until(ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(ele)));

		} catch (Exception e) {
			return flag;
		}
		if (DriverManager.getDriver().findElements(ele).size() > 0)
			return true;
		else
			return flag;
	}

}
