 package testcases;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.ProductPage;

public class Testcase8 {
	 private WebDriver driver;
	    private ProductPage productPage;
	    
	    @BeforeMethod
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
	    	ChromeOptions opt = new ChromeOptions();
	        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
	        driver = new ChromeDriver(opt);
	        driver.get("http://automationexercise.com");
	        driver.manage().window().maximize();
	        productPage = new ProductPage(driver);
	    }

	    @Test
	    public void testVerifyAllProductsAndProductDetail() {
	
	        WebElement homePageHeader = productPage.getHomePageHeader();
	        Assert.assertTrue(homePageHeader.isDisplayed(), "Home page is not visible successfully");

	        WebElement productsButton = productPage.getProductsButton();
	        productsButton.click();

	        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");
	        
	        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");

	        productPage.getViewProductButton();
	   
	        List<WebElement> productDetails = driver.findElements(By.className("product-details"));
	        Assert.assertTrue(productDetails.size() > 0);
	        
	        Assert.assertTrue(productPage.isProductInfoDisplayed(), "Product information is not displayed correctly");
	    }

	    @AfterMethod
	   public void close() {
	        driver.quit();
	    }
}

