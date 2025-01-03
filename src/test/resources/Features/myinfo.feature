Feature: Personal Details Functionality

  Scenario: Update personal details with valid inputs
    Given I am on the personal details page
    When I enter first name "John"
    And I enter last name "Doe"
    And I click on save button
    Then The first name and last name should be updated successfully
