Feature: Recruitment Vacancies Management

  Scenario: Verify vacancies search functionality
    Given User is on the Recruitment Vacancies page
    When User selects "Account Assistant" from the Job Title dropdown
    And User selects "Hasi" from the Vacancy dropdown
    And User selects "Ranga Akunuri" from the Hiring Manager dropdown
    And User selects "Active" from the Status dropdown
    And User clicks the Search button
    Then The results should display the vacancies matching the criteria
    And The test environment is cleaned up

  Scenario: Search vacancies with selected criteria
    Given User is on the Recruitment Vacancies page
    When User selects "Account Assistant" from the Job Title dropdown
    And User selects "Hasi" from the Vacancy dropdown
    And User selects "Ranga Akunuri" from the Hiring Manager dropdown
    And User selects "Active" from the Status dropdown
    And User clicks the Search button
    Then The results should display vacancies that match all the selected criteria
    And The test environment is cleaned up

  Scenario: Applying filters that do not match any records
    Given User is on the Recruitment Vacancies page
    When User applies filters with criteria that have no matching records
    Then No records should be displayed
    And The test environment is cleaned up
