Feature: Add Employee in PIM Module
  As an admin
  I want to add a new employee
  So that they are listed in the employee directory

  Scenario: Add a new employee
    Given I am logged in as an admin
    When I navigate to the PIM module
    And I click on "Add"
    And I enter employee details:
      | firstName | middleName | lastName | employeeId |
      | John      | A.         | Doe      | 12345      |
    And I save the employee
    #Then Then the new employee with ID {12345} should be visible in the employee list



  Scenario: Search for an employee by employee ID
    Given I am logged in as an admin
    When I navigate to the PIM module
    And I enter the following employee ID:
      | employeeId |
      | 12345      |
    And I click on the "Search" button
    Then the employee with ID "<employeeId>" should appear in the employee list

