@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page
  @tag2
  Scenario Outline: Positive test of submiting the order
  	
    Given Logged in with username <name> and password <password> 
    When I add product <pName> to cart
    And Checkout <pName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is to be displayed on confirmation page

    Examples: 
      | Email            | password | pName       |
      | vikas@gmail.com | Vikas123 | ZARA COAT 3 |
     
