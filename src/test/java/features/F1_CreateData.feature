Feature: Create Data feature
  I want to use this template for my feature file

  Scenario: Login with valid info
    Given I am on the URL "http://demo.guru99.com/v4/"
    When I fill in Username with "mngr340276" 
    And I fill in Password with "amApyzU"
    And I click on the "btnLogin" button 
    Then I should see "Welcome To Manager's Page of Guru99 Bank" message

