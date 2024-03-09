package testcases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basic.DataDriven;
import pom.CartPage;
import pom.CheckoutPage;
import pom.LoginPage;
import pom.ProductPage;
import pom.RegisterPage;

public class Testcase23 extends DataDriven {
private WebDriver driver;
private CartPage cartpage;
private ProductPage productPage;
private CheckoutPage checkoutPage;
private LoginPage loginPage;
private RegisterPage homepage;
    
	@BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        cartpage = new CartPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);
        homepage = new RegisterPage(driver);
        
    }

    @Test(priority = 1)
    public void testHomePage() {
    	Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    @Test(priority = 2, dataProvider = "signUpCredentials")
    public void testSignUp(String name, String email) {
    	homepage.clickSignUpLoginButton();
        Assert.assertTrue(homepage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
        homepage.enterNameAndEmail(name, email);
        homepage.clickSignUpButton();

        homepage.fillAccountInformation("Mr", "Password", "01/01/1990");
        
        homepage.fillAdditionalDetails("ramya", "yarra", "XYZ Corp", "123 Main St", "Apt 456", "USA", "California", "Los Angeles", "90001", "1234567890");
        homepage.clickCreateAccountButton();
        Assert.assertTrue(homepage.isAccountCreatedMessageVisible(), "'ACCOUNT CREATED!' is not visible");
        
        homepage.clickContinueButton();
        String actualLoggedInText = homepage.getLoggedInText();
        String expectedLoggedInText = "ramya";

        Assert.assertEquals(actualLoggedInText, expectedLoggedInText, "Logged in text does not match.");
    }
    @Test(priority = 3)
    public void testAddProductsToCart() {
    	 WebElement homePageHeader = productPage.getHomePageHeader();
	        Assert.assertTrue(homePageHeader.isDisplayed(), "Home page is not visible successfully");

	        WebElement productsButton = productPage.getProductsButton();
	        productsButton.click();
	        
	        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");
	        
	        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");
        productPage.addToCart(1);
        productPage.clickContinueShoppingButton();
        cartpage.goToCartPage();
        WebElement cartTitle = driver.findElement(By.xpath("//li[contains(text(), 'Shopping Cart')]"));
        Assert.assertTrue(cartTitle.isDisplayed(), "Cart page is not displayed");
        cartpage.proceedToCheckout();
        
        checkoutPage.DeliveryAddressElement();
        checkoutPage.BillingAddressElement();
    }
    
    @Test(priority = 4)
    public void addressCheck() {
    	String registrationAddress = "123 Main St, Apt 456, Los Angeles, California, 90001, USA"; 
    	String actualDeliveryAddress = driver.findElement(By.id("address_delivery")).getText(); 
    	if (actualDeliveryAddress.equals(registrationAddress)) {
    	    System.out.println("Delivery address verification successful.");
    	} else {
    	    System.out.println("Delivery address verification failed.");
    	}
    	
    	String actualBillingAddress = driver.findElement(By.id("address_invoice")).getText(); 
    	if (actualBillingAddress.equals(registrationAddress)) {
    	    System.out.println("Billing address verification successful.");
    	} else {
    	    System.out.println("Billing address verification failed.");
    	}
     }
    @Test(priority = 5)
    public void testDeleteAccount() {
    	loginPage.clickOnDeleteAccountButton();
        loginPage.verifyAccountDeletedIsVisible();
        homepage.clickContinueButton();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


