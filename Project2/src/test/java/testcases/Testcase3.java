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
import basic.ScreenshotUtility;
import pom.LoginPage;

public class Testcase3 extends DataDriven {
	
	private WebDriver driver;
	 private LoginPage loginPage;

	    @BeforeClass
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
	    	ChromeOptions opt = new ChromeOptions();
	        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
	        driver = new ChromeDriver(opt);
	        loginPage = new LoginPage(driver);
	        driver.get("http://automationexercise.com");
	        driver.manage().window().maximize();
	    }

	    @Test(dataProvider = "invalidLoginCredentials")
	    public void testLoginWithIncorrectCredentials(String email, String password) {
	        loginPage.clickOnSignupLoginButton();
	        loginPage.verifyLoginToYourAccountIsVisible();
	        loginPage.enterEmail(email);
	        loginPage.enterPassword(password);
	        loginPage.clickOnLoginButton();
	        Assert.assertTrue(loginPage.isErrorVisible("Your email or password is incorrect!"));
	        ScreenshotUtility.takeScreenshot(driver, "LoginFailure");
	    }
	    
	    @AfterClass
	    public void tearDown() {
	    	if (driver != null) {
	            driver.quit();
	        }
	    }
}
