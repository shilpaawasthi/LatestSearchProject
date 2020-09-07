Feature: For Search and add product to the basket
  
  As a User 
  I want to Verify that when a user searches for a valid product and adds to the basket 
   So product should be available in the basket.

  @SearchAndAddProduct @UserStory("SA_STC_JUL")
  Scenario Outline: Title of your scenario
    Given that user is on the search page
    When the user searches for "<product>"
    And adds product to the basket
    Then the added "<product>" should be available in the basket

    Examples: 
      | product |
      | battery |
