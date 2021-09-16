Feature: Create Authorization Feature
  In order to validate Create Authorization of an Assignment
  As a registered user
  I should be able to create a new transfer or assignment in Aires application

  Background: User is Logged in to MobilityX application with Client access
    Given User is already logged in to MobilityX application with following credentials
      | UserLoginName | UserName                | Password   | UserAccessType |
      |   |  |  | Client         |

  Scenario: Validating create new transfer or assignment in Aires application
    Given I am on home page of MobilityX application
    And I click on Create an authorization button to create new initiation, I enter following details and click on continue
      | FirstName | LastName |
      | Rakesh    | Narayan  |
    And I create new transfer or assignment authorization by entering following information on initiation form
      | AuthorizationType         | RelocationPolicy | OriginCity  | OriginState | OriginCountry | DestinationCity | DestinationState | DestinationCountry | HomeStatus | AssignmentType | AuthorizedBy |
      | Pre-Acceptance Initiation | Domestic Policy  | Los Angeles | California  | USA           | Huntsville      | Alabama          | USA                | Renter     | Commuter       | Vinod Mallah |
    When I click on Submit to Aires button
    Then I should navigate to the dashboard page of MobilityX application displaying submission success message
