Feature: Basketball England User Account Registration

@Registration
  Scenario: Successful registration with valid credentials
   Given I am on the registration page
   When I fill in all the details
   And I agree to the terms and conditions
   And I should see a confirmation that I am over 18 years
   And I accept the code of ethics and conduct
   And I click on the "CONFIRM AND JOIN" button
   And I see it is redirecting to join page
   Then I should see a confirmation message

@MissingLastName
   Scenario: Missing last name
    Given I am on the registration page
    When I fill in all the details except the last name
    And I agree to the terms and conditions
    And I click on the "CONFIRM AND JOIN" button
    Then I should see an error message for missing last name

 @MismatchPassword
  Scenario: Passwords do not match
  Given I am on the registration page
  When I fill in the details with mismatched passwords
  And I agree to the terms and conditions
  And I click on the "CONFIRM AND JOIN" button
  Then I should see a message with mismatch password error

 @TermsNotAccepted
 Scenario: Terms and Conditions not accepted
  Given I am on the registration page
  When I fill in all the details
  And I do not agree with the terms and conditions
  And I click on the "CONFIRM AND JOIN" button
  Then I should see an error message for not accepting the terms


@Scenario_outline
Scenario Outline: Successful registration with valid credentials
 Given I am on the registration page
 When I fill in all the details "<firstName>", "<lastName>", "<email>" "<password>", "<confirmPassword>","<dob>"
 And I agree to the terms and conditions
 And I should see a confirmation that I am over 18 years
 And I accept the code of ethics and conduct
 And I click on the "CONFIRM AND JOIN" button
 And I see it is redirecting to join page
 Then I should see a confirmation message

Examples:
 | firstName | lastName | email              | password | confirmPassword | dob        |
 | Samika    | Buddh    | samika@example.com | sam123   | sam123          | 19/03/2001 |
 | Ajay      | Mishra   | ajay@example.com   | ajay_21  | ajay_21         | 28/01/1997 |
 | Stavya    | Buddh    | stav@esaxpme.com   | stav@67  | stav@67         | 20/04/1987 |






