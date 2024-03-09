package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void goToCartPage() {
        driver.findElement(By.cssSelector("li a[href='/view_cart']")).click();
    }
    
    public String verifycartPage() {
         WebElement cartPageTitle = driver.findElement(By.cssSelector("ol.breadcrumb li.active"));
         return cartPageTitle.getText().trim();
    }
    
    public boolean isProductDisplayed(String productName) {
        By productDescriptionLocator = By.xpath("//td[@class='cart_description']//h4/a[contains(text(), '" + productName + "')]");
        return isElementPresent(productDescriptionLocator);
    }

    public String getProductPrice(String productName) {
        By productPriceLocator = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]/../../following-sibling::td[@class='cart_price']//p");
        return getElementText(productPriceLocator);
    }

    public String getProductQuantity(String productName) {
        By productQuantityLocator = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]/../../following-sibling::td[@class='cart_quantity']//button");
        return getElementText(productQuantityLocator);
    }

    public String getProductTotalPrice(String productName) {
        By productTotalPriceLocator = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]/../../following-sibling::td[@class='cart_total']//p[@class='cart_total_price']");
        return getElementText(productTotalPriceLocator);
    }

    private boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String getElementText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isDisplayed() ? element.getText() : "";
    }
    
    public void proceedToCheckout() {
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
        proceedToCheckoutButton.click();
    }
}

