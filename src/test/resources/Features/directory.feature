Feature: directory Management

  Scenario: Navigate to the Directory page
    Given User logs into the OrangeHRM application
    When User clicks on the Directory menu
    Then User should see the Directory page

  Scenario: Search for an employee by name
    Given User is on the Directory page
    When User enters "Ranga Akunuri" in the employee name field
    And User clicks the search button
    Then System displays results for "Ranga Akunuri"

#Feature: Search employees by location

  Scenario: Search employees by location
    Given User is on the Directory page
    When User selects "New York Sales Office" as the location
    And User clicks the search button
    Then System displays employees in "New York Sales Office"
