package parallel;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.intsof.factory.DriverFactory;
import com.intsof.pages.HomePage;
import com.intsof.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	String validLoggedInUserName;

	@Given("I am on the login page of MobilityX application")
	public void i_am_on_the_login_page_of_mobility_x_application() {

	}

	@Given("I have entered {string} and {string}")
	public void i_have_entered_and(String userName, String password) {

		validLoggedInUserName = userName;
		loginPage.enterUsername(userName);
		loginPage.clickNext();
		loginPage.enterPassword(password);

	}

	@When("I click on SignIn button")
	public void i_click_on_sign_in_button() {
		loginPage.clickLogin();
	}

	@Then("I navigate to the dashboard page of MobilityX application")
	public void i_navigate_to_the_dashboard_page_of_mobility_x_application() {
		Assert.assertTrue(homePage.isUserNavigatedtoHomePage());
		homePage.clickLogoutButton();
		Assert.assertTrue(loginPage.validateUsernameTextFieldPresent(),
				"User not navigated to Sign In Page having Username Field");

	}

	@Given("User is already logged in to MobilityX application with following credentials")
	public void user_is_already_logged_in_to_mobility_x_application_with_following_credentials(DataTable dataTable) {

		List<Map<String, String>> loginCredentials = dataTable.asMaps();

		loginPage.enterUsername(loginCredentials.get(0).get("UserName"));
		loginPage.clickNext();
		loginPage.enterPassword(loginCredentials.get(0).get("Password"));
		loginPage.clickLogin();
		
		Assert.assertTrue(homePage.isUserNavigatedtoHomePage(),"User not navigated to Home Page.");
		
		String actualProfileName = homePage.getProfileName().trim();
		String expectedProfileName = loginCredentials.get(0).get("UserLoginName");

		// Verifying User Profile Name on Home Page.
		Assert.assertEquals(actualProfileName, expectedProfileName, "User Profile Name did not match.");

		// Validating 'Create an authorization' button is displayed for Client User Access
		Assert.assertTrue(homePage.isCreateAnAuthorizationButtonDisplayed(),
				"Create an authorization button is not displayed for Client user access");

	}

}
