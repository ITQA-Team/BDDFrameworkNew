Feature:  PIM Module
  As an admin
  I want to test the PIM function


  Background:
    Given I am logged in as an admin
    When I navigate to the PIM module

  Scenario: Add a new employee with filling all mandatory fields(Happy path)
    And I click on "Add"
    And I enter employee details:
      | firstName | middleName | lastName | employeeId |
      | John      | B       | fulgrim     | 133333     |
    And I save the employee
    Then user will be redirected to the employee details page.



  Scenario: Add a new employee with missing mandatory fields (Unhappy Path)
    And I click on "Add"
    And I enter employee details:
      | firstName | middleName | lastName | employeeId |
      |           | B          | Fulgrim  | 133334     |
    And I save the employee
    Then an error message should appear for the missing fields



  Scenario: Search for an existing employee by employee ID(Happy Path)
    And I enter the following employee ID:
      | employeeId |
      | 0363     |
    And I click on the "Search" button
    Then the employee with ID "0363" should appear in the employee list



  Scenario: Search for a non-existent employee by employee ID (Unhappy Path)
    And I enter the following employee ID:
      | employeeId |
      | 999999     |
    And I click on the "Search" button
    Then a message indicating no record were found should be displayed



  Scenario: Update an existing employee's details
    And I enter the following employee ID:
      | employeeId |
      | 0378       |
    And I click on the "Search" button
    And I click on the Edit button
    And I enter updated  employee's details:
      | firstName | middleName | lastName |
      |Frank|dee|mcdonald|
    And I save the employee
    And I navigate to the PIM module
    And I enter the following employee ID:
      | employeeId |
      | 0378       |
    And I click on the "Search" button
    Then the employee details should be updated successfully
#

  

  Scenario: Delete an existing employee
    And I enter the following employee ID:
      | employeeId |
      | 0363    |
    And I click on the "Search" button
    And I click on the Delete button
    And I confirm the deletion
    And I enter the following employee ID:
      | employeeId |
      | 0363     |
    And I click on the "Search" button
    Then a message indicating no record were found should be displayed
#
