Feature: Basic e-commerce functionality

  As a user of the website www.cider.com
  I need to be able to look through categories and
  carry out searches to view the wanted clothing items

  Scenario: Reachability of product categories (Check at least 5 categories)
    Given I am a user of the website
    When I visit the website
    And I click on the "New In" category
    Then I should be taken to "New In" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

  Scenario: Search functionality
    Given I am a user of the website
    When I search for a product using the term "shirt"
    Then I should see the search results
    And there should be at least 5 products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product