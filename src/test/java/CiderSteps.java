import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CiderSteps {

    WebDriver driver;
    CiderObject CiderObject;

    private String expectedFirstProductName;

    private String inputText;

    private List<WebElement> products;

    @Before
    public void setup()
    {
        //driver setup
        System.setProperty("webdriver.chrome.driver", "C:/Users/emagr/webtesting/chromedriver.exe/");
        driver = new ChromeDriver();
    }

    @After
    public void teardown()
    {
        driver.quit();
    }

    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite()
    {
        CiderObject = new CiderObject(driver);
    }

    @When("I visit the website")
    public void iVisitTheWebsite()
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
    public void theCategoryShouldShowAtLeastMinProducts(int min)
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
    @And("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTermInputText(String inputText)
    {
        //using the search bar
        CiderObject.usingTheSearchBar(inputText);
    }

    @Then("I should see the {string} search results")
    public void iShouldSeeTheInputTextSearchResults(String inputText)
    {
        //getting the new page name
        String actualPageName = CiderObject.actualPageName();

        Assertions.assertEquals("Results for\n“" + inputText + "”", actualPageName);
    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastMinProductsInTheSearchResults(int min)
    {
        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > min);
    }
}
