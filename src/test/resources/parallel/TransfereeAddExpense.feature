Feature: Transferee - Add Expense Feature
  In order to validate Add Expense Feature of an Transferee
  As a registered user
  I should be able to add a new expense in MobilityX application

  Background: User is Logged in to MobilityX application with Trasnferee User access
    Given User is already logged in to MobilityX application with following credentials
      | UserLoginName   | UserName                    | Password   | UserAccessType |
     

  Scenario: Validating Transferee User is able to Add an Expense through MobilityX application
    Given I am on "Mobility Journey Home page" of MobilityX application
    And I click on 'add expense' button to enter following details on 'Create a new Expense Form'
      | Receipt Currency | Reimbursement Account |
      | Indian Rupee     | CHECK - USD (default) |
    And I click on 'Continue' button to enter following information in Expense Form
      | Expense Report Name | Check here to mail receipts instead of uploading | Expense Category | Start Date  | End Date    | Gsa rate | NumberOfDependentTravelers12AndOlder | NumberOfDependentTravelersUnder12 | LodgingZipCodes | EmployeeTravel | ExpenseAmount | Detail     |
      | TestReport001       | Yes                                              | Lump sum payment | 14 Sep 2021 | 15 Sep 2021 |      123 |                                    1 |                                 0 |           96522 | Yes            |        270.45 | TestDetail |
    And I click on 'Continue to itemization' button to navigate to Itemize expenses page
    When I click on 'Review and Submit expense form' button on "Itemize expenses page"
    Then I should be navigated to Review expense form displaying submission success message
