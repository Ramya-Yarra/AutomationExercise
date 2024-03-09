package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import pom.CartPage;
import pom.ProductPage;

public class Testcase13 {
    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartpage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        cartpage = new CartPage(driver);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
    }

    @Test
    public void testAddProductsToCart() {

        WebElement homePageHeader = productPage.getHomePageHeader();
        Assert.assertTrue(homePageHeader.isDisplayed(), "Home page is not visible successfully");

        WebElement productsButton = productPage.getProductsButton();
        productsButton.click();

        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(), "User is not navigated to ALL PRODUCTS page successfully");

        productPage.getViewProductButton();

        List<WebElement> productDetails = driver.findElements(By.className("product-details"));
        Assert.assertTrue(productDetails.size() > 0);
        Assert.assertTrue(productPage.isProductInfoDisplayed(), "Product information is not displayed correctly");

        productPage.increaseProductQuantity(4);
        String productName = "Blue Top";
        int expectedQuantity = 4;
        driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
        cartpage.goToCartPage();
        WebElement viewCartLink = driver.findElement(By.cssSelector("a[href='/view_cart']"));
        viewCartLink.click();
            
        Assert.assertTrue(cartpage.isProductDisplayed(productName), "Product is not displayed in the cart");
        Assert.assertEquals(cartpage.getProductQuantity(productName), String.valueOf(expectedQuantity), "Product quantity in the cart is not as expected");
    }
    @AfterClass
    public void close() {
    	driver.quit();
    }
}
