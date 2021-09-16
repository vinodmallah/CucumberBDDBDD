package com.intsof.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	
	public void waitForVisibilityOfElementNoWait(By locator) {

		wait = new WebDriverWait(driver, 2L);
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

	public void selectByVisibleText(By dropdown, String visibleText) {

		Select select = new Select(getElement(dropdown));
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

	public void check(By checkbox) {
		
		if(!getElement(checkbox).isSelected()) {
			getElement(checkbox).click();
		}		
		
	}

	public void clickAndWait(By locator) {	
		waitForElementToBeRefreshed(locator);		
		getElement(locator).click();		
	}

	public void waitForElementToBeRefreshed(By locator) {
		wait = new WebDriverWait(driver, 15L);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));			
	}

	public void waitForFrameToLoad(By locator) {		
		wait = new WebDriverWait(driver, 15L);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public String getattribute(By locator, String value) {
		
		return getElement(locator).getAttribute(value);
		
	}

	public void checkIfEmpty(By field, String enteredValue) {
		
		getElement(field).sendKeys(Keys.TAB);
		
		if((getElement(field).getText()).equals(enteredValue))
			return;
		else {			
			getElement(field).clear();	
			getElement(field).sendKeys(enteredValue);
		}
		
	}

	public void waitForFrameToLoad(WebElement element) {
		
		wait = new WebDriverWait(driver, 15L);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		
	}

	public void waitForVisibilityOfElement(WebElement element) {
		
		wait = new WebDriverWait(driver, 20L);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
				
	}

	public void selectByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);		
	}

	public void click(WebElement element) {		
		element.click();
	}

	public void set(WebElement element, String value) {
		element.sendKeys(value);		
	}

	public void check(WebElement element) {
		
		if(!element.isSelected()) {
			element.click();
		}	
	}

	public void clickAndWait(WebElement element) {
		waitForElementToBeRefreshed(element);		
		element.click();
		
	}

	private void waitForElementToBeRefreshed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15L);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));		
	}

	public String get(WebElement element) {		
		return element.getText();
	}

	public String getPageTitle() {
		
		return driver.getTitle();
	}

	
	

}
