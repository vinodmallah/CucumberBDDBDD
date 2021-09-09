package com.intsof.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	private WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}

	public void set(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public String get(By locator) {
		return getElement(locator).getText();
	}

	public void click(By locator) {
		getElement(locator).click();
	}

	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void waitForVisibilityOfElement(By locator) {

		wait = new WebDriverWait(driver, 20L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void waitForInvisibilityOfElement(By locator) {

		wait = new WebDriverWait(driver, 20L);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}
	
	public void waitForTestToBePresentInElementLocated(By locator, String text) {

		wait = new WebDriverWait(driver, 20L);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

	}

	public void switchToFrame(By locator) {
		driver.switchTo().frame(getElement(locator));
	}

	public void selectRadioButton(By locator) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", getElement(locator));

	}

	public void selectByVisibleText(By relocationType_Dropdown, String visibleText) {

		Select select = new Select(getElement(relocationType_Dropdown));
		select.selectByVisibleText(visibleText);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void staticWait(long waitTime) {
		
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
