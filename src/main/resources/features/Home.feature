@Default
Feature: TouchPoint Home Page

  @Home @SmokeTest
  Scenario: Verify Home page
    Given I am navigate to "https://www.google.co.nz/"
    Then I should see sign-in in header section right
    When I search for "Maui"
    Then I should see "https://www.maui-rentals.com" results
    Given I am navigate to "https://www.google.co.nz/"
    When I search for "Brtiz"
    Then I should see "https://www.britz.com" results
    

 