package com.intsof.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used to initialize the ThreadLocal driver on the basis of given browser
	 * @param browser
	 * @return threadLocalDriver instance
	 */
	
	public WebDriver init_driver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {			
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver());

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			threadLocalDriver.set(new FirefoxDriver());

		} else if (browser.equalsIgnoreCase("safari")) {

			threadLocalDriver.set(new SafariDriver());

		} else {
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();		
		return getDriver();

	}

	/**
	 * This method is used to get the driver with ThreadLocal
	 * @return
	 */
	public static synchronized WebDriver getDriver() {

		return threadLocalDriver.get();

	}

}
