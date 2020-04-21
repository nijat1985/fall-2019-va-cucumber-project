@show_records @smoke @regression @lib-7031
Feature: User table search and sort

  Background:
    Given I access Users page as a librarian

  Scenario: Search by valid email
    When I search for any valid email
    Then the users table must display 1 records

  Scenario: Search by invalid email
    When I search for any invalid email
    Then the users table must display message "No data available in table"

  Scenario: default sort User Management
    Then users table should be sorted by "User ID" in "ascending" order
    When I click on the "User ID" column
    Then users table should be sorted by "User ID" in "descending" order
    When I click on the "Email" column
    Then users table should be sorted by "User ID" in "ascending" order
    When I click on the "Email" column
    Then users table should be sorted by "User ID" in "descending" order
