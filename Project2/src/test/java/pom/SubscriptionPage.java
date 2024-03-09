package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPage {
	private WebDriver driver;
	
    public SubscriptionPage(WebDriver driver) {
        this.driver = driver;
    } 
    
    public void scrollDownToFooter() {
    	 WebElement footer = driver.findElement(By.id("footer"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
     }
    public boolean isSubscriptionHeaderVisible() {
        return driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();
    }

    public void subscribeWithEmail(String email) {
        driver.findElement(By.id("susbscribe_email")).sendKeys("Yramya@gmail.com");
        driver.findElement(By.id("subscribe")).click();
    }
    public String getSuccessMessage() {
        return driver.findElement(By.id("success-subscribe")).getText();
    }
    
    public void scrollUpToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
    
    By scrollUpArrow = By.id("scrollUp");   
    public void clickScrollUpArrow() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement scrollUpArrowElement = wait.until(ExpectedConditions.elementToBeClickable(scrollUpArrow));
        scrollUpArrowElement.click();
    }

    public boolean isTextVisibleOnScreen(String text) {
        String script = "return typeof arguments[0].scrollIntoViewIfNeeded === 'function' ? "
                      + "arguments[0].scrollIntoViewIfNeeded(true) : "
                      + "arguments[0].scrollIntoView(true);";
        ((JavascriptExecutor) driver).executeScript(script, driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")));
        return driver.getPageSource().contains(text);
    }
}
