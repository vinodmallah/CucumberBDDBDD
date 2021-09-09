package parallel;

import org.testng.Assert;

import com.intsof.factory.DriverFactory;
import com.intsof.pages.CreateAuthorizationPage;
import com.intsof.pages.HomePage;
import com.intsof.util.MyConstants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateAuthorizationSteps {

	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private CreateAuthorizationPage createAuthorizationPage;

	@Given("I am on home page of MobilityX application")
	public void i_am_on_home_page_of_mobility_x_application() {

		// Validating 'Create an authorization' button is displayed for Client User
		// access
		Assert.assertTrue(homePage.isCreateAnAuthorizationButtonDisplayed(),
				"Create an authorization button is not displayed for Client user access");
	}

	@Given("I click on Create an authorization button to create new initiation, enter following details and click on continue")
	public void i_click_on_create_an_authorization_button_to_create_new_initiation_enter_following_details_and_click_on_continue(io.cucumber.datatable.DataTable dataTable) {

		createAuthorizationPage = homePage.clickCreateAnAuthorization();
		createAuthorizationPage.inputNewAuthorizationInformationAndContinue(dataTable.asMaps());

	}

	@Given("I create new transfer or assignment authorization by entering following information on initiation form")
	public void i_create_new_transfer_or_assignment_authorization_by_entering_following_information_on_initiation_form(
			DataTable dataTable) {

		createAuthorizationPage.enterInitiationFormDetails(dataTable.asMaps());
	}

	@When("I click on Submit to Aires button")
	public void i_click_on_submit_to_aires_button() {
		createAuthorizationPage.submitToAires();
	}

	@Then("I should navigate to the dashboard page of MobilityX application displaying submission success message")
	public void i_should_navigate_to_the_dashboard_page_of_mobility_x_application_displaying_submission_success_message() {
		
		String newInitiationName = createAuthorizationPage.getInitiation_FirstName() + " " + createAuthorizationPage.getInitiation_LastName();
		String expectedSubmissionStatusMessage = MyConstants.INITIATION_SUBMISSION_SUCCESS_MESSAGE; 
		String actualSubmissionStatusMessage = homePage.getSubmissionSuccessMessage();
		expectedSubmissionStatusMessage = expectedSubmissionStatusMessage.replace("#", newInitiationName.trim());
		
		//Validating initiation submission status message displayed on the home page. 
		Assert.assertEquals(actualSubmissionStatusMessage, expectedSubmissionStatusMessage, "Initiation Submission success message did not match.");
	}

}
