package com.intsof.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.intsof.util.ElementUtil;

public class TransfereeAddExpensePage extends BasePage {

	private ElementUtil elementUtil;	

	/************ TransfereeAddExpensePage : Object Repository ***********/

	@FindBy(id = "dcefhp1:logoicon")
	private WebElement pageLogo;

	@FindBy(xpath = "//div[contains(@id,'popup-container')]//div[contains(@class,'inlineFrame')]/iframe[@title='Content']")
	private WebElement popUpIFrame;

	@FindBy(id = "expenseCurrency::content")
	private WebElement receiptCurrency_Dropdown;

	@FindBy(id = "reimAccount::content")
	private WebElement reimbursementAccount_Dropdown;

	@FindBy(xpath = "//span[contains(text(),'Continue')]/parent::a[@class='af_button_link']")
	private WebElement createExpenseContinueButton;

	@FindBy(id = "efRgn:0:formName::content")
	private WebElement expenseReportName_TextField;

	@FindBy(xpath = "//label[@for='efRgn:0:sendReceiptByMId::content']")
	private WebElement mailEmailReceipts_Checkbox;

	@FindBy(id = "efRgn:0:category_1::content")
	private WebElement expenseCategoryAndType_Dropdown;

	/*-----Meals during househunting trip------*/

	@FindBy(id = "efRgn:0:startDate_1::content")
	private WebElement startDate_TextField;

	@FindBy(id = "efRgn:0:endDate_1::content")
	private WebElement endDate_CalendarField;

	@FindBy(id = "efRgn:0:GSARate_1::content")
	private WebElement gsaRate_TextField;

	@FindBy(id = "efRgn:0:familyTravelersCount_1::content")
	private WebElement numberofDependentTravellers12YrAndOlder_TextField;

	@FindBy(id = "efRgn:0:familyTravelersCountUnder12_1::content")
	private WebElement numberofDependentTravellersUnder12Yr_TextField;

	@FindBy(id = "efRgn:0:zipCode_1::content")
	private WebElement lodgingZipCode_TextField;

	@FindBy(id = "efRgn:0:employeeTravel_1::content")
	private WebElement employeeTravel_Dropdown;

	@FindBy(id = "efRgn:0:expAmount_1::content")
	private WebElement expenseAmount_TextField;

	@FindBy(id = "efRgn:0:comment_1::content")
	private WebElement expenseDetail_TextField;

	@FindBy(id = "efRgn:0:otaefb32")
	private WebElement continueToItemization_Button;

	@FindBy(id = "efRgn:1:pglaefb5")
	private WebElement reviewAndSubmitExpenseForm_Button;

	@FindBy(xpath = "//div[@id='growls']//div[@class='growl-message']")
	private WebElement expenseSubmissionMessage;

	/************ TransfereeAddExpensePage class Constructor ***********/

	public TransfereeAddExpensePage(WebDriver driver) {
		super(driver);
		elementUtil = new ElementUtil(driver);

	}

	public void enterReceiptCurrencyReimAccountDetails(List<Map<String, String>> expenseFormMap) {

		elementUtil.waitForFrameToLoad(popUpIFrame);
		elementUtil.waitForVisibilityOfElement(popUpIFrame);
		elementUtil.switchToFrame(popUpIFrame);
		elementUtil.waitForVisibilityOfElement(receiptCurrency_Dropdown);
		elementUtil.selectByVisibleText(receiptCurrency_Dropdown, expenseFormMap.get(0).get("Receipt Currency"));
		elementUtil.selectByVisibleText(reimbursementAccount_Dropdown,
				expenseFormMap.get(0).get("Reimbursement Account"));

	}

	public void continueToExpenseForm() {
		elementUtil.click(createExpenseContinueButton);
	}

	public void fillExpenseForm(List<Map<String, String>> expenseFormValues) {

		String startDate = expenseFormValues.get(0).get("Start Date");
		String endDate = expenseFormValues.get(0).get("End Date");
		String expenseAmount = expenseFormValues.get(0).get("ExpenseAmount");
		String details = expenseFormValues.get(0).get("Detail");

		elementUtil.waitForVisibilityOfElement(expenseReportName_TextField);
		elementUtil.set(expenseReportName_TextField, expenseFormValues.get(0).get("Expense Report Name"));
		if (expenseFormValues.get(0).get("Check here to mail receipts instead of uploading").equals("Yes")) {
			elementUtil.check(mailEmailReceipts_Checkbox);
		}
		elementUtil.selectByVisibleText(expenseCategoryAndType_Dropdown,
				expenseFormValues.get(0).get("Expense Category"));

		elementUtil.set(startDate_TextField, startDate);
		elementUtil.set(endDate_CalendarField, endDate);
		elementUtil.set(expenseAmount_TextField, expenseAmount);
		elementUtil.set(expenseDetail_TextField, details);

	}

	public void continueToItemization() {
		elementUtil.click(continueToItemization_Button);
	}

	public void reviewAndSubmitExpenseForm() {
		elementUtil.waitForVisibilityOfElement(reviewAndSubmitExpenseForm_Button);
		elementUtil.click(reviewAndSubmitExpenseForm_Button);
	}

	public String getSubmissionSuccessMessage() {

		elementUtil.waitForVisibilityOfElement(expenseSubmissionMessage);
		return elementUtil.get(expenseSubmissionMessage);
	}

}
