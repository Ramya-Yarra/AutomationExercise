package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basic.DataDriven;
import pom.LoginPage;

public class Testcase4 extends DataDriven {
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

    @Test(dataProvider = "loginCredentials")
    public void testLogoutUser(String email, String password) {
        loginPage.clickOnSignupLoginButton();
        loginPage.verifyLoginToYourAccountIsVisible();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        loginPage.verifyLoggedInAsUsernameIsVisible();
        LoginPage loginPageAfterLogout = loginPage.clickOnLogoutButton();
        loginPageAfterLogout.verifyLoginToYourAccountIsVisible();
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

