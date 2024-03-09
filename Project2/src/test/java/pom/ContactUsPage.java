package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ContactUsPage {

 private WebDriver driver;

 public ContactUsPage(WebDriver driver) {
     this.driver = driver;
 }

 By contactUsLink = By.cssSelector("a[href*='/contact_us']");
 By contactUsHeader = By.xpath("//h2[contains(., 'Contact ') and ./strong[contains(., 'Us')]]");
 By header = By.xpath("//h2[contains(., 'Get In Touch')]");
 By nameInput = By.cssSelector("input[data-qa='name']");
 By emailInput = By.cssSelector("input[data-qa='email']");
 By subjectInput = By.cssSelector("input[data-qa='subject']");
 By messageInput = By.name("message");
 By fileInput = By.name("upload_file");
 By successMessage = By.xpath("//div[contains(text(),'Success! Your details have been submitted successfully.')]");
 By homeButton = By.linkText("Home");
 By automationExerciseHeader = By.xpath("//h1/span[text()='Automation']/ancestor::h1");

 public void clickContactUsLink() {
     driver.findElement(contactUsLink).click();
 }

 public boolean isContactUsHeaderVisible() {
     return driver.findElement(contactUsHeader).isDisplayed();
 }

 public boolean isHeaderVisible() {
     return driver.findElement(header).isDisplayed();
 }

 public void fillContactForm(String name, String email, String subject, String message, String filePath) {
     driver.findElement(nameInput).sendKeys(name);
     driver.findElement(emailInput).sendKeys(email);
     driver.findElement(subjectInput).sendKeys(subject);
     driver.findElement(messageInput).sendKeys(message);
     driver.findElement(fileInput).sendKeys(filePath);
 }

 public void clickSubmitButton() {
	    WebElement submitButton = driver.findElement(By.name("submit"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
	    Actions actions = new Actions(driver);
	    actions.moveToElement(submitButton).click().perform();
	}

 public boolean isSuccessMessageVisible() {
     return driver.findElement(successMessage).isDisplayed();
 }
 
 public void scrollUpToTop() {
     ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
 }

 public void clickHomeButton() {
     driver.findElement(homeButton).click();
 }

 public boolean isAutomationExerciseHeaderVisible() {
     return driver.findElement(automationExerciseHeader).isDisplayed();
 }
}

