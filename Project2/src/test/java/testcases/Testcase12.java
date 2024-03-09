package testcases;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.CartPage;
import pom.ProductPage;

public class Testcase12 {
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

	        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");
	        
	        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");
        productPage.addToCart(1);
        productPage.clickContinueShoppingButton();
        productPage.addToCart(2);
        cartpage.goToCartPage();
        
        List<WebElement> cartRows = driver.findElements(By.cssSelector("#cart_info_table tbody tr"));
        Assert.assertEquals(cartRows.size(), 2, "Number of products in the cart is incorrect");

        for (WebElement row : cartRows) {
            String productName = row.findElement(By.cssSelector(".cart_description h4 a")).getText();
            String expectedPrice = cartpage.getProductPrice(productName);
            String expectedQuantity = cartpage.getProductQuantity(productName);
            String expectedTotal = cartpage.getProductTotalPrice(productName);
            String actualPrice = cartpage.getProductPrice(productName);
            String actualQuantity = cartpage.getProductQuantity(productName);
            String actualTotal = cartpage.getProductTotalPrice(productName);
            Assert.assertEquals(actualPrice, expectedPrice, "Price is incorrect for product " + productName);
            Assert.assertEquals(actualTotal, expectedTotal, "Total price is incorrect for product " + productName);
            Assert.assertEquals(actualQuantity, expectedQuantity, "Quantity is incorrect for product " + productName);
        }

    }
    	@AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
