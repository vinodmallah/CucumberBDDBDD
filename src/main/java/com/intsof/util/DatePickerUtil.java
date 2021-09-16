package com.intsof.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.intsof.factory.DriverFactory;

public class DatePickerUtil {

	private WebDriver driver;
	private ElementUtil elementUtil = new ElementUtil(DriverFactory.getDriver());

	/******************* End Date *********************/

	private By endDateTableID = By.id("efRgn:0:endDate_1::pop::cd");
	private By monthYearNavigator = By.id("efRgn:0:endDate_1::pop::cd::compactNavigator");
	private By nextYearIncrement = By.id("efRgn:0:endDate_1::pop::cd::ys::increment");
	private By previousYearDecrement = By.id("efRgn:0:endDate_1::pop::cd::ys::decrement");
	private By defaultYearValue = By.id("efRgn:0:endDate_1::pop::cd::ys::content");
	private By defaultMonthValue = By.id("efRgn:0:endDate_1::pop::cd::mSel::content");
	private By previousMonthValue = By.id("efRgn:0:endDate_1::pop::cd::pm");
	private By nextMonthValue = By.id("efRgn:0:endDate_1::pop::cd::nm");
	
	
	public DatePickerUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void setDate(String endDateString, String fieldName) {

		String calendarIcon = "//input[@placeholder='" + fieldName
				+ "']/following-sibling::a[@class='af_inputDate_launch-icon-style'][@title='Select Date']";
		By calendarIcon_Button = By.xpath(calendarIcon);

		String[] dateArray = endDateString.split("-");		
		
		String year = dateArray[0].trim();
		String month = dateArray[1].trim();
		String day = dateArray[2].trim();

		if (isDateValid(endDateString)) {			
			elementUtil.click(calendarIcon_Button);
			elementUtil.waitForVisibilityOfElement(nextYearIncrement);
			selectYearValue(year);
			elementUtil.staticWait(1000L);
			selectMonthValue(month);
			elementUtil.staticWait(1000L);
			
			
		}

		

	}

	private void selectMonthValue(String month) {

		int requiredMonth = Integer.parseInt(month);
		String defaultMonth = (elementUtil.getattribute(defaultMonthValue,"title"));
		
		int defaultMonthIntValue = Month.valueOf(defaultMonth.toUpperCase()).getValue();
		
		
		while(defaultMonthIntValue!=requiredMonth) {			
			
			if(defaultMonthIntValue==requiredMonth) {
				break;
			}			
			else if(requiredMonth>defaultMonthIntValue) {				
				elementUtil.click(nextMonthValue);				
			}
			else if (requiredMonth<defaultMonthIntValue) {
				elementUtil.click(previousMonthValue);
			}
			
			defaultMonthIntValue = Integer.parseInt(elementUtil.getattribute(defaultMonthValue,"value"));
		}
		
		
	}

	private void selectYearValue(String year) {

		int requiredYearInt = Integer.parseInt(year);
		int defaultYearIntValue = Integer.parseInt(elementUtil.getattribute(defaultYearValue,"value"));		
		
		while(defaultYearIntValue!=requiredYearInt) {			
			
			if(defaultYearIntValue==requiredYearInt) {
				break;
			}			
			else if(requiredYearInt>defaultYearIntValue) {				
				elementUtil.click(nextYearIncrement);				
			}
			else if (requiredYearInt<defaultYearIntValue) {
				elementUtil.click(previousYearDecrement);
			}
			
			defaultYearIntValue = Integer.parseInt(elementUtil.getattribute(defaultYearValue,"value"));
		}
		
	}

	public static boolean isDateValid(final String date) {

		boolean valid = false;

		try {

			// ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
					.withResolverStyle(ResolverStyle.STRICT);

			LocalDate.parse(date, dtf);
			valid = true;

		} catch (DateTimeParseException e) {
			valid = false;
		}

		return valid;
	}

}
