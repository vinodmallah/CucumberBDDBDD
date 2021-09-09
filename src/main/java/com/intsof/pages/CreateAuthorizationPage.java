package com.intsof.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.intsof.util.ElementUtil;

public class CreateAuthorizationPage {

	private ElementUtil elementUtil;
	private String initiation_FirstName;
	private String initiation_LastName;
	

	/************ CreateAuthorizationPage : Object Repository ***********/
	

	private By pleaseWaitMessage = By.xpath("//span[contains(text(),'Please wait')]");
	private By createAuthorizationIFrame = By.xpath(
			"//div[contains(@id,'popup-container')]//div[contains(@class,'inlineFrame')]/iframe[@title='Content']");
	private By createNewAuthorizationText = By.xpath("//span[contains(string(),'Create a new authorization')]");
	private By manuallyInputEmpInfo_Radio = By.xpath(
			"//label[contains(text(),'Manually input employee information')]/preceding-sibling::input[@type='radio']");
	private By createAuthContinueButton = By
			.xpath("//span[contains(text(),'Continue')]/parent::a[@class='af_button_link']");
	private By firstNameNewAuthorization_InputField = By.id("fname::content");
	private By lastNameNewAuthorization_InputField = By.id("lname::content");
	private By newTransferAssignmentSelection = By.xpath("//span[contains(text(),'A new transfer or assignment')]");
	private By employeeInformationHeader = By
			.xpath("//span[contains(string(),'Employee Information')][@class='RXHeaderText af_outputLabel']");
	private By authorizationType_RadioButton = By.id("aRegion:0:authorizationType101::content");
	private By relocationType_Dropdown = By.id("aRegion:0:relocationPolicy::content");
	private By originCity_TextField = By.id("aRegion:0:originCity::content");
	private By originState_Dropdown = By.id("aRegion:0:originState::content");
	private By originCountry_Dropdown = By.id("aRegion:0:originCountry::content");
	private By destinatioCity_TextField = By.id("aRegion:0:destCity::content");
	private By destinationState_Dropdown = By.id("aRegion:0:destinationState::content");
	private By destinationCountry_Dropdown = By.id("aRegion:0:destCountry::content");
	private By homeStatus_RadioButton = By.id("aRegion:0:homeStatus101::content");
	private By assignmentType_Dropdown = By.id("aRegion:0:assignmentType::content");
	private By authorizedBy_TextField = By.id("aRegion:0:authorizedBy::content");
	private By submitToAires_Button = By.xpath("//span[contains(text(),'Submit to Aires')]");
	

	/************ CreateAuthorizationPage class Constructor ***********/

	public CreateAuthorizationPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	/********* Getter & Setter Methods for Initiation FirstName & LastName ********/
	 

	public String getInitiation_FirstName() {
		return initiation_FirstName;
	}

	public void setInitiation_FirstName(String initiation_FirstName) {
		this.initiation_FirstName = initiation_FirstName;
	}

	public String getInitiation_LastName() {
		return initiation_LastName;
	}

	public void setInitiation_LastName(String initiation_LastName) {
		this.initiation_LastName = initiation_LastName;
	}

	/**
	 * This method is used to create and authorization and select Manually input
	 * employee information Radio Button
	 * 
	 * @param list
	 */

	public void inputNewAuthorizationInformationAndContinue(List<Map<String, String>> newEmployeeName) {

		elementUtil.staticWait(5000L);
		elementUtil.waitForVisibilityOfElement(createAuthorizationIFrame);
		elementUtil.switchToFrame(createAuthorizationIFrame);
		elementUtil.waitForVisibilityOfElement(createNewAuthorizationText);
		elementUtil.selectRadioButton(manuallyInputEmpInfo_Radio);
		elementUtil.click(createAuthContinueButton);
		elementUtil.waitForVisibilityOfElement(firstNameNewAuthorization_InputField);
		elementUtil.set(firstNameNewAuthorization_InputField, newEmployeeName.get(0).get("FirstName"));
		setInitiation_FirstName(newEmployeeName.get(0).get("FirstName"));
		elementUtil.set(lastNameNewAuthorization_InputField, newEmployeeName.get(0).get("LastName"));
		setInitiation_LastName(newEmployeeName.get(0).get("LastName"));
		elementUtil.click(createAuthContinueButton);

	}

	/**
	 * This method creates a new transfer or assignment and enter employee
	 * information
	 * 
	 * @param employeeDetails
	 */

	public void enterInitiationFormDetails(List<Map<String, String>> employeeDetails) {

		elementUtil.waitForVisibilityOfElement(newTransferAssignmentSelection);
		elementUtil.click(newTransferAssignmentSelection);

		try {
			elementUtil.waitForInvisibilityOfElement(pleaseWaitMessage);
		} catch (Exception e) {
			System.out.println("Exception in PLease Wait......");
		}

		elementUtil.waitForVisibilityOfElement(employeeInformationHeader);
		elementUtil.selectRadioButton(authorizationType_RadioButton);
		elementUtil.selectByVisibleText(relocationType_Dropdown, employeeDetails.get(0).get("RelocationPolicy"));
		elementUtil.set(originCity_TextField, employeeDetails.get(0).get("OriginCity"));
		elementUtil.selectByVisibleText(originState_Dropdown, employeeDetails.get(0).get("OriginState"));
		elementUtil.selectByVisibleText(originCountry_Dropdown, employeeDetails.get(0).get("OriginCountry"));
		elementUtil.set(destinatioCity_TextField, employeeDetails.get(0).get("DestinationCity"));
		elementUtil.selectByVisibleText(destinationState_Dropdown, employeeDetails.get(0).get("DestinationState"));
		elementUtil.selectByVisibleText(destinationCountry_Dropdown, employeeDetails.get(0).get("DestinationCountry"));
		elementUtil.selectRadioButton(homeStatus_RadioButton);
		elementUtil.waitForVisibilityOfElement(assignmentType_Dropdown);
		elementUtil.selectByVisibleText(assignmentType_Dropdown, employeeDetails.get(0).get("AssignmentType"));
		elementUtil.set(authorizedBy_TextField, employeeDetails.get(0).get("AuthorizedBy"));

	}

	public void submitToAires() {

		elementUtil.switchToDefaultContent();
		elementUtil.click(submitToAires_Button);

	}

}
