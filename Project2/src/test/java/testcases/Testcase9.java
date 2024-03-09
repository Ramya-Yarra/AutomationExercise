package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.ProductPage;

public class Testcase9 {
    private WebDriver driver;
    private ProductPage productPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
    }

    @Test
    public void testSearchProduct() {
    	Assert.assertTrue(productPage.getHomePageHeader().isDisplayed(), "Home page is not visible successfully");
        productPage.getProductsButton().click();
        
        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");
        
        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");

        WebElement searchInput = productPage.getSearchInput();
        WebElement searchButton = productPage.getSearchButton();
 
        searchInput.sendKeys("Tshirt");
        searchButton.click();

        WebElement searchedProductsHeader = productPage.getSearchedProductsHeader();
        Assert.assertTrue(searchedProductsHeader.isDisplayed(), "'SEARCHED PRODUCTS' is not visible");

        WebElement productsList = productPage.getProductsList();
        Assert.assertTrue(productsList.isDisplayed(), "Products list is not visible");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
