package testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basic.DataDriven;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import pom.CartPage;
import pom.CheckoutPage;
import pom.PaymentPage;
import pom.ProductPage;
import pom.RegisterPage;

public class Testcase24 extends DataDriven{
	private WebDriver driver;
	private CartPage cartpage;
	private ProductPage productPage;
	private CheckoutPage checkoutPage;
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
	        cartpage = new CartPage(driver);
	        productPage = new ProductPage(driver);
	        paymentPage = new PaymentPage(driver);
	        checkoutPage = new CheckoutPage(driver);
	        homepage = new RegisterPage(driver);
	        
	    }

	    @Test(priority = 1)
	    public void testHomePage() {
	    	Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
	    }

	    @Test(priority = 2)
        public void addProductsToCart() {
        productPage.addToCart(1);
        productPage.clickContinueShoppingButton();
        cartpage.goToCartPage();
        cartpage.proceedToCheckout();
        checkoutPage.registerAndCheckout();
        }
        @Test(priority = 3, dataProvider = "signUpCredentials")
        public void signupPage(String name, String email) {
            Assert.assertTrue(homepage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
            homepage.enterNameAndEmail(name, email);
            homepage.clickSignUpButton();
            Assert.assertTrue(homepage.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' is not visible");
            homepage.fillAccountInformation("Mrs", "Password", "01/01/1990");
            homepage.selectNewsletterCheckbox();
            homepage.selectSpecialOffersCheckbox();
            homepage.fillAdditionalDetails("ramya", "yarra", "XYZ Corp", "123 Main St", "Apt 456", "USA", "California", "Los Angeles", "90001", "1234567890");
            homepage.clickCreateAccountButton();
            Assert.assertTrue(homepage.isAccountCreatedMessageVisible(), "'ACCOUNT CREATED!' is not visible");
            homepage.clickContinueButton();
            String actualLoggedInText = homepage.getLoggedInText();
            String expectedLoggedInText = "ramya";

            Assert.assertEquals(actualLoggedInText, expectedLoggedInText, "Logged in text does not match.");
        }
        @Test(priority = 4)
        public void toCheckout() {
        	cartpage.goToCartPage();
        	checkoutPage.proceedToCheckout();
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
        	
        	paymentPage.enterNameOnCard("John Doe");
            paymentPage.enterCardNumber("1234567812345678");
            paymentPage.enterCVC("311");
            paymentPage.enterExpiryMonth("12");
            paymentPage.enterExpiryYear("2022");
            paymentPage.clickSubmitButton();
            
        }
	    @Test(priority = 6)
	     public void orderPlaced() {	
	     	Assert.assertTrue(paymentPage.isOrderPlacedMessageDisplayed(), "Expected 'Order Placed!' message to be displayed but it was not found.");
	     	paymentPage.downloadInvoiceButtonClick();
	     	String downloadFolderPath = "Downloads";
	        String expectedFileName = "invoice.txt";
	        Path filePath = FileSystems.getDefault().getPath(downloadFolderPath, expectedFileName);
	        Assert.assertTrue(waitForFileToExist(filePath), "Invoice file is not downloaded");
	        System.out.println("Checking for file existence: " + filePath);
	    }
	    private boolean waitForFileToExist(Path filePath) {
	        int maxAttempts = 10;
	        int attempts = 0;
	        while (attempts < maxAttempts) {
	            if (Files.exists(filePath)) {
	                return true;
	            }
	            try {
	                Thread.sleep(2000);  
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            attempts++;
	        }
	        return false;
	     }
	    @Test(priority = 7)
	     public void testDeleteAccount() {
	    	paymentPage.clickContinueButton();
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
