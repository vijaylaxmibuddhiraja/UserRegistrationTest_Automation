Feature: Basketball England User Account Registration


  Scenario: Successful registration with valid credentials
   Given I am on the registration page
   When I fill in all the details
   And I agree to the terms and conditions
   And I click on the "CONFIRM AND JOIN" button
   Then I should see a confirmation message


