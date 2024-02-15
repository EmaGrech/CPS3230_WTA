/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CiderTests
{
    WebDriver driver;
    CiderObject CiderObject;

    @BeforeEach
    public void setup()
    {
        //driver setup
        System.setProperty("webdriver.chrome.driver", "C:/Users/emagr/webtesting/chromedriver.exe/");
        driver = new ChromeDriver();

        //object file setup
        CiderObject = new CiderObject(driver);

        //accessing website
        driver.get("https://www.shopcider.com");
    }

    @AfterEach
    public void teardown()
    {
        driver.quit();
    }

    //Tests if website was accessed correctly
    @Test
    public void accessWebsite()
    {
        String title = driver.getTitle();
        Assertions.assertEquals("Your Closet's Happy Hour - Women's Fashion Clothing | Cider", title);
    }

    //Tests if correct category is redirected to
    @Test
    public void accessSpecifiedCategory()
    {
        //Categories being tested are those that redirect to a product list
        //Some options are not categories or only provide a new hover menu

        //Choose category:
        //New In, Bestsellers, Sale, Clothing,
        //Swimwear, Curve & Plus, Acc & Shoes
        String category = "Sale";
        CiderObject.goToCategory(category);

        //Get expected new page title
        String expectedPageName = CiderObject.expectedCategoryPageName(category);

        //Get actual new page title
        String actualPageName = CiderObject.actualPageName();

        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    //To test if more than the minimum number products are being output
    @Test
    public void minimumProductsAreMoreThan_CategorySelection()
    {
        //Set minimum
        int minimum = 5;

        //Going to specific Category
        CiderObject.goToCategory("Sale");

        //Make sure products are first loaded
        CiderObject.productsLoaded();

        //Find all instances of products in the product list
        List<WebElement> products = driver.findElements(By.className("product-list-item-box"));

        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > minimum);
    }

    //To test if selecting the first product in the list takes it to its specific details page
    @Test
    public void accessSpecifiedProduct()
    {
        //Going to specific Category
        CiderObject.goToCategory("Sale");

        //Make sure products are first loaded
        CiderObject.productsLoaded();

        //Locating first result
        WebElement firstProduct = driver.findElement(By.className("product-item"));

        //Get first result name
        WebElement expectedFirstProduct = firstProduct.findElement(By.className("product-item-name"));
        String expectedFirstProductName = expectedFirstProduct.getText();

        //Accessing first result's details page
        WebElement firstProductLink = firstProduct.findElement(By.className("cider-link"));
        firstProductLink.click();

        //Get name of output result details
        WebElement actualFirstProduct = driver.findElement(By.className("product-detail-title"));
        String actualFirstProductName = actualFirstProduct.getText();

        Assertions.assertEquals(expectedFirstProductName, actualFirstProductName);
    }

    //To test if using the search function works
    @Test
    public void successfulSearchResults()
    {
        //using the search bar
        String inputText = "shirt";
        CiderObject.usingTheSearchBar(inputText);

        //getting the new page name
        String actualPageName = CiderObject.actualPageName();

        Assertions.assertEquals("Results for\n“" + inputText + "”", actualPageName);
    }

    //To test if search results are more than a specified minimum
    @Test
    public void minimumProductsAreMoreThan_SearchResult()
    {
        //Set minimum
        int minimum = 5;

        //using the search bar
        String inputText = "shirt";
        CiderObject.usingTheSearchBar(inputText);

        //Find all instances of products in the product list
        List<WebElement> products = driver.findElements(By.className("product-list-item-box"));

        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > minimum);
    }
}*/


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CiderTests
{
    WebDriver driver;
    CiderObject CiderObject;

    private String expectedFirstProductName;

    private String inputText;

    private List<WebElement> products;

    @BeforeEach
    public void setup()
    {
        //driver setup
        System.setProperty("webdriver.chrome.driver", "C:/Users/emagr/webtesting/chromedriver.exe/");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown()
    {
        driver.quit();
    }

    //SCENARIO 1//
    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite()
    {
        CiderObject = new CiderObject(driver);
    }

    @When("I visit the website")
    public void iVisitTheNewsWebsite()
    {
        driver.get("https://www.shopcider.com");
    }

    @And("I click on the {string} category")
    public void iClickOnTheCategory(String category)
    {
        //Categories being tested are those that redirect to a product list
        //Some options are not categories or only provide a new hover menu

        //Choose category:
        //New In, Bestsellers, Sale, Clothing,
        //Swimwear, Curve & Plus, Acc & Shoes
        CiderObject.goToCategory(category);
    }

    @Then("I should be taken to {string} category")
    public void iShouldBeTakenToCategory(String category)
    {
        //Get expected new page title
        String expectedPageName = CiderObject.expectedCategoryPageName(category);

        //Get actual new page title
        String actualPageName = CiderObject.actualPageName();

        Assertions.assertEquals(expectedPageName, actualPageName);
    }


    @And("the category should show at least {int} products")
    public void theCategoryShouldShowAtLeastProducts(int min)
    {
        //Make sure products are first loaded
        CiderObject.productsLoaded();

        //Find all instances of products in the product list
        List<WebElement> products = driver.findElements(By.className("product-list-item-box"));

        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > min);
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults()
    {
        WebElement firstProduct = driver.findElement(By.className("product-item"));

        //Get first result name
        WebElement expectedFirstProduct = firstProduct.findElement(By.className("product-item-name"));
        expectedFirstProductName = expectedFirstProduct.getText();

        //Accessing first result's details page
        WebElement firstProductLink = firstProduct.findElement(By.className("cider-link"));
        firstProductLink.click();
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct()
    {
        WebElement actualFirstProduct = driver.findElement(By.className("product-detail-title"));
        String actualFirstProductName = actualFirstProduct.getText();

        Assertions.assertEquals(expectedFirstProductName, actualFirstProductName);
    }


    //SCENARIO 2//
    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String inputText)
    {
        //using the search bar
        CiderObject.usingTheSearchBar(inputText);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults()
    {
        //getting the new page name
        String actualPageName = CiderObject.actualPageName();

        Assertions.assertEquals("Results for\n“" + inputText + "”", actualPageName);
    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastProductsInTheSearchResults(int min)
    {
        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > min);
    }
}