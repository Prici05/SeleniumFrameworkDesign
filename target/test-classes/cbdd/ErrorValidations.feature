
@tag
Feature: Purchase order in ecommerce
  I want to purchase any product from ecommerce and verify the display message



  @ErrorValidations
  Scenario Outline: Checking the error vaalidation negative testcase
    Given I landed on ecommerce page
    When Log in with username <name> and password <password>
    Then verify the display message "Incorrect email or password."

    Examples: 
      | name                           | password      | productname   |
      | arockiapricilla1999@gmail.com  | Elohim@0510     | IPHONE 13 PRO |
      
