import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CiderObject
{
    static WebDriver driver;

    public CiderObject(WebDriver driver)
    {
        this.driver = driver;
    }

    //accesses category menu and selects specified category
    public void goToCategory(String pageName)
    {
        WebElement navigationName = driver.findElement(By.xpath ("//span[text()='" + pageName + "']"));
        navigationName.click();
    }

    //Expected Category Page Name for the chosen Category
    public String expectedCategoryPageName(String category)
    {
        String pageName = null;

        switch(category)
        {
            case "New In": pageName = "All New In"; break;
            case "Bestsellers": pageName = "All Bestsellers"; break;
            case "Sale": pageName = "Up to 70% Off"; break;
            case "Clothing": pageName = "Everything"; break;
            case "Swimwear": pageName = "Swimwear\uD83D\uDC59"; break;
            case "Curve & Plus": pageName = "Curve & Plus"; break;
            case "Acc & Shoes": pageName = "ACC & Shoes"; break;
        }

        return pageName;
    }

    //Actual Page Name for the shown Category Page
    public String actualPageName()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sort-title-body")));
        String actualPageName = pageNameElement.getText();

        return actualPageName;
    }

    //Waits for product list to load
    public static void productsLoaded()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-list")));
    }

    public void usingTheSearchBar(String inputText)
    {
        //Accessing search bar
        WebElement searchBar = driver.findElement(By.xpath("//div[@class='header-search']//input"));

        searchBar.click();

        //wait for searchbar to load
        CiderObject.searchBarLoaded();

        //Entering search request
        WebElement searchInput = driver.findElement(By.className("search-model-input_inner"));
        searchInput.sendKeys(inputText);
        searchInput.sendKeys(Keys.ENTER);

        //Make sure products are first loaded
        CiderObject.productsLoaded();
    }

    public static void searchBarLoaded()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-model-input_inner")));
    }
}