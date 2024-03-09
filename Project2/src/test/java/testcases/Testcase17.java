package testcases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Testcase17 {
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
	        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");
	        setZoomLevel(driver, 0.50);
        productPage.addToCart(1);
        productPage.clickContinueShoppingButton();
        cartpage.goToCartPage();
        WebElement cartTitle = driver.findElement(By.xpath("//li[contains(text(), 'Shopping Cart')]"));
        Assert.assertTrue(cartTitle.isDisplayed(), "Cart page is not displayed");
        WebElement deleteButton = driver.findElement(By.cssSelector("a.cart_quantity_delete[data-product-id='1']"));
        deleteButton.click();

	    }
        private void setZoomLevel(WebDriver driver, double zoomLevel) {
    		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String script = "document.body.style.transform = 'scale(' + arguments[0] + ')';document.body.style.transformOrigin = '0.50';";
            jsExecutor.executeScript(script, zoomLevel);
    	}
        @AfterClass
        public void close() {
        	driver.quit();
        }
}
