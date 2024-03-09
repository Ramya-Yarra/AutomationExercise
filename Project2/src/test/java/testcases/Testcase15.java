package testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
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
import pom.PaymentPage;
import pom.ProductPage;
import pom.RegisterPage;

public class Testcase15 extends DataDriven{
	private WebDriver driver;
    private CheckoutPage checkoutPage;
    private CartPage cartpage;
    private ProductPage productPage;
    private PaymentPage paymentPage;
    private RegisterPage homepage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        checkoutPage = new CheckoutPage(driver);
        productPage = new ProductPage(driver);
        cartpage = new CartPage(driver);
        paymentPage = new PaymentPage(driver);
        homepage = new RegisterPage(driver);
        
    }  
    	@Test(priority = 1)
        public void testHomePage() {
        	Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        }
    	
    	@Test(priority = 2, dataProvider = "signUpCredentials")
        public void signupPage(String name, String email) {
    		homepage.clickSignUpLoginButton();
            Assert.assertTrue(homepage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
            homepage.enterNameAndEmail(name, email);
            homepage.clickSignUpButton();
            Assert.assertTrue(homepage.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' is not visible");
            homepage.fillAccountInformation("Mr","Password", "01/01/1990");
            homepage.selectNewsletterCheckbox();
            homepage.selectSpecialOffersCheckbox();
            homepage.fillAdditionalDetails("ramya", "yarra", "XYZ Corp", "123 Main St", "Apt 456", "USA", "California", "Los Angeles", "90001", "1234567890");
            homepage.clickCreateAccountButton();
            Assert.assertTrue(homepage.isAccountCreatedMessageVisible(), "'ACCOUNT CREATED!' is not visible");
            homepage.clickContinueButton();
            setZoomLevel(driver, 0.50);
            String actualLoggedInText = homepage.getLoggedInText();
            String expectedLoggedInText = "ramya";
            Assert.assertEquals(actualLoggedInText, expectedLoggedInText, "Logged in text does not match.");
        }

        @Test(priority = 3)
        public void addProducts() {
            WebElement productsButton = productPage.getProductsButton();
	        productsButton.click();

	        Assert.assertTrue(productPage.getAllProductsPageHeader().isDisplayed(),"User is not navigated to ALL PRODUCTS page successfully");
	        
	        Assert.assertTrue(productPage.getProductsList().isDisplayed(), "Products list is not visible");
        productPage.addToCart(1);
        productPage.clickContinueShoppingButton();
        cartpage.goToCartPage();
        String actualBreadcrumb = cartpage.verifycartPage();
        String expectedBreadcrumb = "Shopping Cart";
        Assert.assertEquals(actualBreadcrumb, expectedBreadcrumb, "Active breadcrumb does not match.");
        cartpage.proceedToCheckout();
        }
        
        @Test(priority = 4)
        public void toCheckout() {
        	setZoomLevel(driver, 0.50);
        	Assert.assertTrue(checkoutPage.getDeliveryAddressTitleElement().isDisplayed(), "Delivery address title is not displayed");
        	Assert.assertTrue(checkoutPage.getDeliveryAddressElement().isDisplayed(), "Delivery address content is not displayed");
        	Assert.assertTrue(checkoutPage.getBillingAddressTitleElement().isDisplayed(), "Billing address title is not displayed");
            Assert.assertTrue(checkoutPage.getBillingAddressElement().isDisplayed(), "Billing address content is not displayed");
            Assert.assertTrue(checkoutPage.isReviewOrderHeadingDisplayed(), "Review Your Order heading is not displayed.");;
            boolean isProductDetailsMatch = checkoutPage.verifyProductDetails("Blue Top", "Women > Tops", "Rs. 500", "1", "Rs. 500");
            Assert.assertTrue(isProductDetailsMatch, "Product details in the cart do not match.");
            String orderComment = "Please deliver the items as soon as possible.";
            checkoutPage.enterOrderComment(orderComment);
            checkoutPage.clickPlaceOrderButton();  	
        }
        @Test(priority = 5)
        public void Payment() {       	
        	paymentPage.enterNameOnCard("Ramya Yarra");
            paymentPage.enterCardNumber("1234567812345678");
            paymentPage.enterCVC("123");
            paymentPage.enterExpiryMonth("12");
            paymentPage.enterExpiryYear("2024");
            paymentPage.clickSubmitButton();
            
        }      
        @Test(priority = 6)
        public void orderPlaced() {	
        	Assert.assertTrue(paymentPage.isOrderPlacedMessageDisplayed(),
                    "Expected 'Order Placed!' message to be displayed but it was not found.");
        }
        @Test(priority = 7)
        public void testDeleteAccount() {
        	homepage.clickDeleteAccountButton();
            Assert.assertTrue(homepage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible");
            homepage.clickContinueButton();
        }
        private void setZoomLevel(WebDriver driver, double zoomLevel) {
    		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String script = "document.body.style.transform = 'scale(' + arguments[0] + ')';document.body.style.transformOrigin = '0.50';";
            jsExecutor.executeScript(script, zoomLevel);
    	}
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
