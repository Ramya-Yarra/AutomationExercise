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

public class Testcase26 {
	private WebDriver driver;
	private SubscriptionPage subscriptionpage;

	@BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
    	ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("./Extensions/uBlock Origin 1.56.0.0.crx"));
        driver = new ChromeDriver(opt);
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();
        subscriptionpage = new SubscriptionPage(driver);
	}
	@Test(priority = 1)
    public void testVerifyScrollUpAndDown() {
		Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
		Assert.assertNotNull(subscriptionpage, "SubscriptionPage object is not initialized");
    	subscriptionpage.scrollDownToFooter();
    	Assert.assertTrue(subscriptionpage.isSubscriptionHeaderVisible(), "'SUBSCRIPTION' text is not visible.");
    	subscriptionpage.scrollUpToTop();
    	String expectedText = "Full-Fledged practice website for Automation Engineers";
        Assert.assertTrue(subscriptionpage.isTextVisibleOnScreen(expectedText),expectedText + " is not visible on the screen after scrolling up.");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
