package com.intsof.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.intsof.factory.DriverFactory;
import com.intsof.util.ElementUtil;

public class HomePage {

	// Object Repositories
	private By profileName = By.cssSelector("span[id$='openUserProfileText");
	private By logoutButton = By.xpath("//span[@id='dcmjhhr:logout::text']/div");
	private By acceptCookieButton = By.id("cookiePupupButtonId");

	private By createAnAuthorizationButton = By.id("creatAuthForm");
	private By initiationSuccessMessage = By.xpath("//div[@id='growls']//div[@class='growl-message']");
	private By addExpenseButton = By.xpath("//span[@class='AddExpenseLinkTextFont'][contains(string(),'add')]/parent::div");

	private ElementUtil elementUtil;

	public HomePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}
	
	public String getTitle() {
		
		return elementUtil.getPageTitle();
		
	}

	public String getProfileName() {
		elementUtil.waitForVisibilityOfElement(profileName);
		return elementUtil.get(profileName);

	}

	public boolean isUserNavigatedtoHomePage() {

		try {
			acceptCookie();
			elementUtil.waitForInvisibilityOfElement(acceptCookieButton);			
		} catch (Exception e) {
		}

		elementUtil.waitForVisibilityOfElement(profileName);
		return elementUtil.isElementDisplayed(profileName);

	}

	public void clickLogoutButton() {

		if (elementUtil.isElementDisplayed(profileName)) {
			elementUtil.click(profileName);
			elementUtil.waitForVisibilityOfElement(logoutButton);
			elementUtil.click(logoutButton);
		}

	}

	public void acceptCookie() {
		elementUtil.waitForVisibilityOfElement(acceptCookieButton);
		elementUtil.click(acceptCookieButton);
	}

	public CreateAuthorizationPage clickCreateAnAuthorization() {

		elementUtil.waitForVisibilityOfElement(createAnAuthorizationButton);
		elementUtil.click(createAnAuthorizationButton);
		return new CreateAuthorizationPage(DriverFactory.getDriver());

	}

	public boolean isSubmissionSuccessMessageDisplayed() {

		elementUtil.waitForVisibilityOfElement(initiationSuccessMessage);
		return elementUtil.isElementDisplayed(initiationSuccessMessage);

	}

	public boolean isCreateAnAuthorizationButtonDisplayed() {
		try {
		elementUtil.waitForVisibilityOfElementNoWait(createAnAuthorizationButton);
		return elementUtil.isElementDisplayed(createAnAuthorizationButton);
		}catch(TimeoutException | NoSuchElementException e) {
			return false;
		}

	}

	public String getSubmissionSuccessMessage() {

		elementUtil.waitForVisibilityOfElement(initiationSuccessMessage);
		return elementUtil.get(initiationSuccessMessage);
	}

	public void clickAddExpense() {		
		elementUtil.click(addExpenseButton);
	}

}
