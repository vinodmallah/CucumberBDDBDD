package com.intsof.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.intsof.util.ElementUtil;

public class LoginPage {

	// Object Repositories
	private By userName = By.id("username::content");
	private By nextButton = By.id("nextButton");
	private By password = By.id("password::content");
	private By loginButton = By.id("loginButton");
	
	private ElementUtil elementUtil;

	public LoginPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	public void enterUsername(String name) {
		elementUtil.set(userName, name);
	}

	public void enterPassword(String pwd) {
		elementUtil.waitForVisibilityOfElement(password);
		elementUtil.set(password, pwd);
	}

	public void clickNext() {
		elementUtil.click(nextButton);
	}

	public void clickLogin() {
		elementUtil.click(loginButton);
	}

	public boolean validateUsernameTextFieldPresent() {

		try {
			elementUtil.waitForVisibilityOfElement(userName);
			return (elementUtil.isElementDisplayed(userName));
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}

	}

	public boolean validatePasswordTextFieldPresent() {
		try {
			return (elementUtil.isElementDisplayed(password));
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
