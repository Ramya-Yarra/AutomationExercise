package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BrandProductsPage {
	private WebDriver driver;

    public BrandProductsPage(WebDriver driver) {
        this.driver = driver;
    }
    public void isBrandsDisplayed() {
    	WebElement Brands = driver.findElement(By.xpath("//h2[contains(text(), 'Brands')]"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Brands);
        Actions actions = new Actions(driver);
        actions.moveToElement(Brands).perform();
    }
    public void isnavigatedtobrandpage() {
    	WebElement POLOBrand = driver.findElement(By.xpath("//a[@href='/brand_products/Polo']"));
        POLOBrand.click();
    }
    public boolean isPOLOBrandpageisdisplayed() {
    	WebElement POLOBrandpage = driver.findElement(By.xpath("//h2[contains(text(), 'Brand - Polo Products')]"));
    	return POLOBrandpage.isDisplayed();
    }
    public void isnavigatedtobrandpage2() {
    	WebElement HMBrand = driver.findElement(By.cssSelector("a[href*='/brand_products/H&M']"));

    	HMBrand.click();
    }
	public boolean isHMBrandpageisdisplayed() {
    	WebElement HMBrandPage = driver.findElement(By.xpath("//h2[contains(text(), 'Brand - H&M Products')]"));
    	return HMBrandPage.isDisplayed();
    }   
}
