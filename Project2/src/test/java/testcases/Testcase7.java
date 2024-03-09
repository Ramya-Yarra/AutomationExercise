package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.LoginPage;

public class Testcase7 {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testVerifyTestCasesPage() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        loginPage.clickTestCasesLink();
        Assert.assertTrue(loginPage.isBoldElementDisplayed(), "<b> element with the text 'Test Cases' is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
