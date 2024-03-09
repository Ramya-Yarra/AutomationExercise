package testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.SubscriptionPage;

public class Testcase10 {
	private WebDriver driver;
    private SubscriptionPage subscriptionpage;

    @BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
    	ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        subscriptionpage = new SubscriptionPage(driver);
        driver.navigate().refresh();
    }

    @Test
    public void testSubscription() {

    	Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    	Assert.assertNotNull(subscriptionpage, "SubscriptionPage object is not initialized");
    	subscriptionpage.scrollDownToFooter();

        Assert.assertTrue(subscriptionpage.isSubscriptionHeaderVisible(), "'SUBSCRIPTION' text is not visible.");

        subscriptionpage.subscribeWithEmail("test@example.com");

        String successMessage = subscriptionpage.getSuccessMessage();
        Assert.assertEquals(successMessage, "You have been successfully subscribed!","Success message is incorrect.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
