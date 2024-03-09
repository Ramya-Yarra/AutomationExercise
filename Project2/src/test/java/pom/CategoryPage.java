package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void isCategoryTitleDisplayed() {
    	WebElement CategoryTitle = driver.findElement(By.className("left-sidebar"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CategoryTitle);
    	CategoryTitle.isDisplayed();
    }

    public void iswomenCategory() {
    	 WebElement womenCategory = driver.findElement(By.xpath("//a[@href='#Women']"));
    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
         Actions actions = new Actions(driver);
         actions.moveToElement(womenCategory).click().perform();
    }
    public void iswomenSubCategory() {
    	WebElement TopsCategoryLink = driver.findElement(By.xpath("//a[contains(text(), 'Tops')]"));
    	TopsCategoryLink.click();
    }
    By TopsCategorypage = By.xpath("//h2[contains(text(), 'Women - Tops Products')]");
    public boolean Womencategorypageisdisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement Topspage = wait.until(ExpectedConditions.elementToBeClickable(TopsCategorypage));
    	return Topspage.isDisplayed();
    }
    public void ismenCategory() {
   	 WebElement womenCategory = driver.findElement(By.xpath("//a[@href='#Men']"));
        womenCategory.click();
   }
    public void ismenSubCategory() {   	
    	WebElement menSubCategory = driver.findElement(By.xpath("//a[contains(text(), 'Tshirts')]"));
    	menSubCategory.click();
    }
    By subCategoryTitleElement = By.cssSelector("h2.title.text-center");
    public String ismenSubCategoryTitle() {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	 WebElement subCategoryTitle = wait.until(ExpectedConditions.elementToBeClickable(subCategoryTitleElement));
    	    return subCategoryTitle.getText();     
    }
}