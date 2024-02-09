import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        String actualPageName = CiderObject.actualCategoryPageName();

        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    //To test if more than the minimum number products are being output
    @Test
    public void minimumProductsAreMoreThan()
    {
        //Set minimum
        int minimum = 5;

        //Going to specific Category
        CiderObject.goToCategory("Sale");

        //Make sure products are first loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-list")));

        //Find all instances of products in the product list
        List<WebElement> products = driver.findElements(By.className("product-list-item-box"));

        //number of products in list
        int noOfProducts = products.size();

        Assertions.assertTrue(noOfProducts > minimum);
    }

    /*
    @Test
    public void accessSpecifiedProduct()
    {

    }

    @Test
    public void successfulSearchResults()
    {

    } */
}