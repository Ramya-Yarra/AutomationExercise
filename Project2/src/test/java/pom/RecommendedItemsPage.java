package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecommendedItemsPage {

    private WebDriver driver;
    public RecommendedItemsPage(WebDriver driver) {
        this.driver = driver;
    }

    By recommendedItemsSection = By.xpath("//div[@class='recommended_items']");
    By viewCartButton = By.linkText("View Cart");

    public boolean scrollDownToRecommendedItems() {
        WebElement recommendedItems = driver.findElement(recommendedItemsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedItems);
		return recommendedItems.isDisplayed();
    }
}
