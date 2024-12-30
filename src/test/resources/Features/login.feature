Feature: Login Functionality

  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter username "Admin"
    And I enter password "admin123"
    And I click on login button
    Then I should be redirected to the homepage