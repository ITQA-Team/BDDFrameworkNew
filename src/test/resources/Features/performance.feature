Feature:  Employee Reviews Management


 Scenario: Search for an employee review by employee name
  Given User is on the Employee Reviews page
  When User enters "Timothy Lewis Amiano" in the Employee Name field
   And User clicks the Search button for Employee Reviews
  Then System displays the search results for Employee Reviews


  Scenario: Search for an existing performance review (Happy Path)
    Given User is on the Employee Reviews page
   When  User selects "IT Manager" from the Job Title dropdown
 And User clicks the Search button for Employee Reviews
    Then System displays the search results for Employee Reviews

   Scenario: Search for Performance Reviews by Review Status
    Given User is on the Employee Reviews page
   When User selects "Activated" from the Review Status dropdown
   And User clicks the Search button for Employee Reviews
    Then System displays the search results for Employee Reviews


  Scenario: Reset the search form
    Given User is on the Employee Reviews page
    When User enters "Timothy Lewis Amiano" in the Employee Name field
    And User selects "IT Manager" from the Job Title dropdown
    And User selects "Activated" from the Review Status dropdown
    And User clicks the Reset button on the search form
    Then All fields in the search form are cleared to their default values



