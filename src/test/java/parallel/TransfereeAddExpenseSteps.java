package parallel;

import org.testng.Assert;

import com.intsof.factory.DriverFactory;
import com.intsof.pages.HomePage;
import com.intsof.pages.TransfereeAddExpensePage;
import com.intsof.util.MyConstants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransfereeAddExpenseSteps {

	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	TransfereeAddExpensePage transfereeAddExpensePage = new TransfereeAddExpensePage(DriverFactory.getDriver());

	@Given("I am on {string} of MobilityX application")
	public void i_am_on_of_mobility_x_application(String string) {
		Assert.assertEquals(homePage.getTitle(), MyConstants.MOBILITY_JOURNEY_HOMEPAGE_TITLE,
				"Page Title did not match");
	}

	@Given("I click on {string} button to enter following details on {string}")
	public void i_click_on_button_to_enter_following_details_on(String addExpenseButton, String createNewExpenseForm, DataTable dataTable) {
		homePage.clickAddExpense();
		transfereeAddExpensePage.enterReceiptCurrencyReimAccountDetails(dataTable.asMaps());

	}
	
	@Given("I click on {string} button to enter following information in Expense Form")
	public void i_click_on_button_to_enter_following_information_in_expense_form(String continueButton, DataTable dataTable) {
		transfereeAddExpensePage.continueToExpenseForm();
		transfereeAddExpensePage.fillExpenseForm(dataTable.asMaps());
	}

	@Given("I click on {string} button to navigate to Itemize expenses page")
	public void i_click_on_button_to_navigate_to_itemize_expenses_page(String continueToItemizationButton) {
		transfereeAddExpensePage.continueToItemization();
	}
		
	

	@When("I click on {string} button on {string}")
	public void i_click_on_button_on(String reviewSubmitExpenseForm, String ItemizeExpensesPage) {

		transfereeAddExpensePage.reviewAndSubmitExpenseForm();
	}

	@Then("I should be navigated to Review expense form displaying submission success message")
	public void i_should_be_navigated_to_review_expense_form_displaying_submission_success_message() {
		String expectedSubmissionStatusMessage = MyConstants.EXPENSE_SUBMISSION_MESSAGE;
		String actualSubmissionStatusMessage = transfereeAddExpensePage.getSubmissionSuccessMessage();
		// Validating expense submission status message displayed on the home page.
		Assert.assertEquals(actualSubmissionStatusMessage, expectedSubmissionStatusMessage,
				"Expense Submission success message did not match.");
	}

}
