package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.BrandProductsPage;
import pom.ProductPage;

public class Testcase19 {
	private WebDriver driver;
    private ProductPage productPage;
    private BrandProductsPage brandproduct;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
        brandproduct = new BrandProductsPage(driver);
    }
    @Test
    public void testAddProductsToCart() {
	        WebElement productsButton = productPage.getProductsButton();
	        productsButton.click();
	        driver.navigate().refresh();
	        brandproduct.isBrandsDisplayed();

	        brandproduct.isnavigatedtobrandpage();
	        brandproduct.isPOLOBrandpageisdisplayed();
	        brandproduct.isnavigatedtobrandpage2();
	        brandproduct.isHMBrandpageisdisplayed();
	    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
