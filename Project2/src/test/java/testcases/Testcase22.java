package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.ProductPage;
import pom.RecommendedItemsPage;

public class Testcase22 {
    private WebDriver driver;
    private RecommendedItemsPage recommendedPage;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
    	ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        recommendedPage = new RecommendedItemsPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test(priority = 1)
    public void testecommendedItems() {
    	recommendedPage.scrollDownToRecommendedItems();
    	productPage.addToCart(5); 
    	productPage.viewCart();
        String expectedProduct = "Winter Top";
        Assert.assertTrue(driver.getPageSource().contains(expectedProduct), expectedProduct + " is not displayed in the cart after adding from Recommended Items.");
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
