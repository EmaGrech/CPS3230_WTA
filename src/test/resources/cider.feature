Feature: Basic e-commerce functionality

  As a user of the website www.cider.com
  I need to be able to look through categories and
  carry out searches to view the wanted clothing items

  Scenario Outline: Reachability of product categories (Check at least 5 categories)
    Given I am a user of the website
    When I visit the website
    And I click on the <category> category
    Then I should be taken to <category> category
    And the category should show at least <min> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      |category|min|
      |"New In"|5|
      |"Bestsellers"|6|
      |"Sale"|7|
      |"Clothing"|8|
      |"Swimwear"|9|

  Scenario Outline: Search functionality
    Given I am a user of the website
    When I visit the website
    And I search for a product using the term <inputText>
    Then I should see the <inputText> search results
    And there should be at least <min> products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      |inputText|min|
      |"shirt"|5|
      |"pants"|6|
      |"socks"|7|
      |"pink"|8|
      |"flannel"|9|