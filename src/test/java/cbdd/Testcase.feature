
@tag
Feature: Purchase order in ecommerce
  I want to purchase any product from ecommerce and verify the display message
  
Background:
Given I landed on ecommerce page


  @Regression
  Scenario Outline: Submitting the order positive testcase
    Given Log in with username <name> and password <password>
    When Add the product items <productname> to cart 
    And checkout the product item <productname> and submit the order
    Then I verify the display message "THANKYOU FOR THE ORDER."

    Examples: 
      | name                           | password      | productname   |
      | arockiapricilla1999@gmail.com  | Elohim@05     | IPHONE 13 PRO |
      
