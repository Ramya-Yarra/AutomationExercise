package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	private WebDriver driver;
	
	public ProductPage(WebDriver driver) {
        this.driver = driver;
        new Actions(driver);
    }
    
    public WebElement getHomePageHeader() {
        return driver.findElement(By.xpath("//h1/span[text()='Automation']/ancestor::h1"));
    }

    public WebElement getProductsButton() {
        return driver.findElement(By.cssSelector("a[href='/products']"));
    }

    public WebElement getProductsList() {
        return driver.findElement(By.xpath("//div[@class='productinfo text-center']"));
    }
    
    public WebElement getAllProductsPageHeader() {
        return waitForElementVisibility(By.xpath("//h2[@class='title text-center' and contains(text(),'All Products')]"), 10);
    }

    public void getViewProductButton() {
    	WebElement ViewProduct = driver.findElement(By.cssSelector("a[href='/product_details/1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ViewProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(ViewProduct).click().perform();
    }

    public WebElement getSearchInput() {
        return driver.findElement(By.id("search_product"));
    }

    public WebElement getSearchButton() {
        return driver.findElement(By.xpath("//*[@id=\"submit_search\"]"));
    }

    public WebElement getSearchedProductsHeader() {
        return driver.findElement(By.xpath("//h2[@class='title text-center' and contains(text(),'Searched Products')]"));
    }

    private WebElement waitForElementVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void addToCart(int productId) {
    	String productSelector = String.format("a[data-product-id='%d']", productId);
        WebElement product = driver.findElement(By.cssSelector(productSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
        Actions actions = new Actions(driver);
        actions.moveToElement(product).click().perform();
    }
    
    By viewCartButton = By.linkText("View Cart");
    public void viewCart() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement viewCartElement = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCartElement.click();
    }

    public void clickContinueShoppingButton() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-confirm")));
        WebElement continueShoppingButton = modal.findElement(By.className("close-modal"));
        continueShoppingButton.click();
    }
    
    public WebElement getProductName() {
        return driver.findElement(By.xpath("//div[@class='product-information']/h2[text()='Blue Top']"));
    }

    public WebElement getProductCategory() {
        return driver.findElement(By.xpath("//div[@class='product-information']/p[text()='Category: Women > Tops']"));
    }

    public WebElement getProductPrice() {
        return driver.findElement(By.xpath("//div[@class='product-information']/span/span[text()='Rs. 500']"));
    }

    public WebElement getProductAvailability() {
        return driver.findElement(By.xpath("//div[@class='product-information']/p[b[text()='Availability:']]"));
    }

    public WebElement getProductCondition() {
        return driver.findElement(By.xpath("//div[@class='product-information']/p[b[text()='Condition:']]"));
    }

    public WebElement getProductBrand() {
        return driver.findElement(By.xpath("//div[@class='product-information']/p[b[text()='Brand:']]"));
    }
    
    public void increaseProductQuantity(int quantity) {
        WebElement quantityInput = driver.findElement(By.id("quantity"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public boolean isProductInfoDisplayed() {
        return getProductName().isDisplayed() &&
                getProductCategory().isDisplayed() &&
                getProductPrice().isDisplayed() &&
                getProductAvailability().isDisplayed() &&
                getProductCondition().isDisplayed() &&
                getProductBrand().isDisplayed();
    }
    
    public boolean isWriteYourReviewDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement reviewDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#reviews']")));
    	return reviewDisplay.isDisplayed();
    }
    
    public void enterNameAndEmail(String name, String email, String review) {
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement reviewInput = driver.findElement(By.id("review"));

        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        reviewInput.sendKeys(review);
    }
    
    public void submitReview() {
        WebElement buttonreview = driver.findElement(By.id("button-review"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonreview);
        Actions actions = new Actions(driver);
        actions.moveToElement(buttonreview).click().perform();
    }
}
