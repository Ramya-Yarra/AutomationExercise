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
import pom.ContactUsPage;

public class Testcase6 extends DataDriven {

    private WebDriver driver;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        contactUsPage = new ContactUsPage(driver);
    }

    @Test(priority = 1)
    public void testHomePage() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    @Test(priority = 2, dataProvider = "ContactUsForm")
    public void testContactUsForm(String name, String email, String subject, String message, String filePath) {
        contactUsPage.clickContactUsLink();
        Assert.assertTrue(contactUsPage.isContactUsHeaderVisible(), "'Contact Us' header is not visible");
        Assert.assertTrue(contactUsPage.isHeaderVisible(), "'GET IN TOUCH' header is not visible");
        contactUsPage.fillContactForm(name, email, subject, message, filePath);
        contactUsPage.clickSubmitButton();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message is not visible");
        contactUsPage.scrollUpToTop();
        ScreenshotUtility.takeScreenshot(driver, "ContactForm");
        contactUsPage.clickHomeButton();
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
