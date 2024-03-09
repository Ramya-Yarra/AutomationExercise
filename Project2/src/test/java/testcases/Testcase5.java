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
import pom.RegisterPage;


public class Testcase5 extends DataDriven {
	private WebDriver driver;
	private RegisterPage homepage;

	    @BeforeClass
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
	    	ChromeOptions opt = new ChromeOptions();
	        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
	        driver = new ChromeDriver(opt);
	        driver.get("http://automationexercise.com");
	        driver.manage().window().maximize();
	        homepage = new RegisterPage(driver);
	    }

    @Test(priority = 1, dataProvider = "RegisterUserWithExistingEmail")
    public void testRegisterUserWithExistingEmail(String name, String email) {
    	homepage.clickSignUpLoginButton();
        Assert.assertTrue(homepage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
        homepage.enterNameAndEmail(name, email);
        homepage.clickSignUpButton();
        Assert.assertTrue(homepage.isErrorVisible("Email Address already exist!"), "'Email Address already exist!' error is not visible");
        ScreenshotUtility.takeScreenshot(driver, "RegisterFailure");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
