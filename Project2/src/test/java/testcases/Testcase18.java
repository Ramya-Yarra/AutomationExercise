package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.CategoryPage;

public class Testcase18 {
    private WebDriver driver;
    private CategoryPage categoryPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        categoryPage = new CategoryPage(driver);
    }

    @Test
    public void testViewCategoryProducts() {
    	
    	categoryPage.isCategoryTitleDisplayed();
    	categoryPage.iswomenCategory();
    	categoryPage.iswomenSubCategory();
    	categoryPage.Womencategorypageisdisplayed();
    	categoryPage.ismenCategory();
    	categoryPage.ismenSubCategory();
    	String subCategoryTitle = categoryPage.ismenSubCategoryTitle();
        Assert.assertEquals(subCategoryTitle, "MEN - TSHIRTS PRODUCTS", "Sub-category title is incorrect"); 	
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
