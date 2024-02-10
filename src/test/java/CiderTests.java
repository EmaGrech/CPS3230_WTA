import org.junit.jupiter.api.AfterEach;
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
}