Feature: Login Page Feature
  In order to validate user login
  As a registered user
  I should be able to login to Aires application

  
  Scenario Outline: Validating Login functionality with valid credential
    Given I am on the login page of MobilityX application
    And I have entered "<UserName>" and "<Password>"
    When I click on SignIn button
    Then I navigate to the dashboard page of MobilityX application

    Examples: 
      | UserName                | Password   |
      
