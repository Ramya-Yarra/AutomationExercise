package testcases;

import java.io.File;

import org.openqa.selenium.Alert;
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

public class Testcase1<assertTrue> extends DataDriven {

    private WebDriver driver;
    private RegisterPage homepage;
    
	@BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
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
        Assert.assertTrue(homepage.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' is not visible");
        homepage.fillAccountInformation("Mr", "Password", "01/01/1990");
        homepage.selectNewsletterCheckbox();
        homepage.selectSpecialOffersCheckbox();
        homepage.fillAdditionalDetails("ramya", "yarra", "XYZ Corp", "123 Main St", "Apt 456", "USA", "California", "Los Angeles", "90001", "1234567890");
        homepage.clickCreateAccountButton();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertTrue(homepage.isAccountCreatedMessageVisible(), "'ACCOUNT CREATED!' is not visible");
        ScreenshotUtility.takeScreenshot(driver, "AccountCreated");
        homepage.clickContinueButton();
        String actualLoggedInText = homepage.getLoggedInText();
        String expectedLoggedInText = "ramya";

        Assert.assertEquals(actualLoggedInText, expectedLoggedInText, "Logged in text does not match.");
    }

    @Test(priority = 3)
    public void testDeleteAccount() {
    	homepage.clickDeleteAccountButton();
        Assert.assertTrue(homepage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible");
        ScreenshotUtility.takeScreenshot(driver, "AccountDeleted");
        homepage.clickContinueButton();
    }
	@AfterClass
    public void tearDown() {
        driver.quit();
    }
}