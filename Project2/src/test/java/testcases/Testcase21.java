package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basic.DataDriven;
import pom.ProductPage;

public class Testcase21 extends DataDriven{
	private WebDriver driver;
    private ProductPage productPage;

	@BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
	}
	
	 @Test(dataProvider = "WriteYourReview")
	    public void viewProduct(String name, String email, String review) {
	    	Assert.assertTrue(productPage.getHomePageHeader().isDisplayed(), "Home page is not visible successfully");
	        productPage.getProductsButton().click();      
	        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully"); 
	        productPage.getViewProductButton();	        
	        Assert.assertTrue(productPage.isWriteYourReviewDisplayed(),"Write Your Review is not visible");  
	        productPage.enterNameAndEmail(name, email, review);
	        productPage.submitReview();
	 }	
	 
	 @AfterClass
	 public void tearDown() {
	        driver.quit();
	    }
}
