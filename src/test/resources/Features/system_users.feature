Feature: System Users Management

  Scenario: Search for a user by username
    Given User is on the System Users page
    When User enters "Admin" in the username field for System Users
    And User clicks the search button for System Users
    Then System displays the search results for System Users

  Scenario: Filter users by user role
    Given User is on the System Users page
    When User selects "Admin" as the user role for System Users
    And User clicks the search button for System Users
    Then System displays the search results for System Users

  Scenario: Add a new system user
    Given User is on the System Users page
    When User clicks the add button for System Users
    And User enters "testUser" in the username field
    And User selects "ESS" as the user role
    And User selects "Enabled" as the status for System Users
    And User enters "Rahul  Das" in the employee name field for System Users
    And User enters "testPassword123" in the password field for System Users
    And User confirms "testPassword123" in the confirm password field for System Users
    And User clicks the save button for System Users

  Scenario: Delete an existing user
    Given User is on the System Users page
    When User enters "FMLName" in the username field for System Users
    And User clicks the search button for System Users
    And User selects the user "FMLName"
    And User clicks the delete button for System Users
    And User confirms the deletion for System Users
    
    