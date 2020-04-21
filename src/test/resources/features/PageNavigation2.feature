@smoke @lib-132 @navigation
Feature: Page navigation links

#  login as librarian
#  click on the users link
#  verify page Users

#  login as librarian
#  click on the Books link
#  verify page Books

  Background:
    Given I am on the login page
    And I login as a librarian
  @db
  Scenario: Go to users page
    When I click on link "Users" link
    Then "Users" page should be displayed


   Scenario: Go to books page
     When I click on link "Books" link
     Then "Books" page should be displayed


    Scenario: Go to dashboard page
      And I click on link "Books" link
      When I click on link "Dashboard" link
      Then "Dashboard" page should be displayed
