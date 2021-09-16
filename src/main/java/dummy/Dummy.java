package dummy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy {

	public static void main(String[] args) {

		String endDateString = "30 Sep 2021";
		String[] dateArray = endDateString.split("\\s");

		for (String a : dateArray) {
			System.out.println(a);
		}

		if (isDateValid(endDateString)) {
			System.out.println("Provided date : " + endDateString + " is a valid date");
		} else {
			System.out.println("Provided date : " + endDateString + " is a Invalid date");
		}

		/*
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(); driver.manage().window().maximize();
		 * //driver.manage().deleteAllCookies();
		 * 
		 * driver.get("");
		 * 
		 * driver.findElement(By.id("username::content")).sendKeys(
		 * "rohit.sharma@intsof.com"); driver.findElement(By.id("nextButton")).click();
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver,10L);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
		 * "password::content")));
		 * 
		 * driver.findElement(By.id("password::content")).sendKeys("relonetng1");
		 * driver.findElement(By.id("loginButton")).click();
		 * 
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(
		 * "programActivityLoader")));
		 * driver.findElement(By.id("cookiePupupButtonId")).click();
		 * 
		 * driver.findElement(By.id("creatAuthForm")).click();
		 */

	}

	public static boolean isDateValid(final String date) {

		boolean valid = false;

		try {
			
			DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
			DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			LocalDateTime datetime = LocalDateTime.parse(date, oldPattern);
//			String output = datetime.format(newPattern);
//			// ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
//					.withResolverStyle(ResolverStyle.STRICT);
//
//			LocalDate.parse(output, dtf);
//			valid = true;

		} catch (DateTimeParseException e) {
			
			e.printStackTrace();
			valid = false;
		}

		return valid;
	}

}
