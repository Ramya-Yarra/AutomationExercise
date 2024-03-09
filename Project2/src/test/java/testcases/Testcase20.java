package testcases;

import org.testng.annotations.BeforeClass;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import basic.DataDriven;
import pom.CartPage;
import pom.LoginPage;
import pom.ProductPage;

public class Testcase20 extends DataDriven {
	private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartpage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
        cartpage = new CartPage(driver);
        loginPage = new LoginPage(driver);
    }
    @Test(priority = 1)
    public void testSearchProduct() {
    	Assert.assertTrue(productPage.getHomePageHeader().isDisplayed(), "Home page is not visible successfully");
        productPage.getProductsButton().click();      
        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");        
        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");
        WebElement searchInput = productPage.getSearchInput();
        WebElement searchButton = productPage.getSearchButton();
        searchInput.sendKeys("Dress ");
        searchButton.click();
        WebElement searchedProductsHeader = productPage.getSearchedProductsHeader();
        Assert.assertTrue(searchedProductsHeader.isDisplayed(), "'SEARCHED PRODUCTS' is not visible");
        WebElement productsList = productPage.getProductsList();
        Assert.assertTrue(productsList.isDisplayed(), "Products list is not visible");        
        productPage.addToCart(3);
        productPage.clickContinueShoppingButton();
        productPage.addToCart(4);
        productPage.clickContinueShoppingButton();
        productPage.addToCart(38);
        productPage.clickContinueShoppingButton();
        cartpage.goToCartPage();
    }
    @Test(priority = 2, dataProvider = "loginCredentials")
    public void LoginWithCredentials(String email, String password) {
    	loginPage.clickOnSignupLoginButton();
    	loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        cartpage.goToCartPage();
    }
    @Test(priority = 3)
    public void verifyCart() {
    	String product1Name = "Sleeveless Dress";
        String product2Name = "Stylish Dress";
        String product3Name = "Rose Pink Embroidered Maxi Dress";

        Assert.assertTrue(driver.getPageSource().contains(product1Name),
                product1Name + " is not visible in the cart after login.");
        Assert.assertTrue(driver.getPageSource().contains(product2Name),
                product2Name + " is not visible in the cart after login.");
        Assert.assertTrue(driver.getPageSource().contains(product3Name),
                product3Name + " is not visible in the cart after login.");
    }    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
